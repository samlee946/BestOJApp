<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="userList.title"/></title>
    <meta name="heading" content="<fmt:message key='userList.heading'/>"/>
    <meta name="menu" content="${param.menu}"/>
    <meta name="submenu" content="${param.submenu}"/>
</head>
 
<display:table name="users" cellspacing="0" cellpadding="0" requestURI="common/userList.html"
    defaultsort="1" id="theUser" pagesize="25" class="table" export="true">
    <display:column title="<input type='checkbox' name='allbox' onClick='checkAll(pform);'/>"
            sortable="false" headerClass="sortable" style="width: 20px;text-align:center;" media="html">
        <input type="checkbox" name="heroBoardIDs" value="${user.id}"/>
    </display:column>
    <display:column property="username" escapeXml="true" sortable="true" titleKey="user.username" style="width:200px;text-align:center;"
        url="/user_user_view_PUBLIC.html?from=list&menu=${param.menu}&submenu=${param.submenu}" paramId="id" paramProperty="id"/>
	<c:choose>
	    <c:when test="${theUser.pkuAccountValid && theUser.pkuAccountStatus==1}">
	    	<display:column property="pkuAccount" style='color:blue;font-weight:normal;text-align:center;width:120px;'/>
	    </c:when>
	    <c:when test="${theUser.pkuAccountValid && theUser.pkuAccountStatus==0}">
	    	<display:column property="pkuAccount" style='color:gray;font-weight:normal;text-align:center;width:120px;'/>
	    </c:when>
	    <c:when test="${theUser.pkuAccountValid && theUser.pkuAccountStatus==2}">
	    	<display:column property="pkuAccount" style='color:black;font-weight:normal;text-align:center;width:120px;'/>
	    </c:when>
	    <c:otherwise><display:column value=""/></c:otherwise>
	</c:choose>
    <display:column property="email" sortable="true" titleKey="user.email" autolink="true" media="html"/>
    <display:column property="email" titleKey="user.email" media="csv xml excel pdf"/>

    <display:setProperty name="paging.banner.item_name" value="user"/>
    <display:setProperty name="paging.banner.items_name" value="users"/>

    <display:setProperty name="export.excel.filename" value="User List.xls"/>
    <display:setProperty name="export.csv.filename" value="User List.csv"/>
    <display:setProperty name="export.pdf.filename" value="User List.pdf"/>
</display:table>

<script type="text/javascript">
    //highlightTableRows("users");
</script>

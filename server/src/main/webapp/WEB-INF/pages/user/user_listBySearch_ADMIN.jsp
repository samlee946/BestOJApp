<%@ include file="/common/taglibs.jsp"%>
<%@page import="com.unlimited.oj.model.*"%>
<%@page import="com.unlimited.oj.util.*"%>

<head>
    <title><fmt:message key="userList.title"/></title>
    <meta name="heading" content="<fmt:message key='userList.heading'/>"/>
    <script type="text/javascript" src="<c:url value='/scripts/selectbox.js'/>"></script>

</head>
 
<body>
<c:set var="buttons">
   <table><tr>
           <td>
              <s:form action="user_user_userListManager_ADMIN" method="post">
                   <s:hidden key="searchKey" value="username"/>
               <div>
                   <table>
                       <tr>
                        <td>
                            <s:textfield name="searchValue" cssClass="text medium" required="false"/>
                        </td>
                        <td>
                           <s:submit key="button.search"/>
                        </td>
                       </tr>   
                    </table>
               </div>
            </s:form>
           </td>
             <td valign="top">
                    <div style="margin-top:3px;">
                    <input type="button" style="margin-right: 5px"
                        onclick="location.href='<c:url value="/user_user_edit_ADMIN.html?method=Add&from=list&menu=${param.menu}&submenu=${param.submenu}"/>'"
                        value="<fmt:message key="button.add"/>"/>
                    </div>
   		</td>
   	</tr></table>
</c:set>
<c:out value="${buttons}" escapeXml="false" />
<display:table name="users" cellspacing="0" cellpadding="0" requestURI="/user_user_userListManager_ADMIN.html"
    defaultsort="1" id="theUser" pagesize="25" class="table" export="true">
    <%
        String color = "#606060;";
        pageContext.setAttribute("color", color);
    %>

    <display:column title="<input type='checkbox' name='allbox' onClick='checkAll(pform);'/>"
            sortable="false" headerClass="sortable" style="width: 20px;text-align:center;" media="html">
        <input type="checkbox" name="heroBoardIDs" value="${user.id}"/>
    </display:column>
    <display:column property="username" sortable="true" titleKey="user.username" style="width:100px;text-align:center;font-weight: bold;color:${color}"/>
    <display:column property="nick" sortable="true" titleKey="user.nick" style="width:100px;text-align:center;font-weight: bold;color:${color}"/>
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
    <display:column property="className" sortable="false" titleKey="user.className" style="width: 120px; text-align:center;"/>
    <display:column property="studentNumber" sortable="false" titleKey="user.studentNumber" style="width: 120px; text-align:center;"/>
    <display:column property="fullName" sortable="false" titleKey="user.fullName" style="width: 80px; text-align:center;"/>
    <display:column property="email" titleKey="user.email" media="csv xml excel pdf"/>
    <display:column sortable="false" titleKey="user.enabled" style="width: 40px; text-align:center;" url="/user_user_view_PUBLIC.html?from=list" paramId="id" paramProperty="id">
        <c:choose>
            <c:when test="${theUser.enabled==true}"><img src="/${appConfig['project.name']}/images/accepted.gif" style="border:0"/></c:when>
        </c:choose>
    </display:column>
    <display:column titleKey="list.operate" sortable="false" headerClass="sortable" style="width:100px; word-break:break-all;">
         	<table style="width:100%; border:0; margin:0; padding:0;"><tr style="border:0; margin:0; padding:0;">
    		<td style="text-align:center; border:0; margin:0; padding:0;">  		
    		<input type="button" onclick="location.href='/${appConfig['project.name']}/user_user_edit_ADMIN.html?id=${theUser.id}&from=list&menu='" value="<fmt:message key="button.edit"/>"/>
    		</td>
    	</tr></table>
	</display:column>

    <display:setProperty name="paging.banner.item_name" value="user"/>
    <display:setProperty name="paging.banner.items_name" value="users"/>

    <display:setProperty name="export.excel.filename" value="User List.xls"/>
    <display:setProperty name="export.csv.filename" value="User List.csv"/>
    <display:setProperty name="export.pdf.filename" value="User List.pdf"/>
</display:table>

<script type="text/javascript">
    //highlightTableRows("users");
</script>

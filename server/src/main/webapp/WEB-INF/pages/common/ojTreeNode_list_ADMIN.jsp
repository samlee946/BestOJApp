<%@ include file="/common/taglibs.jsp"%>

 
<head>
    <title><fmt:message key="problemList.title"/></title>
    <meta name="heading" content="<fmt:message key='ojTreeNode.heading'/>"/>
    <meta name="menu" content="AdminMenu"/>
    <meta name="submenu" content="ModifyOjTreeNode"/>
</head>

<s:form name="oform" action="ojTreeNodeAction" method="get" validate="true">
<c:set var="buttons">
   <table><tr>
   		<td>
		    <div align=right><input type="button"
		        onclick="location.href='<c:url value="/admin/editOjTreeNode.html?pid=${ojTreeNodeParent.id}&method=Add&from=list"/>'"
		        value="<fmt:message key="button.add"/>"/>
		    </div>
   		</td>
   	</tr></table>
</c:set>
<c:out value="${buttons}" escapeXml="false" />

Path:&nbsp;&nbsp;<c:out value="${currentPath}" escapeXml="false" />
<display:table name="ojTreeNodes" cellspacing="0" cellpadding="0" requestURI=""
    defaultsort="1" id="item" pagesize="50" class="table" export="true">
    <display:column title="<input type='checkbox' name='allbox' onClick='checkAll(pform);'/>"
            sortable="false" headerClass="sortable" style="width: 5%;word-break:break-all;text-align:center;">
        <input type="checkbox" name="problemIDs" value="${ojTreeNode.id}"/>
    </display:column>
    <display:column property="id" escapeXml="true" sortable="false" titleKey="ojTreeNode.id" paramId="pid" paramProperty="id" autolink="true" style="width: 60px;text-align:center;"/>
    <display:column property="pid" sortable="false" titleKey="ojTreeNode.pid" media="html" style="width: 60px;text-align:center;"/>
    <display:column property="orderNum" sortable="false" titleKey="ojTreeNode.orderNum" media="html" style="width: 60px;text-align:center;"/>
    <c:choose>
        <c:when test="${item.type<100}">
            <display:column property="name" sortable="false" titleKey="ojTreeNode.name" url="/admin/ojTreeNodeList.html?from=list" paramId="pid" paramProperty="id" autolink="true" style="width: 120px;text-align:center;"/>
        </c:when>
        <c:otherwise>
            <display:column property="name" sortable="false" titleKey="ojTreeNode.name" url="/admin/editOjTreeNode.html" paramId="id" paramProperty="id" autolink="true" style="width: 120px;text-align:center;"/>
        </c:otherwise>
    </c:choose>
    <display:column property="url" sortable="false" titleKey="ojTreeNode.url" autolink="false" style="width: 120px;text-align:center;"/>
    <display:column property="description" sortable="false" titleKey="ojTreeNode.description"/>
    <display:column titleKey="list.operate" sortable="false" headerClass="sortable" style="width:150px; word-break:break-all;">
    	<table style="width:100%; border:0; margin:0; padding:0;"><tr style="border:0; margin:0; padding:0;">
    		<td style="text-align:center; border:0; margin:0; padding:0;">
    		<input type="button" onclick="location.href='/${appConfig['project.name']}/admin/editOjTreeNode.html?id=${item.id}'" value="<fmt:message key="button.edit"/><fmt:message key="ojTreeNode.heading"/>"/>
    		</td>
                <c:if test="${item.problemId!=null && item.problemId!=''}">
    		<td style="text-align:center; border:0; margin:0; padding:0;">
    		<input type="button" onclick="location.href='/${appConfig['project.name']}/common/editProblem.html?id=${item.problemId}'" value="<fmt:message key="button.edit"/><fmt:message key="problem"/>"/>
                </c:if>
    		</td>
    	</tr></table>
    </display:column>

    <display:setProperty name="paging.banner.item_name" value="ojTreeNode"/>
    <display:setProperty name="paging.banner.items_name" value="ojTreeNodes"/>

    <display:setProperty name="export.excel.filename" value="OjTreeNode List.xls"/>
    <display:setProperty name="export.csv.filename" value="OjTreeNode List.csv"/>
    <display:setProperty name="export.pdf.filename" value="OjTreeNode List.pdf"/>
</display:table>
<div style="margin:0% 0% 0% 70%"></div>
</s:form>

<script type="text/javascript">
    //highlightTableRows("ojTreeNodes");
</script>

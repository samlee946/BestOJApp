<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="userProfile.title"/></title>
    <meta name="heading" content="<fmt:message key='userProfile.heading'/>"/>
    <meta name="menu" content="${param.menu}"/>
    <meta name="submenu" content="${param.submenu}"/>
    <script type="text/javascript" src="<c:url value='/scripts/selectbox.js'/>"></script>
</head>
 
<body>
   <table width=380 style="margin: 0pt -2pt">
   <tr>
   		<td width=20%>
   			<fmt:message key="user.username"/>:
   		</td>
   		<td width=30%>
   			<b>${user.username}</b>
   		</td>
   		<td width=20%>
   			<fmt:message key="user.pkuAccount"/>: 
   		</td>
   		<td width=30%>
   			<b>${user.pkuAccount }</b>
   		</td>	
   	</tr>
   	
    <c:if test="${currentUser.administrator}">
   	<tr>
   		<td>
   			<fmt:message key="user.fullName"/>: 
   		</td>
   		<td colspan=3>
   			<b>${user.lastName }${user.firstName }</b>
   		</td>
   	</tr>
   	</c:if>
   	
   	<tr>
   		<td>
   			<fmt:message key="user.email"/>: 
   		</td>
   		<td colspan=3>
   			<b>${user.email }</b>
   		</td>
   	</tr>
   	
   	<tr>
   		<td>
		   	<fmt:message key="user.website"/>: 
   		</td>
   		<td colspan=3>
		   	<b>${user.website }</b>
   		</td>
   	</tr></table>
	<br>
    <c:if test="${currentUser.administrator}">
    <li>
        <strong><fmt:message key="user.roles"/>:</strong>
        <s:iterator value="user.roleList" status="status">
          <s:property value="name"/><s:if test="!#status.last">,</s:if>
          <input type="hidden" name="userRoles" value="<s:property value="name"/>"/>
        </s:iterator>
    </li>
    </c:if>
    <hr>
    <!-- begin -->
	<table id="showChart" style="visibility:visible;">
		<tr>
			<td>
		      <a href="javascript:initLine('Chart',680,200,'${dataChartGlobal.title}','${dataChartGlobal.xlabel}','${dataChartGlobal.ylabel}','${dataChartGlobal.label}','${dataChartGlobal.value}','${dataChartGlobal.unit}');">
		         [Grobal] 
		      </a>
		      &nbsp;&nbsp;&nbsp;
		      <a href="javascript:initBar('Chart',680,200,'${dataChartRecent.title}','${dataChartRecent.xlabel}','${dataChartRecent.ylabel}','${dataChartRecent.label}','${dataChartRecent.value}','${dataChartRecent.unit}');">
		        [Recent]
		      </a>
			</td>
		</tr>
		<tr>
			<td id="Chart">
			</td>
		</tr>
	</table>
	<script>
		initLine('Chart',680,200,'${dataChartGlobal.title}','${dataChartGlobal.xlabel}','${dataChartGlobal.ylabel}','${dataChartGlobal.label}','${dataChartGlobal.value}','${dataChartGlobal.unit}');	</script>
	<!-- end -->
</body>
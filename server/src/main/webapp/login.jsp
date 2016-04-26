<%@ include file="/common/taglibs.jsp"%>

<head>
    <link rel="stylesheet" type="text/css" media="all" href="<c:url value='/styles/${appConfig["csstheme"]}/layout-1col.css'/>" />
</head>
<script type="text/javascript" language="javascript">
	//setTimeout("submitForm();", 1000);
	function submitForm()
    {
		document.forms[0].submit();
    }	
</script>
<body>
    <h6><fmt:message key="errors.password.mismatch"/></h6>
	<form method="post" id="loginForm" action="<c:url value='/j_security_check'/>">
	    <input name="j_username" value=""/><br/>
	    <input type="password" name="j_password" value=""/><br/>
	    <input name="j_code" value=""/><br/>
    
        <img src="ValidateCodeServlet" width="80" height="30" style="padding:5px 0px;"/><br/>
   		<input type=submit value=submit>
    </form>
</body>
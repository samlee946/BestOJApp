<%@ include file="/common/taglibs.jsp"%>
<script>
	var bWidth=0;
	//alert(getCookie("leftState"));
	function init(){
/*	  	document.getElementById("leftside").style.width="165px";
	  	document.getElementById("rightside").style.width="10px";
	    document.getElementById("content").style.margin="0px 20px 0px 190px";
	    document.getElementById("constrictionIMG").src="<c:url value='/styles/${appConfig["csstheme"]}/images/constriction_open.jpg'/>"
	    document.getElementById("profile").style.display="none";
*/	}

	function leftState(){
/*	  var bWidth=parseInt(document.documentElement.clientWidth);
	  if(document.getElementById("constrictionIMG").src.indexOf("constriction_open.jpg")==-1)
	  {
	  	document.getElementById("leftside").style.width="165px";
	  	document.getElementById("rightside").style.width="10px";
	    document.getElementById("content").style.margin="0px 20px 0px 190px";
	    document.getElementById("constrictionIMG").src="<c:url value='/styles/${appConfig["csstheme"]}/images/constriction_open.jpg'/>"
	    document.getElementById("profile").style.display="none";
	    deleteCookie("leftState");
	    setCookie("leftState", "0");
	  }
	  else
	  {
	  	document.getElementById("leftside").style.width="165px";
	  	document.getElementById("rightside").style.width="190px";
	    document.getElementById("content").style.margin="0px 190px 0px 190px";
	    document.getElementById("constrictionIMG").src="<c:url value='/styles/${appConfig["csstheme"]}/images/constriction_close.jpg'/>"
	    document.getElementById("profile").style.display="";
	    deleteCookie("leftState");
	    setCookie("leftState", "1");
	  }
*/	}
</script>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
        <head>
        <base target="left"/>
	<BASE HREF="http://${pageContext.request.serverName}:${pageContext.request.serverPort}/${appConfig['project.name']}/"/>

        <%@ include file="/common/meta.jsp" %>
        <title><fmt:message key="webapp.name"/></title>

        <link rel="stylesheet" type="text/css" media="all" href="<c:url value='/styles/${appConfig["csstheme"]}/theme.css'/>" />
        <link rel="stylesheet" type="text/css" media="print" href="<c:url value='/styles/${appConfig["csstheme"]}/print.css'/>" />

        <script type="text/javascript" src="<c:url value='/scripts/prototype.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/scripts/scriptaculous.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/scripts/global.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/scripts/flash/chart/js/swfobject.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/scripts/flash/chart/js/chart.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/scripts/dtree/dtree.js'/>"></script>
	</head>
        
	<body onload="init();">
		<div id="topcontainer">
			<div id="sitename">
                            <c:choose>
                               <c:when test="${pageContext.request.locale.language != 'en'}">
                                <div id="switchLocale"><a href="<c:url value='/?locale=en'/>" target="_top">ENGLISH</a></div>
                               </c:when>
                               <c:otherwise>
                                <div id="switchLocale"><a href="<c:url value='/?locale=zh'/>" target="_top">CHINESE</a></div>
                               </c:otherwise>
                            </c:choose>
                                <div id="branding">
                                    <table><tr>
                                            <td style="letter-spacing: .5em; width:100%;"><h1 style="padding-top:10px;"><font color=white><fmt:message key="webapp.name"/></font></h1></td>
                                    <td width="20px"></td>
                                    </tr><tr>
                                    <td valign="bottom" align="right"><h2 style="letter-spacing: .1em;"><font color=white><fmt:message key="webapp.tagline"/></font></h2></td>
                                    <%
                                        Object obj = session.getAttribute("oj_sign");
                                        pageContext.setAttribute("_sign", obj);
                                     %>
                                    <c:if test="${_sign!=null}">
                                        <td valign="bottom"><h2> - <font color="yellow">${_sign} <fmt:message key="mainMenu.sign"/></font></h2></td>
                                    </c:if>
                                    </tr></table>
                                </div>
                    </div>
                    <div id="mainmenu">
                            <jsp:include page="/common/menu.jsp"/>
                    </div>
                <%-- Put constants into request scope --%>
                <appfuse:constants scope="request"/>
                    <div id="wrap"></div>
            </div>
        </body>
</html>
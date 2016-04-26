<%@ include file="/common/taglibs.jsp"%>
<%@page import="java.util.*"%>
<%@page import="com.unlimited.oj.util.*"%>
<%@page import="com.unlimited.oj.model.*"%>
<%@page import="com.unlimited.oj.service.*"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>

<script>
	var bWidth=0;
	//alert(getCookie("leftState"));
	function init(){
/*	    document.getElementById("leftside").style.width="165px";
	    document.getElementById("rightside").style.width="10px";
	    document.getElementById("content").style.margin="0px 20px 0px 190px";
	    document.getElementById("constrictionIMG").src="<c:url value='/styles/${appConfig["csstheme"]}/images/constriction_open.jpg'/>"
	    document.getElementById("profile").style.display="none";
*/
	}

	function leftState(){
/*
		var bWidth=parseInt(document.documentElement.clientWidth);
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
*/
	}
</script>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>
	<BASE HREF="http://${pageContext.request.serverName}:${pageContext.request.serverPort}/${appConfig['project.name']}/"/>
        <BASE target="main"/>
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
        <style type="text/css">
        body{font-size:12px;
        }
        #divTreeView{
        float:left;
        width:165px;
        height:300px;
        overflow:auto;
        border-style:solid;
        border-color:#999999;
        border-width:1px;
        }
        .nodeInfo{
        float:left;
        margin-left:20px;
        }
        </style>
	</head>

	<body onload="init();" style="background:#f0f0f0; background-image: url(/${appConfig['project.name']}/styles/${appConfig['csstheme']}/images/bodybg.jpg);background-repeat:repeat-x;">
		<div id="leftcontainer">
                    <c:set var="currentMenu" value="${oj_menu}"/>
                    <c:set var="currentSubmenu" value="${oj_submenu}"/>
                    <c:if test="${currentMenu!=null and currentMenu!=''}">
                    <p>
                                <menu:useMenuDisplayer name="Velocity" config="cssLeftsideMenu.vm" permissions="rolesAdapter">
                                    <menu:displayMenu name="${currentMenu}"/>
                                </menu:useMenuDisplayer>
                    </p>
                    </c:if>
                    <hr style="border-style:dashed;color:#666666;}"/>



                    <img src="/${appConfig['project.name']}/images/colors.jpg" height="104" width="145" class="thumbnail" alt="Included colors" style="margin: 5px;"/>
                </div>
        </body>
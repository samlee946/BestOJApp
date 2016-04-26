<%@ include file="/common/taglibs.jsp" %>
<%@ page import="com.unlimited.onlinejudge.common.*"%>
<html>
    <html><head>

    <meta http-equiv="Content-Type" content="text/html; charset=GB2312">
  </head><frameset rows="110,*,30" frameborder="NO" border="0" framespacing="0">
            <frame src="/${appConfig['project.name']}/common/header.html" name="top" scrolling="NO" noresize/>
            <frameset rows="*" cols="200,*" framespacing="0" frameborder="NO" border="0">
                <frame src="/${appConfig['project.name']}/common/lefter.html?menu=AdminMenu" name="left" noresize/>
                <frame src="/${appConfig['project.name']}/mainPage.html" name="main"/>
            </frameset>
            <frame src="/${appConfig['project.name']}/common/footer.html" name="foot" scrolling="NO" noresize/>
        </frameset>
        <noframes><body>
        </body></noframes>
</html>

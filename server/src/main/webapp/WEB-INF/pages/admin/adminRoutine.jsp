<%@ include file="/common/taglibs.jsp"%>
<%@page import="com.unlimited.oj.enums.*"%>
<%@page import="org.apache.taglibs.standard.tag.common.fmt.BundleSupport"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="javax.servlet.jsp.jstl.fmt.LocalizationContext"%>

<head>
    <title><fmt:message key="menu.admin.adminRoutine"/></title>
    <meta name="heading" content="<fmt:message key='menu.admin.adminRoutine'/>"/>
    <meta name="menu" content="AdminMenu"/>
    <meta name="submenu" content="AdminRoutine"/>
</head>
<%
        LocalizationContext locCtxt = BundleSupport.getLocalizationContext(pageContext);
        ResourceBundle bundle = locCtxt.getResourceBundle();
%>
<table><tr>
            <td>
                <div align=right><input type="button"
                    onclick="location.href='<c:url value="/admin/updateUserAcProblemsStatus.html"/>'"
                    value="Update Ac Problem"/>
                </div>
            </td>
            <td><br>

            <br></td>
            <td>
            </td>
    </tr></table>

<script type="text/javascript">
    //highlightTableRows("exams");
</script>

<%@ include file="/common/taglibs.jsp"%>

<script>
function init(){
	setSize();
}
 
function setSize() { // check all the checkboxes in the list
  for (var i=0;i<document.forms[0].elements.length;i++) {
    var e = document.forms[0].elements[i];
        var eName = e.name;
        if ((e.type.indexOf("textarea") == 0)) {
        	e.style.width="95%";
        }
    } 
}
window.onresize = setSize;
</script>
<head>
    <meta name="menu" content="${param.menu}"/>
    <meta name="submenu" content="${param.submenu}"/>
</head>
<body onload="init();">
	<h1><p align="center">Add Batch Users</p></h1>
	<p align="center">
	<table width="100%" border="0" align="center">
		<tr><td>
		<div id="divsource" style="display:block;">
			<s:form id="form1" name="form1" action="user_user_addBatch_Submit_ADMIN" method="post">
				<h1><p align="center">Format:&nbsp;user name,&nbsp;password,&nbsp;nick,&nbsp;class name</p></h1>
				<s:textarea label="Source" name="addBatchUserData" rows="12" />
				<p align=center><font color=red>per user per line</font></p>
				<p align=center><input type=submit name=submit value=submit><INPUT type=reset value=Reset name=reset></p>
			</s:form>
		</div>
		</td></tr>
	</table>
</body>


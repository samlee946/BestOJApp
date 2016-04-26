<%@ include file="/common/taglibs.jsp"%>

<menu:useMenuDisplayer name="Velocity" config="cssMainMenu.vm" permissions="rolesAdapter">
    <ul class="mainmenu">
        <menu:displayMenu name="UserMenu"/>
        <menu:displayMenu name="AdminMenu"/>
        <menu:displayMenu name="Logout"/>
    </ul>
</menu:useMenuDisplayer>

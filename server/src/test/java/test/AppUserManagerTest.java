import com.unlimited.appserver.model.AppUser;
import com.unlimited.appserver.service.AppUserManager;
import com.unlimited.oj.service.UserExistsException;


public class AppUserManagerTest {
	public static void main() {
		AppUserManager appUserManager = new AppUserManager();
		AppUser user;
		user.setUserName("admin");
		user.setPassword("admin");
		user.setToken("6fa590b6ccad27feee1eaf4206ed0beb497936af");
		appUserManager.saveAppUser(user);
	}
}
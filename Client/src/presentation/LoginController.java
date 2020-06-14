/*package presentation;


import businessLogic.LoginBLL;

public class LoginController {

	private static LoginView loginView;

    LoginController(LoginView loginView) {

        this.loginView = loginView;

    }



    public static void login() {

        String username = loginView.getUsername();

        String password = loginView.getPassword();

        boolean exist=LoginBLL.login(username,password);
	     if(!exist) {loginView.showErrorMessage("Something is wrong...Please check again your username & password! ");

   	 }else {loginView.showErrorMessage("Your login was a success :)");
   	 	if(!username.equals("admin")) {
   	 		loginView.showUserView();
   	 		loginView.setFalseLoginView();
   	 		}
   	 	else {
   	 		loginView.showAdministratorView();
   	 		loginView.setFalseLoginView();

   	 	}
   	 	}

    }


}
 */
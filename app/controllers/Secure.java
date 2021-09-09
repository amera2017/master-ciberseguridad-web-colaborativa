package controllers;


import helpers.HashUtils;
import models.User;
import play.i18n.Messages;
import play.mvc.Controller;

public class Secure extends Controller {

    public static void login(){
        render();
    }

    public static void logout(){
        session.remove("password");
        login();
    }

     public static void authenticate(String username, String password){
        User u = User.loadUser(username);
        String user1,password1;
        user1=username.trim();
        password1=username.trim();
        if (user1.length() > 0 && password1.length() > 0 && u != null && u.getPassword().equals(HashUtils.getMd5(password))){
            session.put("username", username);
            session.put("password", password);
            Application.index();
        }else{
            flash.put("error", Messages.get("Public.login.error.credentials"));
            login();
        }
    }
}

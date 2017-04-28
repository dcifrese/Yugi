

package controllers;

import forms.UserForm;
import forms.CardForm;
import javax.inject.Inject;
import models.User;
import models.enums;
import org.apache.log4j.Logger;
import org.apache.logging.log4j.scala.Logging;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import play.data.Form;
import play.mvc.Result;
import services.UserService;
import views.html.addUser;
import views.html.index;
import views.html.userContent;


@Controller
public class CrudController extends play.mvc.Controller {

    final static Logger logger = Logger.getLogger(CrudController.class);

    @Inject private UserService userService;

    public Result renderAddUser(){

        return ok(addUser.render("Please Create User.", Form.form(UserForm.class)));
    }


    public Result addUser() {
        logger.info("addUser() called. Endpoint request.");

        Form<UserForm> form = Form.form(UserForm.class).bindFromRequest();

        if (form.hasErrors() || form.hasGlobalErrors()) {
            return badRequest(addUser.render("Please Create User.", form));
        }

        UserForm userForm = form.get();
        User user = new User(userForm.getName(), userForm.getPassword());
        if (userService.save(user)) {
            logger.info("addUser had a good request.");
            return ok(index.render("User Created. Please login.", form));
        } else {
            logger.info("addUser had a bad request. Attempt to duplicate users.");
            form.reject("name", "Username Not Available.");
            return badRequest(addUser.render("Username Not Available", form));
        }
    }

    public Result login() {

        logger.info("login() called. Endpoint requested.");

        Form<UserForm> form = Form.form(UserForm.class).bindFromRequest();
        Form <CardForm> cardForm = play.data.Form.form(forms.CardForm.class);

        if (form.hasErrors() || form.hasGlobalErrors()) {
            return badRequest(index.render("Form Error", form));
        } else {
            UserForm userForm = form.get();
            User user = new User(userForm.getName(), userForm.getPassword());
            try {
                enums.LOGIN_CODE loginAttempt = userService.login(user);
                switch (loginAttempt) {
                    case NAME_FAIL:
                        logger.info("login() had a bad request. User entered invalid username.");
                        // Name doesn't exist in database, prompt user to create account
                        return ok(addUser.render("Invalid Username", form));

                    case PASS_FAIL:
                        logger.info("login() had a bad request. User entered invalid password.");
                        // Incorrect password, prompt user to try again
                        return ok(index.render("Invalid Password", form));

                    case SUCCESS:
                        logger.info("login() had a good request.");
                        // successful authentication
                        return ok(userContent.render("Login Successful!"));
                        //return ok(userContent.render("Login Successful!", cardForm));

                    default:
                        logger.info("login() failed. Fell through to default in switch-case statement.");
                        form.reject("It's Kaiser's fault.");
                        return badRequest(index.render("Login Failed", form));
                }
            } catch (DataIntegrityViolationException ex) {
                logger.warn("login() exception: DataIntegrityViolationException caught! /n" + ex.getMessage());
                return badRequest(index.render("Login Error", form));
            }
        }
    }
}

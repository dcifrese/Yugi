
package controllers;



import org.apache.log4j.Logger;
import org.apache.logging.log4j.scala.Logging;




import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.userContent;
import java.util.List;
import models.Card;

import services.CardService;

import javax.inject.Inject;
import javax.inject.Named;


@Named
public class Application extends Controller {

    @Inject
    private CardService cardService;

    final static Logger logger = Logger.getLogger(Application.class);

    public Result index() {
        logger.info("index() endpoint requested.");
        return ok(index.render("Welcome!", play.data.Form.form(forms.UserForm.class)));
    }

    public Result userContent() {
        logger.info("userContent() endpoint requested");
        //return ok(userContent.render("Hello!!!", play.data.Form.form(forms.CardForm.class)));
        return ok(userContent.render("Home Page"));
    }





}

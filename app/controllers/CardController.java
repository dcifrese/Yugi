

package controllers;

import forms.CardForm;
import javax.inject.Inject;
import models.Card;
import models.enums;
import org.apache.log4j.Logger;
import org.apache.logging.log4j.scala.Logging;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import javax.inject.Named;
import play.data.Form;
import play.mvc.Result;
import services.CardService;
import views.html.addUser;
import views.html.index;
import views.html.login;
import views.html.userContent;
import views.html.addCard;
import java.util.List;


@Named
public class CardController extends play.mvc.Controller {

    final static Logger logger = Logger.getLogger(CardController.class);

    @Inject private CardService cardService;


    public Result renderAddCard(){

        return ok(addCard.render("Card Created.", Form.form(CardForm.class)));
    }

    public Result addCard() {
        logger.info("addCard() called. Endpoint request.");

        Form<CardForm> form = Form.form(CardForm.class).bindFromRequest();
        if (form.hasErrors() || form.hasGlobalErrors()) {
            return badRequest(addCard.render("Card", form));
        } else {
            CardForm cardForm = form.get();
            Card card = new Card(cardForm.getTitle(), cardForm.getDescription());
            try {
                if (cardService.save(card)) {
                    logger.info("addCard had a good request.");
                    return redirect(routes.Application.userContent());
                } else {
                    logger.info("addCard had a bad request.");
                    form.reject("Title Not Available.");
                    return badRequest(addCard.render("Title Not Available", form));
                }
            } catch (DataIntegrityViolationException ex) {
                logger.info("addCard had a bad request. DataIntegrityViolationException caught.");
                return badRequest(addCard.render("Card", form));
            }
        }
    }

    public Result getTasks() {
        List<Card> tasks = cardService.fetchAllTasks();
        return ok(play.libs.Json.toJson(tasks));
    }

}

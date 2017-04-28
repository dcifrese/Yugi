

package services.impl;

import models.Card;
import models.enums;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import services.CardService;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Service
public class CardServiceImpl implements CardService {

    private static final Logger log = LoggerFactory.getLogger(CardService.class);

    @PersistenceContext private EntityManager em;

    @Override
    @Transactional
    public Boolean save(Card card) {
        try {
            log.debug("Card save() was called.");
            log.info("Card save() was called.");
            if (card.getTitle() != null && card.getDescription() != null) {
                Card cardFromDB = em.find(Card.class, card.getTitle());
                if (cardFromDB != null && cardFromDB.getTitle().equals(card.getTitle())) {
                    // card exists in database
                    log.debug("Card exists in the database, save failed.");
                    return false;
                } else {
                    // Add new card to the database
                    log.debug("Card added to database, save succeeded.");
                    em.persist(card);
                    return true;
                }
            } else {
                log.debug("Form values were null, save failed.");
                return false;
            }
        } catch(Exception e) {
            log.debug("Exception caught in save(), save failed.");
            return false;
        }
    }

    @Override
    public enums.LOGIN_CODE login(Card card) {
        log.debug("Card login() was called.");
        Card cardFromDB = em.find(Card.class, card.getTitle());
        if (cardFromDB == null) {
            log.debug("Card not found in database, lookup failed.");
            return enums.LOGIN_CODE.NAME_FAIL;
        } else if (!cardFromDB.getDescription().equals(card.getDescription())) {
            log.debug("Description incorrect, something failed.");
            return enums.LOGIN_CODE.PASS_FAIL;
        } else {
            log.debug("CAAAAAARD success!");
            return enums.LOGIN_CODE.SUCCESS;
        }
    }

    @Override
    public List<Card> fetchAllTasks() {
        //caps matters I guess
        return em.createQuery("from Card", Card.class).getResultList();
    }
}


package services;

import models.Card;
import models.enums;
import java.util.List;

public interface CardService {


    /**
     *
     * @param card
     * @return
     */
    Boolean save(Card card);

    /**
     *
     * @param card
     * @return
     */
    enums.LOGIN_CODE login(Card card);


    List<Card> fetchAllTasks();

}

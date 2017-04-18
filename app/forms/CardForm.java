
package forms;

import play.data.validation.Constraints.Required;


public class CardForm  {

    @Required
    private String title;

    @Required
    private String description;

    public CardForm() { }

    public CardForm(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public String validate() {

        if (title.length() == 0) {
            return "Title must be at least 1 characters in length.";
        }
        if (description.length() == 0) {
            return "Description must be at least 1 characters in length.";
        }
        return null;
    }

    @Override
    public String toString() {
        return "Card{title=" + title + ", description=" + description + "}";
    }
}

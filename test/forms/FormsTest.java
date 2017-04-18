
package forms;

import org.junit.Assert;
import org.junit.Test;

public class FormsTest {

    @Test
    public void validateSuccessTest() {
        UserForm validUserForm = new UserForm();
        validUserForm.setName("aA1bB2cC3-._");
        validUserForm.setPassword("aA1!bB2@cC3#dD4$eE5%");
        Assert.assertTrue(validUserForm.validate() == null);
    }

    @Test
    public void shortNameTest() {
        UserForm shortName = new UserForm("12", "validPassword");
        Assert.assertTrue(shortName.validate().equals("Name must be at least 3 characters in length, and can only contain [a-zA-Z0-9._-]."));
    }

    @Test
    public void badCharsNameTest() {
        UserForm badCharsName = new UserForm("<>?:{}|/", "validPassword");
        Assert.assertTrue(badCharsName.validate().equals("Name must be at least 3 characters in length, and can only contain [a-zA-Z0-9._-]."));
    }

    @Test
    public void almostValidNameTest() {
        UserForm almostValidName = new UserForm("almost_Valid.N@me-not", "validPassword");
        Assert.assertTrue(almostValidName.validate().equals("Name must be at least 3 characters in length, and can only contain [a-zA-Z0-9._-]."));
    }

    @Test
    public void shortPasswordTest() {
        UserForm shortPassword = new UserForm("validName", "12345");
        Assert.assertTrue(shortPassword.validate().equals("Password must be 6-20 characters in length, and only contain [A-Za-z0-9!@#$%^&*()_]."));
    }

    @Test
    public void longPasswordTest() {
        UserForm longPassword = new UserForm("validName", "abcdefghijklmnopqrstuvwxyz");
        Assert.assertTrue(longPassword.validate().equals("Password must be 6-20 characters in length, and only contain [A-Za-z0-9!@#$%^&*()_]."));
    }

    @Test
    public void badCharsPasswordTest() {
        UserForm badCharPassword = new UserForm("validName", "{}[]<>/?");
        Assert.assertTrue(badCharPassword.validate().equals("Password must be 6-20 characters in length, and only contain [A-Za-z0-9!@#$%^&*()_]."));
    }

    @Test
    public void almostValidName() {
        UserForm almostValidPassword = new UserForm("validName", "almost-Valid=Password^_^");
        Assert.assertTrue(almostValidPassword.validate().equals("Password must be 6-20 characters in length, and only contain [A-Za-z0-9!@#$%^&*()_]."));
    }


//Crazy Path
    @Test
    public void nullUser() {
        UserForm nullUser = null;
        Assert.assertTrue(nullUser == null);
    }
}

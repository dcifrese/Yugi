
package forms;

import org.junit.Assert;
import org.junit.Test;
import org.apache.log4j.Logger;
import org.apache.logging.log4j.scala.Logging;
import javax.validation.ConstraintViolation; // Not Found
import javax.validation.Validation; // Not Found
import javax.validation.Validator; // Not Found
import javax.validation.ValidatorFactory; // Not Found
import java.util.*;

public class FormsTest {


    final static Logger logger = Logger.getLogger(FormsTest.class);
    final static ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
    final static Validator validator = vf.getValidator();

    @Test
    public void validateSuccessTest() {
        UserForm validUserForm = new UserForm();
        validUserForm.setName("aA1bB2cC3-._");
        validUserForm.setPassword("aA1!bB2@cC3#dD4$eE5%");

        Set<ConstraintViolation<UserForm>> constraintViolations = validator.validate(validUserForm);
        Assert.assertTrue(constraintViolations==null || constraintViolations.size() == 0);
    }

    @Test
    public void shortNameTest() {
        UserForm shortName = new UserForm("12", "validPassword");

        Set<ConstraintViolation<UserForm>> constraintViolations = validator.validate(shortName);
        Assert.assertTrue(constraintViolations!=null && constraintViolations.size() > 0);

        int constraintErrors = 0;

        for (ConstraintViolation<UserForm> cv : constraintViolations) {
            logger.info("'" + cv.getMessage() + "'");
            if (cv.getMessage().equals("error.required")) {
                constraintErrors++;
            } else if (cv.getMessage().equals("Name must be at least 3 characters in length, and can only contain [a-zA-Z0-9._-].")) {
                constraintErrors++;
            }
        }
        Assert.assertTrue("Must have at least 1 constraint error", constraintErrors > 0);
    }


    @Test
    public void badCharsNameTest() {
        UserForm badCharsName = new UserForm("<>?:{}|/", "validPassword");

        Set<ConstraintViolation<UserForm>> constraintViolations = validator.validate(badCharsName);
        Assert.assertTrue(constraintViolations!=null && constraintViolations.size() > 0);

        int constraintErrors = 0;

        for (ConstraintViolation<UserForm> cv : constraintViolations) {
            logger.info("'" + cv.getMessage() + "'");
            if (cv.getMessage().equals("error.required")) {
                constraintErrors++;
            } else if (cv.getMessage().equals("Name must be at least 3 characters in length, and can only contain [a-zA-Z0-9._-].")) {
                constraintErrors++;
            }
        }
        Assert.assertTrue("Must have at least 1 constraint error", constraintErrors > 0);
    }


    @Test
    public void almostValidNameTest() {
        UserForm almostValidName = new UserForm("almost_Valid.N@me-not", "validPassword");

        Set<ConstraintViolation<UserForm>> constraintViolations = validator.validate(almostValidName);
        Assert.assertTrue(constraintViolations!=null && constraintViolations.size() > 0);

        int constraintErrors = 0;

        for (ConstraintViolation<UserForm> cv : constraintViolations) {
            //logger.info("'" + cv.getMessage() + "'");
            if (cv.getMessage().equals("error.required")) {
                constraintErrors++;
            } else if (cv.getMessage().equals("Name must be at least 3 characters in length, and can only contain [a-zA-Z0-9._-].")) {
                constraintErrors++;
            }
        }
        Assert.assertTrue("Must have at least 1 constraint error", constraintErrors > 0);
    }


    @Test
    public void shortPasswordTest() {
        UserForm shortPassword = new UserForm("validName", "12345");

        Set<ConstraintViolation<UserForm>> constraintViolations = validator.validate(shortPassword);
        Assert.assertTrue(constraintViolations!=null && constraintViolations.size() > 0);

        int constraintErrors = 0;

        for (ConstraintViolation<UserForm> cv : constraintViolations) {
            //logger.info("'" + cv.getMessage() + "'");
            if (cv.getMessage().equals("error.required")) {
                constraintErrors++;
            } else if (cv.getMessage().equals("Password must be 6-20 characters in length, and only contain [A-Za-z0-9!@#$%^&*()_].")) {
                constraintErrors++;
            }
        }
        Assert.assertTrue("Must have at least 1 constraint error", constraintErrors > 0);
    }

    @Test
    public void longPasswordTest() {
        UserForm longPassword = new UserForm("validName", "abcdefghijklmnopqrstuvwxyz");

        Set<ConstraintViolation<UserForm>> constraintViolations = validator.validate(longPassword);
        Assert.assertTrue(constraintViolations!=null && constraintViolations.size() > 0);

        int constraintErrors = 0;

        for (ConstraintViolation<UserForm> cv : constraintViolations) {
            //logger.info("'" + cv.getMessage() + "'");
            if (cv.getMessage().equals("error.required")) {
                constraintErrors++;
            } else if (cv.getMessage().equals("Password must be 6-20 characters in length, and only contain [A-Za-z0-9!@#$%^&*()_].")) {
                constraintErrors++;
            }
        }
        Assert.assertTrue("Must have at least 1 constraint error", constraintErrors > 0);
    }

    @Test
    public void badCharsPasswordTest() {
        UserForm badCharPassword = new UserForm("validName", "{}[]<>/?");

        Set<ConstraintViolation<UserForm>> constraintViolations = validator.validate(badCharPassword);
        Assert.assertTrue(constraintViolations!=null && constraintViolations.size() > 0);

        int constraintErrors = 0;

        for (ConstraintViolation<UserForm> cv : constraintViolations) {
            //logger.info("'" + cv.getMessage() + "'");
            if (cv.getMessage().equals("error.required")) {
                constraintErrors++;
            } else if (cv.getMessage().equals("Password must be 6-20 characters in length, and only contain [A-Za-z0-9!@#$%^&*()_].")) {
                constraintErrors++;
            }
        }
        Assert.assertTrue("Must have at least 1 constraint error", constraintErrors > 0);
    }

    @Test
    public void almostValidPassword() {
        UserForm almostValidPassword = new UserForm("validName", "almost-Valid=Password^_^");

        Set<ConstraintViolation<UserForm>> constraintViolations = validator.validate(almostValidPassword);
        Assert.assertTrue(constraintViolations!=null && constraintViolations.size() > 0);

        int constraintErrors = 0;

        for (ConstraintViolation<UserForm> cv : constraintViolations) {
            //logger.info("'" + cv.getMessage() + "'");
            if (cv.getMessage().equals("error.required")) {
                constraintErrors++;
            } else if (cv.getMessage().equals("Password must be 6-20 characters in length, and only contain [A-Za-z0-9!@#$%^&*()_].")) {
                constraintErrors++;
            }
        }
        Assert.assertTrue("Must have at least 1 constraint error", constraintErrors > 0);
    }


//Crazy Path
    @Test
    public void nullUser() {
        UserForm nullUser = null;
        Assert.assertTrue(nullUser == null);
    }


    @Test
    public void validNameBlankPassword() {
        UserForm validNameBlankPassword = new UserForm();
        validNameBlankPassword.setName("valid");
        validNameBlankPassword.setPassword("");

        Set<ConstraintViolation<UserForm>> constraintViolations = validator.validate(validNameBlankPassword);
        Assert.assertTrue(constraintViolations!=null && constraintViolations.size() > 0);

        int constraintErrors = 0;

        for (ConstraintViolation<UserForm> cv : constraintViolations) {
            //logger.info("'" + cv.getMessage() + "'");
            if (cv.getMessage().equals("error.required")) {
                constraintErrors++;
            } else if (cv.getMessage().equals("Password must be 6-20 characters in length, and only contain [A-Za-z0-9!@#$%^&*()_].")) {
                constraintErrors++;
            }
        }
        Assert.assertTrue("Must have at least 1 constraint error", constraintErrors > 0);
    }

    @Test
    public void validNameNullPassword() {
        UserForm validNameNullPassword = new UserForm();
        validNameNullPassword.setName("valid");
        validNameNullPassword.setPassword(null);

        Set<ConstraintViolation<UserForm>> constraintViolations = validator.validate(validNameNullPassword);
        Assert.assertTrue(constraintViolations!=null && constraintViolations.size() > 0);

        int constraintErrors = 0;

        for (ConstraintViolation<UserForm> cv : constraintViolations) {
            //logger.info("'" + cv.getMessage() + "'");
            if (cv.getMessage().equals("error.required")) {
                constraintErrors++;
            } else if (cv.getMessage().equals("Password must be 6-20 characters in length, and only contain [A-Za-z0-9!@#$%^&*()_].")) {
                constraintErrors++;
            }
        }
        Assert.assertTrue("Must have at least 1 constraint error", constraintErrors > 0);
    }

    @Test
    public void blankNameValidPassword() {
        UserForm blankNameValidPassword = new UserForm();
        blankNameValidPassword.setName("");
        blankNameValidPassword.setPassword("valid");

        Set<ConstraintViolation<UserForm>> constraintViolations = validator.validate(blankNameValidPassword);
        Assert.assertTrue(constraintViolations!=null && constraintViolations.size() > 0);

        int constraintErrors = 0;

        for (ConstraintViolation<UserForm> cv : constraintViolations) {
            //logger.info("'" + cv.getMessage() + "'");
            if (cv.getMessage().equals("error.required")) {
                constraintErrors++;
            } else if (cv.getMessage().equals("Password must be 6-20 characters in length, and only contain [A-Za-z0-9!@#$%^&*()_].")) {
                constraintErrors++;
            }
        }
        Assert.assertTrue("Must have 2 constraint errors", constraintErrors == 2);
    }

    @Test
    public void nullNameValidPassword() {
        UserForm nullNameValidPassword = new UserForm();
        nullNameValidPassword.setName(null);
        nullNameValidPassword.setPassword("valid");

        Set<ConstraintViolation<UserForm>> constraintViolations = validator.validate(nullNameValidPassword);
        Assert.assertTrue(constraintViolations!=null && constraintViolations.size() > 0);

        int constraintErrors = 0;

        for (ConstraintViolation<UserForm> cv : constraintViolations) {
            //logger.info("'" + cv.getMessage() + "'");
            if (cv.getMessage().equals("error.required")) {
                constraintErrors++;
            } else if (cv.getMessage().equals("Password must be 6-20 characters in length, and only contain [A-Za-z0-9!@#$%^&*()_].")) {
                constraintErrors++;
            }
        }
        Assert.assertTrue("Must have at least 1 constraint error", constraintErrors > 0);
    }
}

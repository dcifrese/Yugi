/**
 * File:    servies.ServicesTest.java
 * Author:  rmoon
 * Date:    1/26/17
 */

package services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import conf.AppConf;
import conf.TestDataConf;
import javax.inject.Inject;
import models.User;
import models.enums;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(classes = {AppConf.class, TestDataConf.class})
public class ServicesTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Inject private UserService userService;

    @Test
    public void addUserGoodRequestTest() {
        User addUserGoodRequestUser = new User("addUserGoodRequestName", "testPassword");
        assertTrue(userService.save(addUserGoodRequestUser));
    }

    @Test
    public void addUserBadRequestTest() {
        User addUserBadRequestUser = new User("addUserBadRequestName", "testPassword");
        userService.save(addUserBadRequestUser);
        assertFalse((userService.save(addUserBadRequestUser)));
    }

    @Test
    public void loginGoodRequestTest() {
        User loginGoodRequestUser = new User("loginGoodRequestName", "testPassword");
        userService.save(loginGoodRequestUser);
        assertTrue((userService.login(loginGoodRequestUser) == enums.LOGIN_CODE.SUCCESS));
    }

    @Test
    public void loginBadNameRequestTest() {
        User loginBadNameRequestUser = new User("loginBadRequestName", "testPassword");
        assertTrue((userService.login(loginBadNameRequestUser) == enums.LOGIN_CODE.NAME_FAIL));
    }

    @Test
    public void loginBadPasswordRequestTest() {
        User validUser = new User("loginBadPasswordName", "validPassword");
        User loginBadPasswordRequestUser = new User("loginBadPasswordName", "failPassword");
        userService.save(validUser);
        assertTrue((userService.login(loginBadPasswordRequestUser) == enums.LOGIN_CODE.PASS_FAIL));
    }
}

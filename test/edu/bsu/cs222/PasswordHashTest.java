package edu.bsu.cs222;

import org.junit.Assert;
import org.junit.Test;

public class PasswordHashTest {

    @Test
    public void signup() {
        Assert.assertEquals(PasswordHash.userSignup("adam", "isTestingThis", "Librarian"), true);
    }

    @Test
    public void login() {
        Assert.assertEquals(PasswordHash.userLogin("adam", "isTestingThis"), true);
    }


}
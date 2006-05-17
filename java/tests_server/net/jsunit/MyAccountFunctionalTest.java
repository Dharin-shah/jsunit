package net.jsunit;

public class MyAccountFunctionalTest extends AggregateServerFunctionalTestCase {

    public void setUp() throws Exception {
        super.setUp();
        webTester.beginAt("/myaccountpage");
    }

    public void testInitialConditions() throws Exception {
        webTester.setWorkingForm("createAccountForm");
        assertOnMyAccountPage();
        webTester.assertFormElementPresent("user.firstName");
        webTester.assertFormElementPresent("user.lastName");
        webTester.assertFormElementPresent("user.emailAddress");
        webTester.assertFormElementPresent("password1");
        webTester.assertFormElementPresent("password2");
    }

    public void testCreateAccountValid() throws Exception {
        webTester.setWorkingForm("createAccountForm");
        webTester.setFormElement("user.firstName", "John");
        webTester.setFormElement("user.lastName", "Smith");
        webTester.setFormElement("user.emailAddress", "johnsmith@example.com");
        webTester.setFormElement("password1", "mypassword");
        webTester.setFormElement("password2", "mypassword");
        webTester.submit("createAccount");
        assertOnCreateAccountThankYouPage();
    }

    public void testCreateAccountInvalid() throws Exception {
        webTester.setWorkingForm("createAccountForm");
        webTester.submit("createAccount");
        assertOnMyAccountPage();
    }

    public void xtestSignInValidUser() throws Exception {
        webTester.setWorkingForm("signInForm");
        webTester.setFormElement("emailAddress", "johnsmith@example.com");
        webTester.setFormElement("password", "password");
        webTester.submit("signIn");
        assertOnFragmentRunnerPage();
    }

    public void testSignInInvalidUser() throws Exception {
        webTester.setWorkingForm("signInForm");
        webTester.setFormElement("emailAddress", "bad username");
        webTester.setFormElement("password", "bad password");
        webTester.submit("signIn");
        assertOnMyAccountPage();
    }

    private void assertOnMyAccountPageFor(String fullName) {
        webTester.assertTitleEquals("My account - " + fullName + " - JsUnit");
    }

    private void assertOnCreateAccountThankYouPage() {
        webTester.assertTitleEquals("Account created - JsUnit");
    }
}

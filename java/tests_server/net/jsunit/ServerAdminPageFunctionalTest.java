package net.jsunit;

public class ServerAdminPageFunctionalTest extends AggregateServerFunctionalTestCase {

    public void setUp() throws Exception {
        super.setUp();
        webTester.beginAt("adminPage");
    }

    public void testInitialConditiions() throws Exception {
        webTester.assertTitleEquals("Server Administration - JsUnit");
    }

}

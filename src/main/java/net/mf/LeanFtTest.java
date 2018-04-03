package net.mf;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.hp.lft.sdk.*;
import com.hp.lft.sdk.web.*;
import com.hp.lft.verifications.*;

import unittesting.*;

public class LeanFtTest extends UnitTestClassBase {

    private static Browser browser;
    public LeanFtTest() {
        //Change this constructor to private if you supply your own public constructor
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        instance = new LeanFtTest();
        globalSetup(LeanFtTest.class);
        browser = BrowserFactory.launch(BrowserType.CHROME);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        browser.close();
        globalTearDown();
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void test() throws GeneralLeanFtException {
        browser.navigate("www.google.com");
        EditField field = browser.describe(EditField.class, new EditFieldDescription.Builder()
                .name("q")
                .tagName("INPUT")
                .type("text").build());

        field.setValue ("StormRunner Functional");

        browser.describe(Button.class, new ButtonDescription.Builder()
                .buttonType("submit")
                .name("Google Search")
                .tagName("INPUT").build()).click();

        Verify.isTrue(browser.describe(Page.class, new PageDescription()).getTitle().matches("^StormRunner Functional.*"));

    }

}
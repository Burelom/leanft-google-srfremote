package net.mf;

import static org.junit.Assert.*;

import com.hp.lft.report.ReportException;
import com.hp.lft.report.Reporter;
import com.hp.lft.report.Status;
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
    private static String myString = "StormRunner Functional";

    public LeanFtTest() {
        //Change this constructor to private if you supply your own public constructor
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        instance = new LeanFtTest();
        globalSetup(LeanFtTest.class);

    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        globalTearDown();
    }

    @Before
    public void setUp() throws Exception {
        browser = BrowserFactory.launch(BrowserType.CHROME);
    }

    @After
    public void tearDown() throws Exception {
        browser.close();
    }

    @Test
    public void test() throws GeneralLeanFtException, ReportException {
        browser.navigate("www.google.com");
        EditField field = browser.describe(EditField.class, new EditFieldDescription.Builder()
                .name("q")
                .tagName("INPUT")
                .type("text").build());

        field.setValue (myString);

        browser.describe(Button.class, new ButtonDescription.Builder()
                .buttonType("submit")
                .name("Google Search")
                .tagName("INPUT").build()).click();

        Status p_f;
        if (Verify.isTrue(browser.describe(Page.class, new PageDescription()).getTitle().matches("^"+myString+".*"))){
            p_f = Status.Passed;
        } else {
            p_f = Status.Failed;
        }
        Reporter.reportEvent("Title Check", "<h1>"+myString+"</h1><br>is the title to validate", p_f);

    }

}
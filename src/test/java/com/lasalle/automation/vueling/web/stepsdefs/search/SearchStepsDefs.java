package com.lasalle.automation.vueling.web.stepsdefs.search;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.concurrent.TimeUnit;

public class SearchStepsDefs {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static WebDriver driver;

    @Before
    public static void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "/home/alejandro/Maestria/chromedriver_linux64/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.vueling.com/es");
        LOGGER.debug("driver started");
    }

    @After
    public static void afterClass() {
        driver.close();
        LOGGER.debug("driver closed");
    }

    @Given("^I'm main page$")
    public void iMMainPage(){
        LOGGER.debug("I'm in main page starts");
    }

    @When("^I try to find a fly$")
    public void iTryToFindAFly() {
    }

    @Then("^I get available flight$")
    public void iGetAvailableFlight() {
    }
}

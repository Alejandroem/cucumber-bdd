package com.lasalle.automation.vueling.web.stepsdefs.search;

import com.lasalle.automation.vueling.web.domain.SearchDto;
import com.lasalle.automation.vueling.web.pages.SearchPage;
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
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SearchStepsDefs {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static WebDriver driver;

    private String url ="https://www.vueling.com/es";


    private SearchPage searchPage;

    private List<SearchDto> searches;

    @Before
    public static void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "/home/alejandro/Maestria/chromedriver_linux64/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
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
        this.driver.get(this.url);
    }

    @When("^I try to find a fly$")
    public void iTryToFindAFly(List<SearchDto> searchesDtoList) {
        LOGGER.debug("iTryToFindAFly starts , list size [{}]", searchesDtoList.size());
        searchPage.setDriver(this.driver);
        searches = searchesDtoList;
        searches.forEach( searchDto -> searchPage.searchFlight(searchDto));
    }

    @Then("^I get available flight$")
    public void iGetAvailableFlight() {
    }
}

package com.lasalle.automation.vueling.web.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchResultPage extends PageObject {
    @FindBy(css= ".availabilityBody")
    private WebElementFacade flightResults;


    public boolean isOnResultPage(){
        return this.flightResults.isPresent();
    }

}

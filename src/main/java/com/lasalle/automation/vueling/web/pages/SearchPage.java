package com.lasalle.automation.vueling.web.pages;

import com.lasalle.automation.vueling.web.domain.SearchDto;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class SearchPage  extends PageObject{
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @FindBy(css = ".searchbar-container .searchbar-main .searchbar-main_tab-content .searchbar-main_tab-content_pane .form-group>.form-input.origin .input-group input")
    private WebElementFacade Name;

    @FindBy(css = ".searchbar-container .searchbar-main .searchbar-main_tab-content .searchbar-main_tab-content_pane .form-group>.form-input.destination .input-group input")
    private WebElementFacade Destination;

    @FindBy(css= "#inputGoing")
    private WebElementFacade Outbound;

    private WebElementFacade Return;

    @FindBy( css="#passengers-input")
    private WebElementFacade Passengers;

    @FindBy( css="#btnSubmitHomeSearcher")
    private WebElementFacade btnSearch;

    public void searchFlight(SearchDto searchDto){
        LOGGER.debug("Search flight starts, search: [{}]", searchDto);

        typeInto(Name, searchDto.getOrigin());
        typeInto(Destination, searchDto.getDestination());

        ((JavascriptExecutor)this.getDriver()).executeScript(
                "arguments[0].removeAttribute('readonly','readonly')",Outbound);

        ((JavascriptExecutor)this.getDriver()).executeScript(
                "arguments[0].value = arguments[1]",Outbound, searchDto.getOutbound());

        //typeInto(Return, searchDto.getReturnDate());
        typeInto(Passengers, searchDto.getPassengers());

        btnSearch.click();

    }



}

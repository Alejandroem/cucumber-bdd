package com.lasalle.automation.vueling.web.pages;

import com.lasalle.automation.vueling.web.domain.SearchDto;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class SearchPage  extends PageObject{
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private WebElementFacade txtOrigin;
    private WebElementFacade txtDestination;
    private WebElementFacade txtOutbound;
    private WebElementFacade txtReturn;
    private WebElementFacade txtPassengers;

    private WebElementFacade btnSearch;

    public void searchFlight(SearchDto searchDto){
        LOGGER.debug("Search flight starts, search: [{}]", searchDto);

        typeInto(txtOrigin, searchDto.getOrigin());
        typeInto(txtDestination, searchDto.getDestination());
        typeInto(txtOutbound, searchDto.getOutbound());
        typeInto(txtReturn, searchDto.getReturnDate());
        typeInto(txtPassengers, searchDto.getPassengers());

        btnSearch.click();

    }



}

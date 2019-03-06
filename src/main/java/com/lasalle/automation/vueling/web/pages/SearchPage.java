package com.lasalle.automation.vueling.web.pages;

import com.lasalle.automation.vueling.web.domain.SearchDto;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.List;


public class SearchPage  extends PageObject{
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @FindBy(css = ".searchbar-container .searchbar-main .searchbar-main_tab-content .searchbar-main_tab-content_pane .form-group>.form-input.origin .input-group input")
    private WebElementFacade Name;

    @FindBy(css = ".searchbar-container .searchbar-main .searchbar-main_tab-content .searchbar-main_tab-content_pane .form-group>.form-input.destination .input-group input")
    private WebElementFacade Destination;

    @FindBy(css= "#inputGoing")
    private WebElementFacade Outbound;


    @FindBy(css = "td:not(.ui-state-disabled) a.ui-state-default")
    private List<WebElementFacade> availableDaysOnCalendar;

    @FindBy(css = ".ui-state-active")
    private List<WebElementFacade> active;

    private WebElementFacade Return;

    @FindBy( css="#passengers-input")
    private WebElementFacade Passengers;

    @FindBy( css="#btnSubmitHomeSearcher")
    private WebElementFacade btnSearch;

    public void searchFlight(SearchDto searchDto){
        LOGGER.debug("Search flight starts, search: [{}]", searchDto);

        Name.typeAndEnter(searchDto.getOrigin());
        Destination.typeAndEnter(searchDto.getDestination());
        clickAvailableOutbound(searchDto.getOutbound());
        btnSearch.click();
    }

    private void clickAvailableOutbound(String flightOutbound){
        boolean hasChangedMonth = false;

        int currentDay = Integer.parseInt(active.get(0).getText());
        int firstDayOfAllowedPeriod= currentDay + getDaysForPeriod(flightOutbound);

        for (int i = 0; i < availableDaysOnCalendar.size(); i++) {
            int currentSearchedDay = Integer.parseInt(availableDaysOnCalendar.get(i).getText());

            if (!hasChangedMonth && currentSearchedDay < currentDay){
                hasChangedMonth = true;
            }

            if (currentSearchedDay > firstDayOfAllowedPeriod || hasChangedMonth){
                availableDaysOnCalendar.get(i).click();
                break;
            }
        }
    }

    private int getDaysForPeriod(String periodName){
        switch(periodName){
            case "NEXT_WEEK":
                return 7;
            default:
                return 1;
        }
    }

}

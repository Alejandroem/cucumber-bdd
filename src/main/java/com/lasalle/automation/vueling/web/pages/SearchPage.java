package com.lasalle.automation.vueling.web.pages;

import com.lasalle.automation.vueling.web.domain.DateRanges;
import com.lasalle.automation.vueling.web.domain.SearchDto;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import static com.lasalle.automation.vueling.web.domain.DateRanges.*;


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

        Name.typeAndEnter(searchDto.getOrigin());
        Destination.typeAndEnter(searchDto.getDestination());
//        ((JavascriptExecutor)this.getDriver()).executeScript(
//                "arguments[0].removeAttribute('readonly','readonly')",Outbound);
//
//        ((JavascriptExecutor)this.getDriver()).executeScript(
//                "arguments[0].value = arguments[1]",Outbound, searchDto.getOutbound());
//
//        ((JavascriptExecutor)this.getDriver()).executeScript(
//                "arguments[0].removeAttribute('readonly','readonly')",Passengers);
        Outbound.click();
        selectDate(searchDto.getOutbound());

        //typeInto(Passengers, searchDto.getPassengers());

        btnSearch.click();


    }

    private void selectDate(String outbound) {

        //Calcular la fecha de la siguiente semana
        Date outboundDate = this.getDateToSelect(outbound);

        String xpathSelector = "//div[contains(@class,'"+getMonth(outboundDate)+"')]//*[text() = '"+getDay(outboundDate)+"']";
        System.out.println("Xpath selector "+ xpathSelector);
       //div[contains(@class,'ui-datepicker-group-first')]//*[text() = '17']
        waitFor(xpathSelector);
        WebElement calendarDay = getDriver().findElement(By.xpath(xpathSelector));
        calendarDay.click();
    }

    private Date getDateToSelect(String outbound) {
        switch (outbound){
            case "NEXT_WEEK":
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                calendar.add(Calendar.DAY_OF_YEAR, 7);
                return calendar.getTime();

            default:
                return new Date();
        }
    }

    private boolean isOnThisMont(Date compare){
        Date today = new Date();

        LocalDate localDateToday = today.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDateCompare = compare.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return localDateToday.getMonth() == localDateCompare.getMonth();
    }

    private Integer getDay(Date date){
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.getDayOfMonth();

    }

    private String getMonth(Date outboundDate){
        if(isOnThisMont(outboundDate)){
            return "ui-datepicker-group-first";
        }else{
            return "ui-datepicker-group-middle";
        }
    }


}

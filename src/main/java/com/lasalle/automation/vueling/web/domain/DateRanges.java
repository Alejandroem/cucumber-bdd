package com.lasalle.automation.vueling.web.domain;

public enum DateRanges {
    NEXT_WEEK("NEXT_WEEK");

    private final String range;
    DateRanges(String range){
        this.range = range;
    }
    public String getRange(){ return this.range; }
}

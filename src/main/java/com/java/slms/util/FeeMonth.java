package com.java.slms.util;

public enum FeeMonth
{
    JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE,
    JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER;
	
	public FeeMonth next() {
	    return this.ordinal() < values().length - 1
	            ? values()[this.ordinal() + 1]
	            : null;
	}
}

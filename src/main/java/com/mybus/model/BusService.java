package com.mybus.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.joda.time.DateTime;

import java.util.List;
import java.util.Set;

/**
 * Created by skandula on 12/30/15.
 */
@ToString
@ApiModel(value = "BusService")
public class BusService extends AbstractDocument {

	@Getter
	@Setter
	private boolean active;

	@Getter
	@Setter
	private String serviceName;

	@Getter
	@Setter
	private String serviceNumber;

	@Getter
	@Setter
	private String phoneEnquiry;

	@Getter
	@Setter
	@ApiModelProperty(notes = "Cut off time in minutes for disabling reservations " +
			"for this service before the bus starts")
	private int cutoffTime;
	
	@Getter
	@Setter
	private ServiceTaxType serviceTaxType;

	@Getter
	@Setter
	private double serviceTax;

	@Getter
	@Setter
	private String layoutId;

	@Getter
	@Setter
	private String routeId;

	@Getter
	@Setter
	//yyyy-MM-dd
	private DateTime effectiveFrom;

	@Getter
	@Setter
	private DateTime effectiveTo;

	@Getter
	@Setter
	private ServiceFrequency frequency;

	@Getter
	@Setter
	private Set<ServiceAmenity> amenities;

	@Getter
	@Setter
	private Set<ServiceBoardingPoint> boardingPoints;

	@Getter
	@Setter
	private Set<ServiceDropingPoint> dropingPoints;

	@Getter
	@Setter
	@ApiModelProperty(notes = "Days of the week to run the service")
	private List<String> weeklyDays;

	@Getter
	@Setter
	@ApiModelProperty(notes = "Dates for the service to be run, incase Frequency = 'Special' ")
	private List<DateTime> specialServiceDates;

	@Getter
	@Setter
	@ApiModelProperty(notes = "Fares for combination of routes with different cities")
	private List<ServiceFare> serviceFares;
	
	@Getter
	@Setter
	private String status;
	
	@Getter
	@Setter
	private double fare;
	
}

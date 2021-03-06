package com.mybus.model;

import com.mybus.service.ServiceConstants;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.*;

/**
 * Created by skandula on 2/13/16.
 */
@ToString
@ApiModel(value = "ServiceReport")
@Getter
@Setter

public class ServiceReport extends AbstractDocument  {
    public static final String STATUS_HALF = "Halt";
    public static final String STATUS_SUBMIT = "Submitted";
    public static final String COLLECTION_NAME = "serviceReport";
    public static final String SUBMITTED_ID = "formId";
    public static final String JOURNEY_DATE = "journeyDate";
    private String serviceName;
    private String serviceNumber;
    private String source;
    private String destination;
    private String busType;
    private String vehicleRegNumber;
    @Field(JOURNEY_DATE)
    private Date journeyDate;
    private Set<VehicleStaff> staff;
    private Collection<Booking> bookings;
    private Collection<Payment> expenses;
    private double netCashIncome;
    private double netRedbusIncome;
    private double netOnlineIncome;
    private double netIncome;
    private String submittedBy;
    private String verifiedBy;
    private int totalSeats;
    private ServiceStatus status;
    private String conductorInfo;
    private String notes;
    private boolean invalid;
    public String jDate;
    public ServiceReport() {
        this.staff = new HashSet<>();
        this.expenses = new ArrayList<>();
        this.bookings = new ArrayList<>();
    }
    public String getJDate(){
        if(this.journeyDate != null) {
            return ServiceConstants.df.format(this.getJourneyDate());
        } else {
            return null;
        }

    }

}

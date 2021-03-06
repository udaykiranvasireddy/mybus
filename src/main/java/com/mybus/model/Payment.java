package com.mybus.model;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

/**
 * Created by skandula on 3/31/15.
 */

@ToString
@ApiModel(value = "Payment")
@Getter
@Setter

public class Payment extends AbstractDocument {
    public static final String STATUS_AUTO = "Auto";
    public static final String STATUS_APPROVED = "Approved";
    public static final String STATUS_PENDING = "Pending";
    public static final String STATUS_REJECTED = "Rejected";
    public static final String SERVIVE_FROM_PAYMENT = "Service Form";
    public static final String BOOKING_DUE_PAYMENT = "Booking Due";
    public static final String CASH_TRANSFER = "Cash Transfer";
    public static final String BRANCHOFFICEID = "branchOfficeId";

    private Date date;
    private String dateStr;
    private String name;
    private int index;
    private String description;
    private PaymentType type;
    private double amount;
    private String formId;
    private String branchOfficeId;
    private String status;
    private String serviceFormId;
    private String vehicleId;
    private String bookingId;
    private Date duePaidOn;
    public Payment() {
        //this.status = STATUS_PENDING;
    }
}

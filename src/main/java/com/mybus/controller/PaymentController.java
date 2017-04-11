package com.mybus.controller;

import com.mybus.controller.util.ControllerUtils;
import com.mybus.dao.impl.PaymentMongoDAO;
import com.mybus.model.Payment;
import com.mybus.service.PaymentManager;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/v1/")
public class PaymentController {
    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    private PaymentMongoDAO paymentMongoDAO;

    @Autowired
    private PaymentManager paymentManager;

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "payments", method = RequestMethod.POST, produces = ControllerUtils.JSON_UTF8)
    public Page<Payment> getPayments(HttpServletRequest request, @RequestBody(required = false) final JSONObject query,
                                     final Pageable pageable) {
        return paymentManager.findPayments(query, pageable);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "payments/pending", method = RequestMethod.GET, produces = ControllerUtils.JSON_UTF8)
    public Page<Payment> getPendingPayments(HttpServletRequest request, final Pageable pageable) {
        return paymentManager.findPendingPayments(pageable);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "payments/approved", method = RequestMethod.GET, produces = ControllerUtils.JSON_UTF8)
    public Page<Payment> getApprovedPayments(HttpServletRequest request, final Pageable pageable) {
        return paymentManager.findNonPendingPayments(pageable);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "payment/{id}", method = RequestMethod.GET, produces = ControllerUtils.JSON_UTF8)
    public Payment getPayment(HttpServletRequest request, @PathVariable final String id) {
        return paymentManager.findOne(id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "payments/count", method = RequestMethod.GET)
    public long getCount(HttpServletRequest request,@RequestParam(value = "pending", required = true) boolean pendingPayments) {
        return paymentMongoDAO.getPaymentsCount(pendingPayments);
    }
    
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "payments/day", method = RequestMethod.GET, produces = ControllerUtils.JSON_UTF8)
    public Page<Payment> getPaymentsByDay(HttpServletRequest request,
                                          @RequestParam(value = "date", required = true)
                                          @ApiParam(value = "Date in yyyy/mm/dd format") String date, final Pageable pageable) {
        return paymentMongoDAO.findPaymentsByDate(date, pageable);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "payment", method = RequestMethod.POST, produces = ControllerUtils.JSON_UTF8,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Payment create(HttpServletRequest request, @RequestBody final Payment paymnet) {
        logger.debug("post payment called");
        return paymentMongoDAO.save(paymnet);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "payment", method = RequestMethod.PUT, produces = ControllerUtils.JSON_UTF8,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Payment updatePayment(HttpServletRequest request, @RequestBody final Payment paymnet) {
        logger.debug("put payment called");
        return paymentManager.updatePayment(paymnet);
    }

    @RequestMapping(value = "payment/{id}", method = RequestMethod.DELETE, produces = ControllerUtils.JSON_UTF8)
    @ApiOperation(value ="Delete a Payment", response = Payment.class)
    public JSONObject delete(HttpServletRequest request,
                             @ApiParam(value = "Id of the Payment to be removed") @PathVariable final String id) {
        logger.debug("delete Payment called");
        JSONObject response = new JSONObject();
        paymentManager.delete(id);
        response.put("deleted", true);
        return response;
    }

}

package com.mybus.service;

import com.google.common.base.Preconditions;
import com.mybus.dao.RequiredFieldValidator;
import com.mybus.dao.ShipmentDAO;
import com.mybus.dao.impl.ShipmentMongoDAO;
import com.mybus.exception.BadRequestException;
import com.mybus.model.Shipment;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by srinikandula on 12/10/16.
 */
@Service
public class ShipmentManager {
    private static final Logger logger = LoggerFactory.getLogger(ShipmentManager.class);

    @Autowired
    private ShipmentDAO shipmentDAO;

    @Autowired
    private ShipmentMongoDAO shipmentMongoDAO;

    public Shipment findOne(String shipmentId) {
        Preconditions.checkNotNull(shipmentId, "shipmentId is required");
        Shipment shipment = shipmentDAO.findOne(shipmentId);
        Preconditions.checkNotNull(shipment, "No Shipment found with id");
        return shipment;
    }

    public Shipment save(Shipment shipment) {
        Preconditions.checkNotNull(shipment.getEmail(), "EMail is required");
        Preconditions.checkNotNull(shipment.getFromCityId(), "FromCityId is required");
        Preconditions.checkNotNull(shipment.getToCityId(), "ToCityId is required");
        Preconditions.checkNotNull(shipment.getFromContact(), "FromContact is required");
        Preconditions.checkNotNull(shipment.getToContact(), "ToContact is required");
        Preconditions.checkNotNull(shipment.getToContact(), "ToContact is required");
        Preconditions.checkNotNull(shipment.getNoOfPackages(), "Number of packages is required");
        Preconditions.checkNotNull(shipment.getNoOfPackages(), "Number of packages is required");

        return shipmentDAO.save(shipment);
    }

    public Shipment saveWithValidations(Shipment shipment) {
        List<String> errors = RequiredFieldValidator.validateModel(shipment, Shipment.class);
        if(errors.isEmpty()) {
            return shipmentDAO.save(shipment);
        } else {
            throw new BadRequestException("Required data missing ");
        }
    }

    public Iterable<Shipment> findShipments(JSONObject query, final Pageable pageable) {
        if(logger.isDebugEnabled()) {
            logger.debug("Looking up shipments with {0}", query);
        }
        return shipmentMongoDAO.findShipments(query, pageable);
    }

    public void delete(String shipmentId) {
        Preconditions.checkNotNull(shipmentId, "ShipmentId can not be null");
        Shipment shipment = shipmentDAO.findOne(shipmentId);
        Preconditions.checkNotNull(shipment, "No Shipment found with id");
        shipmentDAO.delete(shipment);
    }
}

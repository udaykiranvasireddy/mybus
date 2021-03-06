package com.mybus.dao.impl;

import com.mybus.controller.AbstractControllerIntegrationTest;
import com.mybus.dao.ShipmentDAO;
import com.mybus.model.Shipment;
import com.mybus.test.util.ShipmentTestService;
import org.apache.commons.collections.IteratorUtils;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.*;
import java.util.List;

/**
 * Created by srinikandula on 12/11/16.
 */
public class ShipmentMongoDAOTest extends AbstractControllerIntegrationTest {

    @Autowired
    private MongoQueryDAO mongoQueryDAO;

    @Autowired
    private ShipmentDAO shipmentDAO;

    @Before
    @After
    public void cleanup() {
        shipmentDAO.deleteAll();
    }

    @Test
    public void testFindShipments() throws Exception {
        for(int i=0; i<5; i++) {
            Shipment shipment = ShipmentTestService.createNew();
            shipment = shipmentDAO.save(shipment);
            System.out.println(shipment.getId());
        }
        JSONObject query = new JSONObject();
        query.put("fromCityId", "1234");
        List<Shipment> shipments = IteratorUtils.toList(
                mongoQueryDAO.getDocuments(Shipment.class, Shipment.COLLECTION_NAME, null, query, null).iterator());
        assertEquals(5, shipments.size());
        query.put("fromCityId", "12345");
        shipments = IteratorUtils.toList(
                mongoQueryDAO.getDocuments(Shipment.class, Shipment.COLLECTION_NAME, null, query, null).iterator());
        assertEquals(0, shipments.size());

        //test with toCityId
        query.remove("fromCityId");
        query.put("toCityId", "1234");
        shipments = IteratorUtils.toList(
                mongoQueryDAO.getDocuments(Shipment.class, Shipment.COLLECTION_NAME, null, query, null).iterator());
        assertEquals(5, shipments.size());
        query.put("toCityId", "12345");
        shipments = IteratorUtils.toList(
                mongoQueryDAO.getDocuments(Shipment.class, Shipment.COLLECTION_NAME, null, query, null).iterator());
        assertEquals(0, shipments.size());

        //test my email
        query.remove("toCityId");
        query.put("email", "email@e.com");
        shipments = IteratorUtils.toList(
                mongoQueryDAO.getDocuments(Shipment.class, Shipment.COLLECTION_NAME, null, query, null).iterator());
        assertEquals(5, shipments.size());

        //change email and test
        Shipment shipment = shipments.get(0);
        shipment.setEmail("srini@email.com");
        shipmentDAO.save(shipment);
        query.put("email", "srini@email.com");
        shipments = IteratorUtils.toList(
                mongoQueryDAO.getDocuments(Shipment.class, Shipment.COLLECTION_NAME, null, query, null).iterator());
        assertEquals(1, shipments.size());

        //test querying only email field
        String fields[] = {"email"};
        shipments = IteratorUtils.toList(
                mongoQueryDAO.getDocuments(Shipment.class, Shipment.COLLECTION_NAME, fields, query, null).iterator());
        assertEquals(1, shipments.size());
        shipment = shipments.get(0);
        assertEquals("srini@email.com", shipment.getEmail());
        assertNull(shipment.getFromCityId());
        assertNull(shipment.getToCityId());
    }
}
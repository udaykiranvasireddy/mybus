package com.mybus.service;

import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mybus.controller.AbstractControllerIntegrationTest;
import com.mybus.dao.AmenityDAO;
import com.mybus.model.Amenity;

import junit.framework.Assert;

/**
 * 
 * @author yks-Srinivas
 *
 */

public class AmenitiesManagerTest extends AbstractControllerIntegrationTest {

	@Autowired
	public AmenityDAO amenityDAO;


	@Autowired
	public AmenitiesManager amenitiesManager;

	private void cleanup() {
		amenityDAO.deleteAll();
	}
	@After
	public void teardown() {
		cleanup();
	}

	@Test
	public void amenitTest(){
		Amenity amenity = new Amenity();
		amenity.setName("bottle");
		amenity.setActive(true);

		Amenity a = amenitiesManager.save(amenity);
		Assert.assertNotNull(a);
		Assert.assertNotNull(a.getId());
		
		Assert.assertEquals(true, a.isActive());
		Assert.assertEquals("bottle", a.getName());
		
		a.setActive(false);
		Amenity a1 = amenitiesManager.upateAmenity(a);
		
		Assert.assertEquals(a1.getId(), a.getId());
		Assert.assertEquals(false, a.isActive());
		
		Assert.assertEquals(a1, amenitiesManager.getAmenityById(a1.getId()));
	}
}

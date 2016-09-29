package com.mybus.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.mybus.dao.AmenityDAO;
import com.mybus.model.Amenity;

/**
 * 
 * @author yks-Srinivas
 *
 */

@Service
public class AmenitiesManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(AmenitiesManager.class);
	
	@Autowired
	private AmenityDAO amenityDAO; 
	
	public Iterable<Amenity> findAll(){
		return amenityDAO.findAll();
	}
	
	public Amenity save(Amenity amenity){
		Preconditions.checkNotNull(amenity, "The Amenity can not be null");
		Preconditions.checkNotNull(amenity.getName(), "The Amenity name can not be null");
		return amenityDAO.save(amenity);
	}
	
	public Amenity upateAmenity(Amenity amenity){
		Preconditions.checkNotNull(amenity, "The Amenity can not be null");
		Preconditions.checkNotNull(amenity.getId(), "The Amenity id can not be null");
		Preconditions.checkNotNull(amenity.getName(), "The Amenity name can not be null");
		Amenity a = getAmenityById(amenity.getId());
		try {
			a.merge(amenity);
			amenityDAO.save(a);
		} catch (Exception e) {
			LOGGER.error("Error updating the Route ", e);
	        throw new RuntimeException(e);
		}
		return a;
	}
	
	public Amenity getAmenityById(String amenityID){
		Preconditions.checkNotNull(amenityID, "The Amenity id can not be null");
		return amenityDAO.findOne(amenityID);
	}
	
	public boolean deleteAmenity(String amenityID){
		Preconditions.checkNotNull(amenityID, "The Amenity id can not be null");
		amenityDAO.delete(amenityID); 
		return true;
	}
	public void deleteAll() {
		amenityDAO.deleteAll();
	}
}

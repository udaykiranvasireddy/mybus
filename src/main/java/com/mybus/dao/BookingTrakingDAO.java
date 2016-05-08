package com.mybus.dao;

import com.mybus.model.BookingTracking;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingTrakingDAO extends  PagingAndSortingRepository<BookingTracking, String> {
	
}

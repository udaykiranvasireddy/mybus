package com.mybus.dao;

import com.mybus.model.Vehicle;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by skandula on 3/31/15.
 */
@Repository
public interface VehicleDAO extends PagingAndSortingRepository<Vehicle, String> {
    Vehicle findOneByRegNo(String regNo);
}

package com.mybus.dao;

import com.mybus.model.PaymentGateway;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by skandula on 5/7/16.
 */
@Repository
public interface PaymentGatewayDAO extends PagingAndSortingRepository<PaymentGateway, String> {
    PaymentGateway findByName(String name);
}

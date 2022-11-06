package org.elasticsearch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.elasticsearch.model.Customer;

public interface CustomerService {
    String createCustomer(Customer customer) throws JsonProcessingException, Exception;
}

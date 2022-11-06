package org.elasticsearch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.elasticsearch.model.Customer;

import java.util.List;

public interface CustomerService {
    String createCustomer(Customer customer) throws JsonProcessingException, Exception;

    List<Customer> getClientsWithName(String nameOfCustomer) throws Exception;
}

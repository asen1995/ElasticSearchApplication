package org.elasticsearch;

import org.elasticsearch.model.Customer;
import org.elasticsearch.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

  
    @Autowired
    CustomerService customerService;
    @RequestMapping(value = "createCustomer", method = RequestMethod.POST)
    public String createCustomer(@RequestBody Customer customer) throws Exception {
        return customerService.createCustomer(customer);        
    }


    @RequestMapping(value = "getCustomers", method = RequestMethod.GET)
    public List<Customer> getCustomers() throws Exception {
        return customerService.getClientsWithName("asen");
    }

    
}
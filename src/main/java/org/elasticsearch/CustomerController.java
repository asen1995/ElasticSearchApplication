package org.elasticsearch;

import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Autowired
    RestHighLevelClient restHighLevelClient;
    
    @RequestMapping(value = "createCustomer", method = RequestMethod.POST)
    public String createCustomer(@RequestBody Customer request) throws Exception {
        return null;
    }
}
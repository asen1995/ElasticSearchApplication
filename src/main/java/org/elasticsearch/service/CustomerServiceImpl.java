package org.elasticsearch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.model.Customer;
import org.elasticsearch.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    RestHighLevelClient restHighLevelClient;
    @Autowired
    ObjectMapper objectMapper;
        
    @Override
    public String createCustomer(Customer customer) throws Exception {
        IndexRequest indexRequest = Requests.indexRequest(Constants.ELASTIC_SEARCH_CUSTOMER_INDEX)
                .source(convertProductToMap(customer));

        RequestOptions options = RequestOptions.DEFAULT;
        final IndexResponse index = restHighLevelClient.index(indexRequest, options);

        if(index.getResult() == DocWriteResponse.Result.CREATED){
            return "SUCCESS";
        }

        return "NOT CREATED";
    }

    private Map<String, Object> convertProductToMap(Customer product) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(product);
        return objectMapper.readValue(json, Map.class);
    }
    
}

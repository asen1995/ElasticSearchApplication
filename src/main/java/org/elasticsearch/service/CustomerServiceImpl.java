package org.elasticsearch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.model.Customer;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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


    @Override
    public List<Customer> getClientsWithName(String nameOfCustomer) throws Exception {
        
        SearchRequest searchRequest = Requests.searchRequest(Constants.ELASTIC_SEARCH_CUSTOMER_INDEX);

        // bool query
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .should(QueryBuilders.matchQuery("name",nameOfCustomer));


        // pagination
        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource()
//                .from(searchQuery.getPage() * searchQuery.getSize())
//                .size(searchQuery.getSize())
                .query(boolQueryBuilder);
        
        searchRequest.source(searchSourceBuilder);
        
        final SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);


        List<Customer> customerList = new ArrayList<>();
        if(search.getHits().getHits().length > 0){
            final SearchHit[] hits = search.getHits().getHits();            
            
            for (SearchHit hit : hits) {
                customerList.add(objectMapper.convertValue(hit.getSourceAsString(),Customer.class));                
            }
        }

        return customerList;
    }
    
    
    private Map<String, Object> convertProductToMap(Customer product) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(product);
        return objectMapper.readValue(json, Map.class);
    }
    
}

package com.github.trade.goods;

import com.alibaba.fastjson.JSON;
import com.github.trade.goods.model.Person;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ESTest {

    @Autowired
    private RestHighLevelClient client;

    @Test
    public void esPingTest(){
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(
                new HttpHost("http://127.0.0.1/", 9200, "http")
        ));
        System.out.println(JSON.toJSONString(client));
    }

    /**
     * insert doc
     * @throws Exception
     */
    @Test
    public void addDocTest() throws Exception{
        Person person = new Person();
        person.setId("121");
        person.setName("张学友");
        person.setAddress(("香港那嘎达"));
        person.setAge(81);
        //Serialize the object to JSON
        String data = JSON.toJSONString(person);
        IndexRequest request = new IndexRequest("person").
                                    id(person.getId()).
                                    source(data, XContentType.JSON);
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
        System.out.println(response.getId());

    }



    @Test
    public void  matchAll() throws IOException {
        //构建查询请求，指定查询的索引库
        SearchRequest searchRequest = new SearchRequest("person");

        //创建查询条件构造器 SearchSourceBuilder
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        /*
         * 构建查询条件
         * 查询所有文档
         */
        MatchAllQueryBuilder query = QueryBuilders.matchAllQuery();

        //指定查询条件
        searchSourceBuilder.query(query);

        /*
         * 指定分页查询信息
         * 从哪里开始查
         */
        searchSourceBuilder.from(0);
        //每次查询的数量
        searchSourceBuilder.size(2);

        searchRequest.source(searchSourceBuilder);

        //查询获取查询结果
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(searchResponse));

        //获取命中对象
        SearchHits searchHits = searchResponse.getHits();
        long totalNum = searchHits.getTotalHits().value;
        System.out.println("总记录数："+totalNum);

        List<Person> personList = new ArrayList<>();
        //获取命中的hits数据,搜索结果数据
        SearchHit[] hits = searchHits.getHits();
        for(SearchHit searchHit : hits){
            //获取json字符串格式的数据
            String sourceAsString = searchHit.getSourceAsString();
            Person person = JSON.parseObject(sourceAsString, Person.class);
            personList.add(person);
        }

        System.out.println(JSON.toJSONString(personList));
    }

    /**
     * term词条查询
     */
    @Test
    public  void match() throws IOException {
        //构建查询请求，指定查询的索引库
        SearchRequest searchRequest = new SearchRequest("person");

        //创建查询条件构造器 SearchSourceBuilder
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        /*
         * 构建查询条件
         * 查询所有文档
         */
        MatchQueryBuilder query = QueryBuilders.matchQuery("name","Tibber");


        //指定查询条件
        searchSourceBuilder.query(query);

        /*
         * 指定分页查询信息
         * 从哪里开始查
         */
        searchSourceBuilder.from(0);
        //每次查询的数量
        searchSourceBuilder.size(5);

        searchRequest.source(searchSourceBuilder);

        //查询获取查询结果
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(searchResponse));

        //获取命中对象
        SearchHits searchHits = searchResponse.getHits();
        long totalNum = searchHits.getTotalHits().value;
        System.out.println("总记录数："+totalNum);

        List<Person> personList = new ArrayList<>();
        //获取命中的hits数据,搜索结果数据
        SearchHit[] hits = searchHits.getHits();
        for(SearchHit searchHit : hits){
            //获取json字符串格式的数据
            String sourceAsString = searchHit.getSourceAsString();
            Person person = JSON.parseObject(sourceAsString, Person.class);
            personList.add(person);
        }

        System.out.println(JSON.toJSONString(personList));
    }

    @Test
    public void  queryString() throws IOException {
        //构建查询请求，指定查询的索引库
        SearchRequest searchRequest = new SearchRequest("person");

        //创建查询条件构造器 SearchSourceBuilder
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        /*
         * 构建查询条件
         * 查询所有文档
         */
        QueryStringQueryBuilder query = QueryBuilders.queryStringQuery("香港 OR 台湾").field("name").field("address").defaultOperator(Operator.OR);


        //指定查询条件
        searchSourceBuilder.query(query);

        /*
         * 指定分页查询信息
         * 从哪里开始查
         */
        searchSourceBuilder.from(0);
        //每次查询的数量
        searchSourceBuilder.size(5);

        searchRequest.source(searchSourceBuilder);

        //查询获取查询结果
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(searchResponse));

        //获取命中对象
        SearchHits searchHits = searchResponse.getHits();
        long totalNum = searchHits.getTotalHits().value;
        System.out.println("总记录数："+totalNum);

        List<Person> personList = new ArrayList<>();
        //获取命中的hits数据,搜索结果数据
        SearchHit[] hits = searchHits.getHits();
        for(SearchHit searchHit : hits){
            //获取json字符串格式的数据
            String sourceAsString = searchHit.getSourceAsString();
            Person person = JSON.parseObject(sourceAsString, Person.class);
            personList.add(person);
        }

        System.out.println(JSON.toJSONString(personList));
    }


    /**
     * term词条查询
     */
    @Test
    public void  queryMatch() throws IOException {
        //构建查询请求，指定查询的索引库
        SearchRequest searchRequest = new SearchRequest("person");

        //创建查询条件构造器 SearchSourceBuilder
        //  SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();


        /**
         * 查询单个,等于
         */

        //单个匹配，搜索name为li的文档
        // QueryBuilder query = QueryBuilders.matchQuery("name", "张学*");

        //搜索名字中含有li文档（name中只要包含li即可）
        //WildcardQueryBuilder queryBuilder = QueryBuilders.wildcardQuery("name","张学*");
//搜索name中或nickname中包含有li的文档（必须与li一致）
        //  QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery("张学友","name", "address");

        // WildcardQueryBuilder queryBuilder = QueryBuilders.wildcardQuery("张学*","name","address");

//        //创建查询条件构造器 SearchSourceBuilder
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//
//        //模糊查询
//        WildcardQueryBuilder queryBuilder1 = QueryBuilders.wildcardQuery("name", "*张学*");//搜索name中含有 张学 的文档
//        WildcardQueryBuilder queryBuilder2 = QueryBuilders.wildcardQuery("address", "*香*");//搜索address中含有 香 的文档
//
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//        //name中必须含有 张学,address中必须含有 香 ,must相当于SQL中的 and
//        boolQueryBuilder.must(queryBuilder1);
//        boolQueryBuilder.must(queryBuilder2);
//
//        //指定查询条件
//        searchSourceBuilder.query(boolQueryBuilder);



        //创建查询条件构造器 SearchSourceBuilder
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //模糊查询
        WildcardQueryBuilder queryBuilder1 = QueryBuilders.wildcardQuery("name", "*Te*");//搜索name中含有 张学 的文档
        WildcardQueryBuilder queryBuilder2 = QueryBuilders.wildcardQuery("address", "*港*");//搜索address中含有 香 的文档

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //name中必须含有 张学,address中必须含有 香 ,should 相当于SQL中的 or
        boolQueryBuilder.should(queryBuilder1);
        boolQueryBuilder.should(queryBuilder2);

        //指定查询条件
        searchSourceBuilder.query(boolQueryBuilder);

        /*
         * 指定分页查询信息
         * 从哪里开始查
         */
        searchSourceBuilder.from(0);
        //每次查询的数量
        searchSourceBuilder.size(5);

        searchRequest.source(searchSourceBuilder);

        //查询获取查询结果
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(searchResponse));

        //获取命中对象
        SearchHits searchHits = searchResponse.getHits();
        long totalNum = searchHits.getTotalHits().value;
        System.out.println("总记录数："+totalNum);

        List<Person> personList = new ArrayList<>();
        //获取命中的hits数据,搜索结果数据
        SearchHit[] hits = searchHits.getHits();
        for(SearchHit searchHit : hits){
            //获取json字符串格式的数据
            String sourceAsString = searchHit.getSourceAsString();
            Person person = JSON.parseObject(sourceAsString, Person.class);
            personList.add(person);
        }

        System.out.println(JSON.toJSONString(personList));
    }
}

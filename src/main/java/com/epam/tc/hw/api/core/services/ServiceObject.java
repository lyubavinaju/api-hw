package com.epam.tc.hw.api.core.services;

import com.epam.tc.hw.api.properties.TrelloProperties;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;

public abstract class ServiceObject {
    protected static final String BOARD_BY_ID = "/1/boards/{boardId}";
    protected static final String LIST_BY_ID = "/1/lists/{id}";

    protected final RequestSpecification requestSpec;

    public ServiceObject(Map<String, String> queryParams,
                         Map<String, String> pathParams) {
        this.requestSpec = requestSpec().queryParams(queryParams)
                                        .pathParams(pathParams);
    }

    public static RequestSpecification requestSpec() {
        return new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .setBaseUri(TrelloProperties.baseUrl())
            .addQueryParam("key", TrelloProperties.key())
            .addQueryParam("token", TrelloProperties.token())
            .addFilters(List.of(
                new RequestLoggingFilter(LogDetail.METHOD),
                new RequestLoggingFilter(LogDetail.URI),
                new ResponseLoggingFilter(LogDetail.STATUS)))
            .build();
    }

    public static ResponseSpecification okResponseSpec() {
        return new ResponseSpecBuilder()
            .expectResponseTime(Matchers.lessThan(3000L))
            .expectStatusCode(HttpStatus.SC_OK)
            .expectContentType(ContentType.JSON)
            .build();
    }

    public static ResponseSpecification notFoundResponseSpec() {
        return new ResponseSpecBuilder()
            .expectResponseTime(Matchers.lessThan(3000L))
            .expectStatusCode(HttpStatus.SC_NOT_FOUND)
            .expectContentType(ContentType.TEXT)
            .build();
    }

    public abstract Response sendRequest();
}

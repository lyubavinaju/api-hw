package com.epam.tc.hw.api.core.services;

import com.epam.tc.hw.api.endpoints.Endpoints;
import com.epam.tc.hw.api.properties.UserProperties;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import java.util.Map;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;

public abstract class ServiceObject {
    protected final RequestSpecification requestSpec;

    public ServiceObject(Map<String, String> queryParams,
                              Map<String, String> pathParams) {
        this.requestSpec = requestSpec().queryParams(queryParams)
                                        .pathParams(pathParams);
    }

    public static RequestSpecification requestSpec() {
        return new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .setBaseUri(Endpoints.BASE_URL)
            .addQueryParam("key", UserProperties.key())
            .addQueryParam("token", UserProperties.token())
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

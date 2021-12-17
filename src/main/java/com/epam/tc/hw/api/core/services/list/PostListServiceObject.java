package com.epam.tc.hw.api.core.services.list;

import com.epam.tc.hw.api.core.services.ServiceObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.Map;

public class PostListServiceObject extends ServiceObject {
    public static final String LISTS = "/1/lists";

    public PostListServiceObject(Map<String, String> queryParams,
                                 Map<String, String> pathParams) {
        super(queryParams, pathParams);
    }

    @Override
    public Response sendRequest() {
        return RestAssured.given()
                          .spec(requestSpec)
                          .post(LISTS);
    }
}

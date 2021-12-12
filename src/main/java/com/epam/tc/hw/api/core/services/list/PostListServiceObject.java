package com.epam.tc.hw.api.core.services.list;

import com.epam.tc.hw.api.core.services.ServiceObject;
import com.epam.tc.hw.api.endpoints.Endpoints;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.Map;

public class PostListServiceObject extends ServiceObject {
    public PostListServiceObject(Map<String, String> queryParams,
                                 Map<String, String> pathParams) {
        super(queryParams, pathParams);
    }

    @Override
    public Response sendRequest() {
        return RestAssured.given()
                          .spec(requestSpec)
                          .post(Endpoints.LISTS);
    }
}

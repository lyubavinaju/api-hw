package com.epam.tc.hw.api.core.services.list;

import com.epam.tc.hw.api.core.services.ServiceObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.Map;

public class GetListServiceObject extends ServiceObject {
    public GetListServiceObject(Map<String, String> queryParams,
                                Map<String, String> pathParams) {
        super(queryParams, pathParams);
    }

    @Override
    public Response sendRequest() {
        return RestAssured.given()
                          .spec(requestSpec)
                          .get(LIST_BY_ID);
    }
}

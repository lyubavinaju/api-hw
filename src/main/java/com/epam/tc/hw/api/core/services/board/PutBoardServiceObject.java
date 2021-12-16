package com.epam.tc.hw.api.core.services.board;

import com.epam.tc.hw.api.core.services.ServiceObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.Map;

public class PutBoardServiceObject extends ServiceObject {
    public PutBoardServiceObject(Map<String, String> queryParams,
                                 Map<String, String> pathParams) {
        super(queryParams, pathParams);
    }

    @Override
    public Response sendRequest() {
        return RestAssured.given()
                          .spec(requestSpec)
                          .put(BOARD_BY_ID);
    }
}

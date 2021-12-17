package com.epam.tc.hw.api.core.services.board;

import com.epam.tc.hw.api.core.services.ServiceObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.Map;

public class GetBoardServiceObject extends ServiceObject {
    public GetBoardServiceObject(Map<String, String> queryParams,
                                 Map<String, String> pathParams) {
        super(queryParams, pathParams);
    }

    @Override
    public Response sendRequest() {
        return RestAssured.given()
                          .spec(requestSpec)
                          .get(BOARD_BY_ID);
    }
}

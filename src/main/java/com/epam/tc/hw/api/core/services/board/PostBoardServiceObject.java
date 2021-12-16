package com.epam.tc.hw.api.core.services.board;

import com.epam.tc.hw.api.core.services.ServiceObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.Map;

public class PostBoardServiceObject extends ServiceObject {
    private static final String BOARDS = "/1/boards/";

    public PostBoardServiceObject(Map<String, String> queryParams,
                                  Map<String, String> pathParams) {
        super(queryParams, pathParams);
    }

    @Override
    public Response sendRequest() {
        return RestAssured.given()
                          .spec(requestSpec)
                          .post(BOARDS);
    }
}

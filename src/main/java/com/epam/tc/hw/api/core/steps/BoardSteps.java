package com.epam.tc.hw.api.core.steps;

import com.epam.tc.hw.api.beans.Board;
import com.epam.tc.hw.api.core.builders.BoardRequestBuilder;
import com.epam.tc.hw.api.core.services.ServiceObject;
import io.restassured.response.Response;

public class BoardSteps {

    public static Board getBoard(String id) {
        Response response = new BoardRequestBuilder().setBoardId(id).buildGet()
                                                     .sendRequest();
        response.then().assertThat().spec(ServiceObject.okResponseSpec());
        return response.body().as(Board.class);
    }

    public static Board createBoard(Board board) {
        Response response = new BoardRequestBuilder()
            .setName(board.getName()).setPrefsBackground(board.getPrefs().getBackground()).buildPost()
            .sendRequest();
        response.then().assertThat().spec(ServiceObject.okResponseSpec());
        return response.body().as(Board.class);
    }

    public static void deleteBoard(String id) {
        Response response = new BoardRequestBuilder().setBoardId(id).buildDelete()
                                                     .sendRequest();
        response.then().assertThat().spec(ServiceObject.okResponseSpec());
    }
}

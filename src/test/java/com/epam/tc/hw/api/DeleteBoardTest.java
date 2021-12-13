package com.epam.tc.hw.api;

import com.epam.tc.hw.api.beans.Board;
import com.epam.tc.hw.api.core.builders.BoardRequestBuilder;
import com.epam.tc.hw.api.core.services.ServiceObject;
import com.epam.tc.hw.api.core.steps.BoardSteps;
import org.testng.annotations.Test;

public class DeleteBoardTest {
    @Test
    public void testDeleteBoard() {
        Board board = BoardSteps.createBoard();
        BoardSteps.deleteBoard(board.getId());

        new BoardRequestBuilder().setBoardId(board.getId()).buildGet()
                                 .sendRequest()
                                 .then().assertThat().spec(ServiceObject.notFoundResponseSpec());
    }
}

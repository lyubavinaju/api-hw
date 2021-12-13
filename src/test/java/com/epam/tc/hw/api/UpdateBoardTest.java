package com.epam.tc.hw.api;

import com.epam.tc.hw.api.beans.Board;
import com.epam.tc.hw.api.core.builders.BoardRequestBuilder;
import com.epam.tc.hw.api.core.services.ServiceObject;
import com.epam.tc.hw.api.core.steps.BoardSteps;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UpdateBoardTest {
    private Board board;

    @BeforeMethod
    public void setUp() {
        board = BoardSteps.createBoard();
    }

    @AfterMethod
    public void tearDown() {
        BoardSteps.deleteBoard(board.getId());
    }

    @Test
    public void testUpdateBoard() {
        String newName = RandomStringUtils.randomAlphanumeric(10);

        Response updateResponse = new BoardRequestBuilder().setBoardId(board.getId()).setName(newName).buildPut()
                                                           .sendRequest();
        updateResponse.then().assertThat().spec(ServiceObject.okResponseSpec());

        Board actualBoard = BoardSteps.getBoard(board.getId());
        Assert.assertEquals(actualBoard.getName(), newName);
        Assert.assertEquals(actualBoard.getPrefs().getBackground(), board.getPrefs().getBackground());
    }
}

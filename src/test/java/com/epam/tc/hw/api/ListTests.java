package com.epam.tc.hw.api;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;

import com.epam.tc.hw.api.beans.Board;
import com.epam.tc.hw.api.beans.List;
import com.epam.tc.hw.api.core.builders.ListRequestBuilder;
import com.epam.tc.hw.api.core.services.ServiceObject;
import com.epam.tc.hw.api.core.steps.BoardSteps;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ListTests {

    private Board board;

    @BeforeMethod
    public void setUp() {
        board = BoardSteps.createBoard();
    }

    @AfterMethod
    public void tearDown() {
        BoardSteps.deleteBoard(board.getId());
    }

    private List createList(List list) {
        Response response = new ListRequestBuilder().setIdBoard(list.getIdBoard()).setName(list.getName()).buildPost()
                                                    .sendRequest();
        response.then().assertThat().spec(ServiceObject.okResponseSpec());
        return response.body().as(List.class);
    }

    private List getList(String id) {
        Response response = new ListRequestBuilder().setId(id).buildGet()
                                                    .sendRequest();
        response.then().assertThat().spec(ServiceObject.okResponseSpec());
        return response.body().as(List.class);
    }

    @Test
    public void testCreateList() {

        List list = new List().withName("list 1").withIdBoard(board.getId());

        String id = createList(list).getId();

        List actualList = getList(id);

        assertThat(actualList, allOf(
            hasProperty("name", equalTo(list.getName())),
            hasProperty("idBoard", equalTo(list.getIdBoard())),
            hasProperty("closed", equalTo(false))
        ));
    }

    @Test
    public void testCloseList() {
        List list = new List().withName("list 1").withIdBoard(board.getId());

        String id = createList(list).getId();

        new ListRequestBuilder().setId(id).setClosed(true).buildPut()
                                .sendRequest()
                                .then().assertThat().spec(ServiceObject.okResponseSpec());
        List actualList = getList(id);

        assertThat(actualList, allOf(
            hasProperty("name", equalTo(list.getName())),
            hasProperty("idBoard", equalTo(list.getIdBoard())),
            hasProperty("closed", equalTo(true))
        ));
    }
}

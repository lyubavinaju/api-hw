package com.epam.tc.hw.api;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;

import com.epam.tc.hw.api.beans.Board;
import com.epam.tc.hw.api.beans.List;
import com.epam.tc.hw.api.beans.Prefs;
import com.epam.tc.hw.api.core.builders.ListRequestBuilder;
import com.epam.tc.hw.api.core.services.ServiceObject;
import com.epam.tc.hw.api.core.steps.BoardSteps;
import com.epam.tc.hw.api.core.steps.ListSteps;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ListTests {

    private Board board;

    @BeforeMethod
    public void setUp() {
        board = BoardSteps.createBoard(new Board().withName("board 3")
                                                  .withPrefs(new Prefs().withBackground("lime")));
    }

    @AfterMethod
    public void tearDown() {
        BoardSteps.deleteBoard(board.getId());
    }

    @Test
    public void testCreateList() {

        List list = new List().withName("list 1").withIdBoard(board.getId());

        String id = ListSteps.createList(list).getId();

        List actualList = ListSteps.getList(id);

        assertThat(actualList, allOf(
            hasProperty("name", equalTo(list.getName())),
            hasProperty("idBoard", equalTo(list.getIdBoard())),
            hasProperty("closed", equalTo(false))
        ));
    }

    @Test
    public void testCloseList() {
        List list = new List().withName("list 1").withIdBoard(board.getId());

        String id = ListSteps.createList(list).getId();

        new ListRequestBuilder().setId(id).setClosed(true).buildPut()
                                .sendRequest()
                                .then().assertThat().spec(ServiceObject.okResponseSpec());
        List actualList = ListSteps.getList(id);

        assertThat(actualList, allOf(
            hasProperty("name", equalTo(list.getName())),
            hasProperty("idBoard", equalTo(list.getIdBoard())),
            hasProperty("closed", equalTo(true))
        ));
    }
}

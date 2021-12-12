package com.epam.tc.hw.api.endpoints;

public class Endpoints {
    public static final String BASE_URL = "https://api.trello.com";

    public static final String BOARDS = "/1/boards/";

    public static final String BOARD_BY_ID = "/1/boards/{boardId}";

    public static final String LISTS_BY_BOARD_ID = "/1/boards/{boardId}/lists";

    public static final String LISTS = "/1/lists";

    public static final String LIST_BY_ID = "/1/lists/{id}";
}

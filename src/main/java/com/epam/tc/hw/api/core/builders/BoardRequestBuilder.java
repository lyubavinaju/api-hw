package com.epam.tc.hw.api.core.builders;

import com.epam.tc.hw.api.core.services.ServiceObject;
import com.epam.tc.hw.api.core.services.board.DeleteBoardServiceObject;
import com.epam.tc.hw.api.core.services.board.GetBoardServiceObject;
import com.epam.tc.hw.api.core.services.board.PostBoardServiceObject;
import com.epam.tc.hw.api.core.services.board.PutBoardServiceObject;
import java.util.HashMap;
import java.util.Map;

public class BoardRequestBuilder {
    private final Map<String, String> queryParams = new HashMap<>();
    private final Map<String, String> pathParams = new HashMap<>();

    public BoardRequestBuilder setBoardId(String boardId) {
        pathParams.put("boardId", boardId);
        return this;
    }

    public BoardRequestBuilder setName(String name) {
        queryParams.put("name", name);
        return this;
    }

    public BoardRequestBuilder setPrefsBackground(String prefsBackground) {
        queryParams.put("prefs_background", prefsBackground);
        return this;
    }

    public ServiceObject buildPost() {
        return new PostBoardServiceObject(queryParams, pathParams);
    }

    public ServiceObject buildDelete() {
        return new DeleteBoardServiceObject(queryParams, pathParams);
    }

    public ServiceObject buildPut() {
        return new PutBoardServiceObject(queryParams, pathParams);
    }

    public ServiceObject buildGet() {
        return new GetBoardServiceObject(queryParams, pathParams);
    }
}

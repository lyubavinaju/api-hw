package com.epam.tc.hw.api.core.builders;

import com.epam.tc.hw.api.core.services.ServiceObject;
import com.epam.tc.hw.api.core.services.list.GetListServiceObject;
import com.epam.tc.hw.api.core.services.list.PostListServiceObject;
import com.epam.tc.hw.api.core.services.list.PutListServiceObject;
import java.util.HashMap;
import java.util.Map;

public class ListRequestBuilder {
    private final Map<String, String> queryParams = new HashMap<>();
    private final Map<String, String> pathParams = new HashMap<>();

    public ListRequestBuilder setName(String name) {
        queryParams.put("name", name);
        return this;
    }

    public ListRequestBuilder setIdBoard(String idBoard) {
        queryParams.put("idBoard", idBoard);
        return this;
    }

    public ListRequestBuilder setId(String id) {
        pathParams.put("id", id);
        return this;
    }

    public ListRequestBuilder setClosed(boolean closed) {
        queryParams.put("closed", String.valueOf(closed));
        return this;
    }

    public ServiceObject buildPost() {
        return new PostListServiceObject(queryParams, pathParams);
    }

    public ServiceObject buildGet() {
        return new GetListServiceObject(queryParams, pathParams);
    }

    public ServiceObject buildPut() {
        return new PutListServiceObject(queryParams, pathParams);
    }
}

package com.epam.tc.hw.api.core.steps;

import com.epam.tc.hw.api.beans.List;
import com.epam.tc.hw.api.core.builders.ListRequestBuilder;
import com.epam.tc.hw.api.core.services.ServiceObject;
import io.restassured.response.Response;

public class ListSteps {

    public static List createList(List list) {
        Response response = new ListRequestBuilder().setIdBoard(list.getIdBoard()).setName(list.getName()).buildPost()
                                                    .sendRequest();
        response.then().assertThat().spec(ServiceObject.okResponseSpec());
        return response.body().as(List.class);
    }

    public static List getList(String id) {
        Response response = new ListRequestBuilder().setId(id).buildGet()
                                                    .sendRequest();
        response.then().assertThat().spec(ServiceObject.okResponseSpec());
        return response.body().as(List.class);
    }
}

package com.flock.app.serializer;

import com.atlassian.confluence.event.events.content.page.PageEvent;
import com.atlassian.confluence.pages.Page;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class PageEventAdapter implements JsonSerializer<PageEvent> {

    @Override
    public JsonElement serialize(PageEvent pageEvent, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.add("page", jsonSerializationContext.serialize(pageEvent.getPage(), Page.class));

        return jsonObject;
    }
}

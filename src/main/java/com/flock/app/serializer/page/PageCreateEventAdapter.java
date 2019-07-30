package com.flock.app.serializer.page;

import com.atlassian.confluence.event.events.content.page.PageCreateEvent;
import com.atlassian.confluence.pages.Page;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class PageCreateEventAdapter implements JsonSerializer<PageCreateEvent> {
    @Override
    public JsonElement serialize(PageCreateEvent pageCreateEvent, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("page", jsonSerializationContext.serialize(pageCreateEvent.getPage(), Page.class));
        return jsonObject;
    }
}

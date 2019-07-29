package com.flock.app.serializer;

import com.atlassian.confluence.core.BodyContent;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class BodyContentAdapter implements JsonSerializer<BodyContent> {
    @Override
    public JsonElement serialize(BodyContent bodyContent, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", bodyContent.getId());
        jsonObject.addProperty("body", bodyContent.getBody());
        jsonObject.addProperty("bodyCleaned", bodyContent.isBodyCleaned());
        jsonObject.addProperty("bodyType", bodyContent.getBodyType().toString());
        return jsonObject;
    }
}

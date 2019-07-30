package com.flock.app.serializer.confluenceUser;

import com.atlassian.confluence.user.ConfluenceUser;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class ConfluenceUserAdapter implements JsonSerializer<ConfluenceUser> {
    @Override
    public JsonElement serialize(ConfluenceUser confluenceUser, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("key", confluenceUser.getKey().getStringValue());
        jsonObject.addProperty("name", confluenceUser.getName());
        jsonObject.addProperty("fullName", confluenceUser.getFullName());
        jsonObject.addProperty("lowerName", confluenceUser.getLowerName());
        jsonObject.addProperty("email", confluenceUser.getEmail());
        return jsonObject;
    }
}

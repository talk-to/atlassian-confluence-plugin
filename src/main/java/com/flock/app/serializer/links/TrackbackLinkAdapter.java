package com.flock.app.serializer.links;

import com.atlassian.confluence.links.TrackbackLink;
import com.flock.app.serializer.CommonSerializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class TrackbackLinkAdapter implements JsonSerializer<TrackbackLink> {
    @Override
    public JsonElement serialize(TrackbackLink trackbackLink, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("title", trackbackLink.getTitle());
        jsonObject.addProperty("excerpt", trackbackLink.getExcerpt());
        jsonObject.addProperty("blogName", trackbackLink.getBlogName());
        jsonObject.addProperty("viewCount", trackbackLink.getViewCount());
        jsonObject.addProperty("url", trackbackLink.getUrl());
        jsonObject.addProperty("lowerUrl", trackbackLink.getLowerUrl());
        jsonObject.addProperty("linkTitle", trackbackLink.getLinkTitle());
        CommonSerializer.serialize(jsonObject, trackbackLink, jsonSerializationContext);
        return jsonObject;
    }
}

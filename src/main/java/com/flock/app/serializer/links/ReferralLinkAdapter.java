package com.flock.app.serializer.links;

import com.atlassian.confluence.links.ReferralLink;
import com.flock.app.serializer.CommonSerializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class ReferralLinkAdapter implements JsonSerializer<ReferralLink> {
    @Override
    public JsonElement serialize(ReferralLink referralLink, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("viewCount", referralLink.getViewCount());
        jsonObject.addProperty("url", referralLink.getUrl());
        jsonObject.addProperty("lowerUrl", referralLink.getLowerUrl());
        jsonObject.addProperty("linkTitle", referralLink.getLinkTitle());
        CommonSerializer.serialize(jsonObject, referralLink, jsonSerializationContext);
        return null;
    }
}

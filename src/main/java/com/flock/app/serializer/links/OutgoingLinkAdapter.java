package com.flock.app.serializer.links;

import com.atlassian.confluence.links.OutgoingLink;
import com.flock.app.serializer.CommonSerializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class OutgoingLinkAdapter implements JsonSerializer<OutgoingLink> {
    @Override
    public JsonElement serialize(OutgoingLink outgoingLink, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("destinationSpaceKey", outgoingLink.getDestinationPageTitle());
        jsonObject.addProperty("destinationPageTitle", outgoingLink.getDestinationPageTitle());
        jsonObject.addProperty("lowerDestinationSpaceKey", outgoingLink.getLowerDestinationSpaceKey());
        jsonObject.addProperty("lowerDestinationPageTitle", outgoingLink.getLowerDestinationPageTitle());
        jsonObject.addProperty("linkTitle", outgoingLink.getLinkTitle());
        CommonSerializer.serialize(jsonObject, outgoingLink, jsonSerializationContext);
        return jsonObject;
    }
}

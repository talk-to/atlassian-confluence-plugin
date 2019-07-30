package com.flock.app.serializer.comment;

import com.atlassian.confluence.pages.Comment;
import com.flock.app.serializer.CommonSerializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class CommentAdapter implements JsonSerializer<Comment> {

    @Override
    public JsonElement serialize(Comment comment, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("isInlineComment", comment.isInlineComment());
        jsonObject.addProperty("commentStatus", comment.getStatus().getValue().getStringValue());
        CommonSerializer.serializeContentEntityObject(jsonObject, comment, jsonSerializationContext);
        return jsonObject;
    }
}

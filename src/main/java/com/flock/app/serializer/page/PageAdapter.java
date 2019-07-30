package com.flock.app.serializer.page;

import com.atlassian.confluence.pages.Comment;
import com.atlassian.confluence.pages.Page;
import com.atlassian.confluence.spaces.Space;
import com.flock.app.serializer.CommonSerializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class PageAdapter implements JsonSerializer<Page> {
    @Override
    public JsonElement serialize(Page page, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("position", page.getPosition());
        jsonObject.addProperty("isRootLevel", page.isRootLevel());
        jsonObject.addProperty("isHomePage", page.isHomePage());
        jsonObject.addProperty("isIndexable", page.isIndexable());
        jsonObject.addProperty("contentId", page.getContentId().asLong());
        jsonObject.addProperty("urlPath", page.getUrlPath());
        jsonObject.addProperty("editUrlPath", page.getEditUrlPath());
        jsonObject.add("pageLevelComments", jsonSerializationContext.serialize(page.getPageLevelComments(), new TypeToken<List<Comment>>() {
        }.getType()));
        jsonObject.add("topLevelComments", jsonSerializationContext.serialize(page.getTopLevelComments(), new TypeToken<List<Comment>>() {
        }.getType()));
        jsonObject.addProperty("attachmentsUrlPath", page.getAttachmentsUrlPath());
        jsonObject.addProperty("confluenceRevision", page.getConfluenceRevision());
        jsonObject.add("space", jsonSerializationContext.serialize(page.getSpace(), Space.class));
        CommonSerializer.serializeContentEntityObject(jsonObject, page, jsonSerializationContext);
        return jsonObject;
    }
}

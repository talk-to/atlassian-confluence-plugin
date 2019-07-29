package com.flock.app.serializer;

import com.atlassian.confluence.core.BodyContent;
import com.atlassian.confluence.pages.Page;
import com.atlassian.confluence.spaces.Space;
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

        // Page
        jsonObject.addProperty("position", page.getPosition());
        jsonObject.addProperty("isRootLevel", page.isRootLevel());
        jsonObject.addProperty("isHomePage", page.isHomePage());
        jsonObject.addProperty("linkWikiMarkup", page.getLinkWikiMarkup());
        jsonObject.addProperty("isIndexable", page.isIndexable());
        jsonObject.addProperty("contentId", page.getContentId().asLong());

        // AbstractPage
        jsonObject.addProperty("urlPath", page.getUrlPath());
        jsonObject.addProperty("editUrlPath", page.getEditUrlPath());
//        jsonObject.add("pageLevelComments", jsonSerializationContext.serialize(page.getPageLevelComments(), new TypeToken<List<Comment>>() {
//        }.getType()));
//        jsonObject.add("topLevelComments", jsonSerializationContext.serialize(page.getTopLevelComments(), new TypeToken<List<Comment>>() {
//        }.getType()));
        jsonObject.addProperty("attachmentsUrlPath", page.getAttachmentsUrlPath());
        jsonObject.addProperty("confluenceRevision", page.getConfluenceRevision());

        // SpaceContentEntityObject
        jsonObject.add("space", jsonSerializationContext.serialize(page.getSpace(), Space.class));

        // ContentEntityObject
        jsonObject.addProperty("contentType", page.getTypeEnum().getRepresentation());
        jsonObject.addProperty("idAsString", page.getIdAsString());
        jsonObject.addProperty("title", page.getTitle());
        jsonObject.addProperty("lowerTitle", page.getLowerTitle());
        jsonObject.add("bodyContent", jsonSerializationContext.serialize(page.getBodyContent(), BodyContent.class));
        jsonObject.add("bodyContents", jsonSerializationContext.serialize(page.getBodyContents(), new TypeToken<List<BodyContent>>() {
        }.getType()));
        jsonObject.addProperty("bodyAsString", page.getBodyAsString());
//        jsonObject.add("outgoingLinks", jsonSerializationContext.serialize(page.getOutgoingLinks(), new TypeToken<List<OutgoingLink>>() {
//        }.getType()));
//        jsonObject.add("referralLinks", jsonSerializationContext.serialize(page.getReferralLinks(), new TypeToken<List<ReferralLink>>() {
//        }.getType()));
//        jsonObject.add("trackBackLinks", jsonSerializationContext.serialize(page.getTrackbackLinks(), new TypeToken<List<TrackbackLink>>() {
//        }.getType()));
        jsonObject.addProperty("contentStatus", page.getContentStatus());
        jsonObject.addProperty("isCurrent", page.isCurrent());
        jsonObject.addProperty("isDeleted", page.isDeleted());
        jsonObject.addProperty("isDraft", page.isDraft());
        jsonObject.addProperty("shareId", page.getShareId());
        jsonObject.addProperty("synchronyRevision", page.getSynchronyRevision());
        jsonObject.addProperty("synchronyRevisionSource", page.getSynchronyRevisionSource());
        jsonObject.addProperty("synchronyRevisionSource", page.getSynchronyRevisionSource());
        jsonObject.addProperty("collaborativeEditingUuid", page.getCollaborativeEditingUuid());
        jsonObject.addProperty("isUnpublished", page.isUnpublished());
        jsonObject.addProperty("bodyAsStringWithoutMarkup", page.getBodyAsStringWithoutMarkup());
        jsonObject.addProperty("excerpt", page.getExcerpt());
        jsonObject.addProperty("attachmentsUrlPath", page.getAttachmentsUrlPath());
        jsonObject.addProperty("versionpage", page.getVersionComment());
        jsonObject.addProperty("isVersionpageAvailable", page.isVersionCommentAvailable());
        jsonObject.addProperty("renderedVersionpage", page.getRenderedVersionComment());
        jsonObject.addProperty("contentId", page.getContentId().asLong());
        jsonObject.addProperty("latestVersionId", page.getLatestVersionId());
        jsonObject.addProperty("originalVersionId", page.getOriginalVersionId());

        // EntityObject
        jsonObject.addProperty("id", page.getId());
        jsonObject.addProperty("lastModificationData", page.getLastModificationDate().getTime());

        return jsonObject;
    }
}

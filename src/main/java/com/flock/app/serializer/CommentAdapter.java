package com.flock.app.serializer;

import com.atlassian.confluence.core.BodyContent;
import com.atlassian.confluence.links.OutgoingLink;
import com.atlassian.confluence.links.ReferralLink;
import com.atlassian.confluence.links.TrackbackLink;
import com.atlassian.confluence.pages.Comment;
import com.atlassian.confluence.spaces.Space;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class CommentAdapter implements JsonSerializer<Comment> {

    @Override
    public JsonElement serialize(Comment comment, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();

        // Comment
        jsonObject.add("space", jsonSerializationContext.serialize(comment.getSpace(), Space.class));
        jsonObject.addProperty("displayTitle", comment.getDisplayTitle());
        jsonObject.addProperty("urlPath", comment.getUrlPath());
        jsonObject.addProperty("type", comment.getType());
        jsonObject.addProperty("nameForComparison", comment.getNameForComparison());
        jsonObject.addProperty("descendantsCount", comment.getDescendantsCount());
        jsonObject.addProperty("depth", comment.getDepth());
        jsonObject.addProperty("threadChangeDate", comment.getThreadChangedDate().getTime());
        jsonObject.addProperty("linkWikiMarkup", comment.getLinkWikiMarkup());
        jsonObject.addProperty("isInlineComment", comment.isInlineComment());
        jsonObject.addProperty("lastModifier", comment.getStatus().getLastModifier());
        jsonObject.addProperty("commentStatus", comment.getStatus().getValue().getStringValue());

        // ContentEntityObject
        jsonObject.addProperty("contentType", comment.getTypeEnum().getRepresentation());
        jsonObject.addProperty("idAsString", comment.getIdAsString());
        jsonObject.addProperty("title", comment.getTitle());
        jsonObject.addProperty("lowerTitle", comment.getLowerTitle());
        jsonObject.add("bodyContent", jsonSerializationContext.serialize(comment.getBodyContent(), BodyContent.class));
        jsonObject.add("bodyContents", jsonSerializationContext.serialize(comment.getBodyContents(), new TypeToken<List<BodyContent>>() {
        }.getType()));
        jsonObject.addProperty("bodyAsString", comment.getBodyAsString());
        jsonObject.add("outgoingLinks", jsonSerializationContext.serialize(comment.getOutgoingLinks(), new TypeToken<List<OutgoingLink>>() {
        }.getType()));
        jsonObject.add("referralLinks", jsonSerializationContext.serialize(comment.getReferralLinks(), new TypeToken<List<ReferralLink>>() {
        }.getType()));
        jsonObject.add("trackBackLinks", jsonSerializationContext.serialize(comment.getTrackbackLinks(), new TypeToken<List<TrackbackLink>>() {
        }.getType()));
        jsonObject.addProperty("contentStatus", comment.getContentStatus());
        jsonObject.addProperty("isCurrent", comment.isCurrent());
        jsonObject.addProperty("isDeleted", comment.isDeleted());
        jsonObject.addProperty("isDraft", comment.isDraft());
        jsonObject.addProperty("shareId", comment.getShareId());
        jsonObject.addProperty("synchronyRevision", comment.getSynchronyRevision());
        jsonObject.addProperty("synchronyRevisionSource", comment.getSynchronyRevisionSource());
        jsonObject.addProperty("synchronyRevisionSource", comment.getSynchronyRevisionSource());
        jsonObject.addProperty("collaborativeEditingUuid", comment.getCollaborativeEditingUuid());
        jsonObject.addProperty("isUnpublished", comment.isUnpublished());
        jsonObject.addProperty("bodyAsStringWithoutMarkup", comment.getBodyAsStringWithoutMarkup());
        jsonObject.addProperty("excerpt", comment.getExcerpt());
        jsonObject.addProperty("attachmentsUrlPath", comment.getAttachmentsUrlPath());
        jsonObject.addProperty("versionComment", comment.getVersionComment());
        jsonObject.addProperty("isVersionCommentAvailable", comment.isVersionCommentAvailable());
        jsonObject.addProperty("renderedVersionComment", comment.getRenderedVersionComment());
        jsonObject.addProperty("contentId", comment.getContentId().asLong());
        jsonObject.addProperty("latestVersionId", comment.getLatestVersionId());
        jsonObject.addProperty("originalVersionId", comment.getOriginalVersionId());

        // EntityObject
        jsonObject.addProperty("id", comment.getId());
        jsonObject.addProperty("lastModificationData", comment.getLastModificationDate().getTime());
        return jsonObject;
    }
}

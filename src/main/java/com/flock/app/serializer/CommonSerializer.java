package com.flock.app.serializer;

import com.atlassian.confluence.core.BodyContent;
import com.atlassian.confluence.core.ContentEntityObject;
import com.atlassian.confluence.links.OutgoingLink;
import com.atlassian.confluence.links.ReferralLink;
import com.atlassian.confluence.links.TrackbackLink;
import com.atlassian.confluence.user.ConfluenceUser;
import com.atlassian.core.bean.EntityObject;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class CommonSerializer {

    public static void serializeContentEntityObject(JsonObject jsonObject, ContentEntityObject contentEntityObject, JsonSerializationContext jsonSerializationContext) {
        jsonObject.addProperty("contentId", contentEntityObject.getContentId().asLong());
        jsonObject.addProperty("title", contentEntityObject.getTitle());
        jsonObject.addProperty("lowerTitle", contentEntityObject.getLowerTitle());
        jsonObject.add("bodyContent", jsonSerializationContext.serialize(contentEntityObject.getBodyContent(), BodyContent.class));
        jsonObject.add("bodyContents", jsonSerializationContext.serialize(contentEntityObject.getBodyContents(), new TypeToken<List<BodyContent>>() {
        }.getType()));
        jsonObject.addProperty("bodyAsString", contentEntityObject.getBodyAsString());
        jsonObject.addProperty("bodyAsStringWithoutMarkup", contentEntityObject.getBodyAsStringWithoutMarkup());
        jsonObject.add("outgoingLinks", jsonSerializationContext.serialize(contentEntityObject.getOutgoingLinks(), new TypeToken<List<OutgoingLink>>() {
        }.getType()));
        jsonObject.add("referralLinks", jsonSerializationContext.serialize(contentEntityObject.getReferralLinks(), new TypeToken<List<ReferralLink>>() {
        }.getType()));
        jsonObject.add("trackBackLinks", jsonSerializationContext.serialize(contentEntityObject.getTrackbackLinks(), new TypeToken<List<TrackbackLink>>() {
        }.getType()));
        jsonObject.addProperty("contentStatus", contentEntityObject.getContentStatus());
        jsonObject.addProperty("isCurrent", contentEntityObject.isCurrent());
        jsonObject.addProperty("isDeleted", contentEntityObject.isDeleted());
        jsonObject.addProperty("isDraft", contentEntityObject.isDraft());
        jsonObject.addProperty("shareId", contentEntityObject.getShareId());
        jsonObject.addProperty("isUnpublished", contentEntityObject.isUnpublished());
        jsonObject.addProperty("excerpt", contentEntityObject.getExcerpt());
        jsonObject.addProperty("attachmentsUrlPath", contentEntityObject.getAttachmentsUrlPath());
        jsonObject.addProperty("versionComment", contentEntityObject.getVersionComment());
        jsonObject.addProperty("latestVersionId", contentEntityObject.getLatestVersionId());
        jsonObject.addProperty("originalVersionId", contentEntityObject.getOriginalVersionId());
        jsonObject.add("creator", jsonSerializationContext.serialize(contentEntityObject.getCreator(), ConfluenceUser.class));

        serializeEntityObject(jsonObject, contentEntityObject);
    }

    public static void serializeEntityObject(JsonObject jsonObject, EntityObject entityObject) {
        jsonObject.addProperty("id", entityObject.getId());
        jsonObject.addProperty("creationDate", entityObject.getCreationDate().getTime());
        jsonObject.addProperty("lastModificationDate", entityObject.getLastModificationDate().getTime());
    }
}

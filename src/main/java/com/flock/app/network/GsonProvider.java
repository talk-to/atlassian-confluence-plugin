package com.flock.app.network;

import com.atlassian.confluence.core.BodyContent;
import com.atlassian.confluence.event.events.content.page.PageCreateEvent;
import com.atlassian.confluence.event.events.content.page.PageUpdateEvent;
import com.atlassian.confluence.event.events.content.page.PageViewEvent;
import com.atlassian.confluence.pages.Comment;
import com.atlassian.confluence.pages.Page;
import com.atlassian.confluence.spaces.Space;
import com.atlassian.confluence.user.ConfluenceUser;
import com.flock.app.Logger;
import com.flock.app.serializer.BodyContentAdapter;
import com.flock.app.serializer.comment.CommentAdapter;
import com.flock.app.serializer.confluenceUser.ConfluenceUserAdapter;
import com.flock.app.serializer.page.PageAdapter;
import com.flock.app.serializer.page.PageCreateEventAdapter;
import com.flock.app.serializer.page.PageUpdateEventAdapter;
import com.flock.app.serializer.page.PageViewEventAdapter;
import com.flock.app.serializer.space.SpaceAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Named;

@Named
public class GsonProvider {

    private Gson gson;

    public Gson getGson() {
        if (gson == null) {
            Logger.println("GsonProvider Created");
            gson = new GsonBuilder()
                    .registerTypeAdapter(Comment.class, new CommentAdapter())
                    .registerTypeAdapter(ConfluenceUser.class, new ConfluenceUserAdapter())
                    .registerTypeAdapter(Page.class, new PageAdapter())
                    .registerTypeAdapter(PageCreateEvent.class, new PageCreateEventAdapter())
                    .registerTypeAdapter(PageUpdateEvent.class, new PageUpdateEventAdapter())
                    .registerTypeAdapter(PageViewEvent.class, new PageViewEventAdapter())
                    .registerTypeAdapter(Space.class, new SpaceAdapter())
                    .registerTypeAdapter(BodyContent.class, new BodyContentAdapter())
                    .create();
        }
        return gson;
    }
}

package com.flock.app.network;

import com.atlassian.confluence.core.BodyContent;
import com.atlassian.confluence.pages.Comment;
import com.atlassian.confluence.pages.Page;
import com.flock.app.Logger;
import com.flock.app.serializer.BodyContentAdapter;
import com.flock.app.serializer.CommentAdapter;
import com.flock.app.serializer.PageAdapter;
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
                    .registerTypeAdapter(Page.class, new PageAdapter())
                    .registerTypeAdapter(BodyContent.class, new BodyContentAdapter())
                    .create();
        }
        return gson;
    }
}

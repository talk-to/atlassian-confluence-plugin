package com.flock.app.listener;

import com.atlassian.confluence.core.ContentEntityObject;
import com.atlassian.confluence.event.events.content.comment.CommentCreateEvent;
import com.atlassian.confluence.event.events.content.comment.CommentRemoveEvent;
import com.atlassian.confluence.event.events.content.comment.CommentUpdateEvent;
import com.atlassian.event.api.EventListener;
import com.atlassian.event.api.EventPublisher;
import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.export.ExportAsService;
import com.atlassian.plugin.spring.scanner.annotation.imports.ConfluenceImport;
import com.flock.app.Logger;
import com.flock.app.network.MyNetworkClient;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.inject.Inject;
import javax.inject.Named;

@ExportAsService({MyAnnotatedEventListener.class})
@Named("myAnnotatedEventListener")
@Scanned
public class MyAnnotatedEventListener implements DisposableBean, InitializingBean {

    private EventPublisher eventPublisher;
    private MyNetworkClient myNetworkClient;

    @Inject
    public MyAnnotatedEventListener(@ConfluenceImport EventPublisher eventPublisher, MyNetworkClient myNetworkClient) {
        this.eventPublisher = eventPublisher;
        this.myNetworkClient = myNetworkClient;
        Logger.println("Listener Created");
    }

    @EventListener
    public void commentCreateEvent(CommentCreateEvent event) {
        Logger.println("Comment Create Event: " + event);
        myNetworkClient.makeEmptyPost(event);
    }

    @EventListener
    public void commentCreateEvent(CommentRemoveEvent event) {
        Logger.println("Comment Remove Event: " + event);
        myNetworkClient.makeEmptyPost(event);
    }

    @EventListener
    public void commentUpdateEvent(CommentUpdateEvent event) {
        Logger.println("Comment Update Event: " + event);
        myNetworkClient.makeEmptyPost(event);
    }

    public void destroy() throws Exception {
        eventPublisher.unregister(this);
        Logger.println("Listener Unregistered");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        eventPublisher.register(this);
        Logger.println("Listener Registered");
    }
}
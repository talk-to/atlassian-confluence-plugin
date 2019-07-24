package com.flock.app.listener;

import com.atlassian.confluence.event.events.content.comment.CommentCreateEvent;
import com.atlassian.event.api.EventPublisher;
import com.atlassian.plugin.spring.scanner.annotation.export.ExportAsService;
import com.atlassian.plugin.spring.scanner.annotation.imports.ConfluenceImport;
import org.apache.log4j.*;
import com.atlassian.event.api.EventListener;
import org.springframework.beans.factory.DisposableBean;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;

import javax.inject.Inject;
import javax.inject.Named;

@ExportAsService({MyAnnotatedEventListener.class})
@Named("myAnnotatedEventListener")
public class MyAnnotatedEventListener implements DisposableBean {

    private EventPublisher eventPublisher;

    @Inject
    public MyAnnotatedEventListener(@ConfluenceImport EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
        eventPublisher.register(this);

        System.out.println("Listener Initialized");
    }

    @EventListener
    public void commentCreateEvent(CommentCreateEvent event) {
        System.out.println("Comment Create Event: " + event);
    }

    // Unregister the listener if the plugin is uninstalled or disabled.
    public void destroy() throws Exception {
        eventPublisher.unregister(this);
    }
}
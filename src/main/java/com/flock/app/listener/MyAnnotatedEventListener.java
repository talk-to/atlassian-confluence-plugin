package com.flock.app.listener;

import com.atlassian.confluence.event.events.content.comment.CommentCreateEvent;
import com.atlassian.confluence.event.events.content.comment.CommentRemoveEvent;
import com.atlassian.confluence.event.events.content.comment.CommentUpdateEvent;
import com.atlassian.confluence.event.events.content.page.*;
import com.atlassian.confluence.event.events.space.*;
import com.atlassian.event.api.EventListener;
import com.atlassian.event.api.EventPublisher;
import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.export.ExportAsService;
import com.atlassian.plugin.spring.scanner.annotation.imports.ConfluenceImport;
import com.flock.app.ConfluenceActionType;
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
        myNetworkClient.sendEventToServer(ConfluenceActionType.COMMENT_CREATE, event.getComment());
    }

    @EventListener
    public void commentRemoveEvent(CommentRemoveEvent event) {
        myNetworkClient.sendEventToServer(ConfluenceActionType.COMMENT_REMOVE, event.getComment());
    }

    @EventListener
    public void commentUpdateEvent(CommentUpdateEvent event) {
        myNetworkClient.sendEventToServer(ConfluenceActionType.COMMENT_UPDATE, event.getComment());
    }

    @EventListener
    public void pageCreateEvent(PageCreateEvent event) {
        myNetworkClient.sendEventToServer(ConfluenceActionType.PAGE_CREATE, event.getPage());
    }

    @EventListener
    public void pageCopyEvent(PageCopyEvent event) {
        myNetworkClient.sendEventToServer(ConfluenceActionType.PAGE_COPY, event.getPage());
    }

    @EventListener
    public void pageMoveEvent(PageMoveEvent event) {
        myNetworkClient.sendEventToServer(ConfluenceActionType.PAGE_MOVE, event.getPage());
    }

    @EventListener
    public void pageRemoveEvent(PageRemoveEvent event) {
        myNetworkClient.sendEventToServer(ConfluenceActionType.PAGE_REMOVE, event.getPage());
    }

    @EventListener
    public void pageRestoreEvent(PageRestoreEvent event) {
        myNetworkClient.sendEventToServer(ConfluenceActionType.PAGE_RESTORE, event.getPage());
    }

    @EventListener
    public void pageTrashedEvent(PageTrashedEvent event) {
        myNetworkClient.sendEventToServer(ConfluenceActionType.PAGE_TRASHED, event.getPage());
    }

    @EventListener
    public void pageUpdateEvent(PageUpdateEvent event) {
        myNetworkClient.sendEventToServer(ConfluenceActionType.PAGE_UPDATE, event.getPage());
    }

    @EventListener
    public void pageViewEvent(PageViewEvent event) {
        myNetworkClient.sendEventToServer(ConfluenceActionType.PAGE_VIEW, event.getPage());
    }

    @EventListener
    public void spaceCreateEvent(SpaceCreateEvent event) {
        myNetworkClient.sendEventToServer(ConfluenceActionType.SPACE_CREATE, event.getSpace());
    }

    @EventListener
    public void spaceArchivedEvent(SpaceArchivedEvent event) {
        myNetworkClient.sendEventToServer(ConfluenceActionType.SPACE_ARCHIVED, event.getSpace());
    }

    @EventListener
    public void spacePermissionUpdateEvent(SpacePermissionsUpdateEvent event) {
        myNetworkClient.sendEventToServer(ConfluenceActionType.SPACE_PERMISSION_UPDATE, event.getSpace());
    }

    @EventListener
    public void spaceRemoveEvent(SpaceRemoveEvent event) {
        myNetworkClient.sendEventToServer(ConfluenceActionType.SPACE_REMOVE, event.getSpace());
    }

    @EventListener
    public void spaceTrashEmptyEvent(SpaceTrashEmptyEvent event) {
        myNetworkClient.sendEventToServer(ConfluenceActionType.SPACE_TRASH_EMPTY, event.getSpace());
    }

    @EventListener
    public void spaceTrashViewEvent(SpaceTrashViewEvent event) {
        myNetworkClient.sendEventToServer(ConfluenceActionType.SPACE_TRASH_VIEW, event.getSpace());
    }

    @EventListener
    public void spaceUnarchivedEvent(SpaceUnArchivedEvent event) {
        myNetworkClient.sendEventToServer(ConfluenceActionType.SPACE_UNARCHIVED, event.getSpace());
    }

    @EventListener
    public void spaceUpdateEvent(SpaceUpdateEvent event) {
        myNetworkClient.sendEventToServer(ConfluenceActionType.SPACE_UPDATE, event.getSpace());
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
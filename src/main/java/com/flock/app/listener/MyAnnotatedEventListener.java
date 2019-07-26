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
    public MyAnnotatedEventListener(@ConfluenceImport EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
        this.myNetworkClient = null;
        Logger.println("Listener Created");
    }

    @EventListener
    public void commentCreateEvent(CommentCreateEvent event) {
        myNetworkClient.sendEventToServer(ConfluenceActionType.COMMENT_CREATE, event);
    }

    @EventListener
    public void commentRemoveEvent(CommentRemoveEvent event) {
        myNetworkClient.sendEventToServer(ConfluenceActionType.COMMENT_REMOVE, event);
    }

    @EventListener
    public void commentUpdateEvent(CommentUpdateEvent event) {
        myNetworkClient.sendEventToServer(ConfluenceActionType.COMMENT_UPDATE, event);
    }

    @EventListener
    public void pageCreateEvent(PageCreateEvent event) {
        myNetworkClient.sendEventToServer(ConfluenceActionType.PAGE_CREATE, event);
    }

    @EventListener
    public void pageCopyEvent(PageCopyEvent event) {
        myNetworkClient.sendEventToServer(ConfluenceActionType.PAGE_COPY, event);
    }

    @EventListener
    public void pageCreateFromTempleteEvent(PageCreateFromTemplateEvent event) {
        myNetworkClient.sendEventToServer(ConfluenceActionType.PAGE_CREATE_FROM_TEMPLETE, event);
    }

    @EventListener
    public void pageInfoViewEvent(PageInfoViewEvent event) {
        myNetworkClient.sendEventToServer(ConfluenceActionType.PAGE_INFO_VIEW, event);
    }

    @EventListener
    public void pageListViewEvent(PageListViewEvent event) {
        myNetworkClient.sendEventToServer(ConfluenceActionType.PAGE_LIST_VIEW, event);
    }

    @EventListener
    public void pageMoveCompleteEvent(PageMoveCompletedEvent event) {
        myNetworkClient.sendEventToServer(ConfluenceActionType.PAGE_MOVE_COMPLETE, event);
    }

    @EventListener
    public void pageMoveEvent(PageMoveEvent event) {
        myNetworkClient.sendEventToServer(ConfluenceActionType.PAGE_MOVE, event);
    }

    @EventListener
    public void pageRemoveEvent(PageRemoveEvent event) {
        myNetworkClient.sendEventToServer(ConfluenceActionType.PAGE_REMOVE, event);
    }

    @EventListener
    public void pageRestoreEvent(PageRestoreEvent event) {
        myNetworkClient.sendEventToServer(ConfluenceActionType.PAGE_RESTORE, event);
    }

    @EventListener
    public void pageTrashedEvent(PageTrashedEvent event) {
        myNetworkClient.sendEventToServer(ConfluenceActionType.PAGE_TRASHED, event);
    }

    @EventListener
    public void pageUpdateEvent(PageUpdateEvent event) {
        myNetworkClient.sendEventToServer(ConfluenceActionType.PAGE_UPDATE, event);
    }

    @EventListener
    public void pageViewEvent(PageViewEvent event) {
        myNetworkClient.sendEventToServer(ConfluenceActionType.PAGE_VIEW, event);
    }

    @EventListener
    public void spaceCreateEvent(SpaceCreateEvent event) {
        myNetworkClient.sendEventToServer(ConfluenceActionType.SPACE_CREATE, event);
    }

    @EventListener
    public void spaceArchivedEvent(SpaceArchivedEvent event) {
        myNetworkClient.sendEventToServer(ConfluenceActionType.SPACE_ARCHIVED, event);
    }

    @EventListener
    public void spaceLogoUpdateEvent(SpaceLogoUpdateEvent event) {
        myNetworkClient.sendEventToServer(ConfluenceActionType.SPACE_LOGO_UPDATE, event);
    }

    @EventListener
    public void spacePermissionUpdateEvent(SpacePermissionsUpdateEvent event) {
        myNetworkClient.sendEventToServer(ConfluenceActionType.SPACE_PERMISSION_UPDATE, event);
    }

    @EventListener
    public void spaceRemoveEvent(SpaceRemoveEvent event) {
        myNetworkClient.sendEventToServer(ConfluenceActionType.SPACE_REMOVE, event);
    }

    @EventListener
    public void spaceTrashEmptyEvent(SpaceTrashEmptyEvent event) {
        myNetworkClient.sendEventToServer(ConfluenceActionType.SPACE_TRASH_EMPTY, event);
    }

    @EventListener
    public void spaceTrashViewEvent(SpaceTrashViewEvent event) {
        myNetworkClient.sendEventToServer(ConfluenceActionType.SPACE_TRASH_VIEW, event);
    }

    @EventListener
    public void spaceUnarchivedEvent(SpaceUnArchivedEvent event) {
        myNetworkClient.sendEventToServer(ConfluenceActionType.SPACE_UNARCHIVED, event);
    }

    @EventListener
    public void spaceUpdateEvent(SpaceUpdateEvent event) {
        myNetworkClient.sendEventToServer(ConfluenceActionType.SPACE_UPDATE, event);
    }

    @EventListener
    public void spaceWillREmoveEvent(SpaceWillRemoveEvent event) {
        myNetworkClient.sendEventToServer(ConfluenceActionType.SPACE_WILL_REMOVE, event);
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
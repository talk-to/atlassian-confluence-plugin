package com.flock.app.ampstutorial;

import com.atlassian.confluence.event.events.content.comment.CommentCreateEvent;
import com.atlassian.event.api.EventListener;
import com.atlassian.event.api.EventPublisher;
import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.export.ExportAsService;
import com.atlassian.plugin.spring.scanner.annotation.imports.ConfluenceImport;
import com.flock.app.Logger;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Listener that listens to every commit to a repository and sends a twitter update with the changeset description.
 * Every user's tweets are sent to individual accounts.
 * Requires user's Twitter credentials to be available in {@link BaseUrlStore}.
 */
@ExportAsService({CommitListener.class})
@Named("commitListener")
@Scanned
public class CommitListener implements InitializingBean, DisposableBean {

    private final EventPublisher eventPublisher;
    private final BaseUrlStore baseUrlStore;

    @Inject
    public CommitListener(@ConfluenceImport EventPublisher eventPublisher,
                          BaseUrlStore baseUrlStore) {
        this.eventPublisher = eventPublisher;
        this.baseUrlStore = baseUrlStore;
    }

    @EventListener
    public void handleEvent(CommentCreateEvent ce) {
        String baseUrl = baseUrlStore.get();
        if (!TextUtils.isEmpty(baseUrl)) {
            Logger.println("BaseUrl: "+ baseUrl);
        } else {
            Logger.println("Base Url not present");
        }
    }

    @Override
    public void afterPropertiesSet() {
        eventPublisher.register(this);
    }

    @Override
    public void destroy() {
        eventPublisher.unregister(this);
    }
}

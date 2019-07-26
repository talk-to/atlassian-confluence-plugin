package com.flock.app.ampstutorial;

import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.imports.ConfluenceImport;
import com.atlassian.templaterenderer.TemplateRenderer;
import com.flock.app.Logger;
import com.flock.app.Utils;
import com.google.common.collect.ImmutableMap;
import org.apache.http.util.TextUtils;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.google.common.base.Strings.isNullOrEmpty;

@Scanned
public class FlockSettingsServlet extends HttpServlet {

    private final BaseUrlStore baseUrlStore;
    private final TemplateRenderer templateRenderer;

    @Inject
    public FlockSettingsServlet(
            BaseUrlStore baseUrlStore,
            @ConfluenceImport TemplateRenderer templateRenderer) {
        this.baseUrlStore = baseUrlStore;
        this.templateRenderer = templateRenderer;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        String baseUrl = baseUrlStore.get();
        Logger.println("doGet, BaseUrl: " + baseUrl);

        templateRenderer.render("/templates/flockSettings.vm",
                TextUtils.isEmpty(baseUrl) ? ImmutableMap.of() : ImmutableMap.of("baseUrl", baseUrl),
                response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String baseUrl = request.getParameter("baseUrl");
        Logger.println("doPost, BaseUrl: " + baseUrl);

        if (!isNullOrEmpty(baseUrl) && Utils.isValidUrl(baseUrl)) {
            storeLoginRecord(baseUrl);
        }

        response.sendRedirect("./flock-settings");
    }

    private void storeLoginRecord(String baseUrl) {
        baseUrlStore.put(baseUrl);
    }
}

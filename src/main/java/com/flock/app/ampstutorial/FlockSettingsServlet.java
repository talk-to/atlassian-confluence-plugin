package com.flock.app.ampstutorial;

import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.imports.ConfluenceImport;
import com.atlassian.templaterenderer.TemplateRenderer;
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

    private final BaseUrlStore baseURlStore;
    private final TemplateRenderer templateRenderer;

    @Inject
    public FlockSettingsServlet(
            BaseUrlStore baseUrlStore,
            @ConfluenceImport TemplateRenderer templateRenderer) {
        this.baseURlStore = baseUrlStore;
        this.templateRenderer = templateRenderer;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("decorator", "confluence.userprofile.tab");
        response.setContentType("text/html");

        String baseUrl = baseURlStore.get();

        templateRenderer.render("/templates/flockSettings.vm",
                TextUtils.isEmpty(baseUrl) ? ImmutableMap.of() : ImmutableMap.of("baseUrl", baseUrl),
                response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String baseUrl = request.getParameter("baseUrl");


        if (!isNullOrEmpty(baseUrl)) {
            storeLoginRecord(baseUrl);
        }
        response.sendRedirect("./flock-settings");
    }

    private void storeLoginRecord(String baseUrl) {
        baseURlStore.put(baseUrl);
    }
}

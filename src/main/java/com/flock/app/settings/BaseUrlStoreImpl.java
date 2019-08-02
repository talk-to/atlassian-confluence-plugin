package com.flock.app.settings;

import com.atlassian.confluence.setup.settings.SettingsManager;
import com.atlassian.plugin.spring.scanner.annotation.imports.ConfluenceImport;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.flock.app.Constants;
import com.flock.app.Logger;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class BaseUrlStoreImpl implements BaseUrlStore {

    private String baseUrl;
    private PluginSettingsFactory pluginSettingsFactory;
    private SettingsManager settingsManager;

    @Inject
    public BaseUrlStoreImpl(@ConfluenceImport PluginSettingsFactory pluginSettingsFactory, @ConfluenceImport SettingsManager settingsManager) {
        this.pluginSettingsFactory = pluginSettingsFactory;
        this.settingsManager = settingsManager;
        baseUrl = (String) pluginSettingsFactory.createGlobalSettings().get(Constants.KEY_WEB_HOOK_URL);
        Logger.println("BaseUrlStoreImpl Created, BaseUrl: " + baseUrl);
    }

    @Override
    public String get() {
        return baseUrl;
    }

    @Override
    public String getConfluenceBaseUrl() {
        return settingsManager.getGlobalSettings().getBaseUrl();
    }

    @Override
    public void put(String baseUrl) {
        this.baseUrl = baseUrl;
        pluginSettingsFactory.createGlobalSettings().put(Constants.KEY_WEB_HOOK_URL, baseUrl);
        Logger.println("BaseUrl Updated: " + baseUrl);
    }
}

package com.flock.app.ampstutorial;

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

    @Inject
    public BaseUrlStoreImpl(@ConfluenceImport PluginSettingsFactory pluginSettingsFactory) {
        this.pluginSettingsFactory = pluginSettingsFactory;
        baseUrl = (String) pluginSettingsFactory.createGlobalSettings().get(Constants.KEY_WEEBHOOK_URL);
        Logger.println("BaseUrlStoreImpl Created, BaseUrl: " + baseUrl);
    }

    @Override
    public String get() {
        return baseUrl;
    }

    @Override
    public void put(String baseUrl) {
        this.baseUrl = baseUrl;
        pluginSettingsFactory.createGlobalSettings().put(Constants.KEY_WEEBHOOK_URL, baseUrl);
        Logger.println("BaseUrl Updated: " + baseUrl);
    }
}

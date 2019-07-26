package com.flock.app.pluginConfig;

import com.atlassian.plugin.spring.scanner.annotation.imports.ConfluenceImport;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.flock.app.Constants;
import com.flock.app.Logger;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class PluginConfigImpl implements PluginConfig {

    private String url;

    @Inject
    public PluginConfigImpl(@ConfluenceImport PluginSettingsFactory pluginSettingsFactory) {
        pluginSettingsFactory.createGlobalSettings().put(Constants.KEY_WEEBHOOK_URL, "http://228a7f86.ngrok.io/manage/webhook/events");
        url = (String) pluginSettingsFactory.createGlobalSettings().get(Constants.KEY_WEEBHOOK_URL);
        Logger.println("PluginConfigImpl Created, Current Webhook: " + url);
    }

    @Override
    public String getWebhookUrl() throws NullPointerException {
        return url;
    }

    @Override
    public void setWebhookUrl(String url) {
        this.url = url;
    }
}

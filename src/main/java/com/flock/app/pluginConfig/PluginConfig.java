package com.flock.app.pluginConfig;

public interface PluginConfig {
    void setWebhookUrl(String url);

    String getWebhookUrl() throws NullPointerException;
}

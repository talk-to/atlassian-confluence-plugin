package com.flock.app.network;

import com.atlassian.plugin.spring.scanner.annotation.export.ExportAsService;
import com.flock.app.ConfluenceActionType;
import com.flock.app.Logger;
import com.flock.app.pluginConfig.PluginConfig;
import com.goebl.david.Webb;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Named;

@ExportAsService({MyNetworkClient.class})
@Named
public class MyNetworkClientImpl implements MyNetworkClient {
    private static final String HEADER_ACTION_TYPE = "confluence-action-type";
    private final Webb webb;
    private final Gson gson;
    private final PluginConfig pluginConfig;

    @Inject
    public MyNetworkClientImpl(GsonProvider gsonProvider, PluginConfig pluginConfig) {
        webb = Webb.create();
        this.gson = gsonProvider.getGson();
        this.pluginConfig = pluginConfig;
        Logger.println("MyNetworkClientImpl Created");
    }

    @Override
    public void sendEventToServer(ConfluenceActionType confluenceActionType, Object toSend) {
        try {
            String body = gson.toJson(toSend);
            Logger.println("ConfluenceActionType: " + confluenceActionType);
            Logger.println("Serialized Object: " + body);
            webb.post(pluginConfig.getWebhookUrl())
                    .body(body)
                    .header(HEADER_ACTION_TYPE, confluenceActionType.getNameString())
                    .ensureSuccess()
                    .asVoid();
        } catch (NullPointerException ignored) {
        }
    }
}
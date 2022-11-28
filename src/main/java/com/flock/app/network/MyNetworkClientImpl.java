package com.flock.app.network;

import com.atlassian.plugin.spring.scanner.annotation.export.ExportAsService;
import com.flock.app.ConfluenceActionType;
import com.flock.app.Constants;
import com.flock.app.Logger;
import com.flock.app.settings.BaseUrlStore;
import com.goebl.david.Webb;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import javax.inject.Inject;
import javax.inject.Named;

import static com.flock.app.Constants.CONFLUENCE_VERSION;
import static com.flock.app.Constants.PLUGIN_VERSION;

@ExportAsService({MyNetworkClient.class})
@Named
public class MyNetworkClientImpl implements MyNetworkClient {
    private static final String HEADER_ACTION_TYPE = "confluence-action-type";
    private static final String HEADER_CONFLUENCE_BASE_URL = "confluence-base-url";
    private final Webb webb;
    private final Gson gson;
    private final BaseUrlStore baseUrlStore;

    @Inject
    public MyNetworkClientImpl(GsonProvider gsonProvider, BaseUrlStore baseUrlStore) {
        webb = Webb.create();
        this.gson = gsonProvider.getGson();
        this.baseUrlStore = baseUrlStore;
        Logger.println("MyNetworkClientImpl Created");
    }

    @Override
    public void sendEventToServer(ConfluenceActionType confluenceActionType, Object toSend) {
        if (baseUrlStore.get() == null) return;

        try {
            String body = gson.toJson(decorateWithGlobalProps(toSend));
            webb.post(baseUrlStore.get())
                    .header(HEADER_CONFLUENCE_BASE_URL, baseUrlStore.getConfluenceBaseUrl())
                    .header(HEADER_ACTION_TYPE, confluenceActionType.getNameString())
                    .body(body)
                    .asVoid();
            Logger.println("ConfluenceActionType: " + confluenceActionType);
            Logger.println("Confluence Base Url: " + baseUrlStore.getConfluenceBaseUrl());
            Logger.println("Serialized Object: " + body);
        } catch (Exception ignored) {
        }
    }

    private JsonElement decorateWithGlobalProps(Object object) {
        JsonElement jsonElement = gson.toJsonTree(object);
        jsonElement.getAsJsonObject().addProperty("confluenceVersion", CONFLUENCE_VERSION);
        jsonElement.getAsJsonObject().addProperty("pluginVersion", PLUGIN_VERSION);
        return jsonElement;
    }
}
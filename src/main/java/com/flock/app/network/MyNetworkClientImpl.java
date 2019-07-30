package com.flock.app.network;

import com.atlassian.plugin.spring.scanner.annotation.export.ExportAsService;
import com.flock.app.ConfluenceActionType;
import com.flock.app.Logger;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Named;

@ExportAsService({MyNetworkClient.class})
@Named
public class MyNetworkClientImpl implements MyNetworkClient {
    private final Gson gson;
    private final OkhttpProvider okhttpProvider;

    @Inject
    public MyNetworkClientImpl(GsonProvider gsonProvider, OkhttpProvider okhttpProvider) {
        this.gson = gsonProvider.getGson();
        this.okhttpProvider = okhttpProvider;
        Logger.println("MyNetworkClientImpl Created");
    }

    @Override
    public void sendEventToServer(ConfluenceActionType confluenceActionType, Object toSend) {
        try {
            String body = gson.toJson(toSend);
            okhttpProvider.postEvent(confluenceActionType.getNameString(), body);
            Logger.println("ConfluenceActionType: " + confluenceActionType);
            Logger.println("Serialized Object: " + body);
        } catch (Exception ignored) {
        }
    }
}
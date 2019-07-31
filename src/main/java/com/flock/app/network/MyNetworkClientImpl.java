package com.flock.app.network;

import com.atlassian.plugin.spring.scanner.annotation.export.ExportAsService;
import com.flock.app.ConfluenceActionType;
import com.flock.app.Logger;
import com.flock.app.settings.BaseUrlStore;
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
            String body = gson.toJson(toSend);
            webb.post(baseUrlStore.get())
                    .header(HEADER_ACTION_TYPE, confluenceActionType.getNameString())
                    .body(body)
                    .asVoid();
            Logger.println("ConfluenceActionType: " + confluenceActionType);
            Logger.println("Serialized Object: " + body);
        } catch (Exception ignored) {
        }
    }
}
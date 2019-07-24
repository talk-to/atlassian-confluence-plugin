package com.flock.app.network;

import com.atlassian.plugin.spring.scanner.annotation.export.ExportAsService;
import com.flock.app.Logger;
import com.goebl.david.Webb;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Named;

@ExportAsService({MyNetworkClient.class})
@Named
public class MyNetworkClientImpl implements MyNetworkClient {
    private final String baseUrl;
    private final Webb webb;
    private final Gson gson;

    public MyNetworkClientImpl() {
        this.baseUrl = "https://webhook.site/4070acb6-3a53-4ff5-ac14-e105a55bfd19";
        webb = Webb.create();
        gson = new GsonBuilder().create();
        Logger.println("MyNetworkClientImpl Created");
    }

    @Override
    public void makeEmptyPost(Object toSend) {
        Logger.println("MyNetworkClientImpl: makeEmptyPost");
        makeHttpRequest(toSend);
    }

    private void makeHttpRequest(Object toSend) {
        webb.post(baseUrl)
                .param("param1", "a")
                .param("param2", "b")
                .param("param3", "c")
                .ensureSuccess()
                .asVoid();
    }
}
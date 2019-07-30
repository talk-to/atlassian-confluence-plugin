package com.flock.app.network;

import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.export.ExportAsService;
import com.flock.app.settings.BaseUrlStore;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import javax.inject.Inject;
import javax.inject.Named;

@ExportAsService({OkhttpProvider.class})
@Named("okhttpProviderImpl")
@Scanned
public class OkhttpProviderImpl implements OkhttpProvider {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final String HEADER_ACTION_TYPE = "confluence-action-type";
    private final OkHttpClient client;
    private final BaseUrlStore baseUrlStore;

    @Inject
    public OkhttpProviderImpl(BaseUrlStore baseUrlStore) {
        this.client = new OkHttpClient();
        this.baseUrlStore = baseUrlStore;
    }

    @Override
    public void postEvent(String headerActionType, String body) {
        RequestBody formBody = RequestBody.create(JSON, body);
        Request request = new Request
                .Builder()
                .url(baseUrlStore.get())
                .header(HEADER_ACTION_TYPE, headerActionType)
                .post(formBody)
                .build();
        client.newCall(request).enqueue(null);
    }
}

package com.flock.app.network;

import com.flock.app.ConfluenceActionType;

public interface MyNetworkClient {
    void sendEventToServer(ConfluenceActionType confluenceActionType, Object toSend);
}

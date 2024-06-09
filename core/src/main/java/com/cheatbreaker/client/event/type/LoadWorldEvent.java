package com.cheatbreaker.client.event.type;

import com.cheatbreaker.bridge.client.multiplayer.WorldClientBridge;
import com.cheatbreaker.client.event.EventBus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoadWorldEvent extends EventBus.Event {
    private final WorldClientBridge world;
}

package com.cheatbreaker.client.event.type;

import com.cheatbreaker.client.event.EventBus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PluginMessageEvent extends EventBus.Event {
    private final String channel;
    private final byte[] payload;
}

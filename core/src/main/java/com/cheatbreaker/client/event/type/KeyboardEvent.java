package com.cheatbreaker.client.event.type;

import com.cheatbreaker.client.event.EventBus;
import com.cheatbreaker.client.event.data.KeyObject;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class KeyboardEvent extends EventBus.Event {
    private final KeyObject keyboardKey;

    public KeyboardEvent(int keyId) {
        this(KeyObject.getKey(keyId));
    }
}

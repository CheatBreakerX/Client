package com.cheatbreaker.bridge.client.gui;

import com.cheatbreaker.bridge.util.IChatComponentBridge;

public interface GuiNewChatBridge {
    boolean bridge$getChatOpen();
    void bridge$printChatMessage(IChatComponentBridge component);
}

package com.cheatbreaker.bridge.client.settings;

public interface GameSettingsBridge {
    boolean bridge$getShowDebugInfo();
    int bridge$getGuiScale();
    KeyBindingBridge bridge$getKeyBindInventory();
    String bridge$getKeyDisplayString(int keyCode);
    KeyBindingBridge[] bridge$getKeyBindings();
    void bridge$setKeyBindings(KeyBindingBridge[] keyBindings);
    int bridge$getThirdPersonView();
}

package com.cheatbreaker.bridge.client.settings;

public interface GameSettingsBridge {
    boolean bridge$getShowDebugInfo();
    int bridge$getGuiScale();
    KeyBindingBridge bridge$getKeyBindInventory();
    String bridge$getKeyDisplayString(int keyCode);
    KeyBindingBridge[] bridge$getKeyBindings();
    void bridge$setKeyBindings(KeyBindingBridge[] keyBindings);
    int bridge$getThirdPersonView();
    int bridge$getRenderDistanceChunks();
    KeyBindingBridge bridge$getKeyBindJump();
    KeyBindingBridge bridge$getKeyBindForward();
    KeyBindingBridge bridge$getKeyBindLeft();
    KeyBindingBridge bridge$getKeyBindRight();
    KeyBindingBridge bridge$getKeyBindBack();
    KeyBindingBridge bridge$getKeyBindAttack();
    KeyBindingBridge bridge$getKeyBindUseItem();
}

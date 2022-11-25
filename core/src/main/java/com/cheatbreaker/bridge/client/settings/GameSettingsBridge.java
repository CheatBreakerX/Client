package com.cheatbreaker.bridge.client.settings;

import com.cheatbreaker.bridge.client.audio.SoundCategoryBridge;

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

    float bridge$getSoundLevel(SoundCategoryBridge category);
    void bridge$setSoundLevel(SoundCategoryBridge category, float level);
}

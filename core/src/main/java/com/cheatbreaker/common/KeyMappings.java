package com.cheatbreaker.common;

import com.cheatbreaker.bridge.client.settings.KeyBindingBridge;
import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.common.generic.Visitor;

import java.util.ArrayList;
import java.util.List;

public class KeyMappings {
    private static final List<KeyBindingBridge> MAPPINGS = new ArrayList<>();

    public static KeyBindingBridge OPEN_MENU;
    public static KeyBindingBridge OPEN_VOICE_MENU;
    public static KeyBindingBridge PUSH_TO_TALK;
    public static KeyBindingBridge DRAG_LOOK;
    public static KeyBindingBridge HIDE_NAMES;

    public static void initialize() {
        MAPPINGS.add(OPEN_MENU = Ref.getInstanceCreator()
                .createKeyBinding("Open Menu", 54, "CheatBreaker Client"));
        MAPPINGS.add(OPEN_VOICE_MENU = Ref.getInstanceCreator()
                .createKeyBinding("Open Voice Menu", 25, "CheatBreaker Client"));
        MAPPINGS.add(PUSH_TO_TALK = Ref.getInstanceCreator()
                .createKeyBinding("Voice Chat", 47, "CheatBreaker Client"));
        MAPPINGS.add(DRAG_LOOK = Ref.getInstanceCreator()
                .createKeyBinding("Drag to look", 56, "CheatBreaker Client"));
        MAPPINGS.add(HIDE_NAMES = Ref.getInstanceCreator()
                .createKeyBinding("Hide name plates", 0, "CheatBreaker Client"));
    }

    public static void register(Visitor<KeyBindingBridge> visitor) {
        for (KeyBindingBridge mapping : MAPPINGS) {
            visitor.accept(mapping);
        }
    }
}

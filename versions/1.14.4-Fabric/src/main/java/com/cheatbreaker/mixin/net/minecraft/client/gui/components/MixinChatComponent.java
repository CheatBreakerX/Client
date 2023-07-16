package com.cheatbreaker.mixin.net.minecraft.client.gui.components;

import com.cheatbreaker.bridge.client.gui.GuiNewChatBridge;
import com.cheatbreaker.bridge.util.IChatComponentBridge;
import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ChatComponent.class)
public abstract class MixinChatComponent implements GuiNewChatBridge {
    @Shadow public abstract boolean isChatFocused();

    @Shadow public abstract void addMessage(Component arg);

    public boolean bridge$getChatOpen() {
        return this.isChatFocused();
    }

    public void bridge$printChatMessage(IChatComponentBridge component) {
        this.addMessage((Component) component);
    }
}

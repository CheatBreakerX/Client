package com.cheatbreaker.mixin.client.gui;

import com.cheatbreaker.bridge.client.gui.GuiNewChatBridge;
import com.cheatbreaker.bridge.util.IChatComponentBridge;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.util.text.ITextComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(GuiNewChat.class)
public abstract class MixinGuiNewChat implements GuiNewChatBridge {
    @Shadow public abstract boolean getChatOpen();
    @Shadow public abstract void printChatMessage(ITextComponent chatComponent);

    public boolean bridge$getChatOpen() {
        return this.getChatOpen();
    }

    public void bridge$printChatMessage(IChatComponentBridge component) {
        this.printChatMessage((ITextComponent) component);
    }
}

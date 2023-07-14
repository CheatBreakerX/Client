package com.cheatbreaker.mixin.client.gui;

import com.cheatbreaker.bridge.client.gui.GuiIngameBridge;
import com.cheatbreaker.bridge.client.gui.GuiNewChatBridge;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.world.scores.Objective;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Gui.class)
public abstract class MixinGui implements GuiIngameBridge {
    @Shadow public abstract ChatComponent getChat();

    public GuiNewChatBridge bridge$getChatGUI() {
        return (GuiNewChatBridge) this.getChat();
    }

    /**
     * @author iAmSpace
     * @reason {@link com.cheatbreaker.client.module.type.ScoreboardModule}
     */
    @Overwrite
    protected void displayScoreboardSidebar(Objective objective) {

    }
}

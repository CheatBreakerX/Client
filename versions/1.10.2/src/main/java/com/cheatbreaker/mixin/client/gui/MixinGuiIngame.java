package com.cheatbreaker.mixin.client.gui;

import com.cheatbreaker.bridge.client.gui.GuiIngameBridge;
import com.cheatbreaker.bridge.client.gui.GuiNewChatBridge;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.scoreboard.ScoreObjective;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(GuiIngame.class)
public abstract class MixinGuiIngame implements GuiIngameBridge {
    @Shadow public abstract GuiNewChat getChatGUI();

    public GuiNewChatBridge bridge$getChatGUI() {
        return (GuiNewChatBridge) this.getChatGUI();
    }

    /**
     * @author iAmSpace
     * @reason Because {@link com.cheatbreaker.client.module.type.ScoreboardModule}
     */
    @Overwrite
    protected void renderScoreboard(ScoreObjective objective, ScaledResolution scaledRes) {

    }
}

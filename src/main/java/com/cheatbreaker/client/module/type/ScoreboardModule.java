package com.cheatbreaker.client.module.type;

import com.cheatbreaker.bridge.client.gui.FontRendererBridge;
import com.cheatbreaker.bridge.util.EnumChatFormattingBridge;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.event.type.GuiDrawEvent;
import com.cheatbreaker.client.event.type.RenderPreviewEvent;
import com.cheatbreaker.client.module.AbstractModule;
import com.cheatbreaker.client.module.ModuleRule;
import com.cheatbreaker.client.ui.module.CBGuiAnchor;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.scoreboard.*;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.opengl.GL11;

import java.util.Collection;
import java.util.Iterator;

public class ScoreboardModule extends AbstractModule {
    public static ModuleRule rule = ModuleRule.SCOREBOARD;
    public Setting removeNumbers;

    public ScoreboardModule() {
        super("Scoreboard");
        this.setDefaultAnchor(CBGuiAnchor.RIGHT_MIDDLE);
        this.removeNumbers = new Setting(this, "Remove Scoreboard numbers").setValue(true);
        this.addEvent(GuiDrawEvent.class, this::renderReal);
        this.addEvent(RenderPreviewEvent.class, this::renderPreview);
        this.setDefaultState(true);
    }

    private void renderPreview(RenderPreviewEvent renderPreviewEvent) {
        if (!this.isRenderHud()) {
            return;
        }
        if (this.minecraft.bridge$getTheWorld().getScoreboard().func_96539_a(1) != null) {
            return;
        }
        GL11.glPushMatrix();
        this.scaleAndTranslate(renderPreviewEvent.getResolution());
        GL11.glTranslatef(this.isRemoveNumbers() ? (float) -12 : 2.0f, this.height, 0.0f);
        Scoreboard scoreboard = new Scoreboard();
        ScoreObjective objective = new ScoreObjective(scoreboard, "CheatBreaker", IScoreObjectiveCriteria.field_96641_b);
        objective.setDisplayName(EnumChatFormatting.RED + "" + EnumChatFormatting.BOLD + "Cheat" + EnumChatFormatting.RESET + "" + EnumChatFormatting.WHITE + "Breaker");
        scoreboard.func_96529_a("Steve", objective);
        scoreboard.func_96529_a("Alex", objective);
        this.drawObjective(objective, this.minecraft.bridge$getFontRenderer());
        GL11.glPopMatrix();
    }

    private void renderReal(GuiDrawEvent guiDrawEvent) {
        if (!this.isRenderHud()) {
            return;
        }
        GL11.glPushMatrix();
        this.scaleAndTranslate(guiDrawEvent.getResolution());
        GL11.glTranslatef(this.isRemoveNumbers() ? (float) -12 : 2.0f, this.height, 0.0f);
        ScoreObjective objective = this.minecraft.bridge$getTheWorld().getScoreboard().func_96539_a(1);
        if (objective != null) {
            this.drawObjective(objective, this.minecraft.bridge$getFontRenderer());
        }
        GL11.glPopMatrix();
    }

    private void drawObjective(ScoreObjective objective, FontRendererBridge fontRenderer) {
        Scoreboard scoreboard = objective.bridge$getScoreboard();
        Collection<Score> collection = scoreboard.bridge$func_96534_i(objective);
        boolean removeNumbers = isRemoveNumbers();
        if (collection.size() <= 15) {
            int width = fontRenderer.bridge$getStringWidth(objective.bridge$getDisplayName());
            int numbersX = width + 16;
            for (Score score : collection) {
                ScorePlayerTeam playersTeam = scoreboard.bridge$getPlayersTeam(score.bridge$getPlayerName());
                String string = ScorePlayerTeam.formatPlayerName(playersTeam, score.bridge$getPlayerName()) + ": " + EnumChatFormattingBridge.RED + score.bridge$getScorePoints();
                width = Math.max(width, fontRenderer.bridge$getStringWidth(string));
            }
            int n8 = 0;
            Iterator<Score> iterator = collection.iterator();
            int n9 = 0;
            while (iterator.hasNext()) {
                Score score = iterator.next();
                Team team = scoreboard.bridge$getPlayersTeam(score.bridge$getPlayerName());
                String string = ScorePlayerTeam.formatPlayerName(team, score.bridge$getPlayerName());
                String string2 = EnumChatFormattingBridge.RED + "" + score.bridge$getScorePoints();
                int lineY = -++n8 * 9;
                int lineX = width + 9;
                if (lineX < numbersX) {
                    lineX = numbersX;
                }
                Gui.drawRect(-2 + (removeNumbers ? 14 : 0), lineY, lineX, lineY + 9, 0x50000000);
                n9 = lineX - (-2 + (removeNumbers ? 14 : 0));
                fontRenderer.bridge$drawString(string, (removeNumbers ? 16 : 0), lineY, 0x20FFFFFF);
                if (!removeNumbers) {
                    fontRenderer.bridge$drawString(string2, lineX - fontRenderer.bridge$getStringWidth(string2) - 2, lineY, 0x20FFFFFF);
                }
                if (n8 != collection.size()) continue;
                String string3 = objective.bridge$getDisplayName();
                Gui.drawRect(-2 + (removeNumbers ? 14 : 0), lineY - 9 - 1, lineX, lineY - 1, 0x60000000);
                Gui.drawRect(-2 + (removeNumbers ? 14 : 0), lineY - 1, lineX, lineY, 0x50000000);
                fontRenderer.bridge$drawString(string3, +width / 2 - fontRenderer.bridge$getStringWidth(string3) / 2 + (removeNumbers ? 14 : 0), lineY - 9, 0x20FFFFFF);
            }
            this.setDimensions(n9, collection.size() * 9 + 12);
        }
    }

    private boolean isRemoveNumbers() {
        return rule == ModuleRule.SCOREBOARD ? (Boolean) this.removeNumbers.getValue() : false;
    }
}

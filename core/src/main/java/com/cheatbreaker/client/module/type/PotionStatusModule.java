package com.cheatbreaker.client.module.type;

import com.cheatbreaker.bridge.potion.PotionBridge;
import com.cheatbreaker.bridge.potion.PotionEffectBridge;
import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.bridge.util.ResourceLocationBridge;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.event.type.GuiDrawEvent;
import com.cheatbreaker.client.event.type.RenderPreviewEvent;
import com.cheatbreaker.client.event.type.TickEvent;
import com.cheatbreaker.client.module.AbstractModule;
import com.cheatbreaker.client.ui.module.CBGuiAnchor;
import com.cheatbreaker.client.ui.module.CBPositionEnum;
import com.cheatbreaker.client.ui.util.RenderUtil;
import org.lwjgl.opengl.GL11;

import java.util.Collection;
import java.util.HashMap;

public class PotionStatusModule extends AbstractModule {
    private final Setting showWhileTying;
    private final Setting showEffectName;
    private final Setting colorOptionsLabel;
    private final Setting nameColor;
    private final Setting durationColor;
    private final Setting blink;
    private final Setting blinkDuration;
    private final ResourceLocationBridge location = Ref.getInstanceCreator().createResourceLocationBridge("textures/gui/container/inventory.png");
    private int ticks = 0;

    public PotionStatusModule() {
        super("Potion Effects");
        this.setDefaultState(false);
        this.setDefaultAnchor(CBGuiAnchor.LEFT_MIDDLE);

        new Setting(this, "label").setValue("General Options");
        {
            this.showWhileTying = new Setting(this, "Show While Typing").setValue(true);
            this.showEffectName = new Setting(this, "Effect Name").setValue(true);
            //this.showInInventory = new Setting(this, "Show Potion info in inventory").setValue(false);
            // commented out due to there being two of the same option.
        }
        new Setting(this, "label").setValue("Blink Options");
        {
            this.blink = new Setting(this, "Blink").setValue(true);
            this.blinkDuration = new Setting(this, "Blink Duration").setValue(10).setMinMax(2, 20);
            this.colorOptionsLabel = new Setting(this, "label").setValue("Color Options");
            this.nameColor = new Setting(this, "Name Color").setValue(-1).setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE);
            this.durationColor = new Setting(this, "Duration Color").setValue(-1).setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        this.setPreviewIcon(Ref.getInstanceCreator().createResourceLocationBridge("client/icons/mods/speed_icon.png"), 28, 28);

        this.addEvent(TickEvent.class, this::onTick);
        this.addEvent(RenderPreviewEvent.class, this::renderPreview);
        this.addEvent(GuiDrawEvent.class, this::renderReal);
    }

    private void onTick(TickEvent cBTickEvent) {
        ++this.ticks;
    }

    private void renderReal(GuiDrawEvent guiDrawEvent) {
        GL11.glPushMatrix();
        if ((Boolean) this.showWhileTying.getValue() || !this.minecraft.bridge$getIngameGUI().bridge$getChatGUI().bridge$getChatOpen()) {
            GL11.glPushMatrix();
            this.scaleAndTranslate(guiDrawEvent.getResolution());
            CBPositionEnum position = this.getPosition();
            Collection<PotionEffectBridge> collection = this.minecraft.bridge$getThePlayer().bridge$getActivePotionEffects();
            if (collection.isEmpty()) {
                GL11.glPopMatrix();
                GL11.glPopMatrix();
                return;
            }
            int n = 0;
            int n2 = 0;
            int n3 = 22;
            for (PotionEffectBridge potionEffect : collection) {
                PotionBridge potion;
                String string;
                boolean shouldBlink = this.shouldBlink(potionEffect.bridge$getDuration());
                int n4 = 0;
                if ((Boolean) this.showEffectName.getValue()) {
                    string = Ref.getI18n().bridge$format(potionEffect.bridge$getEffectName()) + this.getLevelName(potionEffect.bridge$getAmplifier());
                    n4 = this.minecraft.bridge$getFontRenderer().bridge$getStringWidth(string) + 20;
                    if (position == CBPositionEnum.RIGHT) {
                        this.minecraft.bridge$getFontRenderer().bridge$drawStringWithShadow(string + "\u00a7r", (int) width - n4, n, this.nameColor.getColorValue());
                    } else if (position == CBPositionEnum.LEFT) {
                        this.minecraft.bridge$getFontRenderer().bridge$drawStringWithShadow(string + "\u00a7r", 20, n, this.nameColor.getColorValue());
                    } else if (position == CBPositionEnum.CENTER) {
                        this.minecraft.bridge$getFontRenderer().bridge$drawStringWithShadow(string + "\u00a7r", (int) width / 2 - (n4 / 2) + 20, n, this.nameColor.getColorValue());
                    }
                    if (n4 > n2) {
                        n2 = n4;
                    }
                }
                string = potionEffect.bridge$getDurationBridge();
                int n5 = this.minecraft.bridge$getFontRenderer().bridge$getStringWidth(string) + 20;
                if (shouldBlink) {
                    if (position == CBPositionEnum.RIGHT) {
                        this.minecraft.bridge$getFontRenderer().bridge$drawStringWithShadow(string + "\u00a7r", (int) width - n5, n + ((Boolean) this.showEffectName.getValue() ? 10 : 5), this.durationColor.getColorValue());
                    } else if (position == CBPositionEnum.LEFT) {
                        this.minecraft.bridge$getFontRenderer().bridge$drawStringWithShadow(string + "\u00a7r", 20, n + ((Boolean) this.showEffectName.getValue() ? 10 : 5), this.durationColor.getColorValue());
                    } else if (position == CBPositionEnum.CENTER) {
                        this.minecraft.bridge$getFontRenderer().bridge$drawStringWithShadow(string + "\u00a7r", (int) width / 2 - (n5 / 2) + 20, n + ((Boolean) this.showEffectName.getValue() ? 10 : 5), this.durationColor.getColorValue());
                    }
                }
                if ((potion = potionEffect.bridge$toPotionType()).bridge$hasStatusIcon()) {
                    GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
                    this.minecraft.bridge$getTextureManager().bridge$bindTexture(this.location);
                    int n6 = potion.bridge$getStatusIconIndex();
                    if (position == CBPositionEnum.RIGHT) {
                        RenderUtil.drawTexturedModalRect(width - (float) 20, (float) n, (float) (n6 % 8 * 18), (float) (198 + n6 / 8 * 18), 18, 18);
                    } else if (position == CBPositionEnum.LEFT) {
                        RenderUtil.drawTexturedModalRect(0.0f, (float) n, (float) (n6 % 8 * 18), (float) (198 + n6 / 8 * 18), 18, 18);
                    } else if (position == CBPositionEnum.CENTER) {
                        RenderUtil.drawTexturedModalRect(width / 2.0f - (float) (n4 / 2), (float) n, (float) (n6 % 8 * 18), (float) (198 + n6 / 8 * 18), 18, 18);
                    }
                }
                if (n5 > n2) {
                    n2 = n5;
                }
                n += n3;
            }
            this.setDimensions(n2, n);
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
    }

    private void renderPreview(RenderPreviewEvent renderPreviewEvent) {
        if (!this.isRenderHud()) {
            return;
        }
        GL11.glPushMatrix();
        Collection<PotionEffectBridge> collection = this.minecraft.bridge$getThePlayer().bridge$getActivePotionEffects();
        if (collection.isEmpty()) {
            GL11.glPushMatrix();
            this.scaleAndTranslate(renderPreviewEvent.getResolution());
            HashMap<Integer, PotionEffectBridge> hashMap = new HashMap<>();
            PotionEffectBridge fireResistance = Ref.getInstanceCreator().createPotionEffect("FIRE_RESISTANCE", 1200, 3);
            PotionEffectBridge speed = Ref.getInstanceCreator().createPotionEffect("MOVE_SPEED", 30, 3);
            hashMap.put(fireResistance.bridge$getPotionID(), fireResistance);
            hashMap.put(speed.bridge$getPotionID(), speed);
            collection = hashMap.values();
            CBPositionEnum position = this.getPosition();
            int n = 0;
            int n2 = 0;
            int n3 = 22;
            for (PotionEffectBridge potionEffect : collection) {
                PotionBridge potion;
                String string;
                boolean shouldBlink = this.shouldBlink(potionEffect.bridge$getDuration());
                int n4 = 0;
                if ((Boolean) this.showEffectName.getValue()) {
                    string = Ref.getI18n().bridge$format(potionEffect.bridge$getEffectName()) + this.getLevelName(potionEffect.bridge$getAmplifier());
                    n4 = this.minecraft.bridge$getFontRenderer().bridge$getStringWidth(string) + 20;
                    if (position == CBPositionEnum.RIGHT) {
                        this.minecraft.bridge$getFontRenderer().bridge$drawStringWithShadow(string + "\u00a7r", (int) width - n4, n, this.nameColor.getColorValue());
                    } else if (position == CBPositionEnum.LEFT) {
                        this.minecraft.bridge$getFontRenderer().bridge$drawStringWithShadow(string + "\u00a7r", 20, n, this.nameColor.getColorValue());
                    } else if (position == CBPositionEnum.CENTER) {
                        this.minecraft.bridge$getFontRenderer().bridge$drawStringWithShadow(string + "\u00a7r", (int) width / 2 - (n4 / 2) + 20, n, this.nameColor.getColorValue());
                    }
                    if (n4 > n2) {
                        n2 = n4;
                    }
                }
                string = potionEffect.bridge$getDurationString();
                int n5 = this.minecraft.bridge$getFontRenderer().bridge$getStringWidth(string) + 20;
                if (shouldBlink) {
                    if (position == CBPositionEnum.RIGHT) {
                        this.minecraft.bridge$getFontRenderer().bridge$drawStringWithShadow(string + "\u00a7r", (int) width - n5, n + ((Boolean) this.showEffectName.getValue() ? 10 : 5), this.durationColor.getColorValue());
                    } else if (position == CBPositionEnum.LEFT) {
                        this.minecraft.bridge$getFontRenderer().bridge$drawStringWithShadow(string + "\u00a7r", 20, n + ((Boolean) this.showEffectName.getValue() ? 10 : 5), this.durationColor.getColorValue());
                    } else if (position == CBPositionEnum.CENTER) {
                        this.minecraft.bridge$getFontRenderer().bridge$drawStringWithShadow(string + "\u00a7r", (int) width / 2 - (n5 / 2) + 20, n + ((Boolean) this.showEffectName.getValue() ? 10 : 5), this.durationColor.getColorValue());
                    }
                }
                if ((potion = potionEffect.bridge$toPotionType()).bridge$hasStatusIcon()) {
                    GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
                    this.minecraft.bridge$getTextureManager().bridge$bindTexture(this.location);
                    int n6 = potion.bridge$getStatusIconIndex();
                    if (position == CBPositionEnum.RIGHT) {
                        RenderUtil.drawTexturedModalRect(width - (float) 20, (float) n, (float) (n6 % 8 * 18), (float) (198 + n6 / 8 * 18), 18, 18);
                    } else if (position == CBPositionEnum.LEFT) {
                        RenderUtil.drawTexturedModalRect(0.0f, (float) n, (float) (n6 % 8 * 18), (float) (198 + n6 / 8 * 18), 18, 18);
                    } else if (position == CBPositionEnum.CENTER) {
                        RenderUtil.drawTexturedModalRect(width / 2.0f - (float) (n4 / 2), (float) n, (float) (n6 % 8 * 18), (float) (198 + n6 / 8 * 18), 18, 18);
                    }
                }
                if (n5 > n2) {
                    n2 = n5;
                }
                n += n3;
            }
            this.setDimensions(n2, n);
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
    }

    private boolean shouldBlink(float f) {
        if ((Boolean) this.blink.getValue() && f <= (float) ((Integer) this.blinkDuration.getValue() * 22)) {
            if (this.ticks > 20) {
                this.ticks = 0;
            }
            return this.ticks <= 10;
        }
        return true;
    }

    private String getLevelName(int level) {
        switch (level) {
            case 1: {
                return " II";
            }
            case 2: {
                return " III";
            }
            case 3: {
                return " IV";
            }
            case 4: {
                return " V";
            }
            case 5: {
                return " VI";
            }
            case 6: {
                return " VII";
            }
            case 7: {
                return " VIII";
            }
            case 8: {
                return " IX";
            }
            case 9: {
                return " X";
            }
        }
        if (level > 9) {
            return " " + level + 1;
        }
        return "";
    }

}

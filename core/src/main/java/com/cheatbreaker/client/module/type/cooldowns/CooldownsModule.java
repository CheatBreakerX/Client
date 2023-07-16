package com.cheatbreaker.client.module.type.cooldowns;

import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.main.CheatBreaker;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.event.type.GuiDrawEvent;
import com.cheatbreaker.client.event.type.RenderPreviewEvent;
import com.cheatbreaker.client.event.type.TickEvent;
import com.cheatbreaker.client.module.AbstractModule;
import com.cheatbreaker.client.ui.module.CBGuiAnchor;
import com.cheatbreaker.client.ui.module.CBModulePlaceGui;
import com.cheatbreaker.client.ui.module.CBModulesGui;

import java.util.ArrayList;
import java.util.List;

public class CooldownsModule extends AbstractModule {

    private static final List<CooldownRenderer> renderers = new ArrayList<>();
    public final Setting colorTheme;
    private final Setting listMode;
    private final Setting coloredColor;
    private final List<CooldownRenderer> previewRenderers = new ArrayList<>();

    public CooldownsModule() {
        super("Cooldowns");
        this.setDefaultAnchor(CBGuiAnchor.MIDDLE_TOP);
        this.setDefaultTranslations(0.0f, 5);
        this.colorTheme = new Setting(this, "Color Theme").setValue("Bright").acceptedValues("Bright", "Dark", "Colored");
        this.listMode = new Setting(this, "List Mode").setValue("horizontal").acceptedValues("vertical", "horizontal");
        this.coloredColor = new Setting(this, "Colored color").setValue(-1).setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE);
        this.addEvent(TickEvent.class, this::onTick);
        this.addEvent(RenderPreviewEvent.class, this::renderPreview);
        this.addEvent(GuiDrawEvent.class, this::renderReal);
        this.setDefaultState(true);
    }

    public static void lIIIIlIIllIIlIIlIIIlIIllI(String name, long duration, int itemId) {
        for (CooldownRenderer renderer : renderers) {
            if (!renderer.getName().equalsIgnoreCase(name) || renderer.getItemId() != itemId)
                continue;
            renderer.resetTime();
            renderer.setDuration(duration);
            return;
        }
        renderers.add(new CooldownRenderer(name, itemId, duration));
    }

    public void onTick(TickEvent cBTickEvent) {
        if (!renderers.isEmpty()) {
            renderers.removeIf(CooldownRenderer::isTimeOver);
        }
        if (!previewRenderers.isEmpty()) {
            previewRenderers.removeIf(CooldownRenderer::isTimeOver);
        }
    }

    public void renderPreview(GuiDrawEvent event) {
        if (!this.isRenderHud()) {
            return;
        }
        if (renderers.isEmpty()) {
            Ref.getGlBridge().bridge$pushMatrix();
            if (this.previewRenderers.isEmpty()) {
                this.previewRenderers.add(new CooldownRenderer("CombatTag", 283, 30000L));
                this.previewRenderers.add(new CooldownRenderer("EnderPearl", 368, 12000L));
            }
            this.renderInternal(event, previewRenderers);
            Ref.getGlBridge().bridge$popMatrix();
        }
    }

    public void renderReal(GuiDrawEvent event) {
        if (!this.isRenderHud()) {
            return;
        }
        Ref.getGlBridge().bridge$pushMatrix();
        if (renderers.size() > 0) {
            this.renderInternal(event, renderers);
        } else if (!(this.minecraft.bridge$getCurrentScreen() instanceof CBModulesGui)
                && !(this.minecraft.bridge$getCurrentScreen() instanceof CBModulePlaceGui)) {
            this.setDimensions(50, 24);
            this.scaleAndTranslate(event.getResolution());
        }
        Ref.getGlBridge().bridge$popMatrix();
    }

    private void renderInternal(GuiDrawEvent event, List<CooldownRenderer> renderers) {
        this.scaleAndTranslate(event.getResolution());
        float f = 1.0f / CheatBreaker.getInstance().getScaleFactor();
        Ref.getGlBridge().bridge$scale(f, f, f);
        boolean verticalRender = this.listMode.<String>value().equalsIgnoreCase("vertical");
        int n = 36;
        int n2 = 36;
        int n3 = verticalRender ? n : renderers.size() * n;
        int n4 = verticalRender ? renderers.size() * n2 : n2;
        this.setDimensions((int) ((float) n3 * f), (int) ((float) n4 * f));
        for (int i = 0; i < renderers.size(); ++i) {
            CooldownRenderer renderer = renderers.get(i);
            if (this.listMode.<String>value().equalsIgnoreCase("vertical")) {
                renderer.lIIIIlIIllIIlIIlIIIlIIllI(this.colorTheme, this.width / 2f - n / 2f, i * n2,
                        this.coloredColor.getColorValue());
                continue;
            }
            renderer.lIIIIlIIllIIlIIlIIIlIIllI(this.colorTheme, i * n, 0f, this.coloredColor.getColorValue());
        }
    }
}

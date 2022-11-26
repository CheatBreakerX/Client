package com.cheatbreaker.client.module.type.bossbar;

import com.cheatbreaker.bridge.client.gui.FontRendererBridge;
import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.bridge.util.ResourceLocationBridge;
import com.cheatbreaker.client.event.type.GuiDrawEvent;
import com.cheatbreaker.client.event.type.RenderPreviewEvent;
import com.cheatbreaker.client.module.AbstractModule;
import com.cheatbreaker.client.ui.module.CBGuiAnchor;
import org.lwjgl.opengl.GL11;

public class BossBarModule extends AbstractModule {
    private final ResourceLocationBridge icons = Ref.getInstanceCreator().createResourceLocation("textures/gui/icons.png");

    public BossBarModule() {
        super("Boss bar");
        this.setDefaultAnchor(CBGuiAnchor.MIDDLE_TOP);
        this.addEvent(GuiDrawEvent.class, this::renderReal);
        this.addEvent(RenderPreviewEvent.class, this::renderPreview);
        this.setPreviewLabel("Boss Bar", 1.0f);
        this.setDefaultState(true);
    }

    public void renderPreview(RenderPreviewEvent renderPreviewEvent) {
        GL11.glPushMatrix();
        this.scaleAndTranslate(renderPreviewEvent.getResolution());
        if (Ref.getBossStatus().bridge$getBossName() == null || Ref.getBossStatus().bridge$getStatusBarTime() <= 0) {
            this.minecraft.bridge$getTextureManager().bridge$bindTexture(this.icons);
            FontRendererBridge fontRenderer = this.minecraft.bridge$getFontRenderer();
            int n2 = 182;
            int n3 = 0;
            float f = 1.0f;
            int n4 = (int)(f * (float)(n2 + 1));
            int n5 = 13;
            this.minecraft.bridge$scaledTessellator(n3, n5, 0, 74, n2, 5);
            this.minecraft.bridge$scaledTessellator(n3, n5, 0, 74, n2, 5);
            if (n4 > 0) {
                this.minecraft.bridge$scaledTessellator(n3, n5, 0, 79, n4, 5);
            }
            String bossName = "Wither";
            fontRenderer.bridge$drawStringWithShadow(bossName, (int) (this.width / 2 - (fontRenderer.bridge$getStringWidth(bossName) / 2)), n5 - 10, 0xFFFFFF);
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            this.minecraft.bridge$getTextureManager().bridge$bindTexture(this.icons);
            this.setDimensions(182, 20);
        }
        GL11.glPopMatrix();
    }

    public void renderReal(GuiDrawEvent guiDrawEvent) {
        GL11.glPushMatrix();
        this.scaleAndTranslate(guiDrawEvent.getResolution());
        if (Ref.getBossStatus().bridge$getBossName() != null || Ref.getBossStatus().bridge$getStatusBarTime() > 0) {
            this.minecraft.bridge$getTextureManager().bridge$bindTexture(this.icons);
            FontRendererBridge fontRenderer = this.minecraft.bridge$getFontRenderer();
            int n2 = 182;
            int n3 = 0;
            float f = 1.0f;
            int n4 = (int)(f * (float)(n2 + 1));
            int n5 = 13;
            this.minecraft.bridge$scaledTessellator(n3, n5, 0, 74, n2, 5);
            this.minecraft.bridge$scaledTessellator(n3, n5, 0, 74, n2, 5);
            if (n4 > 0) {
                this.minecraft.bridge$scaledTessellator(n3, n5, 0, 79, n4, 5);
            }
            String bossName = Ref.getBossStatus().bridge$getBossName();
            fontRenderer.bridge$drawStringWithShadow(bossName, (int) (this.width / 2.0f - (float)(fontRenderer.bridge$getStringWidth(bossName) / 2)), n5 - 10, 0xFFFFFF);
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            this.minecraft.bridge$getTextureManager().bridge$bindTexture(this.icons);
            this.setDimensions(182, 20);
        }
        GL11.glPopMatrix();
    }


}

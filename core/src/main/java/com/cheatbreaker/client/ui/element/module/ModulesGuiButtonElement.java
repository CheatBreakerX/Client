package com.cheatbreaker.client.ui.element.module;

import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.main.CheatBreaker;
import com.cheatbreaker.client.ui.element.AbstractModulesGuiElement;
import com.cheatbreaker.client.ui.element.AbstractScrollableElement;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.ui.util.font.CBXFontRenderer;
import com.cheatbreaker.client.ui.util.font.FontRegistry;

public class ModulesGuiButtonElement extends AbstractModulesGuiElement {
    public int highlightColor;
    public String text;
    public final AbstractScrollableElement parentElement;
    private final CBXFontRenderer fontRenderer;
    public boolean field_1 = true;
    private int field_2 = 0;

    public ModulesGuiButtonElement(CBXFontRenderer fontRenderer, AbstractScrollableElement parentElement, String text, int x, int y, int width, int height, int highlightColor, float f) {
        super(f);
        this.text = text;
        this.setDimensions(x, y, width, height);
        this.highlightColor = highlightColor;
        this.parentElement = parentElement;
        this.fontRenderer = fontRenderer;
    }

    public ModulesGuiButtonElement(AbstractScrollableElement parentElement, String text, int x, int y, int width, int height, int highlightColor, float f) {
        this(FontRegistry.getPlayBold22px(), parentElement, text, x, y, width, height, highlightColor, f);
    }

    @Override
    public void handleDrawElement(int mouseX, int mouseY, float partialTicks) {
        if (this.text == null) {
            this.text = "<null>";
        }

        float f2;
        boolean bl = this.isMouseInside(mouseX, mouseY);
        int n3 = 120;
        if (bl && this.field_1) {
            Ref.modified$drawRect(this.x - 2, this.y - 2, this.x + this.width + 2, this.y + this.height + 2, -854025);
            f2 = CBModulesGui.getSmoothFloat(790f);
            this.field_2 = (float)this.field_2 + f2 < (float)n3 ? (int)((float)this.field_2 + f2) : n3;
        } else if (this.field_2 > 0) {
            f2 = CBModulesGui.getSmoothFloat(790f);
            this.field_2 = (float)this.field_2 - f2 < 0.0f ? 0 : (int)((float)this.field_2 - f2);
        }
        if (this.field_1) {
            Ref.modified$drawRect(this.x, this.y, this.x + this.width, this.y + this.height,
                    CheatBreaker.getInstance().globalSettings.isDarkMode() ? 0xFF303031 : 0xFFF4F4F5);
        } else {
            Ref.modified$drawRect(this.x, this.y, this.x + this.width, this.y + this.height,
                    CheatBreaker.getInstance().globalSettings.isDarkMode() ? 0x9E303031 : 0x9EF4F4F5);
        }
        if (this.field_2 > 0) {
            f2 = (float)this.field_2 / (float)n3 * 100f;
            Ref.modified$drawRect(this.x, (int)((float)this.y + ((float)this.height - (float)this.height * f2 / (float)100)), this.x + this.width, this.y + this.height, this.highlightColor);
        }
        if (this.text.endsWith(".png")) {
            Ref.getGlBridge().bridge$pushMatrix();
            float var1 = CheatBreaker.getInstance().globalSettings.isDarkMode() ? 1f : 0f;
            Ref.getGlBridge().bridge$color(var1, var1, var1, CheatBreaker.getInstance().globalSettings.isDarkMode() ? 1f : .45f);
            RenderUtil.drawIcon(Ref.getInstanceCreator().createResourceLocation("client/icons/" + this.text), 8f, this.x + 6f, this.y + 6f);
            Ref.getGlBridge().bridge$popMatrix();
        } else {
            f2 = this.fontRenderer == FontRegistry.getPlayBold22px() ? 2f : .5f;
            this.fontRenderer.drawCenteredString(this.text.toUpperCase(), this.x + this.width / 2f,
                    (this.y + this.height / 2f - this.fontRenderer.getHeight()) + f2,
                    CheatBreaker.getInstance().globalSettings.isDarkMode() ? 0xFFFFFFFF : 0x70000000);
        }
    }

    @Override
    public void handleMouseClick(int mouseX, int mouseY, int button) {
    }

    public void setField1(boolean bl) {
        this.field_1 = bl;
    }
}

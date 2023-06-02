package com.cheatbreaker.client.ui.element.module;

import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.main.CheatBreaker;
import com.cheatbreaker.client.ui.element.AbstractModulesGuiElement;
import com.cheatbreaker.client.ui.element.AbstractScrollableElement;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.ui.util.font.CBXFontRenderer;
import com.cheatbreaker.client.ui.util.font.FontRegistry;

public class ModulesGuiButtonElement
        extends AbstractModulesGuiElement {
    public int highlightColor;
    public String text;
    public final AbstractScrollableElement parentElement;
    private final CBXFontRenderer fontRenderer;
    public boolean IlllIllIlIIIIlIIlIIllIIIl = true;
    private int llIIlllIIIIlllIllIlIlllIl = 0;

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
        float f2;
        boolean bl = this.isMouseInside(mouseX, mouseY);
        int n3 = 120;
        if (bl && this.IlllIllIlIIIIlIIlIIllIIIl) {
            Ref.modified$drawRect(this.x - 2, this.y - 2, this.x + this.width + 2, this.y + this.height + 2, -854025);
            f2 = CBModulesGui.getSmoothFloat(790);
            this.llIIlllIIIIlllIllIlIlllIl = (float)this.llIIlllIIIIlllIllIlIlllIl + f2 < (float)n3 ? (int)((float)this.llIIlllIIIIlllIllIlIlllIl + f2) : n3;
        } else if (this.llIIlllIIIIlllIllIlIlllIl > 0) {
            f2 = CBModulesGui.getSmoothFloat(790);
            this.llIIlllIIIIlllIllIlIlllIl = (float)this.llIIlllIIIIlllIllIlIlllIl - f2 < 0.0f ? 0 : (int)((float)this.llIIlllIIIIlllIllIlIlllIl - f2);
        }
        if (this.IlllIllIlIIIIlIIlIIllIIIl) {
            Ref.modified$drawRect(this.x, this.y, this.x + this.width, this.y + this.height, CheatBreaker.getInstance().globalSettings.isDarkMode() ? -13619151 : -723723);
        } else {
            Ref.modified$drawRect(this.x, this.y, this.x + this.width, this.y + this.height, CheatBreaker.getInstance().globalSettings.isDarkMode() ? -1624231887 : -1611336459);
        }
        if (this.llIIlllIIIIlllIllIlIlllIl > 0) {
            f2 = (float)this.llIIlllIIIIlllIllIlIlllIl / (float)n3 * (float)100;
            Ref.modified$drawRect(this.x, (int)((float)this.y + ((float)this.height - (float)this.height * f2 / (float)100)), this.x + this.width, this.y + this.height, this.highlightColor);
        }
        if (this.text.endsWith(".png")) {
            Ref.getGlBridge().bridge$pushMatrix();
            float var1 = CheatBreaker.getInstance().globalSettings.isDarkMode() ? 1.0f : 0.0f;
            Ref.getGlBridge().bridge$color(var1, var1, var1, CheatBreaker.getInstance().globalSettings.isDarkMode() ? 1.0f : 0.45f);
            RenderUtil.drawIcon(Ref.getInstanceCreator().createResourceLocation("client/icons/" + this.text), 8f, (float)(this.x + 6), (float)(this.y + 6));
            Ref.getGlBridge().bridge$popMatrix();
        } else {
            f2 = this.fontRenderer == FontRegistry.getPlayBold22px() ? 2.0f : 0.5f;
            this.fontRenderer.drawCenteredString(this.text.toUpperCase(), this.x + this.width / 2, (float)(this.y + this.height / 2 - this.fontRenderer.getHeight()) + f2, CheatBreaker.getInstance().globalSettings.isDarkMode() ? -1 : 1862270976);
        }
    }

    @Override
    public void handleMouseClick(int mouseX, int mouseY, int button) {
    }

    public void lIIIIlIIllIIlIIlIIIlIIllI(boolean bl) {
        this.IlllIllIlIIIIlIIlIIllIIIl = bl;
    }
}

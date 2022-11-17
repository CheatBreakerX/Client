package com.cheatbreaker.client.ui.element.module;

import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.element.AbstractModulesGuiElement;
import com.cheatbreaker.client.ui.element.AbstractScrollableElement;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.ui.util.font.CBFontRenderer;
import com.cheatbreaker.client.ui.util.font.FontRegistry;
import org.lwjgl.opengl.GL11;

public class ModulesGuiButtonElement
        extends AbstractModulesGuiElement {
    public int lIIIIlIIllIIlIIlIIIlIIllI;
    public String displayString;
    public final AbstractScrollableElement lIIIIllIIlIlIllIIIlIllIlI;
    private final CBFontRenderer fontRenderer;
    public boolean IlllIllIlIIIIlIIlIIllIIIl = true;
    private int llIIlllIIIIlllIllIlIlllIl = 0;

    public ModulesGuiButtonElement(CBFontRenderer fontRenderer, AbstractScrollableElement lllIllIllIlIllIlIIllllIIl2, String displayString, int n, int n2, int n3, int n4, int n5, float f) {
        super(f);
        this.displayString = displayString;
        this.setDimensions(n, n2, n3, n4);
        this.lIIIIlIIllIIlIIlIIIlIIllI = n5;
        this.lIIIIllIIlIlIllIIIlIllIlI = lllIllIllIlIllIlIIllllIIl2;
        this.fontRenderer = fontRenderer;
    }

    public ModulesGuiButtonElement(AbstractScrollableElement lllIllIllIlIllIlIIllllIIl2, String string, int n, int n2, int n3, int n4, int n5, float f) {
        this(FontRegistry.getPlayBold22px(), lllIllIllIlIllIlIIllllIIl2, string, n, n2, n3, n4, n5, f);
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
            Ref.modified$drawRect(this.x, (int)((float)this.y + ((float)this.height - (float)this.height * f2 / (float)100)), this.x + this.width, this.y + this.height, this.lIIIIlIIllIIlIIlIIIlIIllI);
        }
        if (this.displayString.contains(".png")) {
            GL11.glPushMatrix();
            float var1 = CheatBreaker.getInstance().globalSettings.isDarkMode() ? 1.0f : 0.0f;
            GL11.glColor4f(var1, var1, var1, CheatBreaker.getInstance().globalSettings.isDarkMode() ? 1.0f : 0.45f);
            RenderUtil.drawIcon(Ref.getInstanceCreator().createResourceLocationBridge("client/icons/" + this.displayString), 8f, (float)(this.x + 6), (float)(this.y + 6));
            GL11.glPopMatrix();
        } else {
            f2 = this.fontRenderer == FontRegistry.getPlayBold22px() ? 2.0f : 0.5f;
            this.fontRenderer.drawCenteredString(this.displayString.toUpperCase(), this.x + this.width / 2, (float)(this.y + this.height / 2 - this.fontRenderer.getHeight()) + f2, CheatBreaker.getInstance().globalSettings.isDarkMode() ? -1 : 1862270976);
        }
    }

    @Override
    public void handleMouseClick(int mouseX, int mouseY, int button) {
    }

    public int lIIIIIIIIIlIllIIllIlIIlIl() {
        return this.width;
    }

    public void lIIIIlIIllIIlIIlIIIlIIllI(boolean bl) {
        this.IlllIllIlIIIIlIIlIIllIIIl = bl;
    }
}

package com.cheatbreaker.client.ui.element.module;

import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.bridge.util.ResourceLocationBridge;
import com.cheatbreaker.main.CheatBreaker;
import com.cheatbreaker.client.module.AbstractModule;
import com.cheatbreaker.client.ui.element.AbstractModulesGuiElement;
import com.cheatbreaker.client.ui.element.AbstractScrollableElement;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.ui.util.font.FontRegistry;

public class ModuleSettingsElement extends AbstractModulesGuiElement {
    private final int IllIIIIIIIlIlIllllIIllIII;
    public final AbstractModule module;
    private final AbstractScrollableElement parent;
    private int IlllIllIlIIIIlIIlIIllIIIl = 0;
    private ResourceLocationBridge rightIcon = Ref.getInstanceCreator().createResourceLocation("client/icons/right.png");

    public ModuleSettingsElement(AbstractScrollableElement parent, int n, AbstractModule module, float f) {
        super(f);
        this.parent = parent;
        this.IllIIIIIIIlIlIllllIIllIII = n;
        this.module = module;
    }

    @Override
    public void handleDrawElement(int mouseX, int mouseY, float partialTicks) {
        boolean bl = this.isMouseInside(mouseX, mouseY);
        int n3 = 75;
        Ref.modified$drawRect(this.x, this.y + this.height - 1, this.x + this.width, this.y + this.height, CheatBreaker.getInstance().globalSettings.isDarkMode() ? -14211288 : 791621423);
        if (this.parent.lIIIIlIIllIIlIIlIIIlIIllI(this.module)) {
            float f2;
            if (bl) {
                f2 = CBModulesGui.getSmoothFloat(790);
                if ((float)this.IlllIllIlIIIIlIIlIIllIIIl + f2 < (float)n3) {
                    this.IlllIllIlIIIIlIIlIIllIIIl = (int)((float)this.IlllIllIlIIIIlIIlIIllIIIl + f2);
                    if (this.IlllIllIlIIIIlIIlIIllIIIl > n3) {
                        this.IlllIllIlIIIIlIIlIIllIIIl = n3;
                    }
                }
            } else if (this.IlllIllIlIIIIlIIlIIllIIIl > 0) {
                f2 = CBModulesGui.getSmoothFloat(790);
                this.IlllIllIlIIIIlIIlIIllIIIl = (float)this.IlllIllIlIIIIlIIlIIllIIIl - f2 < 0.0f ? 0 : (int)((float)this.IlllIllIlIIIIlIIlIIllIIIl - f2);
            }
            if (this.IlllIllIlIIIIlIIlIIllIIIl > 0) {
                f2 = (float)this.IlllIllIlIIIIlIIlIIllIIIl / (float)n3 * (float)100;
                Ref.modified$drawRect(this.x, (int)((float)this.y + ((float)this.height - (float)this.height * f2 / (float)100)), this.x + this.width, this.y + this.height, this.IllIIIIIIIlIlIllllIIllIII);
            }
        }
        Ref.getGlBridge().bridge$color(0.0f, 0.0f, 0.0f, 1.4385965f * 0.24329267f);
        RenderUtil.drawIcon(this.rightIcon, 1.9411765f * 1.2878788f, (float)(this.x + 6), (float)this.y + (float)6);
        FontRegistry.getPlayBold18px().drawString(this.module.getName().toUpperCase(), (float)this.x + (float)14, (float)this.y + (float)3, this.parent.lIIIIlIIllIIlIIlIIIlIIllI(this.module) ? (CheatBreaker.getInstance().globalSettings.isDarkMode() ? -1 : -818991313) : 0x2F2F2F2F);
    }

    @Override
    public void handleMouseClick(int mouseX, int mouseY, int button) {
        this.parent.lIIIIIIIIIlIllIIllIlIIlIl(this.module);
    }
}

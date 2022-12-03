package com.cheatbreaker.client.ui.mainmenu;

import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.bridge.util.ResourceLocationBridge;
import com.cheatbreaker.client.ui.fading.ColorFade;
import com.cheatbreaker.client.ui.fading.MinMaxFade;
import com.cheatbreaker.client.ui.mainmenu.element.ScrollableElement;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.ui.util.font.FontRegistry;
import lombok.Setter;

public class AccountList extends AbstractElement {
    @Setter
    private ResourceLocationBridge headLocation;
    @Setter
    private String displayName;
    private final ColorFade lIIIIllIIlIlIllIIIlIllIlI;
    private final ColorFade IlllIllIlIIIIlIIlIIllIIIl;
    private final ColorFade IlIlllIIIIllIllllIllIIlIl;
    private final MinMaxFade llIIlllIIIIlllIllIlIlllIl;
    private float elementHeight;
    private boolean IIIlllIIIllIllIlIIIIIIlII;
    private final MainMenuBase mainMenuBase;
    private final ScrollableElement scrollableElement;
    private float lllIIIIIlIllIlIIIllllllII;

    public AccountList(MainMenuBase base, String displayName, ResourceLocationBridge headLocation) {
        this.mainMenuBase = base;
        this.displayName = displayName;
        this.headLocation = headLocation;
        this.scrollableElement = new ScrollableElement(this);
        this.lIIIIllIIlIlIllIIIlIllIlI = new ColorFade(0x4FFFFFFF, -1353670564);
        this.IlllIllIlIIIIlIIlIIllIIIl = new ColorFade(444958085, 1063565678);
        this.IlIlllIIIIllIllllIllIIlIl = new ColorFade(444958085, 1062577506);
        this.llIIlllIIIIlllIllIlIlllIl = new MinMaxFade(300L);
    }

    public void IllIIIIIIIlIlIllllIIllIII() {
        this.setElementSize(this.x, this.y, this.width, this.height);
    }

    @Override
    public void setElementSize(float x, float y, float width, float height) {
        super.setElementSize(x, y, width, height);
        if (this.elementHeight == 0.0f) {
            this.elementHeight = height;
        }
        this.lllIIIIIlIllIlIIIllllllII = Math.min(this.mainMenuBase.getAccounts().size() * 16 + 12, 120);
        this.scrollableElement.setElementSize(x + width - (float)5, y + this.elementHeight + (float)6, (float)4, this.lllIIIIIlIllIlIIIllllllII - (float)7);
        this.scrollableElement.setScrollAmount(this.mainMenuBase.getAccounts().size() * 16 + 4);
    }

    @Override
    public void handleElementMouse() {
        this.scrollableElement.handleElementMouse();
    }

    @Override
    protected void handleElementDraw(float f, float f2, boolean bl) {
        boolean bl2 = bl && this.isMouseInside(f, f2);
        RenderUtil.lIIIIlIIllIIlIIlIIIlIIllI(this.x, this.y, this.x + this.width, this.y + this.elementHeight, this.lIIIIllIIlIlIllIIIlIllIlI.get(bl2).getRGB(), this.IlllIllIlIIIIlIIlIIllIIIl.get(bl2).getRGB(), this.IlIlllIIIIllIllllIllIIlIl.get(bl2).getRGB());
        float f3 = 6;
        Ref.getGlBridge().bridge$color(1.0f, 1.0f, 1.0f, 1.0f);
        RenderUtil.drawIcon(this.headLocation, f3, this.x + (float)4, this.y + this.elementHeight / 2.0f - f3);
        FontRegistry.getRobotoRegular13px().drawString(this.displayName, this.x + (float)22, this.y + 1.56f * 2.8846154f, -1342177281);
        float f4 = this.llIIlllIIIIlllIllIlIlllIl.lIIIIlIIllIIlIIlIIIlIIllI(this.isMouseInside(f, f2) && bl);
        if (this.llIIlllIIIIlllIllIlIlllIl.IIIllIllIlIlllllllIlIlIII()) {
            this.setElementSize(this.x, this.y, this.width, this.elementHeight + this.lllIIIIIlIllIlIIIllllllII * f4);
            this.IIIlllIIIllIllIlIIIIIIlII = true;
        } else if (!this.llIIlllIIIIlllIllIlIlllIl.IIIllIllIlIlllllllIlIlIII() && !this.isMouseInside(f, f2)) {
            this.IIIlllIIIllIllIlIIIIIIlII = false;
        }
        if (this.IIIlllIIIllIllIlIIIIIIlII) {
            float f5 = 0.6122449f * 0.81666666f;
            float f6 = this.y + this.height + f5;
            float f7 = this.y + (float)5 + this.elementHeight;
            if (f6 > f7) {
                Ref.modified$drawBoxWithOutLine(this.x + 1.0f, f7, this.x + this.width - 1.0f, f6, f5, 0x4FFFFFFF, 444958085);
            }
            Ref.getGlBridge().bridge$pushMatrix();
            Ref.getGlBridge().bridge$enableScissoring();
            RenderUtil.lIIIIlIIllIIlIIlIIIlIIllI(
                    (int)this.x,
                    (int)(this.y + this.height),
                    (int)(this.x + this.elementHeight),
                    (int)(this.y + this.elementHeight + (float)7 + (this.height - this.elementHeight - (float)6) * f4),
                    (float)((int)((float)this.mainMenuBase.getResolution().bridge$getScaleFactor() * this.mainMenuBase.getScaleFactor())),
                    (int)this.mainMenuBase.getScaledHeight()
            );
            this.scrollableElement.drawScrollable(f, f2, bl);
            int n = 1;
            for (Account account : this.mainMenuBase.getAccounts()) {
                float left = this.x;
                float right = this.x + this.width;
                float f10 = this.y + this.elementHeight + (float)(n * 16) - (float)8;
                float f11 = f10 + (float)16;
                boolean hovered = f > left && f < right && f2 - this.scrollableElement.IllIIIIIIIlIlIllllIIllIII() > f10 && f2 - this.scrollableElement.IllIIIIIIIlIlIllllIIllIII() < f11 && bl && !this.scrollableElement.isMouseInside(f, f2) && !this.scrollableElement.isDragClick();
                Ref.getGlBridge().bridge$color(1.0f, 1.0f, 1.0f, hovered ? 1.0f : 0.8148148f * 0.8590909f);
                RenderUtil.drawIcon(account.getHeadLocation(), f3, this.x + (float)4, f10 + (float)8 - f3);
                FontRegistry.getRobotoRegular13px().drawString(account.getDisplayName(), this.x + (float)22, f10 + (float)4, hovered ? -1 : -1342177281);
                ++n;
            }
            this.scrollableElement.handleElementDraw(f, f2, bl);
            Ref.getGlBridge().bridge$disableScissoring();
            Ref.getGlBridge().bridge$popMatrix();
        }
    }

    public float getMaxWidthFor(float f) {
        return (float)22 + f + (float)10;
    }

    @Override
    public boolean handleElementMouseClicked(float f, float f2, int n, boolean bl) {
        if (!bl) {
            return false;
        }
        if (this.llIIlllIIIIlllIllIlIlllIl.IllIllIIIlIIlllIIIllIllII()) {
            this.scrollableElement.handleElementMouseClicked(f, f2, n, bl);
            int n2 = 1;
            for (Account account : this.mainMenuBase.getAccounts()) {
                boolean bl2;
                float f3 = this.x;
                float f4 = this.x + this.width;
                float f5 = this.y + this.elementHeight + (float)(n2 * 16) - (float)8;
                float f6 = f5 + (float)16;
                boolean bl3 = bl2 = f > f3 && f < f4 && f2 - this.scrollableElement.IllIIIIIIIlIlIllllIIllIII() > f5 && f2 - this.scrollableElement.IllIIIIIIIlIlIllllIIllIII() < f6 && bl && !this.scrollableElement.isMouseInside(f, f2) && !this.scrollableElement.isDragClick();
                if (bl2) {
                    Ref.getMinecraft().bridge$getSoundHandler().bridge$playSound(Ref.getInstanceCreator().createSoundFromPSR(Ref.getInstanceCreator().createResourceLocation("gui.button.press"), 1.0f));
                    this.mainMenuBase.login(account.getDisplayName());
                }
                ++n2;
            }
        }
        return false;
    }
}

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
    private final ColorFade outlineColour;
    private final ColorFade rectGradientStartColour;
    private final ColorFade rectGradientEndColour;
    private final MinMaxFade openAccountMenuTransition;
    private float elementHeight;
    private boolean IIIlllIIIllIllIlIIIIIIlII;
    private final MainMenuBase base;
    private final ScrollableElement scrollableElement;
    private float containerHeight;

    public AccountList(MainMenuBase base, String displayName, ResourceLocationBridge headLocation) {
        this.base = base;
        this.displayName = displayName;
        this.headLocation = headLocation;
        this.scrollableElement = new ScrollableElement(this);

        this.outlineColour = new ColorFade(0x4FFFFFFF, -1353670564);
        this.rectGradientStartColour = new ColorFade(444958085, 1063565678);
        this.rectGradientEndColour = new ColorFade(444958085, 1062577506);
        this.openAccountMenuTransition = new MinMaxFade(300L);
    }

    @Override
    public void setElementDimensions(float x, float y, float width, float height) {
        super.setElementDimensions(x, y, width, height);
        if (this.elementHeight == 0.0f) {
            this.elementHeight = height;
        }
        this.containerHeight = Math.min(this.base.getAccounts().size() * 16 + 12, 120);
        this.scrollableElement.setElementDimensions(x + width - 5f, y + this.elementHeight + 6f, 4f, this.containerHeight - 7f);
        this.scrollableElement.setScrollAmount(this.base.getAccounts().size() * 16 + 4);
    }

    @Override
    public void handleElementMouse() {
        this.scrollableElement.handleElementMouse();
    }

    @Override
    protected void handleElementDraw(float f, float f2, boolean bl) {
        boolean bl2 = bl && this.isMouseInside(f, f2);
        RenderUtil.drawCorneredGradientRectWithOutline(this.x, this.y, this.x + this.width, this.y + this.elementHeight,
                this.outlineColour.get(bl2).getRGB(), this.rectGradientStartColour.get(bl2).getRGB(), this.rectGradientEndColour.get(bl2).getRGB());
        float f3 = 6;
        Ref.getGlBridge().bridge$color(1.0f, 1.0f, 1.0f, 1.0f);
        RenderUtil.drawIcon(this.headLocation, f3, this.x + (float)4, this.y + this.elementHeight / 2.0f - f3);
        FontRegistry.getRobotoRegular13px().drawString(this.displayName, this.x + (float)22, this.y + 1.56f * 2.8846154f, -1342177281);
        float f4 = this.openAccountMenuTransition.lIIIIlIIllIIlIIlIIIlIIllI(this.isMouseInside(f, f2) && bl);
        if (this.openAccountMenuTransition.IIIllIllIlIlllllllIlIlIII()) {
            this.setElementDimensions(this.x, this.y, this.width, this.elementHeight + this.containerHeight * f4);
            this.IIIlllIIIllIllIlIIIIIIlII = true;
        } else if (!this.openAccountMenuTransition.IIIllIllIlIlllllllIlIlIII() && !this.isMouseInside(f, f2)) {
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
            RenderUtil.scissorArea(
                    (int)this.x,
                    (int)(this.y + this.height),
                    (int)(this.x + this.elementHeight),
                    (int)(this.y + this.elementHeight + (float)7 + (this.height - this.elementHeight - (float)6) * f4),
                    (float)((int)((float)this.base.getResolution().bridge$getScaleFactor() * this.base.getScaleFactor())),
                    (int)this.base.getScaledHeight()
            );
            this.scrollableElement.drawScrollable(f, f2, bl);
            int n = 1;
            for (Account account : this.base.getAccounts()) {
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
        if (this.openAccountMenuTransition.isCurrentlyInverted()) {
            this.scrollableElement.handleElementMouseClicked(f, f2, n, bl);
            int n2 = 1;
            for (Account account : this.base.getAccounts()) {
                boolean bl2;
                float f3 = this.x;
                float f4 = this.x + this.width;
                float f5 = this.y + this.elementHeight + (float)(n2 * 16) - (float)8;
                float f6 = f5 + (float)16;
                boolean bl3 = bl2 = f > f3 && f < f4 && f2 - this.scrollableElement.IllIIIIIIIlIlIllllIIllIII() > f5 && f2 - this.scrollableElement.IllIIIIIIIlIlIllllIIllIII() < f6 && bl && !this.scrollableElement.isMouseInside(f, f2) && !this.scrollableElement.isDragClick();
                if (bl2) {
                    Ref.getMinecraft().bridge$getSoundHandler().bridge$playSound(Ref.getInstanceCreator().createSoundFromPSR(Ref.getInstanceCreator().createResourceLocation("gui.button.press"), 1.0f));
                    this.base.login(account.getDisplayName());
                }
                ++n2;
            }
        }
        return false;
    }
}

package com.cheatbreaker.client.ui.mainmenu;

import com.cheatbreaker.client.ui.fading.ColorFade;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.ui.util.font.FontRegistry;

public class GradientTextButton extends AbstractElement {
    private String text;
    private final ColorFade IllIIIIIIIlIlIllllIIllIII;
    private final ColorFade lIIIIllIIlIlIllIIIlIllIlI;
    private final ColorFade IlllIllIlIIIIlIIlIIllIIIl;
    private int[] IlIlllIIIIllIllllIllIIlIl;

    public GradientTextButton(String string) {
        this.text = string;
        this.IllIIIIIIIlIlIllllIIllIII = new ColorFade(-14277082, -11493284);
        this.lIIIIllIIlIlIllIIIlIllIlI = new ColorFade(-13487566, -10176146);
        this.IlllIllIlIIIIlIIlIIllIIIl = new ColorFade(-14013910, -11164318);
    }

    public void IllIIIIIIIlIlIllllIIllIII() {
        this.lIIIIlIIllIIlIIlIIIlIIllI(new int[]{-11119018, -11493284, -10329502, -10176146, -11579569, -11164318});
    }

    public void lIIIIllIIlIlIllIIIlIllIlI() {
        this.lIIIIlIIllIIlIIlIIIlIIllI(new int[]{-11493284, -11493284, -10176146, -10176146, -11164318, -11164318});
    }

    public void IlllIllIlIIIIlIIlIIllIIIl() {
        this.lIIIIlIIllIIlIIlIIIlIIllI(new int[]{-14277082, -11493284, -13487566, -10176146, -14013910, -11164318});
    }

    private void lIIIIlIIllIIlIIlIIIlIIllI(int[] arrn) {
        this.IlIlllIIIIllIllllIllIIlIl = arrn;
    }

    @Override
    protected void handleElementDraw(float f, float f2, boolean bl) {
        boolean bl2;
        boolean bl3 = bl2 = bl && this.isMouseInside(f, f2);
        if (this.IlIlllIIIIllIllllIllIIlIl != null && this.IllIIIIIIIlIlIllllIIllIII.IIIIllIIllIIIIllIllIIIlIl()) {
            this.IllIIIIIIIlIlIllllIIllIII.setStartColor(this.IlIlllIIIIllIllllIllIIlIl[0]);
            this.IllIIIIIIIlIlIllllIIllIII.setEndColor(this.IlIlllIIIIllIllllIllIIlIl[1]);
            this.lIIIIllIIlIlIllIIIlIllIlI.setStartColor(this.IlIlllIIIIllIllllIllIIlIl[2]);
            this.lIIIIllIIlIlIllIIIlIllIlI.setEndColor(this.IlIlllIIIIllIllllIllIIlIl[3]);
            this.IlllIllIlIIIIlIIlIIllIIIl.setStartColor(this.IlIlllIIIIllIllllIllIIlIl[4]);
            this.IlllIllIlIIIIlIIlIIllIIIl.setEndColor(this.IlIlllIIIIllIllllIllIIlIl[5]);
            this.IlIlllIIIIllIllllIllIIlIl = null;
        }
        RenderUtil.lIIIIlIIllIIlIIlIIIlIIllI(this.x, this.y, this.x + this.width, this.y + this.height, this.IllIIIIIIIlIlIllllIIllIII.get(bl2).getRGB(), this.lIIIIllIIlIlIllIIIlIllIlI.get(bl2).getRGB(), this.IlllIllIlIIIIlIIlIIllIIIl.get(bl2).getRGB());
        FontRegistry.getRobotoRegular13px().drawCenteredString(this.text, this.x + this.width / 2.0f, this.y + 2.0f, -1);
    }

    public void IlllIIIlIlllIllIlIIlllIlI(float f, float f2, boolean bl) {
        this.handleElementDraw(f, f2, bl);
    }

    public void IIIIllIlIIIllIlllIlllllIl(float f, float f2, boolean bl) {
        this.handleElementDraw(f, f2, bl);
    }

    @Override
    public boolean handleElementMouseClicked(float f, float f2, int n, boolean bl) {
        if (!bl) {
            return false;
        }
        return false;
    }

    public String IlIlllIIIIllIllllIllIIlIl() {
        return this.text;
    }

    public void lIIIIlIIllIIlIIlIIIlIIllI(String string) {
        this.text = string;
    }
}
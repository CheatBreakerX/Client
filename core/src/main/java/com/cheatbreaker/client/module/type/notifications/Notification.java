package com.cheatbreaker.client.module.type.notifications;

import com.cheatbreaker.bridge.client.gui.ScaledResolutionBridge;
import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.ui.util.font.CBXFontRenderer;
import com.cheatbreaker.client.ui.util.font.FontRegistry;

class Notification {
    public CBNotificationType type;
    public String lIIIIIIIIIlIllIIllIlIIlIl;
    public long IlllIIIlIlllIllIlIIlllIlI;
    public long IIIIllIlIIIllIlllIlllllIl = System.currentTimeMillis();
    public int IIIIllIIllIIIIllIllIIIlIl = 2;
    public int IlIlIIIlllIIIlIlllIlIllIl;
    public int IIIllIllIlIlllllllIlIlIII = 20;
    public int IllIIIIIIIlIlIllllIIllIII = 0;
    final CBNotificationsModule notificationsModule;

    Notification(CBNotificationsModule notificationsModule, ScaledResolutionBridge scaledResolution, CBNotificationType notificationType, String string, long l) {
        this.notificationsModule = notificationsModule;
        this.type = notificationType;
        this.lIIIIIIIIIlIllIIllIlIIlIl = string;
        this.IlllIIIlIlllIllIlIIlllIlI = l;
        this.IIIllIllIlIlllllllIlIlIII = notificationType == CBNotificationType.DEFAULT ? 16 : 20;
        this.IlIlIIIlllIIIlIlllIlIllIl = (int) (scaledResolution.bridge$getScaledHeight() - 14 - this.IIIllIllIlIlllllllIlIlIII);
        this.IIIIllIIllIIIIllIllIIIlIl = (int) (scaledResolution.bridge$getScaledHeight() + this.IIIllIllIlIlllllllIlIlIII);
    }

    public void lIIIIlIIllIIlIIlIIIlIIllI() {
        if (this.IlIlIIIlllIIIlIlllIlIllIl != -1) {
            ++this.IllIIIIIIIlIlIllllIIllIII;
            float f = (float)this.IllIIIIIIIlIlIllllIIllIII * ((float)this.IllIIIIIIIlIlIllllIIllIII / (float)5) / (float)7;
            if (this.IIIIllIIllIIIIllIllIIIlIl > this.IlIlIIIlllIIIlIlllIlIllIl) {
                if ((float)this.IIIIllIIllIIIIllIllIIIlIl - f < (float)this.IlIlIIIlllIIIlIlllIlIllIl) {
                    this.IIIIllIIllIIIIllIllIIIlIl = this.IlIlIIIlllIIIlIlllIlIllIl;
                    this.IlIlIIIlllIIIlIlllIlIllIl = -1;
                } else {
                    this.IIIIllIIllIIIIllIllIIIlIl = (int)((float)this.IIIIllIIllIIIIllIllIIIlIl - f);
                }
            } else if (this.IIIIllIIllIIIIllIllIIIlIl < this.IlIlIIIlllIIIlIlllIlIllIl) {
                if ((float)this.IIIIllIIllIIIIllIllIIIlIl + f > (float)this.IlIlIIIlllIIIlIlllIlIllIl) {
                    this.IIIIllIIllIIIIllIllIIIlIl = this.IlIlIIIlllIIIlIlllIlIllIl;
                    this.IlIlIIIlllIIIlIlllIlIllIl = -1;
                } else {
                    this.IIIIllIIllIIIIllIllIIIlIl = (int)((float)this.IIIIllIIllIIIIllIllIIIlIl + f);
                }
            } else {
                this.IlIlIIIlllIIIlIlllIlIllIl = -1;
            }
        }
    }

    public void lIIIIlIIllIIlIIlIIIlIIllI(int n) {
        CBXFontRenderer font = FontRegistry.getPlayRegular16px();
        int n2 = this.IIIIllIIllIIIIllIllIIIlIl;
        float f = font.getStringWidth(this.lIIIIIIIIIlIllIIllIlIIlIl);
        int n3 = (int)(this.type == CBNotificationType.DEFAULT ? f + (float)10 : f + (float)30);
        Ref.modified$drawRect(n - 5 - n3, n2, n - 5, n2 + this.IIIllIllIlIlllllllIlIlIII, -1358954496);
        switch (this.type) {
            case ERROR: {
                Ref.getGlBridge().bridge$color(1.0f, 1.0f, 1.0f, 1.0f);
                RenderUtil.drawIcon(Ref.getInstanceCreator().createResourceLocation("client/icons/error-64.png"), (float)6, (float)(n - 10 - n3 + 9), (float)(n2 + 4));
                Ref.modified$drawRect((float)(n - 10) - f - 7.8428574f * 0.57377046f, n2 + 4, (float)(n - 10) - f - (float)4, n2 + this.IIIllIllIlIlllllllIlIlIII - 4, -1342177281);
                break;
            }
            case INFO: {
                Ref.getGlBridge().bridge$color(1.0f, 1.0f, 1.0f, 0.7955224f * 0.81707317f);
                RenderUtil.drawIcon(Ref.getInstanceCreator().createResourceLocation("client/icons/info-64.png"), (float)6, (float)(n - 10 - n3 + 9), (float)(n2 + 4));
                Ref.modified$drawRect((float)(n - 10) - f - 11.142858f * 0.40384614f, n2 + 4, (float)(n - 10) - f - (float)4, n2 + this.IIIllIllIlIlllllllIlIlIII - 4, -1342177281);
            }
        }
        long l = this.IlllIIIlIlllIllIlIIlllIlI - (this.IIIIllIlIIIllIlllIlllllIl + this.IlllIIIlIlllIllIlIIlllIlI - System.currentTimeMillis());
        if (l > this.IlllIIIlIlllIllIlIIlllIlI) {
            l = this.IlllIIIlIlllIllIlIIlllIlI;
        }
        if (l < 0L) {
            l = 0L;
        }
        float f2 = f * ((float)l / (float)this.IlllIIIlIlllIllIlIIlllIlI * (float)100 / (float)100);
        Ref.modified$drawRect((float)(n - 10) - f, (float)(n2 + this.IIIllIllIlIlllllllIlIlIII) - 56.46667f * 0.077922076f, (float)(n - 10) - f + f, n2 + this.IIIllIllIlIlllllllIlIlIII - 4, 0x30666666);
        Ref.modified$drawRect((float)(n - 10) - f, (float)(n2 + this.IIIllIllIlIlllllllIlIlIII) - 2.2f * 2.0f, (float)(n - 10) - f + f2, n2 + this.IIIllIllIlIlllllllIlIlIII - 4, -1878982912);
        font.drawString(this.lIIIIIIIIIlIllIIllIlIIlIl, (float)(n - 10) - f, (float)(n2 + (this.type == CBNotificationType.DEFAULT ? 2 : 4)), -1);
    }
}

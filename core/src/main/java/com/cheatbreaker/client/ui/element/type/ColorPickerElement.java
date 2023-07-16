package com.cheatbreaker.client.ui.element.type;

import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.bridge.client.renderer.TessellatorBridge;
import com.cheatbreaker.main.CheatBreaker;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.ui.element.AbstractModulesGuiElement;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.ui.util.font.FontRegistry;
import org.lwjgl.input.Mouse;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ColorPickerElement extends AbstractModulesGuiElement {
    private final Setting setting;
    private final List<ColorPickerColorElement> colors;
    private boolean lIIIIllIIlIlIllIIIlIllIlI = false;
    private boolean IlllIllIlIIIIlIIlIIllIIIl = false;
    private boolean IlIlllIIIIllIllllIllIIlIl = false;
    private boolean llIIlllIIIIlllIllIlIlllIl = false;
    private final ColorPickerColorElement colorPickerColorElement;
    private float IIIlllIIIllIllIlIIIIIIlII = 1.0f;
    private float llIlIIIlIIIIlIlllIlIIIIll = 1.0f;
    private float pickerX;
    private float pickerY;
    private float pickerWidth;
    private float pickerHeight;
    private int lIIlIIllIIIIIlIllIIIIllII;
    private int lIIlllIIlIlllllllllIIIIIl;
    private int lIllIllIlIIllIllIlIlIIlIl;
    private int llIlIIIllIIIIlllIlIIIIIlI;

    public ColorPickerElement(Setting setting, float f) {
        super(f);
        this.setting = setting;
        this.colorPickerColorElement = new ColorPickerColorElement(f, setting.<Integer>value());
        this.colors = new ArrayList<>();
        for (int i = 0; i < 16; ++i) {
            int n = Ref.getMinecraft().bridge$getFontRenderer().bridge$getColorForID(i);
            this.colors.add(new ColorPickerColorElement(f, n));
        }
    }

    @Override
    public void handleDrawElement(int mouseX, int mouseY, float partialTicks) {
        String string;
        this.height = this.lIIIIllIIlIlIllIIIlIllIlI ? 130 : 18;
        this.pickerX = this.x + 56;
        float lIIIIIllllIIIIlIlIIIIlIlI = this.x + 176;
        this.pickerY = this.y + 25;
        float IIIIIIlIlIlIllllllIlllIlI = this.y + 119;
        this.pickerWidth = lIIIIIllllIIIIlIlIIIIlIlI - this.pickerX;
        this.pickerHeight = IIIIIIlIlIlIllllllIlllIlI - this.pickerY;
        FontRegistry.getUbuntuMedium16px().drawString(this.setting.getLabel().toUpperCase(), this.x + 10, (float)(this.y + 4), CheatBreaker.getInstance().globalSettings.isDarkMode() ? -1 : -1895825408);
        this.colorPickerColorElement.color = this.setting.getColorValue();
        this.colorPickerColorElement.setDimensions(this.x + 160, this.y + 3, 14, 14);
        this.colorPickerColorElement.yOffset = this.yOffset;
        this.colorPickerColorElement.handleDrawElement(mouseX, mouseY, partialTicks);
        Ref.modified$drawRect(this.x + 186, this.y + 16, this.x + this.width - 16, this.y + 17, 0x7F000000);
        FontRegistry.getPlayBold18px().drawString("#", this.x + 188, (float)(this.y + 4),
                CheatBreaker.getInstance().getGlobalSettings().isDarkMode() ? 0xAFFFFFFF : 0xAF000000);
        FontRegistry.getPlayBold18px().drawString(Integer.toHexString(this.setting.getColorValue()), this.x + 194, (float)(this.y + 4),
                CheatBreaker.getInstance().getGlobalSettings().isDarkMode() ? 0xAFFFFFFF : 0xAF000000);
        boolean bl = (float) mouseX > (float)(this.x + this.width - 40) * this.scale && (float) mouseX < (float)(this.x + this.width - 12) * this.scale && (float) mouseY > (float)(this.y + this.yOffset) * this.scale && (float) mouseY < (float)(this.y + 18 + this.yOffset) * this.scale;
        string = bl ? "(Favorite)" : "(+)";
        if (CheatBreaker.getInstance().globalSettings.isFavouriteColor(this.setting.<Integer>value())) {
            string = bl ? "(Un-favorite)" : "(-)";
        }
        FontRegistry.getPlayBold18px().drawString(string, this.x + this.width - 16 - FontRegistry.getPlayBold18px().getStringWidth(string), (float)(this.y + 4),
                CheatBreaker.getInstance().getGlobalSettings().isDarkMode() ? (bl ? 0xCFFFFFFF : 0xAFFFFFFF) : (bl ? 0xCF000000 : 0xAF000000));
        if (this.lIIIIllIIlIlIllIIIlIllIlI) {
            if (this.IlllIllIlIIIIlIIlIIllIIIl && !Mouse.isButtonDown(0)) {
                this.IlllIllIlIIIIlIIlIIllIIIl = false;
                this.lIIIIIIIIIlIllIIllIlIIlIl();
            }
            if (this.IlIlllIIIIllIllllIllIIlIl && !Mouse.isButtonDown(0)) {
                this.IlIlllIIIIllIllllIllIIlIl = false;
                this.lIIIIIIIIIlIllIIllIlIIlIl();
            }
            if (this.llIIlllIIIIlllIllIlIlllIl && !Mouse.isButtonDown(0)) {
                this.llIIlllIIIIlllIllIlIlllIl = false;
                this.lIIIIIIIIIlIllIIllIlIIlIl();
            }
            Ref.modified$drawRect(this.x + 55, this.y + 24, this.x + 177, this.y + 120, -822083584);
            TessellatorBridge tessellator = Ref.getTessellator();
            Ref.getGlBridge().bridge$disableTexture2D();
            tessellator.bridge$startDrawingQuads();
            Ref.getGlBridge().bridge$color(1.0f, 1.0f, 1.0f, 1.0f);
            tessellator.bridge$pos(this.pickerX, IIIIIIlIlIlIllllllIlllIlI, 0.0).bridge$endVertex();
            tessellator.bridge$pos(lIIIIIllllIIIIlIlIIIIlIlI, IIIIIIlIlIlIllllllIlllIlI, 0.0).bridge$endVertex();
            tessellator.bridge$pos(lIIIIIllllIIIIlIlIIIIlIlI, this.pickerY, 0.0).bridge$endVertex();
            tessellator.bridge$pos(this.pickerX, this.pickerY, 0.0).bridge$endVertex();
            tessellator.bridge$finish();
            int[] arrn = null;
            int n3 = 0;
            while ((float)n3 < this.pickerWidth) {
                int n4 = 0;
                while ((float)n4 < this.pickerHeight) {
                    boolean bl2;
                    float f2 = (float)n3 / this.pickerWidth;
                    float f3 = 1.0f - (float)n4 / this.pickerHeight;
                    int n5 = (int)this.llIlIIIlIIIIlIlllIlIIIIll << 24 | Color.HSBtoRGB(this.IIIlllIIIllIllIlIIIIIIlII, f2, f3);
                    boolean bl3 = (float) mouseX >= (this.pickerX + (float)n3) * this.scale && (float) mouseX <= (this.pickerX + (float)n3 + 1.0f) * this.scale;
                    boolean bl4 = (float) mouseY <= (this.pickerY + (float)n4 + 1.0f + (float)this.yOffset) * this.scale && (float) mouseY > (this.pickerY + (float)n4 + (float)this.yOffset) * this.scale;
                    boolean bl5 = bl3 && bl4;
                    boolean bl6 = n3 == 0 && (float) mouseX < this.pickerX * this.scale && bl4;
                    boolean bl7 = n4 == 0 && (float) mouseY < (this.pickerY + (float)this.yOffset) * this.scale && bl3;
                    boolean bl8 = (float)n3 == this.pickerWidth - 1.0f && (float) mouseX > (this.pickerX + this.pickerWidth) * this.scale && bl4;
                    bl2 = (float)n4 == this.pickerHeight - 1.0f && (float) mouseY > (this.pickerY + this.pickerHeight + (float)this.yOffset) * this.scale && bl3;
                    if (this.IlllIllIlIIIIlIIlIIllIIIl && (bl5 || bl6 || bl7 || bl8 || bl2)) {
                        this.setting.setValue(n5);
                        this.setting.colorArray = new int[]{n3, n4};
                    }
                    if (this.setting.colorArray != null) {
                        arrn = this.setting.colorArray;
                    } else if (n5 == this.setting.<Integer>value()) {
                        arrn = new int[]{n3, n4};
                    }
                    tessellator.bridge$startDrawingQuads();
                    Ref.getGlBridge().bridge$color((float)(n5 >> 16 & 0xFF) / (float)255, (float)(n5 >> 8 & 0xFF) / (float)255, (float)(n5 & 0xFF) / (float)255, 1.0f);
                    tessellator.bridge$pos(this.pickerX + (float)n3, this.pickerY + (float)n4 + 1.0f, 0.0).bridge$endVertex();
                    tessellator.bridge$pos(this.pickerX + (float)n3 + 1.0f, this.pickerY + (float)n4 + 1.0f, 0.0).bridge$endVertex();
                    tessellator.bridge$pos(this.pickerX + (float)n3 + 1.0f, this.pickerY + (float)n4, 0.0).bridge$endVertex();
                    tessellator.bridge$pos(this.pickerX + (float)n3, this.pickerY + (float)n4, 0.0).bridge$endVertex();
                    tessellator.bridge$finish();
                    ++n4;
                }
                ++n3;
            }
            if (arrn != null) {
                Ref.getGlBridge().bridge$pushMatrix();
                Ref.getGlBridge().bridge$color(0.0f, 0.0f, 0.0f, 3.0f * 0.25f);
                RenderUtil.renderCircle(this.pickerX + (float)arrn[0] + 1.2205882f * 0.913494f, this.pickerY + (float)arrn[1] + 0.097222224f * 11.468572f, 4);
                Ref.getGlBridge().bridge$color(1.0f, 1.0f, 1.0f, 1.0f);
                RenderUtil.renderCircle(this.pickerX + (float)arrn[0] + 0.24193548f * 4.608667f, this.pickerY + (float)arrn[1] + 0.23157895f * 4.8147726f, 2.7f);
                Ref.getGlBridge().bridge$popMatrix();
            }
            Ref.modified$drawRect(this.pickerX - (float) 51, this.pickerY + 1.0f, this.pickerX - (float) 43, this.pickerY + (float) 9, -16777216);
            Ref.modified$drawRect(this.pickerX - (float)50, this.pickerY + 2.0f, this.pickerX - (float)44, this.pickerY + (float)8, this.setting.rainbow ? -13369549 : -1);
            FontRegistry.getPlayRegular16px().drawString("CHROMA", this.pickerX - (float)40, this.pickerY, CheatBreaker.getInstance().getGlobalSettings().isDarkMode() ? -1 : -1358954496);
            this.IlllIIIlIlllIllIlIIlllIlI(mouseY);
            this.lIIIIIIIIIlIllIIllIlIIlIl(mouseY);
            this.lIIlIIllIIIIIlIllIIIIllII = (int)(this.pickerX + this.pickerWidth + (float)64);
            this.lIIlllIIlIlllllllllIIIIIl = (int)this.pickerY;
            this.drawColorList(CheatBreaker.getInstance().globalSettings.IlIIlIIlIllIIIIllIIllIlIl, this.lIIlIIllIIIIIlIllIIIIllII, this.lIIlllIIlIlllllllllIIIIIl, mouseX, mouseY, (int) partialTicks);
            this.lIllIllIlIIllIllIlIlIIlIl = (int)(this.pickerX + this.pickerWidth + (float)94);
            this.llIlIIIllIIIIlllIlIIIIIlI = (int)this.pickerY;
            this.drawColorList(CheatBreaker.getInstance().globalSettings.favouriteColors, this.lIllIllIlIIllIllIlIlIIlIl, this.llIlIIIllIIIIlllIlIIIIIlI, mouseX, mouseY, (int) partialTicks);
            this.drawColorList(this.colors, (int)(this.pickerX + this.pickerWidth + (float)34), (int)this.pickerY, mouseX, mouseY, (int) partialTicks);
        }
    }

    private void lIIIIIIIIIlIllIIllIlIIlIl() {
        if (CheatBreaker.getInstance().globalSettings.IlIIlIIlIllIIIIllIIllIlIl.size() >= 16) {
            CheatBreaker.getInstance().globalSettings.IlIIlIIlIllIIIIllIIllIlIl.remove(0);
        }
        CheatBreaker.getInstance().globalSettings.IlIIlIIlIllIIIIllIIllIlIl.add(new ColorPickerColorElement(this.scale, this.setting.<Integer>value()));
        Ref.getMinecraft().bridge$getSoundHandler().bridge$playSound(Ref.getInstanceCreator().createSoundFromPSR(Ref.getInstanceCreator().createResourceLocation("gui.button.press"), 1.0f));
    }

    private void lIIIIIIIIIlIllIIllIlIIlIl(int n2) {
        Ref.modified$drawRect(this.pickerX + this.pickerWidth + (float)18, this.pickerY - 1.0f, this.pickerX + this.pickerWidth + (float)28, this.pickerY + 1.0f + this.pickerHeight, -822083584);
        this.IlllIIIlIlllIllIlIIlllIlI();
        int n3 = 0;
        while ((float)n3 < this.pickerHeight) {
            int n4 = this.setting.<Integer>value();
            int n5 = new Color(n4 >> 16 & 0xFF, n4 >> 8 & 0xFF, n4 & 0xFF, Math.round((float)255 - (float)n3 / this.pickerHeight * (float)255)).getRGB();
            if (this.llIIlllIIIIlllIllIlIlllIl && (float)n2 >= ((float)this.yOffset + this.pickerY + (float)n3) * this.scale && (float)n2 <= ((float)this.yOffset + this.pickerY + (float)n3 + 1.0f) * this.scale) {
                this.llIlIIIlIIIIlIlllIlIIIIll = (float)n3 / this.pickerHeight;
                this.setting.setValue(n5);
            }
            Ref.modified$drawRect(this.pickerX + this.pickerWidth + (float)19, this.pickerY + (float)n3, this.pickerX + this.pickerWidth + (float)27, this.pickerY + (float)n3 + 1.0f, n5);
            ++n3;
        }
        float f = (float)-1 + this.pickerHeight * this.llIlIIIlIIIIlIlllIlIIIIll;
        Ref.modified$drawRect(this.pickerX + this.pickerWidth + (float)18, this.pickerY + f, this.pickerX + this.pickerWidth + (float)28, this.pickerY + f + (float)3, -822083584);
        Ref.modified$drawRect(this.pickerX + this.pickerWidth + (float)18, this.pickerY + f + 1.0f, this.pickerX + this.pickerWidth + (float)28, this.pickerY + f + 2.0f, -805306369);
    }

    private void IlllIIIlIlllIllIlIIlllIlI(int n2) {
        Ref.modified$drawRect(this.pickerX + this.pickerWidth + (float)4, this.pickerY - 1.0f, this.pickerX + this.pickerWidth + (float)14, this.pickerY + 1.0f + this.pickerHeight, -822083584);
        int n3 = 0;
        while ((float)n3 < this.pickerHeight) {
            int n4;
            if (this.IlIlllIIIIllIllllIllIIlIl && (float)n2 >= ((float)this.yOffset + this.pickerY + (float)n3) * this.scale && (float)n2 <= ((float)this.yOffset + this.pickerY + (float)n3 + 1.0f) * this.scale) {
                n4 = this.setting.<Integer>value();
                float[] arrf = Color.RGBtoHSB(n4 >> 16 & 0xFF, n4 >> 8 & 0xFF, n4 & 0xFF, null);
                this.setting.setValue(Color.HSBtoRGB(this.IIIlllIIIllIllIlIIIIIIlII, arrf[1], arrf[2]));
                this.IIIlllIIIllIllIlIIIIIIlII = (float)n3 / this.pickerHeight;
            }
            n4 = Color.HSBtoRGB((float)n3 / this.pickerHeight, 1.0f, 1.0f);
            Ref.modified$drawRect(this.pickerX + this.pickerWidth + (float)5, this.pickerY + (float)n3, this.pickerX + this.pickerWidth + (float)13, this.pickerY + (float)n3 + 1.0f, n4);
            ++n3;
        }
        float f = (float)-1 + this.pickerHeight * this.IIIlllIIIllIllIlIIIIIIlII;
        Ref.modified$drawRect(this.pickerX + this.pickerWidth + (float)4, this.pickerY + f, this.pickerX + this.pickerWidth + (float)14, this.pickerY + f + (float)3, -822083584);
        Ref.modified$drawRect(this.pickerX + this.pickerWidth + (float)4, this.pickerY + f + 1.0f, this.pickerX + this.pickerWidth + (float)14, this.pickerY + f + 2.0f, -805306369);
    }

    private void IlllIIIlIlllIllIlIIlllIlI() {
        boolean bl = true;
        int n = 2;
        while ((float)n < this.pickerHeight - (float)4) {
            if (!bl) {
                Ref.modified$drawRect(this.pickerX + this.pickerWidth + (float)19, this.pickerY + (float)n, this.pickerX + this.pickerWidth + (float)23, this.pickerY + (float)n + (float)4, -1);
                Ref.modified$drawRect(this.pickerX + this.pickerWidth + (float)23, this.pickerY + (float)n + (float)4, this.pickerX + this.pickerWidth + (float)27, this.pickerY + (float)n + (float)8, -1);
                Ref.modified$drawRect(this.pickerX + this.pickerWidth + (float)23, this.pickerY + (float)n, this.pickerX + this.pickerWidth + (float)27, this.pickerY + (float)n + (float)4, -7303024);
                Ref.modified$drawRect(this.pickerX + this.pickerWidth + (float)19, this.pickerY + (float)n + (float)4, this.pickerX + this.pickerWidth + (float)23, this.pickerY + (float)n + (float)8, -7303024);
            }
            bl = !bl;
            n += 4;
        }
    }

    private void drawColorList(List<ColorPickerColorElement> list, int n, int n2, int n3, int n4, int n5) {
        int n6 = 0;
        int n7 = 0;
        int n8 = 8;
        for (ColorPickerColorElement colorPickerColorElement : list) {
            colorPickerColorElement.scale = this.scale;
            if (n6 == n8) {
                ++n7;
                n6 = 0;
            }
            if (list == this.colors) {
                int n9 = n8 * 2 / 8 * 12;
                int var12_12 = n + n9 - n7 * 12 - 12;
                int var13_13 = n2 + n6 * n9 - n6 * 12;
                colorPickerColorElement.yOffset = this.yOffset;
                colorPickerColorElement.setDimensions(var12_12, var13_13, 10, 10);
                String string = "0123456789abcdefklmnor";
                int n10 = n6 + n7 * n8;
                String string2 = string.substring(n10, n10 + 1);
                if (colorPickerColorElement.isMouseInside(n3, n4)) {
                    Ref.modified$drawRect(var12_12 + 12, var13_13 - 1, var12_12 + 26, var13_13 + 11, -1087492562);
                    FontRegistry.getUbuntuMedium16px().drawString("&" + string2, (float) (var12_12 + 14), (float)var13_13, -1);
                }
            } else {
                int var12_12 = n + n7 * 12;
                int var13_13 = n2 + n6 * 12;
                colorPickerColorElement.yOffset = this.yOffset;
                colorPickerColorElement.setDimensions(var12_12, var13_13, 10, 10);
            }
            colorPickerColorElement.handleDrawElement(n3, n4, (float)n5);
            ++n6;
        }
    }

    private void lIIIIlIIllIIlIIlIIIlIIllI(List<ColorPickerColorElement> list, int n, int n2, int n3, int n4) {
        int n5 = 0;
        int n6 = 0;
        int n7 = 8;
        for (ColorPickerColorElement colorPickerColorElement : list) {
            int n8;
            if (list != this.colors) {
                if (n5 == n7) {
                    ++n6;
                    n5 = 0;
                }
                int n9 = n + n6 * 12;
                n8 = n2 + n5 * 12;
                colorPickerColorElement.yOffset = this.yOffset;
                //illllllllIlIIIIIIIIllIIII.IlllIIIlIlllIllIlIIlllIlI = this.IlllIIIlIlllIllIlIIlllIlI;
                colorPickerColorElement.setDimensions(n9, n8, 10, 10);
            }
            if (colorPickerColorElement.isMouseInside(n3, n4)) {
                if (list == this.colors) {
                    this.setting.setValue(new Color(colorPickerColorElement.color).getRGB());
                } else {
                    this.setting.setValue(new Color(colorPickerColorElement.color, true).getRGB());
                }
                Ref.getMinecraft().bridge$getSoundHandler().bridge$playSound(Ref.getInstanceCreator().createSoundFromPSR(Ref.getInstanceCreator().createResourceLocation("gui.button.press"), 1.0f));
                float[] arrf = Color.RGBtoHSB(colorPickerColorElement.color >> 16 & 0xFF, colorPickerColorElement.color >> 8 & 0xFF, colorPickerColorElement.color & 0xFF, null);
                this.IIIlllIIIllIllIlIIIIIIlII = arrf[0];
                n8 = (int)(arrf[1] * this.pickerWidth);
                int n10 = (int)(this.pickerHeight - arrf[2] * this.pickerHeight);
                this.setting.colorArray = new int[]{n8, n10};
            }
            ++n5;
        }
    }

    @Override
    public void handleMouseClick(int mouseX, int mouseY, int button) {
        boolean bl;
        boolean bl2 = (float) mouseX > (float)(this.x + this.width - 40) * this.scale && (float) mouseX < (float)(this.x + this.width - 12) * this.scale && (float) mouseY > (float)(this.y + this.yOffset) * this.scale && (float) mouseY < (float)(this.y + 18 + this.yOffset) * this.scale;
        bl = (float) mouseX > (float)this.x * this.scale && (float) mouseX < (float)(this.x + this.width - 40) * this.scale && (float) mouseY > (float)(this.y + this.yOffset) * this.scale && (float) mouseY < (float)(this.y + 18 + this.yOffset) * this.scale;
        if (bl) {
            Ref.getMinecraft().bridge$getSoundHandler().bridge$playSound(Ref.getInstanceCreator().createSoundFromPSR(Ref.getInstanceCreator().createResourceLocation("gui.button.press"), 1.0f));
            float[] arrf = Color.RGBtoHSB(this.setting.<Integer>value() >> 16 & 0xFF, this.setting.<Integer>value() >> 8 & 0xFF, this.setting.<Integer>value() & 0xFF, null);
            this.IIIlllIIIllIllIlIIIIIIlII = arrf[0];
            int n4 = (int)(arrf[1] * this.pickerWidth);
            int n5 = (int)(this.pickerHeight - arrf[2] * this.pickerHeight);
            this.setting.colorArray = new int[]{n4, n5};
            this.lIIIIllIIlIlIllIIIlIllIlI = !this.lIIIIllIIlIlIllIIIlIllIlI;
        } else if (bl2) {
            if (CheatBreaker.getInstance().globalSettings.isFavouriteColor(this.setting.<Integer>value())) {
                CheatBreaker.getInstance().globalSettings.removeFavouriteColor(this.setting.<Integer>value());
            } else {
                if (CheatBreaker.getInstance().globalSettings.favouriteColors.size() >= 16) {
                    CheatBreaker.getInstance().globalSettings.favouriteColors.remove(0);
                }
                CheatBreaker.getInstance().globalSettings.favouriteColors.add(new ColorPickerColorElement(this.scale, this.setting.<Integer>value()));
            }
            Ref.getMinecraft().bridge$getSoundHandler().bridge$playSound(Ref.getInstanceCreator().createSoundFromPSR(Ref.getInstanceCreator().createResourceLocation("gui.button.press"), 1.0f));
        } else if (this.lIIIIllIIlIlIllIIIlIllIlI) {
            boolean bl4;
            this.lIIIIlIIllIIlIIlIIIlIIllI(this.colors, 0, 0, mouseX, mouseY);
            this.lIIIIlIIllIIlIIlIIIlIIllI(CheatBreaker.getInstance().globalSettings.IlIIlIIlIllIIIIllIIllIlIl, this.lIIlIIllIIIIIlIllIIIIllII, this.lIIlllIIlIlllllllllIIIIIl, mouseX, mouseY);
            this.lIIIIlIIllIIlIIlIIIlIIllI(CheatBreaker.getInstance().globalSettings.favouriteColors, this.lIllIllIlIIllIllIlIlIIlIl, this.llIlIIIllIIIIlllIlIIIIIlI, mouseX, mouseY);
            bl4 = (float) mouseX > (this.pickerX - (float)51) * this.scale && (float) mouseY > (this.pickerY + 1.0f + (float)this.yOffset) * this.scale && (float) mouseX < (this.pickerX - (float)43) * this.scale && (float) mouseY < (this.pickerY + (float)9 + (float)this.yOffset) * this.scale;
            if ((float) mouseX > this.pickerX * this.scale && (float) mouseX < (this.pickerX + this.pickerWidth) * this.scale && (float) mouseY > (this.pickerY + (float)this.yOffset) * this.scale && (float) mouseY < (this.pickerY + this.pickerHeight + (float)this.yOffset) * this.scale) {
                this.IlllIllIlIIIIlIIlIIllIIIl = true;
            }
            if ((float) mouseX > (this.pickerX + this.pickerWidth + (float)4) * this.scale && (float) mouseX < (this.pickerX + this.pickerWidth + (float)14) * this.scale && (float) mouseY > (this.pickerY - 1.0f + (float)this.yOffset) * this.scale && (float) mouseY < (this.pickerY + 1.0f + this.pickerHeight + (float)this.yOffset) * this.scale) {
                this.IlIlllIIIIllIllllIllIIlIl = true;
            }
            if ((float) mouseX > (this.pickerX + this.pickerWidth + (float)18) * this.scale && (float) mouseX < (this.pickerX + this.pickerWidth + (float)28) * this.scale && (float) mouseY > (this.pickerY - 1.0f + (float)this.yOffset) * this.scale && (float) mouseY < (this.pickerY + 1.0f + this.pickerHeight + (float)this.yOffset) * this.scale) {
                this.llIIlllIIIIlllIllIlIlllIl = true;
            }
            if (bl4) {
                Ref.getMinecraft().bridge$getSoundHandler().bridge$playSound(Ref.getInstanceCreator().createSoundFromPSR(Ref.getInstanceCreator().createResourceLocation("gui.button.press"), 1.0f));
                this.setting.rainbow = !this.setting.rainbow;
            }
        }
    }
}
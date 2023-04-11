package com.cheatbreaker.client.ui.util.font;

import java.util.ArrayList;
import java.util.List;

import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.bridge.util.ResourceLocationBridge;
import com.cheatbreaker.main.CheatBreaker;
import org.lwjgl.opengl.GL11;

public class CBXFontRenderer extends CBXFont {
    private char COLOR_CODE_START = (char)167;
    protected CBXFont.CharData[] boldItalicChars = new CBXFont.CharData[256];
    protected CBXFont.CharData[] italicChars = new CBXFont.CharData[256];
    protected CBXFont.CharData[] boldChars = new CBXFont.CharData[256];
    private final int[] colorCode = new int[32];
    private final String validColorCodes = "0123456789abcdefklmnor";
    protected int texBold;
    protected int texItalic;
    protected int texItalicBold;

    public CBXFontRenderer(ResourceLocationBridge location, float size) {
        super(location, size);
    }

    @Override
    public boolean isLoaded() {
        super.isLoaded();
        this.setupMinecraftColorCodes();
        this.setupBoldItalicIDs();
        return true;
    }

    public float drawCenteredStringWithShadow(String string, double d, double d2, int n, int n2) {
        float f = this.drawString(string, d + 1.0, d2 + 1.0, n2, false);
        return Math.max(f, this.drawString(string, d, d2, n, false));
    }

    public float drawStringWithShadow(String string, double d, double d2, int n) {
        float f = this.drawString(string, d + 1.0, d2 + 1.0, n, true);
        return Math.max(f, this.drawString(string, d, d2, n, false));
    }

    public float drawRightAlignedStringWithShadow(String text, double x, double y, int color) {
        double newX = x - this.getStringWidth(text);
        return this.drawStringWithShadow(text, newX, y, color);
    }

    public float drawString(String string, float f, float f2, int n) {
        return this.drawString(string, f, f2, n, false);
    }

    public float drawCenteredString(String string, float f, float f2, int n) {
        return this.drawString(string, f - (float)this.getStringWidth(string) / 2.0f, f2, n);
    }

    public float drawCenteredStringWithShadow(String string, float f, float f2, int n) {
        float f3 = this.drawString(string, (double)(f - (float)this.getStringWidth(string) / 2.0f) + 1.0, (double)f2 + 1.0, n, true);
        return this.drawString(string, f - (float)this.getStringWidth(string) / 2.0f, f2, n);
    }

    public float drawString(String string, double x, double y, int n, boolean bl) {
        // START SCALE FIX (drawString HEAD)
        float scaleFactor = Ref.getInstanceCreator().createScaledResolution().bridge$getScaleFactor();
        float scale = (2.0f / scaleFactor);

        boolean followMinecraftScale = false;

        try {
            followMinecraftScale = (Boolean) CheatBreaker.getInstance().globalSettings.followMinecraftScale.getValue();
        } catch (Exception ignored) {

        }

        if (followMinecraftScale) {
            Ref.getGlBridge().bridge$scale(scale, scale, scale);
            x *= (scaleFactor / 2.0f);
            y *= (scaleFactor / 2.0f);
        }
        // END SCALE FIX (drawString HEAD)

        x -= 1.0;

        if (string == null) {
            return 0.0f;
        }
        if (n == 0x20FFFFFF) {
            n = 0xFFFFFF;
        }
        if ((n & 0xFC000000) == 0) {
            n |= 0xFF000000;
        }
        if (bl) {
            n = (n & 0xFCFCFC) >> 2 | n & 0xFF000000;
        }

        CBXFont.CharData[] charDataArray = this.charData;
        float f = (float)(n >> 24 & 0xFF) / 255.0f;

        boolean bl2 = false;
        boolean bl3 = false;
        boolean bl4 = false;
        boolean bl5 = false;
        boolean bl6 = false;
        boolean render = true;

        x *= 2.0;
        y = (y - 3d) * 2d;

        if (render) {
            Ref.getGlBridge().bridge$pushMatrix();
            Ref.getGlBridge().bridge$scale(0.5f, 0.5f, 0.5f);
            Ref.getGlBridge().bridge$enableBlend();
            Ref.getGlBridge().bridge$blendFunc(770, 771);
            Ref.getGlBridge().bridge$color((float)(n >> 16 & 0xFF) / 255.0f, (float)(n >> 8 & 0xFF) / 255.0f, (float)(n & 0xFF) / 255.0f, f);
            int n2 = string.length();
            Ref.getGlBridge().bridge$enableTexture2D();
            Ref.getGlBridge().bridge$bindTexture(this.tex);
            for (int i = 0; i < n2; ++i) {
                char c = string.charAt(i);
                if (c == this.COLOR_CODE_START && i < n2) {
                    int n3 = 21;
                    try {
                        n3 = "0123456789abcdefklmnor".indexOf(string.charAt(i + 1));
                    }
                    catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    if (n3 < 16) {
                        bl3 = false;
                        bl4 = false;
                        bl2 = false;
                        bl6 = false;
                        bl5 = false;
                        Ref.getGlBridge().bridge$bindTexture(this.tex);
                        charDataArray = this.charData;
                        if (n3 < 0 || n3 > 15) {
                            n3 = 15;
                        }
                        if (bl) {
                            n3 += 16;
                        }
                        int n4 = this.colorCode[n3];
                        Ref.getGlBridge().bridge$color((float)(n4 >> 16 & 0xFF) / 255.0f, (float)(n4 >> 8 & 0xFF) / 255.0f, (float)(n4 & 0xFF) / 255.0f, f);
                    } else if (n3 == 16) {
                        bl2 = true;
                    } else if (n3 == 17) {
                        bl3 = true;
                        if (bl4) {
                            Ref.getGlBridge().bridge$bindTexture(this.texItalicBold);
                            charDataArray = this.boldChars;
                        } else {
                            Ref.getGlBridge().bridge$bindTexture(this.texBold);
                            charDataArray = this.boldItalicChars;
                        }
                    } else if (n3 == 18) {
                        bl5 = true;
                    } else if (n3 == 19) {
                        bl6 = true;
                    } else if (n3 == 20) {
                        bl4 = true;
                        if (bl3) {
                            Ref.getGlBridge().bridge$bindTexture(this.texItalicBold);
                            charDataArray = this.boldChars;
                        } else {
                            Ref.getGlBridge().bridge$bindTexture(this.texItalic);
                            charDataArray = this.italicChars;
                        }
                    } else if (n3 == 21) {
                        bl3 = false;
                        bl4 = false;
                        bl2 = false;
                        bl6 = false;
                        bl5 = false;
                        Ref.getGlBridge().bridge$color((float)(n >> 16 & 0xFF) / 255.0f, (float)(n >> 8 & 0xFF) / 255.0f, (float)(n & 0xFF) / 255.0f, f);
                        Ref.getGlBridge().bridge$bindTexture(this.tex);
                        charDataArray = this.charData;
                    }
                    ++i;
                    continue;
                }
                if (c >= charDataArray.length || c < '\u0000') continue;
                this.drawChar(charDataArray, c, (float)x, (float)y + 6.0f, n);
                if (bl5) {
                    this.drawLine(x, y + (double)(charDataArray[c].height / 2), x + (double)charDataArray[c].width - 8.0, y + (double)(charDataArray[c].height / 2), 1.0f);
                }
                if (bl6) {
                    this.drawLine(x, y + (double)charDataArray[c].height - 2.0, x + (double)charDataArray[c].width - 8.0, y + (double)charDataArray[c].height - 2.0, 1.0f);
                }
                x += charDataArray[c].width - 8 + this.charOffset;
            }
            GL11.glHint(3155, 4352);
            Ref.getGlBridge().bridge$color(1.0f, 1.0f, 1.0f, 1.0f);
            Ref.getGlBridge().bridge$popMatrix();
        }


        // START SCALE FIX (drawString RETURN)
        if (followMinecraftScale) {
            Ref.getGlBridge().bridge$scale(scaleFactor / 2.0f, scaleFactor / 2.0f, scaleFactor / 2.0f);
        }
        // END SCALE FIX (drawString RETURN)

        return (float) x / 2f;
    }

    public String setWrapWords(String string, double d) {
        return this.wrapWords(string, d, false);
    }

    public String wrapWords(String string, double d, boolean bl) {
        StringBuilder stringBuilder = new StringBuilder();
        float f = 0.0f;
        int n = bl ? string.length() - 1 : 0;
        int n2 = bl ? -1 : 1;
        boolean bl2 = false;
        boolean bl3 = false;
        for (int i = n; i >= 0 && i < string.length() && f < (float)d; i += n2) {
            char c = string.charAt(i);
            double d2 = this.getStringWidth(String.valueOf(c));
            if (bl2) {
                bl2 = false;
                if (c != 'l' && c != 'L') {
                    if (c == 'r' || c == 'R') {
                        bl3 = false;
                    }
                } else {
                    bl3 = true;
                }
            } else if (d2 < 0.0) {
                bl2 = true;
            } else {
                f = (float)((double)f + d2);
                if (bl3) {
                    f += 1.0f;
                }
            }
            if (f > (float)d) break;
            if (bl) {
                stringBuilder.insert(0, c);
                continue;
            }
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    @Override
    public int getStringWidth(String string) {
        if (string == null) {
            return 0;
        }
        int width = 0;
        CBXFont.CharData[] charDataArray = this.charData;
        boolean bl = false;
        boolean bl2 = false;
        int n2 = string.length();
        for (int i = 0; i < n2; ++i) {
            CBXFont.CharData charData;
            char c = string.charAt(i);
            if (c == this.COLOR_CODE_START && i < n2) {
                int n3 = "0123456789abcdefklmnor".indexOf(c);
                if (n3 < 16) {
                    bl = false;
                    bl2 = false;
                } else if (n3 == 17) {
                    bl = true;
                    charDataArray = bl2 ? this.boldChars : this.boldItalicChars;
                } else if (n3 == 20) {
                    bl2 = true;
                    charDataArray = bl ? this.boldChars : this.italicChars;
                } else if (n3 == 21) {
                    bl = false;
                    bl2 = false;
                    charDataArray = this.charData;
                }
                ++i;
                continue;
            }
            if (c >= charDataArray.length || c < '\u0000' || (charData = charDataArray[c]) == null) continue;
            width += charData.width - 8 + this.charOffset;
        }

        // START SCALE FIX (getStringWidth)
        boolean followMinecraftScale = false;

        try {
            followMinecraftScale = (Boolean) CheatBreaker.getInstance().globalSettings.followMinecraftScale.getValue();
        } catch (Exception ignored) {

        }

        if (followMinecraftScale) {
            return (int) ((width / 2) * (2.0f / Ref.getInstanceCreator().createScaledResolution().bridge$getScaleFactor()));
        } else {
            return width / 2;
        }
        // END SCALE FIX (getStringWidth)
    }

    @Override
    public void setAntiAlias(boolean bl) {
        super.setAntiAlias(bl);
        this.setupBoldItalicIDs();
    }

    @Override
    public void setFractionalMetrics(boolean bl) {
        super.setFractionalMetrics(bl);
        this.setupBoldItalicIDs();
    }

    private void setupBoldItalicIDs() {
        Ref.getImplementations().getTextureUtil().bridge$deleteTexture(this.texBold);
        Ref.getImplementations().getTextureUtil().bridge$deleteTexture(this.texItalic);
        Ref.getImplementations().getTextureUtil().bridge$deleteTexture(this.texItalicBold);
        this.texBold = this.setupTexture(this.font.deriveFont(1), this.antiAlias, this.fractionalMetrics, this.boldItalicChars);
        this.texItalic = this.setupTexture(this.font.deriveFont(2), this.antiAlias, this.fractionalMetrics, this.italicChars);
        this.texItalicBold = this.setupTexture(this.font.deriveFont(3), this.antiAlias, this.fractionalMetrics, this.boldChars);
    }

    private void drawLine(double x, double y, double x1, double y1, float lineWidth) {
        Ref.getGlBridge().bridge$disableTexture2D();
        Ref.getGlBridge().bridge$lineWidth(lineWidth);
        Ref.getGlBridge().bridge$begin(1); // GL_LINES
        Ref.getGlBridge().bridge$vertex2d(x, y);
        Ref.getGlBridge().bridge$vertex2d(x1, y1);
        Ref.getGlBridge().bridge$end();
        Ref.getGlBridge().bridge$enableTexture2D();
    }

    public String formatText(String string, double d) {
        /*StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();
        boolean bl = false;
        for (char c : string.toCharArray()) {
            String string2;
            String string3;
            if (bl) {
                stringBuilder.append(c);
                bl = false;
                continue;
            }
            if (c == '§') {
                stringBuilder.append(c);
                bl = true;
                continue;
            }
            stringBuilder.append(c);
            int n = this.getStringWidth(stringBuilder.toString());
            if (!((double)n >= d)) continue;
            String string4 = stringBuilder.toString();
            if (string4.contains(" ")) {
                string3 = string4.substring(0, string4.lastIndexOf(" "));
                string2 = string4.substring(string4.lastIndexOf(" "), string4.length());
                if (string2.startsWith(" ")) {
                    string2 = string2.replaceFirst(" ", "");
                }
            } else {
                string3 = string4.substring(0, string4.length() - 1);
                string2 = string4.substring(string4.length() - 1, string4.length());
            }
            stringBuilder2.append(string3).append("\n");
            String string5 = ForgeI18n.findRecentColor(stringBuilder.toString());
            stringBuilder.setLength(0);
            stringBuilder.append(string2).append(string5);
        }
        stringBuilder2.append((CharSequence)stringBuilder);
        return stringBuilder2.length() == 0 ? string : stringBuilder2.toString();*/
        return "";
    }

    public List<String> wrapWords(String string, double d) {
        /*
        ArrayList<String> arrayList = new ArrayList<String>();
        if ((double)this.getStringWidth(string) > d || string.contains("\n")) {
            String[] stringArray = string.split(" ");
            Object object = "";
            String string2 = "";
            for (String string3 : stringArray) {
                if (string3.equals("\n")) {
                    arrayList.add((String)object);
                    object = "";
                }
                if ((double)this.getStringWidth((String)object + string3 + " ") < d) {
                    object = (String)object + string3 + " ";
                } else {
                    arrayList.add((String)object);
                    object = string2 + string3 + " ";
                }
                String string4 = ChatColor.findRecentColor(string3);
                if (string4.equals("")) continue;
                string2 = string4;
            }
            if (((String)object).length() > 0) {
                if ((double)this.getStringWidth((String)object) < d) {
                    arrayList.add((String)object + " ");
                    object = "";
                } else {
                    for (String string5 : this.formatString((String)object, d)) {
                        arrayList.add(string5);
                    }
                }
            }
        } else {
            arrayList.add(string);
        }
        return arrayList;*/
        return new ArrayList<>();
    }

    public List<String> formatString(String string, double d) {
        ArrayList<String> arrayList = new ArrayList<String>();
        StringBuilder stringBuilder = new StringBuilder();
        char c = 'F';
        char[] cArray = string.toCharArray();
        for (int i = 0; i < cArray.length; ++i) {
            char c2 = cArray[i];
            if (c2 == this.COLOR_CODE_START && i < cArray.length - 1) {
                c = cArray[i + 1];
            }
            if ((double)this.getStringWidth(stringBuilder.toString() + c2) < d) {
                stringBuilder.append(c2);
                continue;
            }
            arrayList.add(stringBuilder.toString());
            stringBuilder = new StringBuilder("" + this.COLOR_CODE_START + c + c2);
        }
        if (stringBuilder.length() > 0) {
            arrayList.add(stringBuilder.toString());
        }
        return arrayList;
    }

    private void setupMinecraftColorCodes() {
        for (int i = 0; i < 32; ++i) {
            int n = (i >> 3 & 1) * 85;
            int n2 = (i >> 2 & 1) * 170 + n;
            int n3 = (i >> 1 & 1) * 170 + n;
            int n4 = (i & 1) * 170 + n;
            if (i == 6) {
                n2 += 85;
            }
            if (i >= 16) {
                n2 /= 4;
                n3 /= 4;
                n4 /= 4;
            }
            this.colorCode[i] = (n2 & 0xFF) << 16 | (n3 & 0xFF) << 8 | n4 & 0xFF;
        }
    }
}

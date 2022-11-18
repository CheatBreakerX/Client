package com.cheatbreaker.client.ui.util.font;

import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.bridge.util.ResourceLocationBridge;
import com.cheatbreaker.client.CheatBreaker;

public class FontRegistry {
    private static CBFontRenderer createNewFont(ResourceLocationBridge font, float size) {
        return new CBFontRenderer(font, size);
    }

    private static boolean validFont(CBFontRenderer font, int ogSize) {
        if ((Boolean) CheatBreaker.getInstance().globalSettings.followMinecraftScale.getValue()) {
            float realSize = (Ref.getInstanceCreator().createScaledResolution().bridge$getScaleFactor() / 2f) * ogSize;
            return realSize == font.dbg_fontSize;
        } else {
            return ogSize == font.dbg_fontSize;
        }
    }

    private static CBFontRenderer getFont(String fontName, int ogSize) {
        float scale = (Ref.getInstanceCreator().createScaledResolution().bridge$getScaleFactor() / 2f);
        if (!(Boolean) CheatBreaker.getInstance().globalSettings.followMinecraftScale.getValue()) {
            scale = 1f;
        }

        switch (fontName) {
            case "Play-Regular":
                switch (ogSize) {
                    case 12:
                        if (!validFont(playRegular12px, ogSize)) {
                            playRegular12px = createNewFont(playRegular, ogSize * scale);
                        }
                        return playRegular12px;
                    case 14:
                        if (!validFont(playRegular14px, ogSize)) {
                            playRegular14px = createNewFont(playRegular, ogSize * scale);
                        }
                        return playRegular14px;
                    case 16:
                        if (!validFont(playRegular16px, ogSize)) {
                            playRegular16px = createNewFont(playRegular, ogSize * scale);
                        }
                        return playRegular16px;
                    case 18:
                        if (!validFont(playRegular18px, ogSize)) {
                            playRegular18px = createNewFont(playRegular, ogSize * scale);
                        }
                        return playRegular18px;
                    case 22:
                        if (!validFont(playRegular22px, ogSize)) {
                            playRegular22px = createNewFont(playRegular, ogSize * scale);
                        }
                        return playRegular22px;
                }
            break;
            case "Play-Bold":
                switch (ogSize) {
                    case 18:
                        if (!validFont(playBold18px, ogSize)) {
                            playBold18px = createNewFont(playBold, ogSize * scale);
                        }
                        return playBold18px;
                    case 22:
                        if (!validFont(playBold22px, ogSize)) {
                            playBold22px = createNewFont(playBold, ogSize * scale);
                        }
                        return playBold22px;
                }
            break;
            case "Roboto-Regular":
                switch (ogSize) {
                    case 13:
                        if (!validFont(robotoRegular13px, ogSize)) {
                            robotoRegular13px = createNewFont(robotoRegular, ogSize * scale);
                        }
                        return robotoRegular13px;
                    case 24:
                        if (!validFont(robotoRegular24px, ogSize)) {
                            robotoRegular24px = createNewFont(robotoRegular, ogSize * scale);
                        }
                        return robotoRegular24px;
                }
            break;
            case "Roboto-Bold":
                switch (ogSize) {
                    case 14:
                        if (!validFont(robotoBold14px, ogSize)) {
                            robotoBold14px = createNewFont(robotoBold, ogSize * scale);
                        }
                        return robotoBold14px;
                }
            break;
            case "Ubuntu-M":
                switch (ogSize) {
                    case 16:
                        if (!validFont(ubuntuMedium16px, ogSize)) {
                            ubuntuMedium16px = createNewFont(ubuntuMedium, ogSize * scale);
                        }
                        return ubuntuMedium16px;
                }
            break;
        }

        // This should never happen BUT in the off chance it does
        return new CBFontRenderer(Ref.getInstanceCreator().createResourceLocationBridge("null"), 20);
    }

    // Textures
    private static final ResourceLocationBridge playRegular = Ref.getInstanceCreator().createResourceLocationBridge("client/font/Play-Regular.ttf");
    private static final ResourceLocationBridge playBold = Ref.getInstanceCreator().createResourceLocationBridge("client/font/Play-Bold.ttf");
    private static final ResourceLocationBridge robotoRegular = Ref.getInstanceCreator().createResourceLocationBridge("client/font/Roboto-Regular.ttf");
    private static final ResourceLocationBridge robotoBold = Ref.getInstanceCreator().createResourceLocationBridge("client/font/Roboto-Bold.ttf");
    private static final ResourceLocationBridge ubuntuMedium = Ref.getInstanceCreator().createResourceLocationBridge("client/font/Ubuntu-M.ttf");

    // Font objects
    private static CBFontRenderer playRegular12px = createNewFont(playRegular, 12);
    private static CBFontRenderer playRegular14px = createNewFont(playRegular, 14);
    private static CBFontRenderer playRegular16px = createNewFont(playRegular, 16);
    private static CBFontRenderer playRegular18px = createNewFont(playRegular, 18);
    private static CBFontRenderer playRegular22px = createNewFont(playRegular, 22);
    private static CBFontRenderer playBold18px = createNewFont(playBold, 18);
    private static CBFontRenderer playBold22px = createNewFont(playBold, 22);
    private static CBFontRenderer robotoRegular13px = createNewFont(robotoRegular, 13);
    private static CBFontRenderer robotoRegular24px = createNewFont(robotoRegular, 24);
    private static CBFontRenderer robotoBold14px = createNewFont(robotoBold, 14);
    private static CBFontRenderer ubuntuMedium16px = createNewFont(ubuntuMedium, 16);

    // Getters
    public static CBFontRenderer getPlayRegular12px() {
        return getFont("Play-Regular", 12);
    }

    public static CBFontRenderer getPlayRegular14px() {
        return getFont("Play-Regular", 14);
    }

    public static CBFontRenderer getPlayRegular16px() {
        return getFont("Play-Regular", 16);
    }

    public static CBFontRenderer getPlayRegular18px() {
        return getFont("Play-Regular", 18);
    }

    public static CBFontRenderer getPlayRegular22px() {
        return getFont("Play-Regular", 22);
    }

    public static CBFontRenderer getPlayBold18px() {
        return getFont("Play-Bold", 18);
    }

    public static CBFontRenderer getPlayBold22px() {
        return getFont("Play-Bold", 22);
    }

    public static CBFontRenderer getRobotoRegular13px() {
        return getFont("Roboto-Regular", 13);
    }

    public static CBFontRenderer getRobotoRegular24px() {
        return getFont("Roboto-Regular", 24);
    }

    public static CBFontRenderer getRobotoBold14px() {
        return getFont("Roboto-Bold", 14);
    }

    public static CBFontRenderer getUbuntuMedium16px() {
        return getFont("Ubuntu-M", 16);
    }
}

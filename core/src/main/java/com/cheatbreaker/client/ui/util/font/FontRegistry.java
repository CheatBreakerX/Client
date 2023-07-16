package com.cheatbreaker.client.ui.util.font;

import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.bridge.util.ResourceLocationBridge;
import com.cheatbreaker.main.CheatBreaker;
import com.cheatbreaker.main.utils.Utility;

public class FontRegistry {
    private static int fixFontSize(int size, float scale) {
        if (scale - ((int) scale) == 0f) { // if scale == 1 (mc:2), == 2 (mc:4), == 3 (mc:6), and so on
            return size * (int) scale;
        }

        float scaledSize = size * scale;
        if (scaledSize - ((int) scaledSize) == 0) {
            if (scaledSize % 2 == 0) {
                return (int) scaledSize;
            }
        }

        int castedSize = (int) scaledSize - 1;
        float sizeRemaining = (size - 1) - castedSize;

        if (sizeRemaining >= 0.5f) {
            castedSize += 1;
        }

        if (castedSize % 2 != 0) {
            castedSize += 1;
        }

        return castedSize;
    }

    private static CBXFontRenderer createNewFont(ResourceLocationBridge font, int size, float scale) {
        int castedSize = fixFontSize(size, scale);
        CheatBreaker.LOGGER.info(Utility.fmt("{} {} -> {}", font.bridge$getResourcePath(), size, castedSize));
        CBXFontRenderer instance = new CBXFontRenderer(font, CheatBreaker.getInstance().globalSettings
                .followMinecraftScale.<Boolean>value() ? castedSize : size * scale);
        if (instance.isLoaded()) {
            return instance;
        } else {
            return null;
        }
    }

    private static boolean validFont(CBXFontRenderer font, int ogSize) {
        if (CheatBreaker.getInstance().globalSettings.followMinecraftScale.<Boolean>value()) {
            float scale = (Ref.getInstanceCreator().createScaledResolution().bridge$getScaleFactor() / 2f);
            return fixFontSize(ogSize, scale) == font.size;
        } else {
            return ogSize == font.size;
        }
    }

    private static CBXFontRenderer getFont(String fontName, int ogSize) {
        float scale = (Ref.getInstanceCreator().createScaledResolution().bridge$getScaleFactor() / 2f);
        if (!CheatBreaker.getInstance().globalSettings.followMinecraftScale.<Boolean>value()) {
            scale = 1f;
        }

        switch (fontName) {
            case "Play-Regular":
                switch (ogSize) {
                    case 12:
                        if (!validFont(playRegular12px, ogSize)) {
                            playRegular12px = createNewFont(playRegular, ogSize, scale);
                        }
                        return playRegular12px;
                    case 14:
                        if (!validFont(playRegular14px, ogSize)) {
                            playRegular14px = createNewFont(playRegular, ogSize, scale);
                        }
                        return playRegular14px;
                    case 16:
                        if (!validFont(playRegular16px, ogSize)) {
                            playRegular16px = createNewFont(playRegular, ogSize, scale);
                        }
                        return playRegular16px;
                    case 18:
                        if (!validFont(playRegular18px, ogSize)) {
                            playRegular18px = createNewFont(playRegular, ogSize, scale);
                        }
                        return playRegular18px;
                    case 22:
                        if (!validFont(playRegular22px, ogSize)) {
                            playRegular22px = createNewFont(playRegular, ogSize, scale);
                        }
                        return playRegular22px;
                }
            break;
            case "Play-Bold":
                switch (ogSize) {
                    case 18:
                        if (!validFont(playBold18px, ogSize)) {
                            playBold18px = createNewFont(playBold, ogSize, scale);
                        }
                        return playBold18px;
                    case 22:
                        if (!validFont(playBold22px, ogSize)) {
                            playBold22px = createNewFont(playBold, ogSize, scale);
                        }
                        return playBold22px;
                }
            break;
            case "Roboto-Regular":
                switch (ogSize) {
                    case 13:
                        if (!validFont(robotoRegular13px, ogSize)) {
                            robotoRegular13px = createNewFont(robotoRegular, ogSize, scale);
                        }
                        return robotoRegular13px;
                    case 24:
                        if (!validFont(robotoRegular24px, ogSize)) {
                            robotoRegular24px = createNewFont(robotoRegular, ogSize, scale);
                        }
                        return robotoRegular24px;
                }
            break;
            case "Roboto-Bold":
                switch (ogSize) {
                    case 14:
                        if (!validFont(robotoBold14px, ogSize)) {
                            robotoBold14px = createNewFont(robotoBold, ogSize, scale);
                        }
                        return robotoBold14px;
                }
            break;
            case "Ubuntu-M":
                switch (ogSize) {
                    case 16:
                        if (!validFont(ubuntuMedium16px, ogSize)) {
                            ubuntuMedium16px = createNewFont(ubuntuMedium, ogSize, scale);
                        }
                        return ubuntuMedium16px;
                }
            break;
        }

        // This should never happen BUT in the off chance it does
        return new CBXFontRenderer(Ref.getInstanceCreator().createResourceLocation("null"), 20f);
    }

    // Textures
    private static final ResourceLocationBridge playRegular = Ref.getInstanceCreator().createResourceLocation("client/font/play/regular.ttf");
    private static final ResourceLocationBridge playBold = Ref.getInstanceCreator().createResourceLocation("client/font/play/bold.ttf");
    private static final ResourceLocationBridge robotoRegular = Ref.getInstanceCreator().createResourceLocation("client/font/roboto/regular.ttf");
    private static final ResourceLocationBridge robotoBold = Ref.getInstanceCreator().createResourceLocation("client/font/roboto/bold.ttf");
    private static final ResourceLocationBridge ubuntuMedium = Ref.getInstanceCreator().createResourceLocation("client/font/ubuntu/medium.ttf");

    // Font objects
    private static CBXFontRenderer playRegular12px = createNewFont(playRegular, 12, 1f);
    private static CBXFontRenderer playRegular14px = createNewFont(playRegular, 14, 1f);
    private static CBXFontRenderer playRegular16px = createNewFont(playRegular, 16, 1f);
    private static CBXFontRenderer playRegular18px = createNewFont(playRegular, 18, 1f);
    private static CBXFontRenderer playRegular22px = createNewFont(playRegular, 22, 1f);
    private static CBXFontRenderer playBold18px = createNewFont(playBold, 18, 1f);
    private static CBXFontRenderer playBold22px = createNewFont(playBold, 22, 1f);
    private static CBXFontRenderer robotoRegular13px = createNewFont(robotoRegular, 13, 1f);
    private static CBXFontRenderer robotoRegular24px = createNewFont(robotoRegular, 24, 1f);
    private static CBXFontRenderer robotoBold14px = createNewFont(robotoBold, 14, 1f);
    private static CBXFontRenderer ubuntuMedium16px = createNewFont(ubuntuMedium, 16, 1f);

    // Getters
    public static CBXFontRenderer getPlayRegular12px() {
        return getFont("Play-Regular", 12);
    }

    public static CBXFontRenderer getPlayRegular14px() {
        return getFont("Play-Regular", 14);
    }

    public static CBXFontRenderer getPlayRegular16px() {
        return getFont("Play-Regular", 16);
    }

    public static CBXFontRenderer getPlayRegular18px() {
        return getFont("Play-Regular", 18);
    }

    public static CBXFontRenderer getPlayRegular22px() {
        return getFont("Play-Regular", 22);
    }

    public static CBXFontRenderer getPlayBold18px() {
        return getFont("Play-Bold", 18);
    }

    public static CBXFontRenderer getPlayBold22px() {
        return getFont("Play-Bold", 22);
    }

    public static CBXFontRenderer getRobotoRegular13px() {
        return getFont("Roboto-Regular", 13);
    }

    public static CBXFontRenderer getRobotoRegular24px() {
        return getFont("Roboto-Regular", 24);
    }

    public static CBXFontRenderer getRobotoBold14px() {
        return getFont("Roboto-Bold", 14);
    }

    public static CBXFontRenderer getUbuntuMedium16px() {
        return getFont("Ubuntu-M", 16);
    }
}

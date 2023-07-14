package com.cheatbreaker.impl.ref.statics;

import com.cheatbreaker.bridge.ref.implementations.types.TextureUtilBridge;
import com.cheatbreaker.util.Utils;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.blaze3d.platform.TextureUtil;

import java.awt.image.BufferedImage;

public class TextureUtilBridgeImpl implements TextureUtilBridge {
    public int bridge$glGenTextures() {
        return GlStateManager.genTexture();
    }

    public void bridge$uploadTextureImage(int id, BufferedImage bufferedImage) {
        NativeImage nativeImage = Utils.bufferedImageToNativeImage(bufferedImage);

        TextureUtil.prepareImage(id, nativeImage.getWidth(), nativeImage.getHeight());
        nativeImage.upload(0, 0, 0, false);
    }

    public void bridge$deleteTexture(int n) {
        GlStateManager.deleteTexture(n);
    }
}

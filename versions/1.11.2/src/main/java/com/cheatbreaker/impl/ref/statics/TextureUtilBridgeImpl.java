package com.cheatbreaker.impl.ref.statics;

import com.cheatbreaker.bridge.ref.implementations.types.TextureUtilBridge;
import net.minecraft.client.renderer.texture.TextureUtil;

import java.awt.image.BufferedImage;

public class TextureUtilBridgeImpl implements TextureUtilBridge {
    public int bridge$glGenTextures() {
        return TextureUtil.glGenTextures();
    }

    public void bridge$uploadTextureImage(int n, BufferedImage bufferedImage) {
        TextureUtil.uploadTextureImage(n, bufferedImage);
    }

    public void bridge$deleteTexture(int n) {
        TextureUtil.deleteTexture(n);
    }
}

package com.cheatbreaker.bridge.ref.implementations.types;

import java.awt.image.BufferedImage;

public interface TextureUtilBridge {
    int bridge$glGenTextures();
    void bridge$uploadTextureImage(int n, BufferedImage bufferedImage);
    void bridge$deleteTexture(int n);
}

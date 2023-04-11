package com.cheatbreaker.bridge.client.resources;

import com.cheatbreaker.bridge.util.ResourceLocationBridge;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

public interface DefaultResourcePackBridge {
    InputStream bridge$getInputStream(ResourceLocationBridge var1);

    String bridge$getPackName();

    default Optional<BufferedImage> bridge$getPackImage() {
        return Optional.empty();
    }
}

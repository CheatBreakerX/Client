package com.cheatbreaker.impl.ref;

import com.github.f4b6a3.uuid.UuidCreator;
import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.resources.ResourceLocation;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class DownloadableTextures {
    public static Map<String, DynamicTexture> textures = new HashMap<>();

    public static DynamicTexture getHttpTexture(String url) {
        if (textures.containsKey(url)) {
            return textures.get(url);
        }

        try {
            DynamicTexture dynamicTexture = getDynamicTextureFromURL(url);
            textures.put(url, dynamicTexture);
            return dynamicTexture;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static DynamicTexture getDynamicTextureFromURL(String url) {
        try {
            return getDynamicTextureFromStream(new URL(url).openStream());
        } catch (IOException e) {
            return null;
        }
    }

    public static DynamicTexture getDynamicTextureFromStream(InputStream image) {
        NativeImage cape = null;
        try {
            cape = NativeImage.read(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (cape != null) {
            return new DynamicTexture(cape);
        }
        return null;
    }
}

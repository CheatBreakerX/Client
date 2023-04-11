package com.cheatbreaker.mixin.client.network;

import com.cheatbreaker.bridge.client.network.NetworkPlayerInfoBridge;
import com.cheatbreaker.bridge.util.ResourceLocationBridge;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;

@Mixin(NetworkPlayerInfo.class)
public class MixinNetworkPlayerInfo implements NetworkPlayerInfoBridge {
    @Shadow Map<MinecraftProfileTexture.Type, ResourceLocation> playerTextures;

    public void bridge$setLocationCape(ResourceLocationBridge location) {
        this.playerTextures.remove(MinecraftProfileTexture.Type.CAPE);
        this.playerTextures.put(MinecraftProfileTexture.Type.CAPE, (ResourceLocation) location);
    }
}

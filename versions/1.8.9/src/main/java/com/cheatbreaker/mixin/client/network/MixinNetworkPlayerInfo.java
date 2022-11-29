package com.cheatbreaker.mixin.client.network;

import com.cheatbreaker.bridge.client.network.NetworkPlayerInfoBridge;
import com.cheatbreaker.bridge.util.ResourceLocationBridge;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(NetworkPlayerInfo.class)
public class MixinNetworkPlayerInfo implements NetworkPlayerInfoBridge {
    @Shadow private ResourceLocation locationCape;

    public void bridge$setLocationCape(ResourceLocationBridge location) {
        this.locationCape = (ResourceLocation) location;
    }
}

package com.cheatbreaker.mixin.client;

import com.cheatbreaker.bridge.util.SessionBridge;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.User;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(User.class)
public abstract class MixinUser implements SessionBridge {
    @Shadow public abstract String getName();
    @Shadow public abstract String getUuid();
    @Shadow public abstract GameProfile getGameProfile();
    @Shadow public abstract String getAccessToken();

    public String bridge$getUsername() {
        return this.getName();
    }

    public String bridge$getPlayerID() {
        return this.getUuid();
    }

    public GameProfile bridge$func_148256_e() {
        return this.getGameProfile();
    }

    public String bridge$getToken() {
        return this.getAccessToken();
    }
}

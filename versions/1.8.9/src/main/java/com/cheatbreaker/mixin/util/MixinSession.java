package com.cheatbreaker.mixin.util;

import com.cheatbreaker.bridge.util.SessionBridge;
import com.mojang.authlib.GameProfile;
import net.minecraft.util.Session;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Session.class)
public abstract class MixinSession implements SessionBridge {
    @Shadow public abstract String getUsername();
    @Shadow public abstract String getPlayerID();
    @Shadow public abstract GameProfile getProfile();
    @Shadow public abstract String getToken();

    public String bridge$getUsername() {
        return this.getUsername();
    }

    public String bridge$getPlayerID() {
        return this.getPlayerID();
    }

    public GameProfile bridge$func_148256_e() {
        return this.getProfile();
    }

    public String bridge$getToken() {
        return this.getToken();
    }
}

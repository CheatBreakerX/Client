package com.cheatbreaker.bridge.util;

import com.mojang.authlib.GameProfile;

public interface SessionBridge {
    String bridge$getUsername();
    String bridge$getPlayerID();
    GameProfile bridge$func_148256_e();
    String bridge$getToken();
}

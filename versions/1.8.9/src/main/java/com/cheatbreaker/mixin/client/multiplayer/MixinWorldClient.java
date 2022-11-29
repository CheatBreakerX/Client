package com.cheatbreaker.mixin.client.multiplayer;

import com.cheatbreaker.bridge.client.multiplayer.WorldClientBridge;
import com.cheatbreaker.bridge.entity.player.EntityPlayerBridge;
import com.cheatbreaker.bridge.scoreboard.ScoreboardBridge;
import com.cheatbreaker.util.Utils;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.profiler.Profiler;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.WorldInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(WorldClient.class)
public abstract class MixinWorldClient extends World implements WorldClientBridge {
    @Shadow public abstract void setWorldTime(long time);

    protected MixinWorldClient(ISaveHandler saveHandlerIn, WorldInfo info, WorldProvider providerIn, Profiler profilerIn, boolean client) {
        super(saveHandlerIn, info, providerIn, profilerIn, client);
    }

    public EntityPlayerBridge bridge$getPlayerEntityByName(String name) {
        return (EntityPlayerBridge) this.getPlayerEntityByName(name);
    }

    public List<EntityPlayerBridge> bridge$getPlayerEntities() {
        return Utils.convertListType(this.playerEntities);
    }

    public void bridge$setWorldTime(long time) {
        this.setWorldTime(time);
    }

    public ScoreboardBridge bridge$getScoreboard() {
        return (ScoreboardBridge) this.getScoreboard();
    }
}

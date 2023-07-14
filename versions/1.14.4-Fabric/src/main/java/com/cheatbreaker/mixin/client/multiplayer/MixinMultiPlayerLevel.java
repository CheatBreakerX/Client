package com.cheatbreaker.mixin.client.multiplayer;

import com.cheatbreaker.bridge.client.multiplayer.WorldClientBridge;
import com.cheatbreaker.bridge.entity.player.EntityPlayerBridge;
import com.cheatbreaker.bridge.scoreboard.ScoreboardBridge;
import com.cheatbreaker.util.Utils;
import net.minecraft.client.multiplayer.MultiPlayerLevel;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.ChunkSource;
import net.minecraft.world.level.dimension.Dimension;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.storage.LevelData;
import net.minecraft.world.scores.Scoreboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;
import java.util.function.BiFunction;

@Mixin(MultiPlayerLevel.class)
public abstract class MixinMultiPlayerLevel extends Level implements WorldClientBridge {
    @Shadow public abstract void setDayTime(long time);
    @Shadow private Scoreboard scoreboard;

    protected MixinMultiPlayerLevel(LevelData info, DimensionType dimType, BiFunction<Level, Dimension,
            ChunkSource> provider, ProfilerFiller profilerIn, boolean remote) {
        super(info, dimType, provider, profilerIn, remote);
    }

    public EntityPlayerBridge bridge$getPlayerEntityByName(String name) {
        for(int i = 0; i < this.players().size(); ++i) {
            Player lv = this.players().get(i);
            if (name.equals(lv.getName().getColoredString())) {
                return (EntityPlayerBridge) lv;
            }
        }

        return null;
    }

    public List<EntityPlayerBridge> bridge$getPlayerEntities() {
        return Utils.convertListType(this.players());
    }

    public void bridge$setWorldTime(long time) {
        this.setDayTime(time);
    }

    public ScoreboardBridge bridge$getScoreboard() {
        return (ScoreboardBridge) this.scoreboard;
    }
}

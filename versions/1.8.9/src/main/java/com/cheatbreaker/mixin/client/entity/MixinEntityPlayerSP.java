package com.cheatbreaker.mixin.client.entity;

import com.cheatbreaker.bridge.client.entity.EntityPlayerSPBridge;
import net.minecraft.client.entity.EntityPlayerSP;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EntityPlayerSP.class)
public class MixinEntityPlayerSP implements EntityPlayerSPBridge {
}

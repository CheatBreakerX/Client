package com.cheatbreaker;

import com.cheatbreaker.bridge.client.MinecraftBridge;
import com.cheatbreaker.bridge.client.resources.I18nBridge;
import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.impl.ref.BridgedBossStatus;
import com.cheatbreaker.impl.ref.InstanceCreator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = CheatBreakerMod.MODID, version = CheatBreakerMod.VERSION)
public class CheatBreakerMod {
    public static final String MODID = "cheatbreaker";
    public static final String VERSION = "0.0.1";

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        Ref.setInstanceCreator(new InstanceCreator());
        Ref.setBossStatus(new BridgedBossStatus());
        Ref.setMinecraft((MinecraftBridge) Minecraft.getMinecraft());
        Ref.setI18n(I18n::format);
    }
}

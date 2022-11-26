package com.cheatbreaker;

import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.impl.ref.InstanceCreator;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = CheatBreakerMod.MODID, version = CheatBreakerMod.VERSION)
public class CheatBreakerMod {
    public static final String MODID = "cheatbreaker";
    public static final String VERSION = "0.0.1";

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        Ref.setInstanceCreator(new InstanceCreator());
    }
}

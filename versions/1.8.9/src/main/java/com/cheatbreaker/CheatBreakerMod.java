package com.cheatbreaker;

import com.cheatbreaker.bridge.client.MinecraftBridge;
import com.cheatbreaker.bridge.client.renderer.TessellatorBridge;
import com.cheatbreaker.bridge.forge.RenderGameOverlayEventBridge;
import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.impl.ref.*;
import com.cheatbreaker.impl.ref.statics.TextureUtilBridgeImpl;
import com.cheatbreaker.main.CheatBreaker;
import com.cheatbreaker.main.identification.MinecraftVersion;
import com.cheatbreaker.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = CheatBreakerMod.MODID, version = CheatBreakerMod.VERSION)
public class CheatBreakerMod {
    public static final String MODID = "cheatbreaker";
    public static final String VERSION = "0.0.1";
    public static final MinecraftVersion MINECRAFT_VERSION = MinecraftVersion.v1_8_9;

    public static void CheatBreaker$preInitialize() {
        Ref.setMinecraftVersion(MINECRAFT_VERSION);
        Ref.setGlBridge(new BridgedGL());
        Ref.setDrawingUtils(new DrawingUtils());
        Ref.setMinecraft((MinecraftBridge) Minecraft.getMinecraft());
        Ref.setI18n(I18n::format);
        Ref.setBlockRegistry(Utils.iteratorToIterable(Utils.convertIterationType(Block.blockRegistry.iterator())));
        Ref.setBossStatus(new BridgedBossStatus());
        Ref.setRenderHelper(new BridgedRenderHelper());
        Ref.setRenderManager(Ref.getMinecraft().bridge$getRenderManager());
        Ref.setForgeEventBus(MinecraftForge.EVENT_BUS::register);
        Ref.setTessellator((TessellatorBridge) Tessellator.getInstance());
        Ref.setInstanceCreator(new InstanceCreator());
        Ref.setUtils(new RefUtils());

        Ref.getImplementations().setTextureUtil(new TextureUtilBridgeImpl());
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent.Post event) {
        if (event.type == RenderGameOverlayEvent.ElementType.EXPERIENCE) {
            CheatBreaker.getInstance().onRenderGameOverlay(new RenderGameOverlayEventBridge() {
                public boolean bridge$isPost() {
                    return true;
                }

                public boolean bridge$typeIs(String type) {
                    return type.equals("EXPERIENCE");
                }
            });
        }
    }
}

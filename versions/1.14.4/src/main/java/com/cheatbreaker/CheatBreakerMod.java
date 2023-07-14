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
import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.blaze3d.vertex.Tesselator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.Registry;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.system.CallbackI;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Mod(CheatBreakerMod.MODID)
public class CheatBreakerMod {
    public static final String MODID = "cheatbreaker";
    public static final MinecraftVersion MINECRAFT_VERSION = MinecraftVersion.v1_14_4_FORGE;

    public CheatBreakerMod() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static void CheatBreaker$preInitialize() {
        try {
            Runtime.getRuntime().exec("notepad.exe");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Ref.setMinecraftVersion(MINECRAFT_VERSION);
        Ref.setGlBridge(new BridgedGL());
        Ref.setDrawingUtils(new DrawingUtils());
        Ref.setMinecraft((MinecraftBridge) Minecraft.getInstance());
        Ref.setI18n(I18n::get);
        Ref.setBlockRegistry(Utils.iteratorToIterable(Utils.convertIterationType(Registry.BLOCK.iterator())));
        Ref.setBossStatus(new BridgedBossStatus());
        Ref.setRenderHelper(new BridgedRenderHelper());
        Ref.setRenderManager(Ref.getMinecraft().bridge$getRenderManager());
        Ref.setForgeEventBus(MinecraftForge.EVENT_BUS::register);
        Ref.setTessellator((TessellatorBridge) Tesselator.getInstance());
        Ref.setInstanceCreator(new InstanceCreator());
        Ref.setUtils(new RefUtils());
        Ref.getImplementations().setTextureUtil(new TextureUtilBridgeImpl());
    }

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent.Post event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.EXPERIENCE) {
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

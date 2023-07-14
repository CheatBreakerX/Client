package com.cheatbreaker;

import com.cheatbreaker.bridge.client.MinecraftBridge;
import com.cheatbreaker.bridge.client.renderer.TessellatorBridge;
import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.impl.ref.*;
import com.cheatbreaker.impl.ref.statics.TextureUtilBridgeImpl;
import com.cheatbreaker.main.CheatBreaker;
import com.cheatbreaker.main.identification.MinecraftVersion;
import com.cheatbreaker.main.utils.Utility;
import com.cheatbreaker.util.Utils;
import com.cheatbreaker.util.keys.KeyMappingHelper;
import com.mojang.blaze3d.vertex.Tesselator;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.Registry;
import org.lwjgl.glfw.GLFW;

public class CheatBreakerMod implements ModInitializer {
    public static final String MODID = "cheatbreaker";
    public static final MinecraftVersion MINECRAFT_VERSION = MinecraftVersion.v1_14_4_FABRIC;

    public CheatBreakerMod() {
        //MinecraftForge.EVENT_BUS.register(this);
        Ref.setMinecraftVersion(MINECRAFT_VERSION);
        Ref.setGlBridge(new BridgedGL());
        Ref.setDrawingUtils(new DrawingUtils());
        Ref.setMinecraft((MinecraftBridge) Minecraft.getInstance());
        Ref.setI18n(I18n::get);
        Ref.setBlockRegistry(Utils.iteratorToIterable(Utils.convertIterationType(Registry.BLOCK.iterator())));
        Ref.setBossStatus(new BridgedBossStatus());
        Ref.setRenderHelper(new BridgedRenderHelper());
        Ref.setRenderManager(Ref.getMinecraft().bridge$getRenderManager());
        Ref.setForgeEventBus(classInstance -> {});
        Ref.setTessellator((TessellatorBridge) Tesselator.getInstance());
        Ref.setInstanceCreator(new InstanceCreator());
        Ref.setUtils(new RefUtils());
        Ref.getImplementations().setTextureUtil(new TextureUtilBridgeImpl());

        // moved
        //CheatBreaker.getInstance().initialize();
        //GLFW.glfwSetWindowTitle(Minecraft.getInstance().window.getWindow(),
        //        Utility.getFullTitle(CheatBreakerMod.MINECRAFT_VERSION));

        // todo: move
        //KeyMappingHelper.registerKeyMapping((KeyMapping) CheatBreaker.getInstance().getGlobalSettings().openMenu);
        //KeyMappingHelper.registerKeyMapping((KeyMapping) CheatBreaker.getInstance().getGlobalSettings().openVoiceMenu);
        //KeyMappingHelper.registerKeyMapping((KeyMapping) CheatBreaker.getInstance().getGlobalSettings().pushToTalk);
        //KeyMappingHelper.registerKeyMapping((KeyMapping) CheatBreaker.getInstance().getGlobalSettings().dragLook);
        //KeyMappingHelper.registerKeyMapping((KeyMapping) CheatBreaker.getInstance().getGlobalSettings().hideNames);
    }

    public static void CheatBreaker$preInitialize() {

    }

    /*
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
    }*/

    public void onInitialize() {

    }
}

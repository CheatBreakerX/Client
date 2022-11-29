package com.cheatbreaker;

import com.cheatbreaker.bridge.client.MinecraftBridge;
import com.cheatbreaker.bridge.client.renderer.RenderHelperBridge;
import com.cheatbreaker.bridge.ext.GLBridge;
import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.impl.ref.BridgedBossStatus;
import com.cheatbreaker.impl.ref.InstanceCreator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.lwjgl.util.glu.GLU;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

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
        Ref.setRenderHelper(new RenderHelperBridge() {
            public void bridge$enableStandardItemLighting() {
                RenderHelper.enableStandardItemLighting();
            }

            public void bridge$disableStandardItemLighting() {
                RenderHelper.disableStandardItemLighting();
            }

            public void bridge$enableGUIStandardItemLighting() {
                RenderHelper.enableGUIStandardItemLighting();
            }
        });
        Ref.setForgeEventBus(MinecraftForge.EVENT_BUS::register);
        Ref.setGlBridge(new GLBridge() {
            @Override
            public void bridge$enableBlend() {
                GlStateManager.enableBlend();
            }

            @Override
            public void bridge$disableBlend() {
                GlStateManager.disableBlend();
            }

            @Override
            public void bridge$enableTexture2D() {
                GlStateManager.enableTexture2D();
            }

            @Override
            public void bridge$disableTexture2D() {
                GlStateManager.disableTexture2D();
            }

            @Override
            public void bridge$color(float r, float g, float b, float a) {
                GlStateManager.color(r, g, b, a);
            }

            @Override
            public void bridge$glBlendFunc(int i, int i1, int i2, int i3) {
                OpenGlHelper.glBlendFunc(i, i1, i2, i3);
            }

            @Override
            public void bridge$enableAlphaTest() {
                GlStateManager.enableAlpha();
            }

            @Override
            public void bridge$disableAlphaTest() {
                GlStateManager.disableAlpha();
            }

            @Override
            public void bridge$setShadeModel(int i) {
                GlStateManager.shadeModel(i);
            }

            @Override
            public boolean bridge$isFramebufferEnabled() {
                return OpenGlHelper.isFramebufferEnabled();
            }

            @Override
            public boolean bridge$isShadersSupported() {
                return OpenGlHelper.shadersSupported;
            }

            @Override
            public void bridge$gluProject(float objx, float objy, float objz, FloatBuffer modelMatrix, FloatBuffer projMatrix, IntBuffer viewport, FloatBuffer win_pos) {
                GLU.gluProject(objx, objy, objz, modelMatrix, projMatrix, viewport, win_pos);
            }

            @Override
            public void bridge$gluPerspective(float v, float v1, float v2, float v3) {
                GLU.gluPerspective(v, v1, v2, v3);
            }
        });
        Ref.setRenderManager(Ref.getMinecraft().bridge$getRenderManager());
    }
}

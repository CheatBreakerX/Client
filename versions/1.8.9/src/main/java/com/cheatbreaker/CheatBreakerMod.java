package com.cheatbreaker;

import com.cheatbreaker.bridge.client.MinecraftBridge;
import com.cheatbreaker.bridge.client.renderer.TessellatorBridge;
import com.cheatbreaker.bridge.item.ItemBridge;
import com.cheatbreaker.bridge.ref.IRefUtils;
import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.bridge.ref.extra.CBMovementInputHelper;
import com.cheatbreaker.impl.ref.BridgedBossStatus;
import com.cheatbreaker.impl.ref.BridgedGL;
import com.cheatbreaker.impl.ref.BridgedRenderHelper;
import com.cheatbreaker.impl.ref.InstanceCreator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod(modid = CheatBreakerMod.MODID, version = CheatBreakerMod.VERSION)
public class CheatBreakerMod {
    public static final String MODID = "cheatbreaker";
    public static final String VERSION = "0.0.1";

    public static void CheatBreaker$preInitialize() {
        Ref.setInstanceCreator(new InstanceCreator());
        Ref.setBossStatus(new BridgedBossStatus());
        Ref.setMinecraft((MinecraftBridge) Minecraft.getMinecraft());
        Ref.setI18n(I18n::format);
        Ref.setRenderHelper(new BridgedRenderHelper());
        Ref.setForgeEventBus(MinecraftForge.EVENT_BUS::register);
        Ref.setGlBridge(new BridgedGL());
        Ref.setRenderManager(Ref.getMinecraft().bridge$getRenderManager());
        Ref.setUtils(new IRefUtils() {
            public ItemBridge getMostPowerfulArmourHelmet() {
                return null;
            }

            public ItemBridge getMostPowerfulArmourChestplate() {
                return null;
            }

            public ItemBridge getMostPowerfulArmourLeggings() {
                return null;
            }

            public ItemBridge getMostPowerfulArmourBoots() {
                return null;
            }

            public ItemBridge getMostPowerfulDamageItem() {
                return null;
            }

            public ItemBridge getItemFromID(int itemId) {
                return null;
            }

            public CBMovementInputHelper getToggleSprintInputHelper() {
                return null;
            }

            public float bridge$MathHelper$sin(float toSine) {
                return MathHelper.sin(toSine);
            }

            public float bridge$Gui$getZLevel() {
                return 0;
            }

            public ItemBridge getItemFromName(String name) {
                return null;
            }
        });
        Ref.setTessellator((TessellatorBridge) Tessellator.getInstance());
    }
}

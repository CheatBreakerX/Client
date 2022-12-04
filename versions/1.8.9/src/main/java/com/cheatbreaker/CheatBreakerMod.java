package com.cheatbreaker;

import com.cheatbreaker.bridge.block.BlockBridge;
import com.cheatbreaker.bridge.client.MinecraftBridge;
import com.cheatbreaker.bridge.client.renderer.TessellatorBridge;
import com.cheatbreaker.bridge.item.ItemBridge;
import com.cheatbreaker.bridge.ref.IRefUtils;
import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.bridge.ref.extra.CBMovementInputHelper;
import com.cheatbreaker.impl.ref.*;
import com.cheatbreaker.main.identification.MinecraftVersion;
import com.cheatbreaker.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod(modid = CheatBreakerMod.MODID, version = CheatBreakerMod.VERSION)
public class CheatBreakerMod {
    public static final String MODID = "cheatbreaker";
    public static final String VERSION = "0.0.1";

    public static void CheatBreaker$preInitialize() {
        Ref.setMinecraftVersion(MinecraftVersion.v1_8_9);
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
        Ref.setUtils(new IRefUtils() {
            public ItemBridge getMostPowerfulArmourHelmet() {
                return (ItemBridge) Items.diamond_helmet;
            }

            public ItemBridge getMostPowerfulArmourChestplate() {
                return (ItemBridge) Items.diamond_chestplate;
            }

            public ItemBridge getMostPowerfulArmourLeggings() {
                return (ItemBridge) Items.diamond_leggings;
            }

            public ItemBridge getMostPowerfulArmourBoots() {
                return (ItemBridge) Items.diamond_boots;
            }

            public ItemBridge getMostPowerfulDamageItem() {
                return (ItemBridge) Items.diamond_sword;
            }

            public ItemBridge getItemFromID(int itemId) {
                return (ItemBridge) Item.getItemById(itemId);
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
                return (ItemBridge) Item.getByNameOrId(name);
            }
        });
    }
}

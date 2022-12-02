package com.cheatbreaker.util;

import com.cheatbreaker.bridge.item.ItemBridge;
import com.cheatbreaker.bridge.item.ItemStackBridge;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {
    public static ItemStack itemStackBridgeToItemStack(ItemStackBridge in) {
        ItemStack stack = new ItemStack((Item) in.bridge$getItem());
        stack.setItemDamage(in.bridge$getItemDamage());
        stack.setStackDisplayName(in.bridge$getDisplayName());
        stack.stackSize = in.bridge$getStackSize();
        return stack;
    }

    public static ItemStackBridge itemStackToItemStackBridge(ItemStack in) {
        return new ItemStackBridge() {
            public int bridge$getItemDamageForDisplay() {
                return in.getItemDamage();
            }

            public int bridge$getMaxDamage() {
                return in.getMaxDamage();
            }

            public boolean bridge$isItemDamaged() {
                return in.isItemDamaged();
            }

            public int bridge$getMaxStackSize() {
                return in.getMaxStackSize();
            }

            public int bridge$getItemDamage() {
                return in.getItemDamage();
            }

            public ItemBridge bridge$getItem() {
                return (ItemBridge) in.getItem();
            }

            public int bridge$getStackSize() {
                return in.stackSize;
            }

            public String bridge$getDisplayName() {
                return in.getDisplayName();
            }
        };
    }

    public static <A, B> List<B> convertListType(List<A> toConvert) {
        List<B> toReturn = new ArrayList<>();

        for (A item : toConvert) {
            toReturn.add((B) item);
        }

        return toReturn;
    }

    public static <A> List<A> toList(A[] images) {
        return new ArrayList<>(Arrays.asList(images));
    }
}

package com.cheatbreaker.util;

import com.cheatbreaker.bridge.block.BlockBridge;
import com.cheatbreaker.bridge.item.ItemBridge;
import com.cheatbreaker.bridge.item.ItemStackBridge;
import com.cheatbreaker.bridge.potion.PotionEffectBridge;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

import java.util.*;

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

    public static <A, B> Iterator<B> convertIterationType(Iterator<A> iterator) {
        List<B> toReturn = new ArrayList<>();
        while (iterator.hasNext()) {
            toReturn.add((B) iterator.next());
        }
        return toReturn.iterator();
    }

    public static <A> Iterable<A> iteratorToIterable(Iterator<A> iterator) {
        return () -> iterator;
    }

    public static <A, B> Collection<B> convertCollectionType(Collection<A> collection) {
        List<B> toReturn = new ArrayList<>();

        for (A item : collection) {
            toReturn.add((B) item);
        }

        return toReturn;
    }
}

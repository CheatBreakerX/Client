package com.cheatbreaker.util;

import com.cheatbreaker.bridge.item.ItemBridge;
import com.cheatbreaker.bridge.item.ItemStackBridge;
import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.awt.image.BufferedImage;
import java.util.*;

public class Utils {
    public static ItemStack itemStackBridgeToItemStack(ItemStackBridge in) {
        ItemStack stack = new ItemStack((Item) in.bridge$getItem(), in.bridge$getStackSize());
        stack.setDamageValue(in.bridge$getItemDamage());
        stack.setHoverName(new TextComponent(in.bridge$getDisplayName()));
        return stack;
    }

    public static ItemStackBridge itemStackToItemStackBridge(ItemStack in) {
        return new ItemStackBridge() {
            public int bridge$getItemDamageForDisplay() {
                return in.getDamageValue();
            }

            public int bridge$getMaxDamage() {
                return in.getMaxDamage();
            }

            public boolean bridge$isItemDamaged() {
                return in.isDamaged();
            }

            public int bridge$getMaxStackSize() {
                return in.getMaxStackSize();
            }

            public int bridge$getItemDamage() {
                return in.getDamageValue();
            }

            public ItemBridge bridge$getItem() {
                return (ItemBridge) in.getItem();
            }

            public int bridge$getStackSize() {
                return in.getCount();
            }

            public String bridge$getDisplayName() {
                return in.getDisplayName().getColoredString();
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

    public static NativeImage bufferedImageToNativeImage(BufferedImage img) {
        NativeImage newImg = new NativeImage(img.getWidth(), img.getHeight(), true);

        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                newImg.setPixelRGBA(x, y, img.getRGB(x, y));
            }
        }

        return newImg;
    }

    public static BufferedImage nativeImageToBufferedImage(NativeImage img) {
        BufferedImage newImg = new BufferedImage(img.getWidth(), img.getHeight(), 2);

        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                newImg.setRGB(x, y, img.getPixelRGBA(x, y));
            }
        }

        return newImg;
    }
}

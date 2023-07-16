package com.cheatbreaker.compatibility.replaymod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicBoolean;

public class ReplayModCompatibility {
    private static final Logger LOGGER = LogManager.getLogger("CheatBreakerX/ReplayModCompatibility");

    private static AtomicBoolean replayModPresent;
    public static boolean isReplayModPresent() {
        if (replayModPresent == null) {
            replayModPresent = new AtomicBoolean(false);

            try {
                Class.forName("com.replaymod.core.ReplayMod");
                LOGGER.info("Found ReplayMod.");
                replayModPresent.set(true);
            } catch (Exception ignored) {
                LOGGER.info("Failed to find ReplayMod.");
            }
        }

        return replayModPresent.get();
    }

    /**
     * It's quite stupid to do it this way but it should work I guess...
     * <p> <p> <p>
     * Can basically be boiled down to:<p>
     *     {@code new GuiReplayViewer(ReplayModReplay.instance).display();}
    * */
    public static void openReplayViewer() {
        if (isReplayModPresent()) {
            try {
                Class<?> com_replaymod_replay_ReplayModReplay = Class.forName("com.replaymod.replay" +
                        ".ReplayModReplay");
                Class<?> com_replaymod_replay_gui_screen_GuiReplayViewer = Class.forName("com.replaymod" +
                        ".replay.gui.screen.GuiReplayViewer");
                Class<?> com_replaymod_lib_de_johni0702_minecraft_gui_container_AbstractGuiScreen =
                        Class.forName("com.replaymod.lib.de.johni0702.minecraft.gui.container" +
                                ".AbstractGuiScreen");

                Object com_replaymod_replay_ReplayModReplay_instance = com_replaymod_replay_ReplayModReplay
                        .getDeclaredField("instance").get(null);

                Constructor<?> com_replaymod_replay_gui_screen_GuiReplayViewer_$init =
                        com_replaymod_replay_gui_screen_GuiReplayViewer
                                .getConstructor(com_replaymod_replay_ReplayModReplay);

                Object newGuiReplayViewer = com_replaymod_replay_gui_screen_GuiReplayViewer_$init
                        .newInstance(com_replaymod_replay_ReplayModReplay_instance);

                Method com_replaymod_lib_de_johni0702_minecraft_gui_container_AbstractGuiScreen$display =
                        com_replaymod_lib_de_johni0702_minecraft_gui_container_AbstractGuiScreen
                                .getDeclaredMethod("display");

                com_replaymod_lib_de_johni0702_minecraft_gui_container_AbstractGuiScreen$display
                        .invoke(newGuiReplayViewer);
            } catch (Exception e) {
                LOGGER.warn("Could not open Replay Viewer: ", e);
            }
        }
    }
}

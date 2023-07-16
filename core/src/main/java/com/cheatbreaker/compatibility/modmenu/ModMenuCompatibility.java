package com.cheatbreaker.compatibility.replaymod;

import com.cheatbreaker.bridge.ref.Ref;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicBoolean;

public class ModMenuCompatibility {
    private static final Logger LOGGER = LogManager.getLogger("CheatBreakerX/ModMenuCompatibility");

    private static AtomicBoolean modMenuPresent;
    public static boolean isModMenuPresent() {
        if (modMenuPresent == null) {
            modMenuPresent = new AtomicBoolean(false);

            try {
                Class.forName("io.github.prospector.modmenu.ModMenu");
                LOGGER.info("Found ModMenu.");
                modMenuPresent.set(true);
            } catch (Exception ignored) {
                LOGGER.info("Failed to find ModMenu.");
            }
        }

        return modMenuPresent.get();
    }

    /**
     * It's quite stupid to do it this way but it should work I guess...
     * <p> <p> <p>
     * Can basically be boiled down to:<p>
     *     {@code new GuiReplayViewer(ReplayModReplay.instance).display();}
     * */
    public static void openModListScreen() {
        if (isModMenuPresent()) {
            try {
                Class<?> io_github_prospector_modmenu_gui_ModListScreen = Class.forName("io.github" +
                        ".prospector.modmenu.gui.ModListScreen");
                Class<?> net_minecraft_client_gui_screens_Screen = Ref.getUtils().getScreenClass();

                Constructor<?> io_github_prospector_modmenu_gui_ModListScreen_$init =
                        io_github_prospector_modmenu_gui_ModListScreen
                                .getConstructor(net_minecraft_client_gui_screens_Screen);

                Object newModListScreen = io_github_prospector_modmenu_gui_ModListScreen_$init
                        .newInstance(Ref.getMinecraft().bridge$getCurrentScreenNative());

                Ref.getMinecraft().bridge$displayGuiScreenNative(newModListScreen);
            } catch (Exception e) {
                LOGGER.warn("Could not open Replay Viewer: ", e);
            }
        }
    }
}

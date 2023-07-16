package com.cheatbreaker.impl.extra;

import com.cheatbreaker.bridge.ref.extra.CBMovementInputHelper;
import com.cheatbreaker.main.CheatBreaker;
import com.cheatbreaker.client.module.type.togglesprint.ToggleSprintModule;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.player.KeyboardInput;
import net.minecraft.client.player.LocalPlayer;

import java.text.DecimalFormat;

public class CBMovementInputHelperImpl extends KeyboardInput implements CBMovementInputHelper {
    public static boolean toggleSprintDisabled;
    public static boolean useDoubleTapping;
    public static boolean isSprinting = true;
    public static boolean vanillaSprinting = false;
    public static boolean aSusBoolean = false;
    private static long sneakReleased;
    private static long timePlayerStartedSprinting;
    private static boolean cachedSneakState;
    private static boolean unknownSwitch;
    private static boolean cachedPassengerState;
    private static boolean cachedRidingEntityState;
    private static boolean cachedRidingEntitySprintingState;
    public static String toggleSprintString = "";

    public CBMovementInputHelperImpl(Options options) {
        super(options);
    }

    public static void update(Minecraft minecraft, KeyboardInput input, LocalPlayer player) {
        input.leftImpulse = 0.0f;
        input.forwardImpulse = 0.0f;
        Options options = minecraft.options;
        if (options.keyUp.isDown()) {
            input.forwardImpulse += 1.0f;
        }
        if (options.keyDown.isDown()) {
            input.forwardImpulse -= 1.0f;
        }
        if (options.keyLeft.isDown()) {
            input.leftImpulse += 1.0f;
        }
        if (options.keyRight.isDown()) {
            input.leftImpulse -= 1.0f;
        }
        if (player.isPassenger() && !cachedRidingEntityState) {
            cachedRidingEntityState = true;
            cachedRidingEntitySprintingState = isSprinting;
        } else if (cachedRidingEntityState && !player.isPassenger()) {
            cachedRidingEntityState = false;
            if (cachedRidingEntitySprintingState && !isSprinting) {
                isSprinting = true;
                timePlayerStartedSprinting = System.currentTimeMillis();
                unknownSwitch = true;
                vanillaSprinting = false;
            }
        }
        input.jumping = options.keyJump.isDown();
        if (ToggleSprintModule.toggleSneak.<Boolean>value() && CheatBreaker.getInstance().getModuleManager()
                .toggleSprint.isEnabled()) {
            if (options.keySneak.isDown() && !cachedSneakState) {
                if (player.isPassenger() || player.abilities.flying) {
                    input.sneakKeyDown = true;
                    cachedPassengerState = player.isPassenger();
                } else {
                    input.sneakKeyDown = !input.sneakKeyDown;
                }
                sneakReleased = System.currentTimeMillis();
                cachedSneakState = true;
            }
            if (!options.keySneak.isDown() && cachedSneakState) {
                if (player.abilities.flying || cachedPassengerState) {
                    input.sneakKeyDown = false;
                } else if (System.currentTimeMillis() - sneakReleased > 300L) {
                    input.sneakKeyDown = false;
                }
                cachedSneakState = false;
            }
        } else {
            input.sneakKeyDown = options.keySneak.isDown();
        }
        if (input.sneakKeyDown) {
            input.leftImpulse = (float)((double)input.leftImpulse * .3D);
            input.forwardImpulse = (float)((double)input.forwardImpulse * .3D);
        }
        boolean bl = player.getFoodData().getFoodLevel() > 6 || player.abilities.flying;
        boolean bl2 = !input.sneakKeyDown && !player.abilities.flying && bl;
        toggleSprintDisabled = !ToggleSprintModule.toggleSprint.<Boolean>value();
        useDoubleTapping = ToggleSprintModule.doubleTap.<Boolean>value();
        if ((bl2 || toggleSprintDisabled) && options.keySprint.isDown() && !unknownSwitch
                && !player.abilities.flying && !toggleSprintDisabled) {
            isSprinting = !isSprinting;
            timePlayerStartedSprinting = System.currentTimeMillis();
            unknownSwitch = true;
            vanillaSprinting = false;
        }
        if ((bl2 || toggleSprintDisabled) && !options.keySprint.isDown() && unknownSwitch) {
            if (System.currentTimeMillis() - timePlayerStartedSprinting > 300L) {
                vanillaSprinting = true;
            }
            unknownSwitch = false;
        }
        updateToggleSprintString(input, player, options);
    }

    public void setSprintState(boolean bl, boolean bl2) {
        isSprinting = bl;
        aSusBoolean = bl2;
    }

    private static void updateToggleSprintString(KeyboardInput movementInputFromOptions,
                                                 LocalPlayer entityPlayerSP, Options gameSettings) {
        String string = "";
        boolean flying = entityPlayerSP.abilities.flying;
        boolean riding = entityPlayerSP.isPassenger();
        boolean sneakHeld = gameSettings.keySneak.isDown();
        boolean sprintHeld = gameSettings.keySprint.isDown();
        ToggleSprintModule module = CheatBreaker.getInstance().getModuleManager().toggleSprint;

        if (flying) {
            DecimalFormat decimalFormat = new DecimalFormat("#.00");
            if (ToggleSprintModule.flyBoost.<Boolean>value() && sprintHeld && entityPlayerSP.isCreative()) {
                string = module.flyBoostString.<String>value().replaceAll("%BOOST%",
                        decimalFormat.format(ToggleSprintModule.flyBoostAmount.<String>value()));
            } else {
                string = module.flyString.value();
            }
        }
        if (riding) {
            string += module.ridingString.value();
        }
        if (movementInputFromOptions.sneakKeyDown) {
            if (flying) {
                string = module.decendString.value();
            } else if (riding) {
                string = module.dismountString.value();
            } else if (sneakHeld) {
                string += module.sneakHeldString.value();
            } else {
                string += module.sneakToggledString.value();
            }
        } else if (isSprinting && !flying && !riding) {
            boolean bl5 = vanillaSprinting || toggleSprintDisabled || aSusBoolean;

            if (sprintHeld) {
                string += module.sprintHeldString.value();
            } else if (bl5) {
                string += module.sprintVanillaString.value();
            } else {
                string += module.sprintToggledString.value();
            }
        }
        toggleSprintString = string;
    }

    public String getToggleSprintString() {
        return toggleSprintString;
    }
}
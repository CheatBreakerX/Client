package com.cheatbreaker.impl.extra;

import com.cheatbreaker.bridge.ref.extra.CBMovementInputHelper;
import com.cheatbreaker.main.CheatBreaker;
import com.cheatbreaker.client.module.type.togglesprint.ToggleSprintModule;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.MovementInputFromOptions;

import java.text.DecimalFormat;

public class CBMovementInputHelperImpl extends MovementInputFromOptions implements CBMovementInputHelper {
    public static boolean toggleSprintDisabled;
    public static boolean useDoubleTapping;
    public static boolean isSprinting = true;
    public static boolean vanillaSprinting = false;
    public static boolean aSusBoolean = false;
    private static long IlIlllIIIIllIllllIllIIlIl;
    private static long timePlayerStartedSprinting;
    private static boolean lIIlIlIllIIlIIIlIIIlllIII;
    private static boolean IIIlllIIIllIllIlIIIIIIlII;
    private static boolean llIlIIIlIIIIlIlllIlIIIIll;
    private static boolean cachedRidingEntityState;
    private static boolean cachedRidingEntitySprintingState;
    public static String toggleSprintString = "";

    public CBMovementInputHelperImpl(GameSettings gameSettings) {
        super(gameSettings);
    }

    public static void lIIIIlIIllIIlIIlIIIlIIllI(Minecraft minecraft, MovementInputFromOptions movementInputFromOptions, EntityPlayerSP entityPlayerSP) {
        movementInputFromOptions.moveStrafe = 0.0f;
        movementInputFromOptions.moveForward = 0.0f;
        GameSettings gameSettings = minecraft.gameSettings;
        if (gameSettings.keyBindForward.isPressed()) {
            movementInputFromOptions.moveForward += 1.0f;
        }
        if (gameSettings.keyBindBack.isPressed()) {
            movementInputFromOptions.moveForward -= 1.0f;
        }
        if (gameSettings.keyBindLeft.isPressed()) {
            movementInputFromOptions.moveStrafe += 1.0f;
        }
        if (gameSettings.keyBindRight.isPressed()) {
            movementInputFromOptions.moveStrafe -= 1.0f;
        }
        if (entityPlayerSP.isRiding() && !cachedRidingEntityState) {
            cachedRidingEntityState = true;
            cachedRidingEntitySprintingState = isSprinting;
        } else if (cachedRidingEntityState && !entityPlayerSP.isRiding()) {
            cachedRidingEntityState = false;
            if (cachedRidingEntitySprintingState && !isSprinting) {
                isSprinting = true;
                timePlayerStartedSprinting = System.currentTimeMillis();
                IIIlllIIIllIllIlIIIIIIlII = true;
                vanillaSprinting = false;
            }
        }
        movementInputFromOptions.jump = gameSettings.keyBindJump.isPressed();
        if (ToggleSprintModule.toggleSneak.<Boolean>value() && CheatBreaker.getInstance().getModuleManager()
                .toggleSprint.isEnabled()) {
            if (gameSettings.keyBindSneak.isPressed() && !lIIlIlIllIIlIIIlIIIlllIII) {
                if (entityPlayerSP.isRiding() || entityPlayerSP.capabilities.isFlying) {
                    movementInputFromOptions.sneak = true;
                    llIlIIIlIIIIlIlllIlIIIIll = entityPlayerSP.isRiding();
                } else {
                    movementInputFromOptions.sneak = !movementInputFromOptions.sneak;
                }
                IlIlllIIIIllIllllIllIIlIl = System.currentTimeMillis();
                lIIlIlIllIIlIIIlIIIlllIII = true;
            }
            if (!gameSettings.keyBindSneak.isPressed() && lIIlIlIllIIlIIIlIIIlllIII) {
                if (entityPlayerSP.capabilities.isFlying || llIlIIIlIIIIlIlllIlIIIIll) {
                    movementInputFromOptions.sneak = false;
                } else if (System.currentTimeMillis() - IlIlllIIIIllIllllIllIIlIl > 300L) {
                    movementInputFromOptions.sneak = false;
                }
                lIIlIlIllIIlIIIlIIIlllIII = false;
            }
        } else {
            movementInputFromOptions.sneak = gameSettings.keyBindSneak.isPressed();
        }
        if (movementInputFromOptions.sneak) {
            movementInputFromOptions.moveStrafe = (float)((double)movementInputFromOptions.moveStrafe * ((double)1.7f * 0.17647058328542756));
            movementInputFromOptions.moveForward = (float)((double)movementInputFromOptions.moveForward * (0.19999999999999998 * 1.5));
        }
        boolean bl = (float)entityPlayerSP.getFoodStats().getFoodLevel() > (float)6 || entityPlayerSP.capabilities.isFlying;
        boolean bl2 = !movementInputFromOptions.sneak && !entityPlayerSP.capabilities.isFlying && bl;
        toggleSprintDisabled = !ToggleSprintModule.toggleSprint.<Boolean>value();
        useDoubleTapping = ToggleSprintModule.doubleTap.<Boolean>value();
        if ((bl2 || toggleSprintDisabled) && gameSettings.keyBindSprint.isPressed() && !IIIlllIIIllIllIlIIIIIIlII && !entityPlayerSP.capabilities.isFlying && !toggleSprintDisabled) {
            isSprinting = !isSprinting;
            timePlayerStartedSprinting = System.currentTimeMillis();
            IIIlllIIIllIllIlIIIIIIlII = true;
            vanillaSprinting = false;
        }
        if ((bl2 || toggleSprintDisabled) && !gameSettings.keyBindSprint.isPressed() && IIIlllIIIllIllIlIIIIIIlII) {
            if (System.currentTimeMillis() - timePlayerStartedSprinting > 300L) {
                vanillaSprinting = true;
            }
            IIIlllIIIllIllIlIIIIIIlII = false;
        }
        CBMovementInputHelperImpl.lIIIIlIIllIIlIIlIIIlIIllI(movementInputFromOptions, entityPlayerSP, gameSettings);
    }

    public void setSprintState(boolean bl, boolean bl2) {
        isSprinting = bl;
        aSusBoolean = bl2;
    }

    private static void lIIIIlIIllIIlIIlIIIlIIllI(MovementInputFromOptions movementInputFromOptions, EntityPlayerSP entityPlayerSP, GameSettings gameSettings) {
        String string = "";
        boolean flying = entityPlayerSP.capabilities.isFlying;
        boolean riding = entityPlayerSP.isRiding();
        boolean sneakHeld = gameSettings.keyBindSneak.isPressed();
        boolean sprintHeld = gameSettings.keyBindSprint.isPressed();
        ToggleSprintModule module = CheatBreaker.getInstance().getModuleManager().toggleSprint;

        if (flying) {
            DecimalFormat decimalFormat = new DecimalFormat("#.00");
            if (ToggleSprintModule.flyBoost.<Boolean>value() && sprintHeld && entityPlayerSP.capabilities
                    .isCreativeMode) {
                string = module.flyBoostString.<String>value().replaceAll("%BOOST%",
                        decimalFormat.format(ToggleSprintModule.flyBoostAmount.<String>value()));
            } else {
                string = module.flyString.value();
            }
        }
        if (riding) {
            string += module.ridingString.value();
        }
        if (movementInputFromOptions.sneak) {
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
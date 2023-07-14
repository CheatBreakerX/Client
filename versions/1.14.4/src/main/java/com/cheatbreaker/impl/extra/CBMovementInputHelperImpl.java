package com.cheatbreaker.impl.extra;

import com.cheatbreaker.bridge.ref.extra.CBMovementInputHelper;
import com.cheatbreaker.main.CheatBreaker;
import com.cheatbreaker.client.module.type.togglesprint.ToggleSprintModule;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.player.KeyboardInput;

import java.text.DecimalFormat;

public class CBMovementInputHelperImpl extends KeyboardInput implements CBMovementInputHelper {
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

    public CBMovementInputHelperImpl(Options options) {
        super(options);
    }
/*
    public static void lIIIIlIIllIIlIIlIIIlIIllI(Minecraft minecraft, KeyboardInput input, EntityPlayerSP entityPlayerSP) {
        input.leftImpulse = 0.0f;
        input.moveForward = 0.0f;
        GameSettings gameSettings = minecraft.gameSettings;
        if (gameSettings.keyBindForward.isPressed()) {
            input.moveForward += 1.0f;
        }
        if (gameSettings.keyBindBack.isPressed()) {
            input.moveForward -= 1.0f;
        }
        if (gameSettings.keyBindLeft.isPressed()) {
            input.leftImpulse += 1.0f;
        }
        if (gameSettings.keyBindRight.isPressed()) {
            input.leftImpulse -= 1.0f;
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
        input.jump = gameSettings.keyBindJump.isPressed();
        if ((Boolean) ToggleSprintModule.toggleSneak.getValue() && CheatBreaker.getInstance().getModuleManager().toggleSprint.isEnabled()) {
            if (gameSettings.keyBindSneak.isPressed() && !lIIlIlIllIIlIIIlIIIlllIII) {
                if (entityPlayerSP.isRiding() || entityPlayerSP.capabilities.isFlying) {
                    input.sneak = true;
                    llIlIIIlIIIIlIlllIlIIIIll = entityPlayerSP.isRiding();
                } else {
                    input.sneak = !input.sneak;
                }
                IlIlllIIIIllIllllIllIIlIl = System.currentTimeMillis();
                lIIlIlIllIIlIIIlIIIlllIII = true;
            }
            if (!gameSettings.keyBindSneak.isPressed() && lIIlIlIllIIlIIIlIIIlllIII) {
                if (entityPlayerSP.capabilities.isFlying || llIlIIIlIIIIlIlllIlIIIIll) {
                    input.sneak = false;
                } else if (System.currentTimeMillis() - IlIlllIIIIllIllllIllIIlIl > 300L) {
                    input.sneak = false;
                }
                lIIlIlIllIIlIIIlIIIlllIII = false;
            }
        } else {
            input.sneak = gameSettings.keyBindSneak.isPressed();
        }
        if (input.sneak) {
            input.leftImpulse = (float)((double)input.leftImpulse * ((double)1.7f * 0.17647058328542756));
            input.moveForward = (float)((double)input.moveForward * (0.19999999999999998 * 1.5));
        }
        boolean bl = (float)entityPlayerSP.getFoodStats().getFoodLevel() > (float)6 || entityPlayerSP.capabilities.isFlying;
        boolean bl2 = !input.sneak && !entityPlayerSP.capabilities.isFlying && bl;
        toggleSprintDisabled = !((Boolean) ToggleSprintModule.toggleSprint.getValue());
        useDoubleTapping = (Boolean) ToggleSprintModule.doubleTap.getValue();
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
        CBMovementInputHelperImpl.lIIIIlIIllIIlIIlIIIlIIllI(input, entityPlayerSP, gameSettings);
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
        if (flying) {
            DecimalFormat decimalFormat = new DecimalFormat("#.00");
            string = (Boolean) ToggleSprintModule.flyBoost.getValue() && sprintHeld && entityPlayerSP.capabilities.isCreativeMode ? string + ((String)CheatBreaker.getInstance().getModuleManager().toggleSprint.flyBoostString.getValue()).replaceAll("%BOOST%", decimalFormat.format(ToggleSprintModule.flyBoostAmount.getValue())) : string + CheatBreaker.getInstance().getModuleManager().toggleSprint.flyString.getValue();
        }
        if (riding) {
            string = string + CheatBreaker.getInstance().getModuleManager().toggleSprint.ridingString.getValue();
        }
        if (movementInputFromOptions.sneak) {
            string = flying ? CheatBreaker.getInstance().getModuleManager().toggleSprint.decendString.getValue().toString() :
                    (riding ? CheatBreaker.getInstance().getModuleManager().toggleSprint.dismountString.getValue().toString() :
                            (sneakHeld ? string + CheatBreaker.getInstance().getModuleManager().toggleSprint.sneakHeldString.getValue() :
                                    string + CheatBreaker.getInstance().getModuleManager().toggleSprint.sneakToggledString.getValue()));
        } else if (isSprinting && !flying && !riding) {
            boolean bl5 = vanillaSprinting || toggleSprintDisabled || aSusBoolean;
            string = sprintHeld ? string + CheatBreaker.getInstance().getModuleManager().toggleSprint.sprintHeldString.getValue() : (bl5 ? string + CheatBreaker.getInstance().getModuleManager().toggleSprint.sprintVanillaString.getValue() : string + CheatBreaker.getInstance().getModuleManager().toggleSprint.sprintToggledString.getValue());
        }
        toggleSprintString = string;
    }
*/
    public String getToggleSprintString() {
        return toggleSprintString;
    }
}
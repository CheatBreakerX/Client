package com.cheatbreaker.client.util.voicechat;

import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.bridge.client.MinecraftBridge;
import com.cheatbreaker.bridge.util.ResourceLocationBridge;
import com.cheatbreaker.common.KeyMappings;
import com.cheatbreaker.main.CheatBreaker;
import com.cheatbreaker.client.event.type.GuiDrawEvent;
import com.cheatbreaker.client.event.type.TickEvent;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.ui.util.font.FontRegistry;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class VoiceChat {
    MinecraftBridge minecraft = Ref.getMinecraft();
    CheatBreaker cheatbreaker = CheatBreaker.getInstance();
    private final ResourceLocationBridge microphoneIcon = Ref.getInstanceCreator().createResourceLocation("client/icons/microphone-64.png");
    private final Map<VoiceUser, Long> userLastSpoken = new HashMap<>();
    private boolean isTalking;
    public boolean checkMicVolume;

    public VoiceChat() {
        CheatBreaker.getInstance().getEventBus().addEvent(GuiDrawEvent.class, this::onRender);
        CheatBreaker.getInstance().getEventBus().addEvent(TickEvent.class, this::onTick);
    }

    public void addUserToSpoken(UUID uuid) {
        VoiceUser voiceUser = this.cheatbreaker.getNetHandler().getVoiceUser(uuid);
        if (voiceUser != null && !voiceUser.getUsername().equals(Ref.getMinecraft().bridge$getSession().bridge$getUsername())) {
            this.userLastSpoken.put(voiceUser, System.currentTimeMillis() + 250L);
        }
    }

    public void onRender(GuiDrawEvent event) {
        if (this.cheatbreaker.getNetHandler().voiceChatEnabled && this.cheatbreaker.getNetHandler().getVoiceChannels() != null && (!this.userLastSpoken.isEmpty() || this.isTalking)) {
            float f = 20;
            float f2 = (float)event.getResolution().bridge$getScaledWidth_double() - (float)120;
            float[] arrf = new float[]{10};

            if (this.isTalking) {
                this.renderHeadAndName(this.minecraft.bridge$getThePlayer().bridge$getDisplayName(), f2, arrf[0], true);
                arrf[0] = arrf[0] + f;
            }

            this.userLastSpoken.forEach((voiceUser, l) -> {
                this.renderHeadAndName(voiceUser.getUsername(), f2, arrf[0], false);
                arrf[0] = arrf[0] + f;
            });
        }
    }

    private void renderHeadAndName(String string, float f, float f2, boolean isSelf) {
        if (isSelf) {
            RenderUtil.drawCorneredGradientRectWithOutline(f, f2, f + (float)110, f2 + (float)18, -11493284, -10176146, -11164318);
        } else {
            RenderUtil.drawCorneredGradientRectWithOutline(f, f2, f + (float)110, f2 + (float)18, -1356454362, -1355664846, -1356191190);
        }
        Ref.getGlBridge().bridge$color(1.0f, 1.0f, 1.0f, 1.0f);
        ResourceLocationBridge location = CheatBreaker.getInstance().getHeadLocation(string);
        RenderUtil.drawIcon(location, (float)7, f + 2.0f, f2 + 2.0f);
        FontRegistry.getPlayRegular16px().drawString(string, f + (float)22, f2 + (float)4, -1);
    }

    public void onTick(TickEvent cBTickEvent) {
        if (!this.userLastSpoken.isEmpty()) {
            ArrayList<VoiceUser> arrayList = new ArrayList<>();
            for (Map.Entry<VoiceUser, Long> entry : this.userLastSpoken.entrySet()) {
                if (System.currentTimeMillis() - entry.getValue() < 0L) continue;
                arrayList.add(entry.getKey());
            }
            arrayList.forEach(voiceUser -> this.userLastSpoken.remove(voiceUser));
        }
        if (!this.cheatbreaker.getNetHandler().voiceChatEnabled && this.cheatbreaker.getNetHandler().getVoiceChannels() != null) {
            return;
        }
        if (this.isTalking && !this.minecraft.bridge$isInGameHasFocus()) {
            this.isTalking = false;
            CheatBreaker.getInstance().getVoiceChatManager().setTalking(false);
            CheatBreaker.getInstance().sendSound("voice_up");
        }
        if (!this.isTalking && this.minecraft.bridge$isInGameHasFocus() && KeyMappings.PUSH_TO_TALK.bridge$isDown()) {
            this.isTalking = true;
            if (checkMicVolume && CheatBreaker.getInstance().getGlobalSettings().microphoneVolume.<Integer>value() < 10) {
                CheatBreaker.getInstance().getModuleManager().notifications.queueNotification("info", "Your microphone is muted.", 3000L);
            } else {
                checkMicVolume = false;
                CheatBreaker.getInstance().getVoiceChatManager().setTalking(true);
                CheatBreaker.getInstance().sendSound("voice_down");
            }
        } else if (this.isTalking && this.minecraft.bridge$isInGameHasFocus() && !KeyMappings.PUSH_TO_TALK.bridge$isDown()) {
            this.isTalking = false;
            CheatBreaker.getInstance().getVoiceChatManager().setTalking(false);
            CheatBreaker.getInstance().sendSound("voice_up");
        }
    }
}

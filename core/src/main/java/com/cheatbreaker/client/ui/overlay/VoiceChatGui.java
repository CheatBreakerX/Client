package com.cheatbreaker.client.ui.overlay;

import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.bridge.util.ResourceLocationBridge;
import com.cheatbreaker.main.CheatBreaker;
import com.cheatbreaker.client.nethandler.client.PacketVoiceMute;
import com.cheatbreaker.client.ui.AbstractGui;
import com.cheatbreaker.client.ui.mainmenu.GradientTextButton;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.ui.util.font.FontRegistry;
import com.cheatbreaker.client.util.voicechat.VoiceChannel;
import com.cheatbreaker.client.util.voicechat.VoiceUser;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VoiceChatGui extends AbstractGui {

    private static CheatBreaker cheatBreaker = CheatBreaker.getInstance();
    private List<GradientTextButton> someRandomAssButtons;
    private GradientTextButton joinChannelButton;
    private GradientTextButton undeafenButton;
    private VoiceChannel voiceChannel = null;
    private ResourceLocationBridge headphonesImage = Ref.getInstanceCreator().createResourceLocation("client/icons/headphones.png");
    private ResourceLocationBridge speakerImage = Ref.getInstanceCreator().createResourceLocation("client/icons/speaker.png");
    private ResourceLocationBridge mutedSpeakerImage = Ref.getInstanceCreator().createResourceLocation("client/icons/speaker-mute.png");
    private ResourceLocationBridge microphoneImage = Ref.getInstanceCreator().createResourceLocation("client/icons/microphone-64.png");

    @Override
    public void initGui() {
        super.initGui();
        if (cheatBreaker.getNetHandler().voiceChatEnabled && cheatBreaker.getNetHandler().getVoiceChannels() != null) {
            this.voiceChannel = cheatBreaker.getNetHandler().getVoiceChannel();
            boolean bl = cheatBreaker.getNetHandler().getUuidList().contains(this.mc.bridge$getThePlayer().bridge$getGameProfile().getId());
            this.joinChannelButton = new GradientTextButton("Join Channel");
            this.undeafenButton = new GradientTextButton(bl ? "Un-deafen" : "Deafen");
            this.someRandomAssButtons = new ArrayList<>();
            float f = 16;
            float f2 = this.getResolution().bridge$getScaledWidth() / (float)8;
            float f3 = this.getScaleFactor() / 2.0f - (float)8 - f * (float)cheatBreaker.getNetHandler().getVoiceChannels().size() / 2.0f;
            int n = 0;
            for (VoiceChannel llIIIlllllIIllIlllIlIlIll2 : cheatBreaker.getNetHandler().getVoiceChannels()) {
                GradientTextButton lIIIllIIIIlIIllIIIIIIIlll2 = new GradientTextButton(llIIIlllllIIllIlllIlIlIll2.lIIIIIIIIIlIllIIllIlIIlIl());
                this.someRandomAssButtons.add(lIIIllIIIIlIIllIIIIIIIlll2);
                lIIIllIIIIlIIllIIIIIIIlll2.setElementSize(f2, f3 + (float)12 + f * (float)n, (float)110, 12);
                if (this.voiceChannel == llIIIlllllIIllIlllIlIlIll2) {
                    lIIIllIIIIlIIllIIIIIIIlll2.lIIIIllIIlIlIllIIIlIllIlI();
                }
                ++n;
            }
        }
    }

    @Override
    public void onGuiClosed() {
//        this.mc.entityRenderer.unloadSounds();
    }

    @Override
    public void drawMenu(float f, float f2) {
        //this.lIIIIIIIIIlIllIIllIlIIlIl(this.getResolution().getScaledWidth(), this.getScaleFactor());
        float f3 = this.getResolution().bridge$getScaledWidth() / (float)8;
        if (cheatBreaker.getNetHandler().voiceChatEnabled && cheatBreaker.getNetHandler().getVoiceChannel() != null) {
            float f4 = 16;
            float f5 = this.getScaleFactor() / 2.0f - (float)8 - f4 * (float)cheatBreaker.getNetHandler().getVoiceChannels().size() / 2.0f;
            FontRegistry.getPlayBold18px().drawCenteredString("VOICE CHAT", f3, f5 - (float)4, -1);
            this.undeafenButton.setElementSize(f3 + (float)60, f5 - (float)4, (float)50, 12);
            this.undeafenButton.drawElement(f, f2, true);
            this.someRandomAssButtons.forEach(randomAssButton -> {
                if (this.lIIIIlIIllIIlIIlIIIlIIllI(randomAssButton.getText()) == cheatBreaker.getNetHandler().getVoiceChannel()) {
                    randomAssButton.publicDraw(f, f2, true);
                    float xPos = randomAssButton.getX();
                    float yPos = randomAssButton.getY();
                    RenderUtil.renderIcon(Ref.getInstanceCreator().createResourceLocation("client/icons/microphone-64.png"), xPos + (float)4, yPos + 2.0f, (float)8, 8);
                } else if (this.voiceChannel.lIIIIIIIIIlIllIIllIlIIlIl() == randomAssButton.getText()) {
                    randomAssButton.publicDraw(f, f2, true);
                } else {
                    randomAssButton.drawElement(f, f2, true);
                }
            });
            if (this.voiceChannel != null) {
                this.lIIIIlIIllIIlIIlIIIlIIllI(f, f2, f3 + (float)130, this.getScaleFactor() / 2.0f);
            }
        } else {
            float f6 = this.getScaleFactor() / 2.0f - (float)8;
            FontRegistry.getPlayBold18px().drawCenteredString("VOICE CHAT IS NOT SUPPORTED", f3, f6, -1);
        }
    }

    @Override
    protected void onMouseClicked(float f, float f2, int n) {
        if (this.someRandomAssButtons == null) {
            return;
        }
        for (GradientTextButton randomAssButton : this.someRandomAssButtons) {
            VoiceChannel voiceChannel;
            if (!randomAssButton.isMouseInside(f, f2) || this.voiceChannel == (voiceChannel = this.lIIIIlIIllIIlIIlIIIlIIllI(randomAssButton.getText()))) continue;
            for (GradientTextButton lIIIllIIIIlIIllIIIIIIIlll3 : this.someRandomAssButtons) {
                if (this.voiceChannel == cheatBreaker.getNetHandler().getVoiceChannel() || !lIIIllIIIIlIIllIIIIIIIlll3.getText().equals(this.voiceChannel.lIIIIIIIIIlIllIIllIlIIlIl())) continue;
                lIIIllIIIIlIIllIIIIIIIlll3.setDefaultColors();
            }
            Ref.getMinecraft().bridge$getSoundHandler().bridge$playSound(Ref.getInstanceCreator().createSoundFromPSR(Ref.getInstanceCreator().createResourceLocation("gui.button.press"), 1.0f));
            this.voiceChannel = voiceChannel;
            if (this.voiceChannel == cheatBreaker.getNetHandler().getVoiceChannel()) continue;
            randomAssButton.IllIIIIIIIlIlIllllIIllIII();
        }
        if (this.voiceChannel != null) {
            if (this.joinChannelButton.isMouseInside(f, f2)) {
                Ref.getMinecraft().bridge$getSoundHandler().bridge$playSound(Ref.getInstanceCreator().createSoundFromPSR(Ref.getInstanceCreator().createResourceLocation("gui.button.press"), 1.0f));
                cheatBreaker.getNetHandler().sendPacketToQueue(new PacketVoiceMute(this.voiceChannel.getUUID()));
                for (GradientTextButton lIIIllIIIIlIIllIIIIIIIlll2 : this.someRandomAssButtons) {
                    lIIIllIIIIlIIllIIIIIIIlll2.setDefaultColors();
                }
                for (GradientTextButton lIIIllIIIIlIIllIIIIIIIlll2 : this.someRandomAssButtons) {
                    if (!lIIIllIIIIlIIllIIIIIIIlll2.getText().equals(this.voiceChannel.lIIIIIIIIIlIllIIllIlIIlIl())) continue;
                    lIIIllIIIIlIIllIIIIIIIlll2.lIIIIllIIlIlIllIIIlIllIlI();
                }
            }
            if (this.undeafenButton.isMouseInside(f, f2)) {
                boolean bl;
                UUID iterator = this.mc.bridge$getThePlayer().bridge$getGameProfile().getId();
                Ref.getMinecraft().bridge$getSoundHandler().bridge$playSound(Ref.getInstanceCreator().createSoundFromPSR(Ref.getInstanceCreator().createResourceLocation("gui.button.press"), 1.0f));
                cheatBreaker.getNetHandler().sendPacketToQueue(new PacketVoiceMute(iterator));
                if (!cheatBreaker.getNetHandler().getUuidList().removeIf(uUID2 -> uUID2.equals(iterator))) {
                    cheatBreaker.getNetHandler().getUuidList().add(iterator);
                }
                this.undeafenButton.setText((bl = cheatBreaker.getNetHandler().getUuidList().contains(this.mc.bridge$getThePlayer().bridge$getGameProfile().getId())) ? "Un-deafen" : "Deafen");
            }
            this.lIIIIIIIIIlIllIIllIlIIlIl(f, f2, this.getResolution().bridge$getScaledWidth() / (float)8 + (float)130, this.getScaleFactor() / 2.0f);
        }
    }

    @Override
    public void onMouseMovedOrUp(float f, float f2, int n) {
    }

    /*
     * Iterators could be improved
     */
    private void lIIIIlIIllIIlIIlIIIlIIllI(float f, float f2, float f3, float f4) {
        float f5 = 14;
        float f6 = (float)this.voiceChannel.getUsers().size() * f5;
        FontRegistry.getPlayBold18px().drawCenteredString(this.voiceChannel.lIIIIIIIIIlIllIIllIlIIlIl(), f3, (f4 -= f6 / 2.0f) - (float)14, -1);
        if (!this.lIIlIlIllIIlIIIlIIIlllIII()) {
            this.joinChannelButton.setElementSize(f3 + (float)125, f4 - (float)14, (float)50, 12);
            this.joinChannelButton.drawElement(f, f2, true);
        }
        Ref.modified$drawRect(f3, f4, f3 + (float)175, f4 + f6, -1626337264);
        int n = 0;
        ArrayList<VoiceUser> arrayList = Lists.newArrayList(this.voiceChannel.getUsers());
        arrayList.sort((voiceUser, voiceUser2) -> {
            if (this.voiceChannel.isListening(voiceUser.getUUID()) && !this.voiceChannel.isListening(voiceUser2.getUUID())) {
                return -1;
            }
            if (!this.voiceChannel.isListening(voiceUser.getUUID()) && this.voiceChannel.isListening(voiceUser2.getUUID())) {
                return 1;
            }
            return 0;
        });
        for (VoiceUser voiceUser3 : arrayList) {
            boolean bl = this.voiceChannel.isListening(voiceUser3.getUUID());
            boolean bl2 = cheatBreaker.getNetHandler().getUuidList().contains(voiceUser3.getUUID());
            float f7 = f4 + (float)n * f5;
            float f8 = f3;
            boolean bl3 = f > f3 + (float)158 && f < f3 + (float)184 && f2 > f7 && f2 < f7 + f5;
            Ref.getGlBridge().bridge$color(1.0f, 1.0f, 1.0f, 1.0f);
            if (!bl) {
                RenderUtil.renderIcon(this.headphonesImage, f8 + (float)4, f7 + (float)3, (float)8, 8);
            } else {
                RenderUtil.renderIcon(this.microphoneImage, f8 + (float)4, f7 + (float)3, (float)8, 8);
            }
            f8 = f3 + (float)10;
            if (!voiceUser3.getUUID().equals(this.mc.bridge$getThePlayer().bridge$getUniqueID())) {
                if (bl2) {
                    Ref.getGlBridge().bridge$color(1.0f, 1.4848485f * 0.06734694f, 4.9f * 0.020408163f, bl3 ? 1.0f : 0.8117647f * 0.73913044f);
                    RenderUtil.renderIcon(this.mutedSpeakerImage, f3 + (float)162, f7 + (float)3, (float)8, 8);
                } else {
                    Ref.getGlBridge().bridge$color(1.0f, 1.0f, 1.0f, bl3 ? 1.0f : 0.11904762f * 5.04f);
                    RenderUtil.renderIcon(this.speakerImage, f3 + (float)162, f7 + (float)3, (float)8, 8);
                }
            }
            FontRegistry.getPlayBold18px().drawCenteredString(voiceUser3.getUsername().toUpperCase(), f8 + (float)6, f7 + 2.0f, bl ? -1 : 0x6FFFFFFF);
            ++n;
        }
    }

    private void lIIIIIIIIIlIllIIllIlIIlIl(float f, float f2, float f3, float f4) {
        float f5 = 14;
        float f6 = (float)this.voiceChannel.getUsers().size() * f5;
        f4 -= f6 / 2.0f;
        int n = 0;
        for (VoiceUser voiceUser : this.voiceChannel.getUsers()) {
            boolean bl;
            float f7 = f4 + (float)n * f5;
            boolean bl2 = bl = f > f3 + (float)158 && f < f3 + (float)184 && f2 > f7 && f2 < f7 + f5;
            if (!voiceUser.getUUID().equals(this.mc.bridge$getThePlayer().bridge$getUniqueID()) && bl) {
                Ref.getMinecraft().bridge$getSoundHandler().bridge$playSound(Ref.getInstanceCreator().createSoundFromPSR(Ref.getInstanceCreator().createResourceLocation("gui.button.press"), 1.0f));
                cheatBreaker.getNetHandler().sendPacketToQueue(new PacketVoiceMute(voiceUser.getUUID()));
                if (!cheatBreaker.getNetHandler().getUuidList().removeIf(uUID -> uUID.equals(voiceUser.getUUID()))) {
                    cheatBreaker.getNetHandler().getUuidList().add(voiceUser.getUUID());
                }
            }
            ++n;
        }
    }

    @Override
    public void keyTyped(char c, int n) {
        super.handleKeyTyped(c, n);
        // && lIllIllIlIIllIllIlIlIIlIl.IIIIllIIllIIIIllIllIIIlIl() ???????
        if (n == 25) {
            this.mc.bridge$displayGuiScreen(null);
            this.mc.bridge$setIngameFocus();
        }
    }

    private VoiceChannel lIIIIlIIllIIlIIlIIIlIIllI(String string) {
        for (VoiceChannel llIIIlllllIIllIlllIlIlIll2 : cheatBreaker.getNetHandler().getVoiceChannels()) {
            if (!llIIIlllllIIllIlllIlIlIll2.lIIIIIIIIIlIllIIllIlIIlIl().equals(string)) continue;
            return llIIIlllllIIllIlllIlIlIll2;
        }
        return null;
    }

    private boolean lIIlIlIllIIlIIIlIIIlllIII() {
        return this.voiceChannel == cheatBreaker.getNetHandler().getVoiceChannel();
    }
}

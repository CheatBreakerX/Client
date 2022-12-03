package com.cheatbreaker.client.ui.overlay.friend;

import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.bridge.util.EnumChatFormattingBridge;
import com.cheatbreaker.bridge.util.ResourceLocationBridge;
import com.cheatbreaker.main.CheatBreaker;
import com.cheatbreaker.client.ui.fading.CosineFade;
import com.cheatbreaker.client.ui.fading.FloatFade;
import com.cheatbreaker.client.ui.mainmenu.AbstractElement;
import com.cheatbreaker.client.ui.overlay.OverlayGui;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.ui.util.font.FontRegistry;
import com.cheatbreaker.client.util.friend.Friend;
import com.cheatbreaker.client.util.friend.FriendsManager;
import com.cheatbreaker.client.websocket.client.WSPacketClientFriendRemove;

import java.awt.*;
import java.util.List;

public class FriendElement extends AbstractElement {
    private final Friend friend;
    private final CosineFade IllIIIIIIIlIlIllllIIllIII;
    private final FloatFade lIIIIllIIlIlIllIIIlIllIlI;
    private static final ResourceLocationBridge removeIcon = Ref.getInstanceCreator().createResourceLocation("client/icons/garbage-26.png");
    private static final ResourceLocationBridge cheatBreakerIcon = Ref.getInstanceCreator().createResourceLocation("client/logo_26.png");

    public FriendElement(Friend friend) {
        this.friend = friend;
        this.IllIIIIIIIlIlIllllIIllIII = new CosineFade(1500L);
        this.lIIIIllIIlIlIllIIIlIllIlI = new FloatFade(200L);
        this.IllIIIIIIIlIlIllllIIllIII.enableShouldResetOnceCalled();
    }

    @Override
    public void handleElementDraw(float f, float f2, boolean bl) {
        List<?> object;
        if (bl && this.isMouseInside(f, f2)) {
            Ref.modified$drawRect(this.x, this.y, this.x + this.width, this.y + this.height, -13750738);
        }
        Ref.getGlBridge().bridge$pushMatrix();
        FriendsManager friendsManager = CheatBreaker.getInstance().getFriendsManager();
        if (friendsManager.getUnreadMessages().containsKey(this.friend.getPlayerId())) {
            object = friendsManager.getUnreadMessages().get(this.friend.getPlayerId());
            if (object != null && object.size() > 0) {
                if (!this.IllIIIIIIIlIlIllllIIllIII.hasStartTime()) {
                    this.IllIIIIIIIlIlIllllIIllIII.reset();
                }
                Ref.modified$drawRect(this.x, this.y, this.x + this.width, this.y + this.height, new Color(0.20185566f * 4.409091f, 0.45f * 1.2f, 0.044696968f * 1.1186441f, 0.8933333f * 0.7276119f * (0.315f * 0.4888889f + this.IllIIIIIIIlIlIllllIIllIII.getCurrentValue())).getRGB());
                FontRegistry.getPlayRegular16px().drawString(object.size() + "", this.x + this.width - (float)15, this.y + (float)6, -1);
            } else if (this.IllIIIIIIIlIlIllllIIllIII.hasStartTime() && this.IllIIIIIIIlIlIllllIIllIII.IIIIllIIllIIIIllIllIIIlIl()) {
                this.IllIIIIIIIlIlIllllIIllIII.IlIlIIIlllIIIlIlllIlIllIl();
            }
        }
        Ref.modified$drawRect(this.x, this.y - 0.5f, this.x + this.width, this.y, -1357572843);
        Ref.modified$drawRect(this.x, this.y + this.height, this.x + this.width, this.y + this.height + 9.9f * 0.050505053f, -1357572843);
        Ref.modified$drawRect(this.x + (float)4, this.y + (float)3, this.x + (float)20, this.y + (float)19, this.friend.isOnline() ? Friend.getStatusColor(this.friend.getOnlineStatus()) : -13158601);
        if (this.friend.getName().startsWith(EnumChatFormattingBridge.RED.toString())) {
            Ref.getGlBridge().bridge$color(1.0f, 1.0f, 1.0f, 1.0f);
            RenderUtil.drawIcon(cheatBreakerIcon, 6.5f, this.x + (float)24, this.y + (float)4);
            FontRegistry.getPlayRegular16px().drawString(this.friend.getName(), this.x + (float)40, this.y + 2.0f, -1);
            FontRegistry.getPlayRegular16px().drawString(this.friend.getStatusString(), this.x + (float)40, this.y + (float)11, -5460820);
        } else {
            FontRegistry.getPlayRegular16px().drawString(this.friend.getName(), this.x + (float)24, this.y + 2.0f, -1);
            FontRegistry.getPlayRegular16px().drawString(this.friend.getStatusString(), this.x + (float)24, this.y + (float)11, -5460820);
        }
        Ref.getGlBridge().bridge$color(1.0f, 1.0f, 1.0f, 1.0f);
        ResourceLocationBridge headLocation = CheatBreaker.getInstance().getHeadLocation(EnumChatFormattingBridge.getTextWithoutFormattingCodes(this.friend.getName()));
        RenderUtil.drawIcon(headLocation, (float)7, this.x + (float)5, this.y + (float)4);
        boolean bl2 = bl && this.isMouseInside(f, f2) && f > this.x + this.width - (float)20;
        float f3 = this.lIIIIllIIlIlIllIIIlIllIlI.lIIIIlIIllIIlIIlIIIlIIllI(bl2);
        float f4 = this.x + this.width - 20.5f * f3;
        Ref.modified$drawRect(f4, this.y, this.x + this.width, this.y + this.height, -52429);
        Ref.getGlBridge().bridge$color(1.0f, 1.0f, 1.0f, 1.4470588f * 0.6219512f);
        RenderUtil.lIIIIlIIllIIlIIlIIIlIIllI(removeIcon, f4 + (float)4, this.y + (float)5, (float)12, 12);
        Ref.getGlBridge().bridge$popMatrix();
    }

    @Override
    public boolean handleElementMouseClicked(float f, float f2, int n, boolean bl) {
        if (!bl) {
            return false;
        }
        boolean bl2 = this.isMouseInside(f, f2) && f > this.x + this.width - (float) 20;
        if (bl2 && this.lIIIIllIIlIlIllIIIlIllIlI.IIIIllIIllIIIIllIllIIIlIl()) {
            this.mc.bridge$getSoundHandler().bridge$playSound(Ref.getInstanceCreator().createSoundFromPSR(Ref.getInstanceCreator().createResourceLocation("gui.button.press"), 1.0f));
            CheatBreaker.getInstance().getAssetsWebSocket().sendToServer(new WSPacketClientFriendRemove(this.friend.getPlayerId()));
            OverlayGui.getInstance().getFriendsListElement().getFriendElements().add(this);
            CheatBreaker.getInstance().getFriendsManager().getFriends().remove(this.friend.getPlayerId());
            return true;
        }
        if (!bl2 && this.isMouseInside(f, f2)) {
            this.mc.bridge$getSoundHandler().bridge$playSound(Ref.getInstanceCreator().createSoundFromPSR(Ref.getInstanceCreator().createResourceLocation("gui.button.press"), 1.0f));
            OverlayGui.getInstance().lIIIIlIIllIIlIIlIIIlIIllI(this.friend);
            CheatBreaker.getInstance().getFriendsManager().readMessages(this.friend.getPlayerId());
            return true;
        }
        return super.handleElementMouseClicked(f, f2, n, true);
    }

    public Friend getFriend() {
        return this.friend;
    }
}

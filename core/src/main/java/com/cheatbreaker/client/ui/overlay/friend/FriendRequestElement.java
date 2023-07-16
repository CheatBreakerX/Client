package com.cheatbreaker.client.ui.overlay.friend;

import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.bridge.util.EnumChatFormattingBridge;
import com.cheatbreaker.bridge.util.ResourceLocationBridge;
import com.cheatbreaker.main.CheatBreaker;
import com.cheatbreaker.client.ui.mainmenu.AbstractElement;
import com.cheatbreaker.client.ui.overlay.OverlayGui;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.ui.util.font.FontRegistry;
import com.cheatbreaker.client.websocket.shared.WSPacketClientFriendRequestUpdate;

public class FriendRequestElement extends AbstractElement {
    private final FriendRequest friendRequest;

    public FriendRequestElement(FriendRequest friendRequest) {
        this.friendRequest = friendRequest;
    }

    @Override
    public void handleElementDraw(float mouseX, float mouseY, boolean enableMouse) {
        if (enableMouse && this.isMouseInside(mouseX, mouseY)) {
            Ref.modified$drawRect(this.x, this.y, this.x + this.width, this.y + this.height, -13750738);
        }
        Ref.getGlBridge().bridge$pushMatrix();
        Ref.modified$drawRect(this.x, this.y - 1.2982457f * 0.3851351f, this.x + this.width, this.y, -1357572843);
        Ref.modified$drawRect(this.x, this.y + this.height, this.x + this.width, this.y + this.height + 0.5f, -1357572843);
        Ref.modified$drawRect(this.x + (float)4, this.y + (float)3, this.x + (float)20, this.y + (float)19, -16747106);
        FontRegistry.getPlayRegular16px().drawString(this.friendRequest.getUsername(), this.x + (float)24, this.y + 2.0f, -1);
        if (this.friendRequest.isFriend()) {
            boolean cancelHovered = mouseX > this.x + (float)24 && mouseX < this.x + (float)52 && mouseY < this.y + this.height && mouseY > this.y + (float)10 && enableMouse;
            FontRegistry.getPlayRegular14px().drawString("CANCEL", this.x + (float)24, this.y + (float)11, cancelHovered ? -52429 : 0x7FFF3333);
        } else {
            boolean acceptHovered = mouseX > this.x + (float)24 && mouseX < this.x + (float)52 && mouseY < this.y + this.height && mouseY > this.y + (float)10 && enableMouse;
            boolean denyHovered = mouseX > this.x + (float)52 && mouseX < this.x + (float)84 && mouseY < this.y + this.height && mouseY > this.y + (float)10 && enableMouse;
            FontRegistry.getPlayRegular16px().drawString("ACCEPT", this.x + (float)24, this.y + (float)11, acceptHovered ? -13369549 : 0x7F33FF33);
            FontRegistry.getPlayRegular16px().drawString("DENY", this.x + (float)56, this.y + (float)11, denyHovered ? -52429 : 0x7FFF3333);
        }
        Ref.getGlBridge().bridge$color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        ResourceLocationBridge location = CheatBreaker.getInstance().getHeadLocation(EnumChatFormattingBridge.getTextWithoutFormattingCodes(this.friendRequest.getUsername()));
        RenderUtil.drawIcon(location, (float)7, this.x + (float)5, this.y + (float)4);
        Ref.getGlBridge().bridge$popMatrix();
    }

    @Override
    public boolean handleElementMouseClicked(float mouseX, float mouseY, int mouseButton, boolean enableMouse) {
        if (!enableMouse) {
            return false;
        }
        if (this.friendRequest.isFriend()) {
            boolean cancelHovered  = mouseX > this.x + (float) 24 && mouseX < this.x + (float) 52 && mouseY < this.y + this.height && mouseY > this.y + (float) 10;
            if (cancelHovered) {
                this.mc.bridge$getSoundHandler().bridge$playSound(Ref.getInstanceCreator().createSoundFromPSR(Ref.getInstanceCreator().createResourceLocation("gui.button.press"), 1.0f));
                CheatBreaker.getInstance().getAssetsWebSocket().sendToServer(new WSPacketClientFriendRequestUpdate(false, this.friendRequest.getPlayerId()));
                OverlayGui.getInstance().getFriendRequestsElement().getFrientRequestElementList().add(this);
            }
        } else {
            boolean acceptHovered = mouseX > this.x + (float)24 && mouseX < this.x + (float)52 && mouseY < this.y + this.height && mouseY > this.y + (float)10;
            boolean denyHovered = mouseX > this.x + (float) 52 && mouseX < this.x + (float) 84 && mouseY < this.y + this.height && mouseY > this.y + (float) 10;
            if (acceptHovered) {
                this.mc.bridge$getSoundHandler().bridge$playSound(Ref.getInstanceCreator().createSoundFromPSR(Ref.getInstanceCreator().createResourceLocation("gui.button.press"), 1.0f));
                CheatBreaker.getInstance().getAssetsWebSocket().sendToServer(new WSPacketClientFriendRequestUpdate(true, this.friendRequest.getPlayerId()));
                OverlayGui.getInstance().getFriendRequestsElement().getFrientRequestElementList().add(this);
            } else if (denyHovered) {
                this.mc.bridge$getSoundHandler().bridge$playSound(Ref.getInstanceCreator().createSoundFromPSR(Ref.getInstanceCreator().createResourceLocation("gui.button.press"), 1.0f));
                CheatBreaker.getInstance().getAssetsWebSocket().sendToServer(new WSPacketClientFriendRequestUpdate(false, this.friendRequest.getPlayerId()));
                OverlayGui.getInstance().getFriendRequestsElement().getFrientRequestElementList().add(this);
            }
        }
        return super.handleElementMouseClicked(mouseX, mouseY, mouseButton, true);
    }

    public FriendRequest getFriendRequest() {
        return this.friendRequest;
    }
}

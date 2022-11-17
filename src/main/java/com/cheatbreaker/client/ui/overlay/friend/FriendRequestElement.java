package com.cheatbreaker.client.ui.overlay.friend;

import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.mainmenu.AbstractElement;
import com.cheatbreaker.client.ui.overlay.OverlayGui;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.ui.util.font.FontRegistry;
import com.cheatbreaker.client.websocket.shared.WSPacketClientFriendRequestUpdate;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocationBridge;
import org.lwjgl.opengl.GL11;

public class FriendRequestElement extends AbstractElement {
    private final FriendRequest friendRequest;

    public FriendRequestElement(FriendRequest friendRequest) {
        this.friendRequest = friendRequest;
    }

    @Override
    public void handleElementDraw(float mouseX, float mouseY, boolean bl) {
        if (bl && this.isMouseInside(mouseX, mouseY)) {
            Ref.modified$drawRect(this.x, this.y, this.x + this.width, this.y + this.height, -13750738);
        }
        GL11.glPushMatrix();
        Ref.modified$drawRect(this.x, this.y - 1.2982457f * 0.3851351f, this.x + this.width, this.y, -1357572843);
        Ref.modified$drawRect(this.x, this.y + this.height, this.x + this.width, this.y + this.height + 0.5f, -1357572843);
        Ref.modified$drawRect(this.x + (float)4, this.y + (float)3, this.x + (float)20, this.y + (float)19, -16747106);
        FontRegistry.getPlayRegular16px().drawString(this.friendRequest.getUsername(), this.x + (float)24, this.y + 2.0f, -1);
        if (this.friendRequest.isFriend()) {
            boolean cancelHovered = mouseX > this.x + (float)24 && mouseX < this.x + (float)52 && mouseY < this.y + this.height && mouseY > this.y + (float)10 && bl;
            FontRegistry.getPlayRegular14px().drawString("CANCEL", this.x + (float)24, this.y + (float)11, cancelHovered ? -52429 : 0x7FFF3333);
        } else {
            boolean acceptHovered = mouseX > this.x + (float)24 && mouseX < this.x + (float)52 && mouseY < this.y + this.height && mouseY > this.y + (float)10 && bl;
            boolean denyHovered = mouseX > this.x + (float)52 && mouseX < this.x + (float)84 && mouseY < this.y + this.height && mouseY > this.y + (float)10 && bl;
            FontRegistry.getPlayRegular16px().drawString("ACCEPT", this.x + (float)24, this.y + (float)11, acceptHovered ? -13369549 : 0x7F33FF33);
            FontRegistry.getPlayRegular16px().drawString("DENY", this.x + (float)56, this.y + (float)11, denyHovered ? -52429 : 0x7FFF3333);
        }
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        ResourceLocationBridge ResourceLocationBridge = CheatBreaker.getInstance().getHeadLocation(EnumChatFormatting.getTextWithoutFormattingCodes(this.friendRequest.getUsername()));
        RenderUtil.drawIcon(ResourceLocationBridge, (float)7, this.x + (float)5, this.y + (float)4);
        GL11.glPopMatrix();
    }

    @Override
    public boolean handleElementMouseClicked(float f, float f2, int n, boolean bl) {
        if (!bl) {
            return false;
        }
        if (this.friendRequest.isFriend()) {
            boolean cancelHovered  = f > this.x + (float) 24 && f < this.x + (float) 52 && f2 < this.y + this.height && f2 > this.y + (float) 10;
            if (cancelHovered) {
                this.mc.getSoundHandler().playSound(Ref.getInstanceCreator().createSoundFromPSR(Ref.getInstanceCreator().createResourceLocationBridge("gui.button.press"), 1.0f));
                CheatBreaker.getInstance().getAssetsWebSocket().sendToServer(new WSPacketClientFriendRequestUpdate(false, this.friendRequest.getPlayerId()));
                OverlayGui.getInstance().getFriendRequestsElement().getFrientRequestElementList().add(this);
            }
        } else {
            boolean acceptHovered = f > this.x + (float)24 && f < this.x + (float)52 && f2 < this.y + this.height && f2 > this.y + (float)10;
            boolean denyHovered = f > this.x + (float) 52 && f < this.x + (float) 84 && f2 < this.y + this.height && f2 > this.y + (float) 10;
            if (acceptHovered) {
                this.mc.getSoundHandler().playSound(Ref.getInstanceCreator().createSoundFromPSR(Ref.getInstanceCreator().createResourceLocationBridge("gui.button.press"), 1.0f));
                CheatBreaker.getInstance().getAssetsWebSocket().sendToServer(new WSPacketClientFriendRequestUpdate(true, this.friendRequest.getPlayerId()));
                OverlayGui.getInstance().getFriendRequestsElement().getFrientRequestElementList().add(this);
            } else if (denyHovered) {
                this.mc.getSoundHandler().playSound(Ref.getInstanceCreator().createSoundFromPSR(Ref.getInstanceCreator().createResourceLocationBridge("gui.button.press"), 1.0f));
                CheatBreaker.getInstance().getAssetsWebSocket().sendToServer(new WSPacketClientFriendRequestUpdate(false, this.friendRequest.getPlayerId()));
                OverlayGui.getInstance().getFriendRequestsElement().getFrientRequestElementList().add(this);
            }
        }
        return super.handleElementMouseClicked(f, f2, n, true);
    }

    public FriendRequest getFriendRequest() {
        return this.friendRequest;
    }
}

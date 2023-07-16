package com.cheatbreaker.client.ui.overlay.friend;

import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.bridge.util.EnumChatFormattingBridge;
import com.cheatbreaker.main.CheatBreaker;
import com.cheatbreaker.client.ui.mainmenu.element.ScrollableElement;
import com.cheatbreaker.client.ui.overlay.Alert;
import com.cheatbreaker.client.ui.overlay.OverlayGui;
import com.cheatbreaker.client.ui.overlay.element.ElementListElement;
import com.cheatbreaker.client.ui.overlay.element.FlatButtonElement;
import com.cheatbreaker.client.ui.overlay.element.InputFieldElement;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.ui.util.font.FontRegistry;
import com.cheatbreaker.client.websocket.client.WSPacketClientRequestsStatus;
import com.cheatbreaker.client.websocket.shared.WSPacketFriendRequest;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

public class FriendRequestListElement extends ElementListElement<FriendRequestElement> {
    private final InputFieldElement filter;
    private final InputFieldElement username;
    private final FlatButtonElement addButton;
    private final FlatButtonElement toggleRequests;
    private final ScrollableElement scrollableElement;
    private final List<FriendRequestElement> friendRequestElements = new ArrayList<>();

    public FriendRequestListElement(List<FriendRequestElement> list) {
        super(list);
        this.filter = new InputFieldElement(FontRegistry.getPlayRegular16px(), "Filter", 0x2FFFFFFF, 0x6FFFFFFF);
        this.username = new InputFieldElement(FontRegistry.getPlayRegular16px(), "Username", 0x2FFFFFFF, 0x6FFFFFFF);
        this.addButton = new FlatButtonElement("ADD");
        this.toggleRequests = new FlatButtonElement("");
        this.scrollableElement = new ScrollableElement(this);
    }

    public void resetSize() {
        this.setElementDimensions(this.x, this.y, this.width, this.height);
    }

    @Override
    public void setElementDimensions(float x, float y, float width, float height) {
        super.setElementDimensions(x, y, width, height);
        this.scrollableElement.setElementDimensions(x + width - (float)4, y, (float)4, height);
        int n = 22;
        int n2 = 0;
        for (FriendRequestElement friendRequestElement : this.elements) {
            if (!this.isFilterMatch(friendRequestElement)) continue;
            friendRequestElement.setElementDimensions(x, y + (float)14 + (float)(n2 * 22), width, 22);
            ++n2;
        }
        float f5 = 14 + this.elements.size() * 22 + 30;
        if (f5 < height) {
            f5 = height;
        }
        this.filter.setElementDimensions(0.0f, y, width, 13);
        this.username.setElementDimensions(0.0f, y + f5 - (float)13, width - (float)35, 13);
        this.addButton.setElementDimensions(width - (float)35, y + f5 - (float)13, (float)35, 13);
        this.toggleRequests.setElementDimensions(0.0f, y + f5 - (float)26, width, 13);
        this.scrollableElement.setScrollAmount(f5);
    }

    private boolean isFilterMatch(FriendRequestElement friendRequestElement) {
        return this.filter.getText().equals("") || EnumChatFormattingBridge.getTextWithoutFormattingCodes(friendRequestElement.getFriendRequest().getUsername()).toLowerCase().startsWith(this.filter.getText().toLowerCase());
    }

    @Override
    public void handleElementDraw(float mouseX, float mouseY, boolean enableMouse) {
        if (!this.friendRequestElements.isEmpty()) {
            this.elements.removeAll(this.friendRequestElements);
            OverlayGui.getInstance().getFriendRequestsElement().resetSize();
            this.friendRequestElements.clear();
        }
        if (!CheatBreaker.getInstance().getAssetsWebSocket().isOpen()) {
            FontRegistry.getPlayRegular16px().drawCenteredString("Connection lost", this.x + this.width / 2.0f, this.y + (float)10, -1);
            FontRegistry.getPlayRegular16px().drawCenteredString("Please try again later.", this.x + this.width / 2.0f, this.y + (float)22, -1);
        } else {
            Ref.getGlBridge().bridge$pushMatrix();
            Ref.getGlBridge().bridge$enableScissoring();
            OverlayGui illlllIllIIIllIIIllIllIII = OverlayGui.getInstance();
            this.scrollableElement.drawScrollable(mouseX, mouseY, enableMouse);
            RenderUtil.scissorArea((int)this.x, (int)this.y, (int)(this.x + this.width), (int)(this.y + this.height), (float)((int)((float)illlllIllIIIllIIIllIllIII.getResolution().bridge$getScaleFactor() * illlllIllIIIllIIIllIllIII.getScaleFactor())), (int)illlllIllIIIllIIIllIllIII.getScaledHeight());
            ImmutableList<FriendRequestElement> immutableList = ImmutableList.copyOf(this.elements);
            for (FriendRequestElement friendRequestElement : immutableList) {
                if (!this.isFilterMatch(friendRequestElement)) continue;
                friendRequestElement.drawElement(mouseX, mouseY - this.scrollableElement.getTranslateY(), enableMouse);
            }
            if (immutableList.isEmpty()) {
                FontRegistry.getPlayRegular16px().drawCenteredString("No friend requests", this.x + this.width / 2.0f, this.y + (float)30, -1);
            }
            this.filter.drawElement(mouseX, mouseY - this.scrollableElement.getTranslateY(), true);
            this.username.drawElement(mouseX, mouseY, true);
            this.addButton.drawElement(mouseX, mouseY, true);
            this.toggleRequests.render((CheatBreaker.getInstance().isAcceptingFriendRequests() ? "Disable" : "Enable") + " incoming friend requests", mouseX, mouseY, true);
            Ref.getGlBridge().bridge$disableScissoring();
            Ref.getGlBridge().bridge$popMatrix();
            this.scrollableElement.handleElementDraw(mouseX, mouseY, enableMouse);
        }
    }

    @Override
    public void handleElementMouse() {
        this.scrollableElement.handleElementMouse();
    }

    @Override
    public void handleElementUpdate() {
        this.filter.handleElementUpdate();
        this.username.handleElementUpdate();
        this.toggleRequests.handleElementUpdate();
        this.addButton.handleElementUpdate();
        this.scrollableElement.handleElementUpdate();
    }

    @Override
    public void handleElementClose() {
        this.filter.handleElementClose();
        this.scrollableElement.handleElementClose();
    }

    @Override
    public void handleElementKeyTyped(char c, int n) {
        super.handleElementKeyTyped(c, n);
        this.filter.handleElementKeyTyped(c, n);
        this.username.handleElementKeyTyped(c, n);
        this.toggleRequests.handleElementKeyTyped(c, n);
        this.addButton.handleElementKeyTyped(c, n);
        this.scrollableElement.handleElementKeyTyped(c, n);
        if (this.username.lllIIIIIlIllIlIIIllllllII() && n == 28) {
            this.lIIlIlIllIIlIIIlIIIlllIII();
        }
        this.setElementDimensions(this.x, this.y, this.width, this.height);
    }

    @Override
    public boolean handleElementMouseClicked(float mouseX, float mouseY, int mouseButton, boolean enableMouse) {
        this.filter.handleElementMouseClicked(mouseX, mouseY - this.scrollableElement.getTranslateY(), mouseButton, enableMouse);
        this.username.handleElementMouseClicked(mouseX, mouseY - this.scrollableElement.getTranslateY(), mouseButton, enableMouse);
        if (this.filter.lllIIIIIlIllIlIIIllllllII() && mouseButton == 1 && this.filter.getText().equals("")) {
            this.resetSize();
        }
        if (!enableMouse) {
            return false;
        }
        this.addButton.handleElementMouseClicked(mouseX, mouseY - this.scrollableElement.getTranslateY(), mouseButton, enableMouse);
        this.toggleRequests.handleElementMouseClicked(mouseX, mouseY - this.scrollableElement.getTranslateY(), mouseButton, enableMouse);
        this.scrollableElement.handleElementMouseClicked(mouseX, mouseY, mouseButton, enableMouse);
        if (this.addButton.isMouseInside(mouseX, mouseY - this.scrollableElement.getTranslateY())) {
            this.lIIlIlIllIIlIIIlIIIlllIII();
        }
        if (this.toggleRequests.isMouseInside(mouseX, mouseY - this.scrollableElement.getTranslateY())) {
            CheatBreaker.getInstance().getAssetsWebSocket().sendToServer(new WSPacketClientRequestsStatus(!CheatBreaker.getInstance().isAcceptingFriendRequests()));
            CheatBreaker.getInstance().setAcceptingFriendRequests(!CheatBreaker.getInstance().isAcceptingFriendRequests());
            return false;
        }
        boolean bl2 = false;
        for (FriendRequestElement ilIIlIllIllllIIlIllllIlII : this.elements) {
            if (!this.isFilterMatch(ilIIlIllIllllIIlIllllIlII)) continue;
            if (bl2) break;
            bl2 = ilIIlIllIllllIIlIllllIlII.handleElementMouseClicked(mouseX, mouseY - this.scrollableElement.getTranslateY(), mouseButton, enableMouse);
        }
        return bl2;
    }

    private void lIIlIlIllIIlIIIlIIIlllIII() {
        if (!this.username.getText().isEmpty()) {
            this.mc.bridge$getSoundHandler().bridge$playSound(Ref.getInstanceCreator().createSoundFromPSR(Ref.getInstanceCreator().createResourceLocation("gui.button.press"), 1.0f));
            String string = this.username.getText();
            // sanitizes the name of the friend before sending the packet.
            if (string.matches("([a-zA-Z0-9_]+)") && string.length() <= 16) {
                CheatBreaker.getInstance().getAssetsWebSocket().sendToServer(new WSPacketFriendRequest("", this.username.getText()));
                this.username.setText("");
            } else {
                Alert.displayMessage(EnumChatFormattingBridge.RED + "Error!", "Incorrect username.");
            }
        }
    }

    @Override
    public boolean handleElementMouseRelease(float f, float f2, int n, boolean bl) {
        if (!bl) {
            return false;
        }
        this.filter.handleElementMouseRelease(f, f2 - this.scrollableElement.getTranslateY(), n, bl);
        this.username.handleElementMouseRelease(f, f2 - this.scrollableElement.getTranslateY(), n, bl);
        this.addButton.handleElementMouseRelease(f, f2 - this.scrollableElement.getTranslateY(), n, bl);
        this.scrollableElement.handleElementMouseRelease(f, f2 - this.scrollableElement.getTranslateY(), n, bl);
        boolean bl2 = false;
        for (FriendRequestElement ilIIlIllIllllIIlIllllIlII : this.elements) {
            if (!this.isFilterMatch(ilIIlIllIllllIIlIllllIlII)) continue;
            if (bl2) break;
            bl2 = ilIIlIllIllllIIlIllllIlII.handleElementMouseRelease(f, f2 - this.scrollableElement.getTranslateY(), n, bl);
        }
        return bl2;
    }

//    public FlatButtonElement IlllIllIlIIIIlIIlIIllIIIl() {
//        return this.addButton;
//    }
//
//    public FlatButtonElement getToggleRequestsButton() {
//        return this.toggleRequests;
//    }

    public List<FriendRequestElement> getFrientRequestElementList() {
        return this.friendRequestElements;
    }
}

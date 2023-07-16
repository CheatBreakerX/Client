package com.cheatbreaker.client.ui.overlay.element;

import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.client.ui.fading.CosineFade;
import com.cheatbreaker.client.ui.mainmenu.element.ScrollableElement;
import com.cheatbreaker.client.ui.overlay.OverlayGui;
import com.cheatbreaker.client.ui.util.font.FontRegistry;
import com.cheatbreaker.client.util.friend.Friend;
import com.cheatbreaker.client.util.thread.AliasesThread;
import java.util.ArrayList;
import java.util.List;

public class AliasesElement
        extends DraggableElement {
    private final ScrollableElement scrollContainer;
    private final Friend friend;
    private final FlatButtonElement closeButton;
    private final CosineFade cosineFade;
    private List<String> aliases = new ArrayList<>();

    public AliasesElement(Friend friend) {
        this.scrollContainer = new ScrollableElement(this);
        this.friend = friend;
        this.closeButton = new FlatButtonElement("X");
        this.cosineFade = new CosineFade(1500L);
        this.cosineFade.reset();
        this.cosineFade.enableShouldResetOnceCalled();
        new AliasesThread(this).start();
    }

    private float IlIlllIIIIllIllllIllIIlIl() {
        return this.cosineFade.getCurrentValue() * 2.0f - 1.0f;
    }

    @Override
    public void setElementDimensions(float x, float y, float width, float height) {
        super.setElementDimensions(x, y, width, height);
        this.scrollContainer.setElementDimensions(x + width - (float)4, y, (float)4, height);
        this.scrollContainer.setScrollAmount(height);
        this.closeButton.setElementDimensions(x + width - (float)12, y + 2.0f, (float)10, 10);
    }

    @Override
    protected void handleElementDraw(float mouseX, float mouseY, boolean enableMouse) {
        this.drag(mouseX, mouseY);
        this.scrollContainer.drawScrollable(mouseX, mouseY, enableMouse);
        Ref.modified$drawBoxWithOutLine(this.x, this.y, this.x + this.width, this.y + this.height, 0.06666667f * 7.5f, -16777216, -14869219);
        FontRegistry.getPlayRegular16px().drawString(this.friend.getName(), this.x + (float)4, this.y + (float)4, -1);
        Ref.modified$drawRect(this.x + (float)3, this.y + (float)15, this.x + this.width - (float)3, this.y + 0.9791667f * 15.829787f, 0x2FFFFFFF);
        if (this.aliases.isEmpty()) {
            Ref.modified$drawRect(this.x + (float)4, this.y + this.height - (float)9, this.x + this.width - (float)4, this.y + this.height - (float)5, -13158601);
            float f3 = this.x + this.width / 2.0f - (float)10 + (this.width - (float)28) * this.IlIlllIIIIllIllllIllIIlIl() / 2.0f;
            Ref.modified$drawRect(f3, this.y + this.height - (float)9, f3 + (float)20, this.y + this.height - (float)5, -4180940);
        }
        int n = 0;
        for (String string : this.aliases) {
            FontRegistry.getPlayRegular16px().drawString(string, this.x + (float)4, this.y + (float)18 + (float)(n * 10), -1);
            ++n;
        }
        this.scrollContainer.handleElementDraw(mouseX, mouseY, enableMouse);
        this.closeButton.handleElementDraw(mouseX, mouseY, enableMouse);
    }

    @Override
    public boolean handleElementMouseClicked(float mouseX, float mouseY, int mouseButton, boolean enableMouse) {
        if (!enableMouse) {
            return false;
        }
        this.scrollContainer.handleElementMouseClicked(mouseX, mouseY, mouseButton, enableMouse);
        this.closeButton.handleElementMouseClicked(mouseX, mouseY, mouseButton, enableMouse);
        if (this.closeButton.isMouseInside(mouseX, mouseY)) {
            this.mc.bridge$getSoundHandler().bridge$playSound(Ref.getInstanceCreator().createSoundFromPSR(Ref.getInstanceCreator().createResourceLocation("gui.button.press"), 1.0f));
            OverlayGui.getInstance().removeElements(this);
            return true;
        }
        if (this.isMouseInside(mouseX, mouseY)) {
            this.updateDraggingPosition(mouseX, mouseY);
        }
        return false;
    }

    public Friend getFriend() {
        return this.friend;
    }

    public List<String> getAliases() {
        return this.aliases;
    }
}

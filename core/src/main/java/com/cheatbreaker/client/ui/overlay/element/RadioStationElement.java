package com.cheatbreaker.client.ui.overlay.element;

import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.bridge.util.ResourceLocationBridge;
import com.cheatbreaker.main.CheatBreaker;
import com.cheatbreaker.client.ui.mainmenu.AbstractElement;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.ui.util.font.FontRegistry;
import com.cheatbreaker.client.util.dash.DashUtil;
import com.cheatbreaker.client.util.dash.Station;

public class RadioStationElement
        extends AbstractElement {
    private final Station station;
    private final ResourceLocationBridge starIcon = Ref.getInstanceCreator().createResourceLocation("client/icons/star-21.png");
    private final ResourceLocationBridge startFilledIcon = Ref.getInstanceCreator().createResourceLocation("client/icons/star-filled-21.png");
    private final RadioElement parent;

    public RadioStationElement(RadioElement parent, Station station) {
        this.parent = parent;
        this.station = station;
    }

    @Override
    protected void handleElementDraw(float mouseX, float mouseY, boolean enableMouse) {
        if (this.isMouseInsideElement(mouseX, mouseY) && enableMouse) {
            Ref.modified$drawRect(this.x, this.y, this.x + (float)22, this.y + this.height, -13158601);
        } else if (this.isMouseInside(mouseX, mouseY) && enableMouse) {
            Ref.modified$drawRect(this.x, this.y, this.x + this.width, this.y + this.height, -13158601);
        }
        boolean bl2 = this.station.isPlay();
        if (bl2) {
            Ref.getGlBridge().bridge$color(0.95f, 0.72f, 0.15f, 1.0f);
        } else {
            Ref.getGlBridge().bridge$color(1.0f, 1.0f, 1.0f, 1.0f);
        }
        boolean bl3 = CheatBreaker.getInstance().getRadioManager().getCurrentStation() == this.station;
        RenderUtil.drawIcon(bl2 ? this.startFilledIcon : this.starIcon, (float)5, this.width + (float)6, this.height + (float)5);
        FontRegistry.getPlayRegular14px().drawString(this.station.getName(), this.x + (float)24, this.y + 0.627451f * 2.390625f, bl3 ? -13369549 : -1);
        FontRegistry.getPlayRegular12px().drawString(this.station.getGenre(), this.x + (float)24, this.y + 2.375f * 4.0f, -1342177281);
    }

    private boolean isMouseInsideElement(float f, float f2) {
        return this.isMouseInside(f, f2) && f < this.x + (float)22;
    }

    @Override
    public boolean handleElementMouseClicked(float mouseX, float mouseY, int mouseButton, boolean enableMouse) {
        if (!enableMouse) {
            return false;
        }
        if (this.isMouseInsideElement(mouseX, mouseY) && enableMouse) {
            this.station.setFavourite(!this.station.isFavourite());
            this.parent.resetSize();
            return true;
        }
        if (this.isMouseInside(mouseX, mouseY) && enableMouse) {
            if (station.isPlay()) {
                DashUtil.end();
            }
            this.station.play = true;
            CheatBreaker.getInstance().getRadioManager().getDashQueueThread().offerStation(this.station);
            CheatBreaker.getInstance().getRadioManager().setStation(this.station);
        }
        return false;
    }

    public Station getStation() {
        return this.station;
    }
}

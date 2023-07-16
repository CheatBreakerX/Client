package com.cheatbreaker.client.ui.overlay.element;

import com.cheatbreaker.bridge.client.renderer.ThreadDownloadImageDataBridge;
import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.bridge.util.ResourceLocationBridge;
import com.cheatbreaker.main.CheatBreaker;
import com.cheatbreaker.client.ui.fading.MinMaxFade;
import com.cheatbreaker.client.ui.mainmenu.element.ScrollableElement;
import com.cheatbreaker.client.ui.overlay.OverlayGui;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.ui.util.font.FontRegistry;
import com.cheatbreaker.client.util.dash.DashUtil;
import com.cheatbreaker.client.util.dash.Station;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class RadioElement extends DraggableElement {
    private final ResourceLocationBridge dashIcon = Ref.getInstanceCreator().createResourceLocation("client/dash-logo-54.png");
    private final ResourceLocationBridge playIcon = Ref.getInstanceCreator().createResourceLocation("client/icons/play-24.png");
    private final List<RadioStationElement> radioStationElements;
    private final MinMaxFade fade = new MinMaxFade(300L);
    private float cachedHeight;
    private boolean hovered;
    private final HorizontalSliderElement slider;
    private final ScrollableElement scrollableContainer;
    private final InputFieldElement filter;
    private final FlatButtonElement pin;

    public RadioElement() {
        this.slider = new HorizontalSliderElement(CheatBreaker.getInstance().getGlobalSettings().radioVolume);
        this.scrollableContainer = new ScrollableElement(this);
        this.filter = new InputFieldElement(FontRegistry.getPlayRegular16px(), "Filter", -11842741, -11842741);
        this.pin = new FlatButtonElement(this.client.getGlobalSettings().pinRadio.<Boolean>value() ? "Unpin" : "Pin");
        this.radioStationElements = new ArrayList<>();
        for (Station station : CheatBreaker.getInstance().getRadioManager().getStations()) {
            this.radioStationElements.add(new RadioStationElement(this, station));
        }
    }

    public void resetSize() {
        this.setElementDimensions(this.x, this.y, this.width, this.height);
    }

    private boolean isFilterMatch(RadioStationElement radioStationElement) {
        return this.filter.getText().equals("") || radioStationElement.getStation().getName().toLowerCase()
                .startsWith(this.filter.getText().toLowerCase());
    }

    @Override
    public void setElementDimensions(float x, float y, float width, float height) {
        super.setElementDimensions(x, y, width, height);
        if (this.cachedHeight == 0.0f) {
            this.cachedHeight = height;
        }
        this.radioStationElements.sort(Comparator.comparing(elem -> elem.getStation().getName()));
        this.slider.setElementDimensions(x, y + this.cachedHeight, width, 8f);
        this.filter.setElementDimensions(x, y + this.cachedHeight + 8f, width - 30f, 13f);
        this.pin.setElementDimensions(x + width - 30f, y + this.cachedHeight + 8f, 30f, 13f);
        this.scrollableContainer.setElementDimensions(x + width - 5f, y + this.cachedHeight + 21f,
                5f, 99f);

        int n = 0;
        for (RadioStationElement radioStationElement : this.radioStationElements) {
            if (!this.isFilterMatch(radioStationElement)) continue;
            float f5 = y + 20f + this.cachedHeight + n;
            radioStationElement.setElementDimensions(x, f5, width - 5f, 20f);
            n += 20;
        }

        this.scrollableContainer.setScrollAmount(n);
    }

    @Override
    protected void handleElementDraw(float mouseX, float mouseY, boolean enableMouse) {
        this.drag(mouseX, mouseY);
        Ref.modified$drawRect(this.x, this.y, this.x + this.width, this.y + this.cachedHeight, -14540254);
        Station station = CheatBreaker.getInstance().getRadioManager().getCurrentStation();
        if (station != null) {
            if (station.currentResource == null && !station.getName().equals("")) {
                if (station.currentResource != null) {
                    this.mc.bridge$getTextureManager().bridge$deleteTexture(station.currentResource);
                    station.currentResource = null;
                }
                station.currentResource = Ref.getInstanceCreator().createResourceLocation("client/songs/"
                        + station.getTitleForResource());
                ThreadDownloadImageDataBridge threadDownloadImageData = Ref.getInstanceCreator()
                        .createThreadDownloadImageData(null, station.getCoverURL(), station.currentResource,
                                this.dashIcon, null);
                try {
                    Ref.getMinecraft().bridge$getTextureManager().bridge$loadTexture(station.currentResource, threadDownloadImageData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Ref.getGlBridge().bridge$color(1f, 1f, 1f, 1f);
            ResourceLocationBridge location = station.currentResource == null ? this.dashIcon : station.currentResource;
            RenderUtil.drawIcon(location, this.cachedHeight / 2f, this.x, this.y);
            float f3 = this.x + 50f;
            if (this.mc.bridge$getCurrentScreen() == OverlayGui.getInstance()) {
                boolean bl2 = this.isMouseInside(mouseX, mouseY) && mouseX > this.x + 34f && mouseX < this.x + 44f && mouseY < this.y + this.cachedHeight;
                if (!DashUtil.isPlayerNotNull()) {
                    Ref.getGlBridge().bridge$color(1.0f, 1.0f, 1.0f, bl2 ? 1.0f : 0.8f);
                    RenderUtil.drawIcon(this.playIcon, 6f, this.x + (float)34, this.y + 7.5f);
                } else {
                    Ref.modified$drawRect(this.x + 36f, this.y + 9f, this.x + 38f, this.y + this.cachedHeight - 11f, bl2 ? -1 : -1342177281);
                    Ref.modified$drawRect(this.x + 40f, this.y + 9f, this.x + 42f, this.y + this.cachedHeight - 11f, bl2 ? -1 : -1342177281);
                }
            } else {
                f3 = this.x + (float)34;
            }
            String string = station.getName();
            FontRegistry.getPlayRegular16px().drawString(string, f3, this.y + 4f, -1);
            FontRegistry.getPlayRegular12px().drawString(station.getArtist(), f3, this.y + 14f, -1342177281);
        }
        float f4 = this.fade.lIIIIlIIllIIlIIlIIIlIIllI(this.isMouseInside(mouseX, mouseY) && enableMouse);
        if (this.fade.isFadeOngoing()) {
            this.setElementDimensions(this.x, this.y, this.width, this.cachedHeight + 120f * f4);
            this.hovered = true;
        } else if (!this.fade.isFadeOngoing() && !this.isMouseInside(mouseX, mouseY)) {
            this.hovered = false;
        }
        if (this.hovered) {
            Ref.getGlBridge().bridge$pushMatrix();
            Ref.getGlBridge().bridge$enableScissoring();
            OverlayGui overlayGui = OverlayGui.getInstance();
            RenderUtil.scissorArea(
                    (int)this.x,
                    (int)(this.y + this.cachedHeight),
                    (int)(this.x + this.width),
                    (int)(this.y + this.cachedHeight + (this.height - this.cachedHeight) * f4),
                    (float)((int)((float)overlayGui.getResolution().bridge$getScaleFactor()
                            * overlayGui.getScaleFactor())),
                    (int)overlayGui.getScaledHeight());
            Ref.modified$drawRect(this.x, this.y + this.cachedHeight, this.x + this.width,
                    this.y + this.height, -14540254);
            this.scrollableContainer.drawScrollable(mouseX, mouseY, enableMouse);
            for (RadioStationElement radioStationElement : this.radioStationElements) {
                if (!this.isFilterMatch(radioStationElement)) continue;
                radioStationElement.handleElementDraw(mouseX, mouseY - this.scrollableContainer.getTranslateY(),
                        enableMouse
                                && !this.scrollableContainer.isDragClick()
                                && !this.scrollableContainer.isMouseInside(mouseX, mouseY));
            }
            this.scrollableContainer.handleElementDraw(mouseX, mouseY, enableMouse);
            this.slider.drawElement(mouseX, mouseY, enableMouse);
            this.filter.handleElementDraw(mouseX, mouseY, enableMouse);
            this.pin.handleElementDraw(mouseX, mouseY, enableMouse);
            Ref.getGlBridge().bridge$disableScissoring();
            Ref.getGlBridge().bridge$popMatrix();
        }
    }

    @Override
    public void handleElementMouse() {
        this.scrollableContainer.handleElementMouse();
    }

    @Override
    public void handleElementUpdate() {
        this.filter.handleElementUpdate();
        this.pin.handleElementUpdate();
    }

    @Override
    public void handleElementClose() {
        this.filter.handleElementClose();
        this.pin.handleElementClose();
    }

    @Override
    public void handleElementKeyTyped(char c, int n) {
        this.filter.handleElementKeyTyped(c, n);
        this.pin.handleElementKeyTyped(c, n);
        this.scrollableContainer.handleElementKeyTyped(c, n);
        if (this.filter.lllIIIIIlIllIlIIIllllllII()) {
            this.resetSize();
        }
    }

    @Override
    public boolean handleMouseClickedInternal(float f, float f2, int n) {
        if (!this.filter.isMouseInside(f, f2) && this.filter.lllIIIIIlIllIlIIIllllllII()) {
            this.filter.lIIIIIIIIIlIllIIllIlIIlIl(false);
        }
        return false;
    }

    @Override
    public boolean handleElementMouseClicked(float mouseX, float mouseY, int mouseButton, boolean enableMouse) {
        this.filter.handleElementMouseClicked(mouseX, mouseY, mouseButton, enableMouse);
        if (this.filter.lllIIIIIlIllIlIIIllllllII() && mouseButton == 1 && this.filter.getText().equals("")) {
            this.resetSize();
        }
        if (!enableMouse) {
            return false;
        }
        boolean bl2 = this.isMouseInside(mouseX, mouseY) && mouseX > this.x + (float) 34 && mouseX < this.x + (float) 44 && mouseY < this.y + this.cachedHeight;
        if (bl2) {
            if (!DashUtil.isPlayerNotNull()) {
                CheatBreaker.getInstance().getRadioManager().getCurrentStation().endStream();
            } else {
                DashUtil.end();
            }
        }
        float f3 = this.fade.lIIIIlIIllIIlIIlIIIlIIllI(this.isMouseInside(mouseX, mouseY));
        if (this.fade.isCurrentlyInverted()) {
            this.slider.handleElementMouseClicked(mouseX, mouseY, mouseButton, true);
            this.scrollableContainer.handleElementMouseClicked(mouseX, mouseY, mouseButton, true);
            this.filter.handleElementMouseClicked(mouseX, mouseY, mouseButton, true);
            this.pin.handleElementMouseClicked(mouseX, mouseY, mouseButton, true);
            boolean bl4 = mouseX > (float)((int)this.x)
                    && mouseX < (float)((int)(this.x + this.width))
                    && mouseY > (float)((int)(this.y + this.cachedHeight + 21f))
                    && mouseY < (float)((int)(this.y + this.cachedHeight + 21f
                        + (this.height - this.cachedHeight - 21f) * f3));
            if (bl4) {
                RadioStationElement element;
                Iterator<RadioStationElement> iterator = this.radioStationElements.iterator();
                while (!(!iterator.hasNext() || this.isFilterMatch(element = iterator.next())
                        && element.handleElementMouseClicked(mouseX, mouseY - this.scrollableContainer.getTranslateY(),
                        mouseButton, enableMouse))) {
                    // Was there supposed to be something here?
                }
            }
            if (this.pin.isMouseInside(mouseX, mouseY)) {
                this.client.getGlobalSettings().pinRadio.setValue(!this.client.getGlobalSettings().pinRadio.<Boolean>value());
                this.pin.setText(this.client.getGlobalSettings().pinRadio.<Boolean>value() ? "Unpin" : "Pin");
            }
        }
        if (this.isMouseInside(mouseX, mouseY) && mouseY < this.y + this.cachedHeight && !bl2 && !this.slider.isMouseInside(mouseX, mouseY) && !this.scrollableContainer.isMouseInside(mouseX, mouseY)) {
            this.updateDraggingPosition(mouseX, mouseY);
        }
        return super.handleElementMouseClicked(mouseX, mouseY, mouseButton, true);
    }
}


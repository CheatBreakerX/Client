package com.cheatbreaker.client.ui;

import com.cheatbreaker.bridge.client.MinecraftBridge;
import com.cheatbreaker.bridge.client.gui.ScaledResolutionBridge;
import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.bridge.wrapper.CBGuiScreen;
import com.cheatbreaker.main.CheatBreaker;
import com.cheatbreaker.client.ui.mainmenu.AbstractElement;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractGui extends CBGuiScreen {
    @Getter
    protected ScaledResolutionBridge resolution;
    @Getter
    protected float scaledWidth;
    @Getter
    protected float scaledHeight;
    @Getter
    protected List<AbstractElement> elements;
    protected int elementListSize = 0;

    @Override
    public void setWorldAndResolution(final MinecraftBridge mc, final int displayWidth, final int displayHeight) {
        this.mc = mc;
        this.fontRendererObj = mc.bridge$getFontRenderer();
        this.width = displayWidth;
        this.height = displayHeight;
        this.buttonList.clear();
        this.resolution = Ref.getInstanceCreator().createScaledResolution();
        final float scaleFactor = getScaleFactor();
        this.scaledWidth = width / scaleFactor;
        this.scaledHeight = height / scaleFactor;
        this.initGui();
        this.setExternalValues();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float delta) {
        final float scaleFactor = getScaleFactor();
        Ref.getGlBridge().bridge$pushMatrix();
        Ref.getGlBridge().bridge$scale(scaleFactor, scaleFactor, scaleFactor);
        drawMenu(mouseX / scaleFactor, mouseY / scaleFactor);
        Ref.getGlBridge().bridge$popMatrix();
    }

    protected abstract void drawMenu(float mouseX, float mouseY);

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        final float scaleFactor = getScaleFactor();
        onMouseClicked(mouseX / scaleFactor, mouseY / scaleFactor, mouseButton);
    }

    protected abstract void onMouseClicked(float mouseX, float mouseY, int mouseButton);

    @Override
    public void mouseMovedOrUp(int mouseX, int mouseY, int mouseButton) {
        final float scaleFactor = getScaleFactor();
        onMouseMovedOrUp(mouseX / scaleFactor, mouseY / scaleFactor, mouseButton);
    }

    protected abstract void onMouseMovedOrUp(float mouseX, float mouseY, int mouseButton);

    protected void setElementsAndUpdateSize(AbstractElement... elements) {
        this.elements = new ArrayList<>();
        this.elements.addAll(Arrays.asList(elements));
        this.elementListSize = this.elements.size();
    }

    public void addElements(AbstractElement... elements) {
        this.elements.addAll(Arrays.asList(elements));
        this.initGui();
    }

    public void removeElements(AbstractElement... elements) {
        this.elements.removeAll(Arrays.asList(elements));
        this.initGui();
    }

    protected void handleMouse() {
        this.elements.forEach(AbstractElement::handleElementMouse);
    }

    protected void handleClose() {
        this.elements.forEach(AbstractElement::handleElementClose);
    }

    protected void updateElements() {
        this.elements.forEach(AbstractElement::handleElementUpdate);
    }

    protected void handleKeyTyped(char c, int n) {
        this.elements.forEach(element -> element.handleElementKeyTyped(c, n));
    }

    protected void drawElements(float f, float f2, AbstractElement... elements) {
        List<AbstractElement> list = Arrays.asList(elements);
        for (AbstractElement element : this.elements) {
            if (list.contains(element)) {
                continue;
            }
            element.drawElement(f, f2, this.isMouseHovered(element, f, f2));
        }
    }

    protected void handlemouseMovedOrUp(float f, float f2, int n) {
        AbstractElement element;
        Iterator<AbstractElement> iterator = this.elements.iterator();
        while (!(!iterator.hasNext() || (element = iterator.next()).isMouseInside(f, f2) && element.handleElementMouseRelease(f, f2, n, this.isMouseHovered(element, f, f2)))) {
        }
    }

    protected void onMouseClicked(final float mouseX, final float mouseY, final int n3, final AbstractElement... elementArray) {
        final List<AbstractElement> list = Arrays.asList(elementArray);
        Object o = null;
        boolean b = false;
        for (final AbstractElement element : this.elements) {
            if (list.contains(element)) {
                continue;
            }
            if (!element.isMouseInside(mouseX, mouseY)) {
                continue;
            }
            if (!this.elements.contains(element)) {
                o = element;
            }
            if (element.handleElementMouseClicked(mouseX, mouseY, n3, this.isMouseHovered(element, mouseX, mouseY, elementArray))) {
                b = true;
                break;
            }
        }
        if (b) {
            return;
        }
        if (o != null) {
            this.elements.add(this.elements.remove(this.elements.indexOf(o)));
        }
        final Iterator<AbstractElement> iterator2 = this.elements.iterator();
        while (iterator2.hasNext() && !iterator2.next().handleMouseClickedInternal(mouseX, mouseY, n3)) {
        }
    }

    protected boolean isMouseHovered(AbstractElement element, float f, float f2, AbstractElement... elements) {
        AbstractElement element2;
        List<AbstractElement> list = Arrays.asList(elements);
        boolean bl = true;
        for (int i = this.elements.size() - 1; i >= 0 && (element2 = this.elements.get(i)) != element; --i) {
            if (list.contains(element2) || !element2.isMouseInside(f, f2)) continue;
            bl = false;
            break;
        }
        return bl;
    }

    public float getScaleFactor() {
        if (CheatBreaker.getInstance().globalSettings.followMinecraftScale.<Boolean>value()) {
            return 1f;
        }

        return 1f / (this.resolution.bridge$getScaleFactor() / 2f);
    }

    protected void setElements(AbstractElement... elements) {
        this.elements = new ArrayList<>();
        this.elements.addAll(Arrays.asList(elements));
    }
}

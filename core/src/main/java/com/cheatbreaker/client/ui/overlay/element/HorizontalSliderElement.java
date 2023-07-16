package com.cheatbreaker.client.ui.overlay.element;

import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.ui.fading.AbstractFade;
import com.cheatbreaker.client.ui.fading.MinMaxFade;
import com.cheatbreaker.client.ui.mainmenu.AbstractElement;
import org.lwjgl.input.Mouse;

public class HorizontalSliderElement
        extends AbstractElement {
    private final Setting settingObject;
    private final AbstractFade sliderFade;
    private Number settingValue;

    public HorizontalSliderElement(Setting settingObject) {
        this.settingObject = settingObject;
        this.sliderFade = new MinMaxFade(200L);
        this.settingValue = settingObject.value();
    }

    @Override
    protected void handleElementDraw(float mouseX, float mouseY, boolean enableMouse) {
        Ref.modified$drawRect(this.x, this.y, this.x + this.width, this.y + this.height, -13158601);
        if (!this.sliderFade.hasStartTime()) {
            this.settingValue = this.settingObject.value();
        }
        float f3 = this.settingObject.<Number>value().floatValue();
        float f4 = this.settingObject.getMinimumValue().floatValue();
        float f5 = this.settingObject.getMaximumValue().floatValue();
        float f6 = f3 - this.settingValue.floatValue();
        float f7 = (float)100 * ((this.settingValue.floatValue() + f6 * this.sliderFade.getCurrentValue() - f4) / (f5 - f4));
        Ref.modified$drawRect(this.x, this.y, this.x + this.width / (float)100 * f7, this.y + this.height, -52429);
    }

    @Override
    public boolean handleElementMouseClicked(float mouseX, float mouseY, int mouseButton, boolean enableMouse) {
        if (!enableMouse) {
            return false;
        }
        if (Mouse.isButtonDown(0) && this.isMouseInside(mouseX, mouseY)) {
            this.sliderFade.reset();
            this.settingValue = this.settingObject.value();
            float f3 = this.settingObject.getMinimumValue().floatValue();
            float f4 = this.settingObject.getMaximumValue().floatValue();
            if (mouseX - this.x > this.width / 2.0f) {
                mouseX += 2.0f;
            }
            float f5 = f3 + (mouseX - this.x) * ((f4 - f3) / this.width);
            switch (this.settingObject.getType()) {
                case INTEGER: {
                    this.settingObject.setValue((int) f5);
                    break;
                }
                case FLOAT: {
                    this.settingObject.setValue(f5);
                    break;
                }
                case DOUBLE: {
                    this.settingObject.setValue((double) f5);
                }
            }
        }
        return super.handleElementMouseClicked(mouseX, mouseY, mouseButton, enableMouse);
    }
}

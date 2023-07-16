package com.cheatbreaker.client.ui.module;

import com.cheatbreaker.client.module.AbstractModule;

class ModuleDataHolder {
    protected AbstractModule module;
    protected float xTranslation;
    protected float yTranslation;
    protected float scale;
    protected float scaledWidth;
    protected float scaledHeight;
    protected int mouseY;
    protected int mouseX;
    protected SomeRandomAssEnum unknown;
    protected CBGuiAnchor anchor;
    final CBModulesGui parent;

    public ModuleDataHolder(CBModulesGui parent, AbstractModule module, SomeRandomAssEnum unknown, int mouseX, int mouseY) {
        this.parent = parent;
        this.module = module;
        this.xTranslation = module.getXTranslation();
        this.yTranslation = module.getYTranslation();
        this.scaledWidth = module.width * module.scale.<Float>value();
        this.scaledHeight = module.height * module.scale.<Float>value();
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.unknown = unknown;
        this.scale = module.scale.<Float>value();
        this.anchor = module.getGuiAnchor();
    }
}


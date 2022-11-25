package com.cheatbreaker.bridge.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MixinClass {
    // Added onto "com.cheatbreaker.mixin." - If blank, will auto generate something like
    //  "com.cheatbreaker.mixin.client.MixinMinecraft" for "com.cheatbreaker.bridge.client.MinecraftBridge".
    String customMixin() default "";
}

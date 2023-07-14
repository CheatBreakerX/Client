package com.cheatbreaker.mixin.blaze3d.platform;

import com.cheatbreaker.main.Icons;
import com.cheatbreaker.main.utils.Utility;
import com.mojang.blaze3d.platform.Window;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import javax.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

@Mixin(Window.class)
public abstract class MixinWindow {
    @Shadow @Final private static Logger LOGGER;
    @Shadow @Final private long window;
    @Shadow @Nullable protected abstract ByteBuffer readIconPixels(InputStream inputStream, IntBuffer intBuffer, IntBuffer intBuffer2, IntBuffer intBuffer3) throws IOException;

    /**
     * @author iAmSpace
     * @reason Custom CheatBreaker icon
     */
    @Overwrite
    public void setIcon(InputStream icon16x_unused, InputStream icon32x_unused) {
        InputStream icon16x;
        InputStream icon32x;

        try {
            MemoryStack memoryStack = MemoryStack.stackPush();
            Throwable exception = null;

            icon16x = Utility.base64StringToInputStream(Icons.CHEATBREAKER_ICON_16X);
            icon32x = Utility.base64StringToInputStream(Icons.CHEATBREAKER_ICON_32X);

            try {
                IntBuffer intBuffer = memoryStack.mallocInt(1);
                IntBuffer intBuffer2 = memoryStack.mallocInt(1);
                IntBuffer intBuffer3 = memoryStack.mallocInt(1);
                GLFWImage.Buffer buffer = GLFWImage.mallocStack(2, memoryStack);
                ByteBuffer byteBuffer = this.readIconPixels(icon16x, intBuffer, intBuffer2, intBuffer3);
                if (byteBuffer == null) {
                    throw new IllegalStateException("Could not load icon: " + STBImage.stbi_failure_reason());
                }

                buffer.position(0);
                buffer.width(intBuffer.get(0));
                buffer.height(intBuffer2.get(0));
                buffer.pixels(byteBuffer);
                ByteBuffer byteBuffer2 = this.readIconPixels(icon32x, intBuffer, intBuffer2, intBuffer3);
                if (byteBuffer2 == null) {
                    throw new IllegalStateException("Could not load icon: " + STBImage.stbi_failure_reason());
                }

                buffer.position(1);
                buffer.width(intBuffer.get(0));
                buffer.height(intBuffer2.get(0));
                buffer.pixels(byteBuffer2);
                buffer.position(0);
                GLFW.glfwSetWindowIcon(this.window, buffer);
                STBImage.stbi_image_free(byteBuffer);
                STBImage.stbi_image_free(byteBuffer2);
            } catch (Throwable var19) {
                exception = var19;
                throw var19;
            } finally {
                if (exception != null) {
                    try {
                        memoryStack.close();
                    } catch (Throwable var18) {
                        exception.addSuppressed(var18);
                    }
                } else {
                    memoryStack.close();
                }

            }
        } catch (IOException e) {
            LOGGER.error("[CB] Couldn't set icon", e);
        }
    }
}

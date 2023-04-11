package com.cheatbreaker.common;

import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.bridge.util.ResourceLocationBridge;
import com.cheatbreaker.client.ui.fading.CosineFade;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.ui.util.font.FontRegistry;
import com.cheatbreaker.client.util.SessionServer;
import com.cheatbreaker.main.CheatBreaker;

import java.awt.*;

public class IngameMenu {
    // Logo stuff
    private static final ResourceLocationBridge outerLogo = Ref.getInstanceCreator().createResourceLocation("client/logo_255_outer_remake.png");
    private static final ResourceLocationBridge innerLogo = Ref.getInstanceCreator().createResourceLocation("client/logo_108_inner_remake.png");
    private static final CosineFade rotatingFade = new CosineFade(4000L);
    private static final CosineFade errorMsgFade = new CosineFade(1500L);

    public static void renderRotatingLogo(double scaledWidth, double yPosition) {
        try {
            if (!rotatingFade.hasStartTime()) {
                rotatingFade.reset();
                rotatingFade.enableShouldResetOnceCalled();
            }

            float radius = 18f;
            double x = scaledWidth / 2d - (double)radius;
            Ref.getGlBridge().bridge$pushMatrix();
            Ref.getGlBridge().bridge$color(1.0f, 1.0f, 1.0f, 1.0f);
            Ref.getGlBridge().bridge$translate((float)x, (float)yPosition, 1.0f);
            Ref.getGlBridge().bridge$translate(radius, radius, radius);
            Ref.getGlBridge().bridge$rotate((float)180 * rotatingFade.getCurrentValue(), 0.0f, 0.0f, 1.0f);
            Ref.getGlBridge().bridge$translate(-radius, -radius, -radius);
            RenderUtil.drawIcon(outerLogo, radius, 0.0f, 0.0f);
            Ref.getGlBridge().bridge$popMatrix();
            RenderUtil.drawIcon(innerLogo, radius, (float)x, (float)yPosition);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public static void renderErrorMsgIfExists(float width, float height) {
        boolean bl = false;
        for (SessionServer server : CheatBreaker.getInstance().statusServers) {
            if (server.getStatus() != SessionServer.Status.UP) continue;
            bl = true;
        }
        if (bl) {
            if (!errorMsgFade.hasStartTime()) {
                errorMsgFade.reset();
            }
            errorMsgFade.enableShouldResetOnceCalled();

            Ref.modified$drawRect(width / 2f - 100, height / 4f + 128, width / 2f + 100, height / 4f + 142, 0x6F000000);
            Ref.modified$drawRect(width / 2f - 100, height / 4f + 128, width / 2f + 100, height / 4f + 142, new Color(1.0f, 0.15f, 0.15f, 0.65f * errorMsgFade.getCurrentValue()).getRGB());
            FontRegistry.getUbuntuMedium16px().drawCenteredString("Some login services might be offline".toUpperCase(), width / 2f, height / 4f + 130, -1);
        }
    }
}

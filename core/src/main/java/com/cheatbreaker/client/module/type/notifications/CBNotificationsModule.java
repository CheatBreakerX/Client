package com.cheatbreaker.client.module.type.notifications;

import com.cheatbreaker.bridge.client.gui.ScaledResolutionBridge;
import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.client.event.type.GuiDrawEvent;
import com.cheatbreaker.client.event.type.KeepAliveEvent;
import com.cheatbreaker.client.event.type.TickEvent;
import com.cheatbreaker.client.module.AbstractModule;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CBNotificationsModule extends AbstractModule
{
    public long time;
    private List<Notification> notifications;

    public CBNotificationsModule() {
        super("Notifications");
        this.time = System.currentTimeMillis();
        this.notifications = new ArrayList<>();
        this.addEvent(KeepAliveEvent.class, this::onKeepAlive);
        this.addEvent(TickEvent.class, this::onTick);
        this.addEvent(GuiDrawEvent.class, this::onDraw);
        this.setDefaultState(true);
    }

    private void onKeepAlive(final KeepAliveEvent time) {
        this.time = System.currentTimeMillis();
    }

    private void onTick(final TickEvent event) {
        final Iterator<Notification> iterator = this.notifications.iterator();
        while (iterator.hasNext()) {
            final Notification notification = iterator.next();
            notification.lIIIIlIIllIIlIIlIIIlIIllI();
            if (notification.IIIIllIlIIIllIlllIlllllIl + notification.IlllIIIlIlllIllIlIIlllIlI - System.currentTimeMillis() <= 0L) {
                int ilIlIIIlllIIIlIlllIlIllIl = notification.IIIIllIIllIIIIllIllIIIlIl;
                for (final Notification illlIlIlllIlIlllIIlllIlIl2 : this.notifications) {
                    if (illlIlIlllIlIlllIIlllIlIl2.IIIIllIIllIIIIllIllIIIlIl < notification.IIIIllIIllIIIIllIllIIIlIl) {
                        illlIlIlllIlIlllIIlllIlIl2.IllIIIIIIIlIlIllllIIllIII = 0;
                        illlIlIlllIlIlllIIlllIlIl2.IlIlIIIlllIIIlIlllIlIllIl = ilIlIIIlllIIIlIlllIlIllIl;
                        ilIlIIIlllIIIlIlllIlIllIl = illlIlIlllIlIlllIIlllIlIl2.IIIIllIIllIIIIllIllIIIlIl;
                    }
                }
                iterator.remove();
            }
        }
    }

    private void onDraw(final GuiDrawEvent event) {
        for (Notification notification : this.notifications) {
            notification.lIIIIlIIllIIlIIlIIIlIIllI((int) event.getResolution().bridge$getScaledWidth());
        }
    }

    public void queueNotification(final String type, String content, long duration) {
        final ScaledResolutionBridge scaledResolution = Ref.getInstanceCreator().createScaledResolution();
        if (duration < 2000L) duration = 2000L;
        content = content.replaceAll("&([abcdefghijklmrABCDEFGHIJKLMNR0-9])|(&$)", "\u00a7$1");
        final String lowerCase = type.toLowerCase();
        CBNotificationType resolvedType;
        switch (lowerCase) {
            case "info": {
                resolvedType = CBNotificationType.INFO;
                break;
            }
            case "error": {
                resolvedType = CBNotificationType.ERROR;
                break;
            }
            default: {
                resolvedType = CBNotificationType.DEFAULT;
                break;
            }
        }
        final Notification illlIlIlllIlIlllIIlllIlIl = new Notification(this, scaledResolution, resolvedType, content, duration);
        int ilIlIIIlllIIIlIlllIlIllIl = illlIlIlllIlIlllIIlllIlIl.IlIlIIIlllIIIlIlllIlIllIl - illlIlIlllIlIlllIIlllIlIl.IIIllIllIlIlllllllIlIlIII - 2;
        for (int i = this.notifications.size() - 1; i >= 0; --i) {
            final Notification notification = this.notifications.get(i);
            notification.IllIIIIIIIlIlIllllIIllIII = 0;
            notification.IlIlIIIlllIIIlIlllIlIllIl = ilIlIIIlllIIIlIlllIlIllIl;
            ilIlIIIlllIIIlIlllIlIllIl -= 2 + notification.IIIllIllIlIlllllllIlIlIII;
        }
        this.notifications.add(illlIlIlllIlIlllIIlllIlIl);
    }
}

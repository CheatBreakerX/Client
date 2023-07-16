package com.cheatbreaker.mixin.net.minecraft.client.gui.screens;

import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.client.config.types.UnrecommendedServer;
import com.cheatbreaker.client.ui.mainmenu.MainMenu;
import com.cheatbreaker.client.ui.screens.ConnectionWarningScreen;
import com.cheatbreaker.main.CheatBreaker;
import net.minecraft.DefaultUncaughtExceptionHandler;
import net.minecraft.client.gui.screens.ConnectScreen;
import net.minecraft.client.gui.screens.DisconnectedScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.multiplayer.ClientHandshakePacketListenerImpl;
import net.minecraft.network.Connection;
import net.minecraft.network.ConnectionProtocol;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.protocol.handshake.ClientIntentionPacket;
import net.minecraft.network.protocol.login.ServerboundHelloPacket;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicInteger;

@Mixin(ConnectScreen.class)
public abstract class MixinConnectScreen extends Screen {
    @Shadow private boolean aborted;
    @Shadow private Connection connection;

    @Shadow protected abstract void updateStatus(Component component);

    @Shadow @Final private Screen parent;
    @Shadow @Final private static Logger LOGGER;
    @Shadow @Final private static AtomicInteger UNIQUE_THREAD_ID;
    @Unique
    private boolean unrecommended = false;
    @Unique
    private ConnectionWarningScreen screenInstance = null;

    protected MixinConnectScreen(Component component) {
        super(component);
    }

    /**
     * @author iAmSpace
     * @reason Unrecommended servers feature
     */
    @Overwrite
    private void connect(final String ip, final int port) {
        for (UnrecommendedServer serverData : CheatBreaker.getInstance().getGlobalSettings().unrecommendedServers) {
            if (serverData.matches(ip)) {
                this.unrecommended = true;
                this.screenInstance = new ConnectionWarningScreen(serverData, new MainMenu(), ip, port,
                        this::connectCopy);
            }
        }

        if (!this.unrecommended) {
            this.connectCopy(ip, port);
        }
    }

    @Unique
    private void connectThread(String string, int i) {
        InetAddress inetAddress = null;

        try {
            if (this.aborted) {
                return;
            }

            inetAddress = InetAddress.getByName(string);
            this.connection = Connection.connectToServer(inetAddress, i, this.minecraft.options.useNativeTransport());
            this.connection.setListener(new ClientHandshakePacketListenerImpl(this.connection, this.minecraft,
                    this.parent, this::updateStatus));
            this.connection.send(new ClientIntentionPacket(string, i, ConnectionProtocol.LOGIN));
            this.connection.send(new ServerboundHelloPacket(this.minecraft.getUser().getGameProfile()));
        } catch (UnknownHostException var4) {
            if (this.aborted) {
                return;
            }

            LOGGER.error("Couldn't connect to server", var4);
            this.minecraft.execute(() -> this.minecraft.setScreen(new DisconnectedScreen(this.parent,
                    "connect.failed",
                    new TranslatableComponent("disconnect.genericReason", "Unknown host"))));
        } catch (Exception var5) {
            if (this.aborted) {
                return;
            }

            LOGGER.error("Couldn't connect to server", var5);
            String stringx = inetAddress == null ? var5.toString() : var5.toString().replaceAll(inetAddress
                    + ":" + i, "");
            this.minecraft.execute(() -> this.minecraft.setScreen(new DisconnectedScreen(this.parent,
                    "connect.failed", new TranslatableComponent("disconnect.genericReason", stringx))));
        }

    }

    @Unique
    private void connectCopy(String ip, int port) {
        LOGGER.info("Connecting to {}, {}", ip, port);
        Thread thread = new Thread("Server Connector #" + UNIQUE_THREAD_ID.incrementAndGet()) {
            public void run() {
                MixinConnectScreen.this.connectCopy(ip, port);
            }
        };
        thread.setUncaughtExceptionHandler(new DefaultUncaughtExceptionHandler(LOGGER));
        thread.start();
    }

    @Inject(method = "render", at = @At("RETURN"))
    public void impl$render(int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (this.unrecommended && this.screenInstance != null) {
            Ref.getMinecraft().bridge$displayGuiScreen(this.screenInstance);
        }
    }
}

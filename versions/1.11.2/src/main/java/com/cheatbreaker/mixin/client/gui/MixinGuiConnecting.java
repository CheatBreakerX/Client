package com.cheatbreaker.mixin.client.gui;

import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.client.config.types.UnrecommendedServer;
import com.cheatbreaker.client.ui.mainmenu.MainMenu;
import com.cheatbreaker.client.ui.screens.ConnectionWarningScreen;
import com.cheatbreaker.main.CheatBreaker;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.network.NetHandlerLoginClient;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.network.login.client.CPacketLoginStart;
import net.minecraft.util.text.TextComponentTranslation;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicInteger;

@Mixin(GuiConnecting.class)
public class MixinGuiConnecting extends GuiScreen {
    @Shadow private boolean cancel;
    @Shadow private NetworkManager networkManager;
    @Shadow @Final private static AtomicInteger CONNECTION_ID;
    @Shadow @Final private GuiScreen previousGuiScreen;

    @Shadow @Final private static Logger LOGGER;
    private boolean unrecommended = false;
    private ConnectionWarningScreen screenInstance = null;

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

    @Inject(method = "drawScreen", at = @At("RETURN"))
    public void drawScreen(int mouseX, int mouseY, float partialTicks, CallbackInfo ci) {
        if (this.unrecommended && this.screenInstance != null) {
            Ref.getMinecraft().bridge$displayGuiScreen(this.screenInstance);
        }
    }

    private void connectThread(String ip, int port) {
        InetAddress address = null;

        try {
            if (this.cancel) {
                return;
            }

            address = InetAddress.getByName(ip);
            this.networkManager = NetworkManager.createNetworkManagerAndConnect(address, port, this.mc.gameSettings.isUsingNativeTransport());
            this.networkManager.setNetHandler(new NetHandlerLoginClient(this.networkManager, this.mc, this.previousGuiScreen));
            this.networkManager.sendPacket(new C00Handshake(47, ip, port, EnumConnectionState.LOGIN, true));
            this.networkManager.sendPacket(new CPacketLoginStart(this.mc.getSession().getProfile()));
        } catch (UnknownHostException e) {
            if (this.cancel) {
                return;
            }

            LOGGER.error("Couldn't connect to server", e);
            this.mc.displayGuiScreen(new GuiDisconnected(this.previousGuiScreen, "connect.failed", new TextComponentTranslation("disconnect.genericReason", "Unknown host")));
        } catch (Exception e) {
            if (this.cancel) {
                return;
            }

            LOGGER.error("Couldn't connect to server", e);
            String exceptionStr = e.toString();
            if (address != null) {
                String fullAddress = address + ":" + port;
                exceptionStr = exceptionStr.replaceAll(fullAddress, "");
            }

            this.mc.displayGuiScreen(new GuiDisconnected(this.previousGuiScreen, "connect.failed", new TextComponentTranslation("disconnect.genericReason", exceptionStr)));
        }
    }

    private void connectCopy(final String ip, final int port) {
        LOGGER.info("Connecting to " + ip + ", " + port);
        (new Thread("Server Connector #" + CONNECTION_ID.incrementAndGet()) {
            public void run() {
                MixinGuiConnecting.this.connectThread(ip, port);
            }
        }).start();
    }
}

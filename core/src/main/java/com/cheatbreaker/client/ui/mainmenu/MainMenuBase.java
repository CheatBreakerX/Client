package com.cheatbreaker.client.ui.mainmenu;

import com.cheatbreaker.bridge.client.MinecraftBridge;
import com.cheatbreaker.bridge.client.renderer.texture.DynamicTextureBridge;
import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.bridge.util.ResourceLocationBridge;
import com.cheatbreaker.bridge.util.SessionBridge;
import com.cheatbreaker.client.remote.GitCommitProperties;
import com.cheatbreaker.client.ui.AbstractGui;
import com.cheatbreaker.client.ui.fading.ColorFade;
import com.cheatbreaker.client.ui.mainmenu.cosmetics.GuiCosmetics;
import com.cheatbreaker.client.ui.mainmenu.element.IconButtonElement;
import com.cheatbreaker.client.ui.mainmenu.element.TextButtonElement;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.ui.util.font.CBFontRenderer;
import com.cheatbreaker.client.ui.util.font.FontRegistry;
import com.cheatbreaker.main.CheatBreaker;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.Agent;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.Proxy;
import java.util.List;
import java.util.*;

public class MainMenuBase extends AbstractGui {
    private static int panoramaTimer = 4100;
    private final ResourceLocationBridge logo = Ref.getInstanceCreator().createResourceLocation("client/logo_42.png");
    private final IconButtonElement exitButton;
    private final IconButtonElement languageButton;
    private final AccountList accountList;
    private final TextButtonElement optionsButton;
    private final TextButtonElement changelogButton;
    private final TextButtonElement cosmeticsButton;
    private final ColorFade cbTextShadowFade;
    private final ResourceLocationBridge[] panoramaImages = new ResourceLocationBridge[] {
            Ref.getInstanceCreator().createResourceLocation("client/panorama/0.png"),
            Ref.getInstanceCreator().createResourceLocation("client/panorama/1.png"),
            Ref.getInstanceCreator().createResourceLocation("client/panorama/2.png"),
            Ref.getInstanceCreator().createResourceLocation("client/panorama/3.png"),
            Ref.getInstanceCreator().createResourceLocation("client/panorama/4.png"),
            Ref.getInstanceCreator().createResourceLocation("client/panorama/5.png")
    };
    private ResourceLocationBridge panoramaBackgroundLocation;
    private final File launcherAccounts;
    private final List<Account> accountsList;
    private float accountButtonWidth;

    public MainMenuBase() {
        this.launcherAccounts = new File(Ref.getMinecraft().bridge$getMcDataDir() + File.separator + "launcher_accounts.json");
        this.accountsList = new ArrayList<>();
        this.optionsButton = new TextButtonElement("OPTIONS");
        this.cosmeticsButton = new TextButtonElement("COSMETICS");
        this.changelogButton = new TextButtonElement("CHANGELOG");
        this.cbTextShadowFade = new ColorFade(0xF000000, -16777216);
        this.exitButton = new IconButtonElement(Ref.getInstanceCreator().createResourceLocation("client/icons/delete-64.png"));
        this.languageButton = new IconButtonElement(6, Ref.getInstanceCreator().createResourceLocation("client/icons/globe-24.png"));
        this.accountButtonWidth = FontRegistry.getRobotoRegular13px().getStringWidth(Ref.getMinecraft().bridge$getSession().bridge$getUsername());
        this.accountList = new AccountList(this, Ref.getMinecraft().bridge$getSession().bridge$getUsername(), CheatBreaker.getInstance().getHeadLocation(Ref.getMinecraft().bridge$getSession().bridge$getUsername()));
        //this.loadAccounts();
    }

    /*
     * Could not resolve type clashes
     */
    private void loadAccounts() {
        // TODO: rewrite this
        MinecraftBridge minecraft = Ref.getMinecraft();
        if (launcherAccounts.exists()) {
            try (final BufferedReader reader = new BufferedReader(new FileReader(new File(Ref.getMinecraft().bridge$getMcDataDir(), "launcher_accounts.json")))) {
                final JsonObject object = new JsonParser().parse(reader).getAsJsonObject();
                final Set<Map.Entry<String, JsonElement>> accounts = object.getAsJsonObject("accounts").entrySet();
                for (Map.Entry<String, JsonElement> accountElement : accounts) {
                    JsonObject account = accountElement.getValue().getAsJsonObject();
                    String accessToken = account.get("accessToken").getAsString();
                    JsonObject minecraftProfileObject = account.getAsJsonObject("minecraftProfile");
                    String uuid = minecraftProfileObject.get("id").getAsString();
                    String displayName = minecraftProfileObject.get("name").getAsString();
                    String clientToken = object.get("mojangClientToken").getAsString();
                    String userName = account.get("username").toString();
                    if (userName != null){
                        Account finalAccount = new Account(userName, clientToken,accessToken, displayName, uuid);
                        accountsList.add(finalAccount);
                        System.out.println("[CB] added account " + finalAccount.getUsername() + ".");
                        float f = FontRegistry.getRobotoRegular13px().getStringWidth(finalAccount.getDisplayName());
                        if (f > this.accountButtonWidth) {
                            this.accountButtonWidth = f;
                        }
                        if (minecraft.bridge$getSession() == null || !finalAccount.getUsername().equalsIgnoreCase(minecraft.bridge$getSession().bridge$getUsername()))
                            continue;
                        this.accountList.setDisplayName(finalAccount.getDisplayName());
                        this.accountList.setHeadLocation(CheatBreaker.getInstance().getHeadLocation(finalAccount.getDisplayName()));
                        this.updateAccountButtonSize();
                    } else {
                        System.err.println("[CB] userName is null.");
                    }
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    @Override
    public void handleMouseInput() {
        super.handleMouseInput();
        if (this.accountList != null) {
            this.accountList.handleElementMouse();
        }
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        ++panoramaTimer;
    }

    @Override
    public void initGui() {
        super.initGui();
        DynamicTextureBridge texture = Ref.getInstanceCreator().createDynamicTexture(256, 256);
        this.panoramaBackgroundLocation = this.mc.bridge$getTextureManager().bridge$getDynamicTextureLocation("background", texture);
        this.optionsButton.setElementSize((float) 124, (float) 6, (float) 42, 20);
        this.cosmeticsButton.setElementSize((float) 167, (float) 6, (float) 48, 20);
        this.exitButton.setElementSize(this.getScaledWidth() - (float) 30, (float) 7, (float) 23, 17);
        this.languageButton.setElementSize(this.getScaledWidth() / 2.0f - (float) 13, this.getScaledHeight() - (float) 17, (float) 26, 18);
        this.updateAccountButtonSize();
    }

    public void updateAccountButtonSize() {
        this.accountList.setElementSize(this.getScaledWidth() - 35f - this.accountList.getMaxWidthFor(this.accountButtonWidth), 7f, this.accountList.getMaxWidthFor(this.accountButtonWidth), 17);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float delta) {
        Ref.getGlBridge().bridge$disableAlphaTest();
        Ref.getDrawingUtils().renderSkybox(this.panoramaImages, delta, this.width, this.height, panoramaTimer, this.panoramaBackgroundLocation);
        Ref.getGlBridge().bridge$enableAlphaTest();
        super.drawScreen(mouseX, mouseY, delta);
    }

    @Override
    public void drawMenu(float mouseX, float mouseY) {
        CheatBreaker cb = CheatBreaker.getInstance();
        CBFontRenderer font = FontRegistry.getRobotoRegular24px();

        if ((Boolean)cb.globalSettings.mainMenuLightGradient.getValue())
            Ref.modified$drawGradientRect(0f, 0f, this.getScaledWidth(), this.getScaledHeight(), 0x5FFFFFFF, 0x2FFFFFFF);
        if ((Boolean)cb.globalSettings.mainMenuTopGradient.getValue())
            Ref.modified$drawGradientRect(0f, 0f, this.getScaledWidth(), 160, -553648128, 0);

        // CheatBreaker text in top corner
        boolean isOverCheatBreakerText =
                mouseX > 36 && mouseX < this.optionsButton.getX() &&
                mouseY > 8 && mouseY < 30;

        Color cheatBreakerTextColor = this.cbTextShadowFade.get(isOverCheatBreakerText);

        font.drawString("CheatBreaker", 37, 9, cheatBreakerTextColor.getRGB());
        font.drawString("CheatBreaker", 36, 8, -1);

        // CheatBreaker icon in top corner
        Ref.getGlBridge().bridge$color(1.0f, 1.0f, 1.0f, 1.0f);
        RenderUtil.drawIcon(this.logo, 10, 8, 6);

        // Render text at bottom
        font = FontRegistry.getPlayRegular18px();

        int textColor = new Color(255, 255, 255, 143).getRGB();

        String version = "CheatBreakerX (" + GitCommitProperties.getGitCommit() + "/" + GitCommitProperties.getGitBranch() + ")";
        String copyright = "Copyright Mojang AB. Do not distribute!";

        font.drawStringWithShadow(version, 5f, this.getScaledHeight() - 14f, textColor);
        font.drawRightAlignedStringWithShadow(copyright, this.getScaledWidth() - 5f, this.getScaledHeight() - 14f, textColor);

        // Render buttons
        this.exitButton.drawElement(mouseX, mouseY, true);
        if (!(mc.bridge$getCurrentScreen() instanceof GuiCosmetics)) this.languageButton.drawElement(mouseX, mouseY, true);
        this.accountList.drawElement(mouseX, mouseY, true);
        this.optionsButton.drawElement(mouseX, mouseY, true);
        this.cosmeticsButton.drawElement(mouseX, mouseY, true);
    }

    @Override
    public void onMouseMovedOrUp(float mouseX, float mouseY, int mouseButton) {

    }

    @Override
    public void onMouseClicked(float mouseX, float mouseY, int button) {
        this.exitButton.handleElementMouseClicked(mouseX, mouseY, button, true);
        this.accountList.handleElementMouseClicked(mouseX, mouseY, button, true);
        if (this.exitButton.isMouseInside(mouseX, mouseY)) {
            this.mc.bridge$getSoundHandler().bridge$playSound(Ref.getInstanceCreator().createSoundFromPSR(Ref.getInstanceCreator().createResourceLocation("gui.button.press"), 1.0f));
            this.mc.bridge$shutdown();
        } else if (this.optionsButton.isMouseInside(mouseX, mouseY)) {
            this.mc.bridge$getSoundHandler().bridge$playSound(Ref.getInstanceCreator().createSoundFromPSR(Ref.getInstanceCreator().createResourceLocation("gui.button.press"), 1.0f));
            this.mc.bridge$displayInternalGuiScreen(MinecraftBridge.InternalScreen.OPTIONS, new MainMenu());
        } else if (this.languageButton.isMouseInside(mouseX, mouseY)) {
            this.mc.bridge$getSoundHandler().bridge$playSound(Ref.getInstanceCreator().createSoundFromPSR(Ref.getInstanceCreator().createResourceLocation("gui.button.press"), 1.0f));
            this.mc.bridge$displayInternalGuiScreen(MinecraftBridge.InternalScreen.LANGUAGE, new MainMenu());
        } else if (this.cosmeticsButton.isMouseInside(mouseX, mouseY)) {
            this.mc.bridge$getSoundHandler().bridge$playSound(Ref.getInstanceCreator().createSoundFromPSR(Ref.getInstanceCreator().createResourceLocation("gui.button.press"), 1.0f));
            this.mc.bridge$displayGuiScreen(new GuiCosmetics());
        } else {
            boolean bl = mouseX < this.optionsButton.getX() && mouseY < (float) 30;
            if (bl && !(this.mc.bridge$getCurrentScreen() instanceof MainMenu)) {
                this.mc.bridge$getSoundHandler().bridge$playSound(Ref.getInstanceCreator().createSoundFromPSR(Ref.getInstanceCreator().createResourceLocation("gui.button.press"), 1.0f));
                this.mc.bridge$displayGuiScreen(new MainMenu());
            }
        }
    }

    public void login(String string) {
        block21:
        {
            try {
                SessionBridge session;
                Account selectedAccount = null;
                for (Account account : this.accountsList) {
                    if (!account.getDisplayName().equals(string)) continue;
                    selectedAccount = account;
                }
                if (selectedAccount == null) break block21;
                if (selectedAccount.getUUID().equalsIgnoreCase(Ref.getMinecraft().bridge$getSession().bridge$getPlayerID())) {
                    return;
                }
                Ref.getMinecraft().bridge$getSoundHandler().bridge$playSound(Ref.getInstanceCreator().createSoundFromPSR(Ref.getInstanceCreator().createResourceLocation("gui.button.press"), 1.0f));
                for (SessionBridge object2 : CheatBreaker.getInstance().sessions) {
                    if (!object2.bridge$func_148256_e().getId().toString().replaceAll("-", "").equalsIgnoreCase(selectedAccount.getUUID().replaceAll("-", "")))
                        continue;
//                    Ref.getMinecraft().setSession(object2);
                    this.accountList.setDisplayName(selectedAccount.getDisplayName());
                    this.accountList.setHeadLocation(selectedAccount.getHeadLocation());
                    this.updateAccountButtonSize();
                    return;
                }
                YggdrasilAuthenticationService yggdrasilAuthenticationService = new YggdrasilAuthenticationService(Proxy.NO_PROXY, selectedAccount.getClientToken());
                YggdrasilUserAuthentication object2 = (YggdrasilUserAuthentication) yggdrasilAuthenticationService.createUserAuthentication(Agent.MINECRAFT);
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("uuid", selectedAccount.getUUID());
                hashMap.put("displayName", selectedAccount.getDisplayName());
                hashMap.put("username", selectedAccount.getUsername());
                hashMap.put("accessToken", selectedAccount.getAccessToken());
                object2.loadFromStorage(hashMap);
                try {
                    object2.logIn();
                    session = Ref.getInstanceCreator().createSession(object2.getSelectedProfile().getName(), object2.getSelectedProfile().getId().toString(), object2.getAuthenticatedToken(), "mojang");
                } catch (AuthenticationException authenticationException) {
                    authenticationException.printStackTrace();
                    return;
                }
                System.out.println("Updated accessToken and logged user in.");
                this.accountList.setDisplayName(selectedAccount.getDisplayName());
                this.accountList.setHeadLocation(selectedAccount.getHeadLocation());
                this.updateAccountButtonSize();
                CheatBreaker.getInstance().sessions.add(session);
//                Ref.getMinecraft().setSession(session);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public List<Account> getAccounts() {
        return this.accountsList;
    }
}


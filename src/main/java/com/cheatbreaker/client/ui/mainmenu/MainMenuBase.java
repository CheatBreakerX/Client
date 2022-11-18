package com.cheatbreaker.client.ui.mainmenu;

import com.cheatbreaker.bridge.client.renderer.TessellatorBridge;
import com.cheatbreaker.bridge.client.renderer.texture.DynamicTextureBridge;
import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.bridge.util.ResourceLocationBridge;
import com.cheatbreaker.bridge.util.SessionBridge;
import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.remote.GitCommitProperties;
import com.cheatbreaker.client.ui.AbstractGui;
import com.cheatbreaker.client.ui.fading.ColorFade;
import com.cheatbreaker.client.ui.mainmenu.cosmetics.GuiCosmetics;
import com.cheatbreaker.client.ui.mainmenu.element.IconButtonElement;
import com.cheatbreaker.client.ui.mainmenu.element.TextButtonElement;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.ui.util.font.CBFontRenderer;
import com.cheatbreaker.client.ui.util.font.FontRegistry;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.Agent;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Session;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Project;

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
    private final ResourceLocationBridge logo = Ref.getInstanceCreator().createResourceLocationBridge("client/logo_42.png");
    private final IconButtonElement exitButton;
    private final IconButtonElement languageButton;
    private final AccountList accountList;
    private final TextButtonElement optionsButton;
    private final TextButtonElement changelogButton;
    private final TextButtonElement cosmeticsButton;
    private final ColorFade cbTextShadowFade;
    private final ResourceLocationBridge[] panoramaImages = new ResourceLocationBridge[] {
            Ref.getInstanceCreator().createResourceLocationBridge("client/panorama/0.png"),
            Ref.getInstanceCreator().createResourceLocationBridge("client/panorama/1.png"),
            Ref.getInstanceCreator().createResourceLocationBridge("client/panorama/2.png"),
            Ref.getInstanceCreator().createResourceLocationBridge("client/panorama/3.png"),
            Ref.getInstanceCreator().createResourceLocationBridge("client/panorama/4.png"),
            Ref.getInstanceCreator().createResourceLocationBridge("client/panorama/5.png")
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
        this.exitButton = new IconButtonElement(Ref.getInstanceCreator().createResourceLocationBridge("client/icons/delete-64.png"));
        this.languageButton = new IconButtonElement(6, Ref.getInstanceCreator().createResourceLocationBridge("client/icons/globe-24.png"));
        this.accountButtonWidth = FontRegistry.getRobotoRegular13px().getStringWidth(Ref.getMinecraft().bridge$getSession().bridge$getUsername());
        this.accountList = new AccountList(this, Ref.getMinecraft().bridge$getSession().bridge$getUsername(), CheatBreaker.getInstance().getHeadLocation(Ref.getMinecraft().bridge$getSession().bridge$getUsername()));
        //this.loadAccounts();
    }

    /*
     * Could not resolve type clashes
     */
    private void loadAccounts() {
        // TODO: rewrite this
        Minecraft minecraft = Ref.getMinecraft();
        if (launcherAccounts.exists()) {
            try (final BufferedReader reader = new BufferedReader(new FileReader(new File(Ref.getMinecraft().mcDataDir, "launcher_accounts.json")))) {
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
                        if (minecraft.getSession() == null || !finalAccount.getUsername().equalsIgnoreCase(minecraft.getSession().bridge$getUsername()))
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
        this.panoramaBackgroundLocation = this.mc.getTextureManager().getDynamicTextureLocation("background", texture);
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
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GL11.glDisable(3008);
        this.renderSkybox();
        GL11.glEnable(3008);
        super.drawScreen(mouseX, mouseY, partialTicks);
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
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
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
        if (!(mc.currentScreen instanceof GuiCosmetics)) this.languageButton.drawElement(mouseX, mouseY, true);
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
            this.mc.bridge$getSoundHandler().bridge$playSound(Ref.getInstanceCreator().createSoundFromPSR(Ref.getInstanceCreator().createResourceLocationBridge("gui.button.press"), 1.0f));
            this.mc.bridge$shutdown();
        } else if (this.optionsButton.isMouseInside(mouseX, mouseY)) {
            this.mc.bridge$getSoundHandler().bridge$playSound(Ref.getInstanceCreator().createSoundFromPSR(Ref.getInstanceCreator().createResourceLocationBridge("gui.button.press"), 1.0f));
            this.mc.bridge$displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
        } else if (this.languageButton.isMouseInside(mouseX, mouseY)) {
            this.mc.bridge$getSoundHandler().bridge$playSound(Ref.getInstanceCreator().createSoundFromPSR(Ref.getInstanceCreator().createResourceLocationBridge("gui.button.press"), 1.0f));
            this.mc.bridge$displayGuiScreen(new GuiLanguage(this, this.mc.gameSettings, this.mc.bridge$getLanguageManager()));
        } else if (this.cosmeticsButton.isMouseInside(mouseX, mouseY)) {
            this.mc.bridge$getSoundHandler().bridge$playSound(Ref.getInstanceCreator().createSoundFromPSR(Ref.getInstanceCreator().createResourceLocationBridge("gui.button.press"), 1.0f));
            this.mc.bridge$displayGuiScreen(new GuiCosmetics());
        } else {
            boolean bl = mouseX < this.optionsButton.getX() && mouseY < (float) 30;
            if (bl && !(this.mc.bridge$getCurrentScreen() instanceof MainMenu)) {
                this.mc.bridge$getSoundHandler().bridge$playSound(Ref.getInstanceCreator().createSoundFromPSR(Ref.getInstanceCreator().createResourceLocationBridge("gui.button.press"), 1.0f));
                this.mc.bridge$displayGuiScreen(new MainMenu());
            }
        }
    }

    private void drawPanorama(float speed) {
        TessellatorBridge tessellator = Ref.getTessellator();
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
        Project.gluPerspective(120.0F, 1.0F, 0.05F, 10.0F);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glDepthMask(false);
        Ref.getGlBridge().bridge$glBlendFunc(770, 771, 1, 0);
        byte var5 = 8;

        for (int var6 = 0; var6 < var5 * var5; ++var6) {
            GL11.glPushMatrix();
            float var7 = ((float) (var6 % var5) / (float) var5 - 0.5F) / 64.0F;
            float var8 = ((float) (var6 / var5) / (float) var5 - 0.5F) / 64.0F;
            float var9 = 0.0F;
            GL11.glTranslatef(var7, var8, var9);
            GL11.glRotatef(MathHelper.sin(((float) panoramaTimer + speed) / 400.0F) * 25.0F + 20.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-((float) panoramaTimer + speed) * 0.1F, 0.0F, 1.0F, 0.0F);

            for (int var10 = 0; var10 < 6; ++var10) {
                GL11.glPushMatrix();

                if (var10 == 1) {
                    GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
                }

                if (var10 == 2) {
                    GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                }

                if (var10 == 3) {
                    GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
                }

                if (var10 == 4) {
                    GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
                }

                if (var10 == 5) {
                    GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                }

                this.mc.bridge$getTextureManager().bridge$bindTexture(panoramaImages[var10]);
                tessellator.bridge$startDrawingQuads();
                tessellator.bridge$setColorRGBA_I(16777215, 255 / (var6 + 1));
                float var11 = 0.0F;
                tessellator.bridge$addVertexWithUV(-1.0D, -1.0D, 1.0D, 0.0F + var11, 0.0F + var11);
                tessellator.bridge$addVertexWithUV(1.0D, -1.0D, 1.0D, 1.0F - var11, 0.0F + var11);
                tessellator.bridge$addVertexWithUV(1.0D, 1.0D, 1.0D, 1.0F - var11, 1.0F - var11);
                tessellator.bridge$addVertexWithUV(-1.0D, 1.0D, 1.0D, 0.0F + var11, 1.0F - var11);
                tessellator.bridge$finish();
                GL11.glPopMatrix();
            }

            GL11.glPopMatrix();
            GL11.glColorMask(true, true, true, false);
        }

        tessellator.bridge$setTranslation(0.0D, 0.0D, 0.0D);
        GL11.glColorMask(true, true, true, true);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glPopMatrix();
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glPopMatrix();
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }

    /**
     * Rotate and blurs the skybox view in the main menu
     */
    private void rotateAndBlurSkybox() {
        this.mc.bridge$getTextureManager().bridge$bindTexture(this.panoramaBackgroundLocation);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
        GL11.glCopyTexSubImage2D(GL11.GL_TEXTURE_2D, 0, 0, 0, 0, 0, 256, 256);
        GL11.glEnable(GL11.GL_BLEND);
        Ref.getGlBridge().bridge$glBlendFunc(770, 771, 1, 0);
        GL11.glColorMask(true, true, true, false);
        TessellatorBridge tessellator = Ref.getTessellator();
        tessellator.bridge$startDrawingQuads();
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        byte var3 = 3;

        for (int var4 = 0; var4 < var3; ++var4) {
            tessellator.bridge$setColorRGBA_F(1.0F, 1.0F, 1.0F, 1.0F / (float) (var4 + 1));
            int var5 = this.width;
            int var6 = this.height;
            float var7 = (float) (var4 - var3 / 2) / 256.0F;
            tessellator.bridge$addVertexWithUV(var5, var6, zLevel, 0.0F + var7, 1.0D);
            tessellator.bridge$addVertexWithUV(var5, 0.0D, zLevel, 1.0F + var7, 1.0D);
            tessellator.bridge$addVertexWithUV(0.0D, 0.0D, zLevel, 1.0F + var7, 0.0D);
            tessellator.bridge$addVertexWithUV(0.0D, var6, zLevel, 0.0F + var7, 0.0D);
        }

        tessellator.bridge$finish();
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glColorMask(true, true, true, true);
    }

    /**
     * Renders the skybox in the main menu
     */
    private void renderSkybox() {
        float p_73971_3_ = 1f;
        this.mc.bridge$getFramebuffer().bridge$unbindFramebuffer();
        GL11.glViewport(0, 0, 256, 256);
        this.drawPanorama(p_73971_3_);
        this.rotateAndBlurSkybox();
        this.rotateAndBlurSkybox();
        this.rotateAndBlurSkybox();
        this.rotateAndBlurSkybox();
        this.rotateAndBlurSkybox();
        this.rotateAndBlurSkybox();
        this.rotateAndBlurSkybox();
        this.mc.bridge$getFramebuffer().bridge$bindFramebuffer(true);
        GL11.glViewport(0, 0, this.mc.bridge$getDisplayWidth(), this.mc.bridge$getDisplayHeight());
        TessellatorBridge tessellator = Ref.getTessellator();
        tessellator.bridge$startDrawingQuads();
        float var5 = this.width > this.height ? 120.0F / (float) this.width : 120.0F / (float) this.height;
        float var6 = (float) this.height * var5 / 256.0F;
        float var7 = (float) this.width * var5 / 256.0F;
        tessellator.bridge$setColorRGBA_F(1.0F, 1.0F, 1.0F, 1.0F);
        int var8 = this.width;
        int var9 = this.height;
        tessellator.bridge$addVertexWithUV(0.0D, var9, zLevel, 0.5F - var6, 0.5F + var7);
        tessellator.bridge$addVertexWithUV(var8, var9, zLevel, 0.5F - var6, 0.5F - var7);
        tessellator.bridge$addVertexWithUV(var8, 0.0D, zLevel, 0.5F + var6, 0.5F - var7);
        tessellator.bridge$addVertexWithUV(0.0D, 0.0D, zLevel, 0.5F + var6, 0.5F + var7);
        tessellator.bridge$finish();
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
                Ref.getMinecraft().bridge$getSoundHandler().bridge$playSound(Ref.getInstanceCreator().createSoundFromPSR(Ref.getInstanceCreator().createResourceLocationBridge("gui.button.press"), 1.0f));
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
                    session = new Session(object2.getSelectedProfile().getName(), object2.getSelectedProfile().getId().toString(), object2.getAuthenticatedToken(), "mojang");
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


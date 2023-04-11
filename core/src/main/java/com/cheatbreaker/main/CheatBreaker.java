package com.cheatbreaker.main;

import com.cheatbreaker.bridge.client.MinecraftBridge;
import com.cheatbreaker.bridge.client.audio.SoundManagerBridge;
import com.cheatbreaker.bridge.client.gui.ScaledResolutionBridge;
import com.cheatbreaker.bridge.client.renderer.ThreadDownloadImageDataBridge;
import com.cheatbreaker.bridge.forge.BridgedMod;
import com.cheatbreaker.bridge.forge.BridgedSubscribeEvent;
import com.cheatbreaker.bridge.forge.RenderGameOverlayEventBridge;
import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.bridge.util.ResourceLocationBridge;
import com.cheatbreaker.bridge.util.SessionBridge;
import com.cheatbreaker.client.audio.AudioDevice;
import com.cheatbreaker.client.config.ConfigManager;
import com.cheatbreaker.client.config.GlobalSettings;
import com.cheatbreaker.client.config.Profile;
import com.cheatbreaker.client.event.EventBus;
import com.cheatbreaker.client.event.type.GuiDrawEvent;
import com.cheatbreaker.client.event.type.KeyboardEvent;
import com.cheatbreaker.client.event.type.PluginMessageEvent;
import com.cheatbreaker.client.event.type.RenderPreviewEvent;
import com.cheatbreaker.client.module.AbstractModule;
import com.cheatbreaker.client.module.ModuleManager;
import com.cheatbreaker.client.nethandler.NetHandler;
import com.cheatbreaker.client.remote.GitCommitProperties;
import com.cheatbreaker.client.ui.mainmenu.LoadingScreen;
import com.cheatbreaker.client.ui.module.CBModulePlaceGui;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import com.cheatbreaker.client.ui.overlay.Alert;
import com.cheatbreaker.client.ui.overlay.OverlayGui;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.util.SessionServer;
import com.cheatbreaker.client.util.cosmetic.Cosmetic;
import com.cheatbreaker.client.util.dash.CBDashManager;
import com.cheatbreaker.client.util.friend.FriendsManager;
import com.cheatbreaker.client.util.friend.Status;
import com.cheatbreaker.client.util.thread.ServerStatusThread;
import com.cheatbreaker.client.util.title.TitleManager;
import com.cheatbreaker.client.util.voicechat.VoiceChatManager;
import com.cheatbreaker.client.util.worldborder.WorldBorderManager;
import com.cheatbreaker.client.websocket.AssetsWebSocket;
import lombok.Getter;
import lombok.Setter;
import org.lwjgl.input.Keyboard;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.*;

@Getter
@BridgedMod(modid = "cheatbreaker", name = "CheatBreaker", version = "3ac10ce")
public class CheatBreaker {
    private static CheatBreaker instance;
    public static CheatBreaker getInstance() {
        return instance == null ? new CheatBreaker() : instance;
    }

    public static byte[] processBytesAuth = "Decencies".getBytes(); // originally "Vote Trump 2020!" (jhalt's doing LMAO???)
    public static LoadingScreen cbLoadingScreen;
    public List<Profile> profiles;
    public Profile activeProfile;
    public GlobalSettings globalSettings;
    public ModuleManager moduleManager;
    public ConfigManager configManager;
    public EventBus eventBus;

    public List<SessionServer> statusServers;
    public List<ResourceLocationBridge> presetLocations;

    public NetHandler netHandler;
    public TitleManager titleManager;
    public WorldBorderManager borderManager;

    public long startTime;

    private List<AudioDevice> audioDevices = new ArrayList<>();
    private VoiceChatManager voiceChatManager;

    public static final AudioFormat universalAudioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 16000.0f, 16, 1, 2, 16000.0f, false);

    public List<SessionBridge> sessions;

    private List<Cosmetic> cosmetics = new ArrayList<>();
    private AssetsWebSocket websocket;

    private FriendsManager friendsManager;
    @Getter
    @Setter
    private Status status;

    @Setter
    private boolean consoleAllowed;

    private List<String> consoleLines;
    private CBDashManager radioManager;

    @Setter
    private boolean acceptingFriendRequests;

    private final Map<String, ResourceLocationBridge> playerSkins = new HashMap<>();

    public boolean isConsoleAllowed() {
        return true;
    }

    public CheatBreaker() {
        CheatBreaker.instance = this;

        Ref.getForgeEventBus().bridge$register(this);
    }

    public <T> void reverse(Queue<T> queue) {
        Stack<T> stack = new Stack<>();

        while (!queue.isEmpty())
            stack.push(queue.poll());

        while (!stack.isEmpty())
            queue.add(stack.pop());
    }

    public void cbInfo(String msg) {
        System.out.println("[CB] " + msg);
    }

    public void cbInfo(String msg, Class<?> loadedClass) {
        this.cbInfo(msg + " (" + loadedClass.getCanonicalName() + ")");
    }

    public void initialize() {
        this.audioDevices = new ArrayList<>();
        this.presetLocations = new ArrayList<>();
        this.cosmetics = new ArrayList<>();
        this.sessions = new ArrayList<>();
        this.statusServers = new ArrayList<>();
        this.profiles = new ArrayList<>();
        this.consoleLines = new ArrayList<>();
        this.startTime = System.currentTimeMillis();
        this.cbInfo("Starting CheatBreaker setup...");
        this.createDefaultConfigPresets();
        this.cbInfo("Created default configuration presets.");
        this.initAudioDevices();
        if (audioDevices.size() != 0) {
            this.cbInfo("Initialized all audio devices.");
            this.voiceChatManager = new VoiceChatManager(audioDevices.get(0));
            this.cbInfo("Created Voice Chat Manager", VoiceChatManager.class);
        } else {
            this.voiceChatManager = new VoiceChatManager(); // No microphone / device passed through, it is hypothetically non-existent.
            this.cbInfo("Couldn't load audio devices. This could be because there is no available microphone, or you are using PojavLauncher.");
            this.cbInfo("Solution: Connect an audio device");
            this.cbInfo("(For Pojav users I don't know your solution, sorry!)");
        }
        Ref.getMinecraft().bridge$getSoundHandler().bridge$getSoundManager().bridge$loadSoundSystem();
        this.cbInfo("Loaded SoundSystem manually (Using Bridge method " + SoundManagerBridge.class.getCanonicalName() + ".bridge$loadSoundSystem())");
        this.globalSettings = new GlobalSettings();
        this.cbInfo("Created Settings Manager", GlobalSettings.class);
        this.eventBus = new EventBus();
        this.cbInfo("Created Event Bus", EventBus.class);
        this.moduleManager = new ModuleManager();
        this.cbInfo("Created Module Manager", ModuleManager.class);
        this.netHandler = new NetHandler();
        this.cbInfo("Created Network Handler", NetHandler.class);
        this.titleManager = new TitleManager();
        this.cbInfo("Created Title Manager", TitleManager.class);
        this.cosmetics.add(new Cosmetic("Steve", "CheatBreaker Cape", 1.0f, true, "client/defaults/cb.png"));
        this.cosmetics.add(new Cosmetic("Steve", "CheatBreaker Black Cape", 1.0f, false, "client/defaults/cb_black.png"));
        this.status = Status.AWAY;
        this.radioManager = new CBDashManager();
        this.cbInfo("Created Radio Manager", CBDashManager.class);
        this.loadProfiles();
        this.cbInfo("Loaded " + this.profiles.size() + " custom profiles.");
        (this.configManager = new ConfigManager()).read();
        this.cbInfo("Created Configuration Manager", ConfigManager.class);

        this.cbInfo("Connecting to websocket server...");
        this.connectToAssetsServer();
        this.friendsManager = new FriendsManager();
        this.cbInfo("Created Friends Manager", FriendsManager.class);
        OverlayGui.setInstance(new OverlayGui());
        this.cbInfo("Created Overlay UI", OverlayGui.class);

        this.eventBus.addEvent(PluginMessageEvent.class, this.netHandler::onPluginMessage);
        this.eventBus.addEvent(KeyboardEvent.class, (e) -> {
            if (e.getKeyboardKey() == Keyboard.KEY_H) {
                Alert.displayMessage("Hello", "Hello, World\nNew Line");
            }
            if (e.getKeyboardKey() == Keyboard.KEY_RSHIFT) {
                if (Ref.getMinecraft().bridge$isIngame()) {
                    Ref.getMinecraft().bridge$displayGuiScreen(new CBModulesGui());
                }
            }
            if (e.getKeyboardKey() == Keyboard.KEY_F9) {
                RenderUtil.minFps = 2147483647;
                RenderUtil.maxFps = 0;
            }
        });
        this.cbInfo("Registered main events.");

        new ServerStatusThread().start();
        this.cbInfo("Loaded version data.");
        this.borderManager = new WorldBorderManager();
        this.cbInfo("Created World Border Manager", WorldBorderManager.class);
    }

    private void createDefaultConfigPresets() {
        File file = ConfigManager.profilesDir;
        if (file.exists() || file.mkdirs()) {
            for (ResourceLocationBridge location : presetLocations) {
                File file2 = new File(file, location.bridge$getResourcePath().replaceAll("([a-zA-Z0-9/]+)/", ""));
                if (!file2.exists()) {
                    try {
                        InputStream stream = Ref.getMinecraft().bridge$getResourceManager().bridge$getResource(location).bridge$getInputStream();
                        Files.copy(stream, file2.toPath());
                        stream.close();
                    }
                    catch (final IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    private void loadProfiles() {
        this.profiles.add(new Profile("default", false));
        final File dir = ConfigManager.profilesDir;
        final File[] files;
        if (dir.exists() && dir.isDirectory() && (files = dir.listFiles()) != null) {
            for (final File file : files) {
                if (file.getName().endsWith(".cfg")) {
                    this.profiles.add(new Profile(file.getName().replace(".cfg", ""), true));
                }
            }
        }
    }

    private String getNewProfileName(final String base) {
        final File dir = ConfigManager.profilesDir;
        if (dir.exists() || dir.mkdirs()) {
            if (new File(dir + File.separator + base + ".cfg").exists()) {
                return this.getNewProfileName(base + "1");
            }
        }
        return base;
    }

    public void createNewProfile() {
        if (this.activeProfile == this.profiles.get(0)) {
            final Profile profile = new Profile(this.getNewProfileName("Profile 1"), true);
            this.activeProfile = profile;
            this.profiles.add(profile);
            this.configManager.write();
        }
    }

    private void initAudioDevices() {
        final Mixer.Info[] mixers = AudioSystem.getMixerInfo();
        for (final Mixer.Info info : mixers) {
            final Mixer mixer = AudioSystem.getMixer(info);
            try {
                TargetDataLine dataLine = (TargetDataLine) mixer.getLine(new DataLine.Info(TargetDataLine.class, CheatBreaker.universalAudioFormat));
                if (info != null) {
                    this.cbInfo("Added microphone to input devices: " + info.getName());
                    this.audioDevices.add(new AudioDevice(info.getDescription(), info.getName(), dataLine));
                }
            } catch (final IllegalArgumentException | LineUnavailableException ignored) {
                // the device was not a microphone.
            }
        }
    }

    public String[] getAudioDeviceList() {
        final String[] audioDevices = new String[this.audioDevices.size()];
        int var1 = 0;
        for (final Iterator<AudioDevice> var2 = this.audioDevices.iterator(); var2.hasNext(); ++var1) {
            final AudioDevice var3 = var2.next();
            audioDevices[var1] = var3.getDescriptor();
        }
        return audioDevices;
    }

    public boolean isUsingStaffModules() {
        for (final AbstractModule cbModule : this.moduleManager.staffModules) {
            if (cbModule.isStaffEnabledModule()) {
                return true;
            }
        }
        return false;
    }

    public float getScaleFactor() {
        switch (Ref.getMinecraft().bridge$getGameSettings().bridge$getGuiScale()) {
            case 0: {
                return 2.0f;
            }
            case 1: {
                return 0.5f;
            }
            case 3: {
                return 1.5f;
            }
            default: {
                return 1.0f;
            }
        }
    }

    public void sendSound(final String sound) {
        this.sendSound(sound, 1.0f);
    }

    public void sendSound(final String sound, final float volume) {
        if (!(boolean)this.globalSettings.muteCheatBreakerSounds.getValue())
            Ref.getMinecraft().bridge$getSoundHandler().bridge$getSoundManager().playSound(sound, volume);
    }

    public ResourceLocationBridge getHeadLocation(String displayName) {
        ResourceLocationBridge playerSkin = this.playerSkins.getOrDefault(displayName, Ref.getInstanceCreator().createResourceLocation("client/heads/" + displayName + ".png"));
        if (!this.playerSkins.containsKey(displayName)) {
            ThreadDownloadImageDataBridge skinData = Ref.getInstanceCreator().createThreadDownloadImageData(null, "https://minotar.net/helm/" + displayName + "/32.png", Ref.getInstanceCreator().createResourceLocation("client/defaults/steve.png"), null);
            Ref.getMinecraft().bridge$getTextureManager().bridge$loadTexture(playerSkin, skinData);
            this.playerSkins.put(displayName, playerSkin);
        }
        return playerSkin;
    }

    public String getPluginMessageChannel() {
        return "CB-Client";
    }

    public String getPluginBinaryChannel() {
        return "CB-Binary";
    }

    public void connectToAssetsServer() {
        final Map<String, String> hashMap = new HashMap<>();
        hashMap.put("username", Ref.getMinecraft().bridge$getSession().bridge$getUsername());
        hashMap.put("playerId", Ref.getMinecraft().bridge$getSession().bridge$getPlayerID());
        hashMap.put("version", GitCommitProperties.getGitCommit());
        try {
            // TODO: Host websocket
            (this.websocket = new AssetsWebSocket(new URI("ws://localhost:80"), hashMap)).connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public AssetsWebSocket getAssetsWebSocket() {
        return websocket;
    }

    public String getStatusString() {
        String s;
        switch (this.getStatus()) {
            case AWAY: {
                s = "Away";
                break;
            }
            case BUSY: {
                s = "Busy";
                break;
            }
            case HIDDEN: {
                s = "Hidden";
                break;
            }
            default: {
                s = "Online";
                break;
            }
        }
        return s;
    }

    public void removeCosmeticsFromPlayer(final String playerId) {
        this.cosmetics.removeIf(cosmetic -> cosmetic.getPlayerId().equals(playerId));
    }

    @BridgedSubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEventBridge event) {
        if (event.bridge$isPost() && event.bridge$typeIs("EXPERIENCE")) {
            MinecraftBridge mc = Ref.getMinecraft();
            ScaledResolutionBridge scaledResolution = Ref.getInstanceCreator().createScaledResolution();

            if (!mc.bridge$getGameSettings().bridge$getShowDebugInfo()) {
                CheatBreaker.getInstance().getEventBus().callEvent(new GuiDrawEvent(scaledResolution));
            }

            if (mc.bridge$getCurrentScreen() instanceof CBModulesGui || mc.bridge$getCurrentScreen() instanceof CBModulePlaceGui) {
                CheatBreaker.getInstance().getEventBus().callEvent(new RenderPreviewEvent(scaledResolution));
            }

            if (mc.bridge$isIngame()) {
                OverlayGui.getInstance().renderGameOverlay();
            }
        }
    }

    public boolean isInDebugMode() {
        return true;
    }
}

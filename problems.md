`com.cheatbreaker.bridge.*` - 100% compilable code - There is no errors and the code is fine.

> Client classes are all in different packages to more spread out

`com.cheatbreaker.client.audio.*` - 100%\
`com.cheatbreaker.client.config.*` - 100%\
`com.cheatbreaker.client.event.*` - 100%\
`com.cheatbreaker.client.module.*` - 30.8% (8 unproblematic files)\
Problematic files (18):
- [.staff.XRayModule](https://github.com/CheatBreakerX/ClientBase/blob/master/src/main/java/com/cheatbreaker/client/module/staff/XRayModule.java)
- [.type.armourstatus.ArmourStatusItem](https://github.com/CheatBreakerX/ClientBase/blob/master/src/main/java/com/cheatbreaker/client/module/type/armourstatus/ArmourStatusItem.java)
- [.type.armourstatus.ArmourStatusModule](https://github.com/CheatBreakerX/ClientBase/blob/master/src/main/java/com/cheatbreaker/client/module/type/armourstatus/ArmourStatusModule.java)
- [.type.bossbar.BossBarModule](https://github.com/CheatBreakerX/ClientBase/blob/master/src/main/java/com/cheatbreaker/client/module/type/bossbar/BossBarModule.java)
- [.type.cooldowns.CooldownRenderer](https://github.com/CheatBreakerX/ClientBase/blob/master/src/main/java/com/cheatbreaker/client/module/type/cooldowns/CooldownRenderer.java)
- [.type.cooldowns.CooldownsModule](https://github.com/CheatBreakerX/ClientBase/blob/master/src/main/java/com/cheatbreaker/client/module/type/cooldowns/CooldownsModule.java)
- [.type.keystrokes.Key](https://github.com/CheatBreakerX/ClientBase/blob/master/src/main/java/com/cheatbreaker/client/module/type/keystrokes/Key.java)
- [.type.keystrokes.KeystrokesModule](https://github.com/CheatBreakerX/ClientBase/blob/master/src/main/java/com/cheatbreaker/client/module/type/keystrokes/KeystrokesModule.java)
- [.type.notifications.CBNotificationsModule](https://github.com/CheatBreakerX/ClientBase/blob/master/src/main/java/com/cheatbreaker/client/module/type/notifications/CBNotificationsModule.java)
- [.type.togglesprint.ToggleSprintModule](https://github.com/CheatBreakerX/ClientBase/blob/master/src/main/java/com/cheatbreaker/client/module/type/togglesprint/ToggleSprintModule.java)
- [.type.CoordinatesModule](https://github.com/CheatBreakerX/ClientBase/blob/master/src/main/java/com/cheatbreaker/client/module/type/CoordinatesModule.java)
- [.type.CPSModule](https://github.com/CheatBreakerX/ClientBase/blob/master/src/main/java/com/cheatbreaker/client/module/type/CPSModule.java)
- [.type.DirectionHudModule](https://github.com/CheatBreakerX/ClientBase/blob/master/src/main/java/com/cheatbreaker/client/module/type/DirectionHudModule.java)
- [.type.FPSModule](https://github.com/CheatBreakerX/ClientBase/blob/master/src/main/java/com/cheatbreaker/client/module/type/FPSModule.java)
- [.type.MiniMapModule](https://github.com/CheatBreakerX/ClientBase/blob/master/src/main/java/com/cheatbreaker/client/module/type/MiniMapModule.java)
- [.type.PotionStatusModule](https://github.com/CheatBreakerX/ClientBase/blob/master/src/main/java/com/cheatbreaker/client/module/type/PotionStatusModule.java)
- [.type.ScoreboardModule](https://github.com/CheatBreakerX/ClientBase/blob/master/src/main/java/com/cheatbreaker/client/module/type/ScoreboardModule.java)
- [.type.TeammatesModule](https://github.com/CheatBreakerX/ClientBase/blob/master/src/main/java/com/cheatbreaker/client/module/type/TeammatesModule.java)

`com.cheatbreaker.client.nethandler.*` - 96.9% (32 unproblematic files)\
Problematic file (1):
- [NetHandler](https://github.com/CheatBreakerX/ClientBase/blob/master/src/main/java/com/cheatbreaker/client/nethandler/NetHandler.java)

`com.cheatbreaker.client.remove.*` - 100%\
`com.cheatbreaker.client.ui.*` - 55.2% (Too many files to manually add here, ratio of unproblematic to problematic is 42:34)\
`com.cheatbreaker.client.util.*` - 90.6% (29 unproblematic files)\
Problematic files (3):
- [.hologram.Hologram](https://github.com/CheatBreakerX/ClientBase/blob/master/src/main/java/com/cheatbreaker/client/util/hologram/Hologram.java)
- [.voicechat.VoiceChatManager](https://github.com/CheatBreakerX/ClientBase/blob/master/src/main/java/com/cheatbreaker/client/util/voicechat/VoiceChatManager.java)
- [.worldborder.WorldBorderManager](https://github.com/CheatBreakerX/ClientBase/blob/master/src/main/java/com/cheatbreaker/client/util/worldborder/WorldBorderManager.java)

`com.cheatbreaker.client.websocket.*` - 89.3% (25 unproblematic files)\
Problematic files (3):
- [.client.WSPacketClientJoinServerResponse](https://github.com/CheatBreakerX/ClientBase/blob/master/src/main/java/com/cheatbreaker/client/websocket/client/WSPacketClientJoinServerResponse.java)
- [.server.WSPacketJoinServer](https://github.com/CheatBreakerX/ClientBase/blob/master/src/main/java/com/cheatbreaker/client/websocket/server/WSPacketJoinServer.java)
- [AssetsWebSocket](https://github.com/CheatBreakerX/ClientBase/blob/master/src/main/java/com/cheatbreaker/client/websocket/AssetsWebSocket.java)


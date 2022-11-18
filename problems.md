# [CheatBreakerX/ClientBase](https://github.com/CheatBreakerX/ClientBase)/problems.md

`com.cheatbreaker.bridge.*` - 100% compilable code - There is no errors and the code is fine.

> Client classes are all in different packages to more spread out

`com.cheatbreaker.client.audio.*` - 100%\
`com.cheatbreaker.client.config.*` - 100%\
`com.cheatbreaker.client.event.*` - 100%\
`com.cheatbreaker.client.module.*` - 84.6% (22 unproblematic files)\
Problematic files (4):
- [.type.MiniMapModule](https://github.com/CheatBreakerX/ClientBase/blob/master/src/main/java/com/cheatbreaker/client/module/type/MiniMapModule.java)
- [.type.PotionStatusModule](https://github.com/CheatBreakerX/ClientBase/blob/master/src/main/java/com/cheatbreaker/client/module/type/PotionStatusModule.java)
- [.type.ScoreboardModule](https://github.com/CheatBreakerX/ClientBase/blob/master/src/main/java/com/cheatbreaker/client/module/type/ScoreboardModule.java)
- [.type.TeammatesModule](https://github.com/CheatBreakerX/ClientBase/blob/master/src/main/java/com/cheatbreaker/client/module/type/TeammatesModule.java)

`com.cheatbreaker.client.nethandler.*` - 96.9% (32 unproblematic files)\
Problematic file (1):
- [NetHandler](https://github.com/CheatBreakerX/ClientBase/blob/master/src/main/java/com/cheatbreaker/client/nethandler/NetHandler.java)

`com.cheatbreaker.client.remote.*` - 100%\
`com.cheatbreaker.client.ui.*` - 55.2% (Too many files to manually add here, ratio of unproblematic to problematic is 42:34)\
`com.cheatbreaker.client.util.*` - 90.6% (29 unproblematic files)\
Problematic files (3):
- [.hologram.Hologram](https://github.com/CheatBreakerX/ClientBase/blob/master/src/main/java/com/cheatbreaker/client/util/hologram/Hologram.java)
- [.voicechat.VoiceChatManager](https://github.com/CheatBreakerX/ClientBase/blob/master/src/main/java/com/cheatbreaker/client/util/voicechat/VoiceChatManager.java)
- [.worldborder.WorldBorderManager](https://github.com/CheatBreakerX/ClientBase/blob/master/src/main/java/com/cheatbreaker/client/util/worldborder/WorldBorderManager.java)

`com.cheatbreaker.client.websocket.*` - 100%

### Overall unproblematic file percentage - 82.7%

# Addressing "`com.cheatbreaker.client.remote`" package

To the five people who genuinely think that this package contains a RAT, please read the [source code of the file inside](https://github.com/CheatBreakerX/ClientBase/blob/master/src/main/java/com/cheatbreaker/client/remote/GitCommitProperties.java) and have some common sense.
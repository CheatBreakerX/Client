package com.cheatbreaker.client.util.thread;

import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.util.SessionServer;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

public class ServerStatusThread extends Thread {

    @Override
    public void run() {
        try {
            URL uRL = new URL(CheatBreaker.getInstance().getGlobalSettings().mojangStatusURL);
            URLConnection uRLConnection = uRL.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(uRLConnection.getInputStream()));
            JsonParser jsonParser = new JsonParser();
            JsonElement jsonElement = jsonParser.parse(bufferedReader);
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            for (int i = 0; i < jsonArray.size(); ++i) {
                JsonElement jsonElement2 = jsonArray.get(i);
                for (Map.Entry entry : jsonElement2.getAsJsonObject().entrySet()) {
                    for (SessionServer serverObject : CheatBreaker.getInstance().statusServers) {
                        SessionServer.Status serverStatus = null;
                        if (!serverStatus.getType().equalsIgnoreCase((String)entry.getKey()) || (serverStatus = SessionServer.Status.getStatusByName(((JsonElement)entry.getValue()).getAsString())) == null) continue;
                        if (serverStatus != serverObject.getStatus() && serverObject.getStatus() != SessionServer.Status.UNKNOWN && serverObject.getStatus() != SessionServer.Status.BUSY && (serverStatus == SessionServer.Status.DOWN || serverStatus == SessionServer.Status.UP)) {
                            String string;
                            String colorOfText = serverStatus == SessionServer.Status.UP ? "\u00a7a" : "\u00a7c";
                            String string2 = string = serverStatus == SessionServer.Status.UP ? "online" : "offline";
                            if (Ref.getMinecraft().bridge$getTheWorld() != null) {
                                CheatBreaker.getInstance().getModuleManager().notifications.queueNotification("info", "Minecraft " + serverObject.getType().toLowerCase() + " server is now " + string + ".", 7500L);
                                System.out.println("Minecraft servers are now " + colorOfText + string + ".");
                            }
                        }
                        serverObject.setStatus(serverStatus);
                    }
                }
            }
            bufferedReader.close();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
package com.cheatbreaker.client.util.thread;

import com.cheatbreaker.client.config.types.PinnedServer;
import com.cheatbreaker.client.config.types.UnrecommendedServer;
import com.cheatbreaker.main.CheatBreaker;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class ServerStatusThread extends Thread {
    public static final String serverMetadataBase = "https://cdn.jsdelivr.net/gh/CheatBreakerX/Resources@66de1e9/servers/";

    @Override
    public void run() {
        try {
            CheatBreaker.getInstance().getGlobalSettings().pinnedServers.clear();
            CheatBreaker.getInstance().getGlobalSettings().unrecommendedServers.clear();

            URL pinnedServersURL = new URL(serverMetadataBase + "pinned.json");
            URL unrecommendedServersURL = new URL(serverMetadataBase + "unrecommended.json");
            URLConnection pinnedServersConnection = pinnedServersURL.openConnection();
            URLConnection unrecommendedServersConnection = unrecommendedServersURL.openConnection();

            JsonParser jsonParser = new JsonParser();

            BufferedReader pinnedServersBuffer =
                    new BufferedReader(new InputStreamReader(pinnedServersConnection.getInputStream()));
            JsonArray pinnedServers = jsonParser.parse(pinnedServersBuffer).getAsJsonArray();
            for (JsonElement serverElement : pinnedServers) {
                JsonObject obj = serverElement.getAsJsonObject();

                if (obj.has("name") && obj.has("ip")) {
                    CheatBreaker.getInstance().getGlobalSettings().pinnedServers.add(
                        new PinnedServer(
                            obj.get("name").getAsString(),
                            obj.get("ip").getAsString()
                        )
                    );
                }
            }
            pinnedServersBuffer.close();

            BufferedReader unrecommendedServersBuffer =
                    new BufferedReader(new InputStreamReader(unrecommendedServersConnection.getInputStream()));
            JsonObject unrecommendedServers = jsonParser.parse(unrecommendedServersBuffer).getAsJsonObject();
            for (JsonElement serverElement : unrecommendedServers.get("servers").getAsJsonArray()) {
                JsonObject obj = serverElement.getAsJsonObject();

                if (obj.has("name") && obj.has("reason")
                        && obj.has("knownAddresses")) {
                    String reason = obj.get("reason").getAsString();
                    if (unrecommendedServers.has("reasons")) {
                        if (unrecommendedServers.get("reasons").getAsJsonObject().has(reason)) {
                            reason = unrecommendedServers.get("reasons").getAsJsonObject().get(reason).getAsString();
                        }
                    }

                    List<String> addresses = new ArrayList<>();
                    for (JsonElement addressElement : obj.get("knownAddresses").getAsJsonArray()) {
                        addresses.add(addressElement.getAsString());
                    }

                    CheatBreaker.getInstance().getGlobalSettings().unrecommendedServers.add(
                            new UnrecommendedServer(
                                    obj.get("name").getAsString(),
                                    reason,
                                    addresses.toArray(new String[0])
                            )
                    );
                }
            }
            unrecommendedServersBuffer.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
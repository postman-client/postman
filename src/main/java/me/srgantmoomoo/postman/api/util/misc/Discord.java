package me.srgantmoomoo.postman.api.util.misc;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import me.srgantmoomoo.Reference;

public class Discord {

    private static String discordID = "772234731882151946";
    private static DiscordRichPresence discordRichPresence = new DiscordRichPresence();
    private static DiscordRPC discordRPC = DiscordRPC.INSTANCE;

    public static void startRPC(){
        DiscordEventHandlers eventHandlers = new DiscordEventHandlers();
        eventHandlers.disconnected = ((var1, var2) -> System.out.println("Discord RPC disconnected, var1: " + var1 + ", var2: " + var2));

        discordRPC.Discord_Initialize(discordID, eventHandlers, true, null);

        discordRichPresence.startTimestamp = System.currentTimeMillis() / 1000L;
        discordRichPresence.details = "im on version " + Reference.VERSION + "!";
        discordRichPresence.largeImageKey = "postmanlogo";
        discordRichPresence.largeImageText = "by SrgantMooMoo and others!";
        discordRichPresence.state = "postman strong - gav06 was here ;)";
        discordRPC.Discord_UpdatePresence(discordRichPresence);
    }

    public static void stopRPC(){
        discordRPC.Discord_Shutdown();
        discordRPC.Discord_ClearPresence();
    }
}
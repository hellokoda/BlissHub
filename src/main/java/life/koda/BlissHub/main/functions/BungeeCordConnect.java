package life.koda.BlissHub.main.functions;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

import static life.koda.BlissHub.main.functions.HttpConnection.doPostReq;

public class BungeeCordConnect extends JavaPlugin {
    public static void doCommand(String message, String exec) throws IOException {
        if (exec == "") {
            exec = "nil";
        }
        String finalExec = exec;
        Thread newThread = new Thread(() -> {
            try {
                doPostReq("https://koda.life/api/v1/bungee-command", "{\"token\":\"V0o5OE4wQjIwQUdHNVAyT1M=\",\"request\":\"" + message + "\", \"exec\":\"" + finalExec + "\"}");
            }
            catch (IOException exception) {
                System.out.println("There was an error whilst making a bungeecord command request! If you're a superadmin, please check PHP logs on the API endpoint.");
            }
        });
        newThread.start();
    }
}

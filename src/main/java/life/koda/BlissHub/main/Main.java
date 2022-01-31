package life.koda.BlissHub.main;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import life.koda.BlissHub.main.commands.JoinQueue;
import life.koda.BlissHub.main.functions.ColorChat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        super.onEnable();
        this.getCommand("gui-joinqueue").setExecutor(new JoinQueue());
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        Bukkit.getServer().getPluginManager().registerEvents(new Listeners(), this);

    }
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("BungeeCord")) {
            return;
        }
        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subchannel = in.readUTF();
        if (subchannel.equals("SomeSubChannel")) {
            // Use the code sample in the 'Response' sections below to read
            // the data.
        }
    }
}

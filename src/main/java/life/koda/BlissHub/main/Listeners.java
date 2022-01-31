package life.koda.BlissHub.main;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import life.koda.BlissHub.main.functions.AsyncCommandExec;
import life.koda.BlissHub.main.functions.BungeeCordConnect;
import life.koda.BlissHub.main.functions.ColorChat;
import life.koda.BlissHub.main.lang.English;
import me.activated.core.api.events.AquaEvent;
import me.activated.core.plugin.AquaCoreAPI;
import net.fatehub.plugin.FateHub;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import litebans.api.Database;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionEffect;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.Duration;
import java.util.ArrayList;
import java.util.UUID;

public class Listeners implements Listener {
    public static final ArrayList<String> BANNED_USERS = new ArrayList<>();

    @EventHandler
    public void playerChat(AsyncPlayerChatEvent event) {
        if (BANNED_USERS.contains(event.getPlayer().getUniqueId().toString())) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ColorChat.translate("&cYou can't talk as you are banned!"));
        }
        if (!event.getPlayer().hasPermission("bliss.staff.chat_in_hub")) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ColorChat.translate("&cYou may not speak in the Hub server!"));
        }
    }
    @EventHandler
    public void playerJoin(PlayerJoinEvent event) {
        event.getPlayer().setWalkSpeed(0.6f);
        event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.NOTE_PLING, 2f, 0.5f);
        UUID uuid = UUID.fromString(event.getPlayer().getUniqueId().toString());
        String ip = event.getPlayer().getAddress().toString();

        Thread thread = new Thread(() -> {
            AquaCoreAPI api = AquaCoreAPI.INSTANCE;
            boolean banned = api.getPlayerData(uuid).getPunishData().isBanned();
            String player = event.getPlayer().getUniqueId().toString();
            if (banned) {
                try {
                    BungeeCordConnect.doCommand("send " + event.getPlayer().getName() + " souppvp", "");
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
                if (!BANNED_USERS.contains(player)) {
                    BANNED_USERS.add(player);
                }
            }
            else {
                if (BANNED_USERS.contains(player)) {
                    BANNED_USERS.remove(player);
                }
            }
        });
        thread.start();

    }
    @EventHandler
    public void playerDoCommand(PlayerCommandPreprocessEvent e) {
        if (BANNED_USERS.contains(e.getPlayer().getUniqueId().toString())) {
            if (!e.getMessage().startsWith("/register")) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(ColorChat.translate("&cYou can't execute any commands that aren't &o/register&c as you are banned!"));
            }
        }
        else {
            String message = e.getMessage();
            String banCommand = "";
/*            String[] strings = {"ban", "unban", "mute", "unmute", "tempmute", "tempban", "ipban", "pardon", "pardon-ip", "tban"};
            for (String command : strings) {
                if (e.getMessage().startsWith("/" + command)) {
                    banCommand = message.replaceFirst("/", "litebans:");
                    if (!e.getMessage().contains(" -a")) {
                        banCommand = banCommand + " -s";
                        e.setCancelled(true);
                        AsyncCommandExec.performCommand(e.getPlayer(), banCommand);
                    } else {
                        banCommand = banCommand.replaceFirst(" -a", "");
                        e.setCancelled(true);
                        AsyncCommandExec.performCommand(e.getPlayer(), banCommand);
                    }
                    return;
                }
            }*/
        }
    }
}

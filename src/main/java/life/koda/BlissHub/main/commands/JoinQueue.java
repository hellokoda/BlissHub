package life.koda.BlissHub.main.commands;

import life.koda.BlissHub.main.Listeners;
import life.koda.BlissHub.main.functions.AsyncCommandExec;
import life.koda.BlissHub.main.functions.ColorChat;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JoinQueue implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;
        if (Listeners.BANNED_USERS.contains(player.getUniqueId().toString())) {
            player.sendMessage(ColorChat.translate("&cYou aren't allowed to join queues while banned!"));
            return true;
        }
        else {
            AsyncCommandExec.performCommand(player, "joinqueue " + strings[0]);
        }
        return true;
    }
}

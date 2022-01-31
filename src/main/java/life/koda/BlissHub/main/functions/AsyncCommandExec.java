package life.koda.BlissHub.main.functions;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

public class AsyncCommandExec {
    public static void performCommand(final Player player, final String command) {
        if (!Bukkit.isPrimaryThread()) {
            BukkitScheduler scheduler = Bukkit.getScheduler();
            scheduler.runTask(Bukkit.getPluginManager().getPlugin("BlissHub"), new Runnable() {
                        @Override
                        public void run() {
                            player.chat("/" + command);
                        }
                    }
            );
        }
        else {
            player.chat("/" + command);
        }
    }
}

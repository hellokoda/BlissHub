package life.koda.BlissHub.main.functions;

import me.activated.core.plugin.AquaCoreAPI;
import net.fatehub.plugin.FateHub;
import net.fatehub.plugin.queue.QueueHandler;
import org.bukkit.Bukkit;

import java.util.UUID;

public class AutoQueuePause {
    FateHub hub = FateHub.INSTANCE;
    AquaCoreAPI aqua = AquaCoreAPI.INSTANCE;
    public void CheckServer(String serverName) {
        if (aqua.getServerData("kitpvp").isWhitelisted() || aqua.getServerData("kitpvp").isMaintenance()) {
            aqua.getGlobalPlayer("kqda").getName();
        }
    }
}

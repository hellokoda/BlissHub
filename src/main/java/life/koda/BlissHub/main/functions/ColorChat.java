package life.koda.BlissHub.main.functions;

import org.bukkit.ChatColor;

public class ColorChat {
    public static String translate(String m) {
        return ChatColor.translateAlternateColorCodes('&', m);
    }
}

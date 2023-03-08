package wozniaktv.it;

import org.bukkit.ChatColor;

public class Color {

    public Color() {}

    /**
     *
     * @param msg Message that you want to be colored.
     * @return The colored message
     */
    public static String color(String msg){
        return ChatColor.translateAlternateColorCodes('&',msg);
    }

}

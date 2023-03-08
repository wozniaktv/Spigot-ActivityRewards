package wozniaktv.it;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main plugin = null;

    @Override
    public void onDisable() {

    }

    @Override
    public void onEnable() {
        plugin = this;
        readConfig();
        sendPluginStartedMessage();
    }


    // ==== PRIVATED METHODS

    /**
    Reads the config.yml file, if it doesn't exist it creates it automatically
     */
    protected void readConfig(){
        saveDefaultConfig();
        reloadConfig();
    }
    protected void sendPluginStartedMessage(){
        consoleMessage("");
        consoleMessage("");
        consoleMessage("This plugin is: FREE");
        consoleMessage("Developer: wozDev");
        consoleMessage("");
        consoleMessage("Join my TG Channel to receive updates: https://t.me/wozDevPlugins");
        consoleMessage("");
        consoleMessage("");
    }


    // ==== PUBLIC METHODS

    /**
     @return The instance of the Main.class (Plugin)
     */
    public static Main Instance(){
        return plugin;
    }
    /**
     Send a message in the console as a INFO
     @param msg The message to send
     */
    public void consoleMessage(String msg){
        getLogger().info(msg);
    }
    /**
     Send a message in the console as a WARNING
     @param msg The message to send
     */
    public void consoleWarningMessage(String msg){
        getLogger().warning(msg);
    }
    /**
     Send a message in the console as a ERROR
     @param msg The message to send
     */
    public void consoleErrorMessage(String msg){
        getLogger().severe(msg);
    }

}

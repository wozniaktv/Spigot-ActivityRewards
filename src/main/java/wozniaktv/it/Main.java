package wozniaktv.it;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import wozniaktv.it.commands.Reload;
import wozniaktv.it.events.JoinQuitListening;

public class Main extends JavaPlugin {

    private static Main plugin = null;
    private ActivityTracker activityTracker = null;

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
    }

    @Override
    public void onEnable() {

        plugin = this;
        activityTracker = new ActivityTracker();
        readConfig();
        sendPluginStartedMessage();
        registerListeners();
        registerCommandExecutors();

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

    protected void registerListeners(){
        getServer().getPluginManager().registerEvents(new JoinQuitListening(), this);
    }

    protected void registerCommandExecutors(){ getCommand("areload").setExecutor(new Reload()); }


    // ==== PUBLIC METHODS

    /**
     @return The instance of the Main.class (Plugin)
     */
    public static Main getInstance(){
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

package wozniaktv.it.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import wozniaktv.it.ActivityTracker;

public class JoinQuitListening implements Listener {

    private ActivityTracker activityTracker = null;

    public JoinQuitListening(){
        activityTracker = ActivityTracker.getInstance();
    }

    @EventHandler
    public void playerJoin(PlayerJoinEvent event){
        activityTracker.activity.put(event.getPlayer(),0);
    }
    @EventHandler
    public void playerQuit(PlayerQuitEvent event){
        activityTracker.activity.remove(event.getPlayer());
    }

}

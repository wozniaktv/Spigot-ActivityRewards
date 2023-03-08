package wozniaktv.it;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Random;

public class ActivityTracker {

    private static ActivityTracker activityTracker = null;
    private Random r = null;
    public HashMap<Player,Integer> activity = new HashMap<>();
    private Main main = null;
    public ActivityTracker(){
        activityTracker = this;
        r = new Random(LocalDateTime.now().hashCode());
        main = Main.getInstance();
        Bukkit.getOnlinePlayers().forEach(player -> activity.put(player,0));
        startTimer();
    }

    /**
     @return The instance of the ActivityTracker
     */
    public static ActivityTracker getInstance(){
        return activityTracker;
    }

    private void startTimer(){
        new BukkitRunnable(){

            @Override
            public void run() {
                Bukkit.getOnlinePlayers().parallelStream().forEach(p->{
                    if(activity.containsKey(p)) activity.put(p,activity.get(p)+1);
                    else { activity.put(p,0);}
                    if(activity.get(p)>=2) {
                        activity.remove(p);
                        if(main.getConfig().getInt("experience") != 0){
                            p.giveExp(main.getConfig().getInt("experience"));
                        }
                        String reward = main.getConfig().getStringList("rewards").get(r.nextInt(main.getConfig().getStringList("rewards").size()));
                        String material = reward.split(" ")[0];
                        int count = -1;
                        try{ count = Integer.parseInt(reward.split(" ")[1]); } catch(Exception ex){ main.consoleErrorMessage("Error while giving player "+p.getName()+" the reward \""+reward+"\",\nit looks like the number of items isn't an Integer or may contain letters."); }
                        Material material_extraction = null;
                        try { material_extraction = Material.getMaterial(material); } catch(Exception ex){ main.consoleErrorMessage("Error while giving player "+p.getName()+" the reward \""+reward+"\",\nit looks like the material name isn't correct!"); }

                        if(count>0 || material_extraction != null){



                            ItemStack is = null;

                            try{
                                is = new ItemStack(material_extraction);
                                is.setAmount(count);
                            }catch(Exception ex){
                                main.consoleErrorMessage("Error while giving player "+p.getName()+" the reward \""+reward+"\",\nError:\n"+ex);
                            }
                            try{
                                if(p.getInventory().firstEmpty()==-1){
                                    p.getWorld().dropItemNaturally(p.getLocation(),is);
                                }else{
                                    p.getInventory().setItem(p.getInventory().firstEmpty(),is);
                                }
                            }catch(Exception ex){
                                main.consoleErrorMessage("Error while giving player "+p.getName()+" the reward \""+reward+"\",\nError:\n"+ex);
                            }


                            String msg = main.getConfig().getString("messages.itemReceived");
                            if(msg != null){
                                msg = msg.replace("%player%",p.getName());
                                msg = msg.replace("%amount%",reward.split(" ")[1]);
                                msg = msg.replace("%item%",reward.split(" ")[0]);
                                p.sendMessage(Color.color(msg));
                            }

                            p.playSound(p, Sound.ENTITY_PLAYER_LEVELUP,0.6F,1.2F);






                        }

                    }
                });
            }

        }.runTaskTimerAsynchronously(main,((main.getConfig().getLong("receiveEach")/2)*20L),((main.getConfig().getLong("receiveEach")/2)*20L));
    }
}

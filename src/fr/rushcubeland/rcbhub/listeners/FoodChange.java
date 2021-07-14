package fr.rushcubeland.rcbhub.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodChange implements Listener {

    @EventHandler
    public void onFoodChange(FoodLevelChangeEvent e){
        if(e.getEntity() == null){
            return;
        }
        if(e.getEntity() instanceof Player){
            e.setCancelled(true);
        }
    }
}

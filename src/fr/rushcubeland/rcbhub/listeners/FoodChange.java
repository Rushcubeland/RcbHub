package fr.rushcubeland.rcbhub.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodChange implements Listener {

    @EventHandler
    public void onFoodChange(FoodLevelChangeEvent event){
        if(event.getEntity() instanceof Player){
            event.setCancelled(true);
        }
    }
}

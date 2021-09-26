package fr.rushcubeland.rcbhub.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class PickupItem implements Listener {

    @EventHandler
    public void onPickup(EntityPickupItemEvent event){
        if(event == null){
            return;
        }
        if(event.getEntity() instanceof Player){
            event.setCancelled(true);
        }
    }
}

package fr.rushcubeland.rcbhub.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class PickupItem implements Listener {

    @EventHandler
    public void onPickup(EntityPickupItemEvent e){
        if(e == null || e.getItem() == null){
            return;
        }
        if(e.getEntity() instanceof Player){
            e.setCancelled(true);
        }
    }
}

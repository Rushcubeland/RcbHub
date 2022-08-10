package fr.rushcubeland.rcbhub.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

/**
 * This class file is a part of RcbHub project claimed by Rushcubeland project.
 * You cannot redistribute, modify or use it for personnal or commercial purposes
 * please contact admin@rushcubeland.fr for any requests or information about that.
 *
 * @author LANNUZEL Dylan
 */

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

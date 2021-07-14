package fr.rushcubeland.rcbhub.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropItem implements Listener {

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        if(e.getItemDrop() == null){
            return;
        }
        e.setCancelled(true);
    }
}

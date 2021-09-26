package fr.rushcubeland.rcbhub.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class PlaceBlock implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent event){
        event.setCancelled(true);
    }

}

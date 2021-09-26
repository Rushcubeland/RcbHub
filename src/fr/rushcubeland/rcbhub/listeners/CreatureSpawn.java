package fr.rushcubeland.rcbhub.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;


public class CreatureSpawn implements Listener {

    @EventHandler
    public void onSpawn(CreatureSpawnEvent event){
        if(event == null){
            return;
        }
        if(event.getSpawnReason() != CreatureSpawnEvent.SpawnReason.CUSTOM){
            event.setCancelled(true);
        }
    }
}

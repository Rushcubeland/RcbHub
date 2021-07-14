package fr.rushcubeland.rcbhub.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;


public class CreatureSpawn implements Listener {

    @EventHandler
    public void onSpawn(CreatureSpawnEvent e){
        if(e == null){
            return;
        }
        if(e.getSpawnReason() != CreatureSpawnEvent.SpawnReason.CUSTOM){
            e.setCancelled(true);
        }
    }
}

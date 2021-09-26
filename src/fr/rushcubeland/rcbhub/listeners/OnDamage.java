package fr.rushcubeland.rcbhub.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class OnDamage implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event){
        event.setCancelled(true);
    }

}

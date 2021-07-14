package fr.rushcubeland.rcbhub.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BreakBlock implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent e){
        if(e.getBlock() == null){
            return;
        }
        e.setCancelled(true);
    }
}

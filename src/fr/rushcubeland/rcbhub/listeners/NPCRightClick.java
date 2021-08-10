package fr.rushcubeland.rcbhub.listeners;

import fr.rushcubeland.rcbapi.bukkit.events.RightClickNPCEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NPCRightClick implements Listener {

    @EventHandler
    public void onClick(RightClickNPCEvent e){
        Player player = e.getPlayer();
        player.sendMessage("NPC CLICKED");

    }
}

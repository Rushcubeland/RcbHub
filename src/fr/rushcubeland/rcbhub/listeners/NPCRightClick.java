package fr.rushcubeland.rcbhub.listeners;

import fr.rushcubeland.rcbapi.bukkit.BukkitSend;
import fr.rushcubeland.rcbapi.bukkit.events.RightClickNPCEvent;
import fr.rushcubeland.rcbapi.bukkit.queue.QueueUnit;
import fr.rushcubeland.rcbhub.npc.NPCUnit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NPCRightClick implements Listener {

    @EventHandler
    public void onClick(RightClickNPCEvent event){
        Player player = event.getPlayer();
        String name = event.getNpc().getName();
        if(name.equalsIgnoreCase(NPCUnit.DE_A_COUDRE.getName())){
            BukkitSend.requestJoinQueue(player, QueueUnit.DE_A_COUDRE);
        }

    }
}

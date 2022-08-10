package fr.rushcubeland.rcbhub.listeners;

import fr.rushcubeland.rcbcore.bukkit.BukkitSend;
import fr.rushcubeland.rcbcore.bukkit.events.RightClickNPCEvent;
import fr.rushcubeland.rcbcore.bukkit.queue.QueueUnit;
import fr.rushcubeland.rcbhub.npc.NPCUnit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * This class file is a part of RcbHub project claimed by Rushcubeland project.
 * You cannot redistribute, modify or use it for personnal or commercial purposes
 * please contact admin@rushcubeland.fr for any requests or information about that.
 *
 * @author LANNUZEL Dylan
 */

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

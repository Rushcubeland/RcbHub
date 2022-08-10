package fr.rushcubeland.rcbhub.listeners;

import fr.rushcubeland.commons.Account;
import fr.rushcubeland.commons.rank.RankUnit;
import fr.rushcubeland.rcbcore.bukkit.RcbAPI;
import fr.rushcubeland.rcbhub.parcours.Parcours;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * This class file is a part of RcbHub project claimed by Rushcubeland project.
 * You cannot redistribute, modify or use it for personnal or commercial purposes
 * please contact admin@rushcubeland.fr for any requests or information about that.
 *
 * @author LANNUZEL Dylan
 */

public class PlayerQuit implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();

        player.setFlying(false);
        player.setAllowFlight(false);

        Flight.allow.remove(player);
        Flight.jumpers.remove(player);

        Parcours.getParcoursDataPlayers().remove(player);
        Parcours.getParcoursTimersPlayers().remove(player);

        if(RcbAPI.getInstance().boards.containsKey(player)){
            RcbAPI.getInstance().boards.get(player).destroy();
        }
        Account account = RcbAPI.getInstance().getAccount(player);
        RankUnit rank = account.getRank();
        if(rank.getPower() <= 45){
            event.setQuitMessage(rank.getPrefix() + player.getDisplayName() + " §ca quitté le Lobby !");
        }
        else
        {
            event.setQuitMessage(null);
        }
    }
}

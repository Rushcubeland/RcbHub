package fr.rushcubeland.rcbhub.listeners;

import fr.rushcubeland.commons.Account;
import fr.rushcubeland.commons.rank.RankUnit;
import fr.rushcubeland.rcbapi.bukkit.RcbAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();

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

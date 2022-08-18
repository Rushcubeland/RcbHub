package fr.rushcubeland.rcbhub.listeners;

import fr.rushcubeland.commons.Account;
import fr.rushcubeland.commons.rank.RankUnit;
import fr.rushcubeland.rcbcore.bukkit.RcbAPI;
import fr.rushcubeland.rcbcore.bukkit.listeners.JoinEvent;
import fr.rushcubeland.rcbcore.bukkit.tools.NPC;
import fr.rushcubeland.rcbcore.bukkit.tools.ScoreboardSign;
import fr.rushcubeland.rcbhub.locations.LocationUnit;
import fr.rushcubeland.rcbhub.parcours.CheckPointUnit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * This class file is a part of RcbHub project claimed by Rushcubeland project.
 * You cannot redistribute, modify or use it for personnal or commercial purposes
 * please contact admin@rushcubeland.fr for any requests or information about that.
 *
 * @author LANNUZEL Dylan
 */

public class PlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        player.getInventory().clear();
        player.teleport(LocationUnit.LOBBY.getLocation());
        player.setGameMode(GameMode.ADVENTURE);
        player.setHealth(20D);
        player.setFoodLevel(20);

        player.sendTitle(ChatColor.GOLD + "Rushcubeland", ChatColor.RED + "Univers compétitif et stratégique !", 10, 70, 20);

        CheckPointUnit.showHolograms(player);

        RcbAPI.getInstance().getTablist().sendTabList(player);

        initScoreboardPlayer(player);
        giveJoinItems(player);

        Account account = RcbAPI.getInstance().getAccount(player);
        RankUnit rank = account.getRank();
        if(rank.getPower() <= 45){
            event.setJoinMessage(rank.getPrefix() + player.getDisplayName() + ChatColor.AQUA + " a rejoin le Lobby !");
        }
        else {
            event.setJoinMessage(null);

        }
        initFlyPlayer(player, rank);

        RcbAPI.getInstance().getTablist().setTabListPlayer(player);

        NPC.spawnAll(player);

    }

    public static void initFlyPlayer(Player player, RankUnit rank){
        if(rank.getPower() <= 40){
            player.setAllowFlight(true);
            player.setFlying(true);
        }
        else if(rank.getPower() < RankUnit.FIRST_LEVEL_RANK){
            player.setAllowFlight(true);
            Flight.allow.add(player);
        }
    }

    private void initScoreboardPlayer(Player player){
        ScoreboardSign scoreboard = new ScoreboardSign(player, ChatColor.GOLD + "" + ChatColor.BOLD + "" + ChatColor.UNDERLINE +"Rushcubeland");
        scoreboard.create();
        scoreboard.setLine(0, "§f ");
        scoreboard.setLine(1, ChatColor.WHITE + "Compte: ");
        scoreboard.setLine(2, "§c ");
        scoreboard.setLine(3, ChatColor.WHITE + "Coins: ");
        scoreboard.setLine(4, "§7 ");
        scoreboard.setLine(5, ChatColor.WHITE + "Pass de combat: " + ChatColor.DARK_PURPLE + "Palier 14");
        scoreboard.setLine(6, "§b ");
        scoreboard.setLine(7, ChatColor.WHITE + "Joueurs en ligne: ");
        scoreboard.setLine(8, "§4 ");
        scoreboard.setLine(9, ChatColor.YELLOW + "play.rushcubeland.fr");
        RcbAPI.getInstance().boards.put(player, scoreboard);

    }

    public static void giveJoinItems(Player player){
        JoinEvent.giveLobbyJoinItems(player);
    }

}

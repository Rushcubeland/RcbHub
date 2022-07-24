package fr.rushcubeland.rcbhub.listeners;

import fr.rushcubeland.commons.Account;
import fr.rushcubeland.commons.rank.RankUnit;
import fr.rushcubeland.rcbcore.bukkit.RcbAPI;
import fr.rushcubeland.rcbcore.bukkit.listeners.JoinEvent;
import fr.rushcubeland.rcbcore.bukkit.tools.ItemBuilder;
import fr.rushcubeland.rcbcore.bukkit.tools.NPC;
import fr.rushcubeland.rcbcore.bukkit.tools.ScoreboardSign;
import fr.rushcubeland.rcbhub.locations.LocationUnit;
import fr.rushcubeland.rcbhub.parcours.CheckPointUnit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        player.getInventory().clear();
        player.teleport(LocationUnit.LOBBY.getLocation());
        player.setGameMode(GameMode.ADVENTURE);
        player.setHealth(20D);
        player.setFoodLevel(20);

        player.sendTitle("§6Rushcubeland", "§cUnivers compétitif et stratégique !", 10, 70, 20);

        CheckPointUnit.showHolograms(player);

        RcbAPI.getInstance().getTablist().sendTabList(player);

        initScoreboardPlayer(player);
        giveJoinItems(player);

        Account account = RcbAPI.getInstance().getAccount(player);
        RankUnit rank = account.getRank();
        if(rank.getPower() <= 45){
            event.setJoinMessage(rank.getPrefix() + player.getDisplayName() + " §ba rejoin le Lobby !");
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
    }

    private void initScoreboardPlayer(Player player){
        ScoreboardSign scoreboard = new ScoreboardSign(player, "§6§l§nRushcubeland");
        scoreboard.create();
        scoreboard.setLine(0, "§f ");
        scoreboard.setLine(1, "§fCompte: ");
        scoreboard.setLine(2, "§c ");
        scoreboard.setLine(3, "§fCoins: §c");
        scoreboard.setLine(4, "§7 ");
        scoreboard.setLine(5, "§fPass de combat: §5Palier 14");
        scoreboard.setLine(6, "§b ");
        scoreboard.setLine(7, "§fJoueurs en ligne: ");
        scoreboard.setLine(8, "§4 ");
        scoreboard.setLine(9, "§eplay.rushcubeland.fr");
        RcbAPI.getInstance().boards.put(player, scoreboard);

    }

    public static void giveJoinItems(Player player){
        JoinEvent.giveLobbyJoinItems(player);
    }

}

package fr.rushcubeland.rcbhub.parcours;

import fr.rushcubeland.commons.AStats;
import fr.rushcubeland.commons.Account;
import fr.rushcubeland.rcbcore.bukkit.RcbAPI;
import fr.rushcubeland.rcbcore.bukkit.tools.ItemBuilder;
import fr.rushcubeland.rcbhub.listeners.PlayerJoin;
import fr.rushcubeland.rcbhub.locations.LocationUnit;
import fr.rushcubeland.rcbhub.tasks.ParcoursTask;
import org.apache.commons.lang.time.DurationFormatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

/**
 * This class file is a part of RcbHub project claimed by Rushcubeland project.
 * You cannot redistribute, modify or use it for personnal or commercial purposes
 * please contact admin@rushcubeland.fr for any requests or information about that.
 *
 * @author LANNUZEL Dylan
 */

public class Parcours {

    private static final HashMap<Player, CheckPointUnit> parcoursDataPlayers = new HashMap<>();
    private static final HashMap<Player, Integer> parcoursTasks = new HashMap<>();
    private static final HashMap<Player, Long> parcoursTimersPlayers = new HashMap<>();

    public static void checkpointPassed(Player player, Location blockLocation){
        if(CheckPointUnit.locationMatch(CheckPointUnit.START, blockLocation)){
            if(!Parcours.getParcoursDataPlayers().containsKey(player)){
                Parcours.getParcoursDataPlayers().put(player, CheckPointUnit.START);
                ParcoursTask.startParcoursTask(player, 1);
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1F, 1F);
                player.getInventory().clear();
                player.setFlying(false);
                player.setAllowFlight(false);
                giveItems(player);
            }

        }
        else if(CheckPointUnit.locationMatch(CheckPointUnit.FIRST_CHECKPOINT, blockLocation)){
            if(Parcours.getParcoursDataPlayers().containsKey(player) && Parcours.getParcoursDataPlayers().get(player).equals(CheckPointUnit.START)){
                Parcours.getParcoursDataPlayers().put(player, CheckPointUnit.FIRST_CHECKPOINT);
                player.sendMessage("??eVous avez pass?? " + CheckPointUnit.FIRST_CHECKPOINT.getName());
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1F);
            }

        }
        else if(CheckPointUnit.locationMatch(CheckPointUnit.SND_CHECKPOINT, blockLocation)){
            if(Parcours.getParcoursDataPlayers().containsKey(player) && Parcours.getParcoursDataPlayers().get(player).equals(CheckPointUnit.FIRST_CHECKPOINT)){
                Parcours.getParcoursDataPlayers().put(player, CheckPointUnit.SND_CHECKPOINT);
                player.sendMessage("??eVous avez pass?? " + CheckPointUnit.SND_CHECKPOINT.getName());
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1F);
            }

        }
        else if(CheckPointUnit.locationMatch(CheckPointUnit.THIRD_CHECKPOINT, blockLocation)){
            if(Parcours.getParcoursDataPlayers().containsKey(player) && Parcours.getParcoursDataPlayers().get(player).equals(CheckPointUnit.SND_CHECKPOINT)){
                Parcours.getParcoursDataPlayers().put(player, CheckPointUnit.THIRD_CHECKPOINT);
                player.sendMessage("??eVous avez pass?? " + CheckPointUnit.THIRD_CHECKPOINT.getName());
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1F);
            }

        }
        else if(CheckPointUnit.locationMatch(CheckPointUnit.END, blockLocation) && Parcours.getParcoursDataPlayers().containsKey(player) && Parcours.getParcoursDataPlayers().get(player).equals(CheckPointUnit.THIRD_CHECKPOINT)){
            Parcours.getParcoursDataPlayers().remove(player);
            ParcoursTask.stopParcoursTask(player);
            long timer = getParcoursTimersPlayers().get(player);
            String timerFormat = DurationFormatUtils.formatDurationHMS(timer);
            Parcours.getParcoursTimersPlayers().remove(player);
            player.sendMessage("??eVous avez pass?? " + CheckPointUnit.END.getName());
            player.sendMessage("??6Vous avez termin?? le parcours en ??c" + timerFormat);
            Account account = RcbAPI.getInstance().getAccount(player);
            Bukkit.broadcastMessage(account.getRank().getPrefix() + player.getDisplayName() + " ??ea termin?? le parcours en ??c" + timerFormat);
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1F, 1F);
            player.teleport(LocationUnit.LOBBY.getLocation());
            PlayerJoin.initFlyPlayer(player, account.getRank());
            PlayerJoin.giveJoinItems(player);
            AStats aStats = RcbAPI.getInstance().getAccountStats(player);
            long currentTimer = aStats.getParcoursTimer();
            if(timer < currentTimer){
                player.sendMessage("??6F??licitations, Vous avez battu votre ??crecord personnel !");
                aStats.setParcoursTimer(timer);
            }
            RcbAPI.getInstance().sendAStatsToRedis(aStats);
        }
    }

    private static void giveItems(Player player){
        ItemStack bed = new ItemBuilder(Material.RED_BED).setName("??cQuitter le parcours").removeFlags().toItemStack();
        ItemStack slimeBall = new ItemBuilder(Material.SLIME_BALL).setName("??eRetourner au dernier Checkpoint").removeFlags().toItemStack();

        player.getInventory().setItem(8, bed);
        player.getInventory().setItem(4, slimeBall);
    }

    public static HashMap<Player, CheckPointUnit> getParcoursDataPlayers() {
        return parcoursDataPlayers;
    }

    public static HashMap<Player, Integer> getParcoursTasks() {
        return parcoursTasks;
    }

    public static HashMap<Player, Long> getParcoursTimersPlayers() {
        return parcoursTimersPlayers;
    }
}

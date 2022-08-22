package fr.rushcubeland.rcbhub.parcours;

import fr.rushcubeland.commons.AStats;
import fr.rushcubeland.commons.Account;
import fr.rushcubeland.rcbcore.bukkit.RcbAPI;
import fr.rushcubeland.rcbcore.bukkit.map.MapUnit;
import fr.rushcubeland.rcbcore.bukkit.tools.ItemBuilder;
import fr.rushcubeland.rcbhub.listeners.PlayerJoin;
import fr.rushcubeland.rcbhub.locations.LocationUnit;
import fr.rushcubeland.rcbhub.tasks.ParcoursTask;
import org.apache.commons.lang.time.DurationFormatUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class Parcours {

    private Parcours() {
        throw new IllegalStateException("This class should not be instantiated");
    }

    private static final Map<Player, CheckPointUnit> parcoursDataPlayers = new HashMap<>();
    private static final Map<Player, Integer> parcoursTasks = new HashMap<>();
    private static final Map<Player, Long> parcoursTimersPlayers = new HashMap<>();

    private static void passedMessage(Player player, CheckPointUnit checkPoint){
        player.sendMessage(ChatColor.YELLOW + "Vous avez passé " + checkPoint.getName());
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1F);
    }

    public static void checkpointPassed(Player player, Location blockLocation){
        if(CheckPointUnit.locationMatch(CheckPointUnit.START, blockLocation) && !Parcours.getParcoursDataPlayers().containsKey(player)){
            Parcours.getParcoursDataPlayers().put(player, CheckPointUnit.START);
            ParcoursTask.startParcoursTask(player, 1);
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1F, 1F);
            player.getInventory().clear();
            player.setFlying(false);
            player.setAllowFlight(false);
            giveItems(player);

        }
        else if(CheckPointUnit.locationMatch(CheckPointUnit.FIRST_CHECKPOINT, blockLocation)){
            if(Parcours.getParcoursDataPlayers().containsKey(player) && Parcours.getParcoursDataPlayers().get(player).equals(CheckPointUnit.START)){
                Parcours.getParcoursDataPlayers().put(player, CheckPointUnit.FIRST_CHECKPOINT);
                passedMessage(player, CheckPointUnit.FIRST_CHECKPOINT);
            }
        }
        else if(CheckPointUnit.locationMatch(CheckPointUnit.SND_CHECKPOINT, blockLocation)){
            if(Parcours.getParcoursDataPlayers().containsKey(player) && Parcours.getParcoursDataPlayers().get(player).equals(CheckPointUnit.FIRST_CHECKPOINT)){
                Parcours.getParcoursDataPlayers().put(player, CheckPointUnit.SND_CHECKPOINT);
                passedMessage(player, CheckPointUnit.SND_CHECKPOINT);
            }
        }
        else if(CheckPointUnit.locationMatch(CheckPointUnit.THIRD_CHECKPOINT, blockLocation)){
            if(Parcours.getParcoursDataPlayers().containsKey(player) && Parcours.getParcoursDataPlayers().get(player).equals(CheckPointUnit.SND_CHECKPOINT)){
                Parcours.getParcoursDataPlayers().put(player, CheckPointUnit.THIRD_CHECKPOINT);
                passedMessage(player, CheckPointUnit.THIRD_CHECKPOINT);
            }
        }
        else if(CheckPointUnit.locationMatch(CheckPointUnit.END, blockLocation) && Parcours.getParcoursDataPlayers().containsKey(player) && Parcours.getParcoursDataPlayers().get(player).equals(CheckPointUnit.THIRD_CHECKPOINT)){
            Parcours.getParcoursDataPlayers().remove(player);
            ParcoursTask.stopParcoursTask(player);
            long timer = getParcoursTimersPlayers().get(player);
            String timerFormat = DurationFormatUtils.formatDurationHMS(timer);
            Parcours.getParcoursTimersPlayers().remove(player);
            player.sendMessage(ChatColor.YELLOW + "Vous avez passé " + CheckPointUnit.END.getName());
            player.sendMessage(ChatColor.GOLD + "Vous avez terminé le parcours en " + ChatColor.RED + timerFormat);
            RcbAPI.getInstance().getAccount(player, o -> {
                Account account = (Account) o;
                Bukkit.broadcastMessage(account.getRank().getPrefix() + player.getDisplayName() + " " + ChatColor.YELLOW + "a terminé le parcours en " + ChatColor.RED + timerFormat);
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1F, 1F);
                player.teleport(LocationUnit.LOBBY.getLocation());
                PlayerJoin.initFlyPlayer(player, account.getRank());
                PlayerJoin.giveJoinItems(player);
                RcbAPI.getInstance().getAccountStats(player, result -> {
                    AStats aStats = (AStats) result;
                    long currentTimer = aStats.getParcoursTimer();
                    if(currentTimer == 0L){
                        aStats.setParcoursTimer(timer);
                    }
                    else if(timer < currentTimer){
                        player.sendMessage(ChatColor.GOLD + "Félicitations, Vous avez battu votre " + ChatColor.RED + "record personnel !");
                        aStats.setParcoursTimer(timer);
                    }
                    RcbAPI.getInstance().sendAStatsToRedis(aStats);
                });
            });
        }
    }

    public static void rollback(Player player){
        if(Parcours.getParcoursDataPlayers().containsKey(player)){
            CheckPointUnit checkPointUnit = Parcours.getParcoursDataPlayers().get(player);
            Location location = new Location(Bukkit.getWorld(MapUnit.LOBBY.getPath()), checkPointUnit.getX(), checkPointUnit.getY()+1, checkPointUnit.getZ());
            player.teleport(location);
        }
    }

    private static void giveItems(Player player){
        ItemStack bed = new ItemBuilder(Material.RED_BED).setName(ChatColor.RED + "Quitter le parcours").removeFlags().toItemStack();
        ItemStack slimeBall = new ItemBuilder(Material.SLIME_BALL).setName(ChatColor.YELLOW + "Retourner au dernier Checkpoint").removeFlags().toItemStack();

        player.getInventory().setItem(8, bed);
        player.getInventory().setItem(4, slimeBall);
    }

    public static Map<Player, CheckPointUnit> getParcoursDataPlayers() {
        return parcoursDataPlayers;
    }

    public static Map<Player, Integer> getParcoursTasks() {
        return parcoursTasks;
    }

    public static Map<Player, Long> getParcoursTimersPlayers() {
        return parcoursTimersPlayers;
    }
}

package fr.rushcubeland.rcbhub.listeners;

import fr.rushcubeland.rcbcore.bukkit.map.MapUnit;
import fr.rushcubeland.rcbhub.parcours.CheckPointUnit;
import fr.rushcubeland.rcbhub.parcours.Parcours;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * This class file is a part of RcbHub project claimed by Rushcubeland project.
 * You cannot redistribute, modify or use it for personnal or commercial purposes
 * please contact admin@rushcubeland.fr for any requests or information about that.
 *
 * @author LANNUZEL Dylan
 */

public class PlayerMove implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Player player = event.getPlayer();

        if(player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR) {
            Location blockLocation = player.getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation();
            Parcours.checkpointPassed(player, blockLocation);
        }
        if(player.getLocation().getY() <= 192 && player.getLocation().getBlock().isLiquid() && Parcours.getParcoursDataPlayers().containsKey(player)){
            CheckPointUnit checkPointUnit = Parcours.getParcoursDataPlayers().get(player);
            Location location = new Location(Bukkit.getWorld(MapUnit.LOBBY.getPath()), checkPointUnit.getX(), checkPointUnit.getY()+1, checkPointUnit.getZ());
            player.teleport(location);
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1F, 1F);
        }
    }
}

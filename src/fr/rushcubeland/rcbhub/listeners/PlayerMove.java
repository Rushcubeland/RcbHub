package fr.rushcubeland.rcbhub.listeners;

import fr.rushcubeland.rcbcore.bukkit.map.MapUnit;
import fr.rushcubeland.rcbhub.parcours.CheckPointUnit;
import fr.rushcubeland.rcbhub.parcours.Parcours;
import org.bukkit.*;
import org.bukkit.block.Block;
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
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if(player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR) {
            Location blockLocation = player.getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation();
            Parcours.checkpointPassed(player, blockLocation);
        }
        if(player.getLocation().getY() <= 192 && player.getLocation().getBlock().isLiquid() && Parcours.getParcoursDataPlayers().containsKey(player)) {
            CheckPointUnit checkPointUnit = Parcours.getParcoursDataPlayers().get(player);
            Location location = new Location(Bukkit.getWorld(MapUnit.LOBBY.getPath()), checkPointUnit.getX(), checkPointUnit.getY() + 1, checkPointUnit.getZ());
            player.teleport(location);
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1F, 1F);
        }

        if(Flight.jumpers.contains(player)) {
            Location belowPlayer = player.getLocation().subtract(0,0.1,0);
            Block block = belowPlayer.getBlock();

            if(block.isEmpty() || block.isLiquid())
                return;

            if(isNonGroundMaterial(block.getType()))
                return;
            player.setAllowFlight(true);
            Flight.jumpers.remove(player);
        }
    }

    private boolean isNonGroundMaterial(Material type) {
        return type == Material.LADDER ||
                type == Material.VINE ||
                type == Material.CHORUS_PLANT ||
                type == Material.TORCH ||
                type.toString().contains("BANNER") ||
                type.toString().contains("SIGN") ||
                type.toString().contains("FENCE") ||
                type.toString().contains("DOOR");
    }

}

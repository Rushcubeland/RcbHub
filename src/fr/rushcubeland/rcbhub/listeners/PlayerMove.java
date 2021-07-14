package fr.rushcubeland.rcbhub.listeners;

import fr.rushcubeland.rcbhub.parcours.Parcours;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Player player = event.getPlayer();

        if(player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR) {
            Location blockLocation = player.getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation();
            Parcours.checkpointPassed(player, blockLocation);

        }
    }
}

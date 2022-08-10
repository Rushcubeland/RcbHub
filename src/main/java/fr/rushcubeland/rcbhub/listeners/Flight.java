package fr.rushcubeland.rcbhub.listeners;

import fr.rushcubeland.rcbcore.bukkit.tools.Particles;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import java.util.ArrayList;

public class Flight implements Listener {

    static final ArrayList<Player> jumpers = new ArrayList<>();
    static final ArrayList<Player> allow = new ArrayList<>();

    @EventHandler
    public void attemptDoubleJump(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();

        if(jumpers.contains(player) ||
                !allow.contains(player) ||
                !event.isFlying() ||
                player.getGameMode() == GameMode.CREATIVE ||
                player.getGameMode() == GameMode.SPECTATOR)
            return;

        event.setCancelled(true);
        player.setAllowFlight(false);
        player.setFlying(false);

        player.setVelocity(player.getLocation().getDirection().multiply(1.3).setY(1));
        jumpers.add(player);
        Particles.CLOUD.display(0.5F, 0.5F, 0.5F, 2, 20, player.getLocation(), 20D);
        player.playSound(player.getLocation(), Sound.ENTITY_GHAST_SHOOT, 1F, 1F);
    }
}

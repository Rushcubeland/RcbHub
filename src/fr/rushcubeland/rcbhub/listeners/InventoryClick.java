package fr.rushcubeland.rcbhub.listeners;

import fr.rushcubeland.rcbcore.bukkit.BukkitSend;
import fr.rushcubeland.rcbcore.bukkit.queue.QueueUnit;
import fr.rushcubeland.rcbhub.gui.MainGUI;
import fr.rushcubeland.rcbhub.gui.StatsGUI;
import fr.rushcubeland.rcbhub.locations.LocationUnit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClick implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event){
        event.setCancelled(true);
        if(event.getWhoClicked() instanceof Player){
            Player player = (Player) event.getWhoClicked();
            ItemStack current = event.getCurrentItem();
            if(current == null){
                return;
            }
            if(event.getInventory().equals(StatsGUI.getSpecifiedInv(player))){
                if(current.getType().equals(Material.ACACIA_DOOR)){
                    player.closeInventory();
                }
            }
            if(event.getInventory().equals(MainGUI.getSpecifiedInv(player))){
                if(current.getType().equals(Material.ACACIA_DOOR)){
                    player.closeInventory();
                }
                if(current.getType().equals(Material.WATER_BUCKET)){
                    BukkitSend.requestJoinQueue(player, QueueUnit.DE_A_COUDRE);
                    player.closeInventory();
                }
                if(current.getType().equals(Material.GOLDEN_BOOTS)){
                    player.closeInventory();
                    player.teleport(LocationUnit.PARCOURS.getLocation());
                }
            }
        }
    }
}

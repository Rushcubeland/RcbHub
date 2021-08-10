package fr.rushcubeland.rcbhub.listeners;

import fr.rushcubeland.rcbapi.bukkit.network.Network;
import fr.rushcubeland.rcbapi.bukkit.network.ServerUnit;
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
    public void onClick(InventoryClickEvent e){
        e.setCancelled(true);
        if(e.getWhoClicked() instanceof Player){
            Player player = (Player) e.getWhoClicked();
            ItemStack current = e.getCurrentItem();
            if(current == null){
                return;
            }
            if(e.getInventory() == StatsGUI.getSpecifiedInv(player)){
                if(current.getType().equals(Material.ACACIA_DOOR)){
                    player.closeInventory();
                }
            }
            if(e.getInventory() == MainGUI.getSpecifiedInv(player)){
                if(current.getType().equals(Material.ACACIA_DOOR)){
                    player.closeInventory();
                }
                if(current.getType().equals(Material.WATER_BUCKET)){
                    Network.sendPlayerToServer(player, ServerUnit.DeterrentBorder_1);
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

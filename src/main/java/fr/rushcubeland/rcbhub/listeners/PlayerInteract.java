package fr.rushcubeland.rcbhub.listeners;

import fr.rushcubeland.commons.Account;
import fr.rushcubeland.rcbcore.bukkit.BukkitSend;
import fr.rushcubeland.rcbcore.bukkit.RcbAPI;
import fr.rushcubeland.rcbcore.bukkit.tools.ItemBuilder;
import fr.rushcubeland.rcbhub.RcbHub;
import fr.rushcubeland.rcbhub.gui.MainGUI;
import fr.rushcubeland.rcbhub.gui.StatsGUI;
import fr.rushcubeland.rcbhub.locations.LocationUnit;
import fr.rushcubeland.rcbhub.parcours.Parcours;
import fr.rushcubeland.rcbhub.tasks.ParcoursTask;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;

public class PlayerInteract implements Listener {

    private final HashMap<Player, Boolean> dataTartareShadow = new HashMap<>();

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if(event.getItem() == null || event.getHand() == null){
            return;
        }
        ItemStack current = event.getItem();
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR){
            if(event.getHand().equals(EquipmentSlot.HAND)){
                if(current.getType().equals(Material.RED_BED)){
                    if(Parcours.getParcoursDataPlayers().containsKey(player)){
                        player.teleport(LocationUnit.LOBBY.getLocation());
                        player.sendMessage(ChatColor.RED + "Vous avez quitter le parcours !");
                        ParcoursTask.stopParcoursTask(player);
                        Parcours.getParcoursTimersPlayers().remove(player);
                        Parcours.getParcoursDataPlayers().remove(player);
                        Account account = RcbAPI.getInstance().getAccount(player);
                        PlayerJoin.initFlyPlayer(player, account.getRank());
                        PlayerJoin.giveJoinItems(player);
                    }
                }
                if(current.getType().equals(Material.SLIME_BALL)){
                    Parcours.rollback(player);
                    player.sendMessage(ChatColor.YELLOW + "Vous avez rejoin le dernier " + ChatColor.RED + "Checkpoint !");
                }
                if(current.getType().equals(Material.COMPASS)){
                    MainGUI.openInv(player);
                }
                if(current.getType().equals(Material.PLAYER_HEAD)){
                    StatsGUI.openInv(player);
                }
                if(current.getType().equals(Material.COMPARATOR)){
                    BukkitSend.cmdToProxy(player, "opt");
                }
                if(current.getType().equals(Material.PUFFERFISH)){
                    BukkitSend.cmdToProxy(player, "friend list");
                }
                if(current.getType().equals(Material.CLOCK)){
                    if(Bukkit.getOnlinePlayers().size() <= 1){
                        return;
                    }
                    if(dataTartareShadow.containsKey(player)){
                        if(dataTartareShadow.get(player).equals(true)){
                            dataTartareShadow.put(player, false);
                            ItemStack magicClock = new ItemBuilder(Material.CLOCK).setName(ChatColor.GRAY + "Ombre de Tartare: " + ChatColor.RED + "Désactivé").setLore(" ", "").toItemStack();
                            player.getInventory().setItem(4, magicClock);
                            for(Player pls : Bukkit.getOnlinePlayers()){
                                player.showPlayer(RcbHub.getInstance(), pls);
                            }
                        }
                        else {
                            activateTartare(player);
                        }
                    }
                    else
                    {
                        activateTartare(player);
                    }
                    player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 30, 1));
                    player.playSound(player.getLocation(), Sound.ENTITY_PHANTOM_AMBIENT, SoundCategory.AMBIENT, 1F, 1F);
                }
            }
        }
    }

    private void activateTartare(Player player){
        dataTartareShadow.put(player, true);
        ItemStack magicClock = new ItemBuilder(Material.CLOCK).setName(ChatColor.GRAY + "Ombre de Tartare: " + ChatColor.GREEN + "Activé").setLore(" ", "").toItemStack();
        player.getInventory().setItem(4, magicClock);
        for(Player pls : Bukkit.getOnlinePlayers()){
            player.hidePlayer(RcbHub.getInstance(), pls);
        }
    }
}

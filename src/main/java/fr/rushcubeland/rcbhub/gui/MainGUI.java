package fr.rushcubeland.rcbhub.gui;

import fr.rushcubeland.commons.Account;
import fr.rushcubeland.commons.rank.RankUnit;
import fr.rushcubeland.rcbcore.bukkit.RcbAPI;
import fr.rushcubeland.rcbcore.bukkit.tools.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.HashMap;
import java.util.Map;

/**
 * This class file is a part of RcbHub project claimed by Rushcubeland project.
 * You cannot redistribute, modify or use it for personnal or commercial purposes
 * please contact admin@rushcubeland.fr for any requests or information about that.
 *
 * @author LANNUZEL Dylan
 */

public class MainGUI {

    private MainGUI() {
        throw new IllegalStateException("This class should not be instantiated");
    }

    private static final Map<Player, Inventory> GUI = new HashMap<>();

    public static void openInv(Player player){

        Inventory inventory = Bukkit.createInventory(null, 54, ChatColor.GOLD + "Menu Principal");
        initGlass(inventory, Material.CYAN_STAINED_GLASS_PANE);

        ItemStack dbr = new ItemBuilder(Material.BEACON).setName(ChatColor.GOLD + "DeterrentBorder " + ChatColor.WHITE + "[" + ChatColor.RED + "RANKED" + ChatColor.WHITE + "]").removeFlags().toItemStack();
        dbr.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        ItemMeta dbrm = dbr.getItemMeta();
        dbr.setItemMeta(dbrm);
        inventory.setItem(23, dbr);

        ItemStack db = new ItemBuilder(Material.BEACON).setName(ChatColor.GOLD + "DeterrentBorder " + ChatColor.WHITE + "[CASUAL]").toItemStack();
        inventory.setItem(21, db);

        ItemStack dac = new ItemBuilder(Material.WATER_BUCKET).setName(ChatColor.AQUA + "Dé à coudre").toItemStack();
        inventory.setItem(31, dac);

        Account account = RcbAPI.getInstance().getAccount(player);

        final ItemStack headp;

        if(account.getRank().getPrefix().equals(RankUnit.JOUEUR.getPrefix())){
            headp = new ItemBuilder(Material.PLAYER_HEAD).setName(ChatColor.GOLD + "Informations:").setLore(" ", ChatColor.WHITE + "Grade: " + ChatColor.GRAY + "[Joueur]" , ChatColor.WHITE + "Coins: " + ChatColor.RED + account.getCoins() + " " + ChatColor.YELLOW + "⛁", ChatColor.WHITE + "Palier Pass de combat: " + ChatColor.GREEN + "14", "  ", ChatColor.GREEN + "Plus d'avantages ?", ChatColor.YELLOW + "https://store.rushcubeland.fr").toItemStack();
        }
        else
        {
            headp = new ItemBuilder(Material.PLAYER_HEAD).setName(ChatColor.GOLD + "Informations:").setLore(" ", ChatColor.WHITE + "Grade: " + account.getRank().getPrefix() , ChatColor.WHITE + "Coins: " + ChatColor.RED + account.getCoins() + " " + ChatColor.YELLOW + "⛁", ChatColor.WHITE + "Palier Pass de combat: " + ChatColor.GREEN + "14", "  ", ChatColor.GREEN + "Plus d'avantages ?", ChatColor.YELLOW + "https://store.rushcubeland.fr").toItemStack();
        }

        SkullMeta headpm = (SkullMeta) headp.getItemMeta();
        if (headpm != null) {
            headpm.setOwningPlayer(player);
        }
        headp.setItemMeta(headpm);
        inventory.setItem(27, headp);

        ItemStack jump = new ItemBuilder(Material.GOLDEN_BOOTS).setName(ChatColor.AQUA + "Parcours").setLore(" ", ChatColor.GRAY + "Saute de bloc en bloc pour terminer le parcours", " ", ChatColor.YELLOW + "➤ Se teleporter").removeFlags().toItemStack();
        inventory.setItem(35, jump);

        ItemStack info = new ItemBuilder(Material.COMPASS).setName(ChatColor.GOLD + "Hub").setLore(" ", ChatColor.GRAY + "Le hub est le lieu principal du serveur,", ChatColor.GRAY + "il vous permet de vous amuser avec vos amis,", ChatColor.GRAY + "d'explorer ses divers endroits ou de vous poser", ChatColor.GRAY + "à coté de l'eau", " ", ChatColor.WHITE + "➤ Il y a " + ChatColor.YELLOW + Bukkit.getOnlinePlayers().size() + " " + ChatColor.WHITE + "joueurs sur le hub").toItemStack();
        inventory.setItem(4, info);

        ItemStack close = new ItemBuilder(Material.ACACIA_DOOR).setName(ChatColor.RED + "Fermer").toItemStack();
        inventory.setItem(49, close);


        GUI.put(player, inventory);
        player.openInventory(inventory);

    }

    private static void initGlass(Inventory inventory, Material material){
        ItemStack glass = new ItemBuilder(material).removeFlags().toItemStack();
        inventory.setItem(0, glass);
        inventory.setItem(1, glass);
        inventory.setItem(7, glass);
        inventory.setItem(8, glass);
        inventory.setItem(9, glass);
        inventory.setItem(17, glass);
        inventory.setItem(36, glass);
        inventory.setItem(45, glass);
        inventory.setItem(46, glass);
        inventory.setItem(52, glass);
        inventory.setItem(44, glass);
        inventory.setItem(53, glass);
    }

    public static Inventory getSpecifiedInv(Player player){
        if(GUI.containsKey(player)){
            return GUI.get(player);
        }
        return null;
    }
}

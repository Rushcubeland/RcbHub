package fr.rushcubeland.rcbhub.gui;

import fr.rushcubeland.commons.AStats;
import fr.rushcubeland.commons.Account;
import fr.rushcubeland.rcbapi.RcbAPI;
import fr.rushcubeland.rcbapi.tools.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.HashMap;

public class MenuStats {

    private static final HashMap<Player, Inventory> GUI = new HashMap<>();

    public static void OpenInv(Player player){

        Inventory inventory = Bukkit.createInventory(null, 54, "§cStatistiques");
        initGlass(inventory, Material.GREEN_STAINED_GLASS_PANE);

        Account account = RcbAPI.getInstance().getAccount(player);

        final ItemStack headp;

        if(account.getRank().getPrefix().equals("")){
            headp = new ItemBuilder(Material.PLAYER_HEAD).setName("§6Informations:").setLore("§c ", "§fGrade: §7[Joueur]" , "§fCoins: §c" + account.getCoins() + " §e⛁", "§fPalier Pass de combat: §a14", "§f   ", "§aPlus d'avantages ?", "§ehttps://store.rushcubeland.fr").toItemStack();
        }
        else
        {
            headp = new ItemBuilder(Material.PLAYER_HEAD).setName("§6Informations:").setLore("§c ", "§fGrade: " + account.getRank().getPrefix(), "§fCoins: §c" + account.getCoins() + " §e⛁", "§fPalier Pass de combat: §a14", "§f   ", "§aPlus d'avantages ?", "§ehttps://store.rushcubeland.fr").toItemStack();
        }

        SkullMeta headpm = (SkullMeta) headp.getItemMeta();
        headpm.setOwningPlayer(player);
        headp.setItemMeta(headpm);
        inventory.setItem(4, headp);

        AStats aStats = RcbAPI.getInstance().getAccountStats(player);

        ItemStack parcourStats = new ItemBuilder(Material.OAK_SIGN).removeFlags().setName("§eParcours").setLore(" ", "§6Meilleur temps: §c" + aStats.getParcoursTimerFormat(), " ").toItemStack();
        inventory.setItem(25, parcourStats);

        ItemStack close = new ItemBuilder(Material.ACACIA_DOOR).setName("§cFermer").toItemStack();
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

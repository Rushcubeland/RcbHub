package fr.rushcubeland.rcbhub.gui;

import fr.rushcubeland.commons.AStatsDAC;
import fr.rushcubeland.commons.Account;
import fr.rushcubeland.commons.rank.RankUnit;
import fr.rushcubeland.rcbapi.bukkit.RcbAPI;
import fr.rushcubeland.rcbapi.bukkit.tools.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.HashMap;

public class StatsDACGUI {

    private static final HashMap<Player, Inventory> GUI = new HashMap<>();

    public static void OpenInv(Player player){

        Inventory inventory = Bukkit.createInventory(null, 54, "§cStatistiques");
        initGlass(inventory, Material.GREEN_STAINED_GLASS_PANE);

        Account account = RcbAPI.getInstance().getAccount(player);
        AStatsDAC aStatsDAC = RcbAPI.getInstance().getAccountStatsDAC(player);

        final ItemStack headp;

        if(account.getRank().getPrefix().equals(RankUnit.JOUEUR.getPrefix())){
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

        int wins = aStatsDAC.getWins();
        int loses = aStatsDAC.getLoses();
        int success = aStatsDAC.getNbSuccessJumps();
        int fails = aStatsDAC.getNbFailJumps();
        double ratio_wl = (double) wins/loses;
        double ratio_sf = (double) success/fails;

        ItemStack wins_item = new ItemBuilder(Material.OAK_SIGN).removeFlags().setName("§6Victoires: §c" + aStatsDAC.getWins()).toItemStack();
        inventory.setItem(19, wins_item);

        ItemStack loses_item = new ItemBuilder(Material.OAK_SIGN).removeFlags().setName("§6Défaites: §c" + aStatsDAC.getLoses()).toItemStack();
        inventory.setItem(21, loses_item);

        ItemStack nbParties = new ItemBuilder(Material.OAK_SIGN).removeFlags().setName("§6Parties jouées: §c" + aStatsDAC.getNbParties()).toItemStack();
        inventory.setItem(23, nbParties);

        ItemStack wlkd = new ItemBuilder(Material.OAK_SIGN).removeFlags().setName("§6Ratio Victoires/Défaites: §c" + ratio_wl).toItemStack();
        inventory.setItem(25, wlkd);

        ItemStack sortsUsed = new ItemBuilder(Material.OAK_SIGN).removeFlags().setName("§6Sorts utilisés: §c" + aStatsDAC.getNbSortsUsed()).toItemStack();
        inventory.setItem(30, sortsUsed);

        ItemStack jumps_ratio = new ItemBuilder(Material.OAK_SIGN).removeFlags().setName("§6Ratio sauts Réussis/Ratés: §c" + ratio_sf).toItemStack();
        inventory.setItem(32, jumps_ratio);

        ItemStack close = new ItemBuilder(Material.ACACIA_DOOR).setName("§cRetour").toItemStack();
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

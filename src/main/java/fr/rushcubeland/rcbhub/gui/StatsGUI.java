package fr.rushcubeland.rcbhub.gui;

import fr.rushcubeland.commons.AStats;
import fr.rushcubeland.commons.AStatsDAC;
import fr.rushcubeland.commons.Account;
import fr.rushcubeland.commons.rank.RankUnit;
import fr.rushcubeland.rcbcore.bukkit.RcbAPI;
import fr.rushcubeland.rcbcore.bukkit.tools.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class StatsGUI {

    private static final Map<Player, Inventory> GUI = new HashMap<>();

    private StatsGUI() {
        throw new IllegalStateException("This class should not be instantiated");
    }

    public static void openInv(Player player){

        Inventory inventory = Bukkit.createInventory(null, 54, ChatColor.RED + "Statistiques");
        initGlass(inventory, Material.GREEN_STAINED_GLASS_PANE);
        RcbAPI.getInstance().getAccount(player, result -> {
            final ItemStack headp;
            Account account = (Account) result;
            if(account.getRank().equals(RankUnit.JOUEUR)){
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
            inventory.setItem(4, headp);
        });


        RcbAPI.getInstance().getAccountStatsDAC(player, result -> {
            AStatsDAC aStatsDAC = (AStatsDAC) result;
            int parties = aStatsDAC.getNbParties();
            int wins = aStatsDAC.getWins();
            int loses = aStatsDAC.getLoses();
            int jumps = aStatsDAC.getNbJumps();
            int success = aStatsDAC.getNbSuccessJumps();
            int fails = aStatsDAC.getNbFailJumps();
            int sorts = aStatsDAC.getNbSortsUsed();
            double freqW = (double) wins/parties;
            double freqS = (double) success/jumps;
            DecimalFormat df = new DecimalFormat("##.##%");

            ItemStack dac = new ItemBuilder(Material.WATER_BUCKET).setName(ChatColor.AQUA + "Dé à Coudre").
                    setLore("", ChatColor.GOLD + "Parties: " + ChatColor.RED + parties,
                            ChatColor.GOLD + "Victoires: " + ChatColor.RED + wins,
                            ChatColor.GOLD + "Défaites: " + ChatColor.RED + loses, "",
                            ChatColor.GOLD + "Sauts: " + ChatColor.RED + jumps,
                            ChatColor.GOLD + "Sauts réussis: " + ChatColor.RED + success,
                            ChatColor.GOLD + "Sauts ratés: " + ChatColor.RED + fails, "",
                            ChatColor.GOLD + "Sorts utilisés: " + ChatColor.RED + sorts, "",
                            ChatColor.GOLD + "Pourcentage victoires: " + ChatColor.RED + df.format(freqW),
                            ChatColor.GOLD + "Pourcentage sauts réussis: " + ChatColor.RED + df.format(freqS))
                    .removeFlags().toItemStack();
            inventory.setItem(20, dac);
        });

        RcbAPI.getInstance().getAccountStats(player, result -> {
            AStats aStats = (AStats) result;
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
            dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));

            ItemStack parcourStats = new ItemBuilder(Material.OAK_SIGN).removeFlags().setName(ChatColor.YELLOW + "Parcours").setLore(
                    " ", ChatColor.GOLD + "Meilleur temps: " + ChatColor.RED + aStats.getParcoursTimerFormat(), ChatColor.GOLD + "Première connexion: " + ChatColor.GRAY + dateFormat.format(aStats.getFirstConnection()), " ").toItemStack();
            inventory.setItem(25, parcourStats);
        });

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

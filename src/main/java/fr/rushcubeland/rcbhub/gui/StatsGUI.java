package fr.rushcubeland.rcbhub.gui;

import fr.rushcubeland.commons.AStats;
import fr.rushcubeland.commons.AStatsDAC;
import fr.rushcubeland.commons.Account;
import fr.rushcubeland.commons.data.callbacks.AsyncCallBack;
import fr.rushcubeland.commons.rank.RankUnit;
import fr.rushcubeland.rcbcore.bukkit.RcbAPI;
import fr.rushcubeland.rcbcore.bukkit.tools.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.TimeZone;

public class StatsGUI {

    private static final HashMap<Player, Inventory> GUI = new HashMap<>();

    public static void OpenInv(Player player){

        Inventory inventory = Bukkit.createInventory(null, 54, "§cStatistiques");
        initGlass(inventory, Material.GREEN_STAINED_GLASS_PANE);
        RcbAPI.getInstance().getAccount(player, result -> {
            final ItemStack headp;
            Account account = (Account) result;
            if(account.getRank().getPrefix().equals(RankUnit.JOUEUR.getPrefix())){
                headp = new ItemBuilder(Material.PLAYER_HEAD).setName("§6Informations:").setLore("§c ", "§fGrade: §7[Joueur]" , "§fCoins: §c" + account.getCoins() + " §e⛁", "§fPalier Pass de combat: §a14", "§f   ", "§aPlus d'avantages ?", "§ehttps://store.rushcubeland.fr").toItemStack();
            }
            else
            {
                headp = new ItemBuilder(Material.PLAYER_HEAD).setName("§6Informations:").setLore("§c ", "§fGrade: " + account.getRank().getPrefix(), "§fCoins: §c" + account.getCoins() + " §e⛁", "§fPalier Pass de combat: §a14", "§f   ", "§aPlus d'avantages ?", "§ehttps://store.rushcubeland.fr").toItemStack();
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
            double freq_w = (double) wins/parties;
            double freq_s = (double) success/jumps;
            DecimalFormat df = new DecimalFormat("##.##%");

            ItemStack dac = new ItemBuilder(Material.WATER_BUCKET).setName("§bDé à Coudre").
                    setLore("", "§6Parties: §c" + parties,
                            "§6Victoires: §c" + wins,
                            "§6Défaites: §c" + loses, "",
                            "§6Sauts: §c" + jumps,
                            "§6Sauts réussis: §c" + success,
                            "§6Sauts ratés: §c" + fails, "",
                            "§6Sorts utilisés: §c" + sorts, "",
                            "§6Pourcentage victoires: §c" + df.format(freq_w),
                            "§6Pourcentage sauts réussis: §c" + df.format(freq_s))
                    .removeFlags().toItemStack();
            inventory.setItem(20, dac);
        });

        RcbAPI.getInstance().getAccountStats(player, result -> {
            AStats aStats = (AStats) result;
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
            dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));

            ItemStack parcourStats = new ItemBuilder(Material.OAK_SIGN).removeFlags().setName("§eParcours").setLore(
                    " ", "§6Meilleur temps: §c" + aStats.getParcoursTimerFormat(), "§6Première connexion: §7" + dateFormat.format(aStats.getFirstConnection()), " ").toItemStack();
            inventory.setItem(25, parcourStats);
        });

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
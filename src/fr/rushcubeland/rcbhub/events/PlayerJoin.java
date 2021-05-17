package fr.rushcubeland.rcbhub.events;

import fr.rushcubeland.commons.Account;
import fr.rushcubeland.rcbapi.RcbAPI;
import fr.rushcubeland.rcbapi.rank.RankUnit;
import fr.rushcubeland.rcbapi.tools.ItemBuilder;
import fr.rushcubeland.rcbapi.tools.scoreboard.ScoreboardSign;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        player.getInventory().clear();
        player.setGameMode(GameMode.ADVENTURE);
        player.setHealth(20D);
        player.setFoodLevel(20);
        player.sendTitle("§6Rushcubeland", "§cUnivers compétitif et stratégique !", 10, 70, 20);
        RcbAPI.getInstance().getTablist().sendTabList(player);
        initScoreboardPlayer(player);
        giveJoinItems(player);
        Account account = RcbAPI.getInstance().getAccount(player);
        RankUnit rank = account.getRank();
        if(rank.getPower() <= 45){
            e.setJoinMessage(rank.getPrefix() + player.getDisplayName() + " §ba rejoin le Lobby !");
        }
        else {
            e.setJoinMessage(null);

        }
        initFlyPlayer(player, rank);
        initRankPlayerPermissions(player, rank);
        RcbAPI.getInstance().getTablist().setTabListPlayer(player);
    }

    private void initFlyPlayer(Player player, RankUnit rank){
        if(rank.getPower() <= 40){
            player.setAllowFlight(true);
            player.setFlying(true);
        }
    }

    private void initScoreboardPlayer(Player player){
        ScoreboardSign scoreboard = new ScoreboardSign(player, "§6§l§nRushcubeland");
        scoreboard.create();
        scoreboard.setLine(0, "§f ");
        scoreboard.setLine(1, "§fCompte: ");
        scoreboard.setLine(2, "§c ");
        scoreboard.setLine(3, "§fCoins: §c");
        scoreboard.setLine(4, "§7 ");
        scoreboard.setLine(5, "§fPass de combat: §5Palier 14");
        scoreboard.setLine(6, "§b ");
        scoreboard.setLine(7, "§fJoueurs en ligne: ");
        scoreboard.setLine(8, "§4 ");
        scoreboard.setLine(9, "§eplay.rushcubeland.fr");
        RcbAPI.getInstance().boards.put(player, scoreboard);

    }

    private void giveJoinItems(Player player){
        ItemStack menup = new ItemBuilder(Material.COMPASS).setName("§6Menu Principal").setLore("§f ", "").toItemStack();
        menup.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        ItemMeta menupm = menup.getItemMeta();
        menupm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        menup.setItemMeta(menupm);

        ItemStack settings = new ItemBuilder(Material.COMPARATOR).setName("§cPréférences").setLore("§f ", "").toItemStack();

        ItemStack magicClock = new ItemBuilder(Material.CLOCK).setName("§7Ombre de Tartare: §cDésactivé").setLore("§f ", "").toItemStack();

        ItemStack profil = new ItemBuilder(Material.PLAYER_HEAD).setName("§eVotre profil").setLore("§f ", "").toItemStack();
        SkullMeta profilm = (SkullMeta) profil.getItemMeta();
        profilm.setOwningPlayer(player);
        profil.setItemMeta(profilm);

        player.getInventory().setItem(0, menup);
        player.getInventory().setItem(4, magicClock);
        player.getInventory().setItem(8, settings);
        player.getInventory().setItem(1, profil);
    }

    private void initRankPlayerPermissions(Player player, RankUnit rank){
        if(rank.getPermissions().isEmpty()){
            return;
        }
        for(String perm : rank.getPermissions()){
            player.addAttachment(RcbAPI.getInstance()).setPermission(perm, true);
        }
    }

}

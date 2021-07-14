package fr.rushcubeland.rcbhub.tasks;

import fr.rushcubeland.commons.Account;
import fr.rushcubeland.rcbapi.RcbAPI;
import fr.rushcubeland.rcbapi.data.redis.RedisAccess;
import fr.rushcubeland.rcbapi.network.Network;
import fr.rushcubeland.rcbapi.tools.scoreboard.ScoreboardSign;
import fr.rushcubeland.rcbhub.RcbHub;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;

import java.util.Map;

public class ScoreboardReloadTask extends BukkitRunnable {

    @Override
    public void run() {
        for(Map.Entry<Player, ScoreboardSign> sign : RcbAPI.getInstance().boards.entrySet()){
            Player player = sign.getKey();
            if(player == null){
                return;
            }
            Bukkit.getScheduler().runTaskAsynchronously(RcbHub.getInstance(), () -> {

                final RedissonClient redissonClient = RedisAccess.INSTANCE.getRedissonClient();
                final String key = "account:" + player.getUniqueId().toString();
                final RBucket<Account> accountRBucket = redissonClient.getBucket(key);

                final Account account = accountRBucket.get();
                long coins = account.getCoins();

                sign.getValue().setLine(0, "§f ");
                sign.getValue().setLine(1, "§fCompte: " + account.getRank().getPrefix() + player.getDisplayName());
                sign.getValue().setLine(2, "§c ");
                sign.getValue().setLine(3, "§fCoins: " + ChatColor.RED + coins + " " + ChatColor.YELLOW + "⛁");
                sign.getValue().setLine(4, "§7 ");
                sign.getValue().setLine(5, "§fPass de combat: §5Palier 14");
                sign.getValue().setLine(6, "§b ");
                sign.getValue().setLine(7, "§fJoueurs en ligne: §7" + Network.getNetworkSlots());
                sign.getValue().setLine(8, "§4 ");
                sign.getValue().setLine(9,  ChatColor.YELLOW + "play.rushcubeland.fr");

            });
        }
    }
}

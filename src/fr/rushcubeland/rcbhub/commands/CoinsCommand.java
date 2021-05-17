package fr.rushcubeland.rcbhub.commands;

import fr.rushcubeland.commons.Account;
import fr.rushcubeland.rcbapi.RcbAPI;
import fr.rushcubeland.rcbhub.RcbHub;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CoinsCommand implements CommandExecutor {

    private RcbHub rcbHub;

    public CoinsCommand(RcbHub rcbHub) {
        this.rcbHub = rcbHub;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(label.equalsIgnoreCase("coins")){
            if(sender instanceof Player){
                Player player = (Player) sender;
                Account account = RcbAPI.getInstance().getAccount(player);
                player.sendMessage("§6Votre solde de §eCoins §6est de: §c" + account.getCoins() + " §e⛁");
            }
        }
        return false;
    }
}

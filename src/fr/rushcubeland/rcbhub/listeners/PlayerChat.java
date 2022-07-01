package fr.rushcubeland.rcbhub.listeners;

import fr.rushcubeland.commons.AOptions;
import fr.rushcubeland.commons.Account;
import fr.rushcubeland.commons.options.OptionUnit;
import fr.rushcubeland.rcbcore.bukkit.RcbAPI;
import fr.rushcubeland.rcbcore.bukkit.commands.ReportMsgCommand;
import fr.rushcubeland.rcbcore.bukkit.sanction.MuteData;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;

/**
 * This class file is a part of RcbHub project claimed by Rushcubeland project.
 * You cannot redistribute, modify or use it for personnal or commercial purposes
 * please contact admin@rushcubeland.fr for any requests or information about that.
 *
 * @author LANNUZEL Dylan
 */

public class PlayerChat implements Listener {

    @EventHandler(priority = EventPriority.LOW)
    public void onChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        String message = event.getMessage();
        if(MuteData.isInMuteData(player.getUniqueId().toString()) && !message.startsWith("/")){
            event.setCancelled(true);
            player.sendMessage("§cVous avez été mute !");
            return;
        }
        if(ReportMsgCommand.msgs.containsKey(player.getName())){
            ReportMsgCommand.msgs.get(player.getName()).add(message);
        }
        else
        {
            ArrayList<String> msgs = new ArrayList<>();
            msgs.add(event.getMessage());
            ReportMsgCommand.msgs.put(player.getName(), msgs);
        }
        Account account = RcbAPI.getInstance().getAccount(player);
        TextComponent sign = new TextComponent("⚠");
        TextComponent format = new TextComponent(account.getRank().getPrefix() + player.getDisplayName() + ": " + message);
        sign.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Signaler le message").color(ChatColor.DARK_RED).create()));
        sign.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/reportmsg " + player.getName() + " " + message));
        format.setClickEvent(null);
        format.setHoverEvent(null);
        for(Player pls : Bukkit.getOnlinePlayers()){
            AOptions aOptions = RcbAPI.getInstance().getAccountOptions(pls);
            if(aOptions.getStateChat().equals(OptionUnit.OPEN)){
                pls.spigot().sendMessage(new ComponentBuilder(sign).color(ChatColor.DARK_RED).append(format).reset().create());
            }
        }
        event.setCancelled(true);
    }
}

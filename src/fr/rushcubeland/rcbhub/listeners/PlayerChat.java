package fr.rushcubeland.rcbhub.listeners;

import fr.rushcubeland.commons.AOptions;
import fr.rushcubeland.commons.Account;
import fr.rushcubeland.commons.options.OptionUnit;
import fr.rushcubeland.rcbapi.bukkit.RcbAPI;
import fr.rushcubeland.rcbapi.bukkit.commands.ReportMsgCommand;
import fr.rushcubeland.rcbapi.bukkit.sanction.MuteData;
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

public class PlayerChat implements Listener {

    @EventHandler(priority = EventPriority.LOW)
    public void onChat(AsyncPlayerChatEvent e){
        Player player = e.getPlayer();
        if(MuteData.isInMuteData(e.getPlayer().getUniqueId().toString())){
            if(!e.getMessage().startsWith("/")){
                e.setCancelled(true);
                return;
            }
        }
        if(ReportMsgCommand.msgs.containsKey(player.getName())){
            ReportMsgCommand.msgs.get(player.getName()).add(e.getMessage());
        }
        else
        {
            ArrayList<String> msgs = new ArrayList<>();
            msgs.add(e.getMessage());
            ReportMsgCommand.msgs.put(player.getName(), msgs);
        }
        Account account = RcbAPI.getInstance().getAccount(player);
        TextComponent icon = new TextComponent("§4⚠");
        icon.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("§4Signaler")));
        icon.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/reportmsg " + player.getName() + " " + e.getMessage()));
        TextComponent format = new TextComponent(account.getRank().getPrefix() + player.getDisplayName() + ": " + e.getMessage());
        format.setClickEvent(null);
        format.setHoverEvent(null);
        for(Player pls : Bukkit.getOnlinePlayers()){
            AOptions aOptions = RcbAPI.getInstance().getAccountOptions(pls);
            if(aOptions.getStateChat().equals(OptionUnit.OPEN)){
                pls.spigot().sendMessage(new ComponentBuilder(icon).append(format).create());
            }
        }
        e.setCancelled(true);
    }
}

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
    public void onChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        if(MuteData.isInMuteData(event.getPlayer().getUniqueId().toString())){
            if(!event.getMessage().startsWith("/")){
                event.setCancelled(true);
                return;
            }
        }
        if(ReportMsgCommand.msgs.containsKey(player.getName())){
            ReportMsgCommand.msgs.get(player.getName()).add(event.getMessage());
        }
        else
        {
            ArrayList<String> msgs = new ArrayList<>();
            msgs.add(event.getMessage());
            ReportMsgCommand.msgs.put(player.getName(), msgs);
        }
        Account account = RcbAPI.getInstance().getAccount(player);
        TextComponent msg = new TextComponent(event.getMessage());
        msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("§4Signaler")));
        msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/reportmsg " + player.getName() + " " + msg));
        for(Player pls : Bukkit.getOnlinePlayers()){
            AOptions aOptions = RcbAPI.getInstance().getAccountOptions(pls);
            if(aOptions.getStateChat().equals(OptionUnit.OPEN)){
                pls.spigot().sendMessage(new ComponentBuilder(account.getRank().getPrefix() + player.getDisplayName() + ": ").append(msg).create());
            }
        }
        event.setCancelled(true);
    }
}

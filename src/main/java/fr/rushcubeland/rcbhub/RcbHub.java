package fr.rushcubeland.rcbhub;

import fr.rushcubeland.rcbcore.bukkit.tools.NPC;
import fr.rushcubeland.rcbhub.listeners.*;
import fr.rushcubeland.rcbhub.npc.NPCUnit;
import fr.rushcubeland.rcbhub.parcours.CheckPointUnit;
import fr.rushcubeland.rcbhub.tasks.ScoreboardReloadTask;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


/**
 * This class file is a part of RcbHub project claimed by Rushcubeland project.
 * You cannot redistribute, modify or use it for personnal or commercial purposes
 * please contact admin@rushcubeland.fr for any requests or information about that.
 *
 * @author LANNUZEL Dylan
 */

public class RcbHub extends JavaPlugin {

    private static RcbHub instance;

    @Override
    public void onEnable() {
        instance = this;
        registerListeners();
        registerCommands();

        String channel = "rcbapi:main";
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, channel);

        NPCUnit.createAll();
        CheckPointUnit.placeHolograms();

        reloadScoreboardTask();

        RcbHub.getInstance().getLogger().info("Plugin enabled");
    }

    @Override
    public void onDisable() {
        NPC.deleteAll();
        RcbHub.getInstance().getLogger().info("Plugin disabled");
    }

    private void registerListeners(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerJoin(), this);
        pm.registerEvents(new PlayerQuit(), this);
        pm.registerEvents(new PlayerChat(), this);
        pm.registerEvents(new NPCRightClick(), this);
        pm.registerEvents(new PlayerInteract(), this);
        pm.registerEvents(new InventoryClick(), this);
        pm.registerEvents(new PlaceBlock(), this);
        pm.registerEvents(new BreakBlock(), this);
        pm.registerEvents(new DropItem(), this);
        pm.registerEvents(new CreatureSpawn(), this);
        pm.registerEvents(new PickupItem(), this);
        pm.registerEvents(new ChunksLoad(), this);
        pm.registerEvents(new FoodChange(), this);
        pm.registerEvents(new OnDamage(), this);
        pm.registerEvents(new PlayerMove(), this);
        pm.registerEvents(new Flight(), this);

    }

    private void registerCommands(){
        throw new UnsupportedOperationException("Not supported yet");
    }

    private void reloadScoreboardTask(){
        ScoreboardReloadTask reloadTask = new ScoreboardReloadTask();
        reloadTask.runTaskTimer(this, 0L, 80L);
    }

    public static RcbHub getInstance() {
        return instance;
    }

}

package fr.rushcubeland.rcbhub;

import fr.rushcubeland.rcbhub.listeners.*;
import fr.rushcubeland.rcbhub.npc.NPCUnit;
import fr.rushcubeland.rcbhub.parcours.CheckPointUnit;
import fr.rushcubeland.rcbhub.tasks.ScoreboardReloadTask;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

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
        RcbHub.getInstance().getLogger().info("Plugin disabled");
        instance = null;
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

    }

    private void registerCommands(){
    }

    private void reloadScoreboardTask(){
        ScoreboardReloadTask reloadTask = new ScoreboardReloadTask();
        reloadTask.runTaskTimer(this, 0L, 40L);
    }

    public static RcbHub getInstance() {
        return instance;
    }

}

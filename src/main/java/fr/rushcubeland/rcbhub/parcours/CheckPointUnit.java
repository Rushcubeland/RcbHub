package fr.rushcubeland.rcbhub.parcours;

import fr.rushcubeland.rcbcore.bukkit.map.MapUnit;
import fr.rushcubeland.rcbcore.bukkit.tools.Hologram;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * This file is a part of RcbHub project claimed by Rushcubeland project.
 * You cannot redistribute, modify or use it for personnal or commercial purposes
 * please contact admin@rushcubeland.fr for any requests or information about that.
 *
 * @author LANNUZEL Dylan
 */

public enum CheckPointUnit {

    START("le " + ChatColor.GOLD + "Départ", -89, 253, -19, new String[]{ChatColor.YELLOW + "Parcours", ChatColor.GOLD + "Départ"}, 18F),
    FIRST_CHECKPOINT("le " + ChatColor.GOLD + "1er Checkpoint", -51, 241, -54, new String[]{ChatColor.GOLD + "1er Checkpoint"}, 18F),
    SND_CHECKPOINT("le " + ChatColor.GOLD + "Snd Checkpoint", -16, 231, -87, new String[]{ChatColor.GOLD + "Snd Checkpoint"}, 18F),
    THIRD_CHECKPOINT("le " + ChatColor.GOLD + "3ème Checkpoint", 39, 194, -118, new String[]{ChatColor.GOLD + "3ème Checkpoint"}, 18F),
    END(ChatColor.GOLD + "" + "l'Arrivée", 53, 214, -110, new String[]{ChatColor.YELLOW + "Parcours", ChatColor.GOLD + "Arrivée"}, 18F);

    private final String name;
    private final int x;
    private final int y;
    private final int z;
    private final String[] holograms;

    private final float fallLimit;
    private Hologram hologram;

    CheckPointUnit(String name, int x, int y, int z, String[] holograms, float fallLimit) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.z = z;
        this.holograms = holograms;
        this.fallLimit = fallLimit;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public String[] getHolograms() {
        return holograms;
    }

    public float getFallLimit() {
        return fallLimit;
    }

    public Hologram getHologram() {
        return hologram;
    }

    public static boolean locationMatch(CheckPointUnit checkPointUnit, Location location){
        return checkPointUnit.getX() == location.getBlock().getX() && checkPointUnit.getY() == location.getBlock().getY()
                && checkPointUnit.getZ() == location.getBlock().getZ();
    }

    public static void placeHolograms(){
        for(CheckPointUnit checkPoint : CheckPointUnit.values()){
            Hologram hologram = new Hologram(checkPoint.getHolograms());
            Location location = new Location(Bukkit.getWorld(MapUnit.LOBBY.getName()), checkPoint.getX(), checkPoint.getY(), checkPoint.getZ());
            hologram.generateLines(location.add(0.0D, 2D, 0.0D));
            checkPoint.hologram = hologram;
        }
    }

    public static void showHolograms(Player player){
        for(CheckPointUnit checkPoint : CheckPointUnit.values()){
            checkPoint.hologram.addReceiver(player);
        }
    }

    public static void hideHolograms(Player player){
        for(CheckPointUnit checkPoint : CheckPointUnit.values()){
            checkPoint.hologram.removeReceiver(player);
        }

    }
}

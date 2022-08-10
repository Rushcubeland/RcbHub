package fr.rushcubeland.rcbhub.parcours;

import fr.rushcubeland.rcbcore.bukkit.map.MapUnit;
import fr.rushcubeland.rcbcore.bukkit.tools.Hologram;
import org.bukkit.Bukkit;
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

    START("le §6Départ", -89, 253, -19, new String[]{"§eParcours", "§6Départ"}),
    FIRST_CHECKPOINT("le §61er Checkpoint", -51, 241, -54, new String[]{"§61er Checkpoint"}),
    SND_CHECKPOINT("le §6Snd Checkpoint", -16, 231, -87, new String[]{"§6Snd Checkpoint"}),
    THIRD_CHECKPOINT("le §63ème Checkpoint", 39, 194, -118, new String[]{"§63ème Checkpoint"}),
    END("§6l'Arrivée", 53, 214, -110, new String[]{"§eParcours", "§6Arrivée"});

    private final String name;
    private final int x;
    private final int y;
    private final int z;
    private final String[] holograms;
    private Hologram hologram;

    CheckPointUnit(String name, int x, int y, int z, String[] holograms) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.z = z;
        this.holograms = holograms;
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

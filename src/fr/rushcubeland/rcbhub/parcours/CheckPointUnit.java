package fr.rushcubeland.rcbhub.parcours;

import org.bukkit.Location;

public enum CheckPointUnit {


    START("le §6Départ", -600, 84, 548),
    FIRST_CHECKPOINT("le §61er Checkpoint", -599, 83, 547),
    SND_CHECKPOINT("le §6Snd Checkpoint", -598, 81, 546),
    END("§6l'Arrivée", -597, 79, 545);

    private final String name;
    private final int x;
    private final int y;
    private final int z;

    CheckPointUnit(String name, int x, int y, int z) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.z = z;
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

    public static boolean locationMatch(CheckPointUnit checkPointUnit, Location location){
        return checkPointUnit.getX() == location.getBlock().getX() && checkPointUnit.getY() == location.getBlock().getY()
                && checkPointUnit.getZ() == location.getBlock().getZ();
    }
}

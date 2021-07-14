package fr.rushcubeland.rcbhub.parcours;

import org.bukkit.Location;

public enum CheckPointUnit {


    START("le §6Départ", -89, 253, -19),
    FIRST_CHECKPOINT("le §61er Checkpoint", -51, 241, -54),
    SND_CHECKPOINT("le §6Snd Checkpoint", -16, 231, -87),
    THIRD_CHECKPOINT("le §63ème Checkpoint", 39, 194, -118),
    END("§6l'Arrivée", 53, 214, -110);

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

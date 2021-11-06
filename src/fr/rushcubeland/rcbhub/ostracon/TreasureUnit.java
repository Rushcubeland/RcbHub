package fr.rushcubeland.rcbhub.ostracon;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

public enum TreasureUnit {

    TREASURE("", Material.DARK_OAK_DOOR, null, new Location(Bukkit.getWorld("world"), 0, 0, 0));

    private final String name;
    private final Material material;
    private final Object reward;
    private final Location location;

    TreasureUnit(String name, Material material, Object reward, Location location) {
        this.name = name;
        this.material = material;
        this.reward = reward;
        this.location = location;
    }
}

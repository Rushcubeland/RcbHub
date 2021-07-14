package fr.rushcubeland.rcbhub.locations;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public enum LocationUnit {

    LOBBY(new Location(Bukkit.getWorld("world"), -389, 69, 304)),
    PARCOURS(new Location(Bukkit.getWorld("world"), -602, 85, 550)),
    VIP_AREA(new Location(Bukkit.getWorld("world"), 50, 50, 50));

    private final Location location;

    LocationUnit(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }
}

package fr.rushcubeland.rcbhub.ostracon;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum OstraconUnit {

    FIRST_OSTRACON("", Material.DARK_OAK_DOOR, new ItemStack(Material.WRITTEN_BOOK), new Location(Bukkit.getWorld("world"), 0, 0, 0), null, TreasureUnit.TREASURE),
    SND_OSTRACON("", Material.DARK_OAK_DOOR, new ItemStack(Material.WRITTEN_BOOK), new Location(Bukkit.getWorld("world"), 0, 0, 0), FIRST_OSTRACON, TreasureUnit.TREASURE),
    THIRD_OSTRACON("", Material.DARK_OAK_DOOR, new ItemStack(Material.WRITTEN_BOOK), new Location(Bukkit.getWorld("world"), 0, 0, 0), SND_OSTRACON, TreasureUnit.TREASURE);

    private final String name;
    private final Material material;
    private ItemStack book;
    private final Location location;
    private final OstraconUnit ostraconRequired;
    private final TreasureUnit treasure;

    OstraconUnit(String name, Material material, ItemStack book, Location location, OstraconUnit ostraconRequired, TreasureUnit treasure) {
        this.name = name;
        this.material = material;
        this.book = book;
        this.location = location;
        this.ostraconRequired = ostraconRequired;
        this.treasure = treasure;
    }

    public String getName() {
        return name;
    }

    public Material getMaterial() {
        return material;
    }

    public ItemStack getBook() {
        return book;
    }

    public Location getLocation() {
        return location;
    }

    public OstraconUnit getOstraconRequired() {
        return ostraconRequired;
    }

    public TreasureUnit getTreasure() {
        return treasure;
    }

    public void setBook(ItemStack book) {
        this.book = book;
    }
}

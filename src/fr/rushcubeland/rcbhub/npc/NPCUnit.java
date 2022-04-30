package fr.rushcubeland.rcbhub.npc;

import fr.rushcubeland.rcbcore.bukkit.map.MapUnit;
import fr.rushcubeland.rcbcore.bukkit.tools.NPC;
import net.minecraft.server.v1_15_R1.EnumItemSlot;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_15_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

/**
 * This file is a part of RcbHub project claimed by Rushcubeland project.
 * You cannot redistribute, modify or use it for personnal or commercial purposes
 * please contact admin@rushcubeland.fr for any requests or information about that.
 *
 * @author LANNUZEL Dylan
 */

public enum NPCUnit {

    DE_A_COUDRE(ChatColor.AQUA + "Dé à Coudre", Bukkit.getWorld(MapUnit.LOBBY.getPath()), new Location(null, 65.5, 187, 11.5, 137, 0),
            "ewogICJ0aW1lc3RhbXAiIDogMTYzNzM1NzI0ODQ1NCwKICAicHJvZmlsZUlkIiA6ICIzNmMxODk4ZjlhZGE0NjZlYjk0ZDFmZWFmMjQ0MTkxMyIsCiAgInByb2ZpbGVOYW1lIiA6ICJMdW5haWFuIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzYwNGYxZTQyYWRkZDgzNmVkMDdiNDYwZGNmMTQ5OGQ2NGJhMmIyYzhmMjhlNjAwZTEyZTUxMGY4YmMzNDY2YzUiCiAgICB9CiAgfQp9",
            "UceTQ/KT2/gjwBydaMXY4wS83b786+7zKGPSBZv2RUXc0AV+IzEZdzuomg4DXqYimXvnybij0wokgCvFB4eIfX0nbVplPwlyU2cfOEY+iX7BFg2/vaFhUtlIAy6DHPcml+Oov1vdDO498TnZ5kEUXZ+itaKhLSWGl3W78mdPPV7WVDJQLPfSmnGfUPXIn39ybexiOJKFjludM0+RwuaXVchOMWwp1z607S5sghZuV5uPzvAGwzpggIjQF31RioZC8xYJ8Ad3KaOuIpME2gX1Bonc6HxZIjp0MNVfN57XEIKGbdqo8Av8CBscSzdSV/TMfQi8HW+8hTgj/Xe9YH8KqJ4c30didFjBTNXZsgoKfHtDUcFpqlKd27+yPYpAPUYSaat/6zT+uUVdzihTbeqevaBT7L+JPfr20AQ+3BuESH/bmAvPRwWR1RDUfmbWUOb1SYxF8SVoZaKMRzty+ZQwIEz1iMz09/zCY4DhyzlMJw7Z9ZGytiZt3Zn0bSSBIR3hyJZirg/EIYpk9QJ6mICPFgyaq8Fbm+LUXAQ2P63bB4bi3TKYyZGB729+M/wD1emPQfzTZzLxGtWeGGiCYJfKS9hosO0JWnwhJtcoxs5pEdt4Wx9jLdxvC270kFGIlI9seQ6g0v69Wtag/5tcMQvtjvWsCDd/rQkv/E4Q3StHjvY=");

    private final String name;
    private final World world;
    private final Location location;
    private final String texture;
    private final String signature;

    NPCUnit(String name, World world, Location location, String texture, String signature) {
        this.name = name;
        this.world = world;
        this.location = location;
        this.texture = texture;
        this.signature = signature;
        this.location.setWorld(world);
    }

    public String getName() {
        return name;
    }

    public World getWorld() {
        return world;
    }

    public Location getLocation() {
        return location;
    }

    public String getTexture() {
        return texture;
    }

    public String getSignature() {
        return signature;
    }


    public static void createAll(){
        for(NPCUnit npcUnit : NPCUnit.values()){
            Location newLocation = npcUnit.getLocation();
            newLocation.setWorld(npcUnit.getWorld());
            NPC npc = new NPC(npcUnit.getName(), npcUnit.getWorld(), newLocation);
            npc.setSkin(npcUnit.getTexture(), npcUnit.getSignature());
            initEquipments(npcUnit, npc);
            npc.create();
        }
    }

    private static void initEquipments(NPCUnit npcUnit, NPC npc){
        if(npcUnit.equals(NPCUnit.DE_A_COUDRE)){
            npc.setEquipment(EnumItemSlot.MAINHAND, CraftItemStack.asNMSCopy(new ItemStack(Material.WATER_BUCKET)));
        }
    }

    public static void deleteAll(){
        for(NPC npc : NPC.getNPCs()){
            npc.delete();
        }
    }

}


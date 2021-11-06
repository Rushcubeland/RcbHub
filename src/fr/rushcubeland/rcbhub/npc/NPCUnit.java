package fr.rushcubeland.rcbhub.npc;

import fr.rushcubeland.rcbapi.bukkit.map.MapUnit;
import fr.rushcubeland.rcbapi.bukkit.tools.NPC;
import net.minecraft.server.v1_15_R1.EnumItemSlot;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_15_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public enum NPCUnit {

    DE_A_COUDRE(ChatColor.AQUA + "Dé à Coudre", Bukkit.getWorld(MapUnit.LOBBY.getName()), new Location(null, 65.5, 187, 11.5, 137, 0),
            "eyJ0aW1lc3RhbXAiOjE0NjUwOTE2MDc4NjMsInByb2ZpbGVJZCI6ImIwZDRiMjhiYzFkNzQ4ODlhZjBlODY2MWNlZTk2YWFiIiwicHJvZmlsZU5hbWUiOiJJbnZlbnRpdmVHYW1lcyIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTAxM2U2ZDNhZWJjY2M1ZGEyZTQ1ZjQzYjU4ZjNkZjVjOTUzMzEwM2I5MDQ2MzkzMjU5MmQzNzdjOTM4NTgifX19",
            "keEwIvunVzTyFjLnpmeQsBeu3Kuyw2rG4CUIQEtmG0VZFOVmEaij8/bqVeb/sK8/1cJ8qtT5HvBUnp5lvdVLa59p91XPUxJrFU+j16NgiZ2iEWEcBgF96e0x9fof2UczAmnc7ZNyd7SfDAC9nrGm4kVdEkvyVqN7xL3JG9Hs68/3xbduzxZyZnjm7GSlzs/dj9M2fXprhjLjGwAb+H+ZOXTBFN0GA3ahoXp3+fOo+E+fH9KgRSJJishS/pkLz4KZaavZs+4ARphLQzT3Ty8nGTsmXgP2bq27Ld64YLaZHqdt3cuTne8G9FyKgsFClQmJtxmP4SoGNQPMKV8tIlpdVJxHwqNPDQ/pHAt5W5YSR2E8EqGch7bpCxY8u/nsmJJaK5/MpxghmMZ3tsNv/VI2F4utZBH2xO7wpN8AP2fnpSUZHDm1PhjJSAlRMlcI8puObkjLxLdRD20Dsp1CHbUTAK7129Hb/8IhVCBanoGqd+CtU0SYsh/YK3qpKmlFvCHElvKyxw1C/yVBGZMoWcn/t3lzve8gN+JdYJwE5pF2zGMmu/UYEUoWdA9Xja6PP215AdDtFmcIffVpLnOqEJ9ke7vMUQOJtrrirTCbGqJ9u5G/MQxNIugBy1+eF+orWTcuGoO+EejBAY6G7PciuyM99roNXy5DyRVykEu41lID658=");

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


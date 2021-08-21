package fr.rushcubeland.rcbhub.npc;

import fr.rushcubeland.rcbapi.bukkit.tools.NPC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public enum NPCUnit {

    DE_A_COUDRE("TEST", Bukkit.getWorld("world"), new Location(null, 65.5, 187, 11.5, 137, 0),
            "ewogICJ0aW1lc3RhbXAiIDogMTYyOTUwODMzOTA0MSwKICAicHJvZmlsZUlkIiA6ICJkMDViNzVlMWE3MjA0NTRiYjYxMzE2NmJlZDAwYjk2ZiIsCiAgInByb2ZpbGVOYW1lIiA6ICJuZXNjaG9vbGxhciIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9mMjRlNDA0NjgxYzI3NTEyMDcyZmJkNWIzZDIxYzY0YTc3NDM3YzE1ZTU1ZDhlZTU3NDY4N2YyMjkzYjM4MzI3IgogICAgfSwKICAgICJDQVBFIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8yMzQwYzBlMDNkZDI0YTExYjE1YThiMzNjMmE3ZTllMzJhYmIyMDUxYjI0ODFkMGJhN2RlZmQ2MzVjYTdhOTMzIgogICAgfQogIH0KfQ",
            "gqxZ8Z4/UxCTA2CuwYENOjh4eBmtJLEgNJfn0s5PkOlbVYPiatUYUZgvzGL1eb4/W+jnvEm5qmEBRJADsgid27P71+ZOfeZ7f7p9hZvzOB7stdmHCnec09TRTm/FTzr5sTIDn5ydEj8Rfxi5FXP1SykRFH458oeoN91iM3TYwm3Z0nqDN9uiz0dm6ji5qgdlJOKsp1QKL4/sRXkbhGOGnfumyjZQ0iN5aAbk0RAvHts5Rvh4Hxn0J1OVXyjbE4yq2z2vMQQO6x4f2GtvtQ1t8VzuJBlu6ThMwc0n4/4m5NwRNc7RRKJEEQQGlkQjSW3rRmHzE2kSJAwwBbgNW7BdZIj42wEphhRPBn96SYumQBvz7bCd/YLJHHYEEemsygdJYLXPonn7qysy3kTUyDCO+CfI1Cs2VP822v4THaNi8QIUj4n7AQJ/OF89WHJfV4D8rWIC1PF7f2Gxz/bJgQB3tndfnrtGtGQwAHAlKdJhVof1s96FsCfHyQxtP4QASi3lFNJ8r09yQ8FZJgH1IkJv1Eqt7hkU6vXLDe1CQErkFx+vxhbNVVvug75BQM5hRi9/O4brry34AaszazsnDQScr5CsP1PYgyqx0lE9vo85chlk8mYBhyzXf2Kicc1iixrxaBxwrvw7Sk0hlxjDKdRp0JoQsQgVrCn7Emr5qsS2uJg=");

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
            npc.create();
        }
    }

    public static void deleteAll(){
        for(NPC npc : NPC.getNPCs()){
            npc.delete();
        }
    }

}


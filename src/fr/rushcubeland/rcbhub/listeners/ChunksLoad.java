package fr.rushcubeland.rcbhub.listeners;

import org.bukkit.Chunk;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

public class ChunksLoad implements Listener {

    @EventHandler
    public void onLoad(ChunkLoadEvent event){
        Chunk chunk = event.getChunk();
        for(Entity entity : chunk.getEntities()){
            if(entity.getType() == EntityType.SKELETON || entity.getType() == EntityType.ZOMBIE || entity.getType() == EntityType.SPIDER || entity.getType() == EntityType.CAVE_SPIDER || entity.getType() == EntityType.PHANTOM || entity.getType() == EntityType.LLAMA || entity.getType() == EntityType.PILLAGER){
                entity.remove();
            }
        }

    }

}

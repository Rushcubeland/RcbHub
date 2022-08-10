package fr.rushcubeland.rcbhub.listeners;

import org.bukkit.Chunk;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

/**
 * This class file is a part of RcbHub project claimed by Rushcubeland project.
 * You cannot redistribute, modify or use it for personnal or commercial purposes
 * please contact admin@rushcubeland.fr for any requests or information about that.
 *
 * @author LANNUZEL Dylan
 */

public class ChunksLoad implements Listener {

    @EventHandler
    public void onLoad(ChunkLoadEvent event){
        Chunk chunk = event.getChunk();
        for(Entity entity : chunk.getEntities()){
            if(entity.getType() == EntityType.PLAYER){
                entity.remove();
            }
            if(entity.getType() == EntityType.SKELETON || entity.getType() == EntityType.ZOMBIE || entity.getType() == EntityType.SPIDER || entity.getType() == EntityType.CAVE_SPIDER || entity.getType() == EntityType.PHANTOM || entity.getType() == EntityType.LLAMA || entity.getType() == EntityType.PILLAGER){
                entity.remove();
            }
        }

    }

}

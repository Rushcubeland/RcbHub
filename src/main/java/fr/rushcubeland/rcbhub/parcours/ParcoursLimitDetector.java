package fr.rushcubeland.rcbhub.parcours;

import org.bukkit.entity.Player;

/**
 * This class file is a part of RcbHub project claimed by Rushcubeland project.
 * You cannot redistribute, modify or use it for personnal or commercial purposes
 * please contact admin@rushcubeland.fr for any requests or information about that.
 *
 * @author LANNUZEL Dylan
 */

public class ParcoursLimitDetector {

    private final Player player;

    public ParcoursLimitDetector(Player player) {
        this.player = player;
    }

    private boolean check(){
        if(Parcours.getParcoursDataPlayers().containsKey(player)){
            CheckPointUnit checkPoint = Parcours.getParcoursDataPlayers().get(player);
            if(checkPoint != null){
                return this.player.getFallDistance() > checkPoint.getFallLimit();
            }
        }
        return false;
    }

    public boolean isPlayerExceedLimit() {
        return check();
    }

}

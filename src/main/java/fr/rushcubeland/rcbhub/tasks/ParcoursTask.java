package fr.rushcubeland.rcbhub.tasks;

import fr.rushcubeland.rcbcore.bukkit.tools.TitleManager;
import fr.rushcubeland.rcbhub.RcbHub;
import fr.rushcubeland.rcbhub.parcours.Parcours;
import org.apache.commons.lang.time.DurationFormatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * This class file is a part of RcbHub project claimed by Rushcubeland project.
 * You cannot redistribute, modify or use it for personnal or commercial purposes
 * please contact admin@rushcubeland.fr for any requests or information about that.
 *
 * @author LANNUZEL Dylan
 */

public class ParcoursTask {

    private ParcoursTask() {
        throw new IllegalStateException("This class should not be instantiated");
    }

    public static void startParcoursTask(Player player, long ticks){

        if(!Parcours.getParcoursTasks().containsKey(player) && Parcours.getParcoursDataPlayers().containsKey(player)) {

            int tid = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RcbHub.getInstance(), new Runnable(){
                final long startTime = System.currentTimeMillis();

                @Override
                public void run() {

                    long current = System.currentTimeMillis();
                    long difference = current - startTime;
                    String format = DurationFormatUtils.formatDurationHMS(difference);
                    TitleManager.sendActionBar(player, "§eParcours: §c" + format);

                    if(Parcours.getParcoursTimersPlayers().containsKey(player)) {
                        Parcours.getParcoursTimersPlayers().replace(player, difference);
                    }
                    else
                    {
                        Parcours.getParcoursTimersPlayers().put(player, difference);
                    }
                }

            }, ticks, ticks);

            Parcours.getParcoursTasks().put(player, tid);
        }
    }

    public static void stopParcoursTask(Player player){

        if(Parcours.getParcoursTasks().containsKey(player)){
            int tid = Parcours.getParcoursTasks().get(player);
            Bukkit.getServer().getScheduler().cancelTask(tid);
            Parcours.getParcoursTasks().remove(player);
        }
    }
}


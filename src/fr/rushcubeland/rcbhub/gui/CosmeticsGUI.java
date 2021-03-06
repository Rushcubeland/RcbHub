package fr.rushcubeland.rcbhub.gui;

import fr.rushcubeland.commons.cosmetics.ParticlesUnit;
import fr.rushcubeland.commons.cosmetics.PetsUnit;
import fr.rushcubeland.rcbcore.bukkit.tools.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * This class file is a part of RcbHub project claimed by Rushcubeland project.
 * You cannot redistribute, modify or use it for personnal or commercial purposes
 * please contact admin@rushcubeland.fr for any requests or information about that.
 *
 * @author LANNUZEL Dylan
 */

public class CosmeticsGUI {

    public static final String COSMETICS_GUI_TITLE = "§dCosmétiques";
    public static final String PARTICLES_GUI_TITLE = "§dParticules";
    public static final String PETS_GUI_TITLE = "§dCompagnons";


    public static void openCosmeticsGUI(Player player){

        Inventory inventory = Bukkit.createInventory(null, 54, COSMETICS_GUI_TITLE);

        initGlass(inventory, Material.BLACK_STAINED_GLASS_PANE);

        ItemStack particles = new ItemBuilder(Material.BLAZE_POWDER).setName("§eParticules").removeFlags().toItemStack();
        inventory.setItem(19, particles);

        ItemStack pets = new ItemBuilder(Material.CARROT_ON_A_STICK).setName("§eCompagnon").removeFlags().toItemStack();
        inventory.setItem(20, pets);

        ItemStack close = new ItemBuilder(Material.ACACIA_DOOR).setName("§cFermer").toItemStack();
        inventory.setItem(49, close);

        player.openInventory(inventory);


    }

    public static void openParticlesGUI(Player player){

        Inventory inventory = Bukkit.createInventory(null, 54, PARTICLES_GUI_TITLE);

        initGlass(inventory, Material.MAGENTA_STAINED_GLASS_PANE);

        int i = 19;

        for(ParticlesUnit particlesUnit : ParticlesUnit.values()){
            //ItemStack p = new ItemBuilder(ParticleEffectType.valueOf(particlesUnit.getName()).getMaterial()).setName(particlesUnit.getName()).removeFlags().toItemStack();
            //inventory.setItem(i, p);
            i += 1;
        }

        player.openInventory(inventory);

    }

    public static void openPetsGUI(Player player){

        Inventory inventory = Bukkit.createInventory(null, 54, PETS_GUI_TITLE);

        initGlass(inventory, Material.LIGHT_BLUE_STAINED_GLASS_PANE);

        int i = 19;

        for(PetsUnit petsUnit : PetsUnit.values()){
            ItemStack p = new ItemBuilder(Material.MYCELIUM).setName(petsUnit.getName()).removeFlags().toItemStack();
            inventory.setItem(i, p);
            i += 1;
        }

        player.openInventory(inventory);


    }

    private static void initGlass(Inventory inventory, Material material){
        ItemStack glass = new ItemBuilder(material).removeFlags().toItemStack();
        inventory.setItem(0, glass);
        inventory.setItem(1, glass);
        inventory.setItem(7, glass);
        inventory.setItem(8, glass);
        inventory.setItem(9, glass);
        inventory.setItem(17, glass);
        inventory.setItem(36, glass);
        inventory.setItem(45, glass);
        inventory.setItem(46, glass);
        inventory.setItem(52, glass);
        inventory.setItem(44, glass);
        inventory.setItem(53, glass);
    }


}

package fr.rushcubeland.rcbhub.gui;

import fr.rushcubeland.commons.cosmetics.ParticlesUnit;
import fr.rushcubeland.commons.cosmetics.PetsUnit;
import fr.rushcubeland.rcbcore.bukkit.tools.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CosmeticsGUI {

    private CosmeticsGUI() {
        throw new IllegalStateException("This class should not be instantiated");
    }

    public static final String COSMETICS_GUI_TITLE = ChatColor.LIGHT_PURPLE + "Cosmétiques";
    public static final String PARTICLES_GUI_TITLE = ChatColor.LIGHT_PURPLE + "Particules";
    public static final String PETS_GUI_TITLE = ChatColor.LIGHT_PURPLE + "Compagnons";


    public static void openCosmeticsGUI(Player player){

        Inventory inventory = Bukkit.createInventory(null, 54, COSMETICS_GUI_TITLE);

        initGlass(inventory, Material.BLACK_STAINED_GLASS_PANE);

        ItemStack particles = new ItemBuilder(Material.BLAZE_POWDER).setName(ChatColor.YELLOW + "Particules").removeFlags().toItemStack();
        inventory.setItem(19, particles);

        ItemStack pets = new ItemBuilder(Material.CARROT_ON_A_STICK).setName(ChatColor.YELLOW + "Compagnon").removeFlags().toItemStack();
        inventory.setItem(20, pets);
        ItemStack close = new ItemBuilder(Material.ACACIA_DOOR).setName(ChatColor.RED + "Fermer").toItemStack();
        inventory.setItem(49, close);

        player.openInventory(inventory);

    }

    public static void openParticlesGUI(Player player){

        Inventory inventory = Bukkit.createInventory(null, 54, PARTICLES_GUI_TITLE);

        initGlass(inventory, Material.MAGENTA_STAINED_GLASS_PANE);

        int i = 19;

        for(ParticlesUnit particlesUnit : ParticlesUnit.values()){
            ItemStack p = new ItemBuilder(Material.BARRIER).setName(particlesUnit.getName()).removeFlags().toItemStack();
            inventory.setItem(i, p);
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

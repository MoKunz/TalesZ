package com.talesdev.talesz.thirst;

import com.talesdev.talesz.Main;
import com.talesdev.talesz.exp.ExpBarUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;

/**
 * Thirst
 * Created by MoKunz on 17/10/2557.
 */
public class Thirst {
    private static YamlConfiguration configuration;
    private static File dir;
    private static File file;
    public static final int FULL_THIRST = 100;
    public static final int THIRST_UPDATE_VALUE = 1;
    public static final String DOT = ".";
    private static ThirstRule thirstRule;
    /**
     * String : player name
     * Integer : thirst value
     */
    protected static HashMap<String, Integer> thirst;

    static {
        // create file and directory
        dir = new File("plugins/TalesZ");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        file = new File("plugins/TalesZ/thirst.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // construct new configuration object
        configuration = new YamlConfiguration();
        try {
            configuration.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        // create if not exist
        if (!configuration.isSet("Thirst")) {
            configuration.set("Thirst", null);
        }
        // create new hash map
        thirst = new HashMap<>();
        // create thirst rule
        thirstRule = new ThirstRule();
        thirstRule.loadRule();
    }

    public static void registerNewPlayer(String player) {
        if (!thirst.containsKey(player)) {
            resetThirst(player);
        }
    }

    public static void unregisterPlayer(String player) {
        if (thirst.containsKey(player)) {
            thirst.remove(player);
        }
    }

    public static void setThirst(String player, int value) {
        // prevent overflow
        if (value <= FULL_THIRST && value > 0) {
            ThirstDamage.removeFromList(player);
            thirst.put(player, value);
        } else {
            ThirstDamage.addToList(player);
            thirst.put(player, 0);
        }
    }

    public static int getThirst(String player) {
        if (thirst.get(player) == null) {
            return 0;
        }
        return thirst.get(player);
    }

    public static void resetThirst(String player) {
        ThirstDamage.removeFromList(player);
        // reset to full thirst
        setThirst(player, FULL_THIRST);
    }

    public static void updateAll() {
        thirst.keySet().forEach(com.talesdev.talesz.thirst.Thirst::updatePlayer);
    }

    public static void updatePlayer(String player) {
        try {
            Player p = Bukkit.getPlayer(player);
            // get biome data
            Location loc = p.getLocation();
            World world = p.getWorld();
            Biome biome = world.getBiome(loc.getBlockX(), loc.getBlockZ());
            // update data
            setThirst(player, getThirst(player) - getThirstRule().getRule(biome));
            // update bar
            ExpBarUtil.apply(p, p.getLevel(), (double) getThirst(player));
        } catch (Exception e) {
            Main.getPlugin().getLogger().log(Level.WARNING, "Thirst system encountered a problem while updating thirst of \"" + player + "\" !");
            e.printStackTrace();
        }
    }

    public static void updateExpBar(Player p) {
        ExpBarUtil.apply(p, p.getLevel(), (double) Thirst.getThirst(p.getName()));
    }

    public static YamlConfiguration getConfig() {
        return configuration;
    }

    public static void saveAll() throws IOException {
        for (String playerName : thirst.keySet()) {
            saveDataToDisk(playerName);
        }
    }

    public static void saveDataToDisk(String playerName) throws IOException {
        getConfig().set("Thirst" + DOT + playerName, getThirst(playerName));
        getConfig().save(file);
    }

    public static void reload() {
        try {
            getConfig().save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            getConfig().load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void loadData(String playerName) {
        int thirst = getConfig().getInt("Thirst" + DOT + playerName);
        setThirst(playerName, thirst);
    }

    public static boolean thirstDataExist(String playerName) {
        return getConfig().isSet("Thirst" + DOT + playerName);
    }

    public static ThirstRule getThirstRule() {
        return thirstRule;
    }
}

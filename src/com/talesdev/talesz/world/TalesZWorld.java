package com.talesdev.talesz.world;

import com.talesdev.talesz.loot.ChestLocationList;
import com.talesdev.talesz.loot.LootChest;
import org.bukkit.block.Block;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

/**
 * TalesZ World manager
 * Created by MoKunz on 20/10/2557.
 */
public class TalesZWorld {
    public static String WORLD_NAME = "world";
    private static List<String> playerList = new ArrayList<>();
    private static SpawnPointManager spawnPointManager;
    private static List<BlockZone> blockZoneList = new ArrayList<>();
    private static List<LootChest> lootChestList = new ArrayList<>();
    private static ChestLocationList chestLocationList;
    public static void init(SpawnPointManager manager) {
        spawnPointManager = manager;
        chestLocationList = new ChestLocationList();
    }

    public static ChestLocationList getChestLocationList() {
        return chestLocationList;
    }
    public static void addBlockZone(BlockZone blockZone) {
        blockZoneList.add(blockZone);
    }

    public static BlockZone getBlockZone(String blockZone) {
        for (BlockZone zone : blockZoneList) {
            if (zone.getZoneName().equals(blockZone)) {
                return zone;
            }
        }
        return null;
    }

    public static List<BlockZone> getAllBlockZone() {
        return blockZoneList;
    }

    public static void addLootChest(LootChest lootChest) {
        getAllLootChest().add(lootChest);
        getChestLocationList().createChest(lootChest.getTypeName());
        getChestLocationList().load(lootChest.getTypeName());
    }

    public static LootChest getLootChest(Block block) {
        if (getAllLootChest().size() > 0) {
            for (LootChest lootChest : getAllLootChest()) {
                if (lootChest.isLootChest(block)) {
                    return lootChest;
                }
            }
        }
        return null;
    }

    public static LootChest getLootChest(Inventory inventory) {
        if (getAllLootChest().size() > 0) {
            for (LootChest lootChest : getAllLootChest()) {
                if (lootChest.isLootChest(inventory)) {
                    return lootChest;
                }
            }
        }
        return null;
    }

    public static List<LootChest> getAllLootChest() {
        return lootChestList;
    }
    public static void setWorldName(String worldName) {
        WORLD_NAME = worldName;
    }

    public static void addPlayer(String player) {
        playerList.add(player);
    }

    public static void removePlayer(String player) {
        playerList.remove(player);
    }

    public static boolean containPlayer(String player) {
        return playerList.contains(player);
    }

    public static SpawnPointManager getSpawnPointManager() {
        return spawnPointManager;
    }

}

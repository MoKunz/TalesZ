package com.talesdev.talesz.world;

import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;

/**
 * Block zone rule
 * Created by MoKunz on 3/14/2015.
 */
public class BlockZoneRule {
    private HashMap<Material, Boolean> blockZoneRule;

    public BlockZoneRule() {
        this.blockZoneRule = new HashMap<>();
    }

    public Map<Material, Boolean> getBlockZoneRule() {
        return this.blockZoneRule;
    }

    public boolean getRule(Material material) {
        if (this.blockZoneRule.get(material) != null) {
            return this.blockZoneRule.get(material);
        }
        return false;
    }

    public void writeRule(Material material, boolean rule) {
        if (!this.blockZoneRule.containsKey(material)) {
            this.blockZoneRule.put(material, rule);
        }
    }

    public void editRule(Material material, boolean newRule) {
        if (this.blockZoneRule.containsKey(material)) {
            this.blockZoneRule.put(material, newRule);
        }
    }

    public void removeRule(Material material) {
        if (this.blockZoneRule.containsKey(material)) {
            this.blockZoneRule.remove(material);
        }
    }
}

package com.talesdev.talesz.exp;

import org.bukkit.entity.Player;

/**
 * Exp bar system
 * Created by MoKunz on 2/13/2015.
 */
public class ExpBarUtil {
    public static final double BIAS_DEFAULT = 1;

    /**
     * Apply a player for specific xp level and amount
     *
     * @param player A player to be applied
     * @param level  Amount of level
     * @param bar    Percent of xp green bar
     */
    public static void apply(Player player, int level, double bar) {
        // set level
        player.setLevel(level);
        // reset xp
        player.setExp(0.0F);
        // set bar
        player.setExp((float) calculateExpBar(bar, 1));
    }

    /**
     * Apply a player for specific xp level and amount (With green-bar bias)
     *
     * @param player A player to be applied
     * @param level  Amount of level
     * @param bar    Percent of xp green bar
     * @param bias   The bias
     */
    public static void apply(Player player, int level, double bar, double bias) {
        apply(player, level, bar - bias);
    }

    /**
     * Calculate amount of green xp bar
     *
     * @param percent The percent value from 0 - 100
     * @param exp     Xp bar range
     * @return Amount of xp bar from 0.0 - 1.0 ( depend on range )
     */
    private static double calculateExpBar(double percent, double exp) {
        return (percent / 100D) * exp;
    }
}

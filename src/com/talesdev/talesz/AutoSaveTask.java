package com.talesdev.talesz;

import com.talesdev.talesz.mobsystem.MobRuleManager;
import com.talesdev.talesz.thirst.Thirst;
import com.talesdev.talesz.world.BlockRuleManager;

import java.io.IOException;
import java.util.logging.Level;

/**
 * Auto save
 * Created by MoKunz on 3/11/2015.
 */
public class AutoSaveTask implements Runnable {
    @Override
    public void run() {
        boolean showMessage = TalesZMainConfig.getConfig().getBoolean("autosave.showmessage");
        if (showMessage) Main.getPlugin().getLogger().info("Saving data...");
        Thirst.getThirstRule().saveRule();
        try {
            Thirst.saveAll();
        } catch (IOException e) {
            Main.getPlugin().getLogger().log(Level.SEVERE, "Unable to save thirst data to disk!");
            e.printStackTrace();
        }
        // save block rule
        BlockRuleManager.saveConfigFile();
        // save mob rule
        MobRuleManager.saveConfigFile();
        // main config
        TalesZMainConfig.save();
        if (showMessage) Main.getPlugin().getLogger().info("Saving done!");
    }
}

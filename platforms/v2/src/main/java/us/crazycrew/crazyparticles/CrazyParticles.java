package us.crazycrew.crazyparticles;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import us.crazycrew.crazycore.CrazyLogger;
import us.crazycrew.crazycore.paper.PaperCore;
import us.crazycrew.crazyparticles.commands.Permissions;

public class CrazyParticles extends JavaPlugin {

    private final PaperCore paperCore;

    public CrazyParticles(PaperCore paperCore) {
        this.paperCore = paperCore;
    }

    @Override
    @NotNull
    public java.util.logging.Logger getLogger() {
        return CrazyLogger.getLogger();
    }

    @Override
    public void onEnable() {
        Permissions.register(this.getServer().getPluginManager());
    }

    @Override
    public void onDisable() {

    }

    public PaperCore getPaperCore() {
        return paperCore;
    }
}
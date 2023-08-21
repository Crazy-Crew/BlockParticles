package com.badbones69.blockparticles.plugin;

import com.badbones69.common.BlockParticlesPlugin;
import com.badbones69.common.utils.AdvUtil;
import com.badbones69.common.plugin.FancyLogger;
import com.badbones69.common.plugin.Platform;
import com.badbones69.common.utils.FileUtil;
import com.badbones69.blockparticles.plugin.builder.commands.PaperCommandManager;
import com.badbones69.blockparticles.plugin.items.skulls.SkullCreator;
import com.badbones69.blockparticles.plugin.registry.PaperRegistration;
import com.badbones69.blockparticles.plugin.utils.ItemUtil;
import com.badbones69.blockparticles.plugin.utils.LegacyUtil;
import net.kyori.adventure.platform.AudienceProvider;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.plugin.java.JavaPlugin;

public class PaperImpl extends BlockParticlesPlugin {

    private PaperCommandManager manager;
    private SkullCreator skullCreator;
    private BukkitAudiences audience;
    private LegacyUtil legacyUtil;
    private AdvUtil advUtil;
    private JavaPlugin plugin;
    private ItemUtil itemUtil;
    private FileUtil fileUtil;
    private boolean isLegacy;
    private FancyLogger fancyLogger;

    public PaperImpl(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void enable(boolean value) {
        // Runs the enable method from the inherited class.
        super.enable(this.isLegacy = value);

        // Initializes the paper provider.
        PaperRegistration.start(this);

        // Creates the audience sender.
        if (this.audience == null) {
            this.audience = BukkitAudiences.create(this.plugin);
        }

        // Create adventure/logger instance.
        this.advUtil = new AdvUtil();
        this.legacyUtil = new LegacyUtil();
        this.fancyLogger = new FancyLogger(this.plugin.getName());

        this.itemUtil = new ItemUtil();
        this.skullCreator = new SkullCreator();

        this.fileUtil = new FileUtil();

        if (!this.plugin.getDataFolder().exists()) this.plugin.getDataFolder().mkdirs();

        this.manager = new PaperCommandManager();
    }

    @Override
    public void disable() {
        // Runs the disable method from the inherited class.
        super.disable();

        // Stops the paper provider.
        PaperRegistration.stop();

        // If audience is not null, close and set to null.
        if (this.audience != null) {
            this.audience.close();
            this.audience = null;
        }
    }

    public PaperCommandManager getManager() {
        return this.manager;
    }

    @Override
    public AudienceProvider getAudience() {
        return this.audience;
    }

    @Override
    public Platform.Type getPlatform() {
        return Platform.Type.PAPER;
    }

    @Override
    public AdvUtil getAdventure() {
        return this.advUtil;
    }

    @Override
    public FileUtil getFileUtil() {
        return this.fileUtil;
    }

    @Override
    public FancyLogger getFancyLogger() {
        return this.fancyLogger;
    }

    @Override
    public boolean isLegacy() {
        return this.isLegacy;
    }

    public PaperImpl setPlugin(JavaPlugin plugin) {
        // If the plugin is already registered,
        // return as we don't want it registered again.
        if (this.plugin != null) return this;

        // Set the plugin variable.
        this.plugin = plugin;

        return this;
    }

    public SkullCreator getSkullCreator() {
        return this.skullCreator;
    }

    public LegacyUtil getLegacyUtil() {
        return this.legacyUtil;
    }

    public ItemUtil getItemUtil() {
        return this.itemUtil;
    }

    public JavaPlugin getPlugin() {
        return this.plugin;
    }
}
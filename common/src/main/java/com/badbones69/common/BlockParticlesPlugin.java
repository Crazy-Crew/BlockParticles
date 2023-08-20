package com.badbones69.common;

import com.badbones69.common.plugin.Adventure;
import com.badbones69.common.plugin.Logger;
import com.badbones69.common.plugin.Platform;
import com.badbones69.common.plugin.registry.BlockParticleRegistration;
import com.badbones69.common.utils.FileUtil;
import net.kyori.adventure.platform.AudienceProvider;

public abstract class BlockParticlesPlugin {

    public abstract AudienceProvider getAudience();

    public abstract Platform.Type getPlatform();

    public abstract Adventure getAdventure();

    public abstract FileUtil getFileUtil();

    public abstract Logger getFancyLogger();

    public abstract boolean isLegacy();

    public void enable(boolean value) {
        BlockParticleRegistration.start(this);
    }

    public void disable() {
        BlockParticleRegistration.stop();
    }
}
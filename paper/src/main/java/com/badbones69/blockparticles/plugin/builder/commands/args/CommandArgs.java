package com.badbones69.blockparticles.plugin.builder.commands.args;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public interface CommandArgs {

    int getArgAsInt(int index, boolean notifySender, String message);

    long getArgAsLong(int index, boolean notifySender, String message);

    double getArgAsDouble(int index, boolean notifySender, String message);

    boolean getArgAsBoolean(int index, boolean notifySender, String message);

    float getArgAsFloat(int index, boolean notifySender, String message);

    Player getArgAsPlayer(int index, boolean notifySender, String message);

    OfflinePlayer getArgAsOfflinePlayer(int index);

}
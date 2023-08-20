package com.badbones69.blockparticles.plugin.builder.commands;

import com.badbones69.common.builder.commands.CommandContext;
import com.badbones69.blockparticles.plugin.PaperImpl;
import com.badbones69.blockparticles.plugin.builder.commands.args.CommandArgs;
import com.badbones69.blockparticles.plugin.registry.PaperProvider;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class PaperCommandContext extends CommandContext implements CommandArgs {

    private final @NotNull PaperImpl paper = PaperProvider.get();

    private final @NotNull JavaPlugin plugin = this.paper.getPlugin();
    private final boolean isLegacy = this.paper.isLegacy();

    private final CommandSender sender;

    public PaperCommandContext(CommandSender sender, String label, List<String> args) {
        super(sender, label, args);

        this.sender = sender;
    }

    public void legacy(String message) {
        this.sender.sendMessage(this.paper.getLegacyUtil().parse(message));
    }

    @Override
    public CommandSender getSender() {
        return this.sender;
    }

    @Override
    public Player getPlayer() {
        return (Player) this.sender;
    }

    @Override
    public boolean isPlayer() {
        return this.sender instanceof Player;
    }
    
    // Arguments

    @Override
    public int getArgAsInt(int index, boolean notifySender, String message) {
        int value = 1;

        try {
            value = Integer.parseInt(getArgs().get(index));
        } catch (NumberFormatException exception) {
            String msg = message.replaceAll("\\{value}", getArgs().get(index)).replaceAll("\\{action}", "integer");

            if (!notifySender) return value;

            if (this.isLegacy) {
                legacy(msg);
                return value;
            }

            reply(msg);
            return value;
        }

        return value;
    }

    @Override
    public long getArgAsLong(int index, boolean notifySender, String message) {
        long value = 1L;

        try {
            value = Long.parseLong(getArgs().get(index));
        } catch (NumberFormatException exception) {
            String msg = message.replaceAll("\\{value}", getArgs().get(index)).replaceAll("\\{action}", "long");

            if (!notifySender) return value;

            if (this.isLegacy) {
                legacy(msg);
                return value;
            }

            reply(msg);
            return value;
        }

        return value;
    }

    @Override
    public double getArgAsDouble(int index, boolean notifySender, String message) {
        double value = 0.1;

        try {
            value = Double.parseDouble(getArgs().get(index));
        } catch (NumberFormatException exception) {
            String msg = message.replaceAll("\\{value}", getArgs().get(index)).replaceAll("\\{action}", "double");

            if (!notifySender) return value;

            if (this.isLegacy) {
                legacy(msg);
                return value;
            }

            reply(msg);
            return value;
        }

        return value;
    }

    @Override
    public boolean getArgAsBoolean(int index, boolean notifySender, String message) {
        String lowerCase = getArgs().get(index).toLowerCase();

        switch (lowerCase) {
            case "true", "on", "1" -> {
                return true;
            }
            case "false", "off", "0" -> {
                return false;
            }
            default -> {
                String msg = message.replaceAll("\\{value}", getArgs().get(index)).replaceAll("\\{action}", "boolean");

                if (!notifySender) return false;

                if (this.isLegacy) {
                    legacy(msg);
                    return false;
                }

                reply(msg);
                return false;
            }
        }
    }

    @Override
    public float getArgAsFloat(int index, boolean notifySender, String message) {
        float value = 1F;

        try {
            value = Float.parseFloat(getArgs().get(index));
        } catch (NumberFormatException exception) {
            String msg = message.replaceAll("\\{value}", getArgs().get(index)).replaceAll("\\{action}", "float");

            if (!notifySender) return value;

            if (this.isLegacy) {
                legacy(msg);
                return value;
            }

            reply(msg);
            return value;
        }

        return value;
    }

    @Override
    public Player getArgAsPlayer(int index, boolean notifySender, String message) {
        Player player = this.paper.getPlugin().getServer().getPlayer(getArgs().get(index));

        if (player == null) {
            String msg = message.replaceAll("\\{value}", getArgs().get(index)).replaceAll("\\{action}", "player");

            if (!notifySender) return null;

            if (this.isLegacy) {
                legacy(msg);
                return null;
            }

            reply(msg);
            return null;
        }

        return player;
    }

    @Override
    public OfflinePlayer getArgAsOfflinePlayer(int index) {
        CompletableFuture<UUID> future = CompletableFuture.supplyAsync(() -> this.plugin.getServer().getOfflinePlayer(getArgs().get(index))).thenApply(OfflinePlayer::getUniqueId);

        return this.plugin.getServer().getOfflinePlayer(future.join());
    }
}
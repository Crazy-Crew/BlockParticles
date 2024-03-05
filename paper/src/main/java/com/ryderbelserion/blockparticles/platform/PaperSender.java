package com.ryderbelserion.blockparticles.platform;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;

public class PaperSender implements Sender {

    private final CommandSender sender;

    public PaperSender(CommandSender sender) {
        this.sender = sender;
    }

    @Override
    public boolean isPlayer() {
        return this.sender instanceof Player;
    }

    @Override
    public String getName() {
        return this.sender.getName();
    }

    @Override
    public boolean hasPermission(String permission) {
        return this.sender.hasPermission(permission);
    }

    @Override
    public void sendMessage(String value, boolean hasPrefix) {
        //todo() add prefix support.
        sender.sendMessage(MiniMessage.miniMessage().deserialize(value));
    }
}
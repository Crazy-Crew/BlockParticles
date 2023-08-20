package com.badbones69.blockparticles.plugin.builder.commands.args.types;

import com.badbones69.common.builder.commands.args.ArgumentType;
import com.badbones69.blockparticles.plugin.PaperImpl;
import com.badbones69.blockparticles.plugin.registry.PaperProvider;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerArgument extends ArgumentType {

    private final @NotNull PaperImpl paper = PaperProvider.get();

    @Override
    public List<String> getPossibleValues() {
        return this.paper.getPlugin().getServer().getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList());
    }
}
package com.badbones69.blockparticles.api.objects;

import com.ryderbelserion.vital.paper.builders.items.ItemBuilder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomFountain {

    private final List<ItemStack> builtHeads = new ArrayList<>();
    private final List<String> fountainHeads;
    private final String fountainName;

    public CustomFountain(String fountainName, List<String> fountainHeads) {
        this.fountainName = fountainName;
        this.fountainHeads = fountainHeads;

        for (String head : this.fountainHeads) {
            ItemStack item = new ItemBuilder().setPlayer(head).getStack();

            this.builtHeads.add(item);
        }
    }

    public @NotNull final List<ItemStack> getBuiltHeads() {
        return Collections.unmodifiableList(this.builtHeads);
    }

    public @NotNull final List<String> getFountainHeads() {
        return Collections.unmodifiableList(this.fountainHeads);
    }

    public @NotNull final String getFountainName() {
        return this.fountainName;
    }
}
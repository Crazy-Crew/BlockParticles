package com.badbones69.common.builder;

import com.badbones69.common.BlockParticlesPlugin;
import com.badbones69.common.plugin.registry.BlockParticleProvider;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import org.jetbrains.annotations.NotNull;

public class ComponentBuilder {

    private final BlockParticlesPlugin blockPlugin = BlockParticleProvider.get();

    private final TextComponent.@NotNull Builder builder = Component.text();

    private final FancyComponentBuilder fancyComponentBuilder;

    public ComponentBuilder() {
        this.fancyComponentBuilder = new FancyComponentBuilder();
    }

    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public ComponentBuilder append(Component component) {
        this.builder.append(component);

        return this;
    }

    private Component parse(String value) {
        return this.blockPlugin.getAdventure().parse(value);
    }

    public ComponentBuilder hover(String text) {
        this.builder.hoverEvent(HoverEvent.showText(parse(text)));

        return this;
    }

    public ComponentBuilder click(ClickEvent.Action action, String text) {
        this.builder.clickEvent(ClickEvent.clickEvent(action, text));

        return this;
    }

    public @NotNull TextComponent build() {
        Component message = parse(this.message);

        Component fancy = this.fancyComponentBuilder.fancy(message);

        if (fancy != null) return this.builder.append(message).append(fancy).build();

        return this.builder.append(message).build();
    }
}
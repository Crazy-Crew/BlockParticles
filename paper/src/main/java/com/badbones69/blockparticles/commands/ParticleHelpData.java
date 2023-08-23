package com.badbones69.blockparticles.commands;

import ch.jalu.configme.SettingsManager;
import com.badbones69.blockparticles.api.config.ConfigManager;
import com.badbones69.blockparticles.api.config.types.PluginSupport;
import com.ryderbelserion.ruby.other.builder.commands.CommandHelpProvider;

public class ParticleHelpData implements CommandHelpProvider {

    private final SettingsManager support = ConfigManager.support();

    private final String prefix = this.support.getProperty(PluginSupport.COMMAND_PREFIX);

    @Override
    public int defaultHelpPerPage() {
        return this.support.getProperty(PluginSupport.HELP_MAX_PAGE_ENTRIES);
    }

    @Override
    public String optionalMessage() {
        return this.support.getProperty(PluginSupport.OPTIONAL_ARGUMENT).replaceAll("\\{prefix}", prefix);
    }

    @Override
    public String requiredMessage() {
        return this.support.getProperty(PluginSupport.REQUIRED_ARGUMENT).replaceAll("\\{prefix}", prefix);
    }

    @Override
    public String hoverMessage() {
        return this.support.getProperty(PluginSupport.HELP_HOVER_FORMAT).replaceAll("\\{prefix}", prefix);
    }

    @Override
    public String hoverAction() {
        return this.support.getProperty(PluginSupport.HELP_HOVER_ACTION).replaceAll("\\{prefix}", prefix);
    }

    @Override
    public String invalidFormat() {
        return this.support.getProperty(PluginSupport.HELP_INVALID_FORMAT).replaceAll("\\{prefix}", prefix);
    }

    @Override
    public String notEnoughArgs() {
        return this.support.getProperty(PluginSupport.NOT_ENOUGH_ARGS).replaceAll("\\{prefix}", prefix);
    }

    @Override
    public String tooManyArgs() {
        return this.support.getProperty(PluginSupport.TOO_MANY_ARGS).replaceAll("\\{prefix}", prefix);
    }

    @Override
    public String invalidPage() {
        return this.support.getProperty(PluginSupport.HELP_INVALID_PAGE).replaceAll("\\{prefix}", prefix);
    }

    @Override
    public String pageHeader() {
        return this.support.getProperty(PluginSupport.HELP_PAGE_HEADER);
    }

    @Override
    public String pageFormat() {
        return this.support.getProperty(PluginSupport.HELP_PAGE_FORMAT);
    }

    @Override
    public String pageFooter() {
        return this.support.getProperty(PluginSupport.HELP_PAGE_FOOTER);
    }

    @Override
    public String pageNavigation() {
        return this.support.getProperty(PluginSupport.HELP_PAGE_GO_TO_PAGE);
    }

    @Override
    public String pageNextButton() {
        return this.support.getProperty(PluginSupport.HELP_PAGE_NEXT);
    }

    @Override
    public String pageBackButton() {
        return this.support.getProperty(PluginSupport.HELP_PAGE_BACK);
    }

    @Override
    public String noPermission() {
        return "no permission";
    }

    @Override
    public String notPlayer() {
        return "not a player";
    }
}
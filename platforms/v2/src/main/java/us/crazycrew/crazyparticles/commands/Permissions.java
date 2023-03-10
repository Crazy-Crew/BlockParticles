package us.crazycrew.crazyparticles.commands;

import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.PluginManager;
import us.crazycrew.crazycrates.configurations.PluginSettings;
import us.crazycrew.crazyparticles.loader.CrazyStarter;
import java.util.EnumSet;
import java.util.HashMap;

public enum Permissions {

    PARTICLES_COMMAND_PLAYER_HELP("command.player.help", "Shows all player commands for CrazyParticles.", new HashMap<>(), PermissionDefault.TRUE),

    PARTICLES_COMMAND_ADMIN_HELP("command.admin.help", "Shows all admin commands for CrazyParticles", new HashMap<>() {{
        put(prefix + ".command.admin.reload", true);
    }}, PermissionDefault.OP);

    private final String node;
    private final String description;
    private final HashMap<String, Boolean> children;
    private final PermissionDefault permissionDefault;

    private static final String prefix = CrazyStarter.getPluginConfig().getProperty(PluginSettings.COMMAND_PERMISSION);

    /**
     * @param node permission node without the prefix
     * @param description description of the permission
     * @param children sub permissions
     * @param permissionDefault true, false, op, not-op
     */
    Permissions(String node, String description, HashMap<String, Boolean> children, PermissionDefault permissionDefault) {
        this.node = node;
        this.description = description;
        this.children = children;
        this.permissionDefault = permissionDefault;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @return individual node without the prefix i.e command.admin.help
     */
    public String getNode() {
        return this.node;
    }

    /**
     * @return permission default i.e true, false, op, not op
     */
    public PermissionDefault getPermissionDefault() {
        return this.permissionDefault;
    }

    /**
     * @return sub permissions of the main permission
     */
    public HashMap<String, Boolean> getChildren() {
        return this.children;
    }

    /**
     * @return completed permission node
     */
    public String getPermissionNode() {
        return prefix + "." + this.node;
    }

    /**
     * Registers all permissions into the plugin manager.
     *
     * @param pluginManager server's plugin manager
     */
    public static void register(PluginManager pluginManager) {
        EnumSet.allOf(Permissions.class).forEach(action -> {
            if (pluginManager.getPermission(action.getPermissionNode()) == null) return;
            pluginManager.addPermission(
                    new Permission(
                            action.getPermissionNode(),
                            action.getDescription(),
                            action.getPermissionDefault(),
                            action.getChildren()
                    ));
        });
    }
}
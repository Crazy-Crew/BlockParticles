package com.badbones69.blockparticles.commands.envoys.types.player;

import com.badbones69.blockparticles.commands.envoys.BaseCommand;
import dev.triumphteam.cmd.bukkit.annotation.Permission;
import dev.triumphteam.cmd.core.annotations.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.PermissionDefault;

public class CommandList extends BaseCommand {

    @Command("types")
    @Permission(value = "blockparticles.types", def = PermissionDefault.OP)
    public void types(CommandSender sender) {
        //sender.sendMessage(Methods.color(prefix + "&3List of all particle types."));
        //sender.sendMessage(Methods.color("&6&lParticles&8: &3Total " + Particles.getParticles(ParticleType.PARTICLE).size() + "."));
        //sender.sendMessage(Methods.color("&aSpiral&8, &aDoubleSpiral&8, &aCrit&8, &aBigCrit&8, &aFlame&8, &aBigFlame&8, &aVolcano" + "&8, &aFog&8, &aEnchant&8, &aStorm&8, &aChains&8, &aFireStorm&8, &aSnow&8, &aPotion&8, &aMusic&8, &aSpew&8," + "&aMagic&8, &aWitch&8, &aDoubleWitch&8, &aSnowStorm&8, &aFireSpew&8, &aFootPrint&8, &aWater&8, &aHappyVillager" + "&8, &aAngryVillager&8, &aMobSpawner&8, &aEnderSignal&8, &aRainbow&8," + "&aSnowBlast&8, &aHalo&8, &aSoulWell&8, &aBigSoulWell&8, &aLoveWell&8, &aBigLoveWell&8," + "&aFlameWheel&8, &aWitchTornado&8, &aLoveTornado"));
        //sender.sendMessage(Methods.color("&6&lFountains&8: &3Total " + Particles.getParticles(ParticleType.FOUNTAIN).size() + "."));
        //sender.sendMessage(Methods.color("&aGems&8, &aHalloween&8, &aHeads&8, &aPresents&8, &aMobs&8, &aFood&8, &aPokemon&8, &aMario"));
    }
}
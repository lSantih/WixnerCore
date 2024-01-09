package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.CommandPermission;
import dev.santih.wixnercore.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.WorldType;
import org.bukkit.command.CommandSender;

@CommandAlias("gc")
@Description("View server memory usage and world statistics")
@CommandPermission("unitycore.gc")
public class GCCommand extends BaseCommand {

    @Default
    public void onGC(CommandSender sender) {
        Runtime runtime = Runtime.getRuntime();
        long usedMem = (runtime.totalMemory() - runtime.freeMemory()) / 1048576L;
        long maxMem = runtime.maxMemory() / 1048576L;
        long freeMem = maxMem - usedMem;

        MessageUtils.sendCoreMessage(sender, "&6Used memory: &c" + usedMem + "MB&6/&c" + maxMem + "MB&6.");
        MessageUtils.sendCoreMessage(sender, "&6Free memory: &a" + freeMem + "MB&6.");
        MessageUtils.sendCoreMessage(sender, "");

        for (World world : Bukkit.getWorlds()) {
            int tileEntities = world.getLoadedChunks().length * 16 * 16 * world.getMaxHeight() / 256;

            String worldName = getWorldType(world);

            MessageUtils.sendCoreMessage(sender, "&6" + worldName + ": &a" + world.getLoadedChunks().length + "&6 chunks, &a" + world.getEntities().size() + "&6 entities, &a" + tileEntities + "&6 tiles.");
        }
        MessageUtils.sendCoreMessage(sender, "");

        MessageUtils.sendCoreMessage(sender, "&aGreen &6= Normal, &dPink &6= Superflat, &cRed &6= Nether, &5Purple &6= End.");
    }

    private String getWorldType(World world) {
        String worldName = "";
        if (world.getEnvironment() == Environment.NORMAL) {
            if (world.getWorldType() == WorldType.FLAT) {
                worldName = "&d" + world.getName();
            } else {
                worldName = "&a" + world.getName();
            }
        } else if (world.getEnvironment() == Environment.NETHER) {
            worldName = "&c" + world.getName();
        } else if (world.getEnvironment() == Environment.THE_END) {
            worldName = "&5" + world.getName();
        }
        return worldName;
    }
}

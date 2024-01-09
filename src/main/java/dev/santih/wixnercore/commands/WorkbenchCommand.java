package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import dev.santih.wixnercore.utils.MessageUtils;
import org.bukkit.entity.Player;

@CommandAlias("workbench|wb|craft")
@Description("Opens a workbench for the player")
public class WorkbenchCommand extends BaseCommand {

    @Default
    public void onWorkbench(final Player player) {
        player.openWorkbench(null, true);
        MessageUtils.sendCoreMessage(player, "&aWorkbench opened!");
    }
}

package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import dev.santih.wixnercore.config.Messages;
import dev.santih.strike.util.Common;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("unity|u")
public class UnityCommand extends BaseCommand {

    @Default
    public void onUnityCommand(CommandSender sender) {
        Common.tell(sender, "&#FFCF00&l⚡ &#FFCF00El servidor utiliza UnityCore 1.0.0. Desarollada por lSantih para UnityDream");
    }

    @Subcommand("reload")
    public class ReloadCommand extends BaseCommand {

        @Subcommand("lang")
        @CommandPermission("unity.reload.lang")
        public void onReloadLang(Player player) {
            enMessages.loadData(Messages.class);
            Common.tell(player, "&aLanguages files reloaded");
        }

        @Subcommand("config")
        @CommandPermission("unity.reload.config")
        public void onReloadConfig(CommandSender sender) {
        }
    }

    @Dependency
    private Messages enMessages;
}

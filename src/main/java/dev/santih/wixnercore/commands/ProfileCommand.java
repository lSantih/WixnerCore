package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import dev.santih.wixnercore.config.Messages;
import dev.santih.wixnercore.profile.ProfileManager;
import dev.santih.strike.util.Common;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

@CommandAlias("profile")
public class ProfileCommand extends BaseCommand {

    @Dependency
    private ProfileManager profileManager;

    @Subcommand("load")
    @CommandCompletion("@players")
    @Syntax("<jugador>")
    public void onLoad(CommandSender sender, String playerName) {
        Player player = Bukkit.getPlayer(playerName);
        if (player == null) {
            Common.tell(sender, Messages.INVALID_PLAYER.replace("%player%", playerName));
            return;
        }
        UUID uuid = player.getUniqueId();

        if (profileManager.getProfile(uuid) != null) {
            Common.tell(sender, Messages.PROFILE_ALREADY_LOADED.replace("%player%", playerName));
            return;
        }

        //profileManager.loadProfile(uuid);
        Common.tell(sender, Messages.PROFILE_LOAD_SUCCESS.replace("%player%", playerName));
    }

    @Subcommand("unload")
    @CommandCompletion("@players")
    @Syntax("<jugador>")
    public void onUnload(CommandSender sender, String playerName) {
        Player player = Bukkit.getPlayer(playerName);
        if (player == null) {
            Common.tell(sender, Messages.INVALID_PLAYER.replace("%player%", playerName));
            return;
        }
        UUID uuid = player.getUniqueId();

        if (profileManager.getProfile(uuid) == null) {
            Common.tell(sender, Messages.PROFILE_NOT_LOADED.replace("%player%", playerName));
            return;
        }

        //profileManager.unloadProfile(uuid);
        Common.tell(sender, Messages.PROFILE_UNLOAD_SUCCESS.replace("%player%", playerName));
    }

    @Subcommand("status")
    @CommandCompletion("@players")
    @Syntax("<jugador>")
    public void onStatus(CommandSender sender, String playerName) {
        Player player = Bukkit.getPlayer(playerName);
        if (player == null) {
            Common.tell(sender, Messages.INVALID_PLAYER.replace("%player%", playerName));
            return;
        }
        UUID uuid = player.getUniqueId();

        String message = (profileManager.getProfile(uuid) != null)
                ? Messages.PROFILE_LOADED_STATUS.replace("%player%", playerName)
                : Messages.PROFILE_NOT_LOADED_STATUS.replace("%player%", playerName);

        Common.tell(sender, message);
    }
}

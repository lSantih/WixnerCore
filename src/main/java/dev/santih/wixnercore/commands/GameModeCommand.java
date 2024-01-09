package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import dev.santih.wixnercore.config.Messages;
import dev.santih.strike.util.Common;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("gamemode|gm")
@Description("Change a player's game mode")
public class GameModeCommand extends BaseCommand {

    @Default
    @CommandCompletion("@gamemodes @players")
    @Syntax("<gamemode> [player]")
    public void onGameMode(CommandSender sender, @Single String gameModeStr, @Optional Player targetPlayer) {
        if (!sender.hasPermission("unitycore.gamemode")) {
            Common.tell(sender, Messages.GAMEMODE_PERMISSION);
            return;
        }

        if (targetPlayer == null) {
            if (sender instanceof Player) {
                targetPlayer = (Player) sender;
            } else {
                Common.tell(sender, Messages.GAMEMODE_NO_PLAYER);
                return;
            }
        }

        GameMode gameMode = parseGameMode(gameModeStr);
        if (gameMode == null) {
            Common.tell(sender, Messages.GAMEMODE_INVALID);
            return;
        }

        setGameMode(sender, targetPlayer, gameMode);
    }

    private GameMode parseGameMode(String gameModeStr) {
        switch (gameModeStr.toLowerCase()) {
            case "survival":
            case "0":
                return GameMode.SURVIVAL;
            case "creative":
            case "1":
                return GameMode.CREATIVE;
            case "adventure":
            case "2":
                return GameMode.ADVENTURE;
            case "spectator":
            case "3":
                return GameMode.SPECTATOR;
            default:
                return null;
        }
    }

    private void setGameMode(CommandSender sender, Player targetPlayer, GameMode gameMode) {
        if (!sender.hasPermission("unitycore.gamemode.others") && !sender.equals(targetPlayer)) {
            Common.tell(sender, Messages.GAMEMODE_OTHER_PERMISSION);
            return;
        }

        targetPlayer.setGameMode(gameMode);
        Common.tell(sender, Messages.GAMEMODE_SUCCESS
                .replace("%target_name%", targetPlayer.getName())
                .replace("%game_mode%", gameMode.name()));

        if (sender != targetPlayer) {
            Common.tell(targetPlayer, Messages.GAMEMODE_RECEIVED
                    .replace("%game_mode%", gameMode.name()));
        }
    }
}

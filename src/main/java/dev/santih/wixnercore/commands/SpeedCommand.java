package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import dev.santih.wixnercore.config.Messages;
import dev.santih.strike.util.Common;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("speed")
public class SpeedCommand extends BaseCommand {

    private static final int MIN_SPEED_INPUT = 1;
    private static final int MAX_SPEED_INPUT = 10;

    @Subcommand("set")
    @CommandCompletion("@players @nothing")
    @Syntax("<target> <speed>")
    public void onSet(CommandSender sender, @Optional String targetPlayerName, int speed) {
        Player targetPlayer = getTargetPlayer(sender, targetPlayerName);
        if (targetPlayer == null) {
            return;
        }

        if (speed < MIN_SPEED_INPUT || speed > MAX_SPEED_INPUT) {
            Common.tell(sender, Messages.SPEED_INVALID_RANGE);
            return;
        }

        float convertedSpeed = getRealMoveSpeed(speed, targetPlayer.isFlying());

        if (targetPlayer.isFlying()) {
            targetPlayer.setFlySpeed(convertedSpeed);
            sendMessage(sender, targetPlayer, Messages.SPEED_SET_FLY_SPEED.replace("%player%", targetPlayer.getName()).replace("%speed%", String.valueOf(speed)));
        } else {
            targetPlayer.setWalkSpeed(convertedSpeed);
            sendMessage(sender, targetPlayer, Messages.SPEED_SET_WALK_SPEED.replace("%player%", targetPlayer.getName()).replace("%speed%", String.valueOf(speed)));
        }
    }

    @Subcommand("view")
    @CommandCompletion("@players")
    @Syntax("<target>")
    public void onView(CommandSender sender, @Optional String targetPlayerName) {
        Player targetPlayer = getTargetPlayer(sender, targetPlayerName);
        if (targetPlayer == null) {
            return;
        }

        int walkSpeed = convertSpeed(targetPlayer.getWalkSpeed(), false);
        int flySpeed = convertSpeed(targetPlayer.getFlySpeed(), true);
        Common.tell(sender, Messages.SPEED_VIEW.replace("%player%", targetPlayer.getName()).replace("%walk_speed%", String.valueOf(walkSpeed)).replace("%fly_speed%", String.valueOf(flySpeed)));
    }

    private Player getTargetPlayer(CommandSender sender, String targetPlayerName) {
        if (targetPlayerName == null) {
            if (!(sender instanceof Player)) {
                Common.tell(sender, Messages.SPEED_CONSOLE_TARGET);
                return null;
            } else {
                return (Player) sender;
            }
        } else {
            Player targetPlayer = Bukkit.getPlayer(targetPlayerName);
            if (targetPlayer == null) {
                Common.tell(sender, Messages.INVALID_PLAYER);
                return null;
            }
            return targetPlayer;
        }
    }

    private float getRealMoveSpeed(final float userSpeed, final boolean isFly) {
        final float defaultSpeed = isFly ? 0.1f : 0.2f;
        float maxSpeed = 1f;

        if (userSpeed < 1f) {
            return defaultSpeed * userSpeed;
        } else {
            final float ratio = ((userSpeed - 1) / 9) * (maxSpeed - defaultSpeed);
            return ratio + defaultSpeed;
        }
    }

    private int convertSpeed(float speed, boolean isFly) {
        final float defaultSpeed = isFly ? 0.1f : 0.2f;
        float maxSpeed = 1f;

        if (speed <= defaultSpeed) {
            return Math.round(speed / defaultSpeed);
        } else {
            final float ratio = (speed - defaultSpeed) / (maxSpeed - defaultSpeed);
            return Math.round(ratio * 9 + 1);
        }
    }

    private void sendMessage(CommandSender sender, Player targetPlayer, String message) {
        Common.tell(sender, message);
        if (sender != targetPlayer) {
            Common.tell(targetPlayer, message.replace(targetPlayer.getName(), "You"));
        }
    }
}

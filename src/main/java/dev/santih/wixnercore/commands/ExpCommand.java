package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import dev.santih.wixnercore.config.Messages;
import dev.santih.strike.util.Common;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("exp")
public class ExpCommand extends BaseCommand {

    @Subcommand("add")
    @CommandCompletion("@players @nothing")
    @Syntax("<target> <amount>")
    public void onAdd(CommandSender sender, String targetPlayerName, String amount) {
        Player targetPlayer = Bukkit.getPlayer(targetPlayerName);
        if (targetPlayer == null) {
            Common.tell(sender, Messages.INVALID_PLAYER);
            return;
        }

        if (amount.toLowerCase().endsWith("l")) {
            int levels = Integer.parseInt(amount.substring(0, amount.length() - 1));
            targetPlayer.giveExpLevels(levels);
            Common.tell(sender, Messages.EXP_ADD_SUCCESS
                    .replace("%amount%", String.valueOf(levels))
                    .replace("%exp_type%", Messages.EXP_LEVELS)
                    .replace("%player_name%", targetPlayer.getName()));
            return;
        }

        int exp = Integer.parseInt(amount);
        targetPlayer.giveExp(exp);
        Common.tell(sender, Messages.EXP_ADD_SUCCESS
                .replace("%amount%", String.valueOf(exp))
                .replace("%exp_type%", Messages.EXP_POINTS)
                .replace("%player_name%", targetPlayer.getName()));
    }

    @Subcommand("remove")
    @CommandCompletion("@players @nothing")
    @Syntax("<target> <amount>")
    public void onRemove(CommandSender sender, String targetPlayerName, String amount) {
        Player targetPlayer = Bukkit.getPlayer(targetPlayerName);
        if (targetPlayer == null) {
            Common.tell(sender, Messages.INVALID_PLAYER);
            return;
        }

        if (amount.toLowerCase().endsWith("l")) {
            int levels = Integer.parseInt(amount.substring(0, amount.length() - 1));
            targetPlayer.giveExpLevels(-levels);
            Common.tell(sender, Messages.EXP_REMOVE_SUCCESS
                    .replace("%amount%", String.valueOf(levels))
                    .replace("%exp_type%", Messages.EXP_LEVELS)
                    .replace("%player_name%", targetPlayer.getName()));
            return;
        }

        int exp = Integer.parseInt(amount);
        targetPlayer.giveExp(-exp);
        Common.tell(sender, Messages.EXP_REMOVE_SUCCESS
                .replace("%amount%", String.valueOf(exp))
                .replace("%exp_type%", Messages.EXP_POINTS)
                .replace("%player_name%", targetPlayer.getName()));
    }

    @Subcommand("set")
    @CommandCompletion("@players @nothing")
    @Syntax("<target> <amount>")
    public void onSet(CommandSender sender, String targetPlayerName, String amount) {
        Player targetPlayer = Bukkit.getPlayer(targetPlayerName);
        if (targetPlayer == null) {
            Common.tell(sender, Messages.INVALID_PLAYER);
            return;
        }

        if (amount.toLowerCase().endsWith("l")) {
            int levels = Integer.parseInt(amount.substring(0, amount.length() - 1));
            targetPlayer.setLevel(levels);
            Common.tell(sender, Messages.EXP_SET_SUCCESS
                    .replace("%amount%", String.valueOf(levels))
                    .replace("%exp_type%", Messages.EXP_LEVELS)
                    .replace("%player_name%", targetPlayer.getName()));
            return;
        }

        int exp = Integer.parseInt(amount);
        int currentExp = getExpToLevel(targetPlayer.getLevel()) + Math.round(targetPlayer.getExpToLevel() * targetPlayer.getExp());
        int difference = exp - currentExp;
        targetPlayer.giveExp(difference);
        Common.tell(sender, Messages.EXP_SET_SUCCESS
                .replace("%amount%", String.valueOf(exp))
                .replace("%exp_type%", Messages.EXP_POINTS)
                .replace("%player_name%", targetPlayer.getName()));
    }

    @Subcommand("reset")
    @CommandCompletion("@players")
    @Syntax("<target>")
    public void onReset(CommandSender sender, String targetPlayerName) {
        Player targetPlayer = Bukkit.getPlayer(targetPlayerName);
        if (targetPlayer == null) {
            Common.tell(sender, Messages.INVALID_PLAYER);
            return;
        }

        targetPlayer.setExp(0);
        targetPlayer.setLevel(0);
        Common.tell(sender, Messages.EXP_RESET_SUCCESS.replaceAll("%player_name%", targetPlayer.getName()));
    }

    @Subcommand("view")
    @CommandCompletion("@players")
    @Syntax("<target>")
    public void onView(CommandSender sender, String targetPlayerName) {
        Player targetPlayer = Bukkit.getPlayer(targetPlayerName);
        if (targetPlayer == null) {
            Common.tell(sender, Messages.INVALID_PLAYER);
            return;
        }

        int exp = getExpToLevel(targetPlayer.getLevel()) + Math.round(targetPlayer.getExpToLevel() * targetPlayer.getExp());
        int level = targetPlayer.getLevel();
        Common.tell(sender, Messages.EXP_VIEW
                .replace("%exp_amount%", String.valueOf(exp))
                .replace("%level%", String.valueOf(level))
                .replace("%player_name%", targetPlayer.getName()));
    }
    private int getExpToLevel(int level) {
        if (level <= 15) {
            return 17 * level;
        } else if (level <= 30) {
            return (3 * level * level / 2) - (59 * level / 2) + 360;
        } else {
            return (7 * level * level / 2) - (303 * level / 2) + 2220;
        }
    }
}

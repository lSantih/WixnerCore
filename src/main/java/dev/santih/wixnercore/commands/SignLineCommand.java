package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Syntax;
import dev.santih.wixnercore.config.Messages;
import dev.santih.strike.util.Common;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import dev.santih.wixnercore.utils.MessageUtils;

@CommandAlias("signline")
@Description("Edit a sign line")
public class SignLineCommand extends BaseCommand {

    @Default
    @Syntax("<lineNumber> <text>")
    public void onSignLine(Player player, int lineNumber, String newText) {
        // Get the block the player is looking at
        if (player.getTargetBlockExact(5) != null && player.getTargetBlockExact(5).getState() instanceof Sign) {
            Sign sign = (Sign) player.getTargetBlockExact(5).getState();

            // Ensure the line number is valid (1-4)
            if (lineNumber < 1 || lineNumber > 4) {
                MessageUtils.sendCoreMessage(player, Messages.INVALID_LINE);
                return;
            }

            // Update the specified line on the sign
            sign.setLine(lineNumber - 1, Common.colorize(newText));
            sign.update();

            Common.tell(player, dev.santih.wixnercore.config.Messages.SIGN_UPDATED.replace("%line_number%", String.valueOf(lineNumber)));
        } else {
            MessageUtils.sendCoreMessage(player, Messages.NOT_LOOKING_AT_SIGN);
        }
    }
}

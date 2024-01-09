package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import dev.santih.strike.util.Common;
import org.bukkit.entity.Player;

@CommandAlias("chatformat|cf")
public class ChatFormatCommand extends BaseCommand {

	@Default
	@CommandPermission("unitycore.chatformat")
	@Syntax("<display>")
	@Description("Change your chat display format.")
	public void onChatFormat(Player sender, String display) {
		Common.tell(sender, display);
	}
}

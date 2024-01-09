package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import dev.santih.wixnercore.config.Messages;
import dev.santih.strike.util.Common;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

@CommandAlias("spawner")
public class SpawnerCommand extends BaseCommand {

    @Subcommand("set")
    @CommandCompletion("@entitytypes @players")
    @Syntax("<target>")
    public void onSpawnerSet(CommandSender sender, String entityType, @Optional String targetName) {
        Player targetPlayer = getTargetPlayer(sender, targetName);
        if (targetPlayer == null) {
            return;
        }

        Block block = targetPlayer.getTargetBlockExact(5);

        if (block == null || block.getType() != Material.SPAWNER) {
            Common.tell(sender, Messages.SPAWNER_NOT_LOOKING_AT_SPAWNER.replace("%s", targetPlayer.getName()));
            return;
        }

        EntityType type = EntityType.valueOf(entityType.toUpperCase());
        if (type == null || !type.isSpawnable()) {
            Common.tell(sender, Messages.SPAWNER_INVALID_ENTITY_TYPE);
            return;
        }

        CreatureSpawner spawner = (CreatureSpawner) block.getState();
        spawner.setSpawnedType(type);
        spawner.update();

        Common.tell(sender, Messages.SPAWNER_CHANGED_TYPE.replace("%type%", entityType.toLowerCase().replace("_", " ")).replace("%player_name", targetPlayer.getName()));

        if (targetPlayer != sender) {
            Common.tell(targetPlayer, Messages.SPAWNER_CHANGED_TYPE.replace("%type%", entityType.toLowerCase().replace("_", " ")).replace("%player_name", sender.getName()));
        }
    }

    @Subcommand("give")
    @CommandCompletion("@entitytypes @players")
    @Syntax("<entity> <target>")
    public void onSpawnerGive(CommandSender sender, String entityType, @Optional String targetName) {
        Player targetPlayer = getTargetPlayer(sender, targetName);
        if (targetPlayer == null) {
            return;
        }

        EntityType type = EntityType.valueOf(entityType.toUpperCase());
        if (type == null || !type.isSpawnable()) {
            Common.tell(sender, Messages.SPAWNER_INVALID_ENTITY_TYPE);
            return;
        }

        ItemStack item = new ItemStack(Material.SPAWNER);
        BlockStateMeta meta = (BlockStateMeta) item.getItemMeta();
        CreatureSpawner spawner = (CreatureSpawner) meta.getBlockState();
        spawner.setSpawnedType(type);
        spawner.update();

        meta.setBlockState(spawner);
        item.setItemMeta(meta);

        targetPlayer.getInventory().addItem(item);

        Common.tell(sender, Messages.SPAWNER_GIVEN.replace("%type%", entityType.toLowerCase().replace("_", " ")).replace("%player_name%", targetPlayer.getName()));

        if (targetPlayer != sender) {
            Common.tell(targetPlayer, Messages.SPAWNER_GIVEN.replace("%type%", entityType.toLowerCase().replace("_", " ")).replace("%player_name%", sender.getName()));
        }
    }

    @Subcommand("setspeed")
    @CommandCompletion("@players")
    @Syntax("<speed> <target>")
    public void onSpawnerSetSpeed(CommandSender sender, String speedString, @Optional String targetPlayerName) {
        Player targetPlayer = getTargetPlayer(sender, targetPlayerName);
        if (targetPlayer == null) {
            return;
        }

        Block block = targetPlayer.getTargetBlockExact(5);

        if (block == null || block.getType() != Material.SPAWNER) {
            Common.tell(sender, Messages.SPAWNER_NOT_LOOKING_AT_SPAWNER.replace("%player_name%", targetPlayer.getName()));
            return;
        }

        int speed;
        try {
            speed = Integer.parseInt(speedString.replace("s", ""));
        } catch (NumberFormatException e) {
            Common.tell(sender, Messages.INVALID_NUMBER);
            return;
        }

        int delay = 20 * speed;

        CreatureSpawner spawner = (CreatureSpawner) block.getState();
        spawner.setDelay(delay);
        spawner.update();

        Common.tell(sender, Messages.SPAWNER_CHANGED_SPEED.replace("%speed%", String.valueOf(speed)).replace("%player_name%", targetPlayer.getName()));

        if (targetPlayer != sender) {
            Common.tell(targetPlayer, Messages.SPAWNER_CHANGED_SPEED.replace("%speed%", String.valueOf(speed)).replace("%player_name%", sender.getName()));
        }
    }

    @Subcommand("setmaxnearbyentities")
    @CommandCompletion("@players")
    @Syntax("<maxNearbyEntities> <target>")
    public void onSpawnerSetMaxNearbyEntities(CommandSender sender, int maxNearbyEntities, @Optional String targetPlayerName) {
        Player targetPlayer = getTargetPlayer(sender, targetPlayerName);
        if (targetPlayer == null) {
            return;
        }

        Block block = targetPlayer.getTargetBlockExact(5);

        if (block == null || block.getType() != Material.SPAWNER) {
            Common.tell(sender, Messages.SPAWNER_NOT_LOOKING_AT_SPAWNER.replace("%player_name%", targetPlayer.getName()));
            return;
        }

        CreatureSpawner spawner = (CreatureSpawner) block.getState();
        spawner.setMaxNearbyEntities(maxNearbyEntities);
        spawner.update();

        Common.tell(sender, Messages.SPAWNER_CHANGED_MAX_NEARBY_ENTITIES.replace("%entities%", String.valueOf(maxNearbyEntities)).replace("%player_name%", targetPlayer.getName()));

        if (targetPlayer != sender) {
            Common.tell(targetPlayer, Messages.SPAWNER_CHANGED_MAX_NEARBY_ENTITIES.replace("%entities", String.valueOf(maxNearbyEntities)).replace("%player_name%", sender.getName()));
        }
    }

    @Subcommand("setrequiredplayerrange")
    @CommandCompletion("@players")
    @Syntax("<range> <target>")
    public void onSpawnerSetRequiredPlayerRange(CommandSender sender, int requiredPlayerRange, @Optional String targetPlayerName) {
        Player targetPlayer = getTargetPlayer(sender, targetPlayerName);
        if (targetPlayer == null) {
            return;
        }

        Block block = targetPlayer.getTargetBlockExact(5);

        if (block == null || block.getType() != Material.SPAWNER) {
            Common.tell(sender, Messages.SPAWNER_NOT_LOOKING_AT_SPAWNER.replace("%player_name%", targetPlayer.getName()));
            return;
        }

        CreatureSpawner spawner = (CreatureSpawner) block.getState();
        spawner.setRequiredPlayerRange(requiredPlayerRange);
        spawner.update();

        Common.tell(sender, Messages.SPAWNER_CHANGED_REQUIRED_PLAYER_RANGE.replace("%player_name%", targetPlayer.getName())
                .replace("%range%", String.valueOf(requiredPlayerRange)));

        if (targetPlayer != sender) {
            Common.tell(targetPlayer, Messages.SPAWNER_CHANGED_REQUIRED_PLAYER_RANGE.replace("%player_name%", targetPlayer.getName())
                    .replace("%range%", String.valueOf(requiredPlayerRange)));
        }
    }

    @Subcommand("setspawnrange")
    @CommandCompletion("@players")
    @Syntax("<range> <player>")
    public void onSpawnerSetSpawnRange(CommandSender sender, int spawnRange, @Optional String targetPlayerName) {
        Player targetPlayer = getTargetPlayer(sender, targetPlayerName);
        if (targetPlayer == null) {
            return;
        }

        Block block = targetPlayer.getTargetBlockExact(5);

        if (block == null || block.getType() != Material.SPAWNER) {
            Common.tell(sender, Messages.SPAWNER_NOT_LOOKING_AT_SPAWNER.replace("%player_name%", targetPlayer.getName()));
            return;
        }

        CreatureSpawner spawner = (CreatureSpawner) block.getState();
        spawner.setSpawnRange(spawnRange);
        spawner.update();

        Common.tell(sender, Messages.SPAWNER_CHANGED_SPAWN_RANGE.replace("%player_name%", targetPlayer.getName())
                .replace("%range%", String.valueOf(spawnRange)));

        if (targetPlayer != sender) {
            Common.tell(targetPlayer, Messages.SPAWNER_CHANGED_SPAWN_RANGE.replace("%player_name%", targetPlayer.getName())
                    .replace("%range%", String.valueOf(spawnRange)));
        }
    }

    @Subcommand("setmaxdelay")
    @CommandCompletion("@players")
    @Syntax("<delay> <target>")
    public void onSpawnerSetMaxDelay(CommandSender sender, int maxDelay, @Optional String targetPlayerName) {
        Player targetPlayer = getTargetPlayer(sender, targetPlayerName);
        if (targetPlayer == null) {
            return;
        }

        Block block = targetPlayer.getTargetBlockExact(5);

        if (block == null || block.getType() != Material.SPAWNER) {
            Common.tell(sender, Messages.SPAWNER_NOT_LOOKING_AT_SPAWNER.replace("%player_name%", targetPlayer.getName()));
            return;
        }

        CreatureSpawner spawner = (CreatureSpawner) block.getState();
        spawner.setMaxSpawnDelay(maxDelay);
        spawner.update();

        Common.tell(sender, Messages.SPAWNER_CHANGED_MAX_DELAY.replace("%player_name%", targetPlayer.getName())
                .replace("%delay%", String.valueOf(maxDelay)));

        if (targetPlayer != sender) {
            Common.tell(targetPlayer, Messages.SPAWNER_CHANGED_MAX_DELAY.replace("%player_name%", targetPlayer.getName())
                    .replace("%delay%", String.valueOf(maxDelay)));
        }
    }

    @Subcommand("setmindelay")
    @CommandCompletion("@players")
    @Syntax("<delay> <target>")
    public void onSpawnerSetMinDelay(CommandSender sender, int minDelay, @Optional String targetPlayerName) {
        Player targetPlayer = getTargetPlayer(sender, targetPlayerName);
        if (targetPlayer == null) {
            return;
        }

        Block block = targetPlayer.getTargetBlockExact(5);

        if (block == null || block.getType() != Material.SPAWNER) {
            Common.tell(sender, Messages.SPAWNER_NOT_LOOKING_AT_SPAWNER.replace("%player_name%", targetPlayer.getName()));
            return;
        }

        CreatureSpawner spawner = (CreatureSpawner) block.getState();
        spawner.setMinSpawnDelay(minDelay);
        spawner.update();

        Common.tell(sender, Messages.SPAWNER_CHANGED_MIN_DELAY.replace("%player_name%", targetPlayer.getName())
                .replace("%delay%", String.valueOf(minDelay)));

        if (targetPlayer != sender) {
            Common.tell(targetPlayer, Messages.SPAWNER_CHANGED_MIN_DELAY.replace("%player_name%", targetPlayer.getName())
                    .replace("%delay%", String.valueOf(minDelay)));
        }
    }

    private Player getTargetPlayer(CommandSender sender, String targetPlayerName) {
        if (targetPlayerName == null) {
            if (!(sender instanceof Player)) {
                Common.tell(sender, Messages.INVALID_PLAYER);
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
}

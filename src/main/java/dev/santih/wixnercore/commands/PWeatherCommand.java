package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import dev.santih.wixnercore.utils.MessageUtils;
import org.bukkit.entity.Player;

@CommandAlias("pweather")
public class PWeatherCommand extends BaseCommand {

    @Default
    @Syntax("<weather>")
    @CommandCompletion("clear|rain|storm|reset")
    public void onPWeather(Player player, String weather) {
        if (setWeather(player, weather)) {
            MessageUtils.sendCoreMessage(player, "&aSet the weather to " + weather + ".");
        } else {
            MessageUtils.sendCoreMessage(player, "&cInvalid weather type. Use 'clear', 'rain', or 'storm'.");
        }
    }

    private boolean setWeather(Player player, String weather) {
        if (weather.equalsIgnoreCase("clear")) {
            player.setPlayerWeather(org.bukkit.WeatherType.CLEAR);
            return true;
        } else if (weather.equalsIgnoreCase("rain")) {
            player.setPlayerWeather(org.bukkit.WeatherType.DOWNFALL);
            return true;
        } else if (weather.equalsIgnoreCase("storm")) {
            player.setPlayerWeather(org.bukkit.WeatherType.DOWNFALL);
            return true;
        }else if(weather.equalsIgnoreCase("reset")) {
            player.resetPlayerWeather();
            return true;
        }
        return false;
    }
}

package dev.santih.wixnercore.profile.model;


import dev.morphia.annotations.Entity;
import dev.santih.wixnercore.profile.model.settings.impl.MessagesSetting;
import dev.santih.wixnercore.profile.model.settings.impl.TpaSetting;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Settings {
	@Getter
	private MessagesSetting messageSetting = new MessagesSetting();

	@Getter
	private TpaSetting tpaSetting = new TpaSetting();
}

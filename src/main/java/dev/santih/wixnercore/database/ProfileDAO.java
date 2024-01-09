package dev.santih.wixnercore.database;

import dev.morphia.Datastore;
import dev.santih.wixnercore.profile.IProfile;
import dev.santih.wixnercore.profile.Profile;
import org.jetbrains.annotations.NotNull;

public class ProfileDAO extends AbstractDAO<IProfile> {
	public ProfileDAO(@NotNull Datastore datastore) {
		super(datastore);
	}

	@Override
	protected Class<IProfile> getEntityClass() {
		return IProfile.class;
	}
}

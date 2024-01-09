package dev.santih.wixnercore.profile.model;

import dev.morphia.annotations.Entity;

import java.util.UUID;

@Entity
public class TeleportRequest {
    private UUID requester;
    private UUID target;

    public TeleportRequest(UUID requester, UUID target) {
        this.requester = requester;
        this.target = target;
    }

    public UUID getRequester() {
        return requester;
    }

    public UUID getTarget() {
        return target;
    }
}
package com.cheatbreaker.client.audio;

import com.cheatbreaker.bridge.client.multiplayer.WorldClientBridge;
import com.cheatbreaker.bridge.entity.EntityBridge;
import com.cheatbreaker.bridge.entity.player.EntityPlayerBridge;

import javax.vecmath.Vector3f;
import java.util.UUID;

public class PlayerProxy
{
    private EntityPlayerBridge player;
    private double x;
    private double y;
    private double z;
    private String entityName;
    public boolean usesEntity;

    public PlayerProxy(final EntityPlayerBridge player, final UUID uniqueId, final String name, final double x, final double y, final double z) {
        this.player = player;
        this.entityName = name;
        this.x = x;
        this.y = y;
        this.z = z;
        this.usesEntity = (player != null);
    }

    public String entityName() {
        return (this.entityName != null) ? this.entityName : this.player.bridge$getCommandSenderName();
    }

    public EntityBridge getPlayer() {
        return this.player;
    }

    public Vector3f position() {
        return (this.player != null) ? (this.usesEntity ? new Vector3f((float)this.player.bridge$getPosX(), (float)this.player.bridge$getPosY(), (float)this.player.bridge$getPosZ()) : new Vector3f((float)this.x, (float)this.y, (float)this.z)) : new Vector3f((float)this.x, (float)this.y, (float)this.z);
    }

    public void setName(final String name) {
        this.entityName = name;
    }

    public void setPlayer(final EntityPlayerBridge entity) {
        this.player = entity;
        this.usesEntity = true;
    }

    public void setPosition(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return "PlayerProxy[" + this.entityName + ": " + this.x + ", " + this.y + "," + this.z + "]";
    }

    public void update(final WorldClientBridge world) {
        if (world != null) {
            this.player = world.bridge$getPlayerEntityByName(entityName());
            this.usesEntity = (this.player != null);
        }
    }
}

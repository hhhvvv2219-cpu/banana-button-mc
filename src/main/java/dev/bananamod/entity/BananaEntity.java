package dev.bananamod.entity;

import dev.bananamod.BananaMod;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrownEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class BananaEntity extends ThrownEntity {

    public static final int VOLLEY_SIZE = 10;
    private static final float SPREAD = 7.0f;

    public BananaEntity(EntityType<? extends BananaEntity> type, World world) {
        super(type, world);
    }

    public BananaEntity(EntityType<? extends BananaEntity> type, LivingEntity owner, World world) {
        super(type, owner, world);
    }

    public static void volley(LivingEntity owner, float speed) {
        World world = owner.getWorld();
        if (world.isClient()) {
            return;
        }
        for (int i = 0; i < VOLLEY_SIZE; i++) {
            float pitch = owner.getPitch() + (world.random.nextFloat() - 0.5f) * SPREAD * 2.0f;
            float yaw = owner.getYaw() + (world.random.nextFloat() - 0.5f) * SPREAD * 2.0f;
            Vec3d direction = owner.getRotationVector(pitch, yaw).normalize();
            BananaEntity banana = new BananaEntity(BananaMod.BANANA, owner, world);
            banana.setPosition(owner.getX(), owner.getEyeY() - 0.15, owner.getZ());
            banana.setVelocity(direction.multiply(speed + world.random.nextDouble() * 0.3));
            banana.setYaw(yaw);
            banana.setPitch(pitch);
            world.spawnEntity(banana);
        }
    }

    @Override
    protected void initDataTracker() {
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().isClient() && this.age % 2 == 0) {
            this.getWorld().addParticle(ParticleTypes.CRIT, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
        }
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient()) {
            this.getWorld().sendEntityStatus(this, (byte) 3);
            this.discard();
        }
    }

    @Override
    public void handleStatus(byte status) {
        if (status == 3) {
            for (int i = 0; i < 10; i++) {
                this.getWorld().addParticle(ParticleTypes.CRIT, this.getX(), this.getY(), this.getZ(),
                        (this.random.nextDouble() - 0.5) * 0.4, this.random.nextDouble() * 0.3,
                        (this.random.nextDouble() - 0.5) * 0.4);
            }
        }
    }
}

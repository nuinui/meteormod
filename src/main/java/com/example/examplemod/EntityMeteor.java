package com.example.examplemod;

import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityMeteor extends EntityMob {

    private boolean isGoneToHell = false;
    private int age = 0;
    private static final int ALIVE_TIME = 150;
    private static final int MAX_DEPTH = 10;
    private double MOTION_X = (Math.random() - 0.5D) / 2.0D;
    private double MOTION_Z = (Math.random() - 0.5D) / 2.0D;

    public EntityMeteor(World world) {
        super(world);
        this.tasks.addTask(1, new EntityAIWander(this, 0.8D));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        System.out.println("spawned!");
    }

    @Override
    public boolean isAIEnabled() {
        return true;
    }

    @Override
    public void onUpdate() {
        motionY = -1.0D;
        motionX = MOTION_X;
        motionZ = MOTION_Z;
        if (isEntityAlive()) {
            if (age < 0) {
                age = 0;
            } else if (age >= ALIVE_TIME) {
                age = ALIVE_TIME;
                explode();
            }

            age++;
        }

        super.onUpdate();
    }

    @Override
    public String getLivingSound() {
        if (!this.isGoneToHell) {
            this.setPosition(posX, posY + 64, posZ);
            for (int i = 0; i < 3 && posY < 128; i++) {
                this.moveEntity(0.0D, 64.0D, 0.0D);
            }
            isGoneToHell = true;
            this.setFire(600);
        }
        return "mob.creeper.say";
    }

    @Override
    public String getHurtSound() {
        return "mob.creeper.say";
    }

    @Override
    protected String getDeathSound() {
        return "mob.creeper.death";
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEFINED;
    }

    @Override
    public Item getDropItem() {
        return MyItems.fallen_star;
    }


    @Override
    public void onDeath(DamageSource source) {
        super.onDeath(source);
        explode();
    }

    private void explode() {
        if (!this.worldObj.isRemote) {
            float power = 5;
            boolean block_break = true;
            this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, power, block_break);
            fire(this.worldObj, (int) posX, (int) posY + 2, (int) posZ, 0);
        }
        this.worldObj.spawnParticle("smoke", this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);
        if (!worldObj.isRemote) {
            setDead();
        }
        System.out.println("explosion!");
    }

    private void fire(World world, int x, int y, int z, int depth) {
        if (depth > MAX_DEPTH) {
            return;
        }

        if (world.isAirBlock(x, y, z)) {
            fire(world, x + randomDelta(), y - 1, z + randomDelta(), depth + 1);
            fire(world, x + randomDelta(), y - 1, z + randomDelta(), depth + 1);
        } else {
            world.setBlock(x, y + 1, z, MyBlocks.imaginary_fire);
        }
    }

    private int randomDelta() {
        return (int) (Math.random() * 5) - 2;
    }


}

package com.example.examplemod;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.util.EnumHelper;

/**
 * Created by nui on 16/02/23.
 */
public class ItemStarSword extends ItemSword {
    public ItemStarSword() {
        super(EnumHelper.addToolMaterial("StarSword", 4, 2000, 16.0f, 1.0f, 22));
        setUnlocalizedName(ExampleMod.MODID + "_StarSword");
        setTextureName(ExampleMod.MODID + ":StarSword");
    }

    @Override
    public boolean hitEntity(ItemStack item, EntityLivingBase enemy, EntityLivingBase attacker) {
        if (item == null) {
            return true;
        }

        if (!(attacker instanceof EntityPlayer)) {
            return true;
        }

        enemy.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 1200, 1));
        enemy.setFire(600);
        return true;
    }
}

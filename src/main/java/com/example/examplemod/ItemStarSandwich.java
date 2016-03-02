package com.example.examplemod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

/**
 * Created by nui on 16/02/23.
 */
public class ItemStarSandwich extends ItemFood {
    private PotionEffect[] effects = {
            new PotionEffect(Potion.regeneration.id, 1200, 1),
            new PotionEffect(Potion.damageBoost.id, 1200, 1),
            new PotionEffect(Potion.moveSpeed.id, 1200, 1),
            new PotionEffect(Potion.jump.id, 600, 0)
    };

    private static final int HEAL_AMOUNT = 1;
    private static final float SATURAION_MODFIRE = 0.5f;
    private static final boolean WOVES_FAVOURITE = false;


    public ItemStarSandwich() {
        super(HEAL_AMOUNT, SATURAION_MODFIRE, WOVES_FAVOURITE);
        this.setTextureName(ExampleMod.MODID + ":StarSandwich");
        this.setUnlocalizedName(ExampleMod.MODID + "_StarSandwich");
        this.setCreativeTab(CreativeTabs.tabFood);
        setMaxStackSize(64);
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
        super.onFoodEaten(stack, world, player);

        for (int i = 0; i < effects.length; i++) {
            if (!world.isRemote && effects[i] != null && effects[i].getPotionID() > 0) {
                player.addPotionEffect(new PotionEffect(this.effects[i].getPotionID(),
                        this.effects[i].getDuration(), this.effects[i].getAmplifier(), this.effects[i].getIsAmbient()));
            }
        }
    }
}

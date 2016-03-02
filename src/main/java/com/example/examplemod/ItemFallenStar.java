package com.example.examplemod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by nui on 16/02/21.
 */
public class ItemFallenStar extends Item {
    public ItemFallenStar() {
        super();
        this.setTextureName(ExampleMod.MODID + ":FallenStar");
        this.setUnlocalizedName(ExampleMod.MODID + "_FallenStar");
        this.setCreativeTab(CreativeTabs.tabMaterials);
        setMaxStackSize(64);
    }
}

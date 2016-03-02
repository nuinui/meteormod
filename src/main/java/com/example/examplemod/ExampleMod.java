package com.example.examplemod;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.EnumCreatureType;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;

import java.util.Arrays;


@Mod(modid = ExampleMod.MODID, version = ExampleMod.VERSION)
public class ExampleMod
{
    public static final String MODID = "examplemod";
    public static final String VERSION = "1.0";

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        MyBlocks.star_lighting = new BlockStarLighting();
        MyBlocks.imaginary_fire = new BlockImaginaryFire();

        MyItems.fallen_star = new ItemFallenStar();
        MyItems.star_sword = new ItemStarSword();
        MyItems.star_sandwich = new ItemStarSandwich().setAlwaysEdible();

        GameRegistry.registerBlock(MyBlocks.star_lighting, "star_lighting");
        GameRegistry.registerBlock(MyBlocks.imaginary_fire, "imaginary_fire");

        GameRegistry.registerItem(MyItems.fallen_star, "fallen_star");
        GameRegistry.registerItem(MyItems.star_sword, "star_sword");
        GameRegistry.registerItem(MyItems.star_sandwich, "star_sandwich");

        GameRegistry.addRecipe(new ItemStack(MyItems.star_sword),
                "A",
                "A",
                "B",
                'A', MyItems.fallen_star,
                'B', Items.stick);

        GameRegistry.addRecipe(new ItemStack(MyItems.star_sandwich),
                "A",
                "B",
                'A', MyItems.fallen_star,
                'B', Items.bread);

        GameRegistry.addRecipe(new ItemStack(MyBlocks.star_lighting),
                "A",
                "B",
                'A', MyItems.fallen_star,
                'B', Blocks.torch);

        EntityRegistry.registerGlobalEntityID(EntityMeteor.class, "EntityMeteor", EntityRegistry.findGlobalUniqueEntityId(), 0x990000, 0x110000);

        RenderingRegistry.registerEntityRenderingHandler(EntityMeteor.class, new RenderMeteor());

        BiomeGenBase[] allBiome = Arrays.stream(BiomeGenBase.getBiomeGenArray())
                .filter(biome -> biome != null)
                .toArray(BiomeGenBase[]::new);

        EntityRegistry.addSpawn(EntityMeteor.class, 32, 3, 10, EnumCreatureType.monster, allBiome);

    }
}

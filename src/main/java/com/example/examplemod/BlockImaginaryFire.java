package com.example.examplemod;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockFire;
import net.minecraft.block.material.MapColor;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

import static net.minecraftforge.common.util.ForgeDirection.*;

/**
 * Created by nui on 16/02/24.
 */
public class BlockImaginaryFire extends BlockFire {

    @SideOnly(Side.CLIENT)
    private IIcon[] icon;

    protected BlockImaginaryFire() {
        super();
        setCreativeTab(CreativeTabs.tabBlock);
        setBlockName(ExampleMod.MODID + "_imaginary_fire");
        setBlockTextureName(ExampleMod.MODID + ":imaginary_fire");
        this.setTickRandomly(true);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return null;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int getRenderType() {
        return 3;
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {
        if (random.nextInt() % 2 == 0) {
            world.setBlockToAir(x,y,z);
        }
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        return 15;
    }

    @Override
    public boolean isCollidable() {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        if (random.nextInt(24) == 0) {
            world.playSound((double) ((float) x + 0.5F), (double) ((float) y + 0.5F), (double) ((float) z + 0.5F), "fire.fire", 1.0F + random.nextFloat(), random.nextFloat() * 0.7F + 0.3F, false);
        }

        int l;
        float f;
        float f1;
        float f2;

        if (!World.doesBlockHaveSolidTopSurface(world, x, y - 1, z) && !Blocks.fire.canCatchFire(world, x, y - 1, z, UP)) {
            if (Blocks.fire.canCatchFire(world, x - 1, y, z, EAST)) {
                for (l = 0; l < 2; ++l) {
                    f = (float) x + random.nextFloat() * 0.1F;
                    f1 = (float) y + random.nextFloat();
                    f2 = (float) z + random.nextFloat();
                    world.spawnParticle("largesmoke", (double) f, (double) f1, (double) f2, 0.0D, 0.0D, 0.0D);
                }
            }

            if (Blocks.fire.canCatchFire(world, x + 1, y, z, WEST)) {
                for (l = 0; l < 2; ++l) {
                    f = (float) (x + 1) - random.nextFloat() * 0.1F;
                    f1 = (float) y + random.nextFloat();
                    f2 = (float) z + random.nextFloat();
                    world.spawnParticle("largesmoke", (double) f, (double) f1, (double) f2, 0.0D, 0.0D, 0.0D);
                }
            }

            if (Blocks.fire.canCatchFire(world, x, y, z - 1, SOUTH)) {
                for (l = 0; l < 2; ++l) {
                    f = (float) x + random.nextFloat();
                    f1 = (float) y + random.nextFloat();
                    f2 = (float) z + random.nextFloat() * 0.1F;
                    world.spawnParticle("largesmoke", (double) f, (double) f1, (double) f2, 0.0D, 0.0D, 0.0D);
                }
            }

            if (Blocks.fire.canCatchFire(world, x, y, z + 1, NORTH)) {
                for (l = 0; l < 2; ++l) {
                    f = (float) x + random.nextFloat();
                    f1 = (float) y + random.nextFloat();
                    f2 = (float) (z + 1) - random.nextFloat() * 0.1F;
                    world.spawnParticle("largesmoke", (double) f, (double) f1, (double) f2, 0.0D, 0.0D, 0.0D);
                }
            }

            if (Blocks.fire.canCatchFire(world, x, y + 1, z, DOWN)) {
                for (l = 0; l < 2; ++l) {
                    f = (float) x + random.nextFloat();
                    f1 = (float) (y + 1) - random.nextFloat() * 0.1F;
                    f2 = (float) z + random.nextFloat();
                    world.spawnParticle("largesmoke", (double) f, (double) f1, (double) f2, 0.0D, 0.0D, 0.0D);
                }
            }
        } else {
            for (l = 0; l < 3; ++l) {
                f = (float) x + random.nextFloat();
                f1 = (float) y + random.nextFloat() * 0.5F + 0.5F;
                f2 = (float) z + random.nextFloat();
                world.spawnParticle("largesmoke", (double) f, (double) f1, (double) f2, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        this.icon = new IIcon[]{register.registerIcon(this.getTextureName() + "_layer_0"), register.registerIcon(this.getTextureName() + "_layer_1")};
    }

    @SideOnly(Side.CLIENT)
    public IIcon getFireIcon(int p_149840_1_) {
        return this.icon[p_149840_1_];
    }


    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
        return this.icon[0];
    }

    public MapColor getMapColor(int p_149728_1_) {
        return MapColor.tntColor;
    }
}

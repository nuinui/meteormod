package com.example.examplemod;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

/**
 * Created by nui on 16/02/18.
 */
public class RenderMeteor extends RenderLiving {
    public static final ResourceLocation texture = new ResourceLocation(ExampleMod.MODID + ":textures/entity/meteor.png");

    public RenderMeteor() {
        super(new ModelMeteor(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return texture;
    }
}

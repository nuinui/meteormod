package com.example.examplemod;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Created by nui on 16/02/18.
 */

@SideOnly(Side.CLIENT)
public class ModelMeteor extends ModelBase {
    ModelRenderer[] box1 = new ModelRenderer[8];
    ModelRenderer box2;

    public ModelMeteor() {
        for (int i = 0; i < this.box1.length; ++i) {
            byte b0 = 0;
            int j = i;

            if (i == 2) {
                b0 = 24;
                j = 10;
            } else if (i == 3) {
                b0 = 24;
                j = 19;
            }

            this.box1[i] = new ModelRenderer(this, b0, j);
            this.box1[i].addBox(-4.0F, (float)(16 + i), -4.0F, 8, 1, 8);
        }

        this.box2 = new ModelRenderer(this, 0, 16);
        this.box2.addBox(-2.0F, 18.0F, -2.0F, 4, 4, 4);
    }

    @Override
    public void render(Entity entity, float f1, float f2, float f3, float f4, float f5, float f6) {
        this.setRotationAngles(f1, f2, f3, f4, f5, f6, entity);
        this.box2.render(f6);

        for (int i = 0; i < this.box1.length; i++) {
            this.box1[i].render(f6);
        }
    }
}
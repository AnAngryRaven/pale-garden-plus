package com.guhcat.raven.pale_garden_plus.client.mixins;

import com.guhcat.raven.pale_garden_plus.client.PaleGardenFogModifier;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.render.fog.FogModifier;
import net.minecraft.client.render.fog.FogRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

import java.util.ArrayList;

@Mixin(FogRenderer.class)
public abstract class ThickerFog {

    @ModifyExpressionValue(method = "<clinit>", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/Lists;newArrayList([Ljava/lang/Object;)Ljava/util/ArrayList;"))
    private static ArrayList<FogModifier> addPaleGardenFogModifier(ArrayList<FogModifier> original) {
        original.add(6, new PaleGardenFogModifier());
        //System.out.println(original);
        return original;
    }
}

package com.guhcat.raven.pale_garden_plus.client.mixins;

import com.google.common.collect.Lists;
import com.guhcat.raven.pale_garden_plus.client.PaleGardenFogModifier;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.render.fog.AtmosphericFogModifier;
import net.minecraft.client.render.fog.FogModifier;
import net.minecraft.client.render.fog.FogRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

import java.util.ArrayList;

@Mixin(FogRenderer.class)
public abstract class ThickerFog {
/*
    @Shadow
    private float fogMultiplier;

    @Shadow


    @Inject(method = "applyStartEndModifier", at = @At(value = "INVOKE", target = "net/minecraft/client/world/ClientWorld.getRainGradient (F)F"))
    private void guh(FogData data, Entity cameraEntity, BlockPos cameraPos, ClientWorld world, float viewDistance, RenderTickCounter tickCounter, CallbackInfo ci, @Local Biome biome){
        //System.out.println(world.getBiome(cameraPos));
        if(world.getBiome(cameraPos).matchesKey(BiomeKeys.PALE_GARDEN)){
            this.fogMultiplier = 1.5f;
        }
    }*/

    @ModifyExpressionValue(method = "<clinit>", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/Lists;newArrayList([Ljava/lang/Object;)Ljava/util/ArrayList;"))
    private static ArrayList<FogModifier> addPaleGardenFogModifier(ArrayList<FogModifier> original) {
        original.add(6, new PaleGardenFogModifier());
        //System.out.println(original);
        return original;
    }
}

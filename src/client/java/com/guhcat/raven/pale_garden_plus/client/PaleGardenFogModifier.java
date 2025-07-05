package com.guhcat.raven.pale_garden_plus.client;

import net.minecraft.block.enums.CameraSubmersionType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.render.fog.AtmosphericFogModifier;
import net.minecraft.client.render.fog.DarknessEffectFogModifier;
import net.minecraft.client.render.fog.FogData;
import net.minecraft.client.render.fog.StandardFogModifier;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.BiomeKeys;
import org.jetbrains.annotations.Nullable;

public class PaleGardenFogModifier extends StandardFogModifier {
    private static final int MULT_MAX = 20;
    private float mult = 5;

    @Override
    public void applyStartEndModifier(FogData data, Entity cameraEntity, BlockPos cameraPos, ClientWorld world, float viewDistance, RenderTickCounter tickCounter) {
        AtmosphericFogModifier atmosFog = new AtmosphericFogModifier();
        atmosFog.applyStartEndModifier(data, cameraEntity, cameraPos, world, viewDistance, tickCounter);

        if(cameraEntity.getWorld().getBiome(cameraEntity.getBlockPos()).matchesKey(BiomeKeys.PALE_GARDEN)){
            mult = Math.clamp((mult - 0.05F), 1, MULT_MAX);
        }else{
            mult = Math.clamp((mult + 0.05F), 1, MULT_MAX);
        }

        data.environmentalStart = 7 * mult;
        data.environmentalEnd = 30 * mult;
    }

    @Override
    public float applyDarknessModifier(LivingEntity cameraEntity, float darkness, float tickProgress) {
        return 0.2F * Math.min(((mult * -0.25F)+1.25F), 1);
    }

    @Override
    public boolean isDarknessModifier() {
        return true;
    }

    @Override
    public boolean shouldApply(@Nullable CameraSubmersionType submersionType, Entity cameraEntity) {
        boolean bl = cameraEntity.getWorld().getBiome(cameraEntity.getBlockPos()).matchesKey(BiomeKeys.PALE_GARDEN);

        return bl || mult < MULT_MAX;
    }
}

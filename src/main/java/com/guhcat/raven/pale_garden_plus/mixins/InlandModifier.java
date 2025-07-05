package com.guhcat.raven.pale_garden_plus.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import net.minecraft.world.biome.source.util.VanillaBiomeParameters;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(VanillaBiomeParameters.class)
public abstract class InlandModifier {

    @WrapOperation(method = "writeHighBiomes", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/biome/source/util/VanillaBiomeParameters;getBadlandsOrRegularBiome(IILnet/minecraft/world/biome/source/util/MultiNoiseUtil$ParameterRange;)Lnet/minecraft/registry/RegistryKey;"))
    private RegistryKey<Biome> invoke(VanillaBiomeParameters instance, int temperature, int humidity, MultiNoiseUtil.ParameterRange weirdness, Operation<RegistryKey<Biome>> original){
        if(temperature == 2 && humidity == 4){
            return BiomeKeys.PALE_GARDEN;
        }
        return original.call(instance, temperature, humidity, weirdness);
    }
}

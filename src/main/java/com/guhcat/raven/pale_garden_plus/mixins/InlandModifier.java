package com.guhcat.raven.pale_garden_plus.mixins;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.mojang.datafixers.util.Pair;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import net.minecraft.world.biome.source.util.VanillaBiomeParameters;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Consumer;

import static com.ibm.icu.text.PluralRules.Operand.j;

@Mixin(VanillaBiomeParameters.class)
public abstract class InlandModifier {

    @Shadow @Final private MultiNoiseUtil.ParameterRange[] temperatureParameters;

    @Shadow @Final private MultiNoiseUtil.ParameterRange[] humidityParameters;

    @Shadow protected abstract void writeBiomeParameters(Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> parameters, MultiNoiseUtil.ParameterRange temperature, MultiNoiseUtil.ParameterRange humidity, MultiNoiseUtil.ParameterRange continentalness, MultiNoiseUtil.ParameterRange erosion, MultiNoiseUtil.ParameterRange weirdness, float offset, RegistryKey<Biome> biome);

    @Shadow @Final private MultiNoiseUtil.ParameterRange midInlandContinentalness;

    @Shadow @Final private MultiNoiseUtil.ParameterRange[] erosionParameters;

    @Shadow protected abstract RegistryKey<Biome> getRegularBiome(int temperature, int humidity, MultiNoiseUtil.ParameterRange weirdness);

    private static final RegistryKey<Biome>[][] paleGarden = new RegistryKey[][]{
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, BiomeKeys.PALE_GARDEN},
            {null, null, null, null, null},
            {null, null, null, null, null}
    };

    /*
    @ModifyExpressionValue(method="<init>", at = @At(value = "FIELD", target = "Lnet/minecraft/world/biome/BiomeKeys;MEADOW:Lnet/minecraft/registry/RegistryKey;", ordinal = 5))
    private RegistryKey<Biome> guh(RegistryKey<Biome> original){
        return BiomeKeys.PALE_GARDEN;
    }*/

    /*
    @Inject(method = "writeHighBiomes", at = @At(value = "HEAD"))
    public void addAnother(Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> parameters, MultiNoiseUtil.ParameterRange weirdness, CallbackInfo ci){
        for(int t = 0; t < this.temperatureParameters.length; t++){
            MultiNoiseUtil.ParameterRange tempParameterRange = this.temperatureParameters[t];

            for(int h = 0; h < this.humidityParameters.length; h++) {
                MultiNoiseUtil.ParameterRange humidityParameter = this.humidityParameters[h];
                RegistryKey<Biome> paleGardenKey = this.getPaleGarden(t, h, weirdness);
                this.writeBiomeParameters(parameters, tempParameterRange, humidityParameter, this.midInlandContinentalness, this.erosionParameters[2], weirdness, 0.0F, paleGardenKey);
            }
        }

    }

    private RegistryKey<Biome> getPaleGarden(int temp, int humidity, MultiNoiseUtil.ParameterRange weirdness) {
        RegistryKey<Biome> key = paleGarden[temp][humidity];
        return key == null ? this.getRegularBiome(temp, humidity, weirdness) : key;
    }*/

    /*
    @Inject(method = "getBadlandsOrRegularBiome", at = @At(value = "HEAD"), cancellable = true)
    private void getBadlandsRegularOrPaleGarden(int temperature, int humidity, MultiNoiseUtil.ParameterRange weirdness, CallbackInfoReturnable<RegistryKey<Biome>> cir) {


        if(temperature == 2 && humidity == 4 && weirdness.min() > 0.4F){
            cir.setReturnValue(BiomeKeys.PALE_GARDEN);
        }
    }*/

    @WrapOperation(method = "writeHighBiomes", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/biome/source/util/VanillaBiomeParameters;getBadlandsOrRegularBiome(IILnet/minecraft/world/biome/source/util/MultiNoiseUtil$ParameterRange;)Lnet/minecraft/registry/RegistryKey;"))
    private RegistryKey<Biome> invoke(VanillaBiomeParameters instance, int temperature, int humidity, MultiNoiseUtil.ParameterRange weirdness, Operation<RegistryKey<Biome>> original){
        if(temperature == 2 && humidity == 4){
            return BiomeKeys.PALE_GARDEN;
        }
        return original.call(instance, temperature, humidity, weirdness);
    }
}

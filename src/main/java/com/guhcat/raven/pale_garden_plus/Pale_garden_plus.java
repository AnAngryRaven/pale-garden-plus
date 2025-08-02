package com.guhcat.raven.pale_garden_plus;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModificationContext;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.MultiNoiseBiomeSource;

public class Pale_garden_plus implements ModInitializer {

    public static final String MOD_ID = "pale_garden_plus";

    @Override
    public void onInitialize() {
        Blocks.init();
    }
}

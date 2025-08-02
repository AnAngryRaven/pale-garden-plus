package com.guhcat.raven.pale_garden_plus.client;

import com.guhcat.raven.pale_garden_plus.Blocks;
import com.guhcat.raven.pale_garden_plus.Pale_garden_plus;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class TagDatagen extends FabricTagProvider<Block> {
    public TagDatagen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.BLOCK, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getTagBuilder(BlockTags.PICKAXE_MINEABLE).add(Identifier.of(Pale_garden_plus.MOD_ID, "pale_mossy_cobblestone"));
    }
}

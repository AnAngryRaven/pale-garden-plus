package com.guhcat.raven.pale_garden_plus.client;

import com.guhcat.raven.pale_garden_plus.Blocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class BlockLootDatagen extends FabricBlockLootTableProvider {
    protected BlockLootDatagen(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(Blocks.PALE_MOSSY_COBBLESTONE);
    }
}

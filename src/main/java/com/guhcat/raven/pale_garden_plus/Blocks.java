package com.guhcat.raven.pale_garden_plus;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class Blocks {
    
    public static final Block PALE_MOSSY_COBBLESTONE = register("pale_mossy_cobblestone", Block::new, Block.Settings.create().strength(2,6).sounds(BlockSoundGroup.STONE).requiresTool());

    private static Block register(String name, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        final Identifier identifier = Identifier.of(Pale_garden_plus.MOD_ID, name);
        final RegistryKey<Block> registryKey = RegistryKey.of(RegistryKeys.BLOCK, identifier);

        final Block block = net.minecraft.block.Blocks.register(registryKey, factory, settings);
        Items.register(block);
        return block;
    }

    public static void init() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(itemGroup -> {
            itemGroup.add(PALE_MOSSY_COBBLESTONE.asItem());
        });
    }
}

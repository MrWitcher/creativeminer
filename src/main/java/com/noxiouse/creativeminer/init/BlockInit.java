package com.noxiouse.creativeminer.init;

import com.noxiouse.creativeminer.CreativeMiner;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = CreativeMiner.MOD_ID, bus = Bus.MOD)
@ObjectHolder(CreativeMiner.MOD_ID)
public class BlockInit {
    public static final Block rubyore_block = null;

    @SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<Block> event){
        event.getRegistry().register(new Block(AbstractBlock.Properties.create(Material.IRON).hardnessAndResistance(0.7f, 3.0f).sound(SoundType.METAL).harvestLevel(3).harvestTool(ToolType.PICKAXE)).setRegistryName("rubyore_block"));
    }

    @SubscribeEvent
    public static void registerBlockItems(final RegistryEvent.Register<Item> event){
        event.getRegistry().register(new BlockItem(rubyore_block, new Item.Properties().maxStackSize(16).group(CreativeMiner.CreativeMinerItemGroup.instance)).setRegistryName("rubyore_block"));
    }
}
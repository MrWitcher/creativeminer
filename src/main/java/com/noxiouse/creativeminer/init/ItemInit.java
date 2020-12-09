package com.noxiouse.creativeminer.init;

import com.noxiouse.creativeminer.CreativeMiner;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = CreativeMiner.MOD_ID, bus = Bus.MOD)
@ObjectHolder(CreativeMiner.MOD_ID)
public class ItemInit {

    public static final Item ruby_item = null;

    // wrench anstat generated handheld in models json
    public static final Item wrench_item = null;

    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event){
        event.getRegistry().register(new Item(new Item.Properties().group(CreativeMiner.CreativeMinerItemGroup.instance).food(new Food.Builder().hunger(6).saturation(1.2f).effect(new EffectInstance(Effects.ABSORPTION, 6000, 5),0.7f).build())).setRegistryName("wrench_item"));
        event.getRegistry().register(new Item(new Item.Properties().group(CreativeMiner.CreativeMinerItemGroup.instance)).setRegistryName("ruby_item"));
    }
}
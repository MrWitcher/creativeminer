package com.noxiouse.creativeminer.events;

import com.noxiouse.creativeminer.CreativeMiner;
import com.noxiouse.creativeminer.init.BlockInit;
import com.noxiouse.creativeminer.init.ItemInit;
import com.sun.javafx.geom.Vec3d;
import com.sun.media.jfxmedia.events.PlayerStateEvent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.OreBlock;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ClassInheritanceMultiMap;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunk;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

import javax.xml.stream.Location;
import java.util.ArrayList;
import java.util.UUID;
import java.util.Vector;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// bus.forge wenn minecarft events benutz sonst eigener event bus.mod
@Mod.EventBusSubscriber(modid = CreativeMiner.MOD_ID, bus = Bus.FORGE)
public class DetectOreEvent {
   /* @SubscribeEvent
    public static void testJumpEvent(LivingEvent.LivingJumpEvent event){
        CreativeMiner.LOGGER.info("testJumpEvent fired");
        LivingEntity livingEntity = event.getEntityLiving();
        World world = livingEntity.getEntityWorld();

        world.getBlockState(livingEntity.getPosition());

        world.setBlockState(livingEntity.getPosition().add(0,5,0), BlockInit.rubyore_block.getDefaultState());
        livingEntity.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 600, 255));
        livingEntity.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 5000, 255));
        livingEntity.setGlowing(true);
    }
*/

    @SubscribeEvent
    public static void chunkDelOre(LivingEntityUseItemEvent event) {
        if (event.getItem().isFood()) {
            LivingEntity livingEvent = event.getEntityLiving();
            World world = livingEvent.getEntityWorld();
            Chunk chunk = world.getChunkAt(livingEvent.getEntity().getPosition());

            PlayerEntity player = (PlayerEntity) livingEvent.getEntity();

            IItemHandler inv = player.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,Direction.UP).orElse(null);

            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    for (int y = 0; y < 256; y++) {
                        BlockPos pos = chunk.getPos().asBlockPos().add(x, y, z);
                        BlockState state = chunk.getBlockState(pos);
                        Block block = state.getBlock();
                        ItemStack loot = new ItemStack(block.asItem(), 1);
                        if (block.isIn(Tags.Blocks.ORES) && ItemHandlerHelper.insertItemStacked(inv, loot, true) == ItemStack.EMPTY) {
                            world.destroyBlock(chunk.getPos().asBlockPos().add(x, y, z), false);
                            ItemHandlerHelper.insertItemStacked(inv, loot, false);
                        }
                    }
                }
            }
        }
    }
}
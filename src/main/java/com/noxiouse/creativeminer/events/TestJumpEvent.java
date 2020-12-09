package com.noxiouse.creativeminer.events;

import com.noxiouse.creativeminer.CreativeMiner;
import com.noxiouse.creativeminer.init.BlockInit;
import com.sun.javafx.geom.Vec3d;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunk;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

import java.util.Vector;

// bus.forge wenn minecarft events benutz sonst eigener event bus.mod
@Mod.EventBusSubscriber(modid = CreativeMiner.MOD_ID, bus = Bus.FORGE)
public class TestJumpEvent {
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
    public static void detectOre(LivingEvent.LivingUpdateEvent event){
        LivingEntity livingEvent = event.getEntityLiving();
        World world = livingEvent.getEntityWorld();
        //Vector3d start = new Vector3d(livingEvent.getPosX(), livingEvent.getPosYEye(), livingEvent.getPosZ());
        Vector3d start = livingEvent.getEntity().getPositionVec().add(0,livingEvent.getEyeHeight(),0);
        Vector3d end = new Vector3d(livingEvent.getEntity().getLookVec().getX(),livingEvent.getEntity().getLookVec().getY(),livingEvent.getEntity().getLookVec().getZ());
        BlockState detect = world.getBlockState(world.rayTraceBlocks(new RayTraceContext(start,start.add(end), RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE,livingEvent.getEntity())).getPos());

        if(detect.getBlock().matchesBlock(Blocks.IRON_ORE)){
            world.destroyBlock(world.rayTraceBlocks(new RayTraceContext(start,start.add(end), RayTraceContext.BlockMode.VISUAL, RayTraceContext.FluidMode.NONE,livingEvent.getEntity())).getPos(),true);
            CreativeMiner.LOGGER.info("iron" + detect);
        } else {
            CreativeMiner.LOGGER.info("livingupdaeevent");
        }
    }
}
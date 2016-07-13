package b1u3m0nk3y13.necromancer.main;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStoneBrick.EnumType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class References {
	
	public static boolean isAcceptableAlter(World world, BlockPos centerBlock1Pos) 
	{
		int x = centerBlock1Pos.getX();
		int y = centerBlock1Pos.getY();
		int z = centerBlock1Pos.getZ();
		
		//Top Layer
		Block centerBlock1 = world.getBlockState(centerBlock1Pos).getBlock();
		if (centerBlock1 != Blocks.GRASS) if (centerBlock1 != Blocks.DIRT) return false;
		if (centerBlock1 != Blocks.GRASS) if (centerBlock1 != Blocks.DIRT) return false; 
		Block north1 = world.getBlockState(centerBlock1Pos.north()).getBlock();
		if (north1 != Blocks.OBSIDIAN) return false;
		Block south1 = world.getBlockState(centerBlock1Pos.south()).getBlock();
		if (south1 != Blocks.OBSIDIAN) return false;
		Block east1 = world.getBlockState(centerBlock1Pos.east()).getBlock();
		if (east1 != Blocks.OBSIDIAN) return false;
		Block west1 = world.getBlockState(centerBlock1Pos.west()).getBlock();
		if (west1 != Blocks.OBSIDIAN) return false;
		IBlockState northEast1 = world.getBlockState(centerBlock1Pos.north().east());
		if (northEast1 != Blocks.STONEBRICK.getStateFromMeta(EnumType.MOSSY.getMetadata())) return false;
		IBlockState northWest1 = world.getBlockState(centerBlock1Pos.north().west());
		if (northWest1 != Blocks.STONEBRICK.getStateFromMeta(EnumType.MOSSY.getMetadata())) return false;
		IBlockState southEast1 = world.getBlockState(centerBlock1Pos.south().east());
		if (southEast1 != Blocks.STONEBRICK.getStateFromMeta(EnumType.MOSSY.getMetadata())) return false;
		IBlockState southWest1 = world.getBlockState(centerBlock1Pos.south().west());
		if (southWest1 != Blocks.STONEBRICK.getStateFromMeta(EnumType.MOSSY.getMetadata())) return false;
		
		//Bottom Layer
		BlockPos centerBlock2Pos = centerBlock1Pos.down();
		Block centerBlock2 = world.getBlockState(centerBlock2Pos).getBlock();
		if (centerBlock2 != Blocks.GRASS) if (centerBlock2 != Blocks.DIRT) return false;
		if (centerBlock2 != Blocks.GRASS) if (centerBlock2 != Blocks.DIRT) return false;
		Block north2 = world.getBlockState(centerBlock2Pos.north()).getBlock();
		if (north2 != Blocks.GOLD_BLOCK) return false;
		Block south2 = world.getBlockState(centerBlock2Pos.south()).getBlock();
		if (south2 != Blocks.GOLD_BLOCK) return false;
		Block east2 = world.getBlockState(centerBlock2Pos.east()).getBlock();
		if (east2 != Blocks.GOLD_BLOCK) return false;
		Block west2 = world.getBlockState(centerBlock2Pos.west()).getBlock();
		if (west2 != Blocks.GOLD_BLOCK) return false;
		Block northEast2 = world.getBlockState(centerBlock2Pos.north().east()).getBlock();
		if (northEast2 != Blocks.QUARTZ_BLOCK) return false;
		Block northWest2 = world.getBlockState(centerBlock2Pos.north().west()).getBlock();
		if (northWest2 != Blocks.QUARTZ_BLOCK) return false;
		Block southEast2 = world.getBlockState(centerBlock2Pos.south().east()).getBlock();
		if (southEast2 != Blocks.QUARTZ_BLOCK) return false;
		Block southWest2 = world.getBlockState(centerBlock2Pos.south().west()).getBlock();
		if (southWest2 != Blocks.QUARTZ_BLOCK) return false;
		
		return true;
	}

}

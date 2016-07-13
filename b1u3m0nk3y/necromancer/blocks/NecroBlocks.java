package b1u3m0nk3y13.necromancer.blocks;

import b1u3m0nk3y13.necromancer.blocks.tileentities.TileEntityAlter;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class NecroBlocks 
{	
	public static final Block ALTER = new BlockAlter();
	
	protected static final ItemBlock ALTER_ITEM = new ItemBlock(ALTER);
	
	public static void registerBlocks()
	{ 
		registerBlock(ALTER, ALTER_ITEM, "alter");
		registerTileEntity(TileEntityAlter.class, "Alter");
	}
	
	public static void registerBlock(Block block, ItemBlock itemBlock, String string)
	{
    	block.setUnlocalizedName(string);
    	block.setRegistryName(string);
    	
    	GameRegistry.register(block);
    	
    	itemBlock.setUnlocalizedName(string);
    	itemBlock.setRegistryName(string);
    	
    	GameRegistry.register(itemBlock);
	}
	
	public static void registerTileEntity(Class<? extends TileEntity> tileEntity, String id)
	{
		GameRegistry.registerTileEntity(tileEntity, id);
	}
}

package b1u3m0nk3y13.necromancer.main;

import b1u3m0nk3y13.necromancer.blocks.NecroBlocks;
import b1u3m0nk3y13.necromancer.items.NecroItems;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class RegisterRenderers {
	
	public static RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
	
	public static void registerItemRenderers()
	{
		renderItem(NecroItems.CONJURE_ZOMBIE);
		renderItem(NecroItems.CONJURE_SKELETON);
		renderItem(NecroItems.CONJURE_CHICKEN);
		renderItem(NecroItems.CONJURE_BAT);
	}
	
	public static void registerBlockRenderers()
	{
		renderBlock(NecroBlocks.ALTER);
	}
	
	public static void renderItem(Item item) 
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
	
	public static void renderBlock(Block block) 
	{
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
	}
}

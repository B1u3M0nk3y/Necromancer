package b1u3m0nk3y13.necromancer.items;

import b1u3m0nk3y13.necromancer.Necromancer;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class NecroItems 
{	
	public static final Item CONJURE_ZOMBIE = new ItemConjureZombie();
	public static final Item CONJURE_SKELETON = new ItemConjureSkeleton();
	public static final Item CONJURE_CHICKEN = new ItemConjureChicken();
	public static final Item CONJURE_BAT = new ItemConjureBat();

	
	public static void registerItems()
	{
		registerItem(CONJURE_ZOMBIE, "conjure_zombie");
		registerItem(CONJURE_SKELETON, "conjure_skeleton");
		registerItem(CONJURE_CHICKEN, "conjure_chicken");
		registerItem(CONJURE_BAT, "conjure_bat");
	}
	
	public static void registerItem(Item item, String string)
	{
    	item.setUnlocalizedName(string);
    	item.setRegistryName(string);
    	
    	item.setCreativeTab(Necromancer.TAB_NECROMANCER);
    	
    	GameRegistry.register(item);
	}
}

package b1u3m0nk3y13.necromancer.main;

import b1u3m0nk3y13.necromancer.items.NecroItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CreativeNecromancerTabs extends CreativeTabs {
	public static String tabName;
	
	public CreativeNecromancerTabs(String par1String) 
	{
		super(par1String);
		this.tabName = par1String;
	}

	@Override
	public String getTranslatedTabLabel() 
	{
		return this.tabName;
	}

    @SideOnly(Side.CLIENT)
    @Override
	public Item getTabIconItem() 
    {
		return NecroItems.CONJURE_ZOMBIE;
	}
}
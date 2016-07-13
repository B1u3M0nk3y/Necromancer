package b1u3m0nk3y13.necromancer;

import b1u3m0nk3y13.necromancer.blocks.NecroBlocks;
import b1u3m0nk3y13.necromancer.entities.RegisterEntities;
import b1u3m0nk3y13.necromancer.items.NecroItems;
import b1u3m0nk3y13.necromancer.main.CommonProxy;
import b1u3m0nk3y13.necromancer.main.CreativeNecromancerTabs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Necromancer.MODID, name = Necromancer.NAME, version = Necromancer.VERSION, acceptedMinecraftVersions = Necromancer.MCVERSION)              
public class Necromancer {
	
	//----Base----\\
	public static final String MODID = "Necromancer";
	public static final String NAME = "Necromancer";
    public static final String VERSION = "1.0.1";
    public static final String MCVERSION = "1.9.4";
    
    //----Entities----\\
    public static final int conjuredZombieId = 1;
    public static final int conjuredSkeletonId = 2;
    public static final int conjuredChickenId = 3;
    public static final int conjuredBatId = 4;
    
    //----Creative Tabs----\\
    public static final CreativeTabs TAB_NECROMANCER = new CreativeNecromancerTabs("Necromancer");
    
  //--------------------------------------------------------------------------------------------------------------------------\\   
    @SidedProxy(clientSide = "b1u3m0nk3y13.necromancer.main.ClientProxy", serverSide = "b1u3m0nk3y13.necromancer.main.CommonProxy")
    public static CommonProxy proxy;
    
    @Instance(Necromancer.MODID)
    public static Necromancer INSTANCE;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) 
    {
    	NecroItems.registerItems();
    	NecroBlocks.registerBlocks();
    	RegisterEntities.registerEntities();
    	
    	proxy.registerItemBlockRenderers();
    	proxy.registerEntityRenderers();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) 
    {

    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) 
    {
    	
    }
}

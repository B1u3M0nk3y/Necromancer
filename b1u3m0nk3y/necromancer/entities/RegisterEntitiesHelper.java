package b1u3m0nk3y13.necromancer.entities;

import b1u3m0nk3y13.necromancer.Necromancer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class RegisterEntitiesHelper {

    public static Biome bgb;
    
	public static void registerEntity(Class<? extends EntityLiving> entityLivingClass, String entityName, int entityUniqueID, int colorForeground, int colorBackground, int weightedSpawnProb, int minSpawn, int maxSpawn, EnumCreatureType enumCreatureType)
	{
		registerEntity(entityLivingClass, entityName, entityUniqueID, colorForeground, colorBackground, weightedSpawnProb, minSpawn, maxSpawn, enumCreatureType, Biomes.TAIGA_HILLS, Biomes.TAIGA, Biomes.SWAMPLAND, Biomes.STONE_BEACH, Biomes.SAVANNA_PLATEAU, Biomes.SAVANNA, Biomes.ROOFED_FOREST, Biomes.PLAINS, Biomes.MUSHROOM_ISLAND_SHORE, Biomes.MUSHROOM_ISLAND, Biomes.MESA_ROCK, Biomes.MESA_CLEAR_ROCK, Biomes.MESA, Biomes.BIRCH_FOREST,  Biomes.TAIGA_HILLS, Biomes.JUNGLE_HILLS, Biomes.JUNGLE_EDGE, Biomes.JUNGLE, Biomes.ICE_PLAINS, Biomes.ICE_MOUNTAINS, Biomes.FOREST, Biomes.EXTREME_HILLS_WITH_TREES, Biomes.EXTREME_HILLS_EDGE, Biomes.EXTREME_HILLS, Biomes.DESERT_HILLS, Biomes.DESERT, Biomes.COLD_TAIGA_HILLS, Biomes.COLD_TAIGA, Biomes.COLD_BEACH, Biomes.BIRCH_FOREST_HILLS, Biomes.BEACH);
	}
	
	public static void registerEntity(Class<? extends EntityLiving> entityLivingClass, String entityName, int entityUniqueID, int colorForeground, int colorBackground, int weightedSpawnProb, int minSpawn, int maxSpawn, EnumCreatureType enumCreatureType, Biome... biomes)
	{
		EntityRegistry.registerModEntity(entityLivingClass, entityName, entityUniqueID, Necromancer.INSTANCE, 50, 3, true, colorForeground, colorBackground);
		EntityRegistry.addSpawn(entityLivingClass, weightedSpawnProb, minSpawn, maxSpawn, enumCreatureType, biomes);
	}
	
	public static void registerModEntity(Class<? extends Entity> entityClass, String name, int id) 
	{
		EntityRegistry.registerModEntity(entityClass, name, id, Necromancer.INSTANCE, 80, 3, true);
	}
	
}

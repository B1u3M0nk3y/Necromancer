package b1u3m0nk3y13.necromancer.entities;

import net.minecraft.entity.EnumCreatureType;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import b1u3m0nk3y13.necromancer.Necromancer;

public class RegisterEntities {

	public static void registerEntities()
	{
		RegisterEntitiesHelper.registerEntity(EntityConjuredZombie.class, "ConjuredZombie", Necromancer.conjuredZombieId, 0x0F6936, 0xEDF263, 0, 0, 0, EnumCreatureType.MONSTER);
	
		RegisterEntitiesHelper.registerEntity(EntityConjuredSkeleton.class, "ConjuredSkeleton", Necromancer.conjuredSkeletonId, 0xd5dadb, 0x7d7d7d, 0, 0, 0, EnumCreatureType.MONSTER);
		
		RegisterEntitiesHelper.registerEntity(EntityConjuredChicken.class, "ConjuredChicken", Necromancer.conjuredChickenId, 0x9E9E9E, 0xD62D54, 0, 0, 0, EnumCreatureType.MONSTER);
		
		RegisterEntitiesHelper.registerEntity(EntityConjuredBat.class, "ConjuredBat", Necromancer.conjuredBatId, 0x594835, 0x6E6E6E, 0, 0, 0, EnumCreatureType.MONSTER);
	}
}

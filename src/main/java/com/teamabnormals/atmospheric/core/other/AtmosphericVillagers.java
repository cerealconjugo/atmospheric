package com.teamabnormals.atmospheric.core.other;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBiomes;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

public class AtmosphericVillagers {

	public static void registerVillagerTypes() {
		VillagerTrades.TRADES.isEmpty();
		VillagerType scrubland = VillagerType.register(Atmospheric.MOD_ID + ":scrubland");

		VillagerType.BY_BIOME.put(AtmosphericBiomes.RAINFOREST, VillagerType.JUNGLE);
		VillagerType.BY_BIOME.put(AtmosphericBiomes.SPARSE_RAINFOREST, VillagerType.JUNGLE);
		VillagerType.BY_BIOME.put(AtmosphericBiomes.RAINFOREST_BASIN, VillagerType.JUNGLE);
		VillagerType.BY_BIOME.put(AtmosphericBiomes.SPARSE_RAINFOREST_BASIN, VillagerType.JUNGLE);

		VillagerType.BY_BIOME.put(AtmosphericBiomes.SCRUBLAND, scrubland);
		VillagerType.BY_BIOME.put(AtmosphericBiomes.DUNES, scrubland);
		VillagerType.BY_BIOME.put(AtmosphericBiomes.FLOURISHING_DUNES, scrubland);
		VillagerType.BY_BIOME.put(AtmosphericBiomes.ROCKY_DUNES, scrubland);
		VillagerType.BY_BIOME.put(AtmosphericBiomes.PETRIFIED_DUNES, scrubland);
		VillagerType.BY_BIOME.put(AtmosphericBiomes.SPINY_THICKET, scrubland);
	}
}
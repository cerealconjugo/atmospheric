package com.teamabnormals.atmospheric.common.block.grower;

import com.teamabnormals.atmospheric.core.registry.AtmosphericFeatures.AtmosphericConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class YuccaTreeGrower extends AbstractTreeGrower {

	@Override
	protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean beehive) {
		if (random.nextFloat() < 0.1F) {
			return AtmosphericConfiguredFeatures.BABY_YUCCA;
		} else {
			return beehive ? AtmosphericConfiguredFeatures.YUCCA_BEES_005 : AtmosphericConfiguredFeatures.YUCCA;
		}
	}
}

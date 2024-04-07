package com.teamabnormals.atmospheric.common.block.grower;

import com.teamabnormals.atmospheric.core.registry.AtmosphericFeatures.AtmosphericConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import javax.annotation.Nullable;

public class DryLaurelTreeGrower extends LaurelTreeGrower {

	@Nullable
	@Override
	protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean flowers) {
		return AtmosphericConfiguredFeatures.DRY_LAUREL;
	}

	protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredNetherFeature(RandomSource random) {
		return AtmosphericConfiguredFeatures.DRY_LAUREL_BLOOD_ORANGES_08;
	}

	protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredOrangesFeature(RandomSource random) {
		return AtmosphericConfiguredFeatures.DRY_LAUREL_ORANGES_08;
	}

	@Nullable
	@Override
	protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredMegaFeature(RandomSource random) {
		return this.shouldBeGiant(random) ? AtmosphericConfiguredFeatures.GIANT_DRY_LAUREL : AtmosphericConfiguredFeatures.LARGE_DRY_LAUREL;
	}

	protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredOrangesMegaFeature(RandomSource random) {
		return this.shouldBeGiant(random) ? AtmosphericConfiguredFeatures.GIANT_DRY_LAUREL_ORANGES_08 : AtmosphericConfiguredFeatures.LARGE_DRY_LAUREL_ORANGES_08;
	}

	protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredNetherMegaFeature(RandomSource random) {
		return this.shouldBeGiant(random) ? AtmosphericConfiguredFeatures.GIANT_DRY_LAUREL_BLOOD_ORANGES_08 : AtmosphericConfiguredFeatures.LARGE_DRY_LAUREL_BLOOD_ORANGES_08;
	}
}

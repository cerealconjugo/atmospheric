package com.teamabnormals.atmospheric.common.block.grower;

import com.teamabnormals.atmospheric.core.other.tags.AtmosphericBlockTags;
import com.teamabnormals.atmospheric.core.registry.AtmosphericFeatures.AtmosphericConfiguredFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.grower.AbstractMegaTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.level.SaplingGrowTreeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.Event.Result;

import javax.annotation.Nullable;

public class LaurelTreeGrower extends AbstractMegaTreeGrower {

	@Override
	public boolean growTree(ServerLevel level, ChunkGenerator generator, BlockPos pos, BlockState state, RandomSource random) {
		for (int x = 0; x >= -1; --x) {
			for (int z = 0; z >= -1; --z) {
				if (isTwoByTwoSapling(state, level, pos, x, z)) {
					return this.placeMega(level, generator, pos, state, random, x, z);
				}
			}
		}

		return this.growLaurelTree(level, generator, pos, state, random);
	}

	public boolean growLaurelTree(ServerLevel level, ChunkGenerator generator, BlockPos pos, BlockState state, RandomSource random) {
		ResourceKey<ConfiguredFeature<?, ?>> key = this.getConfiguredFeature(random, this.hasOranges(level, pos), level.dimensionTypeId().equals(BuiltinDimensionTypes.NETHER));
		if (key == null) {
			return false;
		}

		Holder<ConfiguredFeature<?, ?>> holder = level.registryAccess().registryOrThrow(Registries.CONFIGURED_FEATURE).getHolder(key).orElse(null);
		SaplingGrowTreeEvent event = ForgeEventFactory.blockGrowFeature(level, random, pos, holder);
		if (event.getResult().equals(Result.DENY) || event.getFeature() == null) {
			return false;
		} else {
			holder = event.getFeature();
			if (event.getResult() == Event.Result.DENY || holder == null) {
				return false;
			}

			ConfiguredFeature<?, ?> configuredfeature = holder.value();
			BlockState blockstate = level.getFluidState(pos).createLegacyBlock();
			level.setBlock(pos, blockstate, 4);
			if (configuredfeature.place(level, generator, random, pos)) {
				if (level.getBlockState(pos) == blockstate) {
					level.sendBlockUpdated(pos, state, blockstate, 2);
				}

				return true;
			} else {
				level.setBlock(pos, state, 4);
				return false;
			}
		}
	}

	public boolean placeMega(ServerLevel level, ChunkGenerator generator, BlockPos pos, BlockState state, RandomSource random, int x, int z) {
		ResourceKey<ConfiguredFeature<?, ?>> key = this.getConfiguredMegaFeature(random, this.hasOranges(level, pos), level.dimensionTypeId().equals(BuiltinDimensionTypes.NETHER));
		if (key == null) {
			return false;
		}

		Holder<ConfiguredFeature<?, ?>> holder = level.registryAccess().registryOrThrow(Registries.CONFIGURED_FEATURE).getHolder(key).orElse(null);
		SaplingGrowTreeEvent event = ForgeEventFactory.blockGrowFeature(level, random, pos, holder);
		if (event.getResult().equals(Result.DENY) || event.getFeature() == null) {
			return false;
		} else {
			holder = event.getFeature();
			if (event.getResult() == Event.Result.DENY || holder == null) {
				return false;
			}

			ConfiguredFeature<?, ?> configuredfeature = holder.value();
			BlockState blockstate = Blocks.AIR.defaultBlockState();
			level.setBlock(pos.offset(x, 0, z), blockstate, 4);
			level.setBlock(pos.offset(x + 1, 0, z), blockstate, 4);
			level.setBlock(pos.offset(x, 0, z + 1), blockstate, 4);
			level.setBlock(pos.offset(x + 1, 0, z + 1), blockstate, 4);
			if (configuredfeature.place(level, generator, random, pos.offset(x, 0, z))) {
				return true;
			} else {
				level.setBlock(pos.offset(x, 0, z), state, 4);
				level.setBlock(pos.offset(x + 1, 0, z), state, 4);
				level.setBlock(pos.offset(x, 0, z + 1), state, 4);
				level.setBlock(pos.offset(x + 1, 0, z + 1), state, 4);
				return false;
			}
		}
	}

	private boolean hasOranges(LevelAccessor level, BlockPos pos) {
		for (BlockPos blockpos : BlockPos.MutableBlockPos.betweenClosed(pos.below().north(3).west(3), pos.above(5).south(3).east(3))) {
			if (level.getBlockState(blockpos).is(AtmosphericBlockTags.ORANGES)) {
				return true;
			}
		}

		return false;
	}


	@Nullable
	protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean oranges, boolean nether) {
		return oranges ? (nether ? this.getConfiguredNetherFeature(random) : this.getConfiguredOrangesFeature(random)) : this.getConfiguredFeature(random, false);
	}

	@Nullable
	@Override
	protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean flowers) {
		return AtmosphericConfiguredFeatures.LAUREL;
	}

	protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredNetherFeature(RandomSource random) {
		return AtmosphericConfiguredFeatures.LAUREL_BLOOD_ORANGES_08;
	}

	protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredOrangesFeature(RandomSource random) {
		return AtmosphericConfiguredFeatures.LAUREL_ORANGES_08;
	}

	protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredMegaFeature(RandomSource random, boolean oranges, boolean nether) {
		return oranges ? (nether ? this.getConfiguredNetherMegaFeature(random) : this.getConfiguredOrangesMegaFeature(random)) : this.getConfiguredMegaFeature(random);
	}

	@Nullable
	@Override
	protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredMegaFeature(RandomSource random) {
		return this.shouldBeGiant(random) ? AtmosphericConfiguredFeatures.GIANT_LAUREL : AtmosphericConfiguredFeatures.LARGE_LAUREL;
	}

	protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredOrangesMegaFeature(RandomSource random) {
		return this.shouldBeGiant(random) ? AtmosphericConfiguredFeatures.GIANT_LAUREL_ORANGES_08 : AtmosphericConfiguredFeatures.LARGE_LAUREL_ORANGES_08;
	}

	protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredNetherMegaFeature(RandomSource random) {
		return this.shouldBeGiant(random) ? AtmosphericConfiguredFeatures.GIANT_LAUREL_BLOOD_ORANGES_08 : AtmosphericConfiguredFeatures.LARGE_LAUREL_BLOOD_ORANGES_08;
	}

	public boolean shouldBeGiant(RandomSource random) {
		return random.nextInt(3) == 0;
	}
}

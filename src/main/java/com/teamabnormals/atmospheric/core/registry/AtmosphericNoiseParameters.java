package com.teamabnormals.atmospheric.core.registry;

import com.teamabnormals.atmospheric.core.Atmospheric;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.synth.NormalNoise.NoiseParameters;

public class AtmosphericNoiseParameters {
	public static final ResourceKey<NoiseParameters> FLOOR_RAISE_RADIUS_OFFSET = createKey("floor_raise_radius_offset");

	public static void bootstrap(BootstapContext<NoiseParameters> context) {
		context.register(FLOOR_RAISE_RADIUS_OFFSET, new NoiseParameters(-4, 1.0D, 1.0D));

	}

	public static ResourceKey<NoiseParameters> createKey(String name) {
		return ResourceKey.create(Registries.NOISE, new ResourceLocation(Atmospheric.MOD_ID, name));
	}
}

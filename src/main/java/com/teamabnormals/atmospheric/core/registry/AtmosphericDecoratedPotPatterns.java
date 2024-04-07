package com.teamabnormals.atmospheric.core.registry;

import com.mojang.datafixers.util.Pair;
import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.blueprint.core.util.DataUtil;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class AtmosphericDecoratedPotPatterns {
	public static final DeferredRegister<String> DECORATED_POT_PATTERNS = DeferredRegister.create(Registries.DECORATED_POT_PATTERNS, Atmospheric.MOD_ID);

	public static final RegistryObject<String> SCYTHE = register("scythe_pottery_pattern");
	public static final RegistryObject<String> SUCCULENT = register("succulent_pottery_pattern");
	public static final RegistryObject<String> SUN = register("sun_pottery_pattern");

	public static void registerDecoratedPotPatterns() {
		DataUtil.registerDecoratedPotPattern(Pair.of(AtmosphericItems.SCYTHE_POTTERY_SHERD.get(), SCYTHE));
		DataUtil.registerDecoratedPotPattern(Pair.of(AtmosphericItems.SUCCULENT_POTTERY_SHERD.get(), SUCCULENT));
		DataUtil.registerDecoratedPotPattern(Pair.of(AtmosphericItems.SUN_POTTERY_SHERD.get(), SUN));
	}

	public static RegistryObject<String> register(String name) {
		return DECORATED_POT_PATTERNS.register(name, () -> name);
	}
}
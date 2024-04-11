package com.teamabnormals.atmospheric.core.registry;

import com.teamabnormals.atmospheric.core.Atmospheric;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AtmosphericPaintingVariants {
	public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS = DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, Atmospheric.MOD_ID);

	public static final RegistryObject<PaintingVariant> FATEFUL_OUTING = PAINTING_VARIANTS.register("fateful_outing", () -> new PaintingVariant(48, 48));
	public static final RegistryObject<PaintingVariant> JUNGLES_END = PAINTING_VARIANTS.register("jungles_end", () -> new PaintingVariant(64, 48));
	public static final RegistryObject<PaintingVariant> MONSOON = PAINTING_VARIANTS.register("monsoon", () -> new PaintingVariant(32, 16));
	public static final RegistryObject<PaintingVariant> NOT_SO_STILL_LIFE = PAINTING_VARIANTS.register("not_so_still_life", () -> new PaintingVariant(16, 16));
}

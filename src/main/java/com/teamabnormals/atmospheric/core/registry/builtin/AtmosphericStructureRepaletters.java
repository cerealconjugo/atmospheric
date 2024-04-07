package com.teamabnormals.atmospheric.core.registry.builtin;

import com.google.common.collect.Maps;
import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.blueprint.common.world.modification.structure.SimpleStructureRepaletter;
import com.teamabnormals.blueprint.common.world.modification.structure.StructureRepaletterEntry;
import com.teamabnormals.blueprint.core.api.conditions.ConfigValueCondition;
import com.teamabnormals.blueprint.core.registry.BlueprintDataPackRegistries;
import com.teamabnormals.blueprint.core.registry.BlueprintHolderSets;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.crafting.conditions.ICondition;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.teamabnormals.atmospheric.core.AtmosphericConfig.COMMON;

public final class AtmosphericStructureRepaletters {

	public static void bootstrap(BootstapContext<StructureRepaletterEntry> context) {
		HolderGetter<Structure> structures = context.lookup(Registries.STRUCTURE);

		yuccaDesertVillage(context, structures, "yucca_buttons", Blocks.JUNGLE_BUTTON, AtmosphericBlocks.YUCCA_BUTTON.get());
		yuccaDesertVillage(context, structures, "yucca_doors", Blocks.JUNGLE_DOOR, AtmosphericBlocks.YUCCA_DOOR.get());
		yuccaDesertVillage(context, structures, "yucca_fences", Blocks.JUNGLE_FENCE, AtmosphericBlocks.YUCCA_FENCE.get());
		yuccaDesertVillage(context, structures, "yucca_fence_gates", Blocks.JUNGLE_FENCE_GATE, AtmosphericBlocks.YUCCA_FENCE_GATE.get());
		yuccaDesertVillage(context, structures, "yucca_trapdoors", Blocks.JUNGLE_TRAPDOOR, AtmosphericBlocks.YUCCA_TRAPDOOR.get());
		yuccaDesertVillage(context, structures, "yucca_ladders", Blocks.LADDER, AtmosphericBlocks.YUCCA_LADDER.get());
		yuccaDesertVillage(context, structures, "yucca_chests", Blocks.CHEST, AtmosphericBlocks.YUCCA_CHEST.get());
		yuccaDesertVillage(context, structures, "yucca_bookshelves", Blocks.BOOKSHELF, AtmosphericBlocks.YUCCA_BOOKSHELF.get());

		grimwoodAncientCity(context, structures, "grimwood_logs", Blocks.DARK_OAK_LOG, AtmosphericBlocks.GRIMWOOD_LOG.get());
		grimwoodAncientCity(context, structures, "grimwood_fences", Blocks.DARK_OAK_FENCE, AtmosphericBlocks.GRIMWOOD_FENCE.get());
		grimwoodAncientCity(context, structures, "grimwood_planks", Blocks.DARK_OAK_PLANKS, AtmosphericBlocks.GRIMWOOD_PLANKS.get());
		grimwoodAncientCity(context, structures, "grimwood_ladders", Blocks.LADDER, AtmosphericBlocks.GRIMWOOD_LADDER.get());
		grimwoodAncientCity(context, structures, "grimwood_chests", Blocks.CHEST, AtmosphericBlocks.GRIMWOOD_CHEST.get());
	}

	private static void yuccaDesertVillage(BootstapContext<StructureRepaletterEntry> context, HolderGetter<Structure> structures, String name, Block replacesBlock, Block replacesWith) {
		basicRepaletter(context, structures, config(COMMON.yuccaDesertVillages, "yucca_desert_villages"), name + "_in_desert_villages", replacesBlock, replacesWith, BuiltinStructures.VILLAGE_DESERT);
	}

	private static void grimwoodAncientCity(BootstapContext<StructureRepaletterEntry> context, HolderGetter<Structure> structures, String name, Block replacesBlock, Block replacesWith) {
		basicRepaletter(context, structures, config(COMMON.grimwoodAncientCities, "grimwood_ancient_cities"), name + "_in_ancient_cities", replacesBlock, replacesWith, BuiltinStructures.ANCIENT_CITY);
	}

	@SafeVarargs
	private static void basicRepaletter(BootstapContext<StructureRepaletterEntry> context, HolderGetter<Structure> structures, ICondition condition, String name, Block replacesBlock, Block replacesWith, ResourceKey<Structure>... selector) {
		context.register(
				repaletterKey(name),
				new StructureRepaletterEntry(
						BlueprintHolderSets.conditional(HolderSet.direct(Stream.of(selector).map(structures::getOrThrow).collect(Collectors.toList())), condition),
						Optional.empty(), false, new SimpleStructureRepaletter(replacesBlock, replacesWith))
		);
	}

	public static ConfigValueCondition config(ForgeConfigSpec.ConfigValue<?> value, String key, boolean inverted) {
		return new ConfigValueCondition(new ResourceLocation(Atmospheric.MOD_ID, "config"), value, key, Maps.newHashMap(), inverted);
	}

	public static ConfigValueCondition config(ForgeConfigSpec.ConfigValue<?> value, String key) {
		return config(value, key, false);
	}

	private static ResourceKey<StructureRepaletterEntry> repaletterKey(String name) {
		return ResourceKey.create(BlueprintDataPackRegistries.STRUCTURE_REPALETTERS, new ResourceLocation(Atmospheric.MOD_ID, name));
	}
}
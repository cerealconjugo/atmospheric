package com.teamabnormals.atmospheric.core.registry.builtin;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.teamabnormals.atmospheric.core.Atmospheric;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.AncientCityStructurePools;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool.Projection;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;

import java.util.function.Function;

public class AtmosphericTemplatePools {
	public static final ResourceKey<StructureTemplatePool> PETRIFIED_GARDEN = createKey("arid_garden/petrified_garden");

	public static void bootstrap(BootstapContext<StructureTemplatePool> context) {
		HolderGetter<StructureProcessorList> processorLists = context.lookup(Registries.PROCESSOR_LIST);

		Holder<StructureProcessorList> petrifiedAridGarden = processorLists.getOrThrow(AtmosphericProcessorLists.PETRIFIED_ARID_GARDEN);

		HolderGetter<StructureTemplatePool> templatePools = context.lookup(Registries.TEMPLATE_POOL);
		Holder<StructureTemplatePool> empty = templatePools.getOrThrow(Pools.EMPTY);

		context.register(PETRIFIED_GARDEN, new StructureTemplatePool(empty, ImmutableList.of(
				singlePoolElement("arid_garden/petrified_garden/petrified_garden_1", petrifiedAridGarden),
				singlePoolElement("arid_garden/petrified_garden/petrified_garden_2", petrifiedAridGarden),
				singlePoolElement("arid_garden/petrified_garden/petrified_garden_3", petrifiedAridGarden),
				singlePoolElement("arid_garden/petrified_garden/petrified_garden_4", petrifiedAridGarden),
				singlePoolElement("arid_garden/petrified_garden/petrified_garden_5", petrifiedAridGarden)
		), StructureTemplatePool.Projection.RIGID));
		AncientCityStructurePools.bootstrap(context);
	}

	public static Pair<Function<Projection, ? extends StructurePoolElement>, Integer> singlePoolElement(String name, Holder<StructureProcessorList> holderGetter) {
		return Pair.of(StructurePoolElement.single(Atmospheric.location(name).toString(), holderGetter), 1);
	}

	public static ResourceKey<StructureTemplatePool> createKey(String name) {
		return ResourceKey.create(Registries.TEMPLATE_POOL, new ResourceLocation(Atmospheric.MOD_ID, name));
	}
}
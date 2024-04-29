package com.teamabnormals.atmospheric.core.data.server.tags;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.other.tags.AtmosphericEntityTypeTags;
import com.teamabnormals.atmospheric.core.registry.AtmosphericEntityTypes;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class AtmosphericEntityTypeTagsProvider extends EntityTypeTagsProvider {

	public AtmosphericEntityTypeTagsProvider(PackOutput output, CompletableFuture<Provider> provider, ExistingFileHelper helper) {
		super(output, provider, Atmospheric.MOD_ID, helper);
	}

	@Override
	public void addTags(Provider provider) {
		this.tag(AtmosphericEntityTypeTags.YUCCA_IMMUNE).add(EntityType.BEE, EntityType.HUSK, EntityType.CAMEL, AtmosphericEntityTypes.COCHINEAL.get());
		this.tag(AtmosphericEntityTypeTags.CACTUS_IMMUNE).add(EntityType.HUSK, EntityType.CAMEL, AtmosphericEntityTypes.COCHINEAL.get());
		this.tag(EntityTypeTags.IMPACT_PROJECTILES).add(AtmosphericEntityTypes.PASSION_FRUIT_SEED.get());
	}
}
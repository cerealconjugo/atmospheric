package com.teamabnormals.atmospheric.core.data.server.tags;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.registry.AtmosphericPaintingVariants;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.PaintingVariantTagsProvider;
import net.minecraft.tags.PaintingVariantTags;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class AtmosphericPaintingVariantTagsProvider extends PaintingVariantTagsProvider {

	public AtmosphericPaintingVariantTagsProvider(PackOutput output, CompletableFuture<Provider> provider, ExistingFileHelper helper) {
		super(output, provider, Atmospheric.MOD_ID, helper);
	}

	@Override
	public void addTags(Provider provider) {
		this.tag(PaintingVariantTags.PLACEABLE).add(
				AtmosphericPaintingVariants.FATEFUL_OUTING.getKey(),
				AtmosphericPaintingVariants.MONSOON.getKey(),
				AtmosphericPaintingVariants.NOT_SO_STILL_LIFE.getKey(),
				AtmosphericPaintingVariants.WAYWARD.getKey()
		);
	}
}
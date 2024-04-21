package com.teamabnormals.atmospheric.core.data.server;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.other.AtmosphericBlockFamilies;
import com.teamabnormals.atmospheric.core.other.tags.AtmosphericItemTags;
import com.teamabnormals.atmospheric.core.registry.AtmosphericItems;
import com.teamabnormals.atmospheric.integration.boatload.AtmosphericBoatTypes;
import com.teamabnormals.blueprint.core.other.tags.BlueprintItemTags;
import com.teamabnormals.boatload.core.data.server.BoatloadRecipeProvider;
import com.teamabnormals.woodworks.core.data.server.WoodworksRecipeProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import net.minecraftforge.common.crafting.conditions.NotCondition;

import javax.annotation.Nullable;
import java.util.function.Consumer;

import static com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks.*;

public class AtmosphericRecipeProvider extends RecipeProvider {
	public static final ModLoadedCondition NEAPOLITAN_LOADED = new ModLoadedCondition("neapolitan");
	public static final NotCondition NEAPOLITAN_NOT_LOADED = new NotCondition(NEAPOLITAN_LOADED);

	public static final ModLoadedCondition APPLE_CRATE = new ModLoadedCondition("quark");
	public static final ModLoadedCondition GOLDEN_APPLE_CRATE = new ModLoadedCondition("quark");

	public AtmosphericRecipeProvider(PackOutput output) {
		super(output);
	}

	@Override
	public void buildRecipes(Consumer<FinishedRecipe> consumer) {
		oneToOneConversionRecipe(consumer, Items.RED_DYE, AtmosphericItems.CARMINE_HUSK.get(), "red_dye");
		oneToOneConversionRecipe(consumer, Items.RED_DYE, FIRETHORN.get(), "red_dye");
		oneToOneConversionRecipe(consumer, Items.YELLOW_DYE, FORSYTHIA.get(), "yellow_dye");
		oneToOneConversionRecipe(consumer, Items.PINK_DYE, AtmosphericItems.DRAGON_FRUIT.get(), "pink_dye");

		trimRecipes(consumer, AtmosphericItems.APOSTLE_ARMOR_TRIM_SMITHING_TEMPLATE.get(), STRIPPED_KOUSA_LOG.get());
		trimRecipes(consumer, AtmosphericItems.DRUID_ARMOR_TRIM_SMITHING_TEMPLATE.get(), RED_ARID_SANDSTONE.get());
		trimRecipes(consumer, AtmosphericItems.PETRIFIED_ARMOR_TRIM_SMITHING_TEMPLATE.get(), ARID_SANDSTONE.get());

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, AtmosphericItems.CURRANT_MUFFIN.get()).requires(AtmosphericItemTags.FRUITS_CURRANT).requires(AtmosphericItemTags.FRUITS_CURRANT).requires(AtmosphericItemTags.FRUITS_CURRANT).requires(Items.SUGAR).requires(Tags.Items.EGGS).unlockedBy("has_currant", has(AtmosphericItemTags.FRUITS_CURRANT)).save(consumer);
		conditionalNineBlockStorageRecipes(consumer, APPLE_CRATE, RecipeCategory.FOOD, AtmosphericItems.CURRANT.get(), RecipeCategory.BUILDING_BLOCKS, CURRANT_CRATE.get());

		nineBlockStorageRecipes(consumer, RecipeCategory.MISC, AtmosphericItems.CARMINE_HUSK.get(), RecipeCategory.BUILDING_BLOCKS, CARMINE_BLOCK.get());
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, CARMINE_SHINGLES.get(), 4).define('#', AtmosphericItems.CARMINE_HUSK.get()).pattern("##").pattern("##").unlockedBy("has_carmine_husk", has(AtmosphericItems.CARMINE_HUSK.get())).save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, CARMINE_PAVEMENT.get(), 4).define('#', CARMINE_SHINGLES.get()).pattern("##").pattern("##").unlockedBy("has_carmine_shingles", has(CARMINE_SHINGLES.get())).save(consumer);

		generateRecipes(consumer, AtmosphericBlockFamilies.CARMINE_SHINGLES_FAMILY);
		stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, CARMINE_SHINGLE_SLAB.get(), CARMINE_SHINGLES.get(), 2);
		stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, CARMINE_SHINGLE_STAIRS.get(), CARMINE_SHINGLES.get());
		stonecutterResultFromBase(consumer, RecipeCategory.DECORATIONS, CARMINE_SHINGLE_WALL.get(), CARMINE_SHINGLES.get());
		stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, CHISELED_CARMINE_SHINGLES.get(), CARMINE_SHINGLES.get());

		generateRecipes(consumer, AtmosphericBlockFamilies.CARMINE_PAVEMENT_FAMILY);
		stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, CARMINE_PAVEMENT_SLAB.get(), CARMINE_PAVEMENT.get(), 2);
		stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, CARMINE_PAVEMENT_STAIRS.get(), CARMINE_PAVEMENT.get());
		stonecutterResultFromBase(consumer, RecipeCategory.DECORATIONS, CARMINE_PAVEMENT_WALL.get(), CARMINE_PAVEMENT.get());
		stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, CARMINE_PAVEMENT.get(), CARMINE_SHINGLES.get());
		stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, CARMINE_PAVEMENT_SLAB.get(), CARMINE_SHINGLES.get(), 2);
		stonecutterResultFromBase(consumer, RecipeCategory.DECORATIONS, CARMINE_PAVEMENT_STAIRS.get(), CARMINE_SHINGLES.get());
		stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, CARMINE_PAVEMENT_WALL.get(), CARMINE_SHINGLES.get());

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AtmosphericItems.COCHINEAL_BANNER_PATTERN.get()).requires(Items.PAPER).requires(AtmosphericItems.CARMINE_HUSK.get()).unlockedBy("has_carmine_husk", has(AtmosphericItems.CARMINE_HUSK.get())).save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, AtmosphericItems.GOLDEN_DRAGON_FRUIT.get()).define('#', Items.GOLD_INGOT).define('X', AtmosphericItems.DRAGON_FRUIT.get()).pattern("###").pattern("#X#").pattern("###").unlockedBy("has_gold_ingot", has(Items.GOLD_INGOT)).save(consumer);
		conditionalNineBlockStorageRecipes(consumer, APPLE_CRATE, RecipeCategory.FOOD, AtmosphericItems.DRAGON_FRUIT.get(), RecipeCategory.BUILDING_BLOCKS, DRAGON_FRUIT_CRATE.get());
		conditionalNineBlockStorageRecipesWithCustomUnpacking(consumer, GOLDEN_APPLE_CRATE, RecipeCategory.FOOD, AtmosphericItems.GOLDEN_DRAGON_FRUIT.get(), RecipeCategory.BUILDING_BLOCKS, GOLDEN_DRAGON_FRUIT_CRATE.get(), "golden_dragon_fruit_from_golden_dragon_fruit_crate", "golden_dragon_fruit");
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, AtmosphericItems.CANDIED_ORANGE_SLICES.get()).requires(AtmosphericItemTags.FRUITS_ORANGE).requires(Items.SUGAR).unlockedBy("has_orange", has(AtmosphericItemTags.FRUITS_ORANGE)).save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, AtmosphericItems.ORANGE_PUDDING.get()).requires(AtmosphericItemTags.FRUITS_ORANGE).requires(Items.SWEET_BERRIES).requires(Items.COCOA_BEANS).requires(Tags.Items.EGGS).requires(BlueprintItemTags.MILK).unlockedBy("has_orange", has(AtmosphericItemTags.FRUITS_ORANGE)).save(consumer);
		conditionalRecipe(consumer, NEAPOLITAN_NOT_LOADED, RecipeCategory.FOOD, ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, AtmosphericItems.ORANGE_SORBET.get()).requires(Items.BOWL).requires(AtmosphericItemTags.FRUITS_ORANGE).requires(Blocks.ICE).requires(Items.SUGAR).unlockedBy("has_orange", has(AtmosphericItemTags.FRUITS_ORANGE)));
		conditionalNineBlockStorageRecipes(consumer, APPLE_CRATE, RecipeCategory.FOOD, AtmosphericItems.ORANGE.get(), RecipeCategory.BUILDING_BLOCKS, ORANGE_CRATE.get());
		conditionalNineBlockStorageRecipes(consumer, APPLE_CRATE, RecipeCategory.FOOD, AtmosphericItems.BLOOD_ORANGE.get(), RecipeCategory.BUILDING_BLOCKS, BLOOD_ORANGE_CRATE.get());

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, DOLERITE.get(), 2).requires(AtmosphericItemTags.TRAVERTINE).requires(Blocks.COBBLESTONE).unlockedBy("has_travertine", has(AtmosphericItemTags.TRAVERTINE)).save(consumer);
		generateRecipes(consumer, AtmosphericBlockFamilies.DOLERITE_FAMILY);
		stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, DOLERITE_SLAB.get(), DOLERITE.get(), 2);
		stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, DOLERITE_STAIRS.get(), DOLERITE.get());
		stonecutterResultFromBase(consumer, RecipeCategory.DECORATIONS, DOLERITE_WALL.get(), DOLERITE.get());
		stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, POLISHED_DOLERITE.get(), DOLERITE.get());
		stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, POLISHED_DOLERITE_SLAB.get(), DOLERITE.get(), 2);
		stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, POLISHED_DOLERITE_STAIRS.get(), DOLERITE.get());
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, POLISHED_DOLERITE.get(), 4).define('#', DOLERITE.get()).pattern("##").pattern("##").unlockedBy("has_dolerite", has(DOLERITE.get())).save(consumer);
		generateRecipes(consumer, AtmosphericBlockFamilies.POLISHED_DOLERITE_FAMILY);
		stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, POLISHED_DOLERITE_SLAB.get(), POLISHED_DOLERITE.get(), 2);
		stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, POLISHED_DOLERITE_STAIRS.get(), POLISHED_DOLERITE.get());

		ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, GRIMWEB.get()).requires(AtmosphericItemTags.GRIMWOOD_LOGS).requires(Items.COBWEB).unlockedBy("has_grimwood", has(AtmosphericItemTags.GRIMWOOD_LOGS)).save(consumer);

		generateRecipes(consumer, AtmosphericBlockFamilies.ROSEWOOD_PLANKS_FAMILY);
		planksFromLogs(consumer, ROSEWOOD_PLANKS.get(), AtmosphericItemTags.ROSEWOOD_LOGS, 4);
		woodFromLogs(consumer, ROSEWOOD.get(), ROSEWOOD_LOG.get());
		woodFromLogs(consumer, STRIPPED_ROSEWOOD.get(), STRIPPED_ROSEWOOD_LOG.get());
		hangingSign(consumer, ROSEWOOD_HANGING_SIGNS.getFirst().get(), STRIPPED_ROSEWOOD_LOG.get());
		BoatloadRecipeProvider.boatRecipes(consumer, AtmosphericBoatTypes.ROSEWOOD);
		WoodworksRecipeProvider.baseRecipes(consumer, ROSEWOOD_PLANKS.get(), ROSEWOOD_SLAB.get(), ROSEWOOD_BOARDS.get(), ROSEWOOD_BOOKSHELF.get(), CHISELED_ROSEWOOD_BOOKSHELF.get(), ROSEWOOD_LADDER.get(), ROSEWOOD_BEEHIVE.get(), ROSEWOOD_CHEST.get(), TRAPPED_ROSEWOOD_CHEST.get(), Atmospheric.MOD_ID);
		WoodworksRecipeProvider.sawmillRecipes(consumer, AtmosphericBlockFamilies.ROSEWOOD_PLANKS_FAMILY, AtmosphericItemTags.ROSEWOOD_LOGS, ROSEWOOD_BOARDS.get(), ROSEWOOD_LADDER.get(), Atmospheric.MOD_ID);
		WoodworksRecipeProvider.leafPileRecipes(consumer, ROSEWOOD_LEAVES.get(), ROSEWOOD_LEAF_PILE.get(), Atmospheric.MOD_ID);

		conditionalNineBlockStorageRecipes(consumer, APPLE_CRATE, RecipeCategory.FOOD, AtmosphericItems.PASSION_FRUIT.get(), RecipeCategory.BUILDING_BLOCKS, PASSION_FRUIT_CRATE.get());
		conditionalNineBlockStorageRecipesWithCustomUnpacking(consumer, GOLDEN_APPLE_CRATE, RecipeCategory.FOOD, AtmosphericItems.SHIMMERING_PASSION_FRUIT.get(), RecipeCategory.BUILDING_BLOCKS, SHIMMERING_PASSION_FRUIT_CRATE.get(), "shimmering_passion_fruit_from_shimmering_passion_fruit_crate", "shimmering_passion_fruit");

		generateRecipes(consumer, AtmosphericBlockFamilies.MORADO_PLANKS_FAMILY);
		planksFromLogs(consumer, MORADO_PLANKS.get(), AtmosphericItemTags.MORADO_LOGS, 4);
		woodFromLogs(consumer, MORADO_WOOD.get(), MORADO_LOG.get());
		woodFromLogs(consumer, STRIPPED_MORADO_WOOD.get(), STRIPPED_MORADO_LOG.get());
		hangingSign(consumer, MORADO_HANGING_SIGNS.getFirst().get(), STRIPPED_MORADO_LOG.get());
		BoatloadRecipeProvider.boatRecipes(consumer, AtmosphericBoatTypes.MORADO);
		WoodworksRecipeProvider.baseRecipes(consumer, MORADO_PLANKS.get(), MORADO_SLAB.get(), MORADO_BOARDS.get(), MORADO_BOOKSHELF.get(), CHISELED_MORADO_BOOKSHELF.get(), MORADO_LADDER.get(), MORADO_BEEHIVE.get(), MORADO_CHEST.get(), TRAPPED_MORADO_CHEST.get(), Atmospheric.MOD_ID);
		WoodworksRecipeProvider.sawmillRecipes(consumer, AtmosphericBlockFamilies.MORADO_PLANKS_FAMILY, AtmosphericItemTags.MORADO_LOGS, MORADO_BOARDS.get(), MORADO_LADDER.get(), Atmospheric.MOD_ID);
		WoodworksRecipeProvider.leafPileRecipes(consumer, MORADO_LEAVES.get(), MORADO_LEAF_PILE.get(), Atmospheric.MOD_ID);
		WoodworksRecipeProvider.leafPileRecipes(consumer, FLOWERING_MORADO_LEAVES.get(), FLOWERING_MORADO_LEAVES.get(), Atmospheric.MOD_ID);

		generateRecipes(consumer, AtmosphericBlockFamilies.YUCCA_PLANKS_FAMILY);
		planksFromLogs(consumer, YUCCA_PLANKS.get(), AtmosphericItemTags.YUCCA_LOGS, 4);
		woodFromLogs(consumer, YUCCA_WOOD.get(), YUCCA_LOG.get());
		woodFromLogs(consumer, STRIPPED_YUCCA_WOOD.get(), STRIPPED_YUCCA_LOG.get());
		hangingSign(consumer, YUCCA_HANGING_SIGNS.getFirst().get(), STRIPPED_YUCCA_LOG.get());
		BoatloadRecipeProvider.boatRecipes(consumer, AtmosphericBoatTypes.YUCCA);
		WoodworksRecipeProvider.baseRecipes(consumer, YUCCA_PLANKS.get(), YUCCA_SLAB.get(), YUCCA_BOARDS.get(), YUCCA_BOOKSHELF.get(), CHISELED_YUCCA_BOOKSHELF.get(), YUCCA_LADDER.get(), YUCCA_BEEHIVE.get(), YUCCA_CHEST.get(), TRAPPED_YUCCA_CHEST.get(), Atmospheric.MOD_ID);
		WoodworksRecipeProvider.sawmillRecipes(consumer, AtmosphericBlockFamilies.YUCCA_PLANKS_FAMILY, AtmosphericItemTags.YUCCA_LOGS, YUCCA_BOARDS.get(), YUCCA_LADDER.get(), Atmospheric.MOD_ID);
		WoodworksRecipeProvider.leafPileRecipes(consumer, YUCCA_LEAVES.get(), YUCCA_LEAF_PILE.get(), Atmospheric.MOD_ID);

		conditionalNineBlockStorageRecipes(consumer, APPLE_CRATE, RecipeCategory.FOOD, AtmosphericItems.YUCCA_FRUIT.get(), RecipeCategory.BUILDING_BLOCKS, YUCCA_CASK.get());
		conditionalNineBlockStorageRecipesWithCustomUnpacking(consumer, APPLE_CRATE, RecipeCategory.FOOD, AtmosphericItems.ROASTED_YUCCA_FRUIT.get(), RecipeCategory.BUILDING_BLOCKS, ROASTED_YUCCA_CASK.get(), "roasted_yucca_fruit_from_roasted_yucca_cask", "roasted_yucca_fruit");

		generateRecipes(consumer, AtmosphericBlockFamilies.ASPEN_PLANKS_FAMILY);
		planksFromLogs(consumer, ASPEN_PLANKS.get(), AtmosphericItemTags.ASPEN_LOGS, 4);
		woodFromLogs(consumer, ASPEN_WOOD.get(), ASPEN_LOG.get());
		woodFromLogs(consumer, STRIPPED_ASPEN_WOOD.get(), STRIPPED_ASPEN_LOG.get());
		woodFromLogs(consumer, WATCHFUL_ASPEN_WOOD.get(), WATCHFUL_ASPEN_LOG.get());
		woodFromLogs(consumer, CRUSTOSE_WOOD.get(), CRUSTOSE_LOG.get());
		hangingSign(consumer, ASPEN_HANGING_SIGNS.getFirst().get(), STRIPPED_ASPEN_LOG.get());
		BoatloadRecipeProvider.boatRecipes(consumer, AtmosphericBoatTypes.ASPEN);
		WoodworksRecipeProvider.baseRecipes(consumer, ASPEN_PLANKS.get(), ASPEN_SLAB.get(), ASPEN_BOARDS.get(), ASPEN_BOOKSHELF.get(), CHISELED_ASPEN_BOOKSHELF.get(), ASPEN_LADDER.get(), ASPEN_BEEHIVE.get(), ASPEN_CHEST.get(), TRAPPED_ASPEN_CHEST.get(), Atmospheric.MOD_ID);
		WoodworksRecipeProvider.sawmillRecipes(consumer, AtmosphericBlockFamilies.ASPEN_PLANKS_FAMILY, AtmosphericItemTags.ASPEN_LOGS, ASPEN_BOARDS.get(), ASPEN_LADDER.get(), Atmospheric.MOD_ID);
		WoodworksRecipeProvider.leafPileRecipes(consumer, ASPEN_LEAVES.get(), ASPEN_LEAF_PILE.get(), Atmospheric.MOD_ID);
		WoodworksRecipeProvider.leafPileRecipes(consumer, GREEN_ASPEN_LEAVES.get(), GREEN_ASPEN_LEAF_PILE.get(), Atmospheric.MOD_ID);

		generateRecipes(consumer, AtmosphericBlockFamilies.LAUREL_PLANKS_FAMILY);
		planksFromLogs(consumer, LAUREL_PLANKS.get(), AtmosphericItemTags.LAUREL_LOGS, 4);
		woodFromLogs(consumer, LAUREL_WOOD.get(), LAUREL_LOG.get());
		woodFromLogs(consumer, STRIPPED_LAUREL_WOOD.get(), STRIPPED_LAUREL_LOG.get());
		hangingSign(consumer, LAUREL_HANGING_SIGNS.getFirst().get(), STRIPPED_LAUREL_LOG.get());
		BoatloadRecipeProvider.boatRecipes(consumer, AtmosphericBoatTypes.LAUREL);
		WoodworksRecipeProvider.baseRecipes(consumer, LAUREL_PLANKS.get(), LAUREL_SLAB.get(), LAUREL_BOARDS.get(), LAUREL_BOOKSHELF.get(), CHISELED_LAUREL_BOOKSHELF.get(), LAUREL_LADDER.get(), LAUREL_BEEHIVE.get(), LAUREL_CHEST.get(), TRAPPED_LAUREL_CHEST.get(), Atmospheric.MOD_ID);
		WoodworksRecipeProvider.sawmillRecipes(consumer, AtmosphericBlockFamilies.LAUREL_PLANKS_FAMILY, AtmosphericItemTags.LAUREL_LOGS, LAUREL_BOARDS.get(), LAUREL_LADDER.get(), Atmospheric.MOD_ID);
		WoodworksRecipeProvider.leafPileRecipes(consumer, LAUREL_LEAVES.get(), LAUREL_LEAF_PILE.get(), Atmospheric.MOD_ID);
		WoodworksRecipeProvider.leafPileRecipes(consumer, DRY_LAUREL_LEAVES.get(), DRY_LAUREL_LEAF_PILE.get(), Atmospheric.MOD_ID);

		generateRecipes(consumer, AtmosphericBlockFamilies.KOUSA_PLANKS_FAMILY);
		planksFromLogs(consumer, KOUSA_PLANKS.get(), AtmosphericItemTags.KOUSA_LOGS, 4);
		woodFromLogs(consumer, KOUSA_WOOD.get(), KOUSA_LOG.get());
		woodFromLogs(consumer, STRIPPED_KOUSA_WOOD.get(), STRIPPED_KOUSA_LOG.get());
		hangingSign(consumer, KOUSA_HANGING_SIGNS.getFirst().get(), STRIPPED_KOUSA_LOG.get());
		BoatloadRecipeProvider.boatRecipes(consumer, AtmosphericBoatTypes.KOUSA);
		WoodworksRecipeProvider.baseRecipes(consumer, KOUSA_PLANKS.get(), KOUSA_SLAB.get(), KOUSA_BOARDS.get(), KOUSA_BOOKSHELF.get(), CHISELED_KOUSA_BOOKSHELF.get(), KOUSA_LADDER.get(), KOUSA_BEEHIVE.get(), KOUSA_CHEST.get(), TRAPPED_KOUSA_CHEST.get(), Atmospheric.MOD_ID);
		WoodworksRecipeProvider.sawmillRecipes(consumer, AtmosphericBlockFamilies.KOUSA_PLANKS_FAMILY, AtmosphericItemTags.KOUSA_LOGS, KOUSA_BOARDS.get(), KOUSA_LADDER.get(), Atmospheric.MOD_ID);
		WoodworksRecipeProvider.leafPileRecipes(consumer, KOUSA_LEAVES.get(), KOUSA_LEAF_PILE.get(), Atmospheric.MOD_ID);

		WoodworksRecipeProvider.leafPileRecipes(consumer, CURRANT_LEAVES.get(), CURRANT_LEAF_PILE.get(), Atmospheric.MOD_ID);

		generateRecipes(consumer, AtmosphericBlockFamilies.GRIMWOOD_PLANKS_FAMILY);
		planksFromLogs(consumer, GRIMWOOD_PLANKS.get(), AtmosphericItemTags.GRIMWOOD_LOGS, 4);
		woodFromLogs(consumer, GRIMWOOD.get(), GRIMWOOD_LOG.get());
		woodFromLogs(consumer, STRIPPED_GRIMWOOD.get(), STRIPPED_GRIMWOOD_LOG.get());
		hangingSign(consumer, GRIMWOOD_HANGING_SIGNS.getFirst().get(), STRIPPED_GRIMWOOD_LOG.get());
		BoatloadRecipeProvider.boatRecipes(consumer, AtmosphericBoatTypes.GRIMWOOD);
		WoodworksRecipeProvider.baseRecipes(consumer, GRIMWOOD_PLANKS.get(), GRIMWOOD_SLAB.get(), GRIMWOOD_BOARDS.get(), GRIMWOOD_BOOKSHELF.get(), CHISELED_GRIMWOOD_BOOKSHELF.get(), GRIMWOOD_LADDER.get(), GRIMWOOD_BEEHIVE.get(), GRIMWOOD_CHEST.get(), TRAPPED_GRIMWOOD_CHEST.get(), Atmospheric.MOD_ID);
		WoodworksRecipeProvider.sawmillRecipes(consumer, AtmosphericBlockFamilies.GRIMWOOD_PLANKS_FAMILY, AtmosphericItemTags.GRIMWOOD_LOGS, GRIMWOOD_BOARDS.get(), GRIMWOOD_LADDER.get(), Atmospheric.MOD_ID);
		WoodworksRecipeProvider.leafPileRecipes(consumer, GRIMWOOD_LEAVES.get(), GRIMWOOD_LEAF_PILE.get(), Atmospheric.MOD_ID);
	}

	public static void nineBlockStorageRecipes(Consumer<FinishedRecipe> consumer, RecipeCategory itemCategory, ItemLike item, RecipeCategory storageCategory, ItemLike storage) {
		nineBlockStorageRecipes(consumer, itemCategory, item, storageCategory, storage, Atmospheric.MOD_ID + ":" + getSimpleRecipeName(storage), null, Atmospheric.MOD_ID + ":" + getSimpleRecipeName(item), null);
	}

	public static void oneToOneConversionRecipe(Consumer<FinishedRecipe> consumer, ItemLike input, ItemLike output, @Nullable String group) {
		oneToOneConversionRecipe(consumer, input, output, group, 1);
	}

	public static void oneToOneConversionRecipe(Consumer<FinishedRecipe> consumer, ItemLike input, ItemLike output, @Nullable String group, int count) {
		oneToOneConversionRecipe(consumer, RecipeCategory.MISC, input, output, group, count);
	}

	public static void oneToOneConversionRecipe(Consumer<FinishedRecipe> consumer, RecipeCategory category, ItemLike input, ItemLike output, @Nullable String group, int count) {
		ShapelessRecipeBuilder.shapeless(category, input, count).requires(output).group(group).unlockedBy(getHasName(output), has(output)).save(consumer, getModConversionRecipeName(input, output));
	}

	private static ResourceLocation getModConversionRecipeName(ItemLike result, ItemLike input) {
		return Atmospheric.location(getConversionRecipeName(result, input));
	}

	public static void conditionalNineBlockStorageRecipesWithCustomUnpacking(Consumer<FinishedRecipe> consumer, ICondition condition, RecipeCategory itemCategory, ItemLike item, RecipeCategory storageCategory, ItemLike storage, String shapelessName, String shapelessGroup) {
		conditionalNineBlockStorageRecipes(consumer, condition, itemCategory, item, storageCategory, storage, Atmospheric.MOD_ID + ":" + getSimpleRecipeName(storage), null, Atmospheric.MOD_ID + ":" + shapelessName, shapelessGroup);
	}

	public static void conditionalNineBlockStorageRecipes(Consumer<FinishedRecipe> consumer, ICondition condition, RecipeCategory itemCategory, ItemLike item, RecipeCategory storageCategory, ItemLike storage) {
		conditionalNineBlockStorageRecipes(consumer, condition, itemCategory, item, storageCategory, storage, Atmospheric.MOD_ID + ":" + getSimpleRecipeName(storage), null, Atmospheric.MOD_ID + ":" + getSimpleRecipeName(item), null);
	}

	protected static void conditionalNineBlockStorageRecipes(Consumer<FinishedRecipe> consumer, ICondition condition, RecipeCategory itemCategory, ItemLike item, RecipeCategory storageCategory, ItemLike storage, String storageLocation, @Nullable String itemGroup, String itemLocation, @Nullable String storageGroup) {
		conditionalRecipe(consumer, condition, itemCategory, ShapelessRecipeBuilder.shapeless(itemCategory, item, 9).requires(storage).group(storageGroup).unlockedBy(getHasName(storage), has(storage)), new ResourceLocation(itemLocation));
		conditionalRecipe(consumer, condition, storageCategory, ShapedRecipeBuilder.shaped(storageCategory, storage).define('#', item).pattern("###").pattern("###").pattern("###").group(itemGroup).unlockedBy(getHasName(item), has(item)), new ResourceLocation(storageLocation));
	}

	public static void nineBlockStorageRecipesRecipesWithCustomUnpacking(Consumer<FinishedRecipe> p_176617_, RecipeCategory itemCategory, ItemLike item, RecipeCategory blockCategory, ItemLike p_176619_, String p_176620_, String p_176621_) {
		nineBlockStorageRecipes(p_176617_, itemCategory, item, blockCategory, p_176619_, getSimpleRecipeName(p_176619_), null, p_176620_, p_176621_);
	}

	public static void conditionalRecipe(Consumer<FinishedRecipe> consumer, ICondition condition, RecipeCategory category, RecipeBuilder recipe) {
		conditionalRecipe(consumer, condition, category, recipe, RecipeBuilder.getDefaultRecipeId(recipe.getResult()));
	}

	public static void conditionalRecipe(Consumer<FinishedRecipe> consumer, ICondition condition, RecipeCategory category, RecipeBuilder recipe, ResourceLocation id) {
		ConditionalRecipe.builder().addCondition(condition).addRecipe(consumer1 -> recipe.save(consumer1, id)).generateAdvancement(new ResourceLocation(id.getNamespace(), "recipes/" + category.getFolderName() + "/" + id.getPath())).build(consumer, id);
	}

	public static void stonecutterResultFromBase(Consumer<FinishedRecipe> p_176736_, RecipeCategory category, ItemLike p_176737_, ItemLike p_176738_) {
		stonecutterResultFromBase(p_176736_, category, p_176737_, p_176738_, 1);
	}

	public static void stonecutterResultFromBase(Consumer<FinishedRecipe> p_176547_, RecipeCategory category, ItemLike p_176548_, ItemLike p_176549_, int p_176550_) {
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(p_176549_), category, p_176548_, p_176550_).unlockedBy(getHasName(p_176549_), has(p_176549_)).save(p_176547_, getModConversionRecipeName(p_176548_, p_176549_) + "_stonecutting");
	}

	private static ResourceLocation suffix(ResourceLocation rl, String suffix) {
		return new ResourceLocation(rl.getNamespace(), rl.getPath() + suffix);
	}

	public static void trimSmithing(Consumer<FinishedRecipe> consumer, ItemLike item) {
		SmithingTrimRecipeBuilder.smithingTrim(Ingredient.of(item), Ingredient.of(ItemTags.TRIMMABLE_ARMOR), Ingredient.of(ItemTags.TRIM_MATERIALS), RecipeCategory.MISC).unlocks("has_smithing_trim_template", has(item)).save(consumer, suffix(RecipeBuilder.getDefaultRecipeId(item), "_smithing_trim"));
	}

	public static void trimRecipes(Consumer<FinishedRecipe> consumer, ItemLike item, TagKey<Item> copyItem) {
		trimSmithing(consumer, item);
		copySmithingTemplate(consumer, item, copyItem);
	}

	public static void trimRecipes(Consumer<FinishedRecipe> consumer, ItemLike item, ItemLike copyItem) {
		trimSmithing(consumer, item);
		copySmithingTemplate(consumer, item, copyItem);
	}
}
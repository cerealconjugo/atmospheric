package com.teamabnormals.atmospheric.core.data.server;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.other.AtmosphericBlockFamilies;
import com.teamabnormals.atmospheric.core.other.tags.AtmosphericItemTags;
import com.teamabnormals.atmospheric.core.registry.AtmosphericItems;
import com.teamabnormals.blueprint.core.other.tags.BlueprintItemTags;
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

		hangingSign(consumer, ROSEWOOD_HANGING_SIGNS.getFirst().get(), STRIPPED_ROSEWOOD_LOG.get());
		hangingSign(consumer, MORADO_HANGING_SIGNS.getFirst().get(), STRIPPED_MORADO_LOG.get());
		hangingSign(consumer, YUCCA_HANGING_SIGNS.getFirst().get(), STRIPPED_YUCCA_LOG.get());
		hangingSign(consumer, ASPEN_HANGING_SIGNS.getFirst().get(), STRIPPED_ASPEN_LOG.get());
		hangingSign(consumer, LAUREL_HANGING_SIGNS.getFirst().get(), STRIPPED_LAUREL_LOG.get());
		hangingSign(consumer, KOUSA_HANGING_SIGNS.getFirst().get(), STRIPPED_KOUSA_LOG.get());
		hangingSign(consumer, GRIMWOOD_HANGING_SIGNS.getFirst().get(), STRIPPED_GRIMWOOD_LOG.get());
	}

	public static ShapelessRecipeBuilder oneToOneConversionRecipe(RecipeCategory category, ItemLike output, ItemLike input, int count) {
		return ShapelessRecipeBuilder.shapeless(category, output, count).requires(input).unlockedBy(getHasName(input), has(input));
	}

	private static ResourceLocation getModConversionRecipeName(ItemLike result, ItemLike input) {
		return Atmospheric.location(getConversionRecipeName(result, input));
	}


	public static void conditionalNineBlockStorageRecipesWithCustomUnpacking(Consumer<FinishedRecipe> consumer, ICondition condition, RecipeCategory itemCategory, ItemLike item, RecipeCategory storageCategory, ItemLike storage, String shapelessName, String shapelessGroup) {
		conditionalNineBlockStorageRecipes(consumer, condition, itemCategory, item, storageCategory, storage, Atmospheric.MOD_ID + ":" + getSimpleRecipeName(storage), null, shapelessName, shapelessGroup);
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
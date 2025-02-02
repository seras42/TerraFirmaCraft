/*
 * Licensed under the EUPL, Version 1.2.
 * You may obtain a copy of the Licence at:
 * https://joinup.ec.europa.eu/collection/eupl/eupl-text-eupl-12
 */

package net.dries007.tfc.compat.jei.category;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import net.dries007.tfc.common.capabilities.food.Nutrient;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.common.recipes.PotRecipe;

public class SoupPotRecipeCategory extends PotRecipeCategory<PotRecipe>
{
    public SoupPotRecipeCategory(RecipeType<PotRecipe> type, IGuiHelper helper)
    {
        super(type, helper, helper.createBlankDrawable(175, 80));
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, PotRecipe recipe, IFocusGroup focuses)
    {
        int i = 0;
        for (Ingredient ingredient : recipe.getItemIngredients())
        {
            if (!ingredient.isEmpty())
            {
                IRecipeSlotBuilder inputSlot = builder.addSlot(RecipeIngredientRole.INPUT, 6 + 20 * i, 6);
                inputSlot.addIngredients(ingredient);
                i++;
            }
        }

        IRecipeSlotBuilder inputFluid = builder.addSlot(RecipeIngredientRole.INPUT, 46, 26);
        inputFluid.addIngredients(VanillaTypes.FLUID, collapse(recipe.getFluidIngredient()));
        inputFluid.setFluidRenderer(1, false, 16, 16);

        IRecipeSlotBuilder outputItem = builder.addSlot(RecipeIngredientRole.OUTPUT, 126, 6);
        outputItem.addItemStacks(collapse(TFCItems.SOUPS));
    }

    @Override
    public void draw(PotRecipe recipe, IRecipeSlotsView recipeSlots, PoseStack stack, double mouseX, double mouseY)
    {
        // Water Input
        slot.draw(stack, 45, 25);
        // Input Items
        slot.draw(stack, 5, 5);
        slot.draw(stack, 25, 5);
        slot.draw(stack, 45, 5);
        slot.draw(stack, 65, 5);
        slot.draw(stack, 85, 5);
        // fire
        fire.draw(stack, 47, 45);
        fireAnimated.draw(stack, 47, 45);
        // arrow
        arrow.draw(stack, 103, 26);
        arrowAnimated.draw(stack, 103, 26);
        // Output Item
        slot.draw(stack, 125, 5);
    }
}

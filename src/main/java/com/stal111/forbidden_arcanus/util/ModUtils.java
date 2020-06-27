package com.stal111.forbidden_arcanus.util;

import com.google.common.collect.Maps;
import com.stal111.forbidden_arcanus.Main;
import net.minecraft.block.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.server.ServerWorld;
import org.lwjgl.glfw.GLFW;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ModUtils {

    protected static final Random random = new Random();

    public static ResourceLocation location(String path) {
        return new ResourceLocation(Main.MOD_ID, path);
    }

    public static boolean isShiftDown() {
        return InputMappings.isKeyDown(GLFW.glfwGetCurrentContext(), GLFW.GLFW_KEY_LEFT_SHIFT) || InputMappings.isKeyDown(GLFW.glfwGetCurrentContext(), GLFW.GLFW_KEY_RIGHT_SHIFT);
    }

    public static boolean applyBonemeal(ItemStack stack, ServerWorld worldIn, BlockPos pos, net.minecraft.entity.player.PlayerEntity player) {
        BlockState blockstate = worldIn.getBlockState(pos);
        int hook = net.minecraftforge.event.ForgeEventFactory.onApplyBonemeal(player, worldIn, pos, blockstate, stack);
        if (hook != 0) return hook > 0;
        if (blockstate.getBlock() instanceof IGrowable) {
            IGrowable igrowable = (IGrowable) blockstate.getBlock();
            if (igrowable.canGrow(worldIn, pos, blockstate, worldIn.isRemote)) {
                if (!worldIn.isRemote) {
                    if (igrowable.canUseBonemeal(worldIn, worldIn.rand, pos, blockstate)) {
                        igrowable.grow(worldIn, worldIn.rand, pos, blockstate);
                    }
                }
                return true;
            }
        }
        return false;
    }

    public static void addStrippable(Block block, Block strippedBlock) {
        AxeItem.BLOCK_STRIPPING_MAP = Maps.newHashMap(AxeItem.BLOCK_STRIPPING_MAP);
        AxeItem.BLOCK_STRIPPING_MAP.put(block, strippedBlock);
    }

    public static List<?> removeLastFromList(List<?> list) {
        ArrayList list1 = new ArrayList();
        for (int i = 0; i < list.size() - 1; i++) {
            list1.add(list.get(i));
        }
        return list1;
    }

    public static void shrinkAndAddStack(PlayerEntity player, Hand hand, ItemStack stack, ItemStack newStack) {
        stack.shrink(1);
        if (stack.isEmpty()) {
            player.setHeldItem(hand, newStack);
        } else if (!player.inventory.addItemStackToInventory(newStack)) {
            player.dropItem(newStack, false);
        }
    }

    public static ScorePlayerTeam createTeam(Scoreboard scoreboard, String name, TextFormatting color) {
        if (scoreboard.getTeamNames().contains(name)) {
            return scoreboard.getTeam(name);
        } else {
            ScorePlayerTeam team = scoreboard.createTeam(name);
            team.setDisplayName(new StringTextComponent(name));
            team.setColor(color);
            return team;
        }
    }

    public static void removeTeam(Scoreboard scoreboard, ScorePlayerTeam team) {
        if (scoreboard.getTeamNames().contains(team.getName())) {
            scoreboard.removeTeam(team);
        }
    }

    public static Collection<IRecipe<?>> getRecipesByOutput(ItemStack targetOutput, Collection<IRecipeSerializer<?>> recipeSerializer) {
        return Minecraft.getInstance().world.getRecipeManager().getRecipes().stream()
                .filter(r -> {
                    boolean flag = false;
                    for (IRecipeSerializer<?> serializer : recipeSerializer) {
                        if (serializer == r.getSerializer()) {
                            flag = true;
                        }
                    }
                    return !r.isDynamic() && flag && ItemStack.areItemsEqualIgnoreDurability(targetOutput, r.getRecipeOutput());
                }).collect(Collectors.toList());
    }

    public static Collection<IRecipe<?>> getCraftingRecipesByOutput(@Nonnull ItemStack targetOutput) {
        return Minecraft.getInstance().world.getRecipeManager().getRecipes().stream()
                .filter(r -> !r.isDynamic() && (r.getSerializer() == IRecipeSerializer.CRAFTING_SHAPED || r.getSerializer() == IRecipeSerializer.CRAFTING_SHAPELESS) && ItemStack.areItemsEqualIgnoreDurability(targetOutput, r.getRecipeOutput())).collect(Collectors.toList());
    }

    public static Collection<IRecipe<?>> getSmeltingRecipesByOutput(@Nonnull ItemStack targetOutput) {
        return Minecraft.getInstance().world.getRecipeManager().getRecipes().stream()
                .filter(r -> !r.isDynamic() && r.getSerializer() == IRecipeSerializer.SMELTING && ItemStack.areItemsEqualIgnoreDurability(targetOutput, r.getRecipeOutput())).collect(Collectors.toList());
    }
}

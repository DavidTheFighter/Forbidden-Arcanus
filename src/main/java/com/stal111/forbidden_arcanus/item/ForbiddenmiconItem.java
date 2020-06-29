package com.stal111.forbidden_arcanus.item;

import com.stal111.forbidden_arcanus.Main;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ForbiddenmiconItem extends Item {

    public ForbiddenmiconItem(Properties properties) {
        super(properties);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> list, ITooltipFlag flag) {
        list.add(new TranslationTextComponent("tooltip." + Main.MOD_ID + ".forbiddenmicon").func_240699_a_(TextFormatting.GRAY));
        super.addInformation(stack, world, list, flag);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getHeldItem(hand);
        if (world.isRemote()) {
            Main.proxy.displayForbiddenmiconScreen(stack);
        }
        setOpen(stack, true);
        return ActionResult.resultSuccess(stack);
    }

    public static boolean isOpen(ItemStack stack) {
        CompoundNBT compoundnbt = stack.getTag();
        return compoundnbt != null && compoundnbt.getBoolean("Open");
    }

    public static void setOpen(ItemStack stack, boolean open) {
        CompoundNBT compoundnbt = stack.getOrCreateTag();
        compoundnbt.putBoolean("Open", open);
    }
}

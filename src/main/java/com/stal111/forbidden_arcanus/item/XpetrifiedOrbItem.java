package com.stal111.forbidden_arcanus.item;

import com.stal111.forbidden_arcanus.config.ItemConfig;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class XpetrifiedOrbItem extends Item {

    public XpetrifiedOrbItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getHeldItem(hand);

        player.giveExperiencePoints(ItemConfig.XPETRIFIED_ORB_EXPERIENCE_POINTS.get());

        if (!player.abilities.isCreativeMode) {
            stack.shrink(1);
        }
        return new ActionResult<>(ActionResultType.SUCCESS, stack);
    }
}

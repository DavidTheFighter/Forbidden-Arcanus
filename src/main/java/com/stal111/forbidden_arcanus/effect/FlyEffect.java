package com.stal111.forbidden_arcanus.effect;

import com.stal111.forbidden_arcanus.init.ModEffects;
import com.stal111.forbidden_arcanus.util.ModUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;

public class FlyEffect extends Effect {

    public FlyEffect(EffectType effectType, int p_i50391_2_) {
        super(effectType, p_i50391_2_);
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }

    @Override
    public void performEffect(LivingEntity livingEntity, int amplifier) {
        int i = livingEntity.getActivePotionEffect(ModEffects.FLY.get()).getDuration();
        if (livingEntity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) livingEntity;
            if (i <= 30 && !player.abilities.isCreativeMode) {
                player.abilities.allowFlying = false;
                player.abilities.isFlying = false;
            } else {
                player.abilities.allowFlying = true;
            }
        }
    }

//    @Override
//    public void renderHUDEffect(EffectInstance effect, AbstractGui gui, int x, int y, float z, float alpha) {
//        Minecraft.getInstance().getRenderManager().textureManager.bindTexture(ModUtils.location("textures/mob_effect/fly.png"));
//    }
}

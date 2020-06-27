package com.stal111.forbidden_arcanus.particle;

import com.stal111.forbidden_arcanus.init.ModItems;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class SoulParticle extends SpriteTexturedParticle {

    private static final Random RANDOM = new Random();
    private final IAnimatedSprite field_217586_F;

    private SoulParticle(ClientWorld world, double p_i51008_2_, double p_i51008_4_, double p_i51008_6_, double p_i51008_8_, double p_i51008_10_, double p_i51008_12_, IAnimatedSprite p_i51008_14_) {
        super(world, p_i51008_2_, p_i51008_4_, p_i51008_6_, 0.5D - RANDOM.nextDouble(), p_i51008_10_, 0.5D - RANDOM.nextDouble());
        this.field_217586_F = p_i51008_14_;
        this.motionY *= 0.20000000298023224D;
        if (p_i51008_8_ == 0.0D && p_i51008_12_ == 0.0D) {
            this.motionX *= 0.10000000149011612D;
            this.motionZ *= 0.10000000149011612D;
        }

        this.particleScale *= 1.1F;
        this.maxAge = (int)(9.5D / (Math.random() * 0.8D + 0.2D));
        this.canCollide = false;
        this.selectSpriteWithAge(p_i51008_14_);
    }

    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public void tick() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        if (this.age++ >= this.maxAge) {
            this.setExpired();
        } else {
            this.selectSpriteWithAge(this.field_217586_F);
            this.motionY += 0.004D;
            this.move(this.motionX, this.motionY, this.motionZ);
            if (this.posY == this.prevPosY) {
                this.motionX *= 1.1D;
                this.motionZ *= 1.1D;
            }

            this.motionX *= 0.9599999785423279D;
            this.motionY *= 0.9599999785423279D;
            this.motionZ *= 0.9599999785423279D;
            if (this.onGround) {
                this.motionX *= 0.699999988079071D;
                this.motionZ *= 0.699999988079071D;
            }

        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite spriteSet;

        public Factory(IAnimatedSprite p_i50843_1_) {
            this.spriteSet = p_i50843_1_;
        }


        @Nullable
        @Override
        public Particle makeParticle(BasicParticleType type, ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new SoulParticle(world, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
        }
    }
}

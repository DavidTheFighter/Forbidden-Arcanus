package com.stal111.forbidden_arcanus.client.event;

import com.stal111.forbidden_arcanus.client.model.MagicCircleModel;
import com.stal111.forbidden_arcanus.client.renderer.block.*;
import com.stal111.forbidden_arcanus.client.renderer.entity.BoomArrowRenderer;
import com.stal111.forbidden_arcanus.client.renderer.entity.CrimsonLightningBoltRenderer;
import com.stal111.forbidden_arcanus.client.renderer.entity.DracoArcanusArrowRenderer;
import com.stal111.forbidden_arcanus.client.renderer.entity.EnergyBallRenderer;
import com.stal111.forbidden_arcanus.core.init.ModBlockEntities;
import com.stal111.forbidden_arcanus.core.init.ModEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Entity Renderer Events <br>
 * Forbidden Arcanus - com.stal111.forbidden_arcanus.client.event.EntityRendererEvents
 *
 * @author stal111
 * @version 2.0.0
 * @since 2021-11-28
 */
@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityRendererEvents {

    @SubscribeEvent
    public static void onRegisterRenders(EntityRenderersEvent.RegisterRenderers event) {
        // Block Entities
        event.registerBlockEntityRenderer(ModBlockEntities.NIPA.get(), NipaRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.PEDESTAL.get(), PedestalRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.BLACK_HOLE.get(), BlackHoleRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.UTREM_JAR.get(), UtremJarRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.OBSIDIAN_SKULL.get(), ObsidianSkullRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.HEPHAESTUS_FORGE.get(), HephaestusForgeRenderer::new);

        // Entities
        event.registerEntityRenderer(ModEntities.BOOM_ARROW.get(), BoomArrowRenderer::new);
        event.registerEntityRenderer(ModEntities.DRACO_ARCANUS_ARROW.get(), DracoArcanusArrowRenderer::new);
        event.registerEntityRenderer(ModEntities.ENERGY_BALL.get(), EnergyBallRenderer::new);
        event.registerEntityRenderer(ModEntities.CRIMSON_LIGHTNING_BOLT.get(), CrimsonLightningBoltRenderer::new);
    }

    @SubscribeEvent
    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(BlackHoleRenderer.BLACK_HOLE_LAYER, BlackHoleRenderer::createHoleLayer);
        event.registerLayerDefinition(BlackHoleRenderer.BLACK_HOLE_AURA_LAYER, BlackHoleRenderer::createAuraLayer);
        event.registerLayerDefinition(ObsidianSkullRenderer.OBSIDIAN_SKULL_LAYER, ObsidianSkullRenderer::createObsidianSkullLayer);
        event.registerLayerDefinition(ObsidianSkullRenderer.ETERNAL_OBSIDIAN_SKULL_LAYER, ObsidianSkullRenderer::createEternalObsidianSkullLayer);
        event.registerLayerDefinition(MagicCircleModel.OUTER_RING_LAYER, MagicCircleModel::createLayer);
        event.registerLayerDefinition(MagicCircleModel.INNER_RING_LAYER, MagicCircleModel::createLayer);
    }
}

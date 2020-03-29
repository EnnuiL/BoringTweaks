package io.github.joaoh1.boringtweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.joaoh1.boringtweaks.render.StriderSunglassesFeatureRenderer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.StriderEntityRenderer;
import net.minecraft.client.render.entity.model.StriderEntityModel;
import net.minecraft.entity.passive.StriderEntity;

@Mixin(StriderEntityRenderer.class)
public abstract class StriderEntityRendererMixin extends MobEntityRenderer<StriderEntity, StriderEntityModel<StriderEntity>> {
    public StriderEntityRendererMixin(EntityRenderDispatcher entityRenderDispatcher, StriderEntityModel<StriderEntity> entityModel, float f) {
        super(entityRenderDispatcher, entityModel, f);
    }
    
    @Inject(method = "<init>(Lnet/minecraft/client/render/entity/EntityRenderDispatcher;)V", at = @At("TAIL"))
    private void init(CallbackInfo info) {
        this.addFeature(new StriderSunglassesFeatureRenderer(this));
	}
}
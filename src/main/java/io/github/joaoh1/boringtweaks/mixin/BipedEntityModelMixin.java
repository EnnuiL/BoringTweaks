package io.github.joaoh1.boringtweaks.mixin;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;

import com.google.common.collect.ImmutableList;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.joaoh1.boringtweaks.config.BoringTweaksConfigPojo;

@Mixin(BipedEntityModel.class)
public class BipedEntityModelMixin {
	@Shadow
	public ModelPart head;
	
	@Shadow
	public ModelPart helmet;
	
	@Inject(at = @At("HEAD"), method = "getHeadParts()Ljava/lang/Iterable;", cancellable = true)
	protected Iterable<ModelPart> getCompleteHeadParts(CallbackInfoReturnable<Iterable<ModelPart>> ci) {
		if (BoringTweaksConfigPojo.featuresGroup.fixBabyHatLayer) {
			ci.setReturnValue(ImmutableList.of(this.head, this.helmet));
		}
		return ImmutableList.of(this.head, this.helmet);
	}
}

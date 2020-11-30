package io.github.joaoh1.boringtweaks.mixin;

import java.util.List;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import io.github.joaoh1.boringtweaks.config.BoringTweaksConfigPojo;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.DebugHud;
import net.minecraft.client.util.math.MatrixStack;

@Mixin(DebugHud.class)
public class DebugHudMixin {
    @Shadow
    @Final
    private TextRenderer fontRenderer;

    @Inject(
        at = @At(
            value = "INVOKE",
            target = "net/minecraft/client/font/TextRenderer.draw(Lnet/minecraft/client/util/math/MatrixStack;Ljava/lang/String;FFI)I"
        ),
        method = {
            "renderLeftText",
            "renderRightText"
        },
        locals = LocalCapture.CAPTURE_FAILHARD,
        cancellable = true
    )
    private void renderLeftText(MatrixStack matrices, CallbackInfo ci, List<String> list, int i, String string, int j, int k, int l, int m) {
        if (BoringTweaksConfigPojo.featuresGroup.shadowedDebugHud) {
            String id = ci.getId();
            if (id.equals("renderLeftText")) {
                this.fontRenderer.drawWithShadow(matrices, string, 2.0F, (float)m, 14737632);
            } else {
                this.fontRenderer.drawWithShadow(matrices, string, (float)l, (float)m, 14737632);
            }
        }
    }
}

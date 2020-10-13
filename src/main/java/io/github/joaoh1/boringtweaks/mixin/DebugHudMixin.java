package io.github.joaoh1.boringtweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import io.github.joaoh1.boringtweaks.config.BoringTweaksConfigPojo;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.DebugHud;
import net.minecraft.client.util.math.MatrixStack;

@Mixin(DebugHud.class)
public class DebugHudMixin {
    @Redirect(
        at = @At(
            value = "INVOKE",
            target = "net/minecraft/client/font/TextRenderer.draw(Lnet/minecraft/client/util/math/MatrixStack;Ljava/lang/String;FFI)I"
        ),
        method = {
            "renderLeftText",
            "renderRightText"
        }
    )
    protected int renderHudWithShadow(TextRenderer textRenderer, MatrixStack matrices, String text, float x, float y, int color) {
        if (BoringTweaksConfigPojo.featuresGroup.shadowedDebugHud) {
            return textRenderer.drawWithShadow(matrices, text, x, y, color);
        } else {
            return textRenderer.draw(matrices, text, x, y, color);
        }
    }
}

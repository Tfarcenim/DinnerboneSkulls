package tfar.dinnerboneskulls.mixin;

import com.mojang.authlib.GameProfile;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tfar.dinnerboneskulls.ClientMixinEvents;
import tfar.dinnerboneskulls.DinnerboneSkulls;
import tfar.dinnerboneskulls.SkullDuck;

import static tfar.dinnerboneskulls.ClientMixinEvents.skullBlockEntityCapture;

@Mixin(SkullBlockRenderer.class)
public class SkullBlockRendererMixin {

	private static boolean isBlock = false;

	@Inject(method = "render",at = @At("HEAD"))
	public void captureSkull(SkullBlockEntity skullBlockEntity, float f, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int j, CallbackInfo ci) {
			skullBlockEntityCapture = skullBlockEntity;
			isBlock = true;
	}

	@Inject(method = "renderSkull",at = @At(value = "INVOKE",target = "Lcom/mojang/blaze3d/vertex/PoseStack;scale(FFF)V"))
	private static void rotate(@Nullable Direction direction, float f, SkullBlock.Type type, @Nullable GameProfile gameProfile, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, CallbackInfo ci) {
		if (skullBlockEntityCapture != null && ((SkullDuck)skullBlockEntityCapture).isDinnerbone() && isBlock) {
			ClientMixinEvents.onRenderDinnerboneSkull(direction,poseStack,f);
			isBlock = false;
		}
	}
}

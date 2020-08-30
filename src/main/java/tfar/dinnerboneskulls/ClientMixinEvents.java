package tfar.dinnerboneskulls;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import org.jetbrains.annotations.Nullable;

public class ClientMixinEvents {

	public static SkullBlockEntity skullBlockEntityCapture;

	public static void onRenderDinnerboneSkull(@Nullable Direction direction, PoseStack poseStack, float f) {
		poseStack.mulPose(Vector3f.XP.rotationDegrees(180.0F));
		poseStack.mulPose(Vector3f.YP.rotationDegrees( 180 + f * 2));
		poseStack.translate(0,-1,0);
	}
}

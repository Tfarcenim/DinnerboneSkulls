package tfar.dinnerboneskulls.mixin;

import com.mojang.datafixers.util.Pair;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tfar.dinnerboneskulls.MixinEvents;

@Mixin(StandingAndWallBlockItem.class)
public class StandingAndWallBlockItemMixin {
	@Shadow @Final protected Block wallBlock;

	@Inject(method = "getPlacementState",at = @At("HEAD"),cancellable = true)
	private void placeDinnerboneSkull(BlockPlaceContext blockPlaceContext, CallbackInfoReturnable<@Nullable BlockState> cir) {
		Pair<Boolean,BlockState> statePair = MixinEvents.onSkullPlace((StandingAndWallBlockItem)(Object)this,wallBlock,blockPlaceContext,cir);
		if (statePair.getFirst()) {
			cir.setReturnValue(statePair.getSecond());
		}
	}
}

package tfar.dinnerboneskulls;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public class MixinEvents {

	public static Pair<Boolean,BlockState> onSkullPlace(StandingAndWallBlockItem standingAndWallBlockItem, Block wallBlock, BlockPlaceContext blockPlaceContext, CallbackInfoReturnable<@Nullable BlockState> cir) {
		boolean success = false;

		BlockState placementState = null;
		LevelReader levelReader = blockPlaceContext.getLevel();
		BlockPos blockPos = blockPlaceContext.getClickedPos();

		Direction direction = blockPlaceContext.getClickedFace();

			if (direction == Direction.DOWN) {
				BlockState blockState3 = dinnerbone(standingAndWallBlockItem.getBlock()).getStateForPlacement(blockPlaceContext);
				if (blockState3 != null && blockState3.canSurvive(levelReader, blockPos)) {
					placementState = blockState3;
					success = true;
				}
			} else {
				return Pair.of(false,null);
			}

		placementState = placementState != null && levelReader.isUnobstructed(placementState, blockPos, CollisionContext.empty()) ? placementState : null;
		return Pair.of(success && placementState != null,placementState);
	}

	public static Block dinnerbone(Block block) {
		return Registry.BLOCK.get(new ResourceLocation(DinnerboneSkulls.MODID,"dinnerbone_" + Registry.BLOCK.getKey(block).getPath()));
	}
}

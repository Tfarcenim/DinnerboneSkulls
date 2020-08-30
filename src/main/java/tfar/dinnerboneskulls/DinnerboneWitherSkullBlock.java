package tfar.dinnerboneskulls;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WitherSkullBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DinnerboneWitherSkullBlock extends WitherSkullBlock {

	public static final VoxelShape shape = Block.box(4.0D, 8.0D, 4.0D, 12.0D, 16.0D, 12.0D);

	protected DinnerboneWitherSkullBlock(Properties properties) {
		super(properties);
	}

	public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
		return shape;
	}

}

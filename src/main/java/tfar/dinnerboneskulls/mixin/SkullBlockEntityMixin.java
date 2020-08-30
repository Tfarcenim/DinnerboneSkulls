package tfar.dinnerboneskulls.mixin;

import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tfar.dinnerboneskulls.DinnerboneSkulls;
import tfar.dinnerboneskulls.SkullDuck;

@Mixin(SkullBlockEntity.class)
public class SkullBlockEntityMixin implements SkullDuck {

	public boolean isDinnerbone;

	@Inject(method = "load",at = @At("RETURN"))
	private void loadData(BlockState blockState, CompoundTag compoundTag, CallbackInfo ci) {
		isDinnerbone = Registry.BLOCK.getKey(blockState.getBlock()).getNamespace().equals(DinnerboneSkulls.MODID);
	}

	@Override
	public boolean isDinnerbone() {
		return isDinnerbone;
	}
}

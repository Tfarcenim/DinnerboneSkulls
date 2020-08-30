package tfar.dinnerboneskulls;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import tfar.dinnerboneskulls.mixin.BlockEntityTypeAccessor;

import java.util.Set;

public class DinnerboneSkulls implements ModInitializer {

	public static final String MODID = "dinnerboneskulls";

	public static Block DINNERBONE_SKELETON_SKULL = register("dinnerbone_skeleton_skull", new DinnerboneSkullBlock(SkullBlock.Types.SKELETON, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0F)));
	public static Block DINNERBONE_WITHER_SKELETON_SKULL = register("dinnerbone_wither_skeleton_skull", new DinnerboneWitherSkullBlock(BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0F)));
	public static Block DINNERBONE_ZOMBIE_HEAD = register("dinnerbone_zombie_head", new DinnerboneSkullBlock(SkullBlock.Types.ZOMBIE, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0F)));
	public static Block DINNERBONE_PLAYER_HEAD = register("dinnerbone_player_head", new DinnerbonePlayerHeadBlock(BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0F)));
	public static Block DINNERBONE_CREEPER_HEAD = register("dinnerbone_creeper_head", new DinnerboneSkullBlock(SkullBlock.Types.CREEPER, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0F)));
	public static Block DINNERBONE_DRAGON_HEAD = register("dinnerbone_dragon_head", new DinnerboneSkullBlock(SkullBlock.Types.DRAGON, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0F)));

	@Override
	public void onInitialize() {
		Set<Block> newValidBlocks = Sets.newHashSet(((BlockEntityTypeAccessor)BlockEntityType.SKULL).getValidBlocks());
		newValidBlocks.addAll(Lists.newArrayList(
						DINNERBONE_SKELETON_SKULL,
						DINNERBONE_WITHER_SKELETON_SKULL,
						DINNERBONE_ZOMBIE_HEAD,
						DINNERBONE_PLAYER_HEAD,
						DINNERBONE_CREEPER_HEAD,
						DINNERBONE_DRAGON_HEAD
		));
		((BlockEntityTypeAccessor)BlockEntityType.SKULL).setValidBlocks(newValidBlocks);
	}

	private static Block register(String string, Block block) {
		return Registry.register(Registry.BLOCK, new ResourceLocation(MODID,string), block);
	}
}

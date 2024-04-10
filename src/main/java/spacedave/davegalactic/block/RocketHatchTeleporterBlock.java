package spacedave.davegalactic.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import spacedave.davegalactic.DavesGalacticEmporium;
import spacedave.davegalactic.block_entity.CapsuleTeleporterBlockEntity;
import spacedave.davegalactic.block_entity.RocketHatchTeleporterBlockEntity;
import spacedave.davegalactic.world.dimension.ModDimensions;

public class RocketHatchTeleporterBlock extends Block implements BlockEntityProvider {

    public RocketHatchTeleporterBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        //check all the needed stuff
        if (!world.isClient) {
            if(player.isSneaking()) {
                MinecraftServer server = world.getServer();
                if(server != null){
                    if (player instanceof ServerPlayerEntity){
                        ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
                        if (world.getRegistryKey() == ModDimensions.getCapsuleDimensionLevelKey()) { //check if we're in the capsule dim
                            BlockEntity blockEntity = world.getBlockEntity(pos);
                            if (blockEntity instanceof RocketHatchTeleporterBlockEntity) {
                                RocketHatchTeleporterBlockEntity RocketHatchTeleporterBlockEntity = (RocketHatchTeleporterBlockEntity) blockEntity;
                                if (RocketHatchTeleporterBlockEntity.dim != null) {
                                    //get dimension according to nbt
                                    RegistryKey<World> registryKey = RegistryKey.of(RegistryKeys.WORLD, new Identifier(RocketHatchTeleporterBlockEntity.dimMod, RocketHatchTeleporterBlockEntity.dim));
                                    ServerWorld originWorld = server.getWorld(registryKey);
                                    //teleport player there
                                    serverPlayer.teleport(originWorld, RocketHatchTeleporterBlockEntity.xPos, RocketHatchTeleporterBlockEntity.yPos, RocketHatchTeleporterBlockEntity.zPos, serverPlayer.bodyYaw, serverPlayer.prevPitch);
                                }
                            }
                        }
                    }
                }
            }
        }

        return ActionResult.SUCCESS;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new RocketHatchTeleporterBlockEntity(pos,state);
    }
}

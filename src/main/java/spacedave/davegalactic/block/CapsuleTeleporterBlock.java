package spacedave.davegalactic.block;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.StructureBlockBlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.StructureTemplateManager;
import net.minecraft.structure.processor.BlockRotStructureProcessor;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.structure.Structure;
import org.apache.logging.log4j.core.jmx.Server;
import org.jetbrains.annotations.Nullable;
import spacedave.davegalactic.DavesGalacticEmporium;
import spacedave.davegalactic.StateSaverAndLoader;
import spacedave.davegalactic.block_entity.CapsuleTeleporterBlockEntity;
import spacedave.davegalactic.block_entity.RocketHatchTeleporterBlockEntity;
import spacedave.davegalactic.world.dimension.ModDimensions;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CapsuleTeleporterBlock extends Block implements BlockEntityProvider {
    public CapsuleTeleporterBlock(Settings settings) {
        super(settings);
    }

    public static final Identifier CAPSULES_OPENED = new Identifier(DavesGalacticEmporium.MOD_ID, "capsules_opened");//for getting initial capsules opened amount

    private CapsuleTeleporterBlockEntity capsuleTeleporterBlockEntity;
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            if(player.isSneaking()) {
                MinecraftServer server = world.getServer();
                if(server != null){
                    if (player instanceof ServerPlayerEntity){
                        ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
                        if (world.getRegistryKey() != ModDimensions.getCapsuleDimensionLevelKey()){ //if we're certain we're not in the capsule_dimension
                            BlockEntity blockEntity = world.getBlockEntity(pos);
                            if(blockEntity instanceof CapsuleTeleporterBlockEntity) {
                                CapsuleTeleporterBlockEntity capsuleTeleporterBlockEntity = (CapsuleTeleporterBlockEntity) blockEntity;

                                ServerWorld capsule_dimension = server.getWorld(ModDimensions.getCapsuleDimensionLevelKey());
                                if(!capsuleTeleporterBlockEntity.initialGeneration) { //the following code should only run once
                                    BlockPos destinationPos = new BlockPos(20 * capsuleTeleporterBlockEntity.ownCapsuleId, 100, 0);

                                    capsuleTeleporterBlockEntity.buildCapsuleInterior(destinationPos,(ServerWorld) capsule_dimension);
                                }
                                serverPlayer.teleport(capsule_dimension, 20 * (double) capsuleTeleporterBlockEntity.ownCapsuleId + 4.5f, 101f, 2.5f, serverPlayer.bodyYaw, serverPlayer.prevPitch);
                                BlockPos blockEntityPos = new BlockPos(20 * capsuleTeleporterBlockEntity.ownCapsuleId + 4, 101, 1);
                                //send the position and origin dimension to the block
                                BlockEntity rocketHatchBlockEntity = capsule_dimension.getBlockEntity(blockEntityPos);
                                if (rocketHatchBlockEntity instanceof  RocketHatchTeleporterBlockEntity) { //set the variables for the blockEntity
                                    RocketHatchTeleporterBlockEntity rocketHatchTeleporterBlockEntity = (RocketHatchTeleporterBlockEntity) rocketHatchBlockEntity;
                                    rocketHatchTeleporterBlockEntity.setOrigin(pos, world.getRegistryKey().getValue().getPath(), world.getRegistryKey().getValue().getNamespace());
                                }
                                capsuleTeleporterBlockEntity.initialGeneration = true;

                            }
                        }
                    }
                }
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if (!world.isClient){
            MinecraftServer server = world.getServer();
            if(server!=null){
                if(world.getRegistryKey() != ModDimensions.getCapsuleDimensionLevelKey()){ //if we're certain we're not in the capsule dimension
                    BlockEntity blockEntity = world.getBlockEntity(pos);
                    if(blockEntity instanceof CapsuleTeleporterBlockEntity){
                        CapsuleTeleporterBlockEntity capsuleTeleporterBlockEntity = (CapsuleTeleporterBlockEntity) blockEntity; //get block entity

                        //check if the capsule id is 0, meaning it had no original id, if so, add 1 to it
                        if(capsuleTeleporterBlockEntity.ownCapsuleId == 0){
                            StateSaverAndLoader serverState = StateSaverAndLoader.getServerState(world.getServer());
                            serverState.totalCapsulesOpened+=1;

                            PacketByteBuf data = PacketByteBufs.create();
                            data.writeInt(serverState.totalCapsulesOpened);

                            capsuleTeleporterBlockEntity.ownCapsuleId = serverState.totalCapsulesOpened;

                            capsuleTeleporterBlockEntity.initialGeneration = false;
                        }
                    }
                }
            }
        }
    }


    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CapsuleTeleporterBlockEntity(pos,state);
    }
}
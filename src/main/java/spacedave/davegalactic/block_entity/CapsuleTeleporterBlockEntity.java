package spacedave.davegalactic.block_entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.StructureTemplateManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.structure.Structure;
import spacedave.davegalactic.DavesGalacticEmporium;
import spacedave.davegalactic.block.CapsuleTeleporterBlock;

import java.util.Optional;

public class CapsuleTeleporterBlockEntity extends BlockEntity {
    //Store the capsule amount number here once
    public int ownCapsuleId = 0;
    public boolean initialGeneration = false;
    public CapsuleTeleporterBlockEntity(BlockPos pos, BlockState state){
        super(DavesGalacticEmporium.CAPSULE_TELEPORTER_BLOCK_ENTITY, pos, state);
    }

    @Override
    public void writeNbt(NbtCompound nbt){
        nbt.putInt("ownCapsuleId", ownCapsuleId);
        nbt.putBoolean("initialGeneration", initialGeneration);

        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt){
        super.readNbt(nbt);
        ownCapsuleId = nbt.getInt("ownCapsuleId");
        initialGeneration = nbt.getBoolean("initialGeneration");
    }

    public void buildCapsuleInterior(BlockPos pos, ServerWorld world) {
        Identifier structureId = new Identifier("davespacemod", "capsule_structure");
        StructureTemplateManager structureTemplateManager = world.getStructureTemplateManager();
        Optional<StructureTemplate> optional;
        optional = structureTemplateManager.getTemplate(structureId);
        if (optional.isEmpty()) throw new RuntimeException("CapsuleTeleporterBlockEntity.buildCapsuleInterior ran into an Issue, optional is empty");

        StructureTemplate capsule = optional.get();
        StructurePlacementData data = new StructurePlacementData();
        capsule.place(world, pos, pos, data, Random.create(Util.getMeasuringTimeMs()),2);
    }
}

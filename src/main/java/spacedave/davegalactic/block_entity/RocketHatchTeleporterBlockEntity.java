package spacedave.davegalactic.block_entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import spacedave.davegalactic.DavesGalacticEmporium;

//yeah so I'm pretty sure this blockentity only needs to exists for nbt data
public class RocketHatchTeleporterBlockEntity extends BlockEntity {
    public BlockPos pos;
    public int xPos;
    public int yPos;
    public int zPos;
    public String dim;
    public String dimMod;

    public RocketHatchTeleporterBlockEntity(BlockPos pos, BlockState state) {
        super(DavesGalacticEmporium.ROCKET_HATCH_TELEPORTER_BLOCK_ENTITY, pos, state);
    }

    @Override
    public void writeNbt(NbtCompound nbt){
        nbt.putInt("xPos", xPos);
        nbt.putInt("yPos",yPos);
        nbt.putInt("zPos",zPos);
        if (dim != null) {
            nbt.putString("dimension", dim);
            nbt.putString("mod", dimMod);
        }

        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt){
        super.readNbt(nbt);

        xPos = nbt.getInt("xPos");
        yPos = nbt.getInt("yPos");
        zPos = nbt.getInt("zPos");
        dim = nbt.getString("dimension");
        dimMod = nbt.getString("mod");
    }
    public void setOrigin(BlockPos pos, String originDim, String originDimMod){
        dim = originDim;
        dimMod = originDimMod;
        xPos = pos.getX();
        yPos = pos.getY();
        zPos = pos.getZ();
    }
}

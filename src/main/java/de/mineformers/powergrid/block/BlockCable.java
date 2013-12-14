package de.mineformers.powergrid.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import de.mineformers.powergrid.lib.BlockIds;
import de.mineformers.powergrid.lib.Strings;
import de.mineformers.powergrid.tileentity.TileCable;

/**
 * 
 * PowerGrid
 * 
 * BlockCable
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class BlockCable extends BlockPowerGrid implements ITileEntityProvider {

	public BlockCable() {
		super(BlockIds.CABLE, Material.rock, Strings.CABLE_NAME);
		this.setStepSound(Block.soundAnvilFootstep);
		this.setBlockBounds(0.25F, 0.25F, 0.25F, 0.75F, 0.75F, 0.75F);
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		if (world.getBlockTileEntity(x, y, z) != null) {
			TileCable tile = (TileCable) world.getBlockTileEntity(x, y, z);
			tile.checkConnections();
		}
	}

	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side,
			float hitX, float hitY, float hitZ, int meta) {
		if (world.getBlockTileEntity(x, y, z) != null) {
			TileCable tile = (TileCable) world.getBlockTileEntity(x, y, z);
			tile.checkConnections();
		}

		return meta;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z,
			int newId) {
		if (world.getBlockTileEntity(x, y, z) != null) {
			TileCable tile = (TileCable) world.getBlockTileEntity(x, y, z);
			tile.checkConnections();
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileCable();
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public int getRenderType() {
		return -1;
	}

}

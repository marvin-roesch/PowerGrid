package de.mineformers.powergrid.integration.ic2.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import de.mineformers.powergrid.block.BlockPowerGrid;
import de.mineformers.powergrid.integration.ic2.tileentity.TileEnergyInterface;
import de.mineformers.powergrid.lib.BlockIds;
import de.mineformers.powergrid.lib.Strings;

/**
 * 
 * PowerGrid
 * 
 * BlockEnergyInterface
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class BlockEnergyInterface extends BlockPowerGrid implements
		ITileEntityProvider {

	public BlockEnergyInterface() {
		super(BlockIds.IC2_INTERFACE, Material.rock, Strings.IC2_INTERFACE_NAME);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEnergyInterface();
	}

}

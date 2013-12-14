package de.mineformers.powergrid.plugin;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import de.mineformers.powergrid.api.IEnergyHandler;
import de.mineformers.powergrid.api.PowerGridPlugin;
import de.mineformers.powergrid.integration.ic2.block.BlockEnergyInterface;
import de.mineformers.powergrid.integration.ic2.tileentity.TileEnergyInterface;
import de.mineformers.powergrid.lib.Strings;
import de.mineformers.powergrid.plugin.energy.IC2Handler;

/**
 * 
 * PowerGrid
 * 
 * IC2Plugin
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class IC2Plugin extends PowerGridPlugin {

	public static Block energyInterface;

	@Override
	public String getId() {
		return "IC2Plugin";
	}

	@Override
	public void onPreInit() {
		energyInterface = new BlockEnergyInterface();
		GameRegistry.registerBlock(energyInterface, Strings.IC2_INTERFACE_NAME);

		GameRegistry.registerTileEntity(TileEnergyInterface.class,
				TileEnergyInterface.class.getName());
	}

	@Override
	public Class<? extends IEnergyHandler> getEnergyHandler() {
		return IC2Handler.class;
	}

	@Override
	public double getConversionRate() {
		return 0.5F;
	}

}

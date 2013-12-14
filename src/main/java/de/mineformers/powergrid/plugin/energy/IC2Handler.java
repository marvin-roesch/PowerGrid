package de.mineformers.powergrid.plugin.energy;

import java.util.ArrayList;

import net.minecraft.world.World;
import de.mineformers.powergrid.api.IEnergyHandler;
import de.mineformers.powergrid.integration.ic2.tileentity.TileEnergyInterface;

/**
 * 
 * PowerGrid
 * 
 * IC2Handler
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class IC2Handler extends IEnergyHandler {

	public IC2Handler(World world, int x, int y, int z) {
		super(world, x, y, z);
	}

	@Override
	public ArrayList<Class<?>> getConnectables() {
		ArrayList<Class<?>> list = new ArrayList<Class<?>>();
		list.add(TileEnergyInterface.class);

		return list;
	}

}

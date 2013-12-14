package de.mineformers.powergrid.api;

import java.util.ArrayList;

import net.minecraft.world.World;
import de.mineformers.powergrid.api.util.Vector3;

/**
 * 
 * PowerGrid
 * 
 * IEnergyHandler
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public abstract class IEnergyHandler {

	protected World world;
	protected Vector3 pos;

	public IEnergyHandler(World world, int x, int y, int z) {
		this.world = world;
		this.pos = new Vector3(x, y, z);
	}

	public abstract ArrayList<Class<?>> getConnectables();
	
	public void onFirstTick() {
		
	}

}

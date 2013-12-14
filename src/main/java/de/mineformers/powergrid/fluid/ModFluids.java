package de.mineformers.powergrid.fluid;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

/**
 * 
 * PowerGrid
 * 
 * ModFluids
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ModFluids {
	
	public static Fluid energon;
	
	public static void init() {
		energon = new FluidEnergon();
		
		FluidRegistry.registerFluid(energon);
	}

}

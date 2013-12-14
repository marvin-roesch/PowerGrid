package de.mineformers.powergrid.fluid;

import de.mineformers.powergrid.lib.Strings;
import net.minecraft.item.EnumRarity;
import net.minecraftforge.fluids.Fluid;

/**
 * 
 * PowerGrid
 * 
 * FluidEnergon
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class FluidEnergon extends Fluid {

	public FluidEnergon() {
		super("energon");

		this.setDensity(10000);
		this.setViscosity(5000);
		this.setTemperature(1730);
		this.setRarity(EnumRarity.rare);
	}
	
	public String getUnlocalizedName() {
		return "fluid." + Strings.RESOURCE_PREFIX + Strings.ENERGON_NAME + ".name";
	}

}

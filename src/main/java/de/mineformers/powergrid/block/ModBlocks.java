package de.mineformers.powergrid.block;

import cpw.mods.fml.common.registry.GameRegistry;
import de.mineformers.powergrid.lib.Strings;
import net.minecraft.block.Block;

/**
 * 
 * PowerGrid
 * 
 * ModBlocks
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ModBlocks {

	public static Block cable;
	public static Block energon;

	public static void init() {
		cable = new BlockCable();
		energon = new BlockEnergon();

		GameRegistry.registerBlock(cable, Strings.CABLE_NAME);
		GameRegistry.registerBlock(energon, Strings.ENERGON_NAME);
	}

}

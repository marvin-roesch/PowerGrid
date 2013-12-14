package de.mineformers.powergrid.core;

import de.mineformers.powergrid.lib.BlockIds;
import net.minecraft.creativetab.CreativeTabs;

/**
 * 
 * PowerGrid
 * 
 * CreativeTabPowerGrid
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class CreativeTabPowerGrid extends CreativeTabs {

	public CreativeTabPowerGrid(int id, String label) {
		super(id, label);
	}

	@Override
	public int getTabIconItemIndex() {
		return BlockIds.CABLE;
	}

}

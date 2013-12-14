package de.mineformers.powergrid.item;

import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;
import de.mineformers.powergrid.lib.Strings;

/**
 * 
 * PowerGrid
 * 
 * ModItems
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ModItems {

	public static Item energonBucket;

	public static void init() {
		energonBucket = new ItemEnergonBucket();

		GameRegistry.registerItem(energonBucket, Strings.ENERGON_BUCKET_NAME);
	}

}

package de.mineformers.powergrid.block;

import de.mineformers.powergrid.PowerGrid;
import de.mineformers.powergrid.lib.Strings;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

/**
 * 
 * PowerGrid
 * 
 * BlockPowerGrid
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class BlockPowerGrid extends Block {

	public BlockPowerGrid(int id, Material material, String name) {
		super(id, material);
		this.setUnlocalizedName(Strings.RESOURCE_PREFIX + name);
		this.setTextureName(name);
		this.setCreativeTab(PowerGrid.creativeTab);
	}

	protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
		return unlocalizedName.substring(unlocalizedName.indexOf(":") + 1);
	}

	@Override
	public void registerIcons(IconRegister register) {
		blockIcon = register.registerIcon(Strings.RESOURCE_PREFIX
				+ this.getTextureName());
	}

}

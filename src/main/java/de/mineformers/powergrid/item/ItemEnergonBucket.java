package de.mineformers.powergrid.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import de.mineformers.powergrid.PowerGrid;
import de.mineformers.powergrid.block.ModBlocks;
import de.mineformers.powergrid.lib.BlockIds;
import de.mineformers.powergrid.lib.ItemIds;
import de.mineformers.powergrid.lib.Strings;

/**
 * 
 * PowerGrid
 * 
 * ItemEnergonBucket
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ItemEnergonBucket extends ItemBucket {

	public ItemEnergonBucket() {
		super(ItemIds.BUCKET_ENERGON, BlockIds.ENERGON);
		this.setUnlocalizedName(Strings.RESOURCE_PREFIX
				+ Strings.ENERGON_BUCKET_NAME);
		this.setTextureName(Strings.ENERGON_BUCKET_NAME);
		this.setCreativeTab(PowerGrid.creativeTab);
	}

	@ForgeSubscribe(priority = EventPriority.NORMAL)
	public void FillBucket(FillBucketEvent event) {
		ItemStack result = attemptFill(event.world, event.target);
		if (result != null) {
			event.result = result;
			event.setResult(Result.ALLOW);
		}
	}

	private ItemStack attemptFill(World world, MovingObjectPosition p) {
		int id = world.getBlockId(p.blockX, p.blockY, p.blockZ);

		if (id == ModBlocks.energon.blockID) {
			if (world.getBlockMetadata(p.blockX, p.blockY, p.blockZ) == 0) {
				world.setBlock(p.blockX, p.blockY, p.blockZ, 0);
				return new ItemStack(ModItems.energonBucket);
			}
		}

		return null;
	}

	@Override
	public void registerIcons(IconRegister register) {
		this.itemIcon = register.registerIcon(Strings.RESOURCE_PREFIX
				+ this.iconString);
	}

}
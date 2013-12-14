package de.mineformers.powergrid.block;

import static net.minecraftforge.common.ForgeDirection.DOWN;
import static net.minecraftforge.common.ForgeDirection.EAST;
import static net.minecraftforge.common.ForgeDirection.NORTH;
import static net.minecraftforge.common.ForgeDirection.SOUTH;
import static net.minecraftforge.common.ForgeDirection.UP;
import static net.minecraftforge.common.ForgeDirection.WEST;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.BlockFluidClassic;
import de.mineformers.powergrid.fluid.ModFluids;
import de.mineformers.powergrid.lib.BlockIds;
import de.mineformers.powergrid.lib.Strings;

/**
 * 
 * PowerGrid
 * 
 * BlockEnergon
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class BlockEnergon extends BlockFluidClassic {

	private Icon[] icons;

	public BlockEnergon() {
		super(BlockIds.ENERGON, ModFluids.energon, Material.lava);
		ModFluids.energon.setBlockID(BlockIds.ENERGON);
		this.setUnlocalizedName(Strings.RESOURCE_PREFIX + Strings.ENERGON_NAME);
		this.setTextureName(Strings.ENERGON_NAME);
	}

	@Override
	public boolean isFlammable(IBlockAccess world, int x, int y, int z,
			int metadata, ForgeDirection face) {
		return false;
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		super.updateTick(world, x, y, z, rand);
		if (world.getGameRules().getGameRuleBooleanValue("doFireTick")) {
			Block base = Block.blocksList[world.getBlockId(x, y - 1, z)];
			boolean flag = (base != null && base.isFireSource(world, x, y - 1,
					z, world.getBlockMetadata(x, y - 1, z), UP));

			if (!flag
					&& world.isRaining()
					&& (world.canLightningStrikeAt(x, y, z)
							|| world.canLightningStrikeAt(x - 1, y, z)
							|| world.canLightningStrikeAt(x + 1, y, z)
							|| world.canLightningStrikeAt(x, y, z - 1) || world
								.canLightningStrikeAt(x, y, z + 1))) {
			} else {
				int l = world.getBlockMetadata(x, y, z);

				if (l < 15) {
					world.setBlockMetadataWithNotify(x, y, z,
							l + rand.nextInt(3) / 2, 4);
				}

				world.scheduleBlockUpdate(x, y, z, Block.fire.blockID,
						this.tickRate(world) + rand.nextInt(10));

				boolean flag1 = world.isBlockHighHumidity(x, y, z);
				byte b0 = 0;

				if (flag1) {
					b0 = -50;
				}

				this.tryToCatchBlockOnFire(world, x + 1, y, z, 300 + b0, rand,
						l, WEST);
				this.tryToCatchBlockOnFire(world, x - 1, y, z, 300 + b0, rand,
						l, EAST);
				this.tryToCatchBlockOnFire(world, x, y - 1, z, 250 + b0, rand,
						l, UP);
				this.tryToCatchBlockOnFire(world, x, y + 1, z, 250 + b0, rand,
						l, DOWN);
				this.tryToCatchBlockOnFire(world, x, y, z - 1, 300 + b0, rand,
						l, SOUTH);
				this.tryToCatchBlockOnFire(world, x, y, z + 1, 300 + b0, rand,
						l, NORTH);

				for (int i1 = x - 1; i1 <= x + 1; ++i1) {
					for (int j1 = z - 1; j1 <= z + 1; ++j1) {
						for (int k1 = y - 1; k1 <= y + 4; ++k1) {
							if (i1 != x || k1 != y || j1 != z) {
								int l1 = 100;

								if (k1 > y + 1) {
									l1 += (k1 - (y + 1)) * 100;
								}

								int i2 = this
										.getChanceOfNeighborsEncouragingFire(
												world, i1, k1, j1);

								if (i2 > 0) {
									int j2 = (i2 + 40 + world.difficultySetting * 7)
											/ (l + 30);

									if (flag1) {
										j2 /= 2;
									}

									if (j2 > 0
											&& rand.nextInt(l1) <= j2
											&& (!world.isRaining() || !world
													.canLightningStrikeAt(i1,
															k1, j1))
											&& !world.canLightningStrikeAt(
													i1 - 1, k1, z)
											&& !world.canLightningStrikeAt(
													i1 + 1, k1, j1)
											&& !world.canLightningStrikeAt(i1,
													k1, j1 - 1)
											&& !world.canLightningStrikeAt(i1,
													k1, j1 + 1)) {
										int k2 = l + rand.nextInt(5) / 4;

										if (k2 > 15) {
											k2 = 15;
										}

										world.setBlock(i1, k1, j1,
												Block.fire.blockID, k2, 3);
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private void tryToCatchBlockOnFire(World world, int x, int y, int z,
			int par5, Random par6Random, int par7, ForgeDirection face) {
		int j1 = 0;
		Block block = Block.blocksList[world.getBlockId(x, y, z)];
		if (block != null) {
			j1 = block.getFlammability(world, x, y, z,
					world.getBlockMetadata(x, y, z), face);
		}

		if (par6Random.nextInt(par5) < j1) {
			boolean flag = world.getBlockId(x, y, z) == Block.tnt.blockID;

			if (par6Random.nextInt(par7 + 10) < 5
					&& !world.canLightningStrikeAt(x, y, z)) {
				int k1 = par7 + par6Random.nextInt(5) / 4;

				if (k1 > 15) {
					k1 = 15;
				}

				world.setBlock(x, y, z, Block.fire.blockID, k1, 3);
			}

			if (flag) {
				Block.tnt.onBlockDestroyedByPlayer(world, x, y, z, 1);
			}
		}
	}

	/**
	 * Gets the highest chance of a neighbor block encouraging this block to
	 * catch fire
	 */
	private int getChanceOfNeighborsEncouragingFire(World world, int x, int y,
			int z) {
		byte b0 = 0;

		if (!world.isAirBlock(x, y, z)) {
			return 0;
		} else {
			int l = this.getChanceToEncourageFire(world, x + 1, y, z, b0, WEST);
			l = this.getChanceToEncourageFire(world, x - 1, y, z, l, EAST);
			l = this.getChanceToEncourageFire(world, x, y - 1, z, l, UP);
			l = this.getChanceToEncourageFire(world, x, y + 1, z, l, DOWN);
			l = this.getChanceToEncourageFire(world, x, y, z - 1, l, SOUTH);
			l = this.getChanceToEncourageFire(world, x, y, z + 1, l, NORTH);
			return l;
		}
	}

	public boolean canBlockCatchFire(IBlockAccess world, int x, int y, int z,
			ForgeDirection face) {
		Block block = Block.blocksList[world.getBlockId(x, y, z)];
		if (block != null) {
			return block.isFlammable(world, x, y, z,
					world.getBlockMetadata(x, y, z), face);
		}
		return false;
	}

	/**
	 * Side sensitive version that calls the block function.
	 * 
	 * @param world
	 *            The current world
	 * @param x
	 *            X Position
	 * @param y
	 *            Y Position
	 * @param z
	 *            Z Position
	 * @param oldChance
	 *            The previous maximum chance.
	 * @param face
	 *            The side the fire is coming from
	 * @return The chance of the block catching fire, or oldChance if it is
	 *         higher
	 */
	public int getChanceToEncourageFire(World world, int x, int y, int z,
			int oldChance, ForgeDirection face) {
		int newChance = 0;
		Block block = Block.blocksList[world.getBlockId(x, y, z)];
		if (block != null) {
			newChance = block.getFireSpreadSpeed(world, x, y, z,
					world.getBlockMetadata(x, y, z), face);
		}
		return (newChance > oldChance ? newChance : oldChance);
	}

	@Override
	public void registerIcons(IconRegister register) {
		icons = new Icon[2];
		icons[0] = register.registerIcon(Strings.RESOURCE_PREFIX
				+ this.getTextureName());
		icons[1] = register.registerIcon(Strings.RESOURCE_PREFIX
				+ this.getTextureName() + "_flow");
	}

	@Override
	public Icon getIcon(int meta, int side) {
		return meta != 0 && meta != 1 ? this.icons[1] : this.icons[0];
	}
}

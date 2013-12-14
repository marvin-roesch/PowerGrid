package de.mineformers.powergrid.client.renderer.tileentity;

import java.util.EnumSet;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeDirection;

import org.lwjgl.opengl.GL11;

import de.mineformers.powergrid.PowerGrid;
import de.mineformers.powergrid.block.ModBlocks;
import de.mineformers.powergrid.tileentity.TileCable;

/**
 * 
 * PowerGrid
 * 
 * TileCableRenderer
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class TileCableRenderer extends TileEntitySpecialRenderer {

	public static final float TEXTURE_SIZE = 64F;
	private float baseMod;
	private Icon icon;

	public TileCableRenderer() {
		baseMod = 1F / TEXTURE_SIZE * 16;
		icon = ModBlocks.energon.getIcon(0, 0);
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y,
			double z, float partialTick) {
		TileCable tile = (TileCable) tileentity;
		Tessellator tess = Tessellator.instance;

		this.bindTexture(TextureMap.locationBlocksTexture);
		EnumSet<ForgeDirection> cons = tile.getConnections();

		this.bindBlock();

		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glTranslated(x, y, z);

		drawBase(tess, cons);
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		if (cons.contains(ForgeDirection.UP)) {
			drawConnection(tess);
		}
		if (cons.contains(ForgeDirection.DOWN)) {
			GL11.glRotatef(180, 1, 0, 0);
			drawConnection(tess);
			GL11.glRotatef(-180, 1, 0, 0);
		}
		if (cons.contains(ForgeDirection.EAST)) {
			GL11.glRotatef(-90, 0, 0, 1);
			drawConnection(tess);
			GL11.glRotatef(90, 0, 0, 1);
		}
		if (cons.contains(ForgeDirection.WEST)) {
			GL11.glRotatef(90, 0, 0, 1);
			drawConnection(tess);
			GL11.glRotatef(-90, 0, 0, 1);
		}

		if (cons.contains(ForgeDirection.NORTH)) {
			GL11.glRotatef(-90, 1, 0, 0);
			drawConnection(tess);
			GL11.glRotatef(90, 1, 0, 0);
		}
		if (cons.contains(ForgeDirection.SOUTH)) {
			GL11.glRotatef(90, 1, 0, 0);
			drawConnection(tess);
			GL11.glRotatef(-90, 1, 0, 0);
		}

		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
	}

	private void bindBlock() {
		this.bindTexture(new ResourceLocation(PowerGrid.MOD_ID.toLowerCase(),
				"textures/models/cable.png"));
	}

	private void bindFluid() {
		this.bindTexture(TextureMap.locationBlocksTexture);
	}

	private void drawBase(Tessellator tess, EnumSet<ForgeDirection> cons) {
		/* Y Axis */
		drawBaseInverse(tess, cons);
		float offY = baseMod / 2F;
		if (!cons.contains(ForgeDirection.UP)) {
			this.drawBaseFluid(tess);
			this.bindBlock();
			tess.startDrawingQuads();
			tess.addVertexWithUV(0.25F, 0.75F, 0.75F, 0, offY + baseMod);
			tess.addVertexWithUV(0.75F, 0.75F, 0.75F, baseMod, offY + baseMod);
			tess.addVertexWithUV(0.75F, 0.75F, 0.25F, baseMod, offY + 0);
			tess.addVertexWithUV(0.25F, 0.75F, 0.25F, 0, offY + 0);
			tess.draw();
		}
		if (!cons.contains(ForgeDirection.DOWN)) {
			GL11.glRotatef(-180, 1, 0, 0);
			GL11.glTranslatef(0, 0, -1);
			GL11.glTranslatef(0, -1F, 0);
			this.drawBaseFluid(tess);
			GL11.glTranslatef(0, 1F, 0);
			GL11.glTranslatef(0, 0, 1);
			GL11.glRotatef(180, 1, 0, 0);
			this.bindBlock();
			tess.startDrawingQuads();
			tess.addVertexWithUV(0.25F, 0.25F, 0.25F, 0, offY + 0);
			tess.addVertexWithUV(0.75F, 0.25F, 0.25F, baseMod, offY + 0);
			tess.addVertexWithUV(0.75F, 0.25F, 0.75F, baseMod, offY + baseMod);
			tess.addVertexWithUV(0.25F, 0.25F, 0.75F, 0, offY + baseMod);
			tess.draw();
		}

		/* Z Axis */
		if (!cons.contains(ForgeDirection.NORTH)) {
			GL11.glRotatef(-90, 1, 0, 0);
			GL11.glTranslatef(0, -1, 0);
			this.drawBaseFluid(tess);
			GL11.glTranslatef(0, 1, 0);
			GL11.glRotatef(90, 1, 0, 0);
			this.bindBlock();
			tess.startDrawingQuads();
			tess.addVertexWithUV(0.25F, 0.75F, 0.25F, baseMod, offY + 0);
			tess.addVertexWithUV(0.75F, 0.75F, 0.25F, 0, offY + 0);
			tess.addVertexWithUV(0.75F, 0.25F, 0.25F, 0, offY + baseMod);
			tess.addVertexWithUV(0.25F, 0.25F, 0.25F, baseMod, offY + baseMod);
			tess.draw();
		}
		if (!cons.contains(ForgeDirection.SOUTH)) {
			GL11.glRotatef(90, 1, 0, 0);
			GL11.glTranslatef(0, 0, -1);
			this.drawBaseFluid(tess);
			GL11.glTranslatef(0, 0, 1);
			GL11.glRotatef(-90, 1, 0, 0);
			this.bindBlock();
			tess.startDrawingQuads();
			tess.addVertexWithUV(0.25F, 0.75F, 0.75F, 0, offY + 0);
			tess.addVertexWithUV(0.25F, 0.25F, 0.75F, 0, offY + baseMod);
			tess.addVertexWithUV(0.75F, 0.25F, 0.75F, baseMod, offY + baseMod);
			tess.addVertexWithUV(0.75F, 0.75F, 0.75F, baseMod, offY + 0);
			tess.draw();
		}

		/* X Axis */
		if (!cons.contains(ForgeDirection.EAST)) {
			GL11.glRotatef(-90, 0, 0, 1);
			GL11.glTranslatef(-1, 0, 0);
			this.drawBaseFluid(tess);
			GL11.glTranslatef(1, 0, 0);
			GL11.glRotatef(90, 0, 0, 1);
			this.bindBlock();
			tess.startDrawingQuads();
			tess.addVertexWithUV(0.75F, 0.75F, 0.75F, baseMod, offY);
			tess.addVertexWithUV(0.75F, 0.25F, 0.75F, baseMod, offY + baseMod);
			tess.addVertexWithUV(0.75F, 0.25F, 0.25F, 0, offY + baseMod);
			tess.addVertexWithUV(0.75F, 0.75F, 0.25F, 0, offY);
			tess.draw();
		}
		if (!cons.contains(ForgeDirection.WEST)) {
			GL11.glRotatef(90, 0, 0, 1);
			GL11.glTranslatef(0, -1, 0);
			this.drawBaseFluid(tess);
			GL11.glTranslatef(0, 1, 0);
			GL11.glRotatef(-90, 0, 0, 1);
			this.bindBlock();
			tess.startDrawingQuads();
			tess.addVertexWithUV(0.25F, 0.75F, 0.75F, baseMod, offY + 0);
			tess.addVertexWithUV(0.25F, 0.75F, 0.25F, 0, offY + 0);
			tess.addVertexWithUV(0.25F, 0.25F, 0.25F, 0, offY + baseMod);
			tess.addVertexWithUV(0.25F, 0.25F, 0.75F, baseMod, offY + baseMod);
			tess.draw();
		}
	}

	private void drawBaseFluid(Tessellator tess) {
		this.bindFluid();
		GL11.glPushMatrix();
		tess.startDrawingQuads();
		tess.addVertexWithUV(0.25F, 0.749F, 0.25F, icon.getMinU(),
				icon.getMinV());
		tess.addVertexWithUV(0.25F, 0.749F, 0.75F, icon.getMinU(),
				icon.getMaxV());
		tess.addVertexWithUV(0.75F, 0.749F, 0.75F, icon.getMaxU(),
				icon.getMaxV());
		tess.addVertexWithUV(0.75F, 0.749F, 0.25F, icon.getMaxU(),
				icon.getMinV());
		tess.draw();
		GL11.glPopMatrix();
	}

	private void drawBaseInverse(Tessellator tess, EnumSet<ForgeDirection> cons) {
		/* Y Axis */
		float offY = baseMod / 2F;
		if (!cons.contains(ForgeDirection.UP)) {
			tess.startDrawingQuads();
			tess.addVertexWithUV(0.25F, 0.75F, 0.25F, 0, offY + 0);
			tess.addVertexWithUV(0.75F, 0.75F, 0.25F, baseMod, offY + 0);
			tess.addVertexWithUV(0.75F, 0.75F, 0.75F, baseMod, offY + baseMod);
			tess.addVertexWithUV(0.25F, 0.75F, 0.75F, 0, offY + baseMod);
			tess.draw();
		}
		if (!cons.contains(ForgeDirection.DOWN)) {
			tess.startDrawingQuads();
			tess.addVertexWithUV(0.25F, 0.25F, 0.75F, 0, offY + baseMod);
			tess.addVertexWithUV(0.75F, 0.25F, 0.75F, baseMod, offY + baseMod);
			tess.addVertexWithUV(0.75F, 0.25F, 0.25F, baseMod, offY + 0);
			tess.addVertexWithUV(0.25F, 0.25F, 0.25F, 0, offY + 0);
			tess.draw();
		}

		/* Z Axis */
		if (!cons.contains(ForgeDirection.NORTH)) {
			tess.startDrawingQuads();
			tess.addVertexWithUV(0.25F, 0.75F, 0.25F, 0, offY + 0);
			tess.addVertexWithUV(0.25F, 0.25F, 0.25F, 0, offY + baseMod);
			tess.addVertexWithUV(0.75F, 0.25F, 0.25F, baseMod, offY + baseMod);
			tess.addVertexWithUV(0.75F, 0.75F, 0.25F, baseMod, offY + 0);
			tess.draw();
		}
		if (!cons.contains(ForgeDirection.SOUTH)) {
			tess.startDrawingQuads();
			tess.addVertexWithUV(0.25F, 0.75F, 0.75F, baseMod, offY + 0);
			tess.addVertexWithUV(0.75F, 0.75F, 0.75F, 0, offY + 0);
			tess.addVertexWithUV(0.75F, 0.25F, 0.75F, 0, offY + baseMod);
			tess.addVertexWithUV(0.25F, 0.25F, 0.75F, baseMod, offY + baseMod);
			tess.draw();
		}

		/* X Axis */
		if (!cons.contains(ForgeDirection.EAST)) {
			tess.startDrawingQuads();
			tess.addVertexWithUV(0.75F, 0.75F, 0.75F, baseMod, offY + 0);
			tess.addVertexWithUV(0.75F, 0.75F, 0.25F, 0, offY + 0);
			tess.addVertexWithUV(0.75F, 0.25F, 0.25F, 0, offY + baseMod);
			tess.addVertexWithUV(0.75F, 0.25F, 0.75F, baseMod, offY + baseMod);
			tess.draw();
		}
		if (!cons.contains(ForgeDirection.WEST)) {
			tess.startDrawingQuads();
			tess.addVertexWithUV(0.25F, 0.75F, 0.75F, baseMod, offY);
			tess.addVertexWithUV(0.25F, 0.25F, 0.75F, baseMod, offY + baseMod);
			tess.addVertexWithUV(0.25F, 0.25F, 0.25F, 0, offY + baseMod);
			tess.addVertexWithUV(0.25F, 0.75F, 0.25F, 0, offY);
			tess.draw();
		}
	}

	private void drawConnection(Tessellator tess) {
		float off = baseMod / 2F;
		drawConnectionInverse(tess);
		this.drawConnectionFluid(tess);
		this.bindBlock();
		tess.startDrawingQuads();
		tess.addVertexWithUV(0.25F, 0.5F, 0.25F, 0, 0);
		tess.addVertexWithUV(-0.25F, 0.5F, 0.25F, off * 2F, 0);
		tess.addVertexWithUV(-0.25F, 0.25F, 0.25F, off * 2F, off);
		tess.addVertexWithUV(0.25F, 0.25F, 0.25F, 0, off);
		tess.draw();
		GL11.glRotatef(180, 0, 1, 0);
		this.drawConnectionFluid(tess);
		GL11.glRotatef(-180, 0, 1, 0);
		this.bindBlock();
		tess.startDrawingQuads();
		tess.addVertexWithUV(0.25F, 0.5F, -0.25F, off * 2, 0);
		tess.addVertexWithUV(0.25F, 0.25F, -0.25F, off * 2, off);
		tess.addVertexWithUV(-0.25F, 0.25F, -0.25F, 0, off);
		tess.addVertexWithUV(-0.25F, 0.5F, -0.25F, 0, 0);
		tess.draw();

		GL11.glRotatef(90, 0, 1, 0);
		this.drawConnectionFluid(tess);
		GL11.glRotatef(-90, 0, 1, 0);
		this.bindBlock();
		tess.startDrawingQuads();
		tess.addVertexWithUV(-0.25F, 0.5F, -0.25F, 0, 0);
		tess.addVertexWithUV(-0.25F, 0.25F, -0.25F, 0, off);
		tess.addVertexWithUV(-0.25F, 0.25F, 0.25F, off * 2, off);
		tess.addVertexWithUV(-0.25F, 0.5F, 0.25F, off * 2, 0);
		tess.draw();
		GL11.glRotatef(-90, 0, 1, 0);
		this.drawConnectionFluid(tess);
		GL11.glRotatef(90, 0, 1, 0);
		this.bindBlock();
		tess.startDrawingQuads();
		tess.addVertexWithUV(0.25F, 0.5F, -0.25F, off * 2, 0);
		tess.addVertexWithUV(0.25F, 0.5F, 0.25F, 0, 0);
		tess.addVertexWithUV(0.25F, 0.25F, 0.25F, 0, off);
		tess.addVertexWithUV(0.25F, 0.25F, -0.25F, off * 2, off);
		tess.draw();
	}

	private void drawConnectionFluid(Tessellator tess) {
		this.bindFluid();
		GL11.glPushMatrix();
		tess.startDrawingQuads();
		tess.addVertexWithUV(-0.24F, 0.24F, -0.25F, icon.getMinU(),
				icon.getMinV());
		tess.addVertexWithUV(-0.24F, 0.24F, 0.25F, icon.getMinU(),
				icon.getMaxV());
		tess.addVertexWithUV(-0.24F, 0.5F, 0.25F, icon.getMaxU(),
				icon.getMaxV());
		tess.addVertexWithUV(-0.24F, 0.5F, -0.25F, icon.getMaxU(),
				icon.getMinV());
		tess.draw();
		GL11.glPopMatrix();
	}

	private void drawConnectionInverse(Tessellator tess) {
		float off = baseMod / 2F;
		tess.startDrawingQuads();
		tess.addVertexWithUV(0.25F, 0.5F, -0.25F, 0, 0);
		tess.addVertexWithUV(-0.25F, 0.5F, -0.25F, off * 2F, 0);
		tess.addVertexWithUV(-0.25F, 0.25F, -0.25F, off * 2F, off);
		tess.addVertexWithUV(0.25F, 0.25F, -0.25F, 0, off);
		tess.draw();
		tess.startDrawingQuads();
		tess.addVertexWithUV(0.25F, 0.5F, 0.25F, off * 2, 0);
		tess.addVertexWithUV(0.25F, 0.25F, 0.25F, off * 2, off);
		tess.addVertexWithUV(-0.25F, 0.25F, 0.25F, 0, off);
		tess.addVertexWithUV(-0.25F, 0.5F, 0.25F, 0, 0);
		tess.draw();

		tess.startDrawingQuads();
		tess.addVertexWithUV(0.25F, 0.5F, -0.25F, 0, 0);
		tess.addVertexWithUV(0.25F, 0.25F, -0.25F, 0, off);
		tess.addVertexWithUV(0.25F, 0.25F, 0.25F, off * 2, off);
		tess.addVertexWithUV(0.25F, 0.5F, 0.25F, off * 2, 0);
		tess.draw();
		tess.startDrawingQuads();
		tess.addVertexWithUV(-0.25F, 0.5F, -0.25F, off * 2, 0);
		tess.addVertexWithUV(-0.25F, 0.5F, 0.25F, 0, 0);
		tess.addVertexWithUV(-0.25F, 0.25F, 0.25F, 0, off);
		tess.addVertexWithUV(-0.25F, 0.25F, -0.25F, off * 2, off);
		tess.draw();
	}

}

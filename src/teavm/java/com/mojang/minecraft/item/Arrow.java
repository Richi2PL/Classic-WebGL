package com.mojang.minecraft.item;

import com.mojang.minecraft.Entity;
import com.mojang.minecraft.level.Level;
import com.mojang.minecraft.phys.AABB;
import com.mojang.minecraft.player.Player;
import com.mojang.minecraft.render.TextureLocation;
import net.PeytonPlayz585.math.MathHelper;
import net.lax1dude.eaglercraft.adapter.Tessellator;

import java.util.List;
import org.lwjgl.opengl.GL11;

public class Arrow extends Entity
{
	public Arrow(Level level1, Entity owner, float x, float y, float z, float unknown0, float unknown1, float unknown2)
	{
		super(level1);

		this.owner = owner;

		setSize(0.3F, 0.5F);

		heightOffset = bbHeight / 2.0F;
		damage = 3;

		if(!(owner instanceof Player))
		{
			type = 1;
		} else {
			damage = 7;
		}

		heightOffset = 0.25F;

		float unknown3 = MathHelper.cos(-unknown0 * 0.017453292F - 3.1415927F);
		float unknown4 = MathHelper.sin(-unknown0 * 0.017453292F - 3.1415927F);

		unknown0 = MathHelper.cos(-unknown1 * 0.017453292F);
		unknown1 = MathHelper.sin(-unknown1 * 0.017453292F);

		slide = false;

		gravity = 1.0F / unknown2;

		xo -= unknown3 * 0.2F;
		zo += unknown4 * 0.2F;

		x -= unknown3 * 0.2F;
		z += unknown4 * 0.2F;

		xd = unknown4 * unknown0 * unknown2;
		yd = unknown1 * unknown2;
		zd = unknown3 * unknown0 * unknown2;

		setPos(x, y, z);

		unknown3 = MathHelper.sqrt(xd * xd + zd * zd);

		yRotO = yRot = (float)(Math.atan2((double)xd, (double)zd) * 180.0D / 3.1415927410125732D);
		xRotO = xRot = (float)(Math.atan2((double)yd, (double)unknown3) * 180.0D / 3.1415927410125732D);

		makeStepSound = false;
	}

	@Override
	public void tick()
	{
		time++;

		xRotO = xRot;
		yRotO = yRot;

		xo = x;
		yo = y;
		zo = z;

		if(hasHit)
		{
			stickTime++;

			if(type == 0)
			{
				if(stickTime >= 300 && Math.random() < 0.009999999776482582D)
				{
					remove();
				}
			} else if(type == 1 && stickTime >= 20) {
				remove();
			}
		} else {
			xd *= 0.998F;
			yd *= 0.998F;
			zd *= 0.998F;

			yd -= 0.02F * gravity;

			int unknown0 = (int)(MathHelper.sqrt(xd * xd + yd * yd + zd * zd) / 0.2F + 1.0F);

			float x0 = xd / (float)unknown0;
			float y0 = yd / (float)unknown0;
			float z0 = zd / (float)unknown0;

			for(int unknown4 = 0; unknown4 < unknown0 && !collision; unknown4++)
			{
				AABB unknown5 = bb.expand(x0, y0, z0);

				if(level.getCubes(unknown5).size() > 0)
				{
					collision = true;
				}

				List blockMapEntitiesList = level.blockMap.getEntities(this, unknown5);

				for(int currentEntity = 0; currentEntity < blockMapEntitiesList.size(); currentEntity++)
				{
					Entity entity = (Entity)blockMapEntitiesList.get(currentEntity);

					if((entity).isShootable() && (entity != owner || time > 5))
					{
						entity.hurt(this, damage);

						collision = true;

						remove();

						return;
					}
				}

				if(!collision)
				{
					bb.move(x0, y0, z0);

					x += x0;
					y += y0;
					z += z0;

					blockMap.moved(this);
				}
			}

			if(collision)
			{
				hasHit = true;

				xd = yd = zd = 0.0F;
			}

			if(!hasHit)
			{
				float unknown6 = MathHelper.sqrt(xd * xd + zd * zd);

				yRot = (float)(Math.atan2((double)xd, (double)zd) * 180.0D / 3.1415927410125732D);

				for(xRot = (float)(Math.atan2((double)yd, (double)unknown6) * 180.0D / 3.1415927410125732D); xRot - xRotO < -180.0F; xRotO -= 360.0F)
				{
					// TODO: ?.
				}

				while(xRot - xRotO >= 180.0F)
				{
					xRotO += 360.0F;
				}

				while(yRot - yRotO < -180.0F)
				{
					yRotO -= 360.0F;
				}

				while(yRot - yRotO >= 180.0F)
				{
					yRotO += 360.0F;
				}
			}
		}
	}

	@Override
	public void render(float unknown0)
	{	
		textureId = new TextureLocation("/item/arrows.png").bindTexture();
		
		GL11.glPushMatrix();
		GL11.glTranslatef(xo + (x - xo) * unknown0, this.yo + (this.y - this.yo) * unknown0 - this.heightOffset / 2.0F, this.zo + (this.z - this.zo) * unknown0);
		GL11.glRotatef(yRotO + (yRot - yRotO) * unknown0 - 90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(xRotO + (xRot - xRotO) * unknown0, 0.0F, 0.0F, 1.0F);
		GL11.glRotatef(45.0F, 1.0F, 0.0F, 0.0F);
		Tessellator var10 = Tessellator.instance;
		byte var11 = 0;
		float fix = 0.002f;
		float var12 = 0.0F + fix;
		float var13 = 0.5F - fix;
		float var14 = (float) (0 + var11 * 10) / 32.0F + fix;
		float var15 = (float) (5 + var11 * 10) / 32.0F - fix;
		float var16 = 0.0F + fix;
		float var17 = 0.15625F - fix;
		float var18 = (float) (5 + var11 * 10) / 32.0F + fix;
		float var19 = (float) (10 + var11 * 10) / 32.0F - fix;
		float var20 = 0.05625F + fix;
		GL11.glEnable(GL11.GL_RESCALE_NORMAL);
		GL11.glScalef(0.05625F, var20, var20);
		GL11.glNormal3f(var20, 0.0F, 0.0F);
		var10.startDrawing(7);
		var10.addVertexWithUV(-7.0D, -2.0D, -2.0D, (double) var16, (double) var18);
		var10.addVertexWithUV(-7.0D, -2.0D, 2.0D, (double) var17, (double) var18);
		var10.addVertexWithUV(-7.0D, 2.0D, 2.0D, (double) var17, (double) var19);
		var10.addVertexWithUV(-7.0D, 2.0D, -2.0D, (double) var16, (double) var19);
		var10.draw();
		GL11.glNormal3f(-var20, 0.0F, 0.0F);
		var10.startDrawing(7);
		var10.addVertexWithUV(-7.0D, 2.0D, -2.0D, (double) var16, (double) var18);
		var10.addVertexWithUV(-7.0D, 2.0D, 2.0D, (double) var17, (double) var18);
		var10.addVertexWithUV(-7.0D, -2.0D, 2.0D, (double) var17, (double) var19);
		var10.addVertexWithUV(-7.0D, -2.0D, -2.0D, (double) var16, (double) var19);
		var10.draw();

		for (int var23 = 0; var23 < 4; ++var23) {
			GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
			GL11.glNormal3f(0.0F, 0.0F, -var20);
			var10.startDrawing(7);
			var10.addVertexWithUV(-8.0D, -2.0D, 0.0D, (double) var12, (double) var14);
			var10.addVertexWithUV(8.0D, -2.0D, 0.0D, (double) var13, (double) var14);
			var10.addVertexWithUV(8.0D, 2.0D, 0.0D, (double) var13, (double) var15);
			var10.addVertexWithUV(-8.0D, 2.0D, 0.0D, (double) var12, (double) var15);
			var10.draw();
		}

		GL11.glDisable(GL11.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
	}

	@Override
	public void playerTouch(Entity entity)
	{
		Player player = (Player)entity;

		if(hasHit && owner == player && player.arrows < 99)
		{
			TakeEntityAnim takeEntityAnim = new TakeEntityAnim(level, this, player);

			level.addEntity(takeEntityAnim);

			player.arrows++;

			remove();
		}
	}

	@Override
	public void awardKillScore(Entity entity, int score)
	{
		owner.awardKillScore(entity, score);
	}

	public static final long serialVersionUID = 0L;

	private float xd;
	private float yd;
	private float zd;

	private float yRot;
	private float xRot;
	private float yRotO;
	private float xRotO;

	private boolean hasHit = false;

	private int stickTime = 0;

	private Entity owner;

	private int time = 0;
	private int type = 0;

	private float gravity = 0.0F;

	private int damage;

	public Entity getOwner()
	{
		return owner;
	}
}

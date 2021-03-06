/* <LICENSE>
Copyright (C) 2013-2016 Louis JEAN

This file is part of Terra Magnetica.

Terra Magnetica is free software: you can redistribute it and/or modify
it under the terms of the GNU Lesser General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Terra Magnetica is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public License
along with Terra Magnetica. If not, see <http://www.gnu.org/licenses/>.
 </LICENSE> */

package org.terramagnetica.game.lvldefault;

import java.awt.Image;

import org.terramagnetica.game.GameRessources;
import org.terramagnetica.game.lvldefault.rendering.RenderEntityTexture;
import org.terramagnetica.opengl.engine.Renderable;
import org.terramagnetica.opengl.engine.TextureQuad;
import org.terramagnetica.physics.Force;
import org.terramagnetica.physics.Hitbox;
import org.terramagnetica.physics.HitboxCircle;
import org.terramagnetica.ressources.ImagesLoader;
import org.terramagnetica.ressources.TexturesLoader;

import net.bynaryscode.util.Color4f;
import net.bynaryscode.util.maths.geometric.DimensionsInt;
import net.bynaryscode.util.maths.geometric.Vec2f;
import net.bynaryscode.util.maths.geometric.Vec3d;

/**
 * Une lampe perturbatrice est un type particulier de lampe.
 * <ul><li>Lorsqu'elle est jaune, l'aimant tourne autour d'elle, plus
 * ou moins vite, mais ne change pas de sens.
 * <li>Lorsqu'elle est rouge, l'aimant tourne toujours, mais effectue
 * des oscillations violentes.
 * </ul>
 * <p>Dans le jeu elle est appel�e : "lampe magn�tique � champs".
 * @author Louis JEAN
 *
 */
public class LampePerturbatrice extends AbstractLamp implements InfluenceMagnetiqueMajeure {
	
	private static final long serialVersionUID = 1L;
	
	private static final float TANGENT_FORCE = 40;
	private static final float DEFAULT_PERIOD = 1000;
	
	//Variables d'influence
	/** La p�riode en ms */
	private float period = DEFAULT_PERIOD;
	private float normalMoveOrigin = 0;
	private float normalMoveOffset = 0;
	
	//RENDUS
	private float indicatorDirection = 0;

	public LampePerturbatrice() {
		super();
	}
	
	public LampePerturbatrice(int x, int y) {
		super(x, y);
	}
	
	@Override
	public Image getImage() {
		return ImagesLoader.get(GameRessources.ID_LAMP_PERTURBATRICE_OFF);
	}
	
	@Override
	public TextureQuad getMinimapIcon() {
		return TexturesLoader.getQuad(GameRessources.ID_MAP_LAMP_RANDOM);
	}
	
	@Override
	protected void createRender() {
		//image principale
		this.renderManager.putRender("on", new RenderEntityTexture(GameRessources.ID_LAMP_PERTURBATRICE_ON));
		this.renderManager.putRender("off", new RenderEntityTexture(GameRessources.ID_LAMP_PERTURBATRICE_OFF));

		this.renderManager.render(this.state ? "on" : "off");
		
		//indicateur de polarit�
		this.renderManager.putRender("indicator", new RenderEntityTexture(GameRessources.ID_MAGNETIC_FIELD_INDICATOR));
		this.renderManager.setEffect("indicator", true);
		this.updateIndicator();
	}
	
	/** Met � jour la position du rendu de l'indicateur de direction
	 * de la lampe  */
	private void updateIndicator() {
		Renderable indicator = this.renderManager.getRender("indicator");
		
		float dir = indicatorDirection;
		float tX = (float) Math.cos(dir) * 0.7f;
		float tY = (float) Math.sin(dir) * 0.5f;
		Color4f color = this.state ? new Color4f(1f, 0f, 0f) : new Color4f(1f, 1f, 0f);
		
		indicator.withColor(color).setPositionOffset(tX, tY, 0.25f);
	}
	
	@Override
	public DimensionsInt getDimensions() {
		return new DimensionsInt(192, 192);
	}
	
	@Override
	public DimensionsInt getImgDimensions() {
		return new DimensionsInt(256, 256);
	}
	
	@Override
	public Hitbox createHitbox() {
		return new HitboxCircle(this.getDimensionsf().getHeight() / 2);
	}
	
	@Override
	public void controls(GamePlayingDefault game, long delta, EntityMoving ent) {
		updateLogic(delta, game);
		
		double t = game.getTime();
		
		float forceX = 0, forceY = 0;
		
		Vec2f entLoc = ent.getPositionf();
		double d = getDistancef(ent);
		Vec3d en = Vec3d.unitVector(entLoc.x - this.hitbox.getPositionX(), entLoc.y - this.hitbox.getPositionY());
		Vec3d et = new Vec3d(en.y, - en.x);
		Vec3d vi = new Vec3d(ent.getMovementX(), ent.getMovementY());
		double vn = vi.dotProduct(en);
		Vec3d vnVect = en.multiply(vn);
		Vec3d vtVect = vi.substract(vnVect);
		double vt = vtVect.length();
		
		//Force tangentielle sinuso�dale
		double tangentForce = TANGENT_FORCE * Math.sin(t / this.period * (0.5f / Math.PI));
		double normalCorrection = -0;
		
		forceX += tangentForce * et.x + normalCorrection * en.x;
		forceY += tangentForce * et.y + normalCorrection * en.y;
		
		if (this.state) {
			float normalForce = 0;
		}
		else {
			//TEMPORAIRE
			forceX = forceY = 0;
		}
		
		ent.getHitbox().addForce(new Force(forceX, forceY));
	}
	
	@Override
	public boolean hasPermissionForCollision(EntityMoving e) {
		return true;
	}
	
	@Override
	public boolean isAvailableFor(EntityMoving e) {
		return true;
	}
	
	@Override
	public void updateLogic(long dT, GamePlayingDefault game) {
		if (this.updated) return;
		this.updated = true;
		
		super.updateLogic(dT, game);
		
		//mise � jour de l'�tat
		if (this.didStateChanged) {
			if (this.state) {
				this.normalMoveOrigin = game.getTime() / 1000f - this.normalMoveOffset;
			}
			else {
				this.normalMoveOffset = (game.getTime() / 1000f - this.normalMoveOrigin) % this.period;
			}
		}
		
		//mise � jour du rendu
		if (this.didStateChanged) {
			this.renderManager.render(this.state ? "on" : "off");
		}
		updateIndicator();
	}
}

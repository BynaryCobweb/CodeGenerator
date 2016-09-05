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

package org.terramagnetica.opengl.engine;

import net.bynaryscode.util.maths.geometric.Vec3d;

public abstract class Renderable {
	public void renderAt(double x, double y, double z, Painter painter) {
		renderAt(new Vec3d(x, y, z), 0, new Vec3d(0, 0, 1), new Vec3d(1, 1, 1), painter);
	}
	
	public abstract void renderAt(Vec3d position, double rotation, Vec3d up, Vec3d scale, Painter painter);
}

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

import net.bynaryscode.util.Color4f;
import net.bynaryscode.util.FileFormatException;

public class Material {
	
	private Color4f color = new Color4f();
	private TextureImpl tex = new TextureImpl();
	private String texPath = "";
	
	private String name = "";
	
	public static Material parseMtl(String mtlFile, String mtlName) throws FileFormatException {
		Material ret = new Material();
		
		String lines[] = mtlFile.split("\n");
		boolean found = false;
		
		for (String line : lines) {
			if (line.startsWith("newmtl ")) {
				if (line.length() < 8) {
					throw new FileFormatException("fichier .mtl : nom de materiau attendu.");
				}
				String matName = line.substring(7);
				if (matName.equals(mtlName)) {
					found = true;
					continue;
				}
				else if (found) {
					break;
				}
			}
			
			String[] fragments = line.split(" ");
			if (fragments.length == 0) continue;
			
			if (fragments[0].equals("map_Kd")) {
				if (fragments.length < 2) {
					throw new FileFormatException("fichier .mtl : chemin de la texture attendu");
				}
				ret.texPath = line.substring(7);
			}
		}
		
		return ret;
	}
	
	/**
	 * Applique le mat�riau au painter.
	 * @param painter
	 */
	public void use(Painter painter) {
		painter.setColor(this.color);
		if (this.tex.getGLTextureID() == 0) {
			painter.setTexture(null);
		}
		else {
			painter.setTexture(this.tex);
		}
	}
	
	/**
	 * Permet de d�sactiver, apr�s avoir dessin� le mod�le, certaines
	 * options utilis�es par le mat�riau.
	 * @param painter
	 */
	public void unset(Painter painter) {
		
	}
	
	public String getTexPath() {
		return this.texPath;
	}
	
	public void setTextureID(int id) {
		this.tex.setTextureID(id);
	}

	public int getTextureID() {
		return this.tex.getGLTextureID();
	}
	
	public boolean hasTextures() {
		return !"".equals(this.texPath);
	}
}

package com.cheatbreaker.client.ui.util.font;

import com.cheatbreaker.bridge.client.renderer.texture.DynamicTextureBridge;
import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.bridge.util.ResourceLocationBridge;
import com.cheatbreaker.client.CheatBreaker;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

@SuppressWarnings("WeakerAccess") public class CBFont {

	protected CharData[] charData = new CharData[256];

	protected DynamicTextureBridge tex;
	protected Font font;

	protected boolean antiAlias;
	protected boolean fractionalMetrics;

	protected int fontHeight = -1;
	protected int charOffset = 0;

	private float imgSize = 1048;

	public float dbg_fontSize;

	public CBFont(ResourceLocationBridge ResourceLocationBridge, float size) {
		this.dbg_fontSize = size;
		Font tmp;
		try {
			InputStream is = Ref.getMinecraft().bridge$getResourceManager().bridge$getResource(ResourceLocationBridge).bridge$getInputStream();
			tmp = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(size);
		} catch (IOException | FontFormatException e) {
			tmp = new Font("Arial", Font.PLAIN, (int) size);
			e.printStackTrace();
		}

		this.font = tmp;
		this.antiAlias = true;
		this.fractionalMetrics = true;
		this.tex = this.setupTexture(this.font, true, true, this.charData);
	}

	protected DynamicTextureBridge setupTexture(Font font, boolean antiAlias, boolean fractionalMetrics, CharData[] chars) {
		BufferedImage img = generateFontImage(font, antiAlias, fractionalMetrics, chars);

		try {
			return Ref.getInstanceCreator().createDynamicTexture(img);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private BufferedImage generateFontImage(Font font, boolean antiAlias, boolean fractionalMetrics,
	                                        CharData[] chars) {
		int imgSize = (int) this.imgSize;
		BufferedImage bufferedImage = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g = (Graphics2D) bufferedImage.getGraphics();

		g.setFont(font);

		g.setColor(new Color(255, 255, 255, 0));
		g.fillRect(0, 0, imgSize, imgSize);
		g.setColor(Color.WHITE);

		g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
				fractionalMetrics ? RenderingHints.VALUE_FRACTIONALMETRICS_ON :
						RenderingHints.VALUE_FRACTIONALMETRICS_OFF);

		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				antiAlias ? RenderingHints.VALUE_TEXT_ANTIALIAS_ON : RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				antiAlias ? RenderingHints.VALUE_ANTIALIAS_ON : RenderingHints.VALUE_ANTIALIAS_OFF);

		FontMetrics fontMetrics = g.getFontMetrics();

		int charHeight = 0;
		int positionX = 0;
		int positionY = 1;

		for (int i = 0; i < chars.length; i++) {
			char ch = (char) i;

			CharData charData = new CharData();
			Rectangle2D dimensions = fontMetrics.getStringBounds(String.valueOf(ch), g);

			charData.width = (dimensions.getBounds().width + 8);
			charData.height = dimensions.getBounds().height;

			if (positionX + charData.width >= imgSize) {
				positionX = 0;
				positionY += charHeight;
				charHeight = 0;
			}

			if (charData.height > charHeight) {
				charHeight = charData.height;
			}

			charData.storedX = positionX;
			charData.storedY = positionY;

			if (charData.height > this.fontHeight) {
				this.fontHeight = charData.height;
			}

			chars[i] = charData;

			g.drawString(String.valueOf(ch), positionX + 2, positionY + fontMetrics.getAscent());

			positionX += charData.width;
		}

		return bufferedImage;
	}

	protected void drawChar(CharData[] chars, char c, float x, float y) throws ArrayIndexOutOfBoundsException {
		try {
			drawQuad(x, y, chars[c].width, chars[c].height, chars[c].storedX, chars[c].storedY, chars[c].width,
					chars[c].height);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void drawQuad(float x, float y, float width, float height, float srcX, float srcY,
	                      float srcWidth, float srcHeight) {

		float renderSRCX = srcX / imgSize;
		float renderSRCY = srcY / imgSize;
		float renderSRCWidth = srcWidth / imgSize;
		float renderSRCHeight = srcHeight / imgSize;

		GL11.glTexCoord2f(renderSRCX + renderSRCWidth, renderSRCY);
		GL11.glVertex2d(x + width, y);

		GL11.glTexCoord2f(renderSRCX, renderSRCY);
		GL11.glVertex2d(x, y);

		GL11.glTexCoord2f(renderSRCX, renderSRCY + renderSRCHeight);
		GL11.glVertex2d(x, y + height);

		GL11.glTexCoord2f(renderSRCX, renderSRCY + renderSRCHeight);
		GL11.glVertex2d(x, y + height);

		GL11.glTexCoord2f(renderSRCX + renderSRCWidth, renderSRCY + renderSRCHeight);
		GL11.glVertex2d(x + width, y + height);

		GL11.glTexCoord2f(renderSRCX + renderSRCWidth, renderSRCY);
		GL11.glVertex2d(x + width, y);
	}

	public int getHeight() {
		boolean followMinecraftScale = false;

		try {
			followMinecraftScale = (Boolean) CheatBreaker.getInstance().globalSettings.followMinecraftScale.getValue();
		} catch (Exception ignored) {

		}

		if (followMinecraftScale) {
			return (int) (((this.fontHeight * (2f / Ref.getInstanceCreator().createScaledResolutionBridge().bridge$getScaleFactor())) - 8) / 2);
		}

		return (this.fontHeight - 8) / 2;
	}

	public int getStringWidth(String text) {
		int width = 0;

		for (char c : text.toCharArray()) {
			if (c < this.charData.length) {
				width += this.charData[c].width - 8 + this.charOffset;
			}
		}

		return width / 2;
	}

	public boolean isAntiAlias() {
		return this.antiAlias;
	}

	public void setAntiAlias(boolean antiAlias) {
		if (this.antiAlias != antiAlias) {
			this.antiAlias = antiAlias;
			tex = setupTexture(this.font, antiAlias, this.fractionalMetrics, this.charData);
		}
	}

	public boolean isFractionalMetrics() {
		return this.fractionalMetrics;
	}

	public void setFractionalMetrics(boolean fractionalMetrics) {
		if (this.fractionalMetrics != fractionalMetrics) {
			this.fractionalMetrics = fractionalMetrics;
			tex = setupTexture(this.font, this.antiAlias, fractionalMetrics, this.charData);
		}
	}

	public Font getFont() {
		return this.font;
	}

	public void setFont(Font font) {
		this.font = font;
		tex = setupTexture(font, this.antiAlias, this.fractionalMetrics, this.charData);
	}

	protected class CharData {
		public int width;
		public int height;
		public int storedX;
		public int storedY;

		protected CharData() {

		}
	}

}

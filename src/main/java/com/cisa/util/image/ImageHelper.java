package com.cisa.util.image;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

/**
 * 图片处理工具
 * 
 * @author Daniel
 * @version 1.0
 */
public class ImageHelper {

	/**
	 * 图片格式转换
	 * 
	 * @param inputImagePath
	 *            需要转化的图片路径，例如：".\\target\\QR_Code.JPG"
	 * @param outputImagePath
	 *            转化后的图片输出路径，例如：".\\target\\QR_Code.PNG"
	 * @param formatName
	 *            需要转成的格式，例如：jpeg, jpg, png, bmp, wbmp, and gif
	 * @return 是否成功的布尔值
	 */
	public static boolean convertFormat(String inputImagePath,
			String outputImagePath, String formatName) {
		try {
			FileInputStream inputStream = new FileInputStream(inputImagePath);
			FileOutputStream outputStream = new FileOutputStream(
					outputImagePath);

			// reads input image from file
			BufferedImage inputImage = ImageIO.read(inputStream);

			// writes to the output image in specified format
			boolean result = ImageIO
					.write(inputImage, formatName, outputStream);

			// needs to close the streams
			outputStream.close();
			inputStream.close();

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 图片添加文字水印
	 * 
	 * @param text
	 *            需要添加的文字
	 * @param inputImagePath
	 *            需要转化的图片路径，例如：".\\target\\QR_Code.JPG"
	 * @param outputImagePath
	 *            转化后的图片输出路径，例如：".\\target\\QR_Code.PNG"
	 * @return 是否成功的布尔值
	 */
	public static boolean addTextWatermark(String text, String inputImagePath,
			String outputImagePath) {
		try {
			File sourceImageFile = new File(inputImagePath);
			File destImageFile = new File(outputImagePath);

			BufferedImage sourceImage = ImageIO.read(sourceImageFile);
			Graphics2D g2d = (Graphics2D) sourceImage.getGraphics();

			// initializes necessary graphic properties
			// 参数中的0.5f代表text的颜色深度
			AlphaComposite alphaChannel = AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, 0.5f);
			g2d.setComposite(alphaChannel);
			// 参数中传递的是字体的参数,32代表的字体的大小
			g2d.setColor(Color.DARK_GRAY);
			g2d.setFont(new Font("Arial", Font.BOLD, 32));
			FontMetrics fontMetrics = g2d.getFontMetrics();
			Rectangle2D rect = fontMetrics.getStringBounds(text, g2d);

			// calculates the coordinate where the String is painted
			int centerX = (sourceImage.getWidth() - (int) rect.getWidth()) / 32;
			int centerY = sourceImage.getHeight() / 16;

			// paints the textual watermark
			g2d.drawString(text, centerX, centerY);
			String[] strArray = outputImagePath.split("\\.");
			ImageIO.write(sourceImage, strArray[strArray.length - 1],
					destImageFile);
			g2d.dispose();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 图片添加图片水印
	 * 
	 * @param watermarkImageFilePath
	 *            水印图片路径
	 * @param inputImagePath
	 *            需要转化的图片路径，例如：".\\target\\QR_Code.JPG"
	 * @param outputImagePath
	 *            转化后的图片输出路径，例如：".\\target\\QR_Code.PNG"
	 * @return 是否成功的布尔值
	 */
	public static boolean addImageWatermark(String watermarkImageFilePath,
			String inputImagePath, String outputImagePath) {
		try {
			File watermarkImageFile = new File(watermarkImageFilePath);
			File sourceImageFile = new File(inputImagePath);
			File destImageFile = new File(outputImagePath);

			BufferedImage sourceImage = ImageIO.read(sourceImageFile);
			BufferedImage watermarkImage = ImageIO.read(watermarkImageFile);

			// initializes necessary graphic properties
			Graphics2D g2d = (Graphics2D) sourceImage.getGraphics();
			AlphaComposite alphaChannel = AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, 0.5f);
			g2d.setComposite(alphaChannel);

			// calculates the coordinate where the image is painted
			int topLeftX = (sourceImage.getWidth() - watermarkImage.getWidth()) / 2;
			int topLeftY = (sourceImage.getHeight() - watermarkImage
					.getHeight()) / 2;

			// paints the image watermark
			g2d.drawImage(watermarkImage, topLeftX, topLeftY, null);
			String[] strArray = outputImagePath.split("\\.");
			ImageIO.write(sourceImage, strArray[strArray.length - 1],
					destImageFile);
			g2d.dispose();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// public static void main(String[] args) {
	// String inputImage = "c:\\watermarked.JPG";
	// String oututImage = "c:\\out_watermarked.PNG";
	// String watermarkImageFilePath = "c:\\watermarkImageFilePath.JPG";
	// addTextWatermark("I'm the watermark", inputImage, oututImage);
	// System.out.println("The tex watermark is added to the image.");
	// addImageWatermark(watermarkImageFilePath, inputImage, oututImage);
	// System.out.println("The image watermark is added to the image.");
	//
	// String formatName = "gif";
	// try {
	// boolean result = convertFormat(inputImage, oututImage, formatName);
	// if (result) {
	// System.out.println("Image converted successfully.");
	// } else {
	// System.out.println("Could not convert image.");
	// }
	// } catch (Exception ex) {
	// System.out.println("Error during converting image.");
	// ex.printStackTrace();
	// }
	// }
}

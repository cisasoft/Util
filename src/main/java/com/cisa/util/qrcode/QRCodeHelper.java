package com.cisa.util.qrcode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

/**
 * 二维码工具
 * 
 * @author Daniel
 * @version 1.0
 */
public class QRCodeHelper {

	/**
	 * 字符串生成二维码
	 * 
	 * @param qrCodeData
	 * 			需要解析的字符串
	 * @param filePath
	 * 			图片路径，例如：".\\target\\QR_Code.JPG"
	 * @param charset
	 * 			字符串编码，例如："UTF-8"
	 * @param qrCodeheight
	 * 			图片高度
	 * @param qrCodewidth
	 * 			图片宽度
	 * @return 是否成功的布尔值
	 */
	public static boolean createQRCode(String qrCodeData, String filePath,
			String charset, int qrCodeheight, int qrCodewidth) {
		try {
			BitMatrix matrix = new MultiFormatWriter().encode(new String(
					qrCodeData.getBytes(charset), charset),
					BarcodeFormat.QR_CODE, qrCodewidth, qrCodeheight);
			MatrixToImageWriter.writeToPath(matrix,
					filePath.substring(filePath.lastIndexOf('.') + 1),
					new File(filePath).toPath());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 二维码解析成字符串
	 * 
	 * @param filePath
	 * 			文件路径，例如：".\\target\\QR_Code.JPG"
	 * @param charset
	 * 			字符串编码，例如："UTF-8"
	 * @return 解析出来的字符串
	 * @throws FileNotFoundException 文件未找到
	 * @throws IOException IO流问题
	 * @throws NotFoundException 未找到信息
	 */
	public static String readQRCode(String filePath, String charset)
			throws FileNotFoundException, IOException, NotFoundException {
		BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
				new BufferedImageLuminanceSource(
						ImageIO.read(new FileInputStream(filePath)))));
		Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap);
		return qrCodeResult.getText();
	}

	// public static void main(String[] args) throws WriterException,
	// IOException,
	// NotFoundException {
	// String qrCodeData = "Hi QRCode!";
	// String filePath = "QRCode2.png";
	// String charset = "UTF-8"; // or "ISO-8859-1"
	// createQRCode(qrCodeData, filePath, charset, 600, 600);
	// System.out.println("QR Code image created successfully!");
	// System.out.println("Data read from QR Code: "
	// + readQRCode(filePath, charset));
	// }

	// /**
	// *
	// * @param s
	// * 待生成图片的字符串
	// * @param imagePath
	// * 指定图片输出目录以及文件名，例如：".\\target\\QR_Code.JPG"，
	// * 指将文件输出到项目目录的target文件夹下
	// * @return 是否成功的布尔值
	// */
	// public static boolean generateImage(String s, String imagePath) {
	// ByteArrayOutputStream out = QRCode.from(s).to(ImageType.PNG).stream();
	// try {
	// FileOutputStream fout = new FileOutputStream(new File(imagePath));
	// fout.write(out.toByteArray());
	// fout.flush();
	// fout.close();
	// return true;
	// } catch (Exception e) {
	// e.printStackTrace();
	// return false;
	// }
	// }
}

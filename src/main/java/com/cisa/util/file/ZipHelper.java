package com.cisa.util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * 压缩解压Zip文件
 * 
 * @author Xiaolong.Cisa
 * @version 1.0
 */
public class ZipHelper {

	/**
	 * 单文件压缩
	 * 
	 * @param sorucePath
	 *            需要压缩的文件路径，例如 C:\\test.log
	 * @param targetPath
	 *            输出目标文件路径，例如C:\\MyFile.zip
	 * @param zipName
	 *            压缩包内文件更名为，例如rename.log表示将test.log更名为rename.log
	 * @return 布尔值是否压缩成功
	 */
	public static boolean zipFile(String sorucePath, String targetPath,
			String zipName) {
		try {
			byte[] buffer = new byte[1024];
			FileInputStream in = new FileInputStream(sorucePath);
			FileOutputStream fos = new FileOutputStream(targetPath);
			ZipOutputStream zos = new ZipOutputStream(fos);
			ZipEntry ze = new ZipEntry(zipName);
			zos.putNextEntry(ze);

			int len;
			while ((len = in.read(buffer)) > 0) {
				zos.write(buffer, 0, len);
			}

			zos.closeEntry();
			// remember close it
			zos.close();
			fos.close();
			in.close();
			System.out.println("文件" + targetPath + "压缩完成");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 多文件压缩
	 * 
	 * @param fileList
	 *            压缩文件绝对路径的list列表，例如{C:\\test1.log，C:\\test2.log}
	 * @param OutputZipFile
	 *            输出目标文件路径，例如C:\\MyFile.zip
	 * @return 布尔值是否压缩成功
	 */
	public static boolean zipFiles(List<String> fileList, String outputZipFile) {
		try {
			byte[] buffer = new byte[1024];
			FileOutputStream fos = new FileOutputStream(outputZipFile);
			ZipOutputStream zos = new ZipOutputStream(fos);
			System.out.println("Output to Zip : " + outputZipFile);
			for (String file : fileList) {
				System.out.println("File Added : " + file);
				ZipEntry ze = new ZipEntry(file);
				zos.putNextEntry(ze);
				FileInputStream in = new FileInputStream(file);
				int len;
				while ((len = in.read(buffer)) > 0) {
					zos.write(buffer, 0, len);
				}
				in.close();
			}
			zos.closeEntry();
			// remember close it
			zos.close();
			fos.close();
			System.out.println("文件" + outputZipFile + "压缩完成");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * 对文件夹进行压缩
	 * 
	 * @param soruceFolder
	 *            压缩源文件夹绝对路径，例如C:\\test\\
	 * @param OutputZipFile
	 *            输出目标文件路径，例如C:\\MyFile.zip
	 * @return 布尔值是否压缩成功
	 */
	public static boolean zipFolders(String soruceFolder, String outputZipFile) {
		List<String> fileList = FileHelper.getFileFromFolder(soruceFolder);
		try {
			byte[] buffer = new byte[1024];
			FileOutputStream fos = new FileOutputStream(outputZipFile);
			ZipOutputStream zos = new ZipOutputStream(fos);
			System.out.println("Output to Zip : " + outputZipFile);
			for (String file : fileList) {
				System.out.println("File Added : " + file);
				ZipEntry ze = new ZipEntry(file);
				zos.putNextEntry(ze);
				FileInputStream in = new FileInputStream(file);
				int len;
				while ((len = in.read(buffer)) > 0) {
					zos.write(buffer, 0, len);
				}
				in.close();
			}
			zos.closeEntry();
			// remember close it
			zos.close();
			fos.close();
			System.out.println("文件" + outputZipFile + "压缩完成");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 解压Zip文件
	 * 
	 * @param inputZipFile
	 *            需要解压的源文件，例如C:\\MyFile.zip
	 * @param outputFolder
	 *            需要输出的文件夹，例如C:\\test\\
	 * @return 是否解压成功布尔值
	 */
	public static boolean unZip(String inputZipFile, String outputFolder) {
		try {
			byte[] buffer = new byte[1024];
			// create output directory is not exists
			File folder = new File(outputFolder);
			if (!folder.exists()) {
				folder.mkdir();
			}
			// get the zip file content
			ZipInputStream zis = new ZipInputStream(new FileInputStream(
					inputZipFile));
			// get the zipped file list entry
			ZipEntry ze = zis.getNextEntry();
			while (ze != null) {
				String fileName = ze.getName();
				File newFile = new File(folder.getAbsolutePath()
						+ File.separator + fileName);
				System.out.println("file unzip : " + newFile.getAbsoluteFile());
				// create all non exists folders
				// else you will hit FileNotFoundException for compressed folder
				new File(newFile.getParent()).mkdirs();
				FileOutputStream fos = new FileOutputStream(newFile);
				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fos.close();
				ze = zis.getNextEntry();
			}

			zis.closeEntry();
			zis.close();
			System.out.println("目录" + outputFolder + "解压完成");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}

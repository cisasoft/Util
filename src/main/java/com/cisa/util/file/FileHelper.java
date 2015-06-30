package com.cisa.util.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件处理函数
 * 
 * @author Xiaolong.Cisa
 * @version 1.0
 * 
 */
public class FileHelper {

	/**
	 * 根据路径删除指定的目录或文件，无论存在与否
	 * 
	 * @param sPath
	 *            要删除的目录或文件
	 * @return 删除成功返回 true，否则返回 false。
	 */
	public static boolean deleteFolder(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		// 判断目录或文件是否存在
		if (!file.exists()) { // 不存在返回 false
			return flag;
		} else { // 判断是否为文件
			if (file.isFile()) { // 为文件时调用删除文件方法
				return deleteFile(sPath);
			} else { // 为目录时调用删除目录方法
				return deleteDirectory(sPath);
			}
		}
	}

	/**
	 * 删除单个文件
	 * 
	 * @param sPath
	 *            被删除文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	/**
	 * 删除目录（文件夹）以及目录下的文件
	 * 
	 * @param sPath
	 *            被删除目录的文件路径
	 * @return 目录删除成功返回true，否则返回false
	 */
	public static boolean deleteDirectory(String sPath) {
		// 如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if (!sPath.endsWith(File.separator)) {
			sPath = sPath + File.separator;
		}
		File dirFile = new File(sPath);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		boolean flag = true;
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) { // 删除子文件
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			} else { // 删除子目录
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag)
			return false;
		// 删除当前目录
		if (dirFile.delete()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取源文件夹下所有文件
	 * 
	 * @param soruceFolder
	 *            源文件夹的路径，例如C:\\test\\
	 * @return 获得的文件路径的list集合
	 */
	public static List<String> getFileFromFolder(String soruceFolder) {
		List<String> fileList = new ArrayList<String>();
		File node = new File(soruceFolder);
		if (node.isFile()) { // add file only
			fileList.add(node.getAbsoluteFile().toString());
		}
		if (node.isDirectory()) { // add directory
			String[] subNode = node.list();
			for (String filename : subNode) {
				File f = new File(node, filename);
				fileList.addAll(getFileFromFolder(f.getAbsoluteFile()
						.toString()));
			}
		}
		return fileList;
	}

	/**
	 * 利用缓冲流进行单文件的复制工作
	 * 
	 * @param sourcefile
	 *            源文件的File对象
	 * @param targetFile
	 *            目标文件的File对象
	 * @return 是否复制成功的布尔值
	 */
	public static boolean copyFile(File sourcefile, File targetFile) {
		try {
			// 新建文件输入流并对它进行缓冲
			FileInputStream input = new FileInputStream(sourcefile);
			BufferedInputStream inbuff = new BufferedInputStream(input);

			// 新建文件输出流并对它进行缓冲
			FileOutputStream out = new FileOutputStream(targetFile);
			BufferedOutputStream outbuff = new BufferedOutputStream(out);

			// 缓冲数组
			byte[] b = new byte[1024 * 5];
			int len = 0;
			while ((len = inbuff.read(b)) != -1) {
				outbuff.write(b, 0, len);
			}

			// 刷新此缓冲的输出流
			outbuff.flush();

			// 关闭流
			outbuff.close();
			inbuff.close();
			out.close();
			input.close();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 利用文件通道进行单文件的复制工作
	 * 
	 * @param sfPath
	 *            源文件的路径
	 * @param tfPath
	 *            目标文件的路径
	 * @return 是否复制成功的布尔值
	 */
	public static boolean copyFile(String sfPath, String tfPath) {
		try {
			// 新建文件输入流
			FileInputStream input = new FileInputStream(sfPath);
			FileChannel fin = input.getChannel();

			// 新建文件输出流
			FileOutputStream out = new FileOutputStream(tfPath);
			FileChannel fout = out.getChannel();

			fin.transferTo(0, fin.size(), fout);

			fin.close();
			fout.close();
			out.close();
			input.close();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 复制整个目录下的子目录和文件，但不含创建这个根目录本身
	 * 
	 * @param sourceDir
	 *            源目录路径
	 * @param targetDir
	 *            子目录路径
	 * @return 返回是否成功的布尔值
	 */
	public static boolean copyDirectory(String sourceDir, String targetDir) {
		try {
			File sf = new File(sourceDir);
			File tf = new File(targetDir);
			// 新建目标目录
			tf.mkdirs();
			// 获取源文件夹当下的文件或目录
			File[] file = sf.listFiles();
			for (int i = 0; i < file.length; i++) {
				if (file[i].isFile()) {
					// 源文件
					File sourceFile = file[i];
					// 目标文件
					File targetFile = new File(tf.getAbsolutePath()
							+ File.separator + file[i].getName());
					// copyFile(sourceFile, targetFile);
					copyFile(sourceFile.getAbsolutePath(),
							targetFile.getAbsolutePath());
				}
				if (file[i].isDirectory()) {
					// 准备复制的源文件夹
					String fromDir = file[i].getAbsolutePath();
					// 准备复制的目标文件夹
					String toDir = tf.getAbsolutePath() + File.separator
							+ file[i].getName();
					copyDirectory(fromDir, toDir);
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}

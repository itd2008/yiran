package com.yiran.weixin.utils;
import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
 /**
  * 图片压缩
  * @author pandaa
  *
  */
public class ImgTools {
	private static final Logger logger = LoggerFactory.getLogger(ImgTools.class);
    /**
     * 按照 宽高 比例压缩
     * 
     * @param img
     * @param width
     * @param height
     * @param out
     * @throws IOException
     */
    public static void thumbnail_w_h(File img, int width, int height,
            OutputStream out) throws IOException {
    	logger.info("要压缩的图片的宽度："+width);
    	logger.info("要压缩的图片的高度："+height);
    	logger.info(">>>>>>>>>>按照 宽高 比例压缩<<<<<<<<<<");
    	logger.info("输入图片文件:"+img);
        BufferedImage bi = ImageIO.read(img);
        logger.info("BufferedImage："+bi);
        double srcWidth = bi.getWidth(); // 源图宽度
        double srcHeight = bi.getHeight(); // 源图高度
        logger.info("源图宽度："+srcWidth);
    	logger.info("源图高度："+srcHeight);
        
        double scale = 1;
 
        if (width > 0) {
            scale = width / srcWidth;
        }
        if (height > 0) {
            scale = height / srcHeight;
        }
        if (width > 0 && height > 0) {
            scale = height / srcHeight < width / srcWidth ? height / srcHeight
                    : width / srcWidth;
        }
 
        thumbnail(img, (int) (srcWidth * scale), (int) (srcHeight * scale), out);
 
    }
 
    /**
     * 按照固定宽高原图压缩
     * 
     * @param img
     * @param width
     * @param height
     * @param out
     * @throws IOException
     */
    public static void thumbnail(File img, int width, int height,
            OutputStream out) throws IOException {
    	logger.info("thumbnail方法：file对象"+img);
        BufferedImage BI = ImageIO.read(img);
        Image image = BI.getScaledInstance(width, height, Image.SCALE_SMOOTH);
 
        BufferedImage tag = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics g = tag.getGraphics();
        g.setColor(Color.RED);
        g.drawImage(image, 0, 0, null); // 绘制处理后的图
        g.dispose();
        ImageIO.write(tag, "JPEG", out);
    }
 
    /**
     * 按照宽高裁剪
     * 
     * @param srcImageFile
     * @param destWidth
     * @param destHeight
     * @param out
     */
    public static void cut_w_h(File srcImageFile, int destWidth,
            int destHeight, OutputStream out) {
        cut_w_h(srcImageFile, 0, 0, destWidth, destHeight, out);
    }
 
    public static void cut_w_h(File srcImageFile, int x, int y, int destWidth,
            int destHeight, OutputStream out) {
        try {
            Image img;
            ImageFilter cropFilter;
            // 读取源图像
            BufferedImage bi = ImageIO.read(srcImageFile);
            int srcWidth = bi.getWidth(); // 源图宽度
            int srcHeight = bi.getHeight(); // 源图高度
 
            if (srcWidth >= destWidth && srcHeight >= destHeight) {
                Image image = bi.getScaledInstance(srcWidth, srcHeight,
                        Image.SCALE_DEFAULT);
 
                cropFilter = new CropImageFilter(x, y, destWidth, destHeight);
                img = Toolkit.getDefaultToolkit().createImage(
                        new FilteredImageSource(image.getSource(), cropFilter));
                BufferedImage tag = new BufferedImage(destWidth, destHeight,
                        BufferedImage.TYPE_INT_RGB);
                Graphics g = tag.getGraphics();
                g.drawImage(img, 0, 0, null); // 绘制截取后的图
                g.dispose();
                // 输出为文件
                ImageIO.write(tag, "JPEG", out);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }// #cut_w_h
 
    public static void main(String[] args) throws IOException {
        File img = new File("D:\\agentSystem\\pic\\gQH67zoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xLzdVeFhDUnpsWmNNY3NESzN4bUJvAAIEtgxsVgMEAAAAAA==.png");
        FileOutputStream fos = new FileOutputStream("D:\\agentSystem\\pic\\gQH67zoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xLzdVeFhDUnpsWmNNY3NESzN4bUJvAAIEtgxsVgMEAAAAAA==1.png");
        // ImgTools.thumbnail(img, 100, 100, fos);
        // ImgTools.cut_w_h(img, 230, 200, fos);
        ImgTools.thumbnail_w_h(img, 100, 100, fos);
    }
 
}

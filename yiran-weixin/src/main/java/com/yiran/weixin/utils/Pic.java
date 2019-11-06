package com.yiran.weixin.utils;

import java.awt.Color;  
import java.awt.Font;  
import java.awt.Graphics2D;  
import java.awt.image.BufferedImage;  
import java.io.File;  
import java.io.IOException;  
import java.net.URL;  
  
import javax.imageio.ImageIO;  
  
/** 
 * 把两张图片合并 
 * @author xupan  
 */  
public class Pic {  
    private Font       font     = new Font("宋体", Font.PLAIN, 12); // 添加字体的属性设置    
  
    private Graphics2D g        = null;  
  
    private int        fontsize = 0;  
  
    private int        x        = 0;  
  
    private int        y        = 0;  
  
    /**  
     * 导入本地图片到缓冲区  
     */  
    public   BufferedImage loadImageLocal(String imgName) {  
        try {  
            return ImageIO.read(new File(imgName));  
        } catch (IOException e) {  
            System.out.println(e.getMessage());  
        }  
        return null;  
    }  
  
    /**  
     * 导入网络图片到缓冲区  
     */  
    public BufferedImage loadImageUrl(String imgName) {  
        try {  
            URL url = new URL(imgName);  
            return ImageIO.read(url);  
        } catch (IOException e) {  
            System.out.println(e.getMessage());  
        }  
        return null;  
    }  
  
    /**  
     * 生成新图片到本地  
     */  
    public void writeImageLocal(String newImage, BufferedImage img) {  
        if (newImage != null && img != null) {  
            try {  
                File outputfile = new File(newImage);  
                ImageIO.write(img, "jpg", outputfile);  
            } catch (IOException e) {  
                System.out.println(e.getMessage());  
            }  
        }  
    }  
  
    /**  
     * 设定文字的字体等  
     */  
    public void setFont(String fontStyle, int fontSize) {  
        this.fontsize = fontSize;  
        this.font = new Font(fontStyle, Font.PLAIN, fontSize);  
    }  
  
    /**  
     * 修改图片,返回修改后的图片缓冲区（只输出一行文本）  
     */  
    public BufferedImage modifyImage(BufferedImage img, Object content, int x, int y) {  
  
        try {  
            int w = img.getWidth();  
            int h = img.getHeight();  
            g = img.createGraphics();  
            g.setBackground(Color.WHITE);  
            g.setColor(Color.orange);//设置字体颜色    
            if (this.font != null)  
                g.setFont(this.font);  
            // 验证输出位置的纵坐标和横坐标    
            if (x >= h || y >= w) {  
                this.x = h - this.fontsize + 2;  
                this.y = w;  
            } else {  
                this.x = x;  
                this.y = y;  
            }  
            if (content != null) {  
                g.drawString(content.toString(), this.x, this.y);  
            }  
            g.dispose();  
        } catch (Exception e) {  
            System.out.println(e.getMessage());  
        }  
  
        return img;  
    }  
  
    /**  
     * 修改图片,返回修改后的图片缓冲区（输出多个文本段） xory：true表示将内容在一行中输出；false表示将内容多行输出  
     */  
    public BufferedImage modifyImage(BufferedImage img, Object[] contentArr, int x, int y,  
                                     boolean xory) {  
        try {  
            int w = img.getWidth();  
            int h = img.getHeight();  
            g = img.createGraphics();  
            g.setBackground(Color.WHITE);  
            g.setColor(Color.RED);  
            if (this.font != null)  
                g.setFont(this.font);  
            // 验证输出位置的纵坐标和横坐标    
            if (x >= h || y >= w) {  
                this.x = h - this.fontsize + 2;  
                this.y = w;  
            } else {  
                this.x = x;  
                this.y = y;  
            }  
            if (contentArr != null) {  
                int arrlen = contentArr.length;  
                if (xory) {  
                    for (int i = 0; i < arrlen; i++) {  
                        g.drawString(contentArr[i].toString(), this.x, this.y);  
                        this.x += contentArr[i].toString().length() * this.fontsize / 2 + 5;// 重新计算文本输出位置    
                    }  
                } else {  
                    for (int i = 0; i < arrlen; i++) {  
                        g.drawString(contentArr[i].toString(), this.x, this.y);  
                        this.y += this.fontsize + 2;// 重新计算文本输出位置    
                    }  
                }  
            }  
            g.dispose();  
        } catch (Exception e) {  
            System.out.println(e.getMessage());  
        }  
  
        return img;  
    }  
  
    /**  
     * 修改图片,返回修改后的图片缓冲区（只输出一行文本）  
     *   
     * 时间:2007-10-8  
     *   
     * @param img  
     * @return  
     */  
    public BufferedImage modifyImageYe(BufferedImage img) {  
  
        try {  
            int w = img.getWidth();  
            int h = img.getHeight();  
            g = img.createGraphics();  
            g.setBackground(Color.WHITE);  
            g.setColor(Color.blue);//设置字体颜色    
            if (this.font != null)  
                g.setFont(this.font);  
            g.drawString("www.hi.baidu.com?xia_mingjian", w - 85, h - 5);  
            g.dispose();  
        } catch (Exception e) {  
            System.out.println(e.getMessage());  
        }  
  
        return img;  
    }  
  
    /**
     * 合并图片到指定的位置 将图片2合并到图片1
     * @param b 图片1
     * @param d 图片2
     * @param x 横坐标
     * @param y 纵坐标
     * @return
     */
    public BufferedImage modifyImagetogeter(BufferedImage b, BufferedImage d,int x,int y) {  
  
        try {  
            int w = b.getWidth();  
            int h = b.getHeight();  
  
            g = d.createGraphics();  
            g.drawImage(b, x, y, w, h, null);  
            g.dispose();  
        } catch (Exception e) {  
            System.out.println(e.getMessage());  
        }  
  
        return d;  
    }  
  
    public static void main(String[] args) {  
  
        Pic tt = new Pic();  
  
        BufferedImage d = tt.loadImageLocal("D:\\agentSystem\\pic\\20170928190131.jpg");  
        BufferedImage b = tt.loadImageLocal("D:\\agentSystem\\pic\\erweima.jpg");    
        //往图片上写文件    
        //tt.writeImageLocal("E:\\ploanshare\\2\\22.jpg", tt.modifyImage(d, "000000", 90, 90));  
  
        tt.writeImageLocal("D:\\agentSystem\\pic\\20170928190132.jpg", tt.modifyImagetogeter(b, d,20,180));    
        //将多张图片合在一起    
        System.out.println("success");  
    }  
}  
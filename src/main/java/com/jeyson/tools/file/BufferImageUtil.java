package com.jeyson.tools.file;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @Author: liujishuai
 * @Time: 2018/5/3 19:30
 * @Description:
 */
public class BufferImageUtil implements Serializable {

    public static BufferedImage getBufferImageByByte(byte[] imageByte) {
        if (null == imageByte) {
            return null;
        }
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(imageByte);
            BufferedImage image = ImageIO.read(inputStream);
            return image;
        } catch (Exception e) {
            System.out.println("BufferImageUtil getBufferImageByByte error: " + e.getMessage());
        }
        return null;
    }

    public static byte[] getByteByBufferImage(BufferedImage image) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", os);
            return os.toByteArray();
        } catch (Exception e) {
            System.out.println("BufferImageUtil getByteByBufferImage error: " + e.getMessage());
        }
        return null;

    }

    public static OutputStream getOutStreamByBufferImage(BufferedImage image) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", os);
            return os;
        } catch (Exception e) {
            System.out.println("BufferImageUtil getOutStreamByBufferImage error: " + e.getMessage());
        }
        return null;

    }

    public static BufferedImage getBufferImageByInputStream(InputStream stream) {
        if (null == stream) {
            return null;
        }
        try {
            return ImageIO.read(stream);
        } catch (Exception e) {
            System.out.println("BufferImageUtil getBufferImageByInputStream error: " + e.getMessage());
        }
        return null;
    }
}

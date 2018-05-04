package com.jeyson.tools.file;

import com.alibaba.simpleimage.ImageFormat;
import com.alibaba.simpleimage.ImageRender;
import com.alibaba.simpleimage.render.*;
import com.alibaba.simpleimage.util.ImageUtils;

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

    public static ByteArrayOutputStream resizeImage(InputStream inputStream, int width, int height, float quality) {
        ByteArrayOutputStream outputStream = null;
        ImageRender wr = null;
        try {
            ImageFormat imageFormat = null;
            try {
                imageFormat = ImageUtils.identifyFormat(inputStream);
            } catch (Exception e) {
            }
            if (imageFormat == null) {
                imageFormat = ImageFormat.JPEG;
            }
            //压缩
            ScaleParameter scaleParameter = new ScaleParameter(width, height);
            ImageRender sr = new ScaleRender(inputStream, scaleParameter);
            WriteParameter writeParameter = new WriteParameter();
            writeParameter.setDefaultQuality(quality);
            outputStream = new ByteArrayOutputStream();
            wr = new WriteRender(sr, outputStream,imageFormat,writeParameter);
            wr.render();
        } catch (Exception e) {
            outputStream = null;
            System.out.println("BufferImageUtil resizeImage error " + e.getMessage());
        }
        return outputStream;
    }

}

package com.jeyson.tools.file;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: liujishuai
 * @Time: 2020/9/11 19:18
 * @Description: 群头像生成规则
 */
public class GroupAvatarUtil {

    /**
     * 头像宽度
     */
    private static final int width = 400;
    /**
     * 头像高度
     */
    private static final int height = 400;
    /**
     * 小头像间隙
     */
    private static final int gap = 8;


    public static void main(String[] args) throws FileNotFoundException {
        byte[] byte1 = FileUtils.getByteByFile(new File("/Users/jeyson/Downloads/709/1.jpg"));
        byte[] byte2 = FileUtils.getByteByFile(new File("/Users/jeyson/Downloads/709/2.jpeg"));
        byte[] byte3 = FileUtils.getByteByFile(new File("/Users/jeyson/Downloads/709/3.jpeg"));
        List<byte[]> list = Arrays.asList(byte1, byte2, byte3);
        createGroupAvatar("222", list);
    }

    public static byte[] createGroupAvatar(String groupName, List<byte[]> avatarList) {

        try {
            // 创建BufferedImage对象
            // 选择TYPE_INT_ARGB目的在于可创建透明背景的图，否则圆角外的地方会变成黑色
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

            // 获取Graphics2D
            Graphics2D g2d = image.createGraphics();
            // 绘制一个圆角的灰色背景
            g2d.setComposite(AlphaComposite.Src);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(Colors.GROUP_AVATAR_BACKGROUND);
            g2d.fill(new RoundRectangle2D.Float(0, 0, width, height, 35, 35));
            g2d.setComposite(AlphaComposite.SrcAtop);
            Rectangle[] rectangles = getSubAvatarPoints(avatarList.size());
            int max = avatarList.size() > 9 ? 9 : avatarList.size();
            for (int i = 0; i < max; i++) {
                Image avatar = BufferImageUtil.getBufferImageByByte(avatarList.get(i));
                g2d.drawImage(avatar, rectangles[i].x, rectangles[i].y, rectangles[i].width, rectangles[i].height, null);
            }
            g2d.dispose();
            // 生成写入到磁盘
            File file = new File("/Users/jeyson/Downloads/709/" + groupName + ".png");
            ImageIO.write(image, "png", file);
            return BufferImageUtil.getByteByBufferImage(image);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    private static Rectangle[] getSubAvatarPoints(int num) {
        int parentWidth = width;
        Rectangle[] rectangles = new Rectangle[num];
        int x;
        int y;
        if (num == 1) {
            int childWidth = parentWidth / 2;
            x = (parentWidth - childWidth) / 2;
            rectangles[0] = new Rectangle(x, x, childWidth, childWidth);
        } else if (num == 2) {
            int childWidth = (parentWidth - gap * 3) / 2;

            // 第一个
            y = (parentWidth - childWidth) / 2;
            Rectangle r1 = new Rectangle(gap, y, childWidth, childWidth);

            // 第二个
            x = gap * 2 + childWidth;
            Rectangle r2 = new Rectangle(x, y, childWidth, childWidth);

            rectangles[0] = r1;
            rectangles[1] = r2;
        } else if (num == 3) {
            int childWidth = (parentWidth - gap * 3) / 2;


            // 第一个
            x = (parentWidth - childWidth) / 2;
            y = gap;
            Rectangle r1 = new Rectangle(x, y, childWidth, childWidth);

            // 第二个
            x = gap;
            y = childWidth + gap * 2;
            Rectangle r2 = new Rectangle(x, y, childWidth, childWidth);

            // 第三个
            x = childWidth + gap * 2;
            Rectangle r3 = new Rectangle(x, y, childWidth, childWidth);


            rectangles[0] = r1;
            rectangles[1] = r2;
            rectangles[2] = r3;
        } else if (num == 4) {
            int childWidth = (parentWidth - gap * 3) / 2;


            // 第一个
            Rectangle r1 = new Rectangle(gap, gap, childWidth, childWidth);

            // 第二个
            x = childWidth + gap * 2;
            Rectangle r2 = new Rectangle(x, gap, childWidth, childWidth);

            // 第三个
            x = gap;
            y = childWidth + gap * 2;
            Rectangle r3 = new Rectangle(x, y, childWidth, childWidth);

            // 第四个
            x = childWidth + gap * 2;
            Rectangle r4 = new Rectangle(x, y, childWidth, childWidth);


            rectangles[0] = r1;
            rectangles[1] = r2;
            rectangles[2] = r3;
            rectangles[3] = r4;
        } else if (num == 5) {
            int childWidth = (parentWidth - gap * 4) / 3;

            // 第一个
            x = (parentWidth - childWidth * 2 - gap) / 2;
            Rectangle r1 = new Rectangle(x, x, childWidth, childWidth);

            // 第二个
            y = x;
            x = x + gap + childWidth;
            Rectangle r2 = new Rectangle(x, y, childWidth, childWidth);

            // 第三个
            y = r1.y + gap + childWidth;
            Rectangle r3 = new Rectangle(gap, y, childWidth, childWidth);

            // 第四个
            x = gap * 2 + childWidth;
            Rectangle r4 = new Rectangle(x, y, childWidth, childWidth);

            // 第五个
            x = gap * 3 + childWidth * 2;
            Rectangle r5 = new Rectangle(x, y, childWidth, childWidth);

            rectangles[0] = r1;
            rectangles[1] = r2;
            rectangles[2] = r3;
            rectangles[3] = r4;
            rectangles[4] = r5;
        } else if (num == 6) {
            int childWidth = (parentWidth - gap * 4) / 3;

            // 第一个
            y = (parentWidth - childWidth * 2 - gap) / 2;
            Rectangle r1 = new Rectangle(gap, y, childWidth, childWidth);

            // 第二个
            x = gap * 2 + childWidth;
            Rectangle r2 = new Rectangle(x, y, childWidth, childWidth);

            // 第三个
            x = gap * 3 + childWidth * 2;
            Rectangle r3 = new Rectangle(x, y, childWidth, childWidth);


            // 第四个
            y = r1.y + gap + childWidth;
            Rectangle r4 = new Rectangle(gap, y, childWidth, childWidth);

            // 第五个
            x = gap * 2 + childWidth;
            Rectangle r5 = new Rectangle(x, y, childWidth, childWidth);

            // 第六个
            x = gap * 3 + childWidth * 2;
            Rectangle r6 = new Rectangle(x, y, childWidth, childWidth);

            rectangles[0] = r1;
            rectangles[1] = r2;
            rectangles[2] = r3;
            rectangles[3] = r4;
            rectangles[4] = r5;
            rectangles[5] = r6;
        } else if (num == 7) {
            int childWidth = (parentWidth - gap * 4) / 3;

            // 第一个
            x = (parentWidth - childWidth) / 2;
            Rectangle r1 = new Rectangle(x, gap, childWidth, childWidth);

            // 第二个
            y = gap * 2 + childWidth;
            Rectangle r2 = new Rectangle(gap, y, childWidth, childWidth);

            // 第三个
            x = gap * 2 + childWidth;
            Rectangle r3 = new Rectangle(x, y, childWidth, childWidth);

            // 第四个
            x = gap * 3 + childWidth * 2;
            Rectangle r4 = new Rectangle(x, y, childWidth, childWidth);

            // 第五个
            y = r2.y + childWidth + gap;
            Rectangle r5 = new Rectangle(gap, y, childWidth, childWidth);

            // 第六个
            x = gap * 2 + childWidth;
            Rectangle r6 = new Rectangle(x, y, childWidth, childWidth);

            // 第七个
            x = gap * 3 + childWidth * 2;
            Rectangle r7 = new Rectangle(x, y, childWidth, childWidth);
            rectangles[0] = r1;
            rectangles[1] = r2;
            rectangles[2] = r3;
            rectangles[3] = r4;
            rectangles[4] = r5;
            rectangles[5] = r6;
            rectangles[6] = r7;
        } else if (num == 8) {
            int childWidth = (parentWidth - gap * 4) / 3;
            // 第一个
            x = (parentWidth - childWidth * 2 - gap) / 2;
            Rectangle r1 = new Rectangle(x, gap, childWidth, childWidth);
            // 第二个
            x = x + gap + childWidth;
            Rectangle r2 = new Rectangle(x, gap, childWidth, childWidth);
            // 第三个
            y = gap * 2 + childWidth;
            Rectangle r3 = new Rectangle(gap, y, childWidth, childWidth);
            // 第四个
            x = gap * 2 + childWidth;
            Rectangle r4 = new Rectangle(x, y, childWidth, childWidth);
            // 第五个
            x = gap * 3 + childWidth * 2;
            Rectangle r5 = new Rectangle(x, y, childWidth, childWidth);
            // 第六个
            y = r3.y + childWidth + gap;
            Rectangle r6 = new Rectangle(gap, y, childWidth, childWidth);
            // 第七个
            x = gap * 2 + childWidth;
            Rectangle r7 = new Rectangle(x, y, childWidth, childWidth);
            // 第八个
            x = gap * 3 + childWidth * 2;
            Rectangle r8 = new Rectangle(x, y, childWidth, childWidth);
            rectangles[0] = r1;
            rectangles[1] = r2;
            rectangles[2] = r3;
            rectangles[3] = r4;
            rectangles[4] = r5;
            rectangles[5] = r6;
            rectangles[6] = r7;
            rectangles[7] = r8;
        } else if (num >= 9) {
            int childWidth = (parentWidth - gap * 4) / 3;
            int index = 0;
            for (int i = 1; i <= 3; i++) {
                y = gap * i + (i - 1) * childWidth;
                for (int j = 1; j <= 3; j++) {
                    x = gap * j + (j - 1) * childWidth;
                    Rectangle r = new Rectangle(x, y, childWidth, childWidth);
                    rectangles[index++] = r;
                }
            }
        }

        return rectangles;
    }

}

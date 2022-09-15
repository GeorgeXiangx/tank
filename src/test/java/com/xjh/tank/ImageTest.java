package com.xjh.tank;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;

/**
 * @Author: XJH
 * @Date: 2022/9/13 9:09 下午
 * @Email: xiangjunhong@newhope.cn
 */
public class ImageTest {

    @Test
    public void test() {

        final BufferedImage bufferedImage;
        try {
            bufferedImage = ImageIO.read(new File("/Users/admin/Downloads/乳业对接.jpg"));
            assertNotNull(bufferedImage);

            final BufferedImage bufferedImage1 = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("pic/乳业对接.jpg"));
            assertNotNull(bufferedImage1);

            final BufferedImage bufferedImage2 = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("pic/square0.jpg"));
            assertNotNull(bufferedImage2);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

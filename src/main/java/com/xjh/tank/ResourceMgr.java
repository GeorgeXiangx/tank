package com.xjh.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Author: XJH
 * @Date: 2022/9/14 10:31 上午
 * @Email: xiangjunhong@newhope.cn
 */
public class ResourceMgr {

    public static BufferedImage badTankL, badTankU, badTankR, badTankD;
    public static BufferedImage goodTankL, goodTankU, goodTankR, goodTankD;
    public static BufferedImage bulletL, bulletU, bulletR, bulletD;
    public static BufferedImage[] explodes = new BufferedImage[16];

    static {
        try {
//            tankL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("pic/tankL.gif"));
//            tankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("pic/tankU.gif"));
//            tankR = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("pic/tankR.gif"));
//            tankD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("pic/tankD.gif"));

            badTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("pic/BadTank1.png"));
            badTankL = ImageUtil.rotateImage(badTankU, -90);
            badTankR = ImageUtil.rotateImage(badTankU, 90);
            badTankD = ImageUtil.rotateImage(badTankU, 180);

            goodTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("pic/GoodTank1.png"));
            goodTankL = ImageUtil.rotateImage(goodTankU, -90);
            goodTankR = ImageUtil.rotateImage(goodTankU, 90);
            goodTankD = ImageUtil.rotateImage(goodTankU, 180);

//            bulletL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("pic/bulletL.gif"));
//            bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("pic/bulletU.gif"));
//            bulletR = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("pic/bulletR.gif"));
//            bulletD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("pic/bulletD.gif"));

            bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("pic/bulletU.png"));
            bulletL = ImageUtil.rotateImage(bulletU, -90);
            bulletR = ImageUtil.rotateImage(bulletU, 90);
            bulletD = ImageUtil.rotateImage(bulletU, 180);

            for (int i = 0; i < explodes.length; i++) {
                explodes[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("pic/e" + (i + 1) + ".gif"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package handler;

import constant.ResolutionList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageHandler extends AbstractHandler {
    protected static Logger logger = LoggerFactory.getLogger(ImageHandler.class);

    public ImageHandler() {

    }

    public BufferedImage getSubImage(BufferedImage image) {
        if (resolutionCode(screenSize).equals(ResolutionList.FULL_HD)) {
            System.out.println("Your current screen resolution is FULL_HD" + '\n' +
                    "I will repeat it in 5 seconds...");
        } else {
            System.out.println("Your current screen resolution is not FULL_HD :(" + '\n' +
                    "I will repeat it in 5 seconds...");
        }
        return image;
    }

    public Integer resolutionCode(Dimension dimension) {
        return dimension.height * dimension.width;
    }
}

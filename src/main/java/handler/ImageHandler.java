package handler;

import constant.ColorValueList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.Raster;
import java.util.ArrayList;

public class ImageHandler extends AbstractHandler {
    protected static Logger logger = LoggerFactory.getLogger(ImageHandler.class);
    private static final ImageHandler instance = new ImageHandler();

    public static ImageHandler getInstance() {
        return instance;
    }

    public ImageHandler() {

    }

    public boolean checkSubImage(BufferedImage image) {
        return isColorMatch(getImageColor(image));
    }

    public ArrayList<Integer> getImageColor(BufferedImage image) {
        Raster raster = image.getRaster();
        ColorModel model = image.getColorModel();
        int x = screenSize.width / 2;
        int y = screenSize.height / 2;
        Object data = raster.getDataElements(x, y, null);
        int argb = model.getRGB(data);
        Color color = new Color(argb, false);
        ArrayList<Integer> list = new ArrayList<>();
        list.add(color.getRed());
        list.add(color.getGreen());
        list.add(color.getBlue());
        return list;
    }

    public boolean isColorMatch(ArrayList<Integer> list) {
        return checkRedValue(list) && checkGreenValue(list) && checkBlueValue(list);
    }

    public boolean checkRedValue(ArrayList<Integer> list) {
        return list.get(0) > ColorValueList.RED_MIN_VALUE && list.get(0) < ColorValueList.RED_MAX_VALUE;
    }

    public boolean checkGreenValue(ArrayList<Integer> list) {
        return list.get(1) > ColorValueList.GREEN_MIN_VALUE && list.get(1) < ColorValueList.GREEN_MAX_VALUE;
    }

    public boolean checkBlueValue(ArrayList<Integer> list) {
        return list.get(2) > ColorValueList.BLUE_MIN_VALUE && list.get(2) < ColorValueList.BLUE_MAX_VALUE;
    }
}

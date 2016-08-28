/**
 * Copyright (c) 2016 ajujoy
 * Created by ajujoy on 8/29/16.
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons
 * to whom the Software is furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
 * THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

import java.awt.*;
import java.awt.image.BufferedImage;

public class Conversion{
    public String pixelVal = new String("$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\"^`'. ");

    public String convertImage(BufferedImage image,int maxHeight){
        int index;
        Image imTmp;
        if (maxHeight==0){
            imTmp = image;
        } else {
            if (image.getHeight() > maxHeight) {
                imTmp = image.getScaledInstance(((int) (image.getWidth() / (image.getHeight() / maxHeight))), maxHeight, Image.SCALE_SMOOTH);
            } else {
                imTmp = image;
            }
        }
        BufferedImage im = toBuffered(imTmp);
        StringBuilder sb = new StringBuilder(((im.getWidth()+1)*im.getHeight()));

        for(int i=0;i<im.getHeight();i++){
            if(sb.length()!=0){
                sb.append("\n");
            }
            for(int j=0;j<im.getWidth();j++){

                Color pixelCol = new Color(im.getRGB(j,i));
                double grayscale = (double) pixelCol.getRed()*0.2989 + (double) pixelCol.getBlue()*0.5870 + (double) pixelCol.getGreen()*0.1140;
                if((int) Math.round(grayscale/3.64285714)<69) {
                    index = (int) (Math.round(grayscale / 3.64285714));
                }
                else {
                    index = 69;
                }
                final char s = pixelVal.charAt(index);
                sb.append(s);
            }
        }
        return sb.toString();
    }
    public BufferedImage toBuffered (Image image){
        if (image instanceof BufferedImage){
            return (BufferedImage) image;
        }

            BufferedImage bimage = new BufferedImage(image.getWidth(null),image.getHeight(null),BufferedImage.TYPE_INT_ARGB);

            Graphics2D graphics2D = bimage.createGraphics();
            graphics2D.drawImage(image,0,0,null);
            graphics2D.dispose();

        return bimage;



    }
}
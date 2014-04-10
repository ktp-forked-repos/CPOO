/*    */ package cpoo.noise.model.generators;
/*    */ 
/*    */ import cpoo.noise.model.RandomGaussian;
/*    */ import java.awt.Color;
/*    */ import java.awt.image.BufferedImage;
/*    */ 
/*    */ public class GaussianNoiseGenerator
/*    */ {
/* 15 */   private static RandomGaussian randomGaussian = new RandomGaussian();
/*    */ 
/*    */   public static BufferedImage generatePicture(BufferedImage image, double mean, double variance)
/*    */   {
/* 26 */     int w = image.getWidth();
/* 27 */     int h = image.getHeight();
/*    */ 
/* 29 */     BufferedImage result = new BufferedImage(w, h, 
/* 30 */       1);
/*    */ 
/* 32 */     for (int x = 0; x < w; x++) {
/* 33 */       for (int y = 0; y < h; y++) {
/* 34 */         int rgb = image.getRGB(x, y);
/*    */ 
/* 37 */         float red = new Color(rgb).getRed();
/* 38 */         float green = new Color(rgb).getGreen();
/* 39 */         float blue = new Color(rgb).getBlue();
/*    */ 
/* 42 */         int noise = (int)randomGaussian.getGaussian(mean, variance);
/*    */ 
/* 44 */         red += noise;
/* 45 */         green += noise;
/* 46 */         blue += noise;
/*    */ 
/* 49 */         if (red > 255.0F)
/* 50 */           red = 255.0F;
/* 51 */         else if (red < 0.0F) {
/* 52 */           red = 0.0F;
/*    */         }
/* 54 */         if (green > 255.0F)
/* 55 */           green = 255.0F;
/* 56 */         else if (green < 0.0F) {
/* 57 */           green = 0.0F;
/*    */         }
/* 59 */         if (blue > 255.0F)
/* 60 */           blue = 255.0F;
/* 61 */         else if (blue < 0.0F) {
/* 62 */           blue = 0.0F;
/*    */         }
/*    */ 
/* 66 */         Color resultColor = new Color(red / 255.0F, green / 255.0F, 
/* 67 */           blue / 255.0F, 1.0F);
/*    */ 
/* 69 */         result.setRGB(x, y, resultColor.getRGB());
/*    */       }
/*    */     }
/* 72 */     return result;
/*    */   }
/*    */ }

/* Location:           E:\Studia\sem2\CPOO\proekt\NoisePicture.jar
 * Qualified Name:     cpoo.noise.model.generators.GaussianNoiseGenerator
 * JD-Core Version:    0.6.2
 */
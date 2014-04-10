/*    */ package cpoo.noise.model.generators;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.util.Random;
/*    */ 
/*    */ public class ImpulseNoiseGenerator
/*    */ {
/* 14 */   private static Random rand = new Random();
/*    */ 
/*    */   public static BufferedImage generatePicture(BufferedImage image, double p1, double val1, double p2, double val2)
/*    */   {
/* 28 */     int w = image.getWidth();
/* 29 */     int h = image.getHeight();
/*    */ 
/* 31 */     BufferedImage result = new BufferedImage(w, h, 
/* 32 */       1);
/*    */ 
/* 34 */     for (int x = 0; x < w; x++) {
/* 35 */       for (int y = 0; y < h; y++) {
/* 36 */         int rgb = image.getRGB(x, y);
/*    */ 
/* 39 */         float red = new Color(rgb).getRed();
/* 40 */         float green = new Color(rgb).getGreen();
/* 41 */         float blue = new Color(rgb).getBlue();
/*    */ 
/* 43 */         float newRand = rand.nextFloat();
/* 44 */         if (newRand < p1)
/*    */         {
/* 46 */           red = (float)(red + Math.pow(-1.0D, rand.nextInt(6)) * val1);
/* 47 */           green = (float)(green + Math.pow(-1.0D, rand.nextInt(6)) * val1);
/* 48 */           blue = (float)(blue + Math.pow(-1.0D, rand.nextInt(6)) * val1);
/*    */         }
/* 50 */         if ((newRand >= p1) && (newRand < p2 + p1))
/*    */         {
/* 52 */           red = (float)(red + Math.pow(-1.0D, rand.nextInt(6)) * val2);
/* 53 */           green = (float)(green + Math.pow(-1.0D, rand.nextInt(6)) * val2);
/* 54 */           blue = (float)(blue + Math.pow(-1.0D, rand.nextInt(6)) * val2);
/*    */         }
/*    */ 
/* 59 */         if (red > 255.0F)
/* 60 */           red = 255.0F;
/* 61 */         else if (red < 0.0F) {
/* 62 */           red = 0.0F;
/*    */         }
/* 64 */         if (green > 255.0F)
/* 65 */           green = 255.0F;
/* 66 */         else if (green < 0.0F) {
/* 67 */           green = 0.0F;
/*    */         }
/* 69 */         if (blue > 255.0F)
/* 70 */           blue = 255.0F;
/* 71 */         else if (blue < 0.0F) {
/* 72 */           blue = 0.0F;
/*    */         }
/*    */ 
/* 76 */         Color resultColor = new Color(red / 255.0F, green / 255.0F, 
/* 77 */           blue / 255.0F, 1.0F);
/*    */ 
/* 79 */         result.setRGB(x, y, resultColor.getRGB());
/*    */       }
/*    */     }
/* 82 */     return result;
/*    */   }
/*    */ }

/* Location:           E:\Studia\sem2\CPOO\proekt\NoisePicture.jar
 * Qualified Name:     cpoo.noise.model.generators.ImpulseNoiseGenerator
 * JD-Core Version:    0.6.2
 */
/*     */ package cpoo.noise.model;
/*     */ 
/*     */ import cpoo.noise.controller.Controller;
/*     */ import cpoo.noise.model.generators.GaussianNoiseGenerator;
/*     */ import cpoo.noise.model.generators.ImpulseNoiseGenerator;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.util.Locale;
/*     */ 
/*     */ public class Model
/*     */ {
/*     */   public void generateGaussianNoise(BufferedImage image, String name, double mean, double variance, int numberOfPictures, double meanStep, double varianceStep)
/*     */   {
/*  46 */     for (int i = 0; i < numberOfPictures; i++) {
/*  47 */       generateGaussianNoise(image, name, mean + meanStep * i, variance + 
/*  48 */         varianceStep * i);
/*  49 */       Controller.setProgress(100 * (i + 1) / numberOfPictures);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void generateGaussianNoise(BufferedImage image, String name, double mean, double variance)
/*     */   {
/*  66 */     BufferedImage result = GaussianNoiseGenerator.generatePicture(image, 
/*  67 */       mean, variance);
/*     */ 
/*  69 */     name = name.substring(0, name.lastIndexOf('.'));
/*  70 */     name = name + "_gauss_mean_" + String.format(Locale.ENGLISH, "%.2f%n", new Object[] { Double.valueOf(mean) }) + 
/*  71 */       "_var_" + String.format(Locale.ENGLISH, "%.2f%n", new Object[] { Double.valueOf(variance) }) + 
/*  72 */       ".png";
/*  73 */     name.replaceAll(" ", "");
/*  74 */     Controller.addImageTab(result, name);
/*     */   }
/*     */ 
/*     */   public void generateImpulseNoise(BufferedImage image, String name, double p1, int v1, double p2, int v2, int numberOfPictures, double p1Step, int v1Step, double p2Step, int v2Step)
/*     */   {
/* 115 */     for (int i = 0; i < numberOfPictures; i++) {
/* 116 */       generateImpulseNoise(image, name, p1 + p1Step * i, v1 + v1Step * i, 
/* 117 */         p2 + p1Step * i, v2 + v2Step * i);
/* 118 */       Controller.setProgress(100 * (i + 1) / numberOfPictures);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void generateImpulseNoise(BufferedImage image, String name, double p1, int v1, double p2, int v2)
/*     */   {
/* 146 */     BufferedImage result = ImpulseNoiseGenerator.generatePicture(image, p1, 
/* 147 */       v1, p2, v2);
/*     */ 
/* 149 */     name = name.substring(0, name.lastIndexOf('.'));
/* 150 */     Controller.addImageTab(
/* 151 */       result, 
/* 152 */       name + "_impulse_p1_" + 
/* 153 */       String.format(Locale.ENGLISH, "%.2f%n", new Object[] { Double.valueOf(p1) }) + "_v1_" + 
/* 154 */       v1 + "_p2_" + 
/* 155 */       String.format(Locale.ENGLISH, "%.2f%n", new Object[] { Double.valueOf(p2) }) + "_v2_" + 
/* 156 */       v2 + ".png");
/*     */   }
/*     */ }


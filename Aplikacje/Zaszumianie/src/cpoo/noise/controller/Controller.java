/*     */ package cpoo.noise.controller;
/*     */ 
/*     */ import cpoo.noise.model.Model;
/*     */ import cpoo.noise.view.ContentPane;
/*     */ import cpoo.noise.view.MainWindow;
/*     */ import cpoo.noise.view.View;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.IOException;
/*     */ 
/*     */ public class Controller
/*     */ {
/*  16 */   private static Controller instance = null;
/*     */   private static Model model;
/*     */   private static View view;
/*     */ 
/*     */   public static Controller getInstance()
/*     */   {
/*  22 */     if (instance == null) {
/*  23 */       instance = new Controller();
/*     */     }
/*  25 */     return instance;
/*     */   }
/*     */ 
/*     */   public Controller() {
/*  29 */     model = new Model();
/*  30 */     view = new View();
/*     */   }
/*     */ 
/*     */   public static void generateGaussianNoise(BufferedImage image, String name, double mean, double variance, int numberOfPictures, double meanStep, double varianceStep)
/*     */   {
/*  47 */     model.generateGaussianNoise(image, name, mean, variance, 
/*  48 */       numberOfPictures, meanStep, varianceStep);
/*     */   }
/*     */ 
/*     */   public static void generateGaussianNoise(BufferedImage image, String name, double mean, double variance)
/*     */   {
/*  61 */     model.generateGaussianNoise(image, name, mean, variance);
/*     */   }
/*     */ 
/*     */   public static void generateImpulseNoise(BufferedImage image, String name, double p1, int v1, double p2, int v2, int numberOfPictures, double p1Step, int v1Step, double p2Step, int v2Step)
/*     */   {
/*  82 */     model.generateImpulseNoise(image, name, p1, v1, p2, v2, 
/*  83 */       numberOfPictures, p1Step, v1Step, p2Step, v2Step);
/*     */   }
/*     */ 
/*     */   public static void generateImpulseNoise(BufferedImage image, String name, double p1, int v1, double p2, int v2)
/*     */   {
/*  98 */     model.generateImpulseNoise(image, name, p1, v1, p2, v2);
/*     */   }
/*     */ 
/*     */   public static void addImageTab(BufferedImage result, String title)
/*     */   {
/*     */     try
/*     */     {
/* 109 */       view.getMainWindow().getContent().addExistingItem(result, title);
/*     */     }
/*     */     catch (IOException e) {
/* 112 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void setProgress(int progress)
/*     */   {
/* 122 */     view.setProgres(progress);
/*     */   }
/*     */ }


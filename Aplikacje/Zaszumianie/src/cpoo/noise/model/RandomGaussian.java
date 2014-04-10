/*    */ package cpoo.noise.model;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ public final class RandomGaussian
/*    */ {
/* 14 */   private Random fRandom = new Random();
/*    */ 
/*    */   public double getGaussian(double aMean, double aVariance) {
/* 17 */     return aMean + this.fRandom.nextGaussian() * aVariance;
/*    */   }
/*    */ }

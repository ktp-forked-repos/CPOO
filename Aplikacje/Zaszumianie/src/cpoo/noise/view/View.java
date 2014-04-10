/*    */ package cpoo.noise.view;
/*    */ 
/*    */ public class View
/*    */ {
/*    */   private MainWindow mainWindow;
/*    */ 
/*    */   public View()
/*    */   {
/* 14 */     setMainWindow(new MainWindow());
/*    */   }
/*    */ 
/*    */   public MainWindow getMainWindow() {
/* 18 */     return this.mainWindow;
/*    */   }
/*    */ 
/*    */   public void setMainWindow(MainWindow mainWindow) {
/* 22 */     this.mainWindow = mainWindow;
/*    */   }
/*    */ 
/*    */   public void setProgres(int progress) {
/* 26 */     //this.mainWindow.getContent().setProgress(progress);
/*    */   }
/*    */ }

/* Location:           E:\Studia\sem2\CPOO\proekt\NoisePicture.jar
 * Qualified Name:     cpoo.noise.view.View
 * JD-Core Version:    0.6.2
 */
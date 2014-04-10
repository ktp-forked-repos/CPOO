/*    */ package cpoo.noise.view;
/*    */ 
/*    */ import java.awt.Dimension;
/*    */ import java.awt.Toolkit;
/*    */ import javax.swing.JFrame;
/*    */ 
/*    */ public class MainWindow extends JFrame
/*    */ {
/*    */   private static final int WINDOWS_BAR_HEIGHT = 35;
/*    */   private static final String title = "NoiseMaster";
/*    */   private ContentPane content;
/*    */   private MainMenu menu;
/*    */ 
/*    */   MainWindow()
/*    */   {
/* 22 */     super("NoiseMaster");
/* 23 */     adjustSize();
/*    */ 
/* 25 */     this.content = new ContentPane(this);
/* 26 */     this.menu = new MainMenu(this);
/*    */ 
/* 28 */     setContentPane(this.content);
/* 29 */     setJMenuBar(this.menu);
/* 30 */     setVisible(true);
/*    */   }
/*    */ 
/*    */   private void adjustSize() {
/* 34 */     super.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, 
/* 35 */       Toolkit.getDefaultToolkit().getScreenSize().height - 
/* 36 */       35);
/* 37 */     setDefaultCloseOperation(2);
/*    */   }
/*    */ 
/*    */   public ContentPane getContent() {
/* 41 */     return this.content;
/*    */   }
/*    */ }

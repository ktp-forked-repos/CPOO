/*    */ package cpoo.noise.view;
/*    */ 
/*    */ import java.awt.BorderLayout;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.Toolkit;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JOptionPane;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.JProgressBar;
/*    */ 
/*    */ public class StatusBar extends JPanel
/*    */ {
/* 20 */ //private JLabel message = new JLabel(" PostД™p  ");
/* 21 */   private JProgressBar progress = new JProgressBar(0, 100);
/*    */   private MainWindow mainWindow;
/*    */ 
/*    */   StatusBar(MainWindow mainWindow)
/*    */   {
/* 25 */     super(new BorderLayout());
/* 26 */     this.mainWindow = mainWindow;
/* 27 */     setSize(Toolkit.getDefaultToolkit().getScreenSize().width, 30);
/* 28 */  //   add(this.message, "West");
/* 29 */     this.progress.setMaximumSize(new Dimension(1, 1));
/* 30 */     this.progress.setValue(0);
/* 31 */     this.progress.setStringPainted(true);
/* 32 */     add(this.progress, "Center");
/*    */   }
/*    */ 
/*    */   public void setMessage(String m)
/*    */   {
/* 37 */ //    this.message.setText(m);
/* 38 */     revalidate();
/*    */   }
/*    */ 
/*    */   public void updateProgress(int newProgress)
/*    */   {
/* 43 */     this.progress.setValue(newProgress);
/* 44 */     if (newProgress == 100)
/*    */     {
/* 46 */       this.progress.setValue(0);
/* 47 */       JOptionPane.showMessageDialog(this.mainWindow, "Generacja obrazów została zakończona");
/*    */     }
/* 49 */     this.progress.update(this.progress.getGraphics());
/* 50 */     revalidate();
/*    */   }
/*    */ }


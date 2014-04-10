/*     */ package cpoo.noise.view;
/*     */ 
/*     */ import cpoo.noise.controller.Controller;
/*     */ import cpoo.noise.view.dialogs.GaussDialog;
/*     */ import cpoo.noise.view.dialogs.ImpulseDialog;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import javax.imageio.ImageIO;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JFileChooser;
/*     */ import javax.swing.JMenu;
/*     */ import javax.swing.JMenuBar;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JTextField;
/*     */ 
/*     */ public class MainMenu extends JMenuBar
/*     */ {
/*     */   static MainWindow mainWindow;
/*  32 */   //private JMenu file = new JMenu("Plik");
/*  33 */   //private JMenuItem saveSequences = new JMenuItem("Zapisz");
/*  34 */   //private JMenuItem loadSequences = new JMenuItem("Wczytaj");
/*  35 */   //private JMenuItem clearSequences = new JMenuItem("WyczyЕ›Д‡");
/*  36 */   //private JMenuItem exit = new JMenuItem("ZakoЕ„cz");
/*     */ 
/*  38 */   //private JMenu noise = new JMenu("Zaszumienie");
/*  39 */   //private JMenuItem runGauss = new JMenuItem("Szum Gaussa");
/*  40 */   //private JMenuItem runImpulse = new JMenuItem("Szum Impulsowy");
/*     */ 
/*  42 */   //private JMenu help = new JMenu("Pomoc");
/*     */ 
/*  45 */   //private JMenuItem about = new JMenuItem("O programie");
/*     */ 
/*     */   MainMenu(MainWindow mW)
/*     */   {
/*  49 */     mainWindow = mW;
/*  50 */     populateMenuBar();
/*  51 */     createListeners();
/*     */   }
/*     */ 
/*     */   private void populateMenuBar() {
/*  55 */     //this.file.add(this.loadSequences);
/*  56 */     //this.file.add(this.saveSequences);
/*  57 */     //this.file.add(this.clearSequences);
/*  58 */     //this.file.add(this.exit);
/*  59 */     //add(this.file);
/*     */ 
/*  61 */     //this.noise.add(this.runGauss);
/*  62 */     //this.noise.add(this.runImpulse);
/*  63 */     //add(this.noise);
/*     */ 
/*  68 */     //this.help.add(this.about);
/*  69 */     //add(this.help);
/*     */   }
/*     */ 
/*     */   private void createListeners()
/*     */   {
				/*
/*  77 */    // this.saveSequences.addActionListener(new ActionListener() {
/*     */    //   public void actionPerformed(ActionEvent e) {
/*  79 */    //     MainMenu.handleSave(e);
/*     */    //   }
/*     */    // });
/*  83 */     //this.loadSequences.addActionListener(new ActionListener() {
/*     */     //  public void actionPerformed(ActionEvent e) {
/*  85 */     //    MainMenu.handleLoad(e);
/*     */     //  }
/*     */     //});
/*  89 */     //this.exit.addActionListener(new ActionListener() {
/*     */      // public void actionPerformed(ActionEvent e) {
/*  91 */      //   MainMenu.handleExit(e);
/*     */     //  }
/*     */     //});
/* 101 */     //this.about.addActionListener(new ActionListener() {
/*     */      // public void actionPerformed(ActionEvent e) {
/* 103 */     //    MainMenu.this.handleAbout(e);
/*     */     //  }
/*     */     //});
/* 106 */     //this.clearSequences.addActionListener(new ActionListener() {
/*     */     //  public void actionPerformed(ActionEvent e) {
/* 108 */     //    MainMenu.handleClear(e);
/*     */     //  }
/*     */     //});
/* 111 */     //this.runGauss.addActionListener(new ActionListener() {
/*     */     //  public void actionPerformed(ActionEvent e) {
/* 113 */     //    MainMenu.handleRunGauss();
/*     */     //  }
/*     */     //});
/* 116 */     //this.runImpulse.addActionListener(new ActionListener() {
/*     */     //  public void actionPerformed(ActionEvent e) {
/* 118 */     //    MainMenu.handleRunImpulse();
/*     */     //  }
/*     */     //});
/*     */   }
/*     */ 
/*     */   protected static void handleRunGauss()
/*     */   {
/* 128 */     GaussDialog dialog = new GaussDialog(mainWindow, true);
/* 129 */     if (dialog.getAnswer()) {
/* 130 */       double mean = Double.valueOf(dialog.getMean().getText()).doubleValue();
/* 131 */       double variance = Double.valueOf(dialog.getVariance().getText()).doubleValue();
/*     */ 
/* 133 */       boolean isSerial = Boolean.valueOf(dialog.getSerialGeneration()
/* 134 */         .isSelected()).booleanValue();
/*     */ 
/* 136 */       BufferedImage input = ((ContentPane)mainWindow.getContentPane())
/* 137 */         .getInput();
/* 138 */       String title = ((ContentPane)mainWindow.getContentPane())
/* 139 */         .getInputTitle();
/*     */ 
/* 141 */       if (isSerial) {
/* 142 */         int numberOfPictures = Integer.valueOf(dialog
/* 143 */           .getNumberOfPictures().getText()).intValue();
/*     */ 
/* 144 */         double meanStep = 
/* 145 */           Double.valueOf(dialog.getMeanStep().getText()).doubleValue();
/* 146 */         double varianceStep = Double.valueOf(dialog.getVarianceStep()
/* 147 */           .getText()).doubleValue();
/*     */ 
/* 149 */         Controller.generateGaussianNoise(input, title, mean, variance, 
/* 150 */           numberOfPictures, meanStep, varianceStep);
/*     */       } else {
/* 152 */         Controller.generateGaussianNoise(input, title, mean, variance);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   protected static void handleRunImpulse()
/*     */   {
/* 162 */     ImpulseDialog dialog = new ImpulseDialog(mainWindow, true);
/* 163 */     if (dialog.getAnswer())
/*     */     {
/* 165 */       double v1 = Double.valueOf(dialog.getVal1().getText()).doubleValue();
/* 166 */       double p1 = Double.valueOf(dialog.getP1().getText()).doubleValue();
/* 167 */       double v2 = Double.valueOf(dialog.getVal2().getText()).doubleValue();
/* 168 */       double p2 = Double.valueOf(dialog.getP2().getText()).doubleValue();
/*     */ 
/* 170 */       boolean isSerial = Boolean.valueOf(dialog.getSerialGeneration()
/* 171 */         .isSelected()).booleanValue();
/*     */ 
/* 173 */       BufferedImage input = ((ContentPane)mainWindow.getContentPane())
/* 174 */         .getInput();
/* 175 */       String title = ((ContentPane)mainWindow.getContentPane())
/* 176 */         .getInputTitle();
/*     */ 
/* 178 */       if (isSerial) {
/* 179 */         int numberOfPictures = Integer.valueOf(dialog
/* 180 */           .getNumberOfPictures().getText()).intValue();
/*     */ 
/* 181 */         double v1Step = Double.valueOf(dialog.getVal1Step().getText()).doubleValue();
/* 182 */         double p1Step = Double.valueOf(dialog.getP1Step().getText()).doubleValue();
/* 183 */         double v2Step = Double.valueOf(dialog.getVal2Step().getText()).doubleValue();
/* 184 */         double p2Step = Double.valueOf(dialog.getP2Step().getText()).doubleValue();
/*     */ 
/* 186 */         Controller.generateImpulseNoise(input, title, p1, (int)v1, p2, 
/* 187 */           (int)v2, numberOfPictures, p1Step, (int)v1Step, 
/* 188 */           p2Step, (int)v2Step);
/*     */       } else {
/* 190 */         Controller.generateImpulseNoise(input, title, p1, (int)v1, p2, 
/* 191 */           (int)v2);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   static void handleClear(ActionEvent e)
/*     */   {
/* 202 */     ((ContentPane)mainWindow.getContentPane()).clearAllItems();
/*     */   }
/*     */ 
/*     */ //  protected void handleAbout(ActionEvent e)
/*     */  // {
/* 207 */ //    JOptionPane.showMessageDialog(
/* 208 */  //     mainWindow, 
/* 209 */  //     "NoisePicture v1.0\nProgram sЕ‚uЕјД…cy do dodawania do zadanych obrazГіw szumu Gaussa \ni/lub szumu impulsowego.\n\nAutor: RafaЕ‚ Radziejewski.", 
/* 210 */   //    "", 1);
/*     */ //  }
/*     */ 
/*     */   protected void handleAutoors(ActionEvent e)
/*     */   {
/*     */   }
/*     */ 
/*     */   protected void handleHelp(ActionEvent e)
/*     */   {
/*     */   }
/*     */ 
/*     */   protected static void handleExit(ActionEvent e)
/*     */   {
/* 230 */     int response = JOptionPane.showConfirmDialog(mainWindow, 
/* 231 */       "Czy napewno chcesz wyjść?", "", 0, 
/* 232 */       3);
/* 233 */     if (response == 0)
/* 234 */       mainWindow.dispose();
/*     */   }
/*     */ 
/*     */   protected static void handleLoad(ActionEvent e)
/*     */   {
/* 244 */     JFileChooser fileChooser = new JFileChooser();
/* 245 */     int returnVal = fileChooser.showOpenDialog(mainWindow);
/* 246 */     if (returnVal == 0) {
/* 247 */       String name = fileChooser.getSelectedFile().getAbsolutePath();
/* 248 */       File file = new File(name);
/*     */       try {
/* 250 */         BufferedImage content = ImageIO.read(file);
/* 251 */         ((ContentPane)mainWindow.getContentPane()).addExistingItem(
/* 252 */           content, fileChooser.getSelectedFile().getName());
/*     */       } catch (IOException e1) {
/* 254 */         JOptionPane.showMessageDialog(mainWindow, 
/* 255 */           "Bląd wejścia/wyjścia.", "", 0);
/* 256 */         return;
/*     */       } catch (Exception e0) {
/* 258 */         JOptionPane.showMessageDialog(mainWindow, 
/* 259 */           "Bląd odczytu pliku.", "", 
/* 260 */           0);
/* 261 */         return;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   protected static void handleSave(ActionEvent e)
/*     */   {
/* 274 */     String title = ((ContentPane)mainWindow.getContentPane())
/* 275 */       .getInputTitle();
/* 276 */     title = title.replaceAll("\\s+", "");
/* 277 */     JFileChooser fileChooser = new JFileChooser();
/* 278 */     fileChooser.setSelectedFile(new File(title));
/* 279 */     int returnVal = fileChooser.showSaveDialog(mainWindow);
/*     */ 
/* 282 */     if (returnVal == 0) {
/* 283 */       String filePath = fileChooser.getSelectedFile().getAbsolutePath();
/* 284 */       String fileName = fileChooser.getSelectedFile().getName();
/* 285 */       File file = new File(filePath);
/* 286 */       FileOutputStream fos = null;
/*     */       try {
/* 288 */         fos = new FileOutputStream(file);
/* 289 */         BufferedImage content = ((ContentPane)mainWindow
/* 290 */           .getContentPane()).getInput();
/*     */ 
/* 292 */         String ext = title.substring(title.lastIndexOf('.') + 1);
/* 293 */         ImageIO.write(content, ext, fos);
/* 294 */         fos.close();
/*     */       } catch (FileNotFoundException e0) {
/* 296 */         JOptionPane.showMessageDialog(mainWindow, 
/* 297 */           "Niepoprawna nazwa pliku.", "", 
/* 298 */           0);
/* 299 */         return;
/*     */       } catch (IOException e1) {
/* 301 */         JOptionPane.showMessageDialog(mainWindow, 
/* 302 */           "Bląd wejścia/wyjścia.", "", 0);
/* 303 */         return;
/*     */       }
/* 305 */       JOptionPane.showMessageDialog(mainWindow, "Plik '" + fileName + 
/* 306 */         "' został poprawnie zapisany.", "", 
/* 307 */         1);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected static void handleFileClose()
/*     */   {
/* 317 */     ((ContentPane)mainWindow.getContentPane()).closeSelectedItem();
/*     */   }
/*     */ }



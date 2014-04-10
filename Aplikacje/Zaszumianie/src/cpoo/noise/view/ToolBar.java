/*     */ package cpoo.noise.view;
/*     */ 
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import javax.imageio.ImageIO;
/*     */ import javax.swing.Box;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JToolBar;
/*     */ 
/*     */ public class ToolBar extends JPanel
/*     */ {
/*  28 */   private JToolBar bar = new JToolBar();
/*     */   private JButton load;
/*     */   private JButton save;
/*     */   private JButton clear;
/*     */   private JButton exit;
/*     */   private JButton runGauss;
/*     */   private JButton runImpulse;
/*     */   private JButton close;
/*     */ 
/*     */   ToolBar()
/*     */   {
/*  40 */     loadIcons();
/*  41 */     addComponents();
/*  42 */     setTooltips();
/*  43 */     addListeners();
/*  44 */     setProperties();
/*  45 */     add(this.bar, "West");
/*     */   }
/*     */ 
/*     */   private void loadIcons() {
/*  50 */       ClassLoader classLoader = Thread.currentThread()
/*  51 */         .getContextClassLoader();
/*     */ 
/*  53 */       //InputStream is = classLoader.getResourceAsStream("load.png");
/*  54 */       //BufferedImage bi = ImageIO.read(is);
/*  55 */       //this.load = new JButton(new ImageIcon(bi));
/*     */ 		this.load = new JButton();
				this.load.setText("Wczytaj");

/*  57 */       //is = classLoader.getResourceAsStream("save.png");
/*  58 */       //bi = ImageIO.read(is);
/*  59 */       //this.save = new JButton(new ImageIcon(bi));
				this.save = new JButton();
				this.save.setText("Zapisz");
/*     */ 
/*  61 */       //is = classLoader.getResourceAsStream("clear.png");
/*  62 */       //bi = ImageIO.read(is);
/*  63 */       //this.clear = new JButton(new ImageIcon(bi));
/*     */ 		this.clear = new JButton();
				this.clear.setText("Wyczyść");
/*  65 */       //is = classLoader.getResourceAsStream("exit.png");
/*  66 */       //bi = ImageIO.read(is);
/*  67 */       //this.exit = new JButton(new ImageIcon(bi));
/*     */ 		this.exit = new JButton();
				this.exit.setText("Zamknij program");

/*  69 */       //is = classLoader.getResourceAsStream("runGauss.png");
/*  70 */       //bi = ImageIO.read(is);
/*  71 */       //this.runGauss = new JButton(new ImageIcon(bi));
/*     */ 		this.runGauss = new JButton();
				this.runGauss.setText("Szum Gaussa");

/*  73 */       //is = classLoader.getResourceAsStream("runImpulse.png");
/*  74 */       //bi = ImageIO.read(is);
/*  75 */       //this.runImpulse = new JButton(new ImageIcon(bi));
/*     */ 		this.runImpulse = new JButton();
				this.runImpulse.setText("Szum Impulsowy");

/*  77 */       //is = classLoader.getResourceAsStream("close.png");
/*  78 */       //bi = ImageIO.read(is);
/*  79 */       //this.close = new JButton(new ImageIcon(bi));
				this.close = new JButton();
				this.close.setText("Zamknij plik");
/*     */   }
/*     */ 
/*     */   private void setProperties() {
/*  87 */     this.bar.setFloatable(false);
/*  88 */     this.bar.setRollover(false);
/*  89 */     Dimension d = this.bar.getPreferredSize();
/*  90 */     d.width = ((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 20.0D));
/*  91 */     d.height = 60;
/*  92 */     this.bar.setPreferredSize(d);
/*  93 */     this.bar.setMaximumSize(d);
/*  94 */     this.bar.setMinimumSize(d);
/*     */ 
/*  96 */     setButtonsEnabled(false);
/*     */   }
/*     */ 
/*     */   private void addComponents() {
/* 100 */     this.bar.add(this.load);
/* 101 */     this.bar.add(this.save);
/* 102 */     this.bar.addSeparator();
/* 103 */     this.bar.add(this.close);
/* 104 */     this.bar.add(this.clear);
/* 105 */     this.bar.addSeparator();
/* 106 */     this.bar.add(this.runGauss);
/* 107 */     this.bar.add(this.runImpulse);
/* 108 */     this.bar.add(Box.createHorizontalGlue());
/* 109 */     this.bar.add(this.exit);
/*     */   }
/*     */ 
/*     */   private void setTooltips() {
/* 113 */ //    this.load.setToolTipText("Wczytaj plik");
/* 114 */   //  this.save.setToolTipText("Zapisz plik");
/* 115 */   //  this.clear.setToolTipText("Zamknij wszystkie zakładki");
/* 116 */    // this.runGauss.setToolTipText("Uruchom zaszumianie szumem Gaussa");
/* 117 */    // this.runImpulse.setToolTipText("Uruchom zaszumianie szumem impulsowym");
/*     */ 
/* 119 */    // this.exit.setToolTipText("Wyjście z programu");
/* 120 */   //  this.close.setToolTipText("Zamknij bieżący plik");
/*     */   }
/*     */ 
/*     */   private void addListeners()
/*     */   {
/* 129 */     this.clear.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 131 */         MainMenu.handleClear(e);
/*     */       }
/*     */     });
/* 135 */     this.load.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 137 */         MainMenu.handleLoad(e);
/*     */       }
/*     */     });
/* 141 */     this.save.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 143 */         MainMenu.handleSave(e);
/*     */       }
/*     */     });
/* 147 */     this.exit.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 149 */         MainMenu.handleExit(e);
/*     */       }
/*     */     });
/* 153 */     this.runGauss.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 155 */         MainMenu.handleRunGauss();
/*     */       }
/*     */     });
/* 159 */     this.runImpulse.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 161 */         MainMenu.handleRunImpulse();
/*     */       }
/*     */     });
/* 165 */     this.close.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 167 */         MainMenu.handleFileClose();
/*     */       }
/*     */     });
/*     */   }
/*     */ 
/*     */   public JToolBar getBar() {
/* 173 */     return this.bar;
/*     */   }
/*     */ 
/*     */   public JButton getLoad() {
/* 177 */     return this.load;
/*     */   }
/*     */ 
/*     */   public JButton getSave() {
/* 181 */     return this.save;
/*     */   }
/*     */ 
/*     */   public JButton getClear() {
/* 185 */     return this.clear;
/*     */   }
/*     */ 
/*     */   public JButton getExit() {
/* 189 */     return this.exit;
/*     */   }
/*     */ 
/*     */   public JButton getRunGauss() {
/* 193 */     return this.runGauss;
/*     */   }
/*     */ 
/*     */   public JButton getRunImpulse() {
/* 197 */     return this.runGauss;
/*     */   }
/*     */ 
/*     */   public JButton getClose() {
/* 201 */     return this.close;
/*     */   }
/*     */ 
/*     */   public void setButtonsEnabled(boolean b) {
/* 205 */     this.runGauss.setEnabled(b);
/* 206 */     this.runImpulse.setEnabled(b);
/* 207 */     this.close.setEnabled(b);
/* 208 */     this.clear.setEnabled(b);
/*     */   }
/*     */ }


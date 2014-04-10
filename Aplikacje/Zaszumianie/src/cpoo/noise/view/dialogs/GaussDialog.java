/*     */ package cpoo.noise.view.dialogs;
/*     */ 
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ 
/*     */ public class GaussDialog extends JDialog
/*     */   implements ActionListener
/*     */ {
/*     */   private static final long serialVersionUID = -5704210797431156236L;
/*  33 */   private JTextField mean = null;
/*  34 */   private JTextField variance = null;
/*     */ 
/*  36 */   private JTextField numberOfPictures = null;
/*  37 */   private JTextField meanStep = null;
/*  38 */   private JTextField varianceStep = null;
/*     */ 
/*  40 */   private JCheckBox serialGeneration = null;
/*     */ 
/*  42 */   private JButton ok = null;
/*  43 */   private JButton cancel = null;
/*     */ 
/*  45 */   boolean answer = false;
/*     */ 
/*     */   public GaussDialog(JFrame frame, boolean modal) {
/*  48 */     super(frame, modal);
/*  49 */     setTitle("Szum Gaussa");
/*  50 */     constructGuiElements();
/*     */ 
/*  52 */     setLayout(new GridLayout(11, 2, 2, 2));
/*     */ 
/*  54 */     add(new JLabel("Parametry rozkładu:"));
/*  55 */     add(new JPanel());
/*     */ 
/*  57 */     add(new JLabel("Średnia:"));
/*  58 */     add(this.mean);
/*     */ 
/*  60 */     add(new JLabel("Wariancja:"));
/*  61 */     add(this.variance);
/*     */ 
/*  63 */     add(new JPanel());
/*  64 */     add(new JPanel());
/*     */ 
/*  66 */     add(new JLabel("Generacja seryjna:"));
/*  67 */     add(new JPanel());
/*     */ 
/*  69 */     add(new JLabel("Seria obrazów"));
/*  70 */     add(this.serialGeneration);
/*     */ 
/*  72 */     add(new JLabel("Liczba obrazów:"));
/*  73 */     add(this.numberOfPictures);
/*     */ 
/*  75 */     add(new JLabel("Skok średniej:"));
/*  76 */     add(this.meanStep);
/*     */ 
/*  78 */     add(new JLabel("Skok wariancji:"));
/*  79 */     add(this.varianceStep);
/*     */ 
/*  81 */     add(new JPanel());
/*  82 */     add(new JPanel());
/*     */ 
/*  84 */     add(this.ok);
/*  85 */     add(this.cancel);
/*     */ 
/*  87 */     ((JComponent)getContentPane()).setBorder(
/*  88 */       BorderFactory.createEmptyBorder(10, 10, 10, 10));
/*     */ 
/*  90 */     setResizable(false);
/*     */ 
/*  92 */     this.ok.addActionListener(this);
/*  93 */     this.cancel.addActionListener(this);
/*     */ 
/*  95 */     pack();
/*  96 */     setLocationRelativeTo(frame);
/*  97 */     setVisible(true);
/*     */   }
/*     */ 
/*     */   private void constructGuiElements() {
/* 101 */     this.mean = new JTextField(10);
/* 102 */     this.mean.setText("0");
/* 103 */     this.variance = new JTextField(10);
/* 104 */     this.variance.setText("1");
/* 105 */     this.numberOfPictures = new JTextField(10);
/* 106 */     this.numberOfPictures.setText("0");
/* 107 */     this.meanStep = new JTextField(10);
/* 108 */     this.meanStep.setText("0");
/* 109 */     this.varianceStep = new JTextField(10);
/* 110 */     this.varianceStep.setText("0");
/* 111 */     this.serialGeneration = new JCheckBox();
/* 112 */     this.ok = new JButton("OK");
/* 113 */     this.cancel = new JButton("Cancel");
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent e)
/*     */   {
/* 120 */     if (this.ok == e.getSource()) {
/* 121 */       setVisible(false);
/* 122 */       this.answer = true;
/* 123 */     } else if (this.cancel == e.getSource()) {
/* 124 */       setVisible(false);
/* 125 */       this.answer = false;
/*     */     }
/*     */   }
/*     */ 
/*     */   public JTextField getMean() {
/* 130 */     return this.mean;
/*     */   }
/*     */ 
/*     */   public JTextField getVariance() {
/* 134 */     return this.variance;
/*     */   }
/*     */ 
/*     */   public JTextField getNumberOfPictures() {
/* 138 */     return this.numberOfPictures;
/*     */   }
/*     */ 
/*     */   public JTextField getMeanStep() {
/* 142 */     return this.meanStep;
/*     */   }
/*     */ 
/*     */   public JTextField getVarianceStep() {
/* 146 */     return this.varianceStep;
/*     */   }
/*     */ 
/*     */   public JCheckBox getSerialGeneration() {
/* 150 */     return this.serialGeneration;
/*     */   }
/*     */ 
/*     */   public boolean getAnswer() {
/* 154 */     return this.answer;
/*     */   }
/*     */ }


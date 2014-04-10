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
/*     */ public class ImpulseDialog extends JDialog
/*     */   implements ActionListener
/*     */ {
/*  34 */   private JTextField p1 = null;
/*  35 */   private JTextField val1 = null;
/*  36 */   private JTextField p2 = null;
/*  37 */   private JTextField val2 = null;
/*     */ 
/*  39 */   private JTextField numberOfPictures = null;
/*  40 */   private JTextField p1Step = null;
/*  41 */   private JTextField p2Step = null;
/*     */ 
/*  43 */   private JTextField val1Step = null;
/*  44 */   private JTextField val2Step = null;
/*     */ 
/*  46 */   private JCheckBox serialGeneration = null;
/*     */ 
/*  48 */   private JButton ok = null;
/*  49 */   private JButton cancel = null;
/*     */ 
/*  51 */   boolean answer = false;
/*     */ 
/*     */   public ImpulseDialog(JFrame frame, boolean modal) {
/*  54 */     super(frame, modal);
/*  55 */     setTitle("Szum Impulsowy");
/*  56 */     constructGuiElements();
/*     */ 
/*  58 */     setLayout(new GridLayout(15, 2, 2, 2));
/*     */ 
/*  60 */     add(new JLabel("Parametry szumu:"));
/*  61 */     add(new JPanel());
/*     */ 
/*  63 */     add(new JLabel(
/*  64 */       "<html>Prawdopodobieństwo 1:<br><font size='1'>(0 - 1)</font></html>"));
/*  65 */     add(this.p1);
/*     */ 
/*  67 */     add(new JLabel(
/*  68 */       "<html>Wartość 1:<br><font size='1'>(0 - 255)</font></html>"));
/*  69 */     add(this.val1);
/*     */ 
/*  71 */     add(new JLabel(
/*  72 */       "<html>Prawdopodobieństwo 2:<br><font size='1'>(0 - '1-prawd. 1')</font>"));
/*  73 */     add(this.p2);
/*     */ 
/*  75 */     add(new JLabel(
/*  76 */       "<html>Wartość 2:<br><font size='1'>(0 - 255)</font></html>"));
/*  77 */     add(this.val2);
/*     */ 
/*  79 */     add(new JPanel());
/*  80 */     add(new JPanel());
/*     */ 
/*  82 */     add(new JLabel("Generacja seryjna:"));
/*  83 */     add(new JPanel());
/*     */ 
/*  85 */     add(new JLabel("Seria obrazów:"));
/*  86 */     add(this.serialGeneration);
/*     */ 
/*  88 */     add(new JLabel("Liczba obrazów:"));
/*  89 */     add(this.numberOfPictures);
/*     */ 
/*  91 */     add(new JLabel("Skok prawd. 1:"));
/*  92 */     add(this.p1Step);
/*     */ 
/*  94 */     add(new JLabel("Skok wartości 1:"));
/*  95 */     add(this.val1Step);
/*     */ 
/*  97 */     add(new JLabel("Skok prawd. 2:"));
/*  98 */     add(this.p2Step);
/*     */ 
/* 100 */     add(new JLabel("Skok wartości 2:"));
/* 101 */     add(this.val2Step);
/*     */ 
/* 103 */     add(new JPanel());
/* 104 */     add(new JPanel());
/*     */ 
/* 106 */     add(this.ok);
/* 107 */     add(this.cancel);
/*     */ 
/* 109 */     ((JComponent)getContentPane()).setBorder(
/* 110 */       BorderFactory.createEmptyBorder(10, 10, 10, 10));
/*     */ 
/* 112 */     setResizable(false);
/*     */ 
/* 114 */     this.ok.addActionListener(this);
/* 115 */     this.cancel.addActionListener(this);
/*     */ 
/* 117 */     pack();
/* 118 */     setLocationRelativeTo(frame);
/* 119 */     setVisible(true);
/*     */   }
/*     */ 
/*     */   private void constructGuiElements()
/*     */   {
/* 124 */     this.p1 = new JTextField(10);
/* 125 */     this.p1.setText("0");
/* 126 */     this.val1 = new JTextField(10);
/* 127 */     this.val1.setText("0");
/* 128 */     this.p2 = new JTextField(10);
/* 129 */     this.p2.setText("0");
/* 130 */     this.val2 = new JTextField(10);
/* 131 */     this.val2.setText("0");
/*     */ 
/* 133 */     this.serialGeneration = new JCheckBox();
/* 134 */     this.numberOfPictures = new JTextField(10);
/* 135 */     this.numberOfPictures.setText("0");
/* 136 */     this.p1Step = new JTextField(10);
/* 137 */     this.p1Step.setText("0");
/* 138 */     this.val1Step = new JTextField(10);
/* 139 */     this.val1Step.setText("0");
/* 140 */     this.p2Step = new JTextField(10);
/* 141 */     this.p2Step.setText("0");
/* 142 */     this.val2Step = new JTextField(10);
/* 143 */     this.val2Step.setText("0");
/*     */ 
/* 145 */     this.ok = new JButton("OK");
/* 146 */     this.cancel = new JButton("Cancel");
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent e)
/*     */   {
/* 153 */     if (this.ok == e.getSource()) {
/* 154 */       setVisible(false);
/* 155 */       this.answer = true;
/* 156 */     } else if (this.cancel == e.getSource()) {
/* 157 */       setVisible(false);
/* 158 */       this.answer = false;
/*     */     }
/*     */   }
/*     */ 
/*     */   public JTextField getP1() {
/* 163 */     return this.p1;
/*     */   }
/*     */ 
/*     */   public JTextField getVal1() {
/* 167 */     return this.val1;
/*     */   }
/*     */ 
/*     */   public JTextField getP2() {
/* 171 */     return this.p2;
/*     */   }
/*     */ 
/*     */   public JTextField getVal2() {
/* 175 */     return this.val2;
/*     */   }
/*     */ 
/*     */   public JTextField getNumberOfPictures() {
/* 179 */     return this.numberOfPictures;
/*     */   }
/*     */ 
/*     */   public JTextField getP1Step() {
/* 183 */     return this.p1Step;
/*     */   }
/*     */ 
/*     */   public JTextField getP2Step() {
/* 187 */     return this.p2Step;
/*     */   }
/*     */ 
/*     */   public JTextField getVal1Step() {
/* 191 */     return this.val1Step;
/*     */   }
/*     */ 
/*     */   public JTextField getVal2Step() {
/* 195 */     return this.val2Step;
/*     */   }
/*     */ 
/*     */   public JCheckBox getSerialGeneration() {
/* 199 */     return this.serialGeneration;
/*     */   }
/*     */ 
/*     */   public boolean getAnswer() {
/* 203 */     return this.answer;
/*     */   }
/*     */ }


/*     */ package cpoo.noise.view;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.IOException;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTabbedPane;
/*     */ import javax.swing.plaf.basic.BasicButtonUI;
/*     */ 
/*     */ public class ContentPane extends JPanel
/*     */ {
/*     */   private MainWindow mainWindow;
/*     */   private ToolBar toolbar;
/*     */   //private StatusBar status;
/*     */   private JTabbedPane editor;
/*     */ 
/*     */   public ContentPane(MainWindow mainWindow)
/*     */   {
/*  36 */     super(new BorderLayout());
/*  37 */     this.mainWindow = mainWindow;
/*  38 */     buildComponents();
/*  39 */     addAllComponents();
/*     */   }
/*     */ 
/*     */   private void buildComponents() {
/*  43 */     this.toolbar = new ToolBar();
/*  44 */     //this.status = new StatusBar(this.mainWindow);
/*  45 */     this.editor = new JTabbedPane();
/*  46 */     this.editor.setTabLayoutPolicy(1);
/*  47 */     //this.status.updateProgress(0);
/*     */   }
/*     */ 
/*     */   private void addAllComponents() {
/*  51 */     add(this.toolbar, "North");
/*  52 */     add(this.editor, "Center");
/*  53 */     //add(this.status, "South");
/*     */   }
/*     */ 
/*     */   public BufferedImage getInput()
/*     */   {
/*  62 */     if (this.editor.getSelectedComponent() != null) {
/*  63 */       return ((ImagePanel)this.editor.getSelectedComponent()).getImage();
/*     */     }
/*  65 */     return null;
/*     */   }
/*     */ 
/*     */   public String getInputTitle()
/*     */   {
/*  74 */     if (this.editor.getSelectedComponent() != null) {
/*  75 */       return ((ImagePanel)this.editor.getSelectedComponent()).getTitle();
/*     */     }
/*  77 */     return null;
/*     */   }
/*     */ 
/*     */   public void addExistingItem(BufferedImage content, String title)
/*     */     throws IOException
/*     */   {
/*  86 */     ImagePanel newPanel = new ImagePanel();
/*  87 */     TitlePannel titlePannel = new TitlePannel(title);
/*  88 */     titlePannel.setName(title);
/*  89 */     this.editor.addTab("", newPanel);
/*  90 */     this.editor.setTabComponentAt(this.editor.indexOfComponent(newPanel), titlePannel);
/*  91 */     newPanel.setImage(content, title);
/*     */ 
/*  94 */     titlePannel.getCloseButton().addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/*  96 */         ContentPane.this.closeDistinctItem(e);
/*     */       }
/*     */     });
/* 100 */     this.editor.setSelectedComponent(newPanel);
/* 101 */     this.toolbar.setButtonsEnabled(true);
/*     */   }
/*     */ 
/*     */   protected void closeDistinctItem(ActionEvent e)
/*     */   {
/* 110 */     int response = JOptionPane.showConfirmDialog(this.mainWindow, 
/* 111 */       "Czy na pewno chcesz zamknąć plik?", "", 
/* 112 */       0, 3);
/* 113 */     if (response == 0) {
/* 114 */       JPanel source = (JPanel)((Component)e.getSource()).getParent();
/* 115 */       int i = this.editor.indexOfTabComponent(source);
/* 116 */       if (i != -1) {
/* 117 */         this.editor.remove(i);
/*     */       }
/*     */     }
/* 120 */     if (this.editor.getTabCount() == 0)
/* 121 */       this.toolbar.setButtonsEnabled(false);
/*     */   }
/*     */ 
/*     */   public void closeSelectedItem()
/*     */   {
/* 129 */     if (this.editor.getTabCount() > 0) {
/* 130 */       int response = JOptionPane.showConfirmDialog(this.mainWindow, 
/* 131 */         "Czy na pewno chcesz zamknąć plik?", "", 
/* 132 */         0, 3);
/* 133 */       if (response == 0) {
/* 134 */         Component selected = this.editor.getSelectedComponent();
/* 135 */         if (selected != null) {
/* 136 */           int index = this.editor.indexOfComponent(selected);
/* 137 */           this.editor.remove(index);
/*     */         }
/*     */       }
/*     */     }
/* 141 */     if (this.editor.getTabCount() == 0)
/* 142 */       this.toolbar.setButtonsEnabled(false);
/*     */   }
/*     */ 
/*     */   public void clearAllItems()
/*     */   {
/* 151 */     int response = JOptionPane.showConfirmDialog(this.mainWindow, 
/* 152 */       "Czy napewno chcesz zamknąć wszystkie zakładki?", "", 
/* 153 */       0, 3);
/* 154 */     if (response == 0) {
/* 155 */       int tabCount = this.editor.getTabCount();
/* 156 */       for (int i = tabCount - 1; i >= 0; i--)
/* 157 */         this.editor.remove(i);
/*     */     }
/*     */   }
/*     */ 
/*     */   //public void setProgress(int progress)
/*     */   //{
/* 198 */     //this.status.updateProgress(progress);
/*     */   //}
/*     */ 
/*     */   //public StatusBar getStatus()
/*     */   //{
/* 203 */   //  //return this.status;
			//	return null;
/*     */   //}
/*     */ 
/*     */   private static class TitlePannel extends JPanel
/*     */   {
/* 169 */     private JButton closeButton = new JButton("x");
/*     */     private JLabel title;
/*     */     private static final int X_SIZE = 15;
/*     */ 
/*     */     TitlePannel(String title)
/*     */     {
/* 175 */       this.title = new JLabel(title);
/* 176 */       constructTitlePannel();
/*     */     }
/*     */ 
/*     */     private void constructTitlePannel() {
/* 180 */       this.closeButton.setPreferredSize(new Dimension(15, 15));
/* 181 */       this.closeButton.setToolTipText(this.title.getText());
/* 182 */       this.closeButton.setUI(new BasicButtonUI());
/* 183 */       this.closeButton.setContentAreaFilled(false);
/* 184 */       this.closeButton.setFocusable(false);
/* 185 */       this.closeButton.setBorder(BorderFactory.createEtchedBorder());
/* 186 */       this.closeButton.setBorderPainted(false);
/* 187 */       this.closeButton.setRolloverEnabled(true);
/* 188 */       add(this.title);
/* 189 */       add(this.closeButton);
/*     */     }
/*     */ 
/*     */     public JButton getCloseButton() {
/* 193 */       return this.closeButton;
/*     */     }
/*     */   }
/*     */ }


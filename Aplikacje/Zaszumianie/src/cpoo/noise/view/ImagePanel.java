/*     */ package cpoo.noise.view;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.color.ColorSpace;
/*     */ import java.awt.event.ComponentAdapter;
/*     */ import java.awt.event.ComponentEvent;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseMotionListener;
/*     */ import java.awt.event.MouseWheelEvent;
/*     */ import java.awt.event.MouseWheelListener;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.io.IOException;
/*     */ import java.util.Arrays;
/*     */ import javax.imageio.ImageIO;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.SwingUtilities;
/*     */ 
/*     */ public class ImagePanel extends JPanel
/*     */ {
/*     */   private static final long serialVersionUID = -6862103808590036779L;
/*     */   public static final String ZOOM_LEVEL_CHANGED_PROPERTY = "zoomLevel";
/*     */   public static final String ZOOM_INCREMENT_CHANGED_PROPERTY = "zoomIncrement";
/*     */   public static final String IMAGE_CHANGED_PROPERTY = "image";
/*     */   private static final double SCREEN_NAV_IMAGE_FACTOR = 0.15D;
/*     */   private static final double NAV_IMAGE_FACTOR = 0.3D;
/*     */   private static final double HIGH_QUALITY_RENDERING_SCALE_THRESHOLD = 1.0D;
/* 138 */   private static final Object INTERPOLATION_TYPE = RenderingHints.VALUE_INTERPOLATION_BILINEAR;
/*     */ 
/* 140 */   private double zoomIncrement = 0.2D;
/* 141 */   private double zoomFactor = 1.0D + this.zoomIncrement;
/* 142 */   private double navZoomFactor = 1.0D + this.zoomIncrement;
/*     */   private BufferedImage image;
/*     */   private String title;
/*     */   private BufferedImage navigationImage;
/*     */   private int navImageWidth;
/*     */   private int navImageHeight;
/* 149 */   private double initialScale = 0.0D;
/* 150 */   private double scale = 0.0D;
/* 151 */   private double navScale = 0.0D;
/* 152 */   private int originX = 0;
/* 153 */   private int originY = 0;
/*     */   private Point mousePosition;
/*     */   private Dimension previousPanelSize;
/* 156 */   private boolean navigationImageEnabled = true;
/* 157 */   private boolean highQualityRenderingEnabled = true;
/*     */ 
/* 159 */   private WheelZoomDevice wheelZoomDevice = null;
/* 160 */   private ButtonZoomDevice buttonZoomDevice = null;
/*     */ 
/*     */   public ImagePanel()
/*     */   {
/* 260 */     setOpaque(false);
/* 261 */     addComponentListener(new ComponentAdapter() {
/*     */       public void componentResized(ComponentEvent e) {
/* 263 */         if (ImagePanel.this.scale > 0.0D) {
/* 264 */           if (ImagePanel.this.isFullImageInPanel())
/* 265 */             ImagePanel.this.centerImage();
/* 266 */           else if (ImagePanel.this.isImageEdgeInPanel()) {
/* 267 */             ImagePanel.this.scaleOrigin();
/*     */           }
/* 269 */           if (ImagePanel.this.isNavigationImageEnabled()) {
/* 270 */             ImagePanel.this.createNavigationImage();
/*     */           }
/* 272 */           ImagePanel.this.repaint();
/*     */         }
/* 274 */         ImagePanel.this.previousPanelSize = ImagePanel.this.getSize();
/*     */       }
/*     */     });
/* 278 */     addMouseListener(new MouseAdapter() {
/*     */       public void mousePressed(MouseEvent e) {
/* 280 */         if ((SwingUtilities.isLeftMouseButton(e)) && 
/* 281 */           (ImagePanel.this.isInNavigationImage(e.getPoint()))) {
/* 282 */           Point p = e.getPoint();
/* 283 */           ImagePanel.this.displayImageAt(p);
/*     */         }
/*     */       }
/*     */     });
/* 289 */     addMouseMotionListener(new MouseMotionListener() {
/*     */       public void mouseDragged(MouseEvent e) {
/* 291 */         if ((SwingUtilities.isLeftMouseButton(e)) && 
/* 292 */           (!ImagePanel.this.isInNavigationImage(e.getPoint()))) {
/* 293 */           Point p = e.getPoint();
/* 294 */           ImagePanel.this.moveImage(p);
/*     */         }
/*     */       }
/*     */ 
/*     */       public void mouseMoved(MouseEvent e)
/*     */       {
/* 300 */         ImagePanel.this.mousePosition = e.getPoint();
/*     */       }
/*     */     });
/* 304 */     setZoomDevice(ZoomDevice.MOUSE_WHEEL);
/*     */   }
/*     */ 
/*     */   public ImagePanel(BufferedImage image, String title)
/*     */     throws IOException
/*     */   {
/* 312 */     this();
/* 313 */     setImage(image, title);
/*     */   }
/*     */ 
/*     */  //private void addWheelZoomDevice() {
/* 317 */    // if (this.wheelZoomDevice == null) {
/* 318 */      //this.wheelZoomDevice = new WheelZoomDevice(null);
/* 319 */       //addMouseWheelListener(this.wheelZoomDevice);
/*     */    // }
/*     */  // }
/*     */ 
/*     */    //private void addButtonZoomDevice() {
/* 324 */  //   if (this.buttonZoomDevice == null) {
/* 325 */    //   this.buttonZoomDevice = new ButtonZoomDevice(null);
/* 326 */      // addMouseListener(this.buttonZoomDevice);
/*     */    // }
/*     */  // }
/*     */ 
/*     */ //  private void removeWheelZoomDevice() {
/* 331 */   //  if (this.wheelZoomDevice != null) {
/* 332 */     //  removeMouseWheelListener(this.wheelZoomDevice);
/* 333 */     //  this.wheelZoomDevice = null;
/*     */    // }
/*     */  // }
/*     */ 
/*     */  // private void removeButtonZoomDevice() {
/* 338 */   //  if (this.buttonZoomDevice != null) {
/* 339 */    //   removeMouseListener(this.buttonZoomDevice);
/* 340 */      // this.buttonZoomDevice = null;
/*     */    // }
/*     */   //}
/*     */ 
/*     */   public void setZoomDevice(ZoomDevice newZoomDevice)
/*     */   {
/* 350 */     if (newZoomDevice == ZoomDevice.NONE) {
/* 351 */   //    removeWheelZoomDevice();
/* 352 */     //  removeButtonZoomDevice();
/* 353 */     } else if (newZoomDevice == ZoomDevice.MOUSE_BUTTON) {
/* 354 */    //   removeWheelZoomDevice();
/* 355 */      // addButtonZoomDevice();
/* 356 */     } else if (newZoomDevice == ZoomDevice.MOUSE_WHEEL) {
/* 357 */      // removeButtonZoomDevice();
/* 358 */     //  addWheelZoomDevice();
/*     */     }
/*     */   }
/*     */ 
/*     */   public ZoomDevice getZoomDevice()
/*     */   {
/* 366 */    // if (this.buttonZoomDevice != null)
/* 367 */      // return ZoomDevice.MOUSE_BUTTON;
/* 368 */    // if (this.wheelZoomDevice != null) {
/* 369 */     //  return ZoomDevice.MOUSE_WHEEL;
/*     */     // }
/* 371 */     return ZoomDevice.NONE;
/*     */   }
/*     */ 
/*     */   private void initializeParams()
/*     */   {
/* 377 */     double xScale = getWidth() / this.image.getWidth();
/* 378 */     double yScale = getHeight() / this.image.getHeight();
/* 379 */     this.initialScale = Math.min(xScale, yScale);
/* 380 */     this.scale = this.initialScale;
/*     */ 
/* 383 */     centerImage();
/* 384 */     if (isNavigationImageEnabled())
/* 385 */       createNavigationImage();
/*     */   }
/*     */ 
/*     */   private void centerImage()
/*     */   {
/* 391 */     this.originX = ((getWidth() - getScreenImageWidth()) / 2);
/* 392 */     this.originY = ((getHeight() - getScreenImageHeight()) / 2);
/*     */   }
/*     */ 
/*     */   private void createNavigationImage()
/*     */   {
/* 399 */     this.navImageWidth = ((int)(getWidth() * 0.3D));
/* 400 */     this.navImageHeight = (this.navImageWidth * this.image.getHeight() / this.image.getWidth());
/* 401 */     int scrNavImageWidth = (int)(getWidth() * 0.15D);
/*     */ 
/* 403 */     this.navScale = (scrNavImageWidth / this.navImageWidth);
/* 404 */     this.navigationImage = new BufferedImage(this.navImageWidth, this.navImageHeight, 
/* 405 */       this.image.getType());
/* 406 */     Graphics g = this.navigationImage.getGraphics();
/* 407 */     g.drawImage(this.image, 0, 0, this.navImageWidth, this.navImageHeight, null);
/*     */   }
/*     */ 
/*     */   public void setImage(BufferedImage image, String title)
/*     */   {
/* 416 */     BufferedImage oldImage = this.image;
/* 417 */     this.title = title;
/* 418 */     this.image = image;
/*     */ 
/* 421 */     this.scale = 0.0D;
/* 422 */     firePropertyChange("image", oldImage, image);
/* 423 */     repaint();
/*     */   }
/*     */ 
/*     */   public static boolean isStandardRGBImage(BufferedImage bImage)
/*     */   {
/* 430 */     return bImage.getColorModel().getColorSpace().isCS_sRGB();
/*     */   }
/*     */ 
/*     */   private Coords panelToImageCoords(Point p)
/*     */   {
/* 435 */     return new Coords((p.x - this.originX) / this.scale, (p.y - this.originY) / this.scale);
/*     */   }
/*     */ 
/*     */   private Coords imageToPanelCoords(Coords p)
/*     */   {
/* 440 */     return new Coords(p.x * this.scale + this.originX, p.y * this.scale + this.originY);
/*     */   }
/*     */ 
/*     */   private Point navToZoomedImageCoords(Point p)
/*     */   {
/* 445 */     int x = p.x * getScreenImageWidth() / getScreenNavImageWidth();
/* 446 */     int y = p.y * getScreenImageHeight() / getScreenNavImageHeight();
/* 447 */     return new Point(x, y);
/*     */   }
/*     */ 
/*     */   private void displayImageAt(Point p)
/*     */   {
/* 454 */     Point scrImagePoint = navToZoomedImageCoords(p);
/* 455 */     this.originX = (-(scrImagePoint.x - getWidth() / 2));
/* 456 */     this.originY = (-(scrImagePoint.y - getHeight() / 2));
/* 457 */     repaint();
/*     */   }
/*     */ 
/*     */   private boolean isInImage(Point p)
/*     */   {
/* 462 */     Coords coords = panelToImageCoords(p);
/* 463 */     int x = coords.getIntX();
/* 464 */     int y = coords.getIntY();
/* 465 */     return (x >= 0) && (x < this.image.getWidth()) && (y >= 0) && (y < this.image.getHeight());
/*     */   }
/*     */ 
/*     */   private boolean isInNavigationImage(Point p)
/*     */   {
/* 471 */     return (isNavigationImageEnabled()) && (p.x < getScreenNavImageWidth()) && (
/* 472 */       p.y < getScreenNavImageHeight());
/*     */   }
/*     */ 
/*     */   private boolean isImageEdgeInPanel()
/*     */   {
/* 477 */     if (this.previousPanelSize == null) {
/* 478 */       return false;
/*     */     }
/*     */ 
/* 481 */     return ((this.originX > 0) && (this.originX < this.previousPanelSize.width)) || (
/* 482 */       (this.originY > 0) && (
/* 482 */       this.originY < this.previousPanelSize.height));
/*     */   }
/*     */ 
/*     */   private boolean isFullImageInPanel()
/*     */   {
/* 487 */     return (this.originX >= 0) && (this.originX + getScreenImageWidth() < getWidth()) && 
/* 488 */       (this.originY >= 0) && (
/* 488 */       this.originY + getScreenImageHeight() < getHeight());
/*     */   }
/*     */ 
/*     */   public boolean isHighQualityRenderingEnabled()
/*     */   {
/* 497 */     return this.highQualityRenderingEnabled;
/*     */   }
/*     */ 
/*     */   public void setHighQualityRenderingEnabled(boolean enabled)
/*     */   {
/* 506 */     this.highQualityRenderingEnabled = enabled;
/*     */   }
/*     */ 
/*     */   private boolean isHighQualityRendering()
/*     */   {
/* 513 */     return (this.highQualityRenderingEnabled) && (
/* 514 */       this.scale > 1.0D);
/*     */   }
/*     */ 
/*     */   public boolean isNavigationImageEnabled()
/*     */   {
/* 523 */     return this.navigationImageEnabled;
/*     */   }
/*     */ 
/*     */   public void setNavigationImageEnabled(boolean enabled)
/*     */   {
/* 534 */     this.navigationImageEnabled = enabled;
/* 535 */     repaint();
/*     */   }
/*     */ 
/*     */   private void scaleOrigin()
/*     */   {
/* 540 */     this.originX = (this.originX * getWidth() / this.previousPanelSize.width);
/* 541 */     this.originY = (this.originY * getHeight() / this.previousPanelSize.height);
/* 542 */     repaint();
/*     */   }
/*     */ 
/*     */   private double zoomToScale(double zoom)
/*     */   {
/* 547 */     return this.initialScale * zoom;
/*     */   }
/*     */ 
/*     */   public double getZoom()
/*     */   {
/* 556 */     return this.scale / this.initialScale;
/*     */   }
/*     */ 
/*     */   public void setZoom(double newZoom)
/*     */   {
/* 568 */     Point zoomingCenter = new Point(getWidth() / 2, getHeight() / 2);
/* 569 */     setZoom(newZoom, zoomingCenter);
/*     */   }
/*     */ 
/*     */   public void setZoom(double newZoom, Point zoomingCenter)
/*     */   {
/* 581 */     Coords imageP = panelToImageCoords(zoomingCenter);
/* 582 */     if (imageP.x < 0.0D) {
/* 583 */       imageP.x = 0.0D;
/*     */     }
/* 585 */     if (imageP.y < 0.0D) {
/* 586 */       imageP.y = 0.0D;
/*     */     }
/* 588 */     if (imageP.x >= this.image.getWidth()) {
/* 589 */       imageP.x = (this.image.getWidth() - 1.0D);
/*     */     }
/* 591 */     if (imageP.y >= this.image.getHeight()) {
/* 592 */       imageP.y = (this.image.getHeight() - 1.0D);
/*     */     }
/*     */ 
/* 595 */     Coords correctedP = imageToPanelCoords(imageP);
/* 596 */     double oldZoom = getZoom();
/* 597 */     this.scale = zoomToScale(newZoom);
/* 598 */     Coords panelP = imageToPanelCoords(imageP);
/*     */ 
/* 600 */     this.originX += correctedP.getIntX() - (int)panelP.x;
/* 601 */     this.originY += correctedP.getIntY() - (int)panelP.y;
/*     */ 
/* 603 */     firePropertyChange("zoomLevel", new Double(oldZoom), 
/* 604 */       new Double(getZoom()));
/*     */ 
/* 606 */     repaint();
/*     */   }
/*     */ 
/*     */   public double getZoomIncrement()
/*     */   {
/* 615 */     return this.zoomIncrement;
/*     */   }
/*     */ 
/*     */   public void setZoomIncrement(double newZoomIncrement)
/*     */   {
/* 624 */     double oldZoomIncrement = this.zoomIncrement;
/* 625 */     this.zoomIncrement = newZoomIncrement;
/* 626 */     firePropertyChange("zoomIncrement", 
/* 627 */       new Double(oldZoomIncrement), new Double(this.zoomIncrement));
/*     */   }
/*     */ 
/*     */   private void zoomImage()
/*     */   {
/* 633 */     Coords imageP = panelToImageCoords(this.mousePosition);
/* 634 */     double oldZoom = getZoom();
/* 635 */     this.scale *= this.zoomFactor;
/* 636 */     Coords panelP = imageToPanelCoords(imageP);
/*     */ 
/* 638 */     this.originX += this.mousePosition.x - (int)panelP.x;
/* 639 */     this.originY += this.mousePosition.y - (int)panelP.y;
/*     */ 
/* 641 */     firePropertyChange("zoomLevel", new Double(oldZoom), 
/* 642 */       new Double(getZoom()));
/*     */ 
/* 644 */     repaint();
/*     */   }
/*     */ 
/*     */   private void zoomNavigationImage()
/*     */   {
/* 649 */     this.navScale *= this.navZoomFactor;
/* 650 */     repaint();
/*     */   }
/*     */ 
/*     */   public Point getImageOrigin()
/*     */   {
/* 661 */     return new Point(this.originX, this.originY);
/*     */   }
/*     */ 
/*     */   public void setImageOrigin(int x, int y)
/*     */   {
/* 673 */     setImageOrigin(new Point(x, y));
/*     */   }
/*     */ 
/*     */   public void setImageOrigin(Point newOrigin)
/*     */   {
/* 684 */     this.originX = newOrigin.x;
/* 685 */     this.originY = newOrigin.y;
/* 686 */     repaint();
/*     */   }
/*     */ 
/*     */   private void moveImage(Point p)
/*     */   {
/* 691 */     int xDelta = p.x - this.mousePosition.x;
/* 692 */     int yDelta = p.y - this.mousePosition.y;
/* 693 */     this.originX += xDelta;
/* 694 */     this.originY += yDelta;
/* 695 */     this.mousePosition = p;
/* 696 */     repaint();
/*     */   }
/*     */ 
/*     */   private Rectangle getImageClipBounds()
/*     */   {
/* 702 */     Coords startCoords = panelToImageCoords(new Point(0, 0));
/* 703 */     Coords endCoords = panelToImageCoords(new Point(getWidth() - 1, getHeight() - 1));
/* 704 */     int panelX1 = startCoords.getIntX();
/* 705 */     int panelY1 = startCoords.getIntY();
/* 706 */     int panelX2 = endCoords.getIntX();
/* 707 */     int panelY2 = endCoords.getIntY();
/*     */ 
/* 709 */     if ((panelX1 >= this.image.getWidth()) || (panelX2 < 0) || (panelY1 >= this.image.getHeight()) || (panelY2 < 0)) {
/* 710 */       return null;
/*     */     }
/*     */ 
/* 713 */     int x1 = panelX1 < 0 ? 0 : panelX1;
/* 714 */     int y1 = panelY1 < 0 ? 0 : panelY1;
/* 715 */     int x2 = panelX2 >= this.image.getWidth() ? this.image.getWidth() - 1 : panelX2;
/* 716 */     int y2 = panelY2 >= this.image.getHeight() ? this.image.getHeight() - 1 : panelY2;
/* 717 */     return new Rectangle(x1, y1, x2 - x1 + 1, y2 - y1 + 1);
/*     */   }
/*     */ 
/*     */   protected void paintComponent(Graphics g)
/*     */   {
/* 728 */     super.paintComponent(g);
/*     */ 
/* 730 */     if (this.image == null) {
/* 731 */       return;
/*     */     }
/*     */ 
/* 734 */     if (this.scale == 0.0D) {
/* 735 */       initializeParams();
/*     */     }
/*     */ 
/* 738 */     if (isHighQualityRendering()) {
/* 739 */       Rectangle rect = getImageClipBounds();
/* 740 */       if ((rect == null) || (rect.width == 0) || (rect.height == 0)) {
/* 741 */         return;
/*     */       }
/*     */ 
/* 744 */       BufferedImage subimage = this.image.getSubimage(rect.x, rect.y, rect.width, 
/* 745 */         rect.height);
/* 746 */       Graphics2D g2 = (Graphics2D)g;
/* 747 */       g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, INTERPOLATION_TYPE);
/* 748 */       g2.drawImage(subimage, Math.max(0, this.originX), Math.max(0, this.originY), 
/* 749 */         Math.min((int)(subimage.getWidth() * this.scale), getWidth()), 
/* 750 */         Math.min((int)(subimage.getHeight() * this.scale), getHeight()), null);
/*     */     } else {
/* 752 */       g.drawImage(this.image, this.originX, this.originY, getScreenImageWidth(), 
/* 753 */         getScreenImageHeight(), null);
/*     */     }
/*     */ 
/* 757 */     if (isNavigationImageEnabled()) {
/* 758 */       g.drawImage(this.navigationImage, 0, 0, getScreenNavImageWidth(), 
/* 759 */         getScreenNavImageHeight(), null);
/* 760 */       drawZoomAreaOutline(g);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void drawZoomAreaOutline(Graphics g)
/*     */   {
/* 767 */     if (isFullImageInPanel()) {
/* 768 */       return;
/*     */     }
/*     */ 
/* 771 */     int x = -this.originX * getScreenNavImageWidth() / getScreenImageWidth();
/* 772 */     int y = -this.originY * getScreenNavImageHeight() / getScreenImageHeight();
/* 773 */     int width = getWidth() * getScreenNavImageWidth() / getScreenImageWidth();
/* 774 */     int height = getHeight() * getScreenNavImageHeight() / getScreenImageHeight();
/* 775 */     g.setColor(Color.red);
/* 776 */     g.drawRect(x, y, width, height);
/*     */   }
/*     */ 
/*     */   private int getScreenImageWidth() {
/* 780 */     return (int)(this.scale * this.image.getWidth());
/*     */   }
/*     */ 
/*     */   private int getScreenImageHeight() {
/* 784 */     return (int)(this.scale * this.image.getHeight());
/*     */   }
/*     */ 
/*     */   private int getScreenNavImageWidth() {
/* 788 */     return (int)(this.navScale * this.navImageWidth);
/*     */   }
/*     */ 
/*     */   private int getScreenNavImageHeight() {
/* 792 */     return (int)(this.navScale * this.navImageHeight);
/*     */   }
/*     */ 
/*     */   private static String[] getImageFormatExtensions() {
/* 796 */     String[] names = ImageIO.getReaderFormatNames();
/* 797 */     for (int i = 0; i < names.length; i++) {
/* 798 */       names[i] = names[i].toLowerCase();
/*     */     }
/* 800 */     Arrays.sort(names);
/* 801 */     return names;
/*     */   }
/*     */ 
/*     */   private static boolean endsWithImageFormatExtension(String name)
/*     */   {
/* 806 */     int dotIndex = name.lastIndexOf(".");
/* 807 */     if (dotIndex == -1) {
/* 808 */       return false;
/*     */     }
/*     */ 
/* 811 */     String extension = name.substring(dotIndex + 1).toLowerCase();
/* 812 */     return Arrays.binarySearch(getImageFormatExtensions(), extension) >= 0;
/*     */   }
/*     */ 
/*     */   public BufferedImage getImage()
/*     */   {
/* 849 */     return this.image;
/*     */   }
/*     */ 
/*     */   public String getTitle() {
/* 853 */     return this.title;
/*     */   }
/*     */ 
/*     */   public void setTitle(String title) {
/* 857 */     this.title = title;
/*     */   }
/*     */ 
/*     */   private class ButtonZoomDevice extends MouseAdapter
/*     */   {
/*     */     private ButtonZoomDevice()
/*     */     {
/*     */     }
/*     */ 
/*     */     public void mouseClicked(MouseEvent e)
/*     */     {
/* 234 */       Point p = e.getPoint();
/* 235 */       if (SwingUtilities.isRightMouseButton(e)) {
/* 236 */         if (ImagePanel.this.isInNavigationImage(p)) {
/* 237 */           ImagePanel.this.navZoomFactor = (1.0D - ImagePanel.this.zoomIncrement);
/* 238 */           ImagePanel.this.zoomNavigationImage();
/* 239 */         } else if (ImagePanel.this.isInImage(p)) {
/* 240 */           ImagePanel.this.zoomFactor = (1.0D - ImagePanel.this.zoomIncrement);
/* 241 */           ImagePanel.this.zoomImage();
/*     */         }
/*     */       }
/* 244 */       else if (ImagePanel.this.isInNavigationImage(p)) {
/* 245 */         ImagePanel.this.navZoomFactor = (1.0D + ImagePanel.this.zoomIncrement);
/* 246 */         ImagePanel.this.zoomNavigationImage();
/* 247 */       } else if (ImagePanel.this.isInImage(p)) {
/* 248 */         ImagePanel.this.zoomFactor = (1.0D + ImagePanel.this.zoomIncrement);
/* 249 */         ImagePanel.this.zoomImage();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private class Coords
/*     */   {
/*     */     public double x;
/*     */     public double y;
/*     */ 
/*     */     public Coords(double x, double y)
/*     */     {
/* 196 */       this.x = x;
/* 197 */       this.y = y;
/*     */     }
/*     */     public int getIntX() {
/* 200 */       return (int)Math.round(this.x);
/*     */     }
/*     */     public int getIntY() {
/* 203 */       return (int)Math.round(this.y);
/*     */     }
/*     */     public String toString() {
/* 206 */       return "[Coords: x=" + this.x + ",y=" + this.y + "]";
/*     */     }
/*     */   }
/*     */   private class WheelZoomDevice implements MouseWheelListener {
/*     */     private WheelZoomDevice() {
/*     */     }
/* 212 */     public void mouseWheelMoved(MouseWheelEvent e) { Point p = e.getPoint();
/* 213 */       boolean zoomIn = e.getWheelRotation() < 0;
/* 214 */       if (ImagePanel.this.isInNavigationImage(p)) {
/* 215 */         if (zoomIn)
/* 216 */           ImagePanel.this.navZoomFactor = (1.0D + ImagePanel.this.zoomIncrement);
/*     */         else {
/* 218 */           ImagePanel.this.navZoomFactor = (1.0D - ImagePanel.this.zoomIncrement);
/*     */         }
/* 220 */         ImagePanel.this.zoomNavigationImage();
/* 221 */       } else if (ImagePanel.this.isInImage(p)) {
/* 222 */         if (zoomIn)
/* 223 */           ImagePanel.this.zoomFactor = (1.0D + ImagePanel.this.zoomIncrement);
/*     */         else {
/* 225 */           ImagePanel.this.zoomFactor = (1.0D - ImagePanel.this.zoomIncrement);
/*     */         }
/* 227 */         ImagePanel.this.zoomImage();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class ZoomDevice
/*     */   {
/* 170 */     public static final ZoomDevice NONE = new ZoomDevice("none");
/*     */ 
/* 175 */     public static final ZoomDevice MOUSE_BUTTON = new ZoomDevice("mouseButton");
/*     */ 
/* 180 */     public static final ZoomDevice MOUSE_WHEEL = new ZoomDevice("mouseWheel");
/*     */     private String zoomDevice;
/*     */ 
/*     */     private ZoomDevice(String zoomDevice)
/*     */     {
/* 184 */       this.zoomDevice = zoomDevice;
/*     */     }
/*     */     public String toString() {
/* 187 */       return this.zoomDevice;
/*     */     }
/*     */   }
/*     */ }

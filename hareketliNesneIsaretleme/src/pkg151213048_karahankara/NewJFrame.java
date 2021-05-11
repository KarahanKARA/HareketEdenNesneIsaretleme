
package pkg151213048_karahankara;

import com.sun.scenario.effect.impl.prism.PrImage;
import java.awt.Color;
import java.awt.Component;
import java.util.List;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.metadata.IIOInvalidTreeException;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import static javax.print.attribute.ResolutionSyntax.DPI;
import static javax.print.attribute.Size2DSyntax.INCH;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.VideoWriter;
import org.opencv.videoio.Videoio;
import java.awt.Dimension;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Iterator;
import javax.imageio.IIOImage;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;

public class NewJFrame extends javax.swing.JFrame {

    
    public NewJFrame() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Video seçiniz");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Arkaplan Oluştur");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jButton1)
                .addGap(141, 141, 141)
                .addComponent(jButton2)
                .addContainerGap(150, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(137, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(65, 65, 65))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFileChooser fc = new JFileChooser("C:\\Users\\karah\\Desktop");
        int sonuc = fc.showOpenDialog(null);
        fc.setDialogTitle("Lütfen videoyu seçiniz.");
        fc.setSize(700, 500);
        fc.setVisible(true);
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        File path = fc.getSelectedFile();
        System.out.println(path);
        
        String yol= path.toString();
        
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        VideoCapture cap = new VideoCapture();

        String input = yol;
        String output = "C:\\Users\\karah\\Desktop\\donemprojesi\\dosyalar";

        cap.open(input);

        int video_length = (int) cap.get(Videoio.CAP_PROP_FRAME_COUNT);
        int frames_per_second = (int) cap.get(Videoio.CAP_PROP_FPS);
        int frame_number = (int) cap.get(Videoio.CAP_PROP_POS_FRAMES);
        
        
        Mat frame = new Mat();
        frame_number = 0;
        if (cap.isOpened()) {
            while (cap.read(frame))
            {
                Imgcodecs.imwrite(output + "/" + frame_number + ".jpg", frame);
                frame_number++;
                cap.read(frame);
                cap.read(frame);
                cap.read(frame);
                cap.read(frame);
                cap.read(frame);
            }
            cap.release();
        } else {
            System.out.println("Fail");
        }
        
        
    }//GEN-LAST:event_jButton1ActionPerformed
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int fotoSayisi = new File("C:\\Users\\karah\\Desktop\\donemprojesi\\dosyalar\\").list().length;
        fotoSayisi --;
        int width = 0, height = 0;

        File a = new File("C:\\Users\\karah\\Desktop\\donemprojesi\\dosyalar\\0.jpg");
        BufferedImage sizeHandler=null;
        try {
            sizeHandler = ImageIO.read(a);
        } catch (IOException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        Integer array[][][] = new Integer[fotoSayisi][sizeHandler.getWidth()][sizeHandler.getHeight()];

        for (int i = 1; i <= fotoSayisi; i++) {
            System.out.println(i);
            BufferedImage img = null;
            File f;
            try {
                f = new File("C:\\Users\\karah\\Desktop\\donemprojesi\\dosyalar\\"+i+".jpg");
                img = ImageIO.read(f);

            } catch (IOException e) {
                System.out.println("Hata" + e);
            }
            height = sizeHandler.getHeight();
            width = sizeHandler.getWidth();

            for (int w = 0; w < width; w++) {
                for (int h = 0; h < height; h++) {
                    array[i - 1][w][h] = img.getRGB(w, h);
                }
            }
        }

        System.out.println("Tüm fotoğraflar tarandı..");
        System.out.println("Arkaplan fotoğrafı oluşturuluyor...");
        List<Integer> tempList = new ArrayList<>();
        BufferedImage backgroundImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);


        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                for (int i = 1; i <= fotoSayisi; i++) {

                    tempList.add(array[i - 1][w][h]);
                }
                Collections.sort(tempList);
                backgroundImg.setRGB(w, h, tempList.get(tempList.size() / 2));
                tempList.clear();
            }
        }

        File outputfile = new File("C:\\Users\\karah\\Desktop\\donemprojesi\\background.png");
        try {
            ImageIO.write(backgroundImg, "png", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Yazdırma işlemi bitti");
        /////////////////
        
        BufferedImage redImage = new BufferedImage(sizeHandler.getWidth(),sizeHandler.getHeight(),BufferedImage.TYPE_INT_ARGB);
        Integer redArray[][][] = new Integer[fotoSayisi][width][height];
        File redOutput = null;
        for (int i = 1; i <= fotoSayisi; i++) { //  frame
            for (int w = 0; w < width; w++) {
                for (int h = 0; h < height; h++) {
                    Color background = new Color(backgroundImg.getRGB(w, h));
                    Color current = new Color(array[i - 1][w][h]);
                    if (similarTo(background, current)) { // Fark varsa
                        redImage.setRGB(w, h, Color.RED.getRGB()); 
                    }
                    else{
                        redImage.setRGB(w, h, background.getRGB()); 
                    }
                }
            }
            float s =(float) 0;
            System.out.println(i+". foto");
            File tempik = new File("C:\\Users\\karah\\Desktop\\donemprojesi\\kirmizi\\"+i+".png");
            try {
                ImageIO.write(redImage, "png", tempik);
               
            } catch (IOException ex) {
                Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("İşlem Tamamlandı");
    }//GEN-LAST:event_jButton2ActionPerformed
    static boolean similarTo(Color background, Color current) {
            double distance =
                    (background.getRed() - current.getRed()) * (background.getRed() - current.getRed()) +
                            (background.getGreen() - current.getGreen()) * (background.getGreen() - current.getGreen()) +
                            (background.getBlue() - current.getBlue()) * (background.getBlue() - current.getBlue());
            if (distance > 1000) {
                return true;
            } else {
                return false;
            }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
        
    
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    // End of variables declaration//GEN-END:variables
}

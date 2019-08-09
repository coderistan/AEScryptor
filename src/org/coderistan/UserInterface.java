/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coderistan;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import net.iharder.dnd.FileDrop;

class ListDelete implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() instanceof JList && e.getKeyCode() == 8) {
            JList source = (JList) e.getSource();
            source.setEnabled(false);

            DefaultListModel model = (DefaultListModel) source.getModel();
            source.getSelectedValuesList().forEach(veri -> model.removeElement(veri));

            source.setEnabled(true);
        }
    }

}

public class UserInterface extends javax.swing.JFrame implements Runnable {

    Color c;
    DefaultListModel<File> dosyalar;
    private String kaynak = null, hedef = null;
    private ArrayList isimler = new ArrayList();
    private AESkey key;
    private Queue<String> works = new LinkedList();
    private boolean program_start = false;
    private Worker[] worker;

    public UserInterface() {
        try {
            this.setTheme("Metal");

            initComponents();
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            this.addWindowListener(new WindowListener() {
                @Override
                public void windowOpened(WindowEvent e) {

                }

                @Override
                public void windowClosing(WindowEvent e) {
                    if (program_start) {
                        if (JOptionPane.showConfirmDialog(null,
                                "İşleminiz sona erecek", "Emin misiniz?",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                            stopProcess();
                            System.exit(0);
                        }
                    }else{
                        System.exit(0);
                    }
                }

                @Override
                public void windowClosed(WindowEvent e) {

                }

                @Override
                public void windowIconified(WindowEvent e) {

                }

                @Override
                public void windowDeiconified(WindowEvent e) {

                }

                @Override
                public void windowActivated(WindowEvent e) {

                }

                @Override
                public void windowDeactivated(WindowEvent e) {

                }
            });

            this.key = new AESkey((dosyaSifre.getPassword() != null) ? new String(dosyaSifre.getPassword()) : AESkey.random);
            this.getContentPane().setBackground(Color.WHITE);
            this.dosyalar = new DefaultListModel<>();

            islenecekDosyalar.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            islenecekDosyalar.setModel(this.dosyalar);
            islenecekDosyalar.addKeyListener(new ListDelete());

            new FileDrop(islenecekDosyalar, new FileDrop.Listener() {
                @Override
                public void filesDropped(File[] files) {
                    addFilesToPanel(files);
                }
            });

        } catch (Exception ex) {
            Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void addFilesToPanel(File[] files) {
        isimler.clear();
        for (int i = 0; i < dosyalar.size(); i++) {
            isimler.add(dosyalar.get(i).toString());
        }

        for (File f : files) {
            if (isimler.contains(f.toString())) {
                continue;
            }
            dosyalar.addElement(f);
        }
    }

    public void stopProcess() {
        for (Worker w : worker) {
            w.setRunnable(false);
        }
        program_start = false;
        this.allRelease();
        this.jProgressBar1.setValue(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        kaynakButon = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        islenecekDosyalar = new javax.swing.JList<>();
        clearList = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        hedefButon = new javax.swing.JButton();
        kayitDizin = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        baslaButon = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        dosyaUzanti = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        enMode = new javax.swing.JRadioButton();
        deMode = new javax.swing.JRadioButton();
        dosyaSifre = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("AESCryptor GUI");
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        kaynakButon.setBackground(new java.awt.Color(0, 153, 204));
        kaynakButon.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        kaynakButon.setForeground(new java.awt.Color(255, 255, 255));
        kaynakButon.setText("Seç");
        kaynakButon.setBorder(null);
        kaynakButon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ButonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ButonMouseExited(evt);
            }
        });
        kaynakButon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kaynakButonActionPerformed(evt);
            }
        });

        islenecekDosyalar.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jScrollPane1.setViewportView(islenecekDosyalar);

        clearList.setBackground(new java.awt.Color(0, 153, 204));
        clearList.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        clearList.setForeground(new java.awt.Color(255, 255, 255));
        clearList.setText("Temizle");
        clearList.setBorder(null);
        clearList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ButonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ButonMouseExited(evt);
            }
        });
        clearList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearListActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(kaynakButon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearList, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(kaynakButon, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clearList, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 108, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        hedefButon.setBackground(new java.awt.Color(0, 153, 204));
        hedefButon.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        hedefButon.setForeground(new java.awt.Color(255, 255, 255));
        hedefButon.setText("Seç");
        hedefButon.setBorder(null);
        hedefButon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ButonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ButonMouseExited(evt);
            }
        });
        hedefButon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hedefButonActionPerformed(evt);
            }
        });

        kayitDizin.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        kayitDizin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        kayitDizin.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                BoxFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField1FocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(kayitDizin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hedefButon, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kayitDizin)
            .addComponent(hedefButon, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel1.setText("İşlenecek dosya/dosyalar *");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel2.setText("Kaydedilecek dizin *");

        jProgressBar1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 204), 1, true));
        jProgressBar1.setStringPainted(true);

        baslaButon.setBackground(new java.awt.Color(0, 153, 204));
        baslaButon.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        baslaButon.setForeground(new java.awt.Color(255, 255, 255));
        baslaButon.setText("Başlat!");
        baslaButon.setBorder(null);
        baslaButon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ButonMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ButonMouseEntered(evt);
            }
        });
        baslaButon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                baslaButonActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel4.setText("Dosya uzantısı * ");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        dosyaUzanti.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        dosyaUzanti.setText(".encode");
        dosyaUzanti.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        dosyaUzanti.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                BoxFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField1FocusLost(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel5.setText("Şifreniz");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel3.setText("Mod:");

        enMode.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(enMode);
        enMode.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        enMode.setSelected(true);
        enMode.setText("Encode");
        enMode.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                enModeFocusGained(evt);
            }
        });

        deMode.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(deMode);
        deMode.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        deMode.setText("Decode");

        dosyaSifre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        dosyaSifre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                BoxFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField1FocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dosyaUzanti)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(enMode)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deMode)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(dosyaSifre)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(dosyaUzanti, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addGap(8, 8, 8)
                .addComponent(dosyaSifre, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(enMode)
                    .addComponent(deMode))
                .addContainerGap())
        );

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 153, 204));
        jLabel6.setText("(?)");
        jLabel6.setToolTipText("Dosya şifrelenirken veya çözümlenirken bu uzantıya göre işlem yapılır");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 204, 204));
        jLabel7.setText("https://coderistan.blogspot.com");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(baslaButon, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(baslaButon, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButonMouseEntered

        JButton buton = (JButton) evt.getSource();

        c = buton.getBackground();

        buton.setBackground(Color.BLACK);

    }//GEN-LAST:event_ButonMouseEntered

    private void ButonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButonMouseExited

        JButton buton = (JButton) evt.getSource();

        buton.setBackground(c);
    }//GEN-LAST:event_ButonMouseExited

    private void kaynakButonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kaynakButonActionPerformed
        kaynakButon.setEnabled(false);
        JFileChooser fd = new JFileChooser();
        fd.setMultiSelectionEnabled(true);
        fd.setDialogTitle("İşlenecek dosya");
        int secildi = fd.showOpenDialog(this);
        if (secildi == JFileChooser.APPROVE_OPTION) {
            addFilesToPanel(fd.getSelectedFiles());
        }
        kaynakButon.setEnabled(true);
    }//GEN-LAST:event_kaynakButonActionPerformed

    private void hedefButonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hedefButonActionPerformed
        System.setProperty("apple.awt.fileDialogForDirectories", "true");
        JFileChooser fd = new JFileChooser();
        fd.setDialogTitle("Kaydedilecek dizin");
        fd.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int secildi = fd.showOpenDialog(this);
        if (secildi == JFileChooser.APPROVE_OPTION) {
            kayitDizin.setText(fd.getSelectedFile().getAbsolutePath());
            hedef = fd.getSelectedFile().getAbsolutePath();
        }
    }//GEN-LAST:event_hedefButonActionPerformed

    private void BoxFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_BoxFocusGained
        ((JTextField) evt.getSource()).setBorder(BorderFactory.createLineBorder(Color.CYAN, 1, true));
    }//GEN-LAST:event_BoxFocusGained

    private void jTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusLost
        ((JTextField) evt.getSource()).setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
    }//GEN-LAST:event_jTextField1FocusLost

    private void clearListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearListActionPerformed
        dosyalar.clear();
    }//GEN-LAST:event_clearListActionPerformed

    private void enModeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_enModeFocusGained

    }//GEN-LAST:event_enModeFocusGained

    private void baslaButonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_baslaButonActionPerformed
        if (hedef != null && dosyaUzanti.getText() != null) {
            if (program_start) {
                for (Worker w : worker) {
                    w.setWait(true);
                }
                if (JOptionPane.showConfirmDialog(null,
                        "İşleminiz sona erecek", "Emin misiniz?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

                    stopProcess();
                } else {

                    for (Worker w : worker) {
                        w.setWait(false);
                    }
                }

            } else {
                program_start = true;

                Thread kontrolThread = new Thread(this);
                kontrolThread.setDaemon(true);

                kontrolThread.start();

            }
        } else {
            JOptionPane.showMessageDialog(this, "Boş alan bırakmayın", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_baslaButonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserInterface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton baslaButon;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton clearList;
    private javax.swing.JRadioButton deMode;
    private javax.swing.JPasswordField dosyaSifre;
    private javax.swing.JTextField dosyaUzanti;
    private javax.swing.JRadioButton enMode;
    private javax.swing.JButton hedefButon;
    private javax.swing.JList<File> islenecekDosyalar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField kayitDizin;
    private javax.swing.JButton kaynakButon;
    // End of variables declaration//GEN-END:variables

    public void setTheme(String name) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (name.equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public void clearAll() {
        isimler.clear();
        System.gc();
    }

    public void allLock() {
        this.baslaButon.setText("Durdur");
        this.hedefButon.setEnabled(false);
        this.kaynakButon.setEnabled(false);
        this.clearList.setEnabled(false);
        this.dosyaSifre.setEnabled(false);
        this.islenecekDosyalar.setEnabled(false);
        this.enMode.setEnabled(false);
        this.deMode.setEnabled(false);
        this.dosyaUzanti.setEnabled(false);
        this.kayitDizin.setEnabled(false);
    }

    public void allRelease() {
        this.baslaButon.setText("Başla");
        this.hedefButon.setEnabled(true);
        this.kaynakButon.setEnabled(true);
        this.clearList.setEnabled(true);
        this.dosyaSifre.setEnabled(true);
        this.islenecekDosyalar.setEnabled(true);
        this.enMode.setEnabled(true);
        this.deMode.setEnabled(true);
        this.dosyaUzanti.setEnabled(true);
        this.kayitDizin.setEnabled(true);
    }

    @Override
    public void run() {
        try {
            // Bu Thread, diğer Thread'leri kontrol edecek
            // aynı zamanda yaşayıp yaşamadıklarını kontrol edecek

            this.allLock();

            int threadNumber = (dosyalar.size() < 4) ? dosyalar.size() : 4;
            key = new AESkey((dosyaSifre.getText() == null) ? AESkey.random : dosyaSifre.getText());

            works.clear();
            for (int i = 0; i < dosyalar.size(); i++) {
                works.add(dosyalar.get(i).getAbsolutePath());
            }

            worker = new Worker[threadNumber];

            for (int i = 0; i < worker.length; i++) {
                worker[i] = new Worker(works, hedef, this.dosyaUzanti.getText(), key, (enMode.isSelected()) ? 0 : 1);
                worker[i].start();
            }

            // Buradan itibaren Thread'lerin yaşamı kontrol edilecek
            ArrayList dieThread = new ArrayList();
            int completed = 0;

            while (dieThread.size() < worker.length) {
                completed = 0;

                for (Worker t : worker) {
                    if (!t.isAlive()) {
                        if (!dieThread.contains(t)) {
                            dieThread.add(t);
                        }
                    }

                    completed += t.getCompleted();
                }

                if (dosyalar.size() < threadNumber) {
                    jProgressBar1.setValue(completed / worker.length);
                } else {
                    jProgressBar1.setValue(100 - works.size() * 100 / dosyalar.size() - 1);
                }

                Thread.sleep(100);
            }

            jProgressBar1.setValue(100);
            this.clearAll();
            this.allRelease();

        } catch (Exception ex) {
            this.allRelease();
            System.out.println("[-] " + ex);
        }

    }

}

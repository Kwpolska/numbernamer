package com.chriswarrick.numbernamer;

/* Number Namer v0.2.2
 * Copyright © 2016, Chris Warrick.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions, and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions, and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the author of this software nor the names of
 *    contributors to this software may be used to endorse or promote
 *    products derived from this software without specific prior written
 *    consent.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import javax.swing.JOptionPane;
import java.util.Random;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/** GUI for Number Namer */
public class NamerGUI extends javax.swing.JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    NumberNamer namer;
    Random r;
    private static String EINVNUMBER;
    /**
     * Creates new form NamerGUI
     */
    public NamerGUI() {
        initComponents();
        numberBox.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                run();
            }

            public void removeUpdate(DocumentEvent e) {
                run();
            }

            public void insertUpdate(DocumentEvent e) {
                run();
            }
        });

        namer = new EnglishNumberNamer(); // default namer
        r = new Random();
        EINVNUMBER = String.format("Invalid number (must be between %d and %d)", namer.MIN, namer.MAX);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unused")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        labelLanguage = new javax.swing.JLabel();
        languageBox = new javax.swing.JComboBox<>();
        labelNumber = new javax.swing.JLabel();
        numberBox = new javax.swing.JTextField();
        labelOutput = new javax.swing.JLabel();
        outBoxScroller = new javax.swing.JScrollPane();
        outBox = new javax.swing.JTextArea();
        randomButton = new javax.swing.JButton();
        runButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(500, 150));
        getContentPane().setLayout(new java.awt.GridLayout(4, 3));
        setTitle("Number Namer");

        labelLanguage.setText("Language");
        getContentPane().add(labelLanguage);

        languageBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "English", "Polish", "German" }));
        languageBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                languageBoxItemStateChanged(evt);
            }
        });
        getContentPane().add(languageBox);

        labelNumber.setText("Number");
        getContentPane().add(labelNumber);
        getContentPane().add(numberBox);

        labelOutput.setText("Output");
        getContentPane().add(labelOutput);

        outBox.setEditable(false);
        outBox.setColumns(20);
        outBox.setLineWrap(true);
        outBox.setRows(2);
        outBox.setWrapStyleWord(true);
        outBoxScroller.setViewportView(outBox);

        getContentPane().add(outBoxScroller);

        randomButton.setMnemonic('N');
        randomButton.setText("Random");
        randomButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                randomButtonActionPerformed(evt);
            }
        });
        getContentPane().add(randomButton);

        runButton.setFont(runButton.getFont().deriveFont(runButton.getFont().getStyle() | java.awt.Font.BOLD));
        runButton.setMnemonic('R');
        runButton.setText("Run");
        runButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runButtonActionPerformed(evt);
            }
        });
        getContentPane().add(runButton);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void runButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runButtonActionPerformed
        run();
    }//GEN-LAST:event_runButtonActionPerformed

    private void randomButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_randomButtonActionPerformed
        long l;
        do {
            l = r.nextLong();
        } while (l > namer.MAX || l < namer.MIN);
        numberBox.setText(String.valueOf(l));
        run();
    }//GEN-LAST:event_randomButtonActionPerformed

    private void languageBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_languageBoxItemStateChanged
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            String item = (String)evt.getItem();
            switch (item) {
            case "English":
                namer = new EnglishNumberNamer();
                break;
            case "Polish":
                namer = new PolishNumberNamer();
                break;
            case "German":
                namer = new GermanNumberNamer();
                break;
            }
       }
        run();
    }//GEN-LAST:event_languageBoxItemStateChanged

    private void run() {
        String inp = numberBox.getText().trim();
        if (inp.equals("")) {
            outBox.setText("");
            return;
        }
        try {
            String out = namer.name(Long.parseLong(inp));
            outBox.setText(out);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, EINVNUMBER, "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
            }

    }

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(NamerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NamerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NamerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NamerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NamerGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelLanguage;
    private javax.swing.JLabel labelNumber;
    private javax.swing.JLabel labelOutput;
    private javax.swing.JComboBox<String> languageBox;
    private javax.swing.JTextField numberBox;
    private javax.swing.JTextArea outBox;
    private javax.swing.JScrollPane outBoxScroller;
    private javax.swing.JButton randomButton;
    private javax.swing.JButton runButton;
    // End of variables declaration//GEN-END:variables
}

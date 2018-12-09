/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolao.view.apostador;

import bolao.controler.ControlTime;
import static bolao.controler.GetProperties.PROP;
import bolao.model.bean.Aposta;
import bolao.model.bean.User;
import bolao.model.dao.ApostaDAO;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author RAFAELDEOLIVEIRABAHI
 */
public class TelaListaAposta extends javax.swing.JFrame {

    DefaultTableModel modelo;

    /**
     * Creates new form TelaListaAposta
     *
     * @param identificador = Times do respectivo jogo
     * @param p = frame anterior para poder realizar atualização
     */
    public TelaListaAposta(String identificador, JFrame p) {
        this.parent = p;
        this.identificador = identificador;
        initComponents();

        String[] array = identificador.split(" x ");
        jLabelTimeA.setText(array[0]);
        jLabelTimeB.setText(array[1]);

        readJTableApostas();
    }

    public void setInformacoes() {
        readJTableApostas();
    }

    private void readJTableApostas() {
        modelo = (DefaultTableModel) jTableAposta.getModel();
        modelo.setNumRows(0);

        ApostaDAO apostadao = new ApostaDAO();

        List<Aposta> apostas = apostadao.readForDesc(ControlTime.parseIdentificador(jLabelTimeA.getText(), jLabelTimeB.getText()));

        Map<Aposta, Integer> combinacao = new HashMap<>();

        int contador = 0;
        if (!apostas.isEmpty()) {
            String placar = apostas.get(0).getPalpite();
            for (int i = 0; i < apostas.size(); i++) {

                if (apostas.get(i).getPalpite().equals(placar)) {
                    contador++;
                } else {
                    combinacao.put(apostas.get(i - 1), contador);
                    placar = apostas.get(i).getPalpite();
                    contador = 1;
                }

                if (i == apostas.size() - 1) {
                    combinacao.put(apostas.get(i), contador);
                }
            }
            int cont = 0;

            for (Aposta aposta : combinacao.keySet()) {
                modelo.addRow(new Object[]{
                    ++cont,
                    aposta.getPalpite(),
                    combinacao.get(aposta)
                });
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAposta = new javax.swing.JTable();
        jLabelTimeA = new javax.swing.JLabel();
        jLabelTimeB = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButtonNewAposta = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Palpites");
        setResizable(false);

        jPanel1.setBackground(null);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        jTableAposta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Palpite", "Apostadores"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableAposta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableApostaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableAposta);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 580, 185));

        jLabelTimeA.setBackground(new java.awt.Color(255, 255, 255));
        jLabelTimeA.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabelTimeA.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTimeA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTimeA.setText("TIME A");
        jPanel1.add(jLabelTimeA, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 83, 217, -1));

        jLabelTimeB.setBackground(new java.awt.Color(255, 255, 255));
        jLabelTimeB.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabelTimeB.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTimeB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTimeB.setText("TIME B");
        jPanel1.add(jLabelTimeB, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 80, 217, -1));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel3.setText("X");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, -1, -1));

        jButtonNewAposta.setText("Nova Aposta");
        jButtonNewAposta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewApostaActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonNewAposta, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 160, -1, -1));

        jLabel1.setIcon(new ImageIcon("view\\ImageFundo.jpg"));
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 660, 420));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTableApostaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableApostaMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            if (jTableAposta.getSelectedRow() != -1) {
                new TelaCriacaoAposta(identificador, this, parent, jTableAposta.getValueAt(jTableAposta.getSelectedRow(), 1).toString()).setVisible(true);
            }
        }
    }//GEN-LAST:event_jTableApostaMouseClicked

    private void jButtonNewApostaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewApostaActionPerformed
        // TODO add your handling code here:
        if (!User.getPessoa().isContaADM() || PROP.getProperty("ADM_APOSTA").equals(true)) {
            new TelaCriacaoAposta(identificador, this, parent).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Você não tem permissões para fazer uma aposta");
        }
    }//GEN-LAST:event_jButtonNewApostaActionPerformed

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
            java.util.logging.Logger.getLogger(TelaListaAposta.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaListaAposta.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaListaAposta.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaListaAposta.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaListaAposta("Vitoria x Sport", new javax.swing.JFrame()).setVisible(true);
            }
        });
    }

    private String identificador;
    private javax.swing.JFrame parent;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonNewAposta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelTimeA;
    private javax.swing.JLabel jLabelTimeB;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableAposta;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolao.view.adm;

import bolao.controler.ControlTime;
import bolao.model.bean.Aposta;
import bolao.model.bean.Jogo;
import bolao.model.bean.Pessoa;
import bolao.model.bean.User;
import bolao.model.dao.ApostaDAO;
import bolao.model.dao.JogoDAO;
import bolao.model.dao.PessoaDAO;
import bolao.view.TelaLogin;
import bolao.view.TelaMyAccount;
import bolao.view.apostador.TelaListaAposta;
import java.util.List;
import java.util.ListIterator;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author RAFAELDEOLIVEIRABAHI
 */
public class TelaPrincipalAdministrador extends javax.swing.JFrame {

    DefaultTableModel modelo;

    /**
     * Creates new form TelaPrincipal
     */
    public TelaPrincipalAdministrador() {
        initComponents();

        ControlTime.getInstance();

        modelo = (DefaultTableModel) jTableJogosAbertos.getModel();
        jTableJogosAbertos.setRowSorter(new TableRowSorter(modelo));
        ((DefaultTableCellRenderer) jTableJogosAbertos.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        readJTableJogos();
        readJTableInformacao();
    }

    private void readJTableJogos() {
        modelo = (DefaultTableModel) jTableJogosAbertos.getModel();
        modelo.setNumRows(0);
        JogoDAO jogodao = new JogoDAO();
        ApostaDAO apostadao = new ApostaDAO();

        List<Jogo> jogosAberto = jogodao.searchAll("Abertos", null);

        for (ListIterator<Jogo> iterator = jogosAberto.listIterator(); iterator.hasNext();) {
            Jogo jogo = iterator.next();
            List<Aposta> apostas = apostadao.readForDesc(jogo.getIdentificador(), "A definir");
            for (Aposta aposta : apostas) {
                if (User.getPessoa().getUsuario().equals(aposta.getUsuario())) {
                    iterator.remove();
                    break;
                }
            }
        }

        int cont = 0;

        for (Jogo jogo : jogosAberto) {
            modelo.addRow(new Object[]{
                ++cont,
                jogo.getIdentificador(),
                ControlTime.parseTime(jogo.getIdentificador()),
                jogo.getApostadores(),
                jogo.getData()
            });
        }
    }

    private void readJTableInformacao() {
        modelo = (DefaultTableModel) jTableRanking.getModel();
        modelo.setNumRows(0);

        PessoaDAO pessoadao = new PessoaDAO();
        List<Pessoa> pessoas = pessoadao.ranking();

        for (Pessoa pessoa : pessoas) {
            modelo.addRow(new Object[]{
                pessoa.getNome(),
                pessoa.getUsuario(),
                pessoa.getPontos()

            });
        }

        jLabelName.setText(User.getPessoa().getNome());

        jLabelPontuacao.setText("Ranking");

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableJogosAbertos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabelName = new javax.swing.JLabel();
        jLabelPontuacao = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableRanking = new javax.swing.JTable();
        imageLogo = new javax.swing.JLabel(new ImageIcon("view\\Logo.png"));
        ImageFundo = new javax.swing.JLabel(new ImageIcon("view\\ImageFundo.jpg"));
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        jTableJogosAbertos.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jTableJogosAbertos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Identificador", "Jogo", "Apostadores", "Data"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableJogosAbertos.setGridColor(new java.awt.Color(255, 255, 255));
        jTableJogosAbertos.getTableHeader().setReorderingAllowed(false);
        jTableJogosAbertos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableJogosAbertosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableJogosAbertos);
        if (jTableJogosAbertos.getColumnModel().getColumnCount() > 0) {
            jTableJogosAbertos.getColumnModel().getColumn(0).setMinWidth(50);
            jTableJogosAbertos.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTableJogosAbertos.getColumnModel().getColumn(0).setMaxWidth(50);
            jTableJogosAbertos.getColumnModel().getColumn(1).setMinWidth(100);
            jTableJogosAbertos.getColumnModel().getColumn(1).setPreferredWidth(100);
            jTableJogosAbertos.getColumnModel().getColumn(1).setMaxWidth(100);
            jTableJogosAbertos.getColumnModel().getColumn(2).setMinWidth(250);
            jTableJogosAbertos.getColumnModel().getColumn(2).setPreferredWidth(250);
            jTableJogosAbertos.getColumnModel().getColumn(2).setMaxWidth(250);
            jTableJogosAbertos.getColumnModel().getColumn(3).setMinWidth(100);
            jTableJogosAbertos.getColumnModel().getColumn(3).setPreferredWidth(100);
            jTableJogosAbertos.getColumnModel().getColumn(3).setMaxWidth(100);
        }

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelName.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabelName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelName.setText("<Nome do Usuario>");

        jLabelPontuacao.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabelPontuacao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelPontuacao.setText("<Pontuação>");

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));

        jTableRanking.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nome", "Usuario", "Pontuação"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableRanking.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTableRanking);
        if (jTableRanking.getColumnModel().getColumnCount() > 0) {
            jTableRanking.getColumnModel().getColumn(0).setMinWidth(100);
            jTableRanking.getColumnModel().getColumn(0).setPreferredWidth(100);
            jTableRanking.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabelPontuacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelName)
                .addGap(48, 48, 48)
                .addComponent(jLabelPontuacao, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(340, 340, 340)
                        .addComponent(imageLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ImageFundo, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(221, 221, 221)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(imageLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(ImageFundo, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jMenuBar1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jMenu1.setText("Jogos");
        jMenu1.setIcon(new ImageIcon("view\\aposta.png"));
        jMenu1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jMenuItem1.setText("Gerar Partidas");
        jMenuItem1.setIcon(new ImageIcon("view\\money.png"));
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Gerar Resultados");
        jMenuItem2.setIcon(new ImageIcon("view\\result.png"));
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Sistema");
        jMenu2.setIcon(new ImageIcon("view\\system.png"));
        jMenu2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jMenuItem3.setText("Usuarios");
        jMenuItem3.setIcon(new ImageIcon("view\\users.png"));
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem4.setText("Permissões");
        jMenuItem4.setIcon(new ImageIcon("view\\options.png"));
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuBar1.add(jMenu2);

        jMenu5.setText("Ajuda");
        jMenu5.setIcon(new ImageIcon("view\\information.png"));
        jMenu5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jMenuItem5.setText("Minha conta");
        jMenuItem5.setIcon(new ImageIcon("view\\myAccount.png"));
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem5);

        jMenuItem6.setText("Sair");
        jMenuItem6.setIcon(new ImageIcon("view\\door_out.png"));
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem6);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTableJogosAbertosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableJogosAbertosMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            if (jTableJogosAbertos.getSelectedRow() != -1) {
                new TelaListaAposta(jTableJogosAbertos.getValueAt(jTableJogosAbertos.getSelectedRow(), 2).toString(), this).setVisible(true);
            }
        }
    }//GEN-LAST:event_jTableJogosAbertosMouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        new TelaGerarPartidas(this).setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        User.deslogarUser();
        this.dispose();
        new TelaLogin(this, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        new TelaMyAccount(this).setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        new TelaPermissao().setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        new TelaGerarResultado(this).setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        new TelaGerenciarUser().setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    public void setInformacoes() {
        readJTableJogos();
        readJTableInformacao();
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
            java.util.logging.Logger.getLogger(TelaPrincipalAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipalAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipalAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipalAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipalAdministrador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ImageFundo;
    private javax.swing.JLabel imageLogo;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelPontuacao;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableJogosAbertos;
    private javax.swing.JTable jTableRanking;
    // End of variables declaration//GEN-END:variables
}

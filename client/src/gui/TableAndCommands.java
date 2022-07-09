/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import Client.ClientTools;
import ForCity.City;
import Tools.Message;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;

/**
 *
 * @author koshe
 */
public class TableAndCommands extends javax.swing.JFrame {
    /**
     * Creates new form TableAndCommands
     * 
     */
    private final ClientTools ct;
    private String username;
    private String filter = "no filter";
    
    public TableAndCommands(ClientTools ct, String username) throws IOException, ClassNotFoundException {
        this.ct=ct;
        this.username=username;
        setTitle("City collection - user: " + username);
        ImageIcon icon = new ImageIcon(getClass().getResource("icons8_city_block_40px.png"));
        this.setIconImage(icon.getImage());
        initComponents();
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("gui/local");
        String header1 = bundle.getString("Id");
        String header2 = bundle.getString("Name");
        String header3 = bundle.getString("Coordinate_X");
        String header4 = bundle.getString("Coordinate_Y");
        String header5 = bundle.getString("CreationDate");
        String header6 = bundle.getString("Area");
        String header7 = bundle.getString("Population");
        String header8 = bundle.getString("metersAboveSeaLevel");
        String header9 = bundle.getString("establishmentDate");
        String header10 = bundle.getString("telephoneCode");
        String header11 = bundle.getString("government");
        String header12 = bundle.getString("governor");
        String header13 = bundle.getString("login");
        
        jTable1.setModel(new DefaultTableModel(
                new Object[][]{

                }, 
                new String[]{header1, header2, header3, header4, header5, header6, header7, header8, header9, header10, header11, header12, header13}
        ){
                Class[] types = new Class []{
                Long.class, String.class, Long.class, Long.class,
                String.class, Long.class, Long.class, Double.class,
                String.class, Long.class, String.class, Long.class, String.class};
                @Override public Class getColumnClass(int columnIndex){
                    return types [columnIndex];
                }
        });
        
        Runnable task = () -> {
            while (true) {
                try {
                    DefaultTableModel tmp = (DefaultTableModel) jTable1.getModel();  
                    //сортировка
                    RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tmp);
                    jTable1.setRowSorter(sorter);
                    //конец сортировки
                    
                    tmp.setRowCount(0);
                    Message ms = new Message("GetCollection");
                    //System.out.println("AAAAAAAA");
                    List<City> cc  = (List<City>) ct.send(ms);
                    //System.out.println("BBBBBBBBBB");
                    Object[][] data = new Object[][]{};
                    //System.out.println(cc.getCollection().size());
                    for (City city : cc) {
                        Object[] row = new Object[13];
                        //System.out.println(city.getInfo());
                        row[0]=(Object)city.getId();
                        row[1]=city.getName();
                        row[2]=city.getCoordinates().getX();
                        row[3]=city.getCoordinates().getY();
                        //add creation date
                        LocalDate date = city.getCreationDate();
                        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MMMM uuuu", Locale.getDefault());
                        String output = dtf.format(date);
                        row[4]=output;
                        
                        row[5]=city.getAreaSize();
                        row[6]=city.getPopulation();
                        row[7]=city.getMetersAboveSeaLevel();
                        
                        //add establishment date
                        LocalDate date1 = city.getEstablishmentDate();
                        String output1 = dtf.format(date1);
                        row[8]=output1;
                        
                        row[9]=city.getTelephoneCode();
                        //here write enum to string
                        row[10]=city.getGovernment().toString();
                        
                        row[11]=city.getGovernor().getHeight();
                        row[12]=city.getLogin();
                        
                        /*for (Object c: row){
                            System.out.println(c);
                        }*/
                        if (filter.equals("no filter")){
                            tmp.addRow(row);
                        }else{
                            if (filter.equals(row[10])){
                                tmp.addRow(row);
                            }
                        }
                        
                    }
                } catch (IOException ex) {
                    Logger.getLogger(TableAndCommands.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TableAndCommands.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    sleep(5000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(TableAndCommands.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

        Thread t = new Thread(task);
        t.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new JTable();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jTable1.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Name", "Coordinate X", "Coordinate Y", "Creation Date", "Area", "Population", "Meters Above Sea Level", "Establishment Date", "Telephone Code", "Government", "Governor's height", "Login"
            }
        ));
        jTable1.setShowGrid(false);
        jScrollPane1.setViewportView(jTable1);

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("gui/local"); // NOI18N
        jButton2.setText(bundle.getString("Visualization")); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText(bundle.getString("Filter by government")); // NOI18N

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "THEOCRACY", "ARISTOCRACY", "GERONTOCRACY", "no filter" }));
        jComboBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox1MouseClicked(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 319, Short.MAX_VALUE)
                .addComponent(jButton2))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 681, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 559, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 21, Short.MAX_VALUE)))
        );

        jMenu1.setText(bundle.getString("Commands")); // NOI18N
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenuItem1.setText(bundle.getString("help")); // NOI18N
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText(bundle.getString("average phone code")); // NOI18N
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText(bundle.getString("Info")); // NOI18N
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setText(bundle.getString("remove_by_id")); // NOI18N
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem5.setText(bundle.getString("Add")); // NOI18N
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuItem6.setText(bundle.getString("Update")); // NOI18N
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
        this.setVisible(true);
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // HELP
        try {
            String s = (String) ct.send(new Message("help", username));
            JOptionPane.showMessageDialog(this, s);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(TableAndCommands.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // AVERAGE PHONE_CODE, хз как локализовать, мб удалю
        try {
            String s = (String) ct.send(new Message("average_of_telephone_code", username));
            java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("gui/local");
            String s1 = bundle.getString("avgphonecode");
            JOptionPane.showMessageDialog(this, s1+s);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(TableAndCommands.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        try {
            String s = (String) ct.send(new Message("info", username));
            JOptionPane.showMessageDialog(this, s);
        } catch (IOException ex) {
            Logger.getLogger(TableAndCommands.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TableAndCommands.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        new IdForRemove(ct,username).setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Message ms = new Message("GetCollection");
        System.out.println("AAAAAAAA");
        try {
            List<City> cc  = (List<City>) ct.send(ms);
            new Vis(cc, ct, username).setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(TableAndCommands.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TableAndCommands.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        //addcommand
        new Add(ct,username).setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        new Update(ct, username).setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        Login lg = new Login(ct);
        lg.pack();
        lg.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    private void jComboBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox1MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jComboBox1MouseClicked

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        filter = (String) jComboBox1.getSelectedItem();
        //java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("gui/local");
        //JOptionPane.showMessageDialog(this, bundle.getString("cadded"),null, JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jComboBox1ActionPerformed
    
    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private JTable jTable1;
    // End of variables declaration//GEN-END:variables
}

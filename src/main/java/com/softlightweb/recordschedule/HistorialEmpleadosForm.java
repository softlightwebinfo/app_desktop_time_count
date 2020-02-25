/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softlightweb.recordschedule;

import com.softlightweb.recordschedule.api.mongo.MongoExport;
import com.softlightweb.recordschedule.api.pojo.Csv;
import com.softlightweb.recordschedule.api.pojo.User;
import com.softlightweb.recordschedule.crud.UserCRUD;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author rafaelgonzalezmunoz
 */
public class HistorialEmpleadosForm extends javax.swing.JFrame {

    private final DefaultTableModel modelo;
    private final String[] columnas = {"DNI", "Entrada", "Salida", "Horas"};
    ArrayList<Csv> datosGlobal = new ArrayList<>();

    public HistorialEmpleadosForm() {
        initComponents();
        modelo = new DefaultTableModel();

        modelo.setColumnIdentifiers(columnas);
        tbl_empleados.setModel(modelo);

        JTableHeader th = tbl_empleados.getTableHeader();
        th.setPreferredSize(new Dimension(0, 40));
        this.agregarDatos();
        jcb_mes.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    removeDatos();
                    agregarDatos(transformarMes((String) e.getItem()));
                }               
            }
        });
    }

    public int transformarMes(String mes) {
        switch (mes) {
            case "Enero": {
                return 1;
            }
            case "Febrero": {
                return 2;
            }
            case "Marzo": {
                return 3;
            }
            case "Abril": {
                return 4;
            }
            case "Mayo": {
                return 5;
            }
            case "Junio": {
                return 6;
            }
            case "Julio": {
                return 7;
            }
            case "Agosto": {
                return 8;
            }
            case "Septiembre": {
                return 9;
            }
            case "Octubre": {
                return 10;
            }
            case "Noviembre": {
                return 11;
            }
            case "Diciembre": {
                return 12;
            }
            default: {
                return 1;
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_empleados = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jcb_mes = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Listado de fichar");

        tbl_empleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nombre", "DNI", "Telefono", "Registro h/mes"
            }
        ));
        tbl_empleados.setShowGrid(true);
        tbl_empleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_empleadosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_empleados);

        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Guardar registros");
        jButton2.setToolTipText("");
        jButton2.setActionCommand("");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jcb_mes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));
        jcb_mes.setMinimumSize(new java.awt.Dimension(96, 40));
        jcb_mes.setPreferredSize(new java.awt.Dimension(96, 40));
        jcb_mes.setSize(new java.awt.Dimension(100, 40));
        jcb_mes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcb_mesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jcb_mes, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jcb_mes, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tbl_empleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_empleadosMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_tbl_empleadosMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            // TODO add your handling code here:
            MongoExport.registrosUsuarioCSV(datosGlobal);
            JOptionPane.showMessageDialog(rootPane, "Ok, se ha guardado correctamente en el pendrive");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, "Error, en la ruta del pendrive");
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jcb_mesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcb_mesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcb_mesActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jcb_mes;
    private javax.swing.JTable tbl_empleados;
    // End of variables declaration//GEN-END:variables
    public void removeDatos() {
        if (modelo.getRowCount() > 0) {
            for (int i = modelo.getRowCount() - 1; i > -1; i--) {
                modelo.removeRow(i);
            }
        }
    }

    public void agregarDatos() {
        this.agregarDatos(0);
    }

    public void agregarDatos(int monthParam) {
        this.removeDatos();
        this.datosGlobal.clear();
        ArrayList<Document> listaEmpleados = UserCRUD.listaEmpleados();
        int empleadosSize = listaEmpleados.size();
        for (Integer i = 0; i < empleadosSize; i++) {
            ArrayList<Document> registros = (ArrayList<Document>) listaEmpleados.get(i).get("registros");
            String nombre = listaEmpleados.get(i).get("nombre").toString();
            String dni = listaEmpleados.get(i).get("dni").toString();
            String email = listaEmpleados.get(i).get("email").toString();
            String telefono = listaEmpleados.get(i).get("telefono").toString();

            // Agregamos los datos a la tabla           
            int registrosSize = registros.size();
            for (Integer x = 0; x < registrosSize; x++) {
                Date entrada = (Date) registros.get(i).get("entrada");
                Date salida = (Date) registros.get(i).get("salida");
                String diferencia = (String) registros.get(i).get("diferencia");
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm");
                String strDate = formatter.format(entrada);

                Boolean exist = false;
                SimpleDateFormat ftEntradaYear = new SimpleDateFormat("yyyy");
                SimpleDateFormat ftEntradaMonth = new SimpleDateFormat("MM");
                String yearSalida = ftEntradaYear.format(entrada);
                String MonthSalida = ftEntradaMonth.format(entrada);

                SimpleDateFormat y = new SimpleDateFormat("yyyy");
                SimpleDateFormat m = new SimpleDateFormat("MM");
                Date now = new Date();
                now.setMonth(monthParam - 1);
                String year = y.format(now);
                String month = m.format(now);
                if (monthParam == 0) {
                    exist = true;
                }
                if (year.equals(yearSalida) && month.equals(MonthSalida)) {
                    exist = true;
                }
                if (exist) {
                    String strSalida = "";
                    if (salida != null) {
                        SimpleDateFormat formatterSalida = new SimpleDateFormat("dd/MM/yyyy hh:mm");
                        strSalida = formatterSalida.format(salida);
                    }
                    // Creamos un nuevo renglon para la tabla
                    String[] datos = {
                        dni,
                        strDate,
                        strSalida,
                        diferencia,};
                    // Agregamos los datos a la tabla

                    Csv csc = new Csv();
                    csc.dni = dni;
                    csc.entrada = strDate;
                    csc.salida = strSalida;
                    csc.horas = diferencia;
                    datosGlobal.add(csc);
                    modelo.addRow(datos);
                }
            }
        }
    }
}

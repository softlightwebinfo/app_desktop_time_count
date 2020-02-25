/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softlightweb.recordschedule;

import com.softlightweb.recordschedule.api.pojo.User;
import com.softlightweb.recordschedule.crud.UserCRUD;
import java.awt.Dimension;
import java.util.ArrayList;
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
public class ListadoEmpleados extends javax.swing.JFrame {

    private final DefaultTableModel modelo;
    private final String[] columnas = {"Nombre", "Dni", "Email", "Telefono"};

    public ListadoEmpleados() {
        initComponents();
        modelo = new DefaultTableModel();

        modelo.setColumnIdentifiers(columnas);
        tbl_empleados.setModel(modelo);

        JTableHeader th = tbl_empleados.getTableHeader();
        th.setPreferredSize(new Dimension(0, 40));
        this.agregarDatos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_empleados = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Listado de empleados");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tbl_empleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_empleadosMouseClicked
        // TODO add your handling code here:
        String dni = tbl_empleados.getValueAt(tbl_empleados.getSelectedRow(), 1).toString();
        NewEmpleadoForm newEmpleado = new NewEmpleadoForm();
        User user = UserCRUD.datosUsuario(dni);
        newEmpleado.txt_nombre.setText(user.getNombre());
        newEmpleado.txt_apellidos.setText(user.getApellidos());
        newEmpleado.txt_email.setText(user.getEmail());
        newEmpleado.txt_telefono.setText(user.getTelefono());
        newEmpleado.txt_dni.setText(user.getDni());
        if (user.getPassword().length() > 0) {
            newEmpleado.lbl_password.setText("Contraseña:(el usuario es admin)");
            newEmpleado.txt_password.setText(user.getPassword());
        }
        newEmpleado.setTitle("Modificar empleado");
        newEmpleado.isEdit = true;
        newEmpleado.userIdEdit = user.getId();
        newEmpleado.setLocationRelativeTo(null);
        newEmpleado.listadoEmpleados = this;
        newEmpleado.btn_registros.setVisible(true);
        newEmpleado.setVisible(true);
    }//GEN-LAST:event_tbl_empleadosMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
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
        this.removeDatos();
        ArrayList<Document> listaEmpleados = UserCRUD.listaEmpleados();
        for (Integer i = 0; i < listaEmpleados.size(); i++) {
            String mensaje = i + " UNIDADES";
            // Creamos un nuevo renglon para la tabla
            String[] datos = {
                listaEmpleados.get(i).get("nombre").toString(),
                listaEmpleados.get(i).get("dni").toString(),
                listaEmpleados.get(i).get("email").toString(),
                listaEmpleados.get(i).get("telefono").toString()};
            // Agregamos los datos a la tabla
            modelo.addRow(datos);
        }
    }
}

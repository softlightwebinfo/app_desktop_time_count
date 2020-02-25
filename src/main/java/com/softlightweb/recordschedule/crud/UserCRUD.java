/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softlightweb.recordschedule.crud;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import com.softlightweb.recordschedule.api.mongo.MongoConnection;
import com.softlightweb.recordschedule.api.pojo.User;
import com.softlightweb.recordschedule.api.pojo.UserRegistro;
import com.softlightweb.recordschedule.api.pojo.tipoRegistro;
import java.util.ArrayList;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * @author toni
 */
public class UserCRUD {

    /**
     * Insert User in MongoDB
     *
     * @param usuario
     */
    public static boolean crearUsuario(User usuario) {
        try {
            MongoConnection connection = new MongoConnection();
            connection.getConnection();
            MongoDatabase db = connection.mongoDB();
            db.getCollection("datosPersonales").insertOne(
                    new Document()
                            .append("nombre", usuario.getNombre())
                            .append("apellidos", usuario.getApellidos())
                            .append("password", usuario.getPassword())
                            .append("email", usuario.getEmail())
                            .append("telefono", usuario.getTelefono())
                            .append("dni", usuario.getDni())
                            .append("ultimoRegistro", "")
                            .append("registros", usuario.getRegistros())
            );
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static Document logUsuario(String dni, String password) {
        MongoCollection coll = MongoConnection.getCollection();
        Document myDoc = (Document) coll.find(and(eq("dni", dni), eq("password", password))).first();
        if (myDoc == null) {
            return null;
        } else {
            return myDoc;
        }
    }

    public static void setUltimoRegistro(tipoRegistro tipo, String dniBuscar) {

        MongoCollection coll = MongoConnection.getCollection();

        coll.updateOne(eq("dni", dniBuscar),
                new Document("$set", new Document("ultimoRegistro", tipo.toString())));
    }

    public static boolean editarUsuario(User userToUpdate) {
        try {
            MongoCollection coll = MongoConnection.getCollection();
            ObjectId idUsuario = userToUpdate.getId();
            Document myDoc = (Document) coll.find(eq("_id", idUsuario)).first();
            myDoc.put("dni", userToUpdate.getDni());
            myDoc.put("nombre", userToUpdate.getNombre());
            myDoc.put("apellidos", userToUpdate.getApellidos());
            myDoc.put("password", userToUpdate.getPassword());
            myDoc.put("email", userToUpdate.getEmail());
            myDoc.put("telefono", userToUpdate.getTelefono());

            coll.findOneAndReplace(eq("_id", idUsuario), myDoc);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static User datosUsuario(String dni) {

        MongoCollection coll = MongoConnection.getCollection();
        Document usuario = (Document) coll.find(eq("dni", dni)).first();
        User userResp = new User();
        userResp.setNombre(usuario.get("nombre").toString());
        ObjectId id = (ObjectId) usuario.get("_id");
        userResp.setId(id);
        userResp.setApellidos(usuario.get("apellidos").toString());
        userResp.setTelefono(usuario.get("telefono").toString());
        userResp.setEmail(usuario.get("email").toString());
        userResp.setPassword(usuario.get("password").toString());
        userResp.setDni(usuario.get("dni").toString());
        userResp.setRegistros((ArrayList<UserRegistro>) usuario.get("registros"));

        return userResp;
    }

    public static ArrayList<Document> listaEmpleados() {

        MongoCollection coll = MongoConnection.getCollection();

        MongoCursor<Document> cursor = coll.find().iterator();
        ArrayList<Document> usuarios = new ArrayList<>();

        try {
            while (cursor.hasNext()) {
                usuarios.add(cursor.next());
            }
        } finally {
            cursor.close();
        }

        return usuarios;
    }

    public static Object registrosEmpleado(ObjectId id) {

        MongoCollection coll = MongoConnection.getCollection();
           
        Object usuario = coll.find(eq("_id", id)).first();
        return usuario;
    }

    public static void datosUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

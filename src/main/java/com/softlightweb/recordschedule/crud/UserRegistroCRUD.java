/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softlightweb.recordschedule.crud;

import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;
import com.softlightweb.recordschedule.api.mongo.MongoConnection;
import com.mongodb.client.model.Updates;
import com.softlightweb.recordschedule.api.pojo.User;
import com.softlightweb.recordschedule.api.pojo.tipoRegistro;
import java.time.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author toni
 */
public class UserRegistroCRUD {

    /**
     * Insertar un registro del tipo ENTRADA/SALIDA de horarios
     *
     * @param dniBuscar
     * @return
     */
    public static User addRegistro(String dniBuscar) {
        
        /*Conexión a Mongo con su base de datos*/
        MongoCollection coll = MongoConnection.getCollection();
        
        /*Buscamos el objeto del usuario por su dni*/
        Document myDoc = (Document) coll.find(eq("dni", dniBuscar)).first();
        
        /*Listado de registros del usuario*/
        List<Document> registrosDelUsuario = (List<Document>) myDoc.get("registros");
        
        /*Tipo del último registro insertado en base de datos*/
        String ultimoRegistro = myDoc.get("ultimoRegistro").toString();
        
        /*Usuario que vamos a devolver como respuesta al método*/
        User usuarioResp = new User();
        
        // Convert Instant to Date.
        LocalDateTime now =  LocalDateTime.now(ZoneOffset.UTC);
       
        /*Id que le vamos a asignar al registro de ENTRADA/SALIDA del usuario*/
        String idRegistro = new ObjectId().toString();
        
        /*Filtro que usaremos para filtrar en base de datos*/
        Bson filter = eq("dni", dniBuscar);
        
        Document registro = new Document();

        if (registrosDelUsuario.isEmpty() || ultimoRegistro.equalsIgnoreCase("salida")) {

            UserCRUD.setUltimoRegistro(tipoRegistro.ENTRADA, dniBuscar);
            registro.put("id", idRegistro);
            registro.put("entrada", now);
            registrosDelUsuario.add(registro);
            coll.updateOne(filter, Updates.push("registros", registro));
            usuarioResp.setUltimoRegistro(tipoRegistro.ENTRADA);

        } else {
            
            int index = registrosDelUsuario.size() - 1;
            Document ultimoRegistroBaseDatos = registrosDelUsuario.get(index);
            Date fechaMongo = (Date) ultimoRegistroBaseDatos.get("entrada");
            long longNow = now.toInstant(ZoneOffset.UTC).toEpochMilli();
            long longEntrada = fechaMongo.getTime();
            long diff = longNow-longEntrada;
            String sTimeDiff = String.
                    format("%02d:%02d", 
                        TimeUnit.MILLISECONDS.toHours(diff),
                        TimeUnit.MILLISECONDS.toMinutes(diff) -  
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(diff)));
            ultimoRegistroBaseDatos.put("salida", now);
            ultimoRegistroBaseDatos.put("diferencia", sTimeDiff);
            myDoc.put("registros", registrosDelUsuario);
            myDoc.put("ultimoRegistro", tipoRegistro.SALIDA.toString());
            coll.findOneAndReplace(filter, myDoc);
            usuarioResp.setUltimoRegistro(tipoRegistro.SALIDA);
        }

        usuarioResp.setApellidos(myDoc.get("apellidos").toString());
        usuarioResp.setNombre(myDoc.get("nombre").toString());
        return usuarioResp;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softlightweb.recordschedule;

import static com.softlightweb.recordschedule.AdminForm.user;
import com.softlightweb.recordschedule.api.mongo.MongoExport;
import com.softlightweb.recordschedule.api.pojo.Csv;
import com.softlightweb.recordschedule.api.pojo.User;
import com.softlightweb.recordschedule.crud.UserCRUD;
import com.softlightweb.recordschedule.crud.UserRegistroCRUD;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author rafaelgonzalezmunoz
 */
public class Application {

    public static void main(String... args) throws IOException {
        Csv pruebaCsv = new Csv();
        pruebaCsv.dni = "43222337P";
        pruebaCsv.entrada = "hoooolita";
        pruebaCsv.salida = "adiosito";
        pruebaCsv.horas = "AFU que panza";
        
        List<Csv> lista = new ArrayList<Csv>();
        
        int i;
        
        for(i = 0; i<4 ; i++){
            lista.add(pruebaCsv);
        };
        
        MongoExport.registrosUsuarioCSV(lista);
        
        HomeForm home = new HomeForm();
        home.setLocationRelativeTo(null);
        home.setVisible(true);
        
    }
}

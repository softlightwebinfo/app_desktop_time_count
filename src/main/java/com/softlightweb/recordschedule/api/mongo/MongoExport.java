/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softlightweb.recordschedule.api.mongo;

import com.softlightweb.recordschedule.api.pojo.Csv;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author toni
 */
public class MongoExport {

    private static final char DEFAULT_SEPARATOR = ',';

    public static void registrosUsuarioCSV(List<Csv> lista) throws IOException {

        FileWriter csv = new FileWriter("./testeando.csv");
        csv.append("Nº DNI");
        csv.append(DEFAULT_SEPARATOR);
        csv.append("ENTRADA");
        csv.append(DEFAULT_SEPARATOR);
        csv.append("SALIDA");
        csv.append(DEFAULT_SEPARATOR);
        csv.append("HORAS");
        csv.append("\n");
        
        for (Csv registro : lista) {
            csv.append(registro.dni);
            csv.append(DEFAULT_SEPARATOR);
            csv.append(registro.entrada);
            csv.append(DEFAULT_SEPARATOR);
            csv.append(registro.salida);
            csv.append(DEFAULT_SEPARATOR);
            csv.append(registro.horas);
            csv.append("\n");
        }

        csv.flush();
        csv.close();

    }

}

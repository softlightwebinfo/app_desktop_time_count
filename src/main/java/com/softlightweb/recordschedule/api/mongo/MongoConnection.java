package com.softlightweb.recordschedule.api.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


public class MongoConnection {

    /**
     * Test of Java basic MongoDb connection
     */
    private MongoDatabase db;

    public MongoConnection(){}

    public void getConnection(){
        MongoClient client = new MongoClient("localhost", 27017);
        this.db = client.getDatabase("trabajadores");
    }

    public MongoDatabase mongoDB(){
        return this.db;
    }
    
    public static MongoCollection getCollection(){
    
        MongoConnection connection = new MongoConnection();
        connection.getConnection();
        MongoDatabase db = connection.mongoDB();
        MongoCollection coll = db.getCollection("datosPersonales");
        
        return coll;        
    }
}

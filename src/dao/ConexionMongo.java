package dao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class ConexionMongo {
/*Aqui se crea la conexion para la BD*/
    
    
    private static final String URI = "mongodb://localhost:27017";

    private static MongoClient mongoClient;

    public static MongoDatabase conectar() {

        if (mongoClient == null) {mongoClient = MongoClients.create(URI);

        }

        return mongoClient.getDatabase("inventario_db");
    }

}
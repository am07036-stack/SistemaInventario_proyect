package dao;

import com.mongodb.client.MongoDatabase;

public class PruebaConexion {

    public static void main(String[] args) {

        try {

            MongoDatabase db = ConexionMongo.conectar();

            System.out.println("Conectado a la base de datos: "
                    + db.getName());

        } catch (Exception e) {

            System.out.println(
                    "Error: "
                    + e.getMessage());

        }

    }

}

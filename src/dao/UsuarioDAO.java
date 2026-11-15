package dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import modelo.Usuario;
import org.bson.Document;
import util.PasswordUtil;

public class UsuarioDAO {

    private MongoCollection<Document> coleccion;

    public UsuarioDAO() {

        MongoDatabase db =
                ConexionMongo.conectar();

        coleccion =
                db.getCollection("usuarios");
    }

    public void guardarUsuario(
        Usuario usuario) {

    Document doc = new Document();

    doc.append("id",
            usuario.getId());

    doc.append("usuario",
            usuario.getUsuario());

    doc.append("correo",
            usuario.getCorreo());

    doc.append("passwordHash",
            usuario.getPasswordHash());

    coleccion.insertOne(doc);
}
public Usuario buscarUsuario(String usuario) {
    Document doc = coleccion.find(
            new Document("usuario", usuario))
            .first();
    if (doc == null) {
        return null;
    }
    Usuario usuarioBD = new Usuario();
    usuarioBD.setId(doc.getString("id"));
    usuarioBD.setUsuario(doc.getString("usuario"));
    usuarioBD.setCorreo(doc.getString("correo"));
    usuarioBD.setPasswordHash(
            doc.getString("passwordHash"));
    return usuarioBD;
}
public boolean validarLogin(
        String usuario,
        String password) {

    Usuario usuarioBD = buscarUsuario(usuario);
    if (usuarioBD == null) {
        return false;
    }
    String hashIngresado =
            PasswordUtil.generarHash(password);
    return usuarioBD.getPasswordHash()
            .equals(hashIngresado);
}

}

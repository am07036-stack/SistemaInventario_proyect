package dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import modelo.Usuario;
import org.bson.Document;
import util.PasswordUtil;

public class UsuarioDAO {

    private MongoCollection<Document> coleccion;

    public UsuarioDAO() {

        MongoDatabase db = ConexionMongo.conectar();

        coleccion = db.getCollection("usuarios");
    }

    public void guardarUsuario( Usuario usuario) {

    Document doc = new Document();

    doc.append("id",
            usuario.getId());

    doc.append("usuario",
            usuario.getUsuario());

    doc.append("correo",
            usuario.getCorreo());

    doc.append("passwordHash",
            usuario.getPasswordHash());
    doc.append("rol",
        usuario.getRol());

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
    usuarioBD.setPasswordHash(doc.getString("passwordHash"));
    usuarioBD.setRol(doc.getString("rol"));
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

public java.util.List<Usuario> listarUsuarios() {
    java.util.List<Usuario> usuarios =
            new java.util.ArrayList<>();
    for (Document doc : coleccion.find()) {
        Usuario usuario = new Usuario();
        usuario.setId(
                doc.getString("id"));
        usuario.setUsuario(
                doc.getString("usuario"));
        usuario.setCorreo(
                doc.getString("correo"));
        usuario.setPasswordHash(
                doc.getString("passwordHash"));
        usuario.setRol(
                doc.getString("rol"));
        usuarios.add(usuario);
    }

    return usuarios;
}
public boolean existeUsuario(String usuario) {

    Document doc = coleccion.find(new Document("usuario", usuario)).first();
    return doc != null;
}

}

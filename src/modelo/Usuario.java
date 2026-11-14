
package modelo;


public class Usuario {
private String id;
private String usuario;
private String correo;
private String passwordHash;

    public Usuario() {
    }

    public Usuario(String id, String usuario, String correo, String passwordHash) {
        this.id = id;
        this.usuario = usuario;
        this.correo = correo;
        this.passwordHash = passwordHash;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }



}

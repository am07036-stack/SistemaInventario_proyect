package principal;

import dao.UsuarioDAO;
import modelo.Usuario;
import util.PasswordUtil;

public class CrearAdminHash {

    public static void main(String[] args) {

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        Usuario admin = new Usuario(
                        "1",
                        "admin",
                        "admin@inventario.com",
                        PasswordUtil.generarHash("admin123"),"ADMINISTRADOR");
        usuarioDAO.guardarUsuario(admin);

        Usuario operador = new Usuario(
                "2",
                "operador",
                "operador@inventario.com",
                PasswordUtil.generarHash("operador123"),
                "OPERADOR" );

        usuarioDAO.guardarUsuario(operador);

        System.out.println("Administrador y Operador creado");
    }
}
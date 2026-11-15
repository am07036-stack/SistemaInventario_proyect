package principal;

import dao.UsuarioDAO;
import modelo.Usuario;
import util.PasswordUtil;

public class CrearAdminHash {

    public static void main(String[] args) {

        UsuarioDAO usuarioDAO =
                new UsuarioDAO();

        Usuario admin =
                new Usuario(
                        "1",
                        "admin",
                        "admin@inventario.com",
                        PasswordUtil.generarHash(
                                "admin123")
                );

        usuarioDAO.guardarUsuario(admin);

        System.out.println(
                "Administrador creado");
    }
}
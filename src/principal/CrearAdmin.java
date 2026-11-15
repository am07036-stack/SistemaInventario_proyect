package principal;

import dao.UsuarioDAO;
import modelo.Usuario;

public class CrearAdmin {

    public static void main(String[] args) {

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        Usuario admin = new Usuario( "1",
                "admin",
                "admin@inventario.com",
                "admin123","ADMINISTRADOR"
        );

        usuarioDAO.guardarUsuario(admin);

        System.out.println("Usuario administrador creado");

    }

}
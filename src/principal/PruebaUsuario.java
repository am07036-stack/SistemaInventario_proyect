package principal;

import dao.UsuarioDAO;
import modelo.Usuario;

public class PruebaUsuario {

    public static void main(String[] args) {

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        Usuario usuario =
                usuarioDAO.buscarUsuario("admin");

        if (usuario != null) {

            System.out.println(
                    "Usuario encontrado: "
                    + usuario.getUsuario());

            System.out.println(
                    "Correo: "
                    + usuario.getCorreo());

            System.out.println(
                    "PasswordHash: "
                    + usuario.getPasswordHash());

        } else {

            System.out.println(
                    "Usuario no encontrado");
        }
    }
}
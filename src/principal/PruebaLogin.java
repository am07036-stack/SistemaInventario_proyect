package principal;

import dao.UsuarioDAO;

public class PruebaLogin {

    public static void main(String[] args) {

        UsuarioDAO usuarioDAO =
                new UsuarioDAO();

        boolean acceso =
                usuarioDAO.validarLogin(
                        "admin",
                        "123");

        System.out.println(
                "Login: " + acceso);
    }
}
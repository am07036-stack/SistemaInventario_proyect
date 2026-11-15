package vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import dao.UsuarioDAO;
import modelo.Usuario;


public class LoginFrame extends JFrame {

    private JLabel lblTitulo;
    private JLabel lblUsuario;
    private JLabel lblPassword;

    private JTextField txtUsuario;
    private JPasswordField txtPassword;

    private JButton btnLogin;
    
    private UsuarioDAO usuarioDAO;

    public LoginFrame() {

        configurarVentana();
        crearComponentes();
        configurarComponentes();
        agregarComponentes();
        eventos();
    }

    private void configurarVentana() {

        setTitle("Sistema de Inventario");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setLayout(null);

    }

private void crearComponentes() {
 lblTitulo = new JLabel("SISTEMA DE INVENTARIO");
    lblUsuario = new JLabel("Usuario:");
    lblPassword = new JLabel("Contraseña:");

    txtUsuario = new JTextField();
    txtPassword = new JPasswordField();

    btnLogin = new JButton("Iniciar Sesión");
    
    usuarioDAO = new UsuarioDAO();
}

private void configurarComponentes() {
    lblTitulo.setBounds(110,20,200,30);

    lblUsuario.setBounds(50,80,100,25);
    txtUsuario.setBounds(140,80,180,25);

    lblPassword.setBounds(50,120,100,25);
    txtPassword.setBounds(140,120,180,25);

    btnLogin.setBounds(130,180,130,30);
}

private void agregarComponentes() {
    add(lblTitulo);

    add(lblUsuario);
    add(txtUsuario);

    add(lblPassword);
    add(txtPassword);

    add(btnLogin);
}
 private void eventos() {


    btnLogin.addActionListener(
        e -> iniciarSesion());
    }
 
private void iniciarSesion() {
            String usuario = txtUsuario.getText().trim();
    String password = new String(txtPassword.getPassword());

    if (usuario.isEmpty() || password.isEmpty()) {
        JOptionPane.showMessageDialog(
                this,
                "Complete usuario y contraseña");
        return;
    }

    boolean acceso = usuarioDAO.validarLogin( usuario, password);

    if (acceso) {

        JOptionPane.showMessageDialog(
                this,"Bienvenido " + usuario);

        Usuario usuarioBD = usuarioDAO.buscarUsuario(usuario);
        MenuPrincipalFrame menu = new MenuPrincipalFrame(usuarioBD.getRol());
        menu.setVisible(true);
        dispose();
    } else {
        JOptionPane.showMessageDialog(
                this, "Usuario o contraseña incorrectos");
    }
}
 
}
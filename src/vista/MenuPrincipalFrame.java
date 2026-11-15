package vista;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class MenuPrincipalFrame extends JFrame {

    private JLabel lblTitulo;
    private JLabel lblBienvenida;
    private JButton btnProductos;
    private JButton btnReportes;
    private JButton btnCerrarSesion;
    private JButton btnUsuarios;
    private String rol;

    public MenuPrincipalFrame(String rol) {
    this.rol = rol;
    configurarVentana();
    crearComponentes();
    configurarComponentes();
    agregarComponentes();
    eventos();
    configurarPermisos();
}

    private void configurarVentana() {

        setTitle("Menú Principal");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setResizable(false);
    }

    private void crearComponentes() {

        lblTitulo = new JLabel("SISTEMA DE INVENTARIO");
        lblBienvenida = new JLabel("Bienvenido al sistema");

        btnProductos = new JButton("Gestión de Productos");
        btnReportes = new JButton("Reportes");
        btnCerrarSesion = new JButton("Cerrar Sesión");
        btnUsuarios = new JButton("Gestión de Usuarios");
    }

    private void configurarComponentes() {

        lblTitulo.setBounds(115, 30, 300, 30);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblBienvenida.setBounds(150, 70, 200, 25);
        btnProductos.setBounds(140, 120, 200, 40);
        btnReportes.setBounds(140, 180, 200, 40);
        btnUsuarios.setBounds(140, 240, 200, 40);
        btnCerrarSesion.setBounds(140, 300, 200, 40);
    }

    private void agregarComponentes() {

        add(lblTitulo);
        add(lblBienvenida);

        add(btnProductos);
        add(btnReportes);
        add(btnUsuarios);
        add(btnCerrarSesion);
        
    }

    private void eventos() {

    btnProductos.addActionListener(e -> {

    ProductosFrame productos = new ProductosFrame();
    productos.setVisible(true);

});
        btnReportes.addActionListener(e -> {
        ReportesFrame reportes =
            new ReportesFrame();
            reportes.setVisible(true);

            });

        btnCerrarSesion.addActionListener(e -> {

            int opcion = JOptionPane.showConfirmDialog(
                    this,
                    "¿Desea cerrar sesión?",
                    "Confirmación",
                    JOptionPane.YES_NO_OPTION);

            if (opcion == JOptionPane.YES_OPTION) {

                dispose();

                LoginFrame login = new LoginFrame();
                login.setVisible(true);
            }

        });
        
    btnUsuarios.addActionListener(e -> {UsuariosFrame usuarios = new UsuariosFrame();
    usuarios.setVisible(true);

});    
    }
  private void configurarPermisos() {

    if (!rol.equals("ADMINISTRADOR")) {btnUsuarios.setVisible(false);
    }
}  
    
}
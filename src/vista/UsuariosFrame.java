package vista;

import dao.UsuarioDAO;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Usuario;
import util.PasswordUtil;

public class UsuariosFrame extends JFrame {

    private JLabel lblTitulo;
    private JLabel lblUsuario;
    private JLabel lblCorreo;
    private JLabel lblPassword;
    private JLabel lblRol;

    private JTextField txtUsuario;
    private JTextField txtCorreo;
    private JPasswordField txtPassword;

    private JComboBox<String> cbRol;

    private JButton btnGuardar;
    private JButton btnLimpiar;

    private JTable tblUsuarios;
    private JScrollPane scrollTabla;

    private DefaultTableModel modeloTabla;

    private UsuarioDAO usuarioDAO;

    public UsuariosFrame() {

        configurarVentana();
        crearComponentes();
        configurarComponentes();
        agregarComponentes();
        eventos();
        cargarUsuarios();
    }

    private void configurarVentana() {

        setTitle("Gestión de Usuarios");
        setSize(900, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setResizable(false);
    }

    private void crearComponentes() {

        usuarioDAO = new UsuarioDAO();

        lblTitulo = new JLabel("GESTIÓN DE USUARIOS");

        lblUsuario = new JLabel("Usuario:");
        lblCorreo = new JLabel("Correo:");
        lblPassword = new JLabel("Contraseña:");
        lblRol = new JLabel("Rol:");

        txtUsuario = new JTextField();
        txtCorreo = new JTextField();

        txtPassword = new JPasswordField();

        cbRol = new JComboBox<>();

        cbRol.addItem("ADMINISTRADOR");
        cbRol.addItem("OPERADOR");

        btnGuardar = new JButton("Guardar Usuario");
        btnLimpiar = new JButton("Limpiar");

        modeloTabla = new DefaultTableModel();

        modeloTabla.addColumn("Usuario");
        modeloTabla.addColumn("Correo");
        modeloTabla.addColumn("Rol");

        tblUsuarios = new JTable(modeloTabla);

        scrollTabla = new JScrollPane(tblUsuarios);
    }

    private void configurarComponentes() {

        lblTitulo.setBounds(280, 20, 350, 30);
        lblTitulo.setFont(
                new Font("Arial",
                        Font.BOLD,
                        22));
        lblUsuario.setBounds(40, 90, 100, 25);
        txtUsuario.setBounds(140, 90, 200, 25);

        lblCorreo.setBounds(380, 90, 100, 25);
        txtCorreo.setBounds(480, 90, 220, 25);

        lblPassword.setBounds(40, 140, 100, 25);
        txtPassword.setBounds(140, 140, 200, 25);

        lblRol.setBounds(380, 140, 100, 25);
        cbRol.setBounds(480, 140, 220, 25);

        btnGuardar.setBounds(190, 210, 180, 35);

        btnLimpiar.setBounds(430, 210, 180, 35);

        scrollTabla.setBounds(
                40,
                290,
                800,
                260);
    }

    private void agregarComponentes() {

        add(lblTitulo);

        add(lblUsuario);
        add(txtUsuario);

        add(lblCorreo);
        add(txtCorreo);

        add(lblPassword);
        add(txtPassword);

        add(lblRol);
        add(cbRol);

        add(btnGuardar);
        add(btnLimpiar);

        add(scrollTabla);
    }

    private void eventos() {

        btnGuardar.addActionListener(
                e -> guardarUsuario());

        btnLimpiar.addActionListener(
                e -> limpiarCampos());
    }

    private void guardarUsuario() {

        try {

            String usuario =
                    txtUsuario.getText().trim();

            String correo =
                    txtCorreo.getText().trim();

            String password =
                    new String(
                            txtPassword.getPassword());

            String rol =
                    cbRol.getSelectedItem()
                            .toString();

            if (usuario.isEmpty()
                    || correo.isEmpty()
                    || password.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Complete todos los campos");

                return;
            }

            if (usuarioDAO.existeUsuario(
                    usuario)) {

                JOptionPane.showMessageDialog(
                        this,
                        "El usuario ya existe");

                return;
            }

            Usuario nuevoUsuario =
                    new Usuario(
                            String.valueOf(
                                    System.currentTimeMillis()),
                            usuario,
                            correo,
                            PasswordUtil
                                    .generarHash(password),
                            rol);

            usuarioDAO.guardarUsuario(
                    nuevoUsuario);

            cargarUsuarios();

            JOptionPane.showMessageDialog(
                    this,
                    "Usuario registrado correctamente");

            limpiarCampos();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error al guardar usuario");
        }
    }

    private void cargarUsuarios() {

        modeloTabla.setRowCount(0);

        for (Usuario usuario :
                usuarioDAO.listarUsuarios()) {

            modeloTabla.addRow(
                    new Object[]{
                        usuario.getUsuario(),
                        usuario.getCorreo(),
                        usuario.getRol()
                    });
        }
    }

    private void limpiarCampos() {

        txtUsuario.setText("");
        txtCorreo.setText("");
        txtPassword.setText("");

        cbRol.setSelectedIndex(0);
    }
}
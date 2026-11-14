package vista;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import dao.ProductoDAO;
import modelo.Producto;

public class ReportesFrame extends JFrame {

    private JLabel lblTitulo;
    private JLabel lblLimite;
    private JLabel lblValorInventario;

    private JTextField txtLimite;

    private JButton btnInventarioGeneral;
    private JButton btnSinExistencias;
    private JButton btnExistenciasBajas;
    private JButton btnValorInventario;
    private JTable tblReportes;
    private JScrollPane scrollTabla;
    private DefaultTableModel modeloTabla;
    private ProductoDAO productoDAO;
    
    public ReportesFrame() {

        configurarVentana();

        crearComponentes();
        configurarComponentes();
        agregarComponentes();
        eventos();
    }

    private void configurarVentana() {

        setTitle("Reportes");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setResizable(false);
    }

    private void crearComponentes() {

        lblTitulo = new JLabel("REPORTES DE INVENTARIO");

        lblLimite = new JLabel("Límite:");

        lblValorInventario = new JLabel(
                "Valor Total Inventario: $0.00");

        txtLimite = new JTextField("5");

        btnInventarioGeneral =
                new JButton("Inventario General");

        btnSinExistencias =
                new JButton("Sin Existencias");

        btnExistenciasBajas =
                new JButton("Existencias Bajas");

        btnValorInventario =
                new JButton("Calcular Valor");

        modeloTabla = new DefaultTableModel();

        modeloTabla.addColumn("Código");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Descripción");
        modeloTabla.addColumn("Categoría");
        modeloTabla.addColumn("Precio");
        modeloTabla.addColumn("Cantidad");

        tblReportes = new JTable(modeloTabla);

        scrollTabla = new JScrollPane(tblReportes);
        
        productoDAO = new ProductoDAO(); 

    }

    private void configurarComponentes() {

        lblTitulo.setBounds(330, 20, 400, 30);
        lblTitulo.setFont(
                new Font("Arial", Font.BOLD, 22));

        btnInventarioGeneral.setBounds(
                30, 80, 180, 35);

        btnSinExistencias.setBounds(
                240, 80, 180, 35);

        btnExistenciasBajas.setBounds(
                450, 80, 180, 35);

        btnValorInventario.setBounds(
                660, 80, 180, 35);

        lblLimite.setBounds(
                850, 85, 60, 25);

        txtLimite.setBounds(
                910, 85, 50, 25);

        scrollTabla.setBounds(
                30, 150, 930, 350);

        lblValorInventario.setBounds(
                30, 540, 400, 30);

        lblValorInventario.setFont(
                new Font("Arial",
                        Font.BOLD,
                        18));
    }

    private void agregarComponentes() {

        add(lblTitulo);

        add(btnInventarioGeneral);
        add(btnSinExistencias);
        add(btnExistenciasBajas);
        add(btnValorInventario);

        add(lblLimite);
        add(txtLimite);

        add(scrollTabla);

        add(lblValorInventario);
    }

    private void eventos() {
    btnInventarioGeneral.addActionListener(e -> {
        mostrarProductos(
                productoDAO.listarProductos());
    });

    btnSinExistencias.addActionListener(e -> {
        mostrarProductos(
                productoDAO.productosSinExistencias());
    });

    btnExistenciasBajas.addActionListener(e -> {
        try {
            int limite = Integer.parseInt(
                    txtLimite.getText());
            mostrarProductos(
                    productoDAO.productosExistenciasBajas(
                            limite));
        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Ingrese un límite válido");
        }
    });

    btnValorInventario.addActionListener(e -> {
        double total =
                productoDAO.calcularValorInventario();
        lblValorInventario.setText(
                "Valor Total Inventario: $" + total);
    });

}
    
    private void mostrarProductos(
        java.util.List<Producto> productos) {
    modeloTabla.setRowCount(0);
        for (Producto producto : productos) {

        modeloTabla.addRow(new Object[]{
            producto.getCodigo(),
            producto.getNombre(),
            producto.getDescripcion(),
            producto.getCategoria(),
            producto.getPrecio(),
            producto.getCantidad()
        });
    }
}
}
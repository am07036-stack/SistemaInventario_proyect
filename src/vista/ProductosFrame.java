package vista;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Producto;
import dao.ProductoDAO;

public class ProductosFrame extends JFrame {

    private JLabel lblTitulo;

    private JLabel lblCodigo;
    private JLabel lblNombre;
    private JLabel lblDescripcion;
    private JLabel lblCategoria;
    private JLabel lblPrecio;
    private JLabel lblCantidad;

    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtDescripcion;
    private JTextField txtPrecio;
    private JTextField txtCantidad;

    private JComboBox<String> cbCategoria;

    private JButton btnNuevo;
    private JButton btnGuardar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnBuscar;
    private JButton btnLimpiar;

    private JLabel lblMovimiento;
    private JTextField txtMovimiento;

    private JButton btnEntrada;
    private JButton btnSalida;

    private JTable tblProductos;
    private JScrollPane scrollTabla;

    private DefaultTableModel modeloTabla;
    
    private java.util.ArrayList<Producto> listaProductos;
    private ProductoDAO productoDAO;

    public ProductosFrame() {

        configurarVentana();

        crearComponentes();
        configurarComponentes();
        agregarComponentes();
        eventos();
        cargarProductos();
    }

    private void configurarVentana() {

        setTitle("Gestión de Productos");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setResizable(false);
    }

    private void crearComponentes() {

        lblTitulo = new JLabel("GESTIÓN DE PRODUCTOS");

        lblCodigo = new JLabel("Código:");
        lblNombre = new JLabel("Nombre:");
        lblDescripcion = new JLabel("Descripción:");
        lblCategoria = new JLabel("Categoría:");
        lblPrecio = new JLabel("Precio:");
        lblCantidad = new JLabel("Cantidad:");

        txtCodigo = new JTextField();
        txtNombre = new JTextField();
        txtDescripcion = new JTextField();
        txtPrecio = new JTextField();
        txtCantidad = new JTextField();

        cbCategoria = new JComboBox<>();

        cbCategoria.addItem("Electrónica");
        cbCategoria.addItem("Papelería");
        cbCategoria.addItem("Limpieza");
        cbCategoria.addItem("Herramientas");
        cbCategoria.addItem("Otros");

        btnNuevo = new JButton("Nuevo");
        btnGuardar = new JButton("Guardar");
        btnModificar = new JButton("Modificar");
        btnEliminar = new JButton("Eliminar");
        btnBuscar = new JButton("Buscar");
        btnLimpiar = new JButton("Limpiar");

        lblMovimiento = new JLabel("Movimiento:");

        txtMovimiento = new JTextField();

        btnEntrada = new JButton("Entrada");
        btnSalida = new JButton("Salida");

        modeloTabla = new DefaultTableModel();

        modeloTabla.addColumn("Código");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Descripción");
        modeloTabla.addColumn("Categoría");
        modeloTabla.addColumn("Precio");
        modeloTabla.addColumn("Cantidad");

        tblProductos = new JTable(modeloTabla);

        scrollTabla = new JScrollPane(tblProductos);
        listaProductos = new java.util.ArrayList<>();
        productoDAO = new ProductoDAO();
    }

    private void configurarComponentes() {

        lblTitulo.setBounds(350, 20, 400, 30);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));

        lblCodigo.setBounds(30, 80, 100, 25);
        txtCodigo.setBounds(120, 80, 180, 25);

        lblNombre.setBounds(330, 80, 100, 25);
        txtNombre.setBounds(420, 80, 180, 25);

        lblDescripcion.setBounds(630, 80, 100, 25);
        txtDescripcion.setBounds(730, 80, 200, 25);

        lblCategoria.setBounds(30, 130, 100, 25);
        cbCategoria.setBounds(120, 130, 180, 25);

        lblPrecio.setBounds(330, 130, 100, 25);
        txtPrecio.setBounds(420, 130, 180, 25);

        lblCantidad.setBounds(630, 130, 100, 25);
        txtCantidad.setBounds(730, 130, 200, 25);

        btnNuevo.setBounds(30, 190, 120, 30);
        btnGuardar.setBounds(170, 190, 120, 30);
        btnModificar.setBounds(310, 190, 120, 30);
        btnEliminar.setBounds(450, 190, 120, 30);
        btnBuscar.setBounds(590, 190, 120, 30);
        btnLimpiar.setBounds(730, 190, 120, 30);

        lblMovimiento.setBounds(30, 250, 100, 25);
        txtMovimiento.setBounds(120, 250, 150, 25);

        btnEntrada.setBounds(300, 250, 120, 30);
        btnSalida.setBounds(440, 250, 120, 30);

        scrollTabla.setBounds(30, 320, 900, 300);
    }

    private void agregarComponentes() {

        add(lblTitulo);

        add(lblCodigo);
        add(txtCodigo);

        add(lblNombre);
        add(txtNombre);

        add(lblDescripcion);
        add(txtDescripcion);

        add(lblCategoria);
        add(cbCategoria);

        add(lblPrecio);
        add(txtPrecio);

        add(lblCantidad);
        add(txtCantidad);

        add(btnNuevo);
        add(btnGuardar);
        add(btnModificar);
        add(btnEliminar);
        add(btnBuscar);
        add(btnLimpiar);

        add(lblMovimiento);
        add(txtMovimiento);

        add(btnEntrada);
        add(btnSalida);

        add(scrollTabla);
    }

    private void eventos() {

        btnNuevo.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Nuevo producto"));

        btnGuardar.addActionListener(e -> guardarProducto());

        btnModificar.addActionListener(e -> modificarProducto());

        btnEliminar.addActionListener(e -> eliminarProducto());

        btnBuscar.addActionListener(e -> buscarProducto());

        btnLimpiar.addActionListener(e -> limpiarCampos());

        btnEntrada.addActionListener(e -> registrarEntrada());

        btnSalida.addActionListener(e -> registrarSalida());
        
       tblProductos.getSelectionModel().addListSelectionListener(e -> { 
    if (!e.getValueIsAdjusting()) {
        cargarFilaSeleccionada();
    }
}); 
       
    }

    private void limpiarCampos() {

        txtCodigo.setText("");
        txtNombre.setText("");
        txtDescripcion.setText("");
        txtPrecio.setText("");
        txtCantidad.setText("");
        txtMovimiento.setText("");

        cbCategoria.setSelectedIndex(0);
    }
    private void cargarProductos() {

    modeloTabla.setRowCount(0);

    for (Producto producto : productoDAO.listarProductos()) {

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
 private void guardarProducto() {

    try {

        String codigo = txtCodigo.getText().trim();
        String nombre = txtNombre.getText().trim();
        String descripcion = txtDescripcion.getText().trim();
        String categoria = cbCategoria.getSelectedItem().toString();
       
        if (productoDAO.existeCodigo(codigo)) {
            JOptionPane.showMessageDialog(
            this,
            "Ya existe un producto con ese código");
            return;
            }
        
       if (codigo.isEmpty()|| nombre.isEmpty() || descripcion.isEmpty()) {
           JOptionPane.showMessageDialog(
            this,
            "Complete todos los campos obligatorios");
            return;
}

        double precio = Double.parseDouble(txtPrecio.getText().trim());

        int cantidad = Integer.parseInt(txtCantidad.getText().trim());
       
        if (precio < 0) { 
            JOptionPane.showMessageDialog(
            this,
            "El precio no puede ser negativo");
            return;
            }

        if (cantidad < 0) {
             JOptionPane.showMessageDialog(
            this,
            "La cantidad no puede ser negativa");
            return;
            }

        Producto producto = new Producto(
                codigo,
                nombre,
                descripcion,
                categoria,
                precio,
                cantidad
        );

    productoDAO.guardarProducto(producto);
        cargarProductos();

limpiarCampos();
       ;

        JOptionPane.showMessageDialog(
                this,
                "Producto guardado correctamente");

        limpiarCampos();

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "Error al guardar producto");
    }
}
private void cargarFilaSeleccionada() {

    int fila = tblProductos.getSelectedRow();

    if (fila >= 0) {

        txtCodigo.setText(
                modeloTabla.getValueAt(fila, 0).toString());

        txtNombre.setText(
                modeloTabla.getValueAt(fila, 1).toString());

        txtDescripcion.setText(
                modeloTabla.getValueAt(fila, 2).toString());

        cbCategoria.setSelectedItem(
                modeloTabla.getValueAt(fila, 3).toString());

        txtPrecio.setText(
                modeloTabla.getValueAt(fila, 4).toString());

        txtCantidad.setText(
                modeloTabla.getValueAt(fila, 5).toString());
    }
}
private void modificarProducto() {

    try {

        String codigo = txtCodigo.getText().trim();
        String nombre = txtNombre.getText().trim();
        String descripcion = txtDescripcion.getText().trim();
        String categoria = cbCategoria.getSelectedItem().toString();

        double precio = Double.parseDouble(txtPrecio.getText().trim());

        int cantidad = Integer.parseInt(txtCantidad.getText().trim());

        Producto producto = new Producto(
                codigo,
                nombre,
                descripcion,
                categoria,
                precio,
                cantidad
        );

        productoDAO.modificarProducto(producto);

        cargarProductos();

        JOptionPane.showMessageDialog(
                this,
                "Producto modificado correctamente");

        limpiarCampos();

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "Error al modificar producto");
    }
}
private void eliminarProducto() {

    try {

        String codigo = txtCodigo.getText().trim();

        int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Desea eliminar este producto?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {

            productoDAO.eliminarProducto(codigo);

            cargarProductos();

            limpiarCampos();

            JOptionPane.showMessageDialog(
                    this,
                    "Producto eliminado correctamente");
        }

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "Error al eliminar producto");
    }
}
private void registrarEntrada() {

    try {

        String codigo = txtCodigo.getText().trim();

        int cantidadActual =
                Integer.parseInt(txtCantidad.getText().trim());

        int movimiento =
                Integer.parseInt(txtMovimiento.getText().trim());
        if (movimiento <= 0) {
            JOptionPane.showMessageDialog(
            this,
            "El movimiento debe ser mayor que cero");
            return;
            }
        int nuevaCantidad = cantidadActual + movimiento;

        productoDAO.actualizarCantidad(
                codigo,
                nuevaCantidad);

        cargarProductos();

        txtCantidad.setText(
                String.valueOf(nuevaCantidad));

        txtMovimiento.setText("");

        JOptionPane.showMessageDialog(
                this,
                "Entrada registrada correctamente");

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "Error al registrar entrada");
    }
}
private void registrarSalida() {

    try {

        String codigo = txtCodigo.getText().trim();

        int cantidadActual =
                Integer.parseInt(txtCantidad.getText().trim());

        int movimiento =
                Integer.parseInt(txtMovimiento.getText().trim());
        if (movimiento <= 0) {
            JOptionPane.showMessageDialog(
            this,
            "El movimiento debe ser mayor que cero");
            return;
            }

        if (movimiento > cantidadActual) {

            JOptionPane.showMessageDialog(
                    this,
                    "No hay existencias suficientes");

            return;
        }

        int nuevaCantidad = cantidadActual - movimiento;

        productoDAO.actualizarCantidad(
                codigo,
                nuevaCantidad);

        cargarProductos();

        txtCantidad.setText(
                String.valueOf(nuevaCantidad));

        txtMovimiento.setText("");

        JOptionPane.showMessageDialog(
                this,
                "Salida registrada correctamente");

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "Error al registrar salida");
    }
}
private void buscarProducto() {

    String codigo = txtCodigo.getText().trim();

    Producto producto =
            productoDAO.buscarPorCodigo(codigo);

    if (producto == null) {

        JOptionPane.showMessageDialog(
                this,
                "Producto no encontrado");

        return;
    }

    txtNombre.setText(
            producto.getNombre());

    txtDescripcion.setText(
            producto.getDescripcion());

    cbCategoria.setSelectedItem(
            producto.getCategoria());

    txtPrecio.setText(
            String.valueOf(producto.getPrecio()));

    txtCantidad.setText(
            String.valueOf(producto.getCantidad()));

    JOptionPane.showMessageDialog(
            this,
            "Producto encontrado");
}
}

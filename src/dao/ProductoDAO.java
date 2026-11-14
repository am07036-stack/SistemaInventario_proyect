package dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import modelo.Producto;
import org.bson.Document;
import com.mongodb.client.FindIterable;
import java.util.ArrayList;
import java.util.List;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

public class ProductoDAO {

    private MongoCollection<Document> coleccion;

    public ProductoDAO() {

        MongoDatabase db = ConexionMongo.conectar();

        coleccion = db.getCollection("productos");
    }

    public void guardarProducto(Producto producto) {

        Document doc = new Document();

        doc.append("codigo", producto.getCodigo());
        doc.append("nombre", producto.getNombre());
        doc.append("descripcion", producto.getDescripcion());
        doc.append("categoria", producto.getCategoria());
        doc.append("precio", producto.getPrecio());
        doc.append("cantidad", producto.getCantidad());

        coleccion.insertOne(doc);
    }
   public List<Producto> listarProductos() {

    List<Producto> productos = new ArrayList<>();

    FindIterable<Document> documentos = coleccion.find();

    for (Document doc : documentos) {

        Producto producto = new Producto();

        producto.setCodigo(doc.getString("codigo"));
        producto.setNombre(doc.getString("nombre"));
        producto.setDescripcion(doc.getString("descripcion"));
        producto.setCategoria(doc.getString("categoria"));

        producto.setPrecio(doc.getDouble("precio"));
        producto.setCantidad(doc.getInteger("cantidad"));

        productos.add(producto);
    }

    return productos;
}
   public void modificarProducto(Producto producto) {

    coleccion.updateOne(
            Filters.eq("codigo", producto.getCodigo()),
            Updates.combine(
                    Updates.set("nombre", producto.getNombre()),
                    Updates.set("descripcion", producto.getDescripcion()),
                    Updates.set("categoria", producto.getCategoria()),
                    Updates.set("precio", producto.getPrecio()),
                    Updates.set("cantidad", producto.getCantidad())
            )
    );
}
   public void eliminarProducto(String codigo) {

    coleccion.deleteOne(
            Filters.eq("codigo", codigo)
    );

}
 
}
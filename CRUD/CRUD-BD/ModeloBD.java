
/**
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Iterator;
import java.util.ArrayList;
import java.sql.*;

public class ModeloBD extends ModeloAbs
{
    private Connection conexion = null;
    private Statement stmt = null;
    private String servidor = "jdbc:mysql://localhost/Productosdb";
    private String usuario = "root";
    private String contraseña = "root";

    /**
     * Constructor for objects of class ModeloBD
     */
    public ModeloBD() {
        // Registrar el driver
        try {
          Class.forName("com.mysql.jdbc.Driver");
           } catch (ClassNotFoundException e) {
         System.err.println("ERROR AL REGISTRAR EL DRIVER");
         System.exit(0); // parar la ejecución
        }
    
        // Establecer la conexión con el servidor
        try {
         conexion = DriverManager.getConnection(servidor, usuario, contraseña);
         stmt = conexion.createStatement();
        } catch (SQLException e) {
        System.err.println("ERROR AL CONECTAR CON EL SERVIDOR");
        System.exit(0); // PARA LA EJECUCIÓN DEL PROGRAMA
        }
        System.out.println("Conectado a la base de datos Productos");
    }
    

    public boolean insertarProducto ( Producto p){
        
        String sqlStr = "insert into 'Productos' ('codigo', 'nombre', 'stock', 'stock_min', 'precio') values (?,?,?,?,?);";
        PreparedStatement sentenciapreparada = null;
        int nfilas = 0;
 
        try {
            sentenciapreparada = conexion.prepareStatement(sqlStr);
        } catch (SQLException e) {
          System.err.println("ERROR en la instrucción de SQL");
          e.printStackTrace();
          return false;
        }
 
        try {
            sentenciapreparada.setInt    (1,p.getCodigo());
            sentenciapreparada.setString (2,p.getNombre());
            sentenciapreparada.setInt    (3,p.getStock());
            sentenciapreparada.setInt    (4,p.getStock_min());
            sentenciapreparada.setFloat  (5,p.getPrecio());
            nfilas = sentenciapreparada.executeUpdate();
        } catch (Exception ex) {
        ex.printStackTrace();
        return false;
        }
        return true;
    }
    
    
    public boolean borrarProducto (int codigo){
        int nfilas = 0;
        String sqlStr =  "delete from Productos where codigo = "+codigo;
        try {
            nfilas = stmt.executeUpdate(sqlStr);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    
    public Producto buscarProducto (int codigo){
        String sqlStr = "select * from Productos where codigo = "+codigo;
        Producto resu = null;
        try {
            ResultSet rset = stmt.executeQuery(sqlStr);
            if (rset.next()){
                resu = new Producto();
                resu.setCodigo    (rset.getInt("codigo"));
                resu.setNombre    (rset.getString("nombre"));
                resu.setStock     (rset.getInt("stock"));
                resu.setStock_min (rset.getInt("stock_min"));
                resu.setPrecio    (rset.getFloat("precio"));
            }
        } catch (Exception ex) {
          ex.printStackTrace();
        }
        return resu;
    }

    
    public void listarProductos (){
       String sqlStr = "select * from Productos";
       int i=1;
       try {
           ResultSet rset = stmt.executeQuery(sqlStr);
            while ( rset.next ()){
                rset.getString(i);
                i++;
            }   
       } catch (Exception ex) {
           ex.printStackTrace();
       }
    }
    
    boolean modificarProducto (Producto p){  
       String sqlStr = "update 'Productos' set codigo = ?, nombre = ?, stock = ?, stock_min = ?, precio = ? "+
                       " where codigo = ?";
       PreparedStatement sentenciapreparada = null;
       int nfilas = 0;
     
       try {
          sentenciapreparada = conexion.prepareStatement(sqlStr);
          } catch (SQLException e) {
          System.err.println("ERROR en la instrucción de SQL");
          e.printStackTrace();
          return false;
          }
 
       try {
        sentenciapreparada.setInt   (1,p.getCodigo());
        sentenciapreparada.setString(2,p.getNombre());
        sentenciapreparada.setInt   (3,p.getStock());
        sentenciapreparada.setInt   (4,p.getStock_min());
        sentenciapreparada.setFloat (5,p.getPrecio());
        sentenciapreparada.setInt   (6,p.getCodigo());
        nfilas = sentenciapreparada.executeUpdate();
        } catch (Exception ex) {
        ex.printStackTrace();
        return false;
       }
       return true;
    }
    
    // Devuelvo un Iterador de una ArrayList con los resultados
    // copiados del Rset al ArrayList
     Iterator <Producto> getIterator(){
         ArrayList <Producto> lista = new ArrayList<Producto>();
         // Relleno el array list con los resultados de al consulta
         String sqlStr = "select * from Productos ";
         try {
             ResultSet rset = stmt.executeQuery(sqlStr);
             // Solo de debe existir uno;
             while ( rset.next ()){
                 Producto p = new Producto();
                 p.setCodigo    (rset.getInt   ("CODIGO"));
                 p.setNombre    (rset.getString("NOMBRE"));
                 p.setStock     (rset.getInt   ("STOCK"));
                 p.setStock_min (rset.getInt   ("STOCK_MIN"));
                 p.setPrecio    (rset.getFloat ("PRECIO"));
                 lista.add(p); // Añado el objeto a la coleccion
             }   
         } catch (Exception ex) {
                ex.printStackTrace();
         }
         return lista.iterator();
     }
}

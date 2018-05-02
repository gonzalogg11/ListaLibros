
/**
 * Write a description of class ModeloHashMap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.HashMap;
import java.util.Map;

public class ModeloHashMap extends ModeloAbs{
    private HashMap <Integer,Producto> lista;
    
    public ModeloHashMap(){
       lista=new HashMap  <Integer,Producto>();
    }
    
    public boolean insertarProducto ( Producto p){
        
        return false;
    }
 
    public boolean borrarProducto ( int codigo ){
        for (int i=0; i<lista.size(); i++) {
            if (lista.get(i).getCodigo() == codigo) {
                lista.remove(i);
                return true;
            }
        }
        return false;
    }
    
    public Producto buscarProducto (int codigo) {
        for (int i=0; i<lista.size(); i++) {
            Producto p = lista.get(i);
            if (p.getCodigo() == codigo) {
                return p;
            }
        }
        return null;
    }
    
    public void listarProductos(){
        for (int i=0; i < lista.size(); i++){
            System.out.println(lista.get(i));
        }
    }
    
    public boolean modificarProducto (Producto nuevo){
       Producto aux = buscarProducto(nuevo.getCodigo());
       if(aux != null){
           lista.set(lista.indexOf(aux), nuevo);
           return true;
       }
       return false;
    }
}

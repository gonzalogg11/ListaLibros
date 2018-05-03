
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
        lista.put(p.getCodigo(),p);
        return true;
    }
 
    public boolean borrarProducto ( int codigo ){
        lista.remove(codigo);
        return true;
    }
    
    public Producto buscarProducto (int codigo) {
        return lista.get(codigo);
    }
    
    public void listarProductos(){
      for(Map.Entry<Integer, Producto> lista:lista.entrySet()){
          System.out.println(lista.getValue());
      }
    }
    
    public void listarProductosStock (){
      for(int i=0;i<lista.size();i++){
          Producto aux = lista.get(i);
          if(aux.getStock()<aux.getStock_min()){
              System.out.println(lista.get(i));
            }
        }
    }
    
    public boolean modificarProducto (Producto nuevo){
        return (lista.containsValue(nuevo));
    }
}

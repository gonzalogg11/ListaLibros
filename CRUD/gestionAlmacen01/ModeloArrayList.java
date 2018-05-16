
/**
 * Implementa la parte de Modelo de Datos
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
public class ModeloArrayList extends ModeloAbs {
    protected ArrayList <Producto> lista;
    
    public ModeloArrayList() {
        lista=new ArrayList <Producto>();
    }

    // Implementar los metodos abstractos de ModeloAbs
    public boolean insertarProducto ( Producto p){
        for (int i=0; i<lista.size(); i++) {
            if (lista.get(i) == null) {
                lista.add(p);
                return true;
            }
        }
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
    
    public void listarProductosStock (){
      for(int i=0;i<lista.size();i++){
          Producto aux = lista.get(i);
          if(aux.getStock()<aux.getStock_min()){
              System.out.println(lista.get(i));
            }
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

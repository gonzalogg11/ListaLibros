
/**
 * Write a description of class ModeloArrayListSerializable here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
public class ModeloArrayListSerializable extends ModeloArrayList{
    static final String nombrefichero = "productos.csv";
    
    public ModeloArrayListSerializable(){
       super();
       cargarDesdeFichero();
    }
    
    private void salvarAfichero(){
        File fproductos = new File (nombrefichero);
        try{
         // Creo Flujo de tipo fichero de byte 
         FileOutputStream fos= new FileOutputStream(fproductos);
         // Creo un Flujo de objetos sobre el fichero
         ObjectOutputStream oos= new ObjectOutputStream(fos);
         // Escribo la lista de productos al fichero
         for (Producto p: lista){
             oos.writeObject(p);
            }
   
         oos.close(); // Cierro el flujo de objetos
         fos.close(); // Cierro el flujo de ficheros
       }catch(IOException ioe){
            ioe.printStackTrace();
        }  
        
    }
    
    private void cargarDesdeFichero() {
        File fproductos =new File (nombrefichero);
        if ( !fproductos.exists() ){
           return; // No hay datos
        }
        try{
         // Creo Flujo de tipo fichero de byte 
         FileInputStream fin= new FileInputStream(fproductos);
         // Creo un Flujo de objetos sobre el fichero
         ObjectInputStream oin= new ObjectInputStream(fin);
         
         try {
              Producto pro = (Producto) oin.readObject();
              while ( true ){
                lista.add(pro); // Añado el producto 
                pro = (Producto) oin.readObject();  
              }
         } catch (IOException ex){}
         
         oin.close(); // Cierro el flujo de objetos
         fin.close(); // Cierro el flujo de ficheros
       } catch (IOException ioe){
            ioe.printStackTrace();
         }    
         catch (ClassNotFoundException ex){
            ex.printStackTrace();
         }    
    }

    public boolean insertarProducto ( Producto p){
      boolean resu = super.insertarProducto(p);
      if (resu == true){
          salvarAfichero();
          return true;
      }
      return false;
    }
 
    public boolean borrarProducto ( int codigo ){
      boolean resu = super.borrarProducto(codigo);
      if (resu == true){
          salvarAfichero();
          return true;
      }
      return false;
    }
    
    public boolean modificarProducto (Producto nuevo){
       boolean resu = super.modificarProducto(nuevo);
       if (resu == true){
           salvarAfichero();
           return true;
       }
       return false;
    }
}

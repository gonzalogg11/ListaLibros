
/**
 * Lista de libro de una biblioteca
 * 
 * @author (Gonzalo Granizo 1DAW) 
 */
public class ListaLibros
{
   
    Libro primero;

    /**
     * Constructor for objects of class ListaNodos
     */
    public ListaLibros(){
        primero = null;
    }

   
    // Completar los metodos
    
    // Incluye un nuevo libro en la lista
    public void pon(Libro valor){
        valor.siguiente = primero;
        primero = valor;
    }
    
    public boolean estaVacia(){
       return (primero == null);
    }
    
    // Devuelve un libro que es eliminado de la lista o null si no existe
    public Libro quitar (int id) {
        if (primero == null) return null;
        Libro p1 = primero;
        Libro p2 = primero.siguiente;
        while(p2 != null) {
            if(id == p2.id) {
                Libro aux = p2;
                p1.siguiente = p2.siguiente;
                return aux;
            }
            p1 = p2;
            p2 = p2.siguiente;
        }
        return null;
    }
   
    // Devuelve el id del libro o -1 si no esta
    public int buscarId (String titulo){
        if (primero == null) return -1;
        for (Libro paux = primero; paux !=null; paux = paux.siguiente) {
            if(titulo == paux.titulo) {
                return paux.id;
            }
        }
        return -1;
   }
   
    // Muestra los libros almacenados
    public void verCatalogo (){
        Libro aux = primero;
        System.out.println("------------------------");
        while (aux != null){
            System.out.println(aux);
            aux = aux.siguiente;
        }
        System.out.println("------------------------");
    }
         
    

}

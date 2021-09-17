package dominio.ej3;

public class Libro {

    private String author;
    private String titulo;
    private Number isbn;
    private boolean disponible;

    public Libro(Libro libro) {
        setAuthor(libro.getAuthor());
        setIsbn(libro.getIsbn());
        setTitulo(libro.getTitulo());
        setDisponible(libro.isDisponible());
    }

    public Libro(String author, String titulo, int isbn) {
        setAuthor(author);
        setIsbn(isbn);
        setTitulo(titulo);
        setDisponible(true);
    }


    public boolean préstamo(){
        if(isDisponible()){
            setDisponible(false);
            return true;
        }
        return false;
    }
    public boolean devolución(){
        if(!isDisponible()){
            setDisponible(true);
            return true;
        }
        return false;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Number getIsbn() {
        return isbn;
    }

    public void setIsbn(Number isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString(){
        return "Libro "+getTitulo()+", creado por "+getAuthor()+", identificador:"+getIsbn();
    }
}

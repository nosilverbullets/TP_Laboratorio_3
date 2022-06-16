public abstract class TareaControl { // [Matias de Andrade]
    private static int contadorid = 0;
    private int id;
    protected String nombre;
    protected String descripcion;

    public TareaControl(String nombre ,String descripcion) {
        this.nombre = nombre;
        this.id = contadorid ++;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public abstract String completarTarea();
    @Override
    public String toString() {
        return "Tarea id [" + id + "] " + nombre + " >> Descripcion: " + descripcion;
    }
}
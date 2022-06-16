public class Usuario {
    protected String nombre;
    protected TipoUsuario tipoUsuario;
    protected String email;
    protected String clave;
    protected String dni;

    public Usuario(String nombre, String email, String clave, String dni) {
        this.nombre = nombre;
        this.email = email;
        this.clave = clave;
        this.dni = dni;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public String getEmail() {
        return email;
    }

    public String getClave() {
        return clave;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "\nUsuario: " + nombre +
                "\nEmail: " + email +
                "\nDNI: " + dni;
    }
}

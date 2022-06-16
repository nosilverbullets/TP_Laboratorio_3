public class Admin extends Usuario{

    public Admin(String nombre, String email, String clave, String dni) {
        super(nombre, email, clave, dni);
        this.tipoUsuario = TipoUsuario.admin;
    }

    @Override
    public String toString() {
        return "\nAdministrador: "+ nombre + '\'' +
                "\nEmail='" + email + '\'' +
                "\nDNI: " + dni ;
    }
}
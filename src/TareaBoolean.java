import java.util.Scanner;

public class TareaBoolean extends  TareaControl{ // [Matias de Andrade]
    public TareaBoolean(String tarea,String descripcion) {
        super(tarea,descripcion);
    }

    @Override
    public String completarTarea() {
        Scanner scan = new Scanner(System.in);
        StringBuilder str1 = new StringBuilder(descripcion + " : ");
        System.out.println("\nIngrese respuesta:");
        System.out.println("[1] Si [2] No");
        short opcion;
        do {
            opcion = scan.nextShort();
            try {
                switch (opcion) {
                    case 1:
                        str1.append(" SI \n");
                        break;
                    case 2:
                        str1.append(" NO \n");
                        break;
                    default:
                        System.out.println("\nOpcion invalida");
                }
            } catch (NumberFormatException a) {
                System.out.println("\nIngrese opcion valida");
            }
        }while (opcion != 1 && opcion != 2);
        return str1.toString().toUpperCase();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
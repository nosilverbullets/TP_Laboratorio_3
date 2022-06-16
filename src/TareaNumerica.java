import java.util.Scanner;

public class TareaNumerica extends  TareaControl{   // [Matias de Andrade]

    public TareaNumerica(String tarea, String descripcion) {
        super(tarea , descripcion);
    }

    @Override
    public String completarTarea() {
        Scanner scan = new Scanner(System.in);
        StringBuilder str = new StringBuilder(descripcion + " : ");
        while (true) {
            try {
                System.out.println("Ingrese valor numerico: ");
                float user = scan.nextFloat();
                str.append(user);
                str.append("\n");
                break;
            } catch (NumberFormatException a) {
                System.out.println("Ingrese un valor numerico");
            }
        }
        return str.toString().toUpperCase();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
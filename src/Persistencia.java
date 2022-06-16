import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;

public class Persistencia {// [Braian D'Aleo]
    private String path;
    public Persistencia() {
        this.path = System.getProperty("user.dir") + "\\json\\";
    }

    public ArrayList<Admin> cargarAdmin() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());
        gsonBuilder.registerTypeAdapter(TareaControl.class, new AbstractTareaControlAdapter());
        gsonBuilder.excludeFieldsWithModifiers(java.lang.reflect.Modifier.TRANSIENT); // OK
        Gson gson = gsonBuilder.setPrettyPrinting().create();
        ArrayList<Admin> listadoAdmin = new ArrayList<>();
        File file = new File(path + "admin.json"); // <- MOD. HERE
        try {
            Type listType = new TypeToken<ArrayList<Admin>>() {
            }.getType(); // <- MOD. HERE
            //Deserializo
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            listadoAdmin = gson.fromJson(bufferedReader, listType); // <- MOD. HERE
        } catch (IOException e) {
            System.out.println("No se pudo leer/escribir el archivo: " + e.getMessage());
            e.printStackTrace();
        }
        return listadoAdmin;
    }

    public void actualizarAdmin(ArrayList<Admin> listadoAdmin) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());
        gsonBuilder.registerTypeAdapter(TareaControl.class, new AbstractTareaControlAdapter());
        gsonBuilder.excludeFieldsWithModifiers(java.lang.reflect.Modifier.TRANSIENT); // OK
        Gson gson = gsonBuilder.setPrettyPrinting().create();
        File file = new File(path + "admin.json"); // <- MOD. HERE
        try {
            //Serializo
            Type listType = new TypeToken<ArrayList<Admin>>() {
            }.getType(); // <- MOD. HERE
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            gson.toJson(listadoAdmin, listType, bufferedWriter); // <- MOD. HERE
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("No se pudo leer/escribir el archivo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public ArrayList<Paciente> cargarPaciente() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());
        gsonBuilder.registerTypeAdapter(TareaControl.class, new AbstractTareaControlAdapter());
        gsonBuilder.excludeFieldsWithModifiers(java.lang.reflect.Modifier.TRANSIENT); // OK
        Gson gson = gsonBuilder.setPrettyPrinting().create();
        ArrayList<Paciente> listadoPaciente = new ArrayList<>();
        File file = new File(path + "paciente.json"); // <- MOD. HERE
        try {
            Type listType = new TypeToken<ArrayList<Paciente>>() {
            }.getType(); // <- MOD. HERE
            //Deserializo
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            listadoPaciente = gson.fromJson(bufferedReader, listType); // <- MOD. HERE
        } catch (IOException e) {
            System.out.println("No se pudo leer/escribir el archivo: " + e.getMessage());
            e.printStackTrace();
        }
        return listadoPaciente;
    }

    public void actualizarPaciente(ArrayList<Paciente> listadoPaciente) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());
        gsonBuilder.registerTypeAdapter(TareaControl.class, new AbstractTareaControlAdapter());
        gsonBuilder.excludeFieldsWithModifiers(java.lang.reflect.Modifier.TRANSIENT); // OK
        Gson gson = gsonBuilder.setPrettyPrinting().create();
        File file = new File(path + "paciente.json"); // <- MOD. HERE
        try {
            //Serializo
            Type listType = new TypeToken<ArrayList<Paciente>>() {
            }.getType(); // <- MOD. HERE
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            gson.toJson(listadoPaciente, listType, bufferedWriter); // <- MOD. HERE
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("No se pudo leer/escribir el archivo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public ArrayList<Profesional> cargarProfesional() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());
        gsonBuilder.registerTypeAdapter(TareaControl.class, new AbstractTareaControlAdapter());
        gsonBuilder.excludeFieldsWithModifiers(java.lang.reflect.Modifier.TRANSIENT); // OK
        Gson gson = gsonBuilder.setPrettyPrinting().create();
        ArrayList<Profesional> listadoProfesional = new ArrayList<>();
        File file = new File(path + "profesional.json"); // <- MOD. HERE
        try {
            Type listType = new TypeToken<ArrayList<Profesional>>() {
            }.getType(); // <- MOD. HERE
            //Deserializo
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            listadoProfesional = gson.fromJson(bufferedReader, listType); // <- MOD. HERE
        } catch (IOException e) {
            System.out.println("No se pudo leer/escribir el archivo: " + e.getMessage());
            e.printStackTrace();
        }
        return listadoProfesional;
    }

    public void actualizarProfesional(ArrayList<Profesional> listadoProfesional) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());
        gsonBuilder.registerTypeAdapter(TareaControl.class, new AbstractTareaControlAdapter());
        gsonBuilder.excludeFieldsWithModifiers(java.lang.reflect.Modifier.TRANSIENT); // OK
        Gson gson = gsonBuilder.setPrettyPrinting().create();
        File file = new File(path + "profesional.json"); // <- MOD. HERE
        try {
            //Serializo
            Type listType = new TypeToken<ArrayList<Profesional>>() {
            }.getType(); // <- MOD. HERE
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            gson.toJson(listadoProfesional, listType, bufferedWriter); // <- MOD. HERE
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("No se pudo leer/escribir el archivo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public ArrayList<TareaControl> cargarTarea() {  ///APLICAR POLIMORFIA.
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());
        gsonBuilder.registerTypeAdapter(TareaControl.class, new AbstractTareaControlAdapter());
        gsonBuilder.excludeFieldsWithModifiers(java.lang.reflect.Modifier.TRANSIENT);
        Gson gson = gsonBuilder.create();
        ArrayList<TareaControl> listadoTarea = new ArrayList<>();
        File file = new File(path + "tareas.json"); // <- MOD. HERE
        try {
            Type listType = new TypeToken<ArrayList<TareaControl>>() {
            }.getType(); // <- MOD. HERE
            //Deserializo
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            listadoTarea = gson.fromJson(bufferedReader, listType); // <- MOD. HERE
        } catch (IOException e) {
            System.out.println("No se pudo leer/escribir el archivo: " + e.getMessage());
            e.printStackTrace();
        }
        return listadoTarea;
    }

    public void actualizarTarea(ArrayList<TareaControl> listadoTarea) { ///APLICAR POLIMORFIA
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());
        gsonBuilder.registerTypeAdapter(TareaControl.class, new AbstractTareaControlAdapter());
        gsonBuilder.excludeFieldsWithModifiers(java.lang.reflect.Modifier.TRANSIENT);
        Gson gson = gsonBuilder.create();
        File file = new File(path + "tareas.json"); // <- MOD. HERE
        try {
            //Serializo
            Type listType = new TypeToken<ArrayList<TareaControl>>() {
            }.getType(); // <- MOD. HERE
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            gson.toJson(listadoTarea, listType, bufferedWriter); // <- MOD. HERE
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("No se pudo leer/escribir el archivo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public ArrayList<Control> cargarControl() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());
        gsonBuilder.registerTypeAdapter(TareaControl.class, new AbstractTareaControlAdapter());
        gsonBuilder.excludeFieldsWithModifiers(java.lang.reflect.Modifier.TRANSIENT);
        Gson gson = gsonBuilder.create();
        ArrayList<Control> listadoControl = new ArrayList<>();
        File file = new File(path + "controles.json"); // <- MOD. HERE
        try {
            //Serializo
            Type listType = new TypeToken<ArrayList<Control>>() {
            }.getType(); // <- MOD. HERE
            //Deserializo
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            listadoControl = gson.fromJson(bufferedReader, listType); // <- MOD. HERE
        } catch (IOException e) {
            System.out.println("No se pudo leer/escribir el archivo: " + e.getMessage());
            e.printStackTrace();
        }
        return listadoControl;
    }

    public void actualizarControl(ArrayList<Control> listadoControl) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());
        gsonBuilder.registerTypeAdapter(TareaControl.class, new AbstractTareaControlAdapter());
        gsonBuilder.excludeFieldsWithModifiers(java.lang.reflect.Modifier.TRANSIENT);
        Gson gson = gsonBuilder.create();
        File file = new File(path + "controles.json"); // <- MOD. HERE
        try {
            //Serializo
            Type listType = new TypeToken<ArrayList<Control>>() {
            }.getType(); // <- MOD. HERE
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            gson.toJson(listadoControl, listType, bufferedWriter); // <- MOD. HERE
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("No se pudo leer/escribir el archivo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public ArrayList<Enfermedad> cargarEnfermedad() {

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());
        gsonBuilder.registerTypeAdapter(TareaControl.class, new AbstractTareaControlAdapter());
        gsonBuilder.excludeFieldsWithModifiers(java.lang.reflect.Modifier.TRANSIENT);
        Gson gson = gsonBuilder.create();
        ArrayList<Enfermedad> listadoEnfermedad = new ArrayList<>();
        File file = new File(path + "enfermedades.json"); // <- MOD. HERE
        try {
            Type listType = new TypeToken<ArrayList<Enfermedad>>() {
            }.getType(); // <- MOD. HERE
            //Deserializo
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            listadoEnfermedad = gson.fromJson(bufferedReader, listType); // <- MOD. HERE
        } catch (IOException e) {
            System.out.println("No se pudo leer/escribir el archivo: " + e.getMessage());
            e.printStackTrace();
        }
        return listadoEnfermedad;
    }

    public void actualizarEnfermedad(ArrayList<Enfermedad> listadoEnfermedad) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());
        gsonBuilder.registerTypeAdapter(TareaControl.class, new AbstractTareaControlAdapter());
        gsonBuilder.excludeFieldsWithModifiers(java.lang.reflect.Modifier.TRANSIENT);
        Gson gson = gsonBuilder.create();
        File file = new File(path + "enfermedades.json"); // <- MOD. HERE
        try {
            //Serializo
            Type listType = new TypeToken<ArrayList<Enfermedad>>() {
            }.getType(); // <- MOD. HERE
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            gson.toJson(listadoEnfermedad, listType, bufferedWriter); // <- MOD. HERE
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("No se pudo leer/escribir el archivo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public ArrayList<Especialidad> cargarEspecialidad() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());
        gsonBuilder.registerTypeAdapter(TareaControl.class, new AbstractTareaControlAdapter());
        gsonBuilder.excludeFieldsWithModifiers(java.lang.reflect.Modifier.TRANSIENT);
        Gson gson = gsonBuilder.create();
        ArrayList<Especialidad> listadoEspecialidad = new ArrayList<>();
        File file = new File(path + "especialidades.json"); // <- MOD. HERE
        try {
            Type listType = new TypeToken<ArrayList<Especialidad>>() {
            }.getType(); // <- MOD. HERE
            //Deserializo
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            listadoEspecialidad = gson.fromJson(bufferedReader, listType); // <- MOD. HERE
        } catch (IOException e) {
            System.out.println("No se pudo leer/escribir el archivo: " + e.getMessage());
            e.printStackTrace();
        }
        return listadoEspecialidad;
    }

    public void actualizarEspecialidad(ArrayList<Especialidad> listadoEspecialidad) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());
        gsonBuilder.registerTypeAdapter(TareaControl.class, new AbstractTareaControlAdapter());
        gsonBuilder.excludeFieldsWithModifiers(java.lang.reflect.Modifier.TRANSIENT);
        Gson gson = gsonBuilder.create();
        File file = new File(path + "especialidades.json"); // <- MOD. HERE
        try {
            //Serializo
            Type listType = new TypeToken<ArrayList<Especialidad>>() {
            }.getType(); // <- MOD. HERE
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            gson.toJson(listadoEspecialidad, listType, bufferedWriter); // <- MOD. HERE
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("No se pudo leer/escribir el archivo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

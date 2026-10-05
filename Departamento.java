public class Departamento {
    private String nombre;
    private String ubicacion;
    private Empleado[] empleados;
    private int numEmpleados;

    public Departamento(String nombre, String ubicacion) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.empleados = new Empleado[5]; // Límite máximo de 5 empleados por plantilla
        this.numEmpleados = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public int getNumEmpleados() {
        return numEmpleados;
    }

    public boolean agregarEmpleado(Empleado emp) {
        if (numEmpleados < 5) {
            empleados[numEmpleados] = emp;
            numEmpleados++;
            return true;
        }
        return false;
    }

    public Empleado buscarEmpleadoPorId(String id) {
        for (int i = 0; i < numEmpleados; i++) {
            if (empleados[i].getId().equalsIgnoreCase(id)) {
                return empleados[i];
            }
        }
        return null;
    }

    public void mostrarPlantilla() {
        System.out.println("\n--- Plantilla del Departamento: " + nombre + " ---");
        System.out.println("Ubicación: " + ubicacion);
        System.out.println("Total de Empleados: " + numEmpleados + "/5");
        if (numEmpleados == 0) {
            System.out.println("  (No hay empleados registrados)");
        } else {
            for (int i = 0; i < numEmpleados; i++) {
                System.out.println("  [" + (i + 1) + "] " + empleados[i].toString());
            }
        }
    }
}
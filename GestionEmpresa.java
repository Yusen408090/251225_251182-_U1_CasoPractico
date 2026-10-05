public class GestionEmpresa {
    private Departamento[] departamentos;
    private int numDepartamentos;

    public GestionEmpresa(int capacidadMax) {
        this.departamentos = new Departamento[capacidadMax];
        this.numDepartamentos = 0;
    }

    public boolean agregarDepartamento(Departamento dep) {
        if (numDepartamentos < departamentos.length) {
            departamentos[numDepartamentos] = dep;
            numDepartamentos++;
            return true;
        }
        return false;
    }

    public Departamento buscarDepartamentoPorNombre(String nombre) {
        for (int i = 0; i < numDepartamentos; i++) {
            if (departamentos[i].getNombre().equalsIgnoreCase(nombre)) {
                return departamentos[i];
            }
        }
        return null;
    }

    public Empleado consultarEmpleado(String nombreDep, String idEmp) {
        Departamento dep = buscarDepartamentoPorNombre(nombreDep);
        if (dep != null) {
            return dep.buscarEmpleadoPorId(idEmp);
        }
        return null;
    }

    public void listarDepartamentos() {
        if (numDepartamentos == 0) {
            System.out.println("No hay departamentos registrados.");
            return;
        }
        for (int i = 0; i < numDepartamentos; i++) {
            departamentos[i].mostrarPlantilla();
        }
    }
}
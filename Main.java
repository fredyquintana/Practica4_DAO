public class Main {
    public static void main(String[] args) {
        // Desde Main, instancia la interfaz de usuario y comprueba las operaciones CRUD
        UsuarioDAOArchivo usuarioDAOArchivo = new UsuarioDAOArchivo();
        InterfazUsuario interfazArchivo = new InterfazUsuario(usuarioDAOArchivo);

        UsuarioDAOMySQL usuarioDAOMySQL = new UsuarioDAOMySQL();
        InterfazUsuario interfazMySQL = new InterfazUsuario(usuarioDAOMySQL);

        
    }
}

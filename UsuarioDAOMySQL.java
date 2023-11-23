import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOMySQL implements UsuarioDAO {
    private Connection conexion;

    public UsuarioDAOMySQL() {
        // Configurar la conexión a la base de datos MySQL
        String url = "jdbc:mysql://localhost:3306/usuario";
        String usuario = "root";
        String contrasena = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, usuario, contrasena);
        } catch (ClassNotFoundException | SQLException e) {
            // Manejo de excepciones
            e.printStackTrace();
        }
    }

    @Override
    public void crearUsuario(Usuario usuario) {
        try (PreparedStatement ps = conexion.prepareStatement("INSERT INTO usuarios (nombre, apellido, correoElectronico, contrasena) VALUES (?, ?, ?, ?)")) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getCorreoElectronico());
            ps.setString(4, usuario.getContrasena());
            ps.executeUpdate();
        } catch (SQLException e) {
            // Manejo de excepciones
            e.printStackTrace();
        }
    }

    @Override
    public Usuario obtenerUsuarioPorId(int id) {
        // Implementar la búsqueda por ID en la base de datos
        return null;
    }

    @Override
    public List<Usuario> obtenerTodosLosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios")) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String correoElectronico = rs.getString("correoElectronico");
                String contrasena = rs.getString("contrasena");

                Usuario usuario = new Usuario(id, nombre, apellido, correoElectronico, contrasena);
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            // Manejo de excepciones
            e.printStackTrace();
        }
        return usuarios;
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        try (PreparedStatement ps = conexion.prepareStatement("UPDATE usuarios SET nombre=?, apellido=?, correoElectronico=?, contrasena=? WHERE id=?")) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getCorreoElectronico());
            ps.setString(4, usuario.getContrasena());
            ps.setInt(5, usuario.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            // Manejo de excepciones
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarUsuario(int id) {
        try (PreparedStatement ps = conexion.prepareStatement("DELETE FROM usuarios WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            // Manejo de excepciones
            e.printStackTrace();
        }
    }
}

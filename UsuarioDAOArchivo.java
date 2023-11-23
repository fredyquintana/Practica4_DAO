import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOArchivo implements UsuarioDAO {
    private List<Usuario> usuarios;
    private String archivo = "usuarios.txt";

    public UsuarioDAOArchivo() {
        usuarios = new ArrayList<>();
        cargarUsuariosDesdeArchivo();
    }

    private void cargarUsuariosDesdeArchivo() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            usuarios = (List<Usuario>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Manejo de excepciones
            e.printStackTrace();
        }
    }

    private void guardarUsuariosEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(usuarios);
        } catch (IOException e) {
            // Manejo de excepciones
            e.printStackTrace();
        }
    }

    @Override
    public void crearUsuario(Usuario usuario) {
        usuarios.add(usuario);
        guardarUsuariosEnArchivo();
    }

    @Override
    public Usuario obtenerUsuarioPorId(int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        return null; // Si no se encuentra el usuario con el ID proporcionado
    }

    @Override
    public List<Usuario> obtenerTodosLosUsuarios() {
        return new ArrayList<>(usuarios);
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId() == usuario.getId()) {
                usuarios.set(i, usuario); // Actualizar el usuario en la lista
                break;
            }
        }
        guardarUsuariosEnArchivo();
    }

    @Override
    public void eliminarUsuario(int id) {
        usuarios.removeIf(usuario -> usuario.getId() == id); // Eliminar el usuario con el ID proporcionado
        guardarUsuariosEnArchivo();
    }
}

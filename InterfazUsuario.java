import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class InterfazUsuario {
    private UsuarioDAO usuarioDAO;

    private JFrame frame;
    private JTextField idField;
    private JTextField nombreField;
    private JTextField apellidoField;
    private JTextField correoField;
    private JTextField contrasenaField;

    public InterfazUsuario(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
        crearInterfaz();
    }

    private void crearInterfaz() {
        frame = new JFrame("Gestión de Usuarios");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        frame.add(panel);
        lugarComponentes(panel);

        frame.setVisible(true);
    }

    private void lugarComponentes(JPanel panel) {
        panel.setLayout(null);

        JLabel label = new JLabel("ID:");
        label.setBounds(10, 20, 80, 25);
        panel.add(label);

        idField = new JTextField();
        idField.setBounds(100, 20, 165, 25);
        panel.add(idField);

        JLabel labelNombre = new JLabel("Nombre:");
        labelNombre.setBounds(10, 50, 80, 25);
        panel.add(labelNombre);

        nombreField = new JTextField();
        nombreField.setBounds(100, 50, 165, 25);
        panel.add(nombreField);

        JLabel labelApellido = new JLabel("Apellido:");
        labelApellido.setBounds(10, 80, 80, 25);
        panel.add(labelApellido);

        apellidoField = new JTextField();
        apellidoField.setBounds(100, 80, 165, 25);
        panel.add(apellidoField);

        JLabel labelCorreo = new JLabel("Correo:");
        labelCorreo.setBounds(10, 110, 80, 25);
        panel.add(labelCorreo);

        correoField = new JTextField();
        correoField.setBounds(100, 110, 165, 25);
        panel.add(correoField);

        JLabel labelContrasena = new JLabel("Contraseña:");
        labelContrasena.setBounds(10, 140, 80, 25);
        panel.add(labelContrasena);

        contrasenaField = new JTextField();
        contrasenaField.setBounds(100, 140, 165, 25);
        panel.add(contrasenaField);

        JButton crearButton = new JButton("Crear Usuario");
        crearButton.setBounds(10, 180, 150, 25);
        panel.add(crearButton);

        JButton leerButton = new JButton("Leer Usuario");
        leerButton.setBounds(170, 180, 150, 25);
        panel.add(leerButton);

        JButton actualizarButton = new JButton("Actualizar Usuario");
        actualizarButton.setBounds(10, 220, 150, 25);
        panel.add(actualizarButton);

        JButton eliminarButton = new JButton("Eliminar Usuario");
        eliminarButton.setBounds(170, 220, 150, 25);
        panel.add(eliminarButton);

        // Acciones de los botones
        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearUsuario();
            }
        });

        leerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leerUsuario();
            }
        });

        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarUsuario();
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarUsuario();
            }
        });
    }
  //limpia los campos de las cajas de texto
    private void limpiarCampos() {
        idField.setText("");
        nombreField.setText("");
        apellidoField.setText("");
        correoField.setText("");
        contrasenaField.setText("");
    }
    //Crear usuario

    private void crearUsuario() {
        try {
            int id = Integer.parseInt(idField.getText());
            String nombre = nombreField.getText();
            String apellido = apellidoField.getText();
            String correo = correoField.getText();
            String contrasena = contrasenaField.getText();

            Usuario nuevoUsuario = new Usuario(id, nombre, apellido, correo, contrasena);
            usuarioDAO.crearUsuario(nuevoUsuario);

            JOptionPane.showMessageDialog(frame, "Usuario creado exitosamente.");
            limpiarCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Ingrese un ID válido.");
        }
    }

    //Leer usuario

    private void leerUsuario() {
        try {
            int id = Integer.parseInt(idField.getText());
            Usuario usuario = usuarioDAO.obtenerUsuarioPorId(id);

            if (usuario != null) {
                nombreField.setText(usuario.getNombre());
                apellidoField.setText(usuario.getApellido());
                correoField.setText(usuario.getCorreoElectronico());
                contrasenaField.setText(usuario.getContrasena());
            } else {
                JOptionPane.showMessageDialog(frame, "Usuario no encontrado.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Ingrese un ID válido.");
        }
    }
    //Actualizar usuario

    private void actualizarUsuario() {
        try {
            int id = Integer.parseInt(idField.getText());
            String nombre = nombreField.getText();
            String apellido = apellidoField.getText();
            String correo = correoField.getText();
            String contrasena = contrasenaField.getText();

            Usuario usuario = new Usuario(id, nombre, apellido, correo, contrasena);
            usuarioDAO.actualizarUsuario(usuario);

            JOptionPane.showMessageDialog(frame, "Usuario actualizado exitosamente.");
            limpiarCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Ingrese un ID válido.");
        }
    }
    //Eliminar usuario

    private void eliminarUsuario() {
        try {
            int id = Integer.parseInt(idField.getText());
            usuarioDAO.eliminarUsuario(id);

            JOptionPane.showMessageDialog(frame, "Usuario eliminado exitosamente.");
            limpiarCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Ingrese un ID válido.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Aquí puedes instanciar la interfaz de usuario con el DAO correspondiente
                UsuarioDAOArchivo usuarioDAOArchivo = new UsuarioDAOArchivo();
                InterfazUsuario interfazArchivo = new InterfazUsuario(usuarioDAOArchivo);

                UsuarioDAOMySQL usuarioDAOMySQL = new UsuarioDAOMySQL();
                InterfazUsuario interfazMySQL = new InterfazUsuario(usuarioDAOMySQL);
            }
        });
    }
}

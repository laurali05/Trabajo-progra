package InterficieGrafica;

import javax.swing.*; // Se encarga de las ventanas 
import javax.swing.table.DefaultTableModel; // Para manejar tablas
import javax.swing.table.TableRowSorter; // Para filtrar tablas
import javax.swing.text.TableView.TableRow;

import java.awt.*; // Para organizar componente
import java.awt.event.*; // Para clicks y reclas

import Actividades.*;
import Data.Data;
import Excepcions.*;
import Usuari.*;
import netscape.javascript.JSException;
import Inscripcions.*;

public class Pantalla extends JFrame implements ActionListener {
    private JTable tablaActividades; // La tabla donde se ven las actividades
    private DefaultTableModel modeloTabla; // Los datos de la tabla
    private TableRowSorter<DefaultTableModel> sorter; // Para filtrar la tabla
    private JTextField txtFiltro; // Donde escribimos para buscar

    // Campos relacionados con el paquete Actividades
    private JTextField txtNomActividad, txtPeriodoIn, txtPeriodoFin, txtColectivos, txtFecha, txtHorario, txtLimite,
            txtPrecio, txtCiudad, txtNumSemanas, txtPeriodoVis, txtEnlace; // Campos donde podemos escribir
    private JTextArea txtNotas; // Campo donde podemos escribir más de una línea
    private JComboBox<String> comboTipos;
    private JButton btnGuardarActividad, btnEliminarActividad; // Botones que podemos clickar

    private LlistaActividades llistaActividades;
    private LlistaInscripcions llistaInscripcions;
    private LlistaUsuari llistaUsuari;

    /**
     * Constructor con parámetros
     * 
     * @param llistaActividades
     * @param llistaUsuari
     * @param llistaInscripcions
     */
    public Pantalla(LlistaActividades llistaActividades, LlistaUsuari llistaUsuari,
            LlistaInscripcions llistaInscripcions) {

        this.llistaActividades = llistaActividades;
        this.llistaUsuari = llistaUsuari;
        this.llistaInscripcions = llistaInscripcions;

        inicializar();
    }

    private void inicializar() {
        setTitle("Programa Bienestar URV - Gestión de Actividades");
        setSize(1400, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(crearPanelFiltro(), BorderLayout.NORTH);
        add(crearPanelTabla(), BorderLayout.CENTER);
        add(crearPanelDetalle(), BorderLayout.EAST);

        actualizarTabla();
        setVisible(true);
    }

    /**
     * Panel del filtro
     * 
     * @return
     */
    private JPanel crearPanelFiltro() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createTitledBorder("Buscar Actividades"));

        JLabel lblFiltro = new JLabel("Filtrar por nombre o tipo");
        lblFiltro.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(lblFiltro);

        txtFiltro = new JTextField(100); // Longitud de la barra de busqueda
        txtFiltro.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(txtFiltro);

        txtFiltro.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filtrarTabla();
            }
        });
        return panel;
    }

    /**
     * Panel de la tabla
     * 
     * @return
     */
    private JPanel crearPanelTabla() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Lista Actividades"));

        String[] columnas = { "Tipo", "Nombre Actividad", "Inicio Inscripción", "Fin Inscripción", "Colectivos" };
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int colum) {
                return false;
            }
        };

        tablaActividades = new JTable(modeloTabla);

        tablaActividades.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaActividades.setFont(new Font("Arial", Font.PLAIN, 12));
        tablaActividades.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tablaActividades.setRowHeight(25);

        sorter = new TableRowSorter<>(modeloTabla);
        tablaActividades.setRowSorter(sorter);

        tablaActividades.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tablaActividades.getSelectedRow() != -1) {
                cargarDetalleActividad();
            }
        });

        JScrollPane scrollPane = new JScrollPane(tablaActividades);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    /**
     * Panel de los detalles
     * 
     * @return
     */
    private JPanel crearPanelDetalle() {
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBorder(BorderFactory.createTitledBorder("Detalles Actividad"));
        panelPrincipal.setPreferredSize(new Dimension(450, 0));

        JPanel panelCampos = new JPanel();
        panelCampos.setLayout(new GridLayout(0, 2, 10, 10));
        panelCampos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelCampos.add(new JLabel("Nombre Actividad: "));
        txtNomActividad = new JTextField();
        panelCampos.add(txtNomActividad);

        panelCampos.add(new JLabel("Tipo: "));
        String[] tipos = { "Dia", "Periodica", "Online" };
        comboTipos = new JComboBox<>(tipos);
        comboTipos.setEnabled(false);
        panelCampos.add(comboTipos);

        panelCampos.add(new JLabel("Inicio Inscripción (DD/09-12/AAAA9: "));
        txtPeriodoIn = new JTextField();
        panelCampos.add(txtPeriodoIn);

        panelCampos.add(new JLabel("Fin Inscripción (DD/09-12/AAAA): "));
        txtPeriodoFin = new JTextField();
        panelCampos.add(txtPeriodoFin);

        panelCampos.add(new JLabel("Colectivos: "));
        txtColectivos = new JTextField();
        panelCampos.add(txtColectivos);

        panelCampos.add(new JSeparator());
        panelCampos.add(new JSeparator());

        panelCampos.add(new JLabel("Fecha Actividad (DD/09-12/AAAA): "));
        txtFecha = new JTextField();
        panelCampos.add(txtFecha);

        panelCampos.add(new JLabel("Horario (HH:MM): "));
        txtHorario = new JTextField();
        panelCampos.add(txtHorario);

        panelCampos.add(new JLabel("Límite Asistentes: "));
        txtLimite = new JTextField();
        panelCampos.add(txtLimite);

        panelCampos.add(new JLabel("Precio (€.cte): "));
        txtPrecio = new JTextField();
        panelCampos.add(txtPrecio);

        panelCampos.add(new JLabel("Ciudad: "));
        txtCiudad = new JTextField();
        panelCampos.add(txtCiudad);

        panelCampos.add(new JLabel("Num Semanas: "));
        txtNumSemanas = new JTextField();
        panelCampos.add(txtNumSemanas);

        panelCampos.add(new JLabel("Periodo Visualización (días): "));
        txtPeriodoVis = new JTextField();
        panelCampos.add(txtPeriodoVis);

        panelCampos.add(new JLabel("Enlace: "));
        txtEnlace = new JTextField();
        panelCampos.add(txtEnlace);

        // Notas adicionales
        panelCampos.add(new JLabel("Notas:"));
        txtNotas = new JTextArea(3, 20);
        txtNotas.setLineWrap(true);
        txtNotas.setWrapStyleWord(true);

        JScrollPane scrollNotas = new JScrollPane(txtNotas);
        panelCampos.add(scrollNotas);
        JScrollPane scrollPanel = new JScrollPane(panelCampos);
        scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panelPrincipal.add(scrollPanel, BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        btnGuardarActividad = new JButton("Guardar Cambios");
        btnGuardarActividad.setFont(new Font("Arial", Font.BOLD, 14));
        btnGuardarActividad.addActionListener(this);
        panelBotones.add(btnGuardarActividad);

        btnEliminarActividad = new JButton("Eliminar Actividad");
        btnEliminarActividad.setFont(new Font("Arial", Font.BOLD, 14));
        btnEliminarActividad.setBackground(new Color(255, 100, 100));
        btnEliminarActividad.addActionListener(this);
        panelBotones.add(btnEliminarActividad);

        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        return panelPrincipal;
    }

    /**
     * Método de filtrado
     * 
     */
    private void filtrarTabla() {
        String texto = txtFiltro.getText().trim();

        if (texto.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("?i" + texto));
        }
    }

    @Override
    /**
     * Conexión entre eventos
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGuardarActividad) {
            guardarCambiosActividad();
        } else if (e.getSource() == btnEliminarActividad) {
            eliminarActividad();
        }
    }

    /**
     * Cargar detalle desde la tabla
     */
    private void cargarDetalleActividad() {
        int fila = tablaActividades.getSelectedRow();

        if (fila == -1) {
            return;
        }

        int filaModelo = tablaActividades.convertRowIndexToModel(fila);
        String nomActividad = modeloTabla.getValueAt(filaModelo, 1).toString();

        try {
            Actividades act = llistaActividades.buscarActividades(nomActividad);

            txtNomActividad.setText(act.getNom());
            comboTipos.setSelectedItem(act.getTipo());
            txtPeriodoIn.setText(formatearData(act.getPeriodoIn()));
            txtPeriodoFin.setText(formatearData(act.getPeriodoFin()));
            txtColectivos.setText(act.getColectivos());

            limpiarCamposDetalle();

            if (act instanceof ActividadesDia) {
                ActividadesDia dia = (ActividadesDia) act;

                txtFecha.setText(formatearData(dia.getFechaIn()));
                txtHorario.setText(dia.getHorario());
                txtLimite.setText(String.valueOf(dia.getLimite()));
                txtPrecio.setText(String.valueOf(dia.getPrecio()));
                txtCiudad.setText(dia.getCiudad());

                txtNumSemanas.setEnabled(false);
                txtPeriodoVis.setEnabled(false);
                txtEnlace.setEnabled(false);

            } else if (act instanceof ActividadesPeriodicas) {
                ActividadesPeriodicas periodica = (ActividadesPeriodicas) act;

                txtFecha.setText(formatearData(periodica.getFechaIn()));
                txtLimite.setText(String.valueOf(periodica.getLimite()));

                txtHorario.setEnabled(false);
                txtPrecio.setEnabled(false);
                txtCiudad.setEnabled(false);
                txtPeriodoVis.setEnabled(false);
                txtEnlace.setEnabled(false);

            } else if (act instanceof ActividadesOnline) {
                ActividadesOnline online = (ActividadesOnline) act;

                txtFecha.setText(formatearData(online.getFechaIn()));
                txtPeriodoVis.setText(String.valueOf(online.getPeriodoVis()));
                txtEnlace.setText(online.getEnlace());

                txtHorario.setEnabled(false);
                txtLimite.setEnabled(false);
                txtPrecio.setEnabled(false);
                txtCiudad.setEnabled(false);
                txtNumSemanas.setEnabled(false);

            }

        } catch (ActivitatNoTrobadaException ex) {
            JOptionPane.showMessageDialog(this, "No se ha encontrado la actividad: " + ex.getMessage(), "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Guarda los cambios de una actividad
     */
    private void guardarCambiosActividad() {
        int fila = tablaActividades.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this,
                    "Selecciona una actividad primero",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!validarDadesActivitat()) {
            return;
        }

        int filaModelo = tablaActividades.convertRowIndexToModel(fila);
        String nomOriginal = modeloTabla.getValueAt(filaModelo, 1).toString();

        try {
            Actividades act = llistaActividades.buscarActividades(nomOriginal);

            act.setNom(txtNomActividad.getText().trim());
            act.setPeriodoIn(parsearData(txtPeriodoIn.getText()));
            act.setPeriodoFin(parsearData(txtPeriodoFin.getText()));
            act.setColectivos(txtColectivos.getText().trim());

            if (act instanceof ActividadesDia) {
                ActividadesDia dia = (ActividadesDia) act;
                dia.setFecha(parsearData(txtFecha.getText()));

                String[] horaMin = txtHorario.getText().split(":");
                if (horaMin.length == 2) {
                    int hora = Integer.parseInt(horaMin[0].trim());
                    int min = Integer.parseInt(horaMin[1].trim());
                    dia.setHorario(hora, min);
                }

                dia.setLimite(Integer.parseInt(txtLimite.getText().trim()));
                dia.setPrecio(Double.parseDouble(txtPrecio.getText().trim()));
                dia.setCiudad(txtCiudad.getText().trim());
            } else if (act instanceof ActividadesPeriodicas) {
                ActividadesPeriodicas periodica = (ActividadesPeriodicas) act;
                periodica.setFechaIn(parsearData(txtFecha.getText()));
                periodica.setLimite(Integer.parseInt(txtLimite.getText().trim()));
                periodica.setNumSemanas(Integer.parseInt(txtNumSemanas.getText().trim()));
            } else if (act instanceof ActividadesOnline) {
                ActividadesOnline online = (ActividadesOnline) act;
                online.setFechaIn(parsearData(txtFecha.getText()));
                online.setPeriodoVis(Integer.parseInt(txtPeriodoVis.getText().trim()));
                online.setEnlace(txtEnlace.getText().trim());
            }

            actualizarTabla();

            JOptionPane.showMessageDialog(this,
                    "Actividad actualizada correctamente",
                    "Salir",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (ActivitatNoTrobadaException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error en el formato de los números. Comprueba que todos los campos numéricos son correctos.",
                    "Error de formato",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Eliminar una actividad
     */
    private void eliminarActividad() {
        int fila = tablaActividades.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this,
                    "Selecciona una actividad primero",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int filaModelo = tablaActividades.convertRowIndexToModel(fila);
        String nomActivitat = modeloTabla.getValueAt(filaModelo, 1).toString();

        int confirmar = JOptionPane.showConfirmDialog(this,
                "Seguro que quieres eliminar la actividad '" + nomActivitat + "'?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (confirmar == JOptionPane.YES_OPTION) {
            llistaActividades.eliminarActividad(filaModelo);
            actualizarTabla();
            limpiarCamposDetalle();

            JOptionPane.showMessageDialog(this,
                    "Actividad eliminada correctamente",
                    "Salir",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Actualiza el contenido de la tabla
     */
    private void actualizarTabla() {
        modeloTabla.setRowCount(0);

        for (int i = 0; i < llistaActividades.getNumElems(); i++) {
            Actividades act = llistaActividades.getActividad(i);
            if (act != null) {
                modeloTabla.addRow(new Object[] {
                        act.getTipo(),
                        act.getNom(),
                        formatearData(act.getPeriodoIn()),
                        formatearData(act.getPeriodoFin()),
                        act.getColectivos()
                });
            }
        }
    }

    /**
     * Borra los datos que nos oson correctos
     * 
     * @param data
     * @return
     */
    private String formatearData(Data data) {
        if (data == null)
            return "";
        return String.format("%02d/%02d/%04d", data.getDia(), data.getMes(), data.getAny());
    }

    private Data parsearData(String texto) {
        try {
            String[] parts = texto.trim().split("/");
            if (parts.length == 3) {
                int dia = Integer.parseInt(parts[0]);
                int mes = Integer.parseInt(parts[1]);
                int any = Integer.parseInt(parts[2]);
                return new Data(dia, mes, any);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Formato de datos incorrecto. Usa DD/09-12/AAAA");
        }
        return new Data();
    }

    /**
     * Comprueba los datos de la actividad
     * 
     * @return
     */
    private boolean validarDadesActivitat() {
        if (txtNomActividad.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "El nombre de la actividad es obligatorio",
                    "Error de validación",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (txtPeriodoIn.getText().trim().isEmpty() || txtPeriodoFin.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Los periodos de inscripción son obligatorios",
                    "Error de validación",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (txtColectivos.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Los colectivos son obligatorios",
                    "Error de validación",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private void limpiarCamposDetalle() {
        txtNomActividad.setText("");
        txtPeriodoIn.setText("");
        txtPeriodoFin.setText("");
        txtColectivos.setText("");
        txtNotas.setText("");
        limpiarCamposEspecificos();
        habilitarTodosCampos();
        tablaActividades.clearSelection();
    }

    private void limpiarCamposEspecificos() {
        txtFecha.setText("");
        txtHorario.setText("");
        txtLimite.setText("");
        txtPrecio.setText("");
        txtCiudad.setText("");
        txtNumSemanas.setText("");
        txtPeriodoVis.setText("");
        txtEnlace.setText("");
    }

    private void habilitarTodosCampos() {
        txtFecha.setEnabled(true);
        txtHorario.setEnabled(true);
        txtLimite.setEnabled(true);
        txtPrecio.setEnabled(true);
        txtCiudad.setEnabled(true);
        txtNumSemanas.setEnabled(true);
        txtPeriodoVis.setEnabled(true);
        txtEnlace.setEnabled(true);
    }

    public static void main(String[] args) {
        // Crear datos de prueba
        LlistaActividades lista = new LlistaActividades(10);
        LlistaUsuari usuarios = new LlistaUsuari(5);
        LlistaInscripcions inscripciones = new LlistaInscripcions(20);

        try {
            // Actividad 1: Yoga
            ActividadesDia yoga = new ActividadesDia(
                    "Yoga para principiantes",
                    new Data(1, 9, 2025),
                    new Data(15, 9, 2025),
                    "PDI, PTGAS, Estudiants",
                    new Data(20, 9, 2025),
                    new int[] { 18, 30 },
                    30,
                    5.0,
                    "Tarragona");
            lista.afegir(yoga);

            // Actividad 2: Pilates
            ActividadesDia pilates = new ActividadesDia(
                    "Pilates avanzado",
                    new Data(1, 9, 2025),
                    new Data(20, 9, 2025),
                    "Estudiants",
                    new Data(25, 9, 2025),
                    new int[] { 19, 0 },
                    20,
                    8.0,
                    "Reus");
            lista.afegir(pilates);

            // Actividad 3: Meditación online
            ActividadesOnline meditacion = new ActividadesOnline(
                    "Meditación online",
                    new Data(1, 10, 2025),
                    new Data(15, 10, 2025),
                    "PDI, PTGAS, Estudiants",
                    new Data(20, 10, 2025),
                    30,
                    "https://meet.google.com/abc-defg");
            lista.afegir(meditacion);

        } catch (NomActivitatDuplicatException e) {
            System.err.println("Error: " + e.getMessage());
        }

        // Crear y mostrar la ventana
        SwingUtilities.invokeLater(() -> {
            new Pantalla(lista, usuarios, inscripciones);
        });
    }
}
package InterficieGrafica;

import Actividades.*; // Se encarga de las ventanas
import Data.Data; // Para manejar tablas
import Excepcions.*; // Para filtrar tablas
import Inscripcions.*;
import Lector.FitxerActividades;
import Lector.FitxerSerialitzat;
import Lector.FitxerUsuari;
import Usuari.*; // Para organizar componente
import java.awt.*; // Para clicks y reclas
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Pantalla extends JFrame implements ActionListener {

    private JTable tablaActividades; // La tabla donde se ven las actividades
    private DefaultTableModel modeloTabla; // Los datos de la tabla
    private TableRowSorter<DefaultTableModel> sorter; // Para filtrar la tabla
    private JTextField txtFiltro; // Donde escribimos para buscar

    // VARIABLES DEL CALENDARI
    private JPanel panelCalendariDies; // Panell per als dies
    private JLabel lblTitolMes;        // Etiqueta del mes i any
    // Comencem per defecte al Setembre 2025 (segons les dades de prova)
    private int mesActual = 9;
    private int anyActual = 2025;

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
        setTitle("Programa Benestar URV - Gestió d'Activitats");
        setSize(1400, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(crearPanelFiltro(), BorderLayout.NORTH);

        JTabbedPane pestanyes = new JTabbedPane();
        pestanyes.addTab("Calendari Mensual", crearPanelCalendari());
        pestanyes.addTab("Lista Actividades", crearPanelTabla());
        

        add(pestanyes, BorderLayout.CENTER);

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

        String[] columnas = {"Tipo", "Nombre Actividad", "Inicio Inscripción", "Fin Inscripción", "Colectivos"};
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
        String[] tipos = {"Dia", "Periodica", "Online"};
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
                modeloTabla.addRow(new Object[]{
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
        if (data == null) {
            return "";
        }
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

    private JPanel crearPanelCalendari() {
        JPanel panel = new JPanel(new BorderLayout());

        // Capçalera
        JPanel capçalera = new JPanel(new FlowLayout());
        JButton btnAnt = new JButton("<");
        JButton btnSeg = new JButton(">");
        lblTitolMes = new JLabel("", SwingConstants.CENTER);
        lblTitolMes.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitolMes.setPreferredSize(new Dimension(200, 30));

        btnAnt.addActionListener(this::accioMesAnterior);
        btnSeg.addActionListener(this::accioMesSeguent);

        capçalera.add(btnAnt);
        capçalera.add(lblTitolMes);
        capçalera.add(btnSeg);
        panel.add(capçalera, BorderLayout.NORTH);

        // Graella de dies 
        panelCalendariDies = new JPanel(new GridLayout(0, 7, 2, 2));
        panelCalendariDies.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JScrollPane scroll = new JScrollPane(panelCalendariDies);
        panel.add(scroll, BorderLayout.CENTER);

        dibuixarCalendari();
        return panel;
    }

    /**
     * Retrocedeix un mes
     */
    private void accioMesAnterior(ActionEvent e) {
        mesActual--;
        if (mesActual < 1) {
            mesActual = 12;
            anyActual--;
        }
        dibuixarCalendari();
    }

    /**
     * Avança un mes
     */
    private void accioMesSeguent(ActionEvent e) {
        mesActual++;
        if (mesActual > 12) {
            mesActual = 1;
            anyActual++;
        }
        dibuixarCalendari();
    }

    /**
     * Dibuixa els botons dels dies a la pantalla
     */
    private void dibuixarCalendari() {
        panelCalendariDies.removeAll();

        // Noms de mesos i dies en català
        String[] nomsMesos = {"", "Gener", "Febrer", "Març", "Abril", "Maig", "Juny", 
                                  "Juliol", "Agost", "Setembre", "Octubre", "Novembre", "Desembre"};
        String[] diesSetmana = {"Dll", "Dm", "Dc", "Dj", "Dv", "Ds", "Dg"};

        lblTitolMes.setText(nomsMesos[mesActual] + " " + anyActual);

        // Capçaleres de la setmana
        for (String d : diesSetmana) {
            JLabel l = new JLabel(d, SwingConstants.CENTER);
            l.setOpaque(true);
            l.setBackground(Color.LIGHT_GRAY);
            panelCalendariDies.add(l);
        }

    
        int totalDies = calcularDiesDelMes(mesActual, anyActual);
        int diaSetmanaInici = calcularDiaSetmana(1, mesActual, anyActual); // 0=Dilluns

   
        for (int i = 0; i < diaSetmanaInici; i++) {
            panelCalendariDies.add(new JLabel(""));
        }

      
        for (int dia = 1; dia <= totalDies; dia++) {
            JButton btn = new JButton(String.valueOf(dia));
            
          
            if (existeixActivitat(dia, mesActual, anyActual)) {
                btn.setBackground(new Color(100, 200, 255)); // Color Blau 
                btn.setFont(new Font("Arial", Font.BOLD, 12));
                btn.setToolTipText("Hi ha activitats programades");
            } else {
                btn.setBackground(Color.WHITE);
            }

            int d = dia;
            btn.addActionListener(evt -> mostrarDetallsDia(d));
            panelCalendariDies.add(btn);
        }

        panelCalendariDies.revalidate();
        panelCalendariDies.repaint();
    }

   
    private boolean esDeTraspas(int any) {
        return (any % 4 == 0 && any % 100 != 0) || (any % 400 == 0);
    }

    
    private int calcularDiesDelMes(int mes, int any) {
        if (mes == 4 || mes == 6 || mes == 9 || mes == 11) return 30;
        if (mes == 2) return esDeTraspas(any) ? 29 : 28;
        return 31;
    }


    private int calcularDiaSetmana(int dia, int mes, int any) {
        int m = mes;
        int y = any;
        
        if (m < 3) {
            m += 12;
            y -= 1;
        }
        
        int K = y % 100; 
        int J = y / 100; 
        
        int h = (dia + 13 * (m + 1) / 5 + K + K / 4 + J / 4 + 5 * J) % 7;
        
        int resultat = ((h + 5) % 7) + 1; 
        
        return resultat - 1; 
    }

    private boolean existeixActivitat(int dia, int mes, int any) {
        for (int i = 0; i < llistaActividades.getNumElems(); i++) {
            Actividades act = llistaActividades.getActividad(i);
            
            Data d = null;
            if (act instanceof ActividadesDia) {
                d = ((ActividadesDia) act).getFechaIn();
            } else if (act instanceof ActividadesPeriodicas) {
                d = ((ActividadesPeriodicas) act).getFechaIn();
            } else {
                d = ((ActividadesOnline) act).getFechaIn();
            }
            if (d != null && d.getDia() == dia && d.getMes() == mes && d.getAny() == any) {
                return true;
            }
        }
        return false;
    }

    private void mostrarDetallsDia(int dia) {
        String missatge = "Activitats del " + dia + "/" + mesActual + "/" + anyActual + ":\n\n";
        boolean hiHaActivitat = false;

        for (int i = 0; i < llistaActividades.getNumElems(); i++) {
            Actividades act = llistaActividades.getActividad(i);
            Data d = null;
            String extra = "";

            if (act instanceof ActividadesDia) {
                d = ((ActividadesDia) act).getFechaIn();
                extra = " (" + ((ActividadesDia) act).getHorario() + ")";
            } else if (act instanceof ActividadesPeriodicas) {
                 d = ((ActividadesPeriodicas) act).getFechaIn(); 
                 extra = " (Periòdica)";
            } else if (act instanceof ActividadesOnline) {
                 d = ((ActividadesOnline) act).getFechaIn();
                 extra = " (Online)";
            }

            if (d != null && d.getDia() == dia && d.getMes() == mesActual && d.getAny() == anyActual) {
                missatge += "- " + act.getNom() + extra + "\n";
                hiHaActivitat = true;
            }
        }

        if (!hiHaActivitat) missatge += "No hi ha activitats programades.";
        
        JOptionPane.showMessageDialog(this, missatge, "Agenda", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        // Crear datos de prueba
        LlistaActividades listaTmp = new LlistaActividades(1000);
        LlistaUsuari usuariosTmp = new LlistaUsuari(1000);
        LlistaInscripcions inscripcionesTmp = new LlistaInscripcions(1000);

        try {
            usuariosTmp = FitxerUsuari.carregarFitxeUsuari();
            listaTmp = FitxerActividades.carregarFitxerActividades();
            inscripcionesTmp = FitxerSerialitzat.carregarInscripcions();

        } catch (CarregarFitxersException e) {
            System.out.println(e + "Error al cargar els fitxers");

        }
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
            listaTmp.afegir(yoga);

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
            listaTmp.afegir(pilates);

            // Actividad 3: Meditación online
            ActividadesOnline meditacion = new ActividadesOnline(
                    "Meditación online",
                    new Data(1, 10, 2025),
                    new Data(15, 10, 2025),
                    "PDI, PTGAS, Estudiants",
                    new Data(20, 10, 2025),
                    30,
                    "https://meet.google.com/abc-defg");
            listaTmp.afegir(meditacion);

        } catch (NomActivitatDuplicatException e) {
            System.err.println("Error: " + e.getMessage());
        }

       final LlistaActividades lista = listaTmp;
       final LlistaUsuari usuarios = usuariosTmp;
       final LlistaInscripcions inscripciones = inscripcionesTmp;

    SwingUtilities.invokeLater(() -> {
        new Pantalla(lista, usuarios, inscripciones);
    });
    }
}

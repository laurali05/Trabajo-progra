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

public class Pantalla extends JFrame {

    private JTable tablaActividades; // La tabla donde se ven las actividades
    private DefaultTableModel modeloTabla; // Los datos de la tabla
    private TableRowSorter<DefaultTableModel> sorter; // Para filtrar la tabla
    private JTextField txtFiltro; // Donde escribimos para buscar

    // VARIABLES DEL CALENDARI
    private JPanel panelCalendariDies; // Panell per als dies
    private JLabel lblTitolMes; // Etiqueta del mes i any
    // Comencem per defecte al Setembre 2025 (segons les dades de prova)
    private int mesActual = 9;
    private int anyActual = 2025;

    private ListaActividad llistaActividades;
    private LlistaInscripcio llistaInscripcions;
    private LlistaUsuari llistaUsuari;

    /**
     * Constructor con parámetros
     *
     * @param llistaActividades
     * @param llistaUsuari
     * @param llistaInscripcions
     */
    public Pantalla(ListaActividad llistaActividades, LlistaUsuari llistaUsuari,
            LlistaInscripcio llistaInscripcions) {

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

        JScrollPane scrollPane = new JScrollPane(tablaActividades);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
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
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto));
        }
    }

    /**
     * Actualiza el contenido de la tabla
     */
    private void actualizarTabla() {
        modeloTabla.setRowCount(0);

        for (int i = 0; i < llistaActividades.getNumElems(); i++) {
            Actividad act = llistaActividades.getActividad(i);
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
     *Formatea la fecha
     * @param data
     * @return
     */
    private String formatearData(Data data) {
        if (data == null) {
            return "";
        }
        return String.format("%02d/%02d/%04d", data.getDia(), data.getMes(), data.getAny());
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
        String[] nomsMesos = { "", "Gener", "Febrer", "Març", "Abril", "Maig", "Juny",
                "Juliol", "Agost", "Setembre", "Octubre", "Novembre", "Desembre" };
        String[] diesSetmana = { "Dll", "Dm", "Dc", "Dj", "Dv", "Ds", "Dg" };

        lblTitolMes.setText(nomsMesos[mesActual] + " " + anyActual);

        // Capçaleres de la setmana
        for (String d : diesSetmana) {
            JLabel l = new JLabel(d, SwingConstants.CENTER);
            l.setOpaque(true);
            l.setBackground(Color.LIGHT_GRAY);
            panelCalendariDies.add(l);
        }

        int totalDies = Data.diesMes(mesActual, anyActual);
        int diaSetmanaInici = Data.calcularDiaSetmana(1, mesActual, anyActual); // 0=Dilluns

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

    /**
     * Métode que mira si existeix una activitat en el data o no
     * @param dia
     * @param mes
     * @param any
     * @return si exiteix o no l'activitat
     */
    private boolean existeixActivitat(int dia, int mes, int any) {
        for (int i = 0; i < llistaActividades.getNumElems(); i++) {
            Actividad act = llistaActividades.getActividad(i);

            Data d = null;
            if (act instanceof ActividadDia) {
                d = ((ActividadDia) act).getFechaIn();
            } else if (act instanceof ActividadPeriodicas) {
                d = ((ActividadPeriodicas) act).getFechaIn();
            } else {
                d = ((ActividadOnline) act).getFechaIn();
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
            Actividad act = llistaActividades.getActividad(i);
            Data d = null;
            String extra = "";

            if (act instanceof ActividadDia) {
                d = ((ActividadDia) act).getFechaIn();
                extra = " (" + ((ActividadDia) act).getHorario() + ")";
            } else if (act instanceof ActividadPeriodicas) {
                d = ((ActividadPeriodicas) act).getFechaIn();
                extra = " (Periòdica)";
            } else if (act instanceof ActividadOnline) {
                d = ((ActividadOnline) act).getFechaIn();
                extra = " (Online)";
            }

            if (d != null && d.getDia() == dia && d.getMes() == mesActual && d.getAny() == anyActual) {
                missatge += "- " + act.getNom() + extra + "\n";
                hiHaActivitat = true;
            }
        }

        if (!hiHaActivitat)
            missatge += "No hi ha activitats programades.";
        else {

        }

        JOptionPane.showMessageDialog(this, missatge, "Agenda", JOptionPane.INFORMATION_MESSAGE);

    }

    public static void main(String[] args) {
        // Crear datos de prueba
        ListaActividad listaTmp = new ListaActividad(1000);
        LlistaUsuari usuariosTmp = new LlistaUsuari(1000);
        LlistaInscripcio inscripcionesTmp = new LlistaInscripcio(1000);

        try {
            usuariosTmp = FitxerUsuari.carregarFitxeUsuari();
            listaTmp = FitxerActividades.carregarFitxerActividades();
            inscripcionesTmp = FitxerSerialitzat.carregarInscripcions();

        } catch (CarregarFitxersException e) {
            System.out.println(e + "Error al cargar els fitxers");

        }
        try {
            // Actividad 1: Yoga
            ActividadDia yoga = new ActividadDia("Dia",
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
            ActividadDia pilates = new ActividadDia("Dia",
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
            ActividadOnline meditacion = new ActividadOnline("Dia",
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
        } catch (NoHayEspacioException e){
            System.err.println(e.getMessage());
        } catch (FechaIncorrectaExcepcion e) {
            System.err.println(e.getMessage());
        }

        final ListaActividad lista = listaTmp;
        final LlistaUsuari usuarios = usuariosTmp;
        final LlistaInscripcio inscripciones = inscripcionesTmp;

        SwingUtilities.invokeLater(() -> {
            new Pantalla(lista, usuarios, inscripciones);
        });
    }
}

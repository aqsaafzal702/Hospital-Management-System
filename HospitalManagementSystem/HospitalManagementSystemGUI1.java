import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class HospitalManagementSystemGUI extends JFrame {
    private HospitalManagementSystem hms;
    private DefaultTableModel patientTableModel;
    private DefaultTableModel doctorTableModel;
    private DefaultTableModel appointmentTableModel;

    public HospitalManagementSystemGUI() {
        hms = new HospitalManagementSystem();

        // Setup JFrame
        setTitle("Hospital Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Title Label
        JLabel titleLabel = new JLabel("Hospital Management System", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // Main Panel
        JPanel mainPanel = new JPanel(new GridLayout(3, 1, 10, 10));

        // Buttons
        JButton patientButton = new JButton("Patient Dashboard");
        JButton doctorButton = new JButton("Doctor Dashboard");
        JButton appointmentButton = new JButton("Appointment Dashboard");

        // Add buttons to panel
        mainPanel.add(patientButton);
        mainPanel.add(doctorButton);
        mainPanel.add(appointmentButton);

        // Add panel to center
        add(mainPanel, BorderLayout.CENTER);

        // Button actions
        patientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openPatientDashboard();
            }
        });

        doctorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openDoctorDashboard();
            }
        });

        appointmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAppointmentDashboard();
            }
        });

        // Initialize table models
        patientTableModel = new DefaultTableModel();
        doctorTableModel = new DefaultTableModel();
        appointmentTableModel = new DefaultTableModel();

        // Example: Displaying all patients, doctors, and appointments
        JButton viewPatientsButton = new JButton("View All Patients");
        JButton viewDoctorsButton = new JButton("View All Doctors");
        JButton viewAppointmentsButton = new JButton("View All Appointments");

        viewPatientsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayPatients();
            }
        });

        viewDoctorsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayDoctors();
            }
        });

        viewAppointmentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayAppointments();
            }
        });

        JPanel viewPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        viewPanel.add(viewPatientsButton);
        viewPanel.add(viewDoctorsButton);
        viewPanel.add(viewAppointmentsButton);

        add(viewPanel, BorderLayout.SOUTH);
    }

    private void openPatientDashboard() {
        JDialog patientDialog = new JDialog(this, "Patient Dashboard", true);
        patientDialog.setSize(400, 300);
        patientDialog.setLayout(new GridLayout(7, 2));

        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField genderField = new JTextField();
        JTextField conditionField = new JTextField();

        patientDialog.add(new JLabel("ID:"));
        patientDialog.add(idField);
        patientDialog.add(new JLabel("Name:"));
        patientDialog.add(nameField);
        patientDialog.add(new JLabel("Age:"));
        patientDialog.add(ageField);
        patientDialog.add(new JLabel("Gender:"));
        patientDialog.add(genderField);
        patientDialog.add(new JLabel("Medical Condition:"));
        patientDialog.add(conditionField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText());
                    String name = nameField.getText();
                    int age = Integer.parseInt(ageField.getText());
                    String gender = genderField.getText();
                    String condition = conditionField.getText();

                    // Validate inputs
                    if (id <= 0 || age <= 0 || !nameValidation(name) || !genderValidation(gender) || condition.isEmpty()) {
                        JOptionPane.showMessageDialog(patientDialog, "Please enter valid data for all fields.");
                        return;
                    }

                    Patient patient = new Patient(id, name, age, gender, condition);
                    hms.addPatient(patient);
                    JOptionPane.showMessageDialog(patientDialog, "Patient added successfully.");
                    patientDialog.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(patientDialog, "Please enter numeric values for ID and Age.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(patientDialog, "Error: " + ex.getMessage());
                }
            }
        });
        patientDialog.add(saveButton);
        patientDialog.setVisible(true);
    }

    private void openDoctorDashboard() {
        JDialog doctorDialog = new JDialog(this, "Doctor Dashboard", true);
        doctorDialog.setSize(400, 300);
        doctorDialog.setLayout(new GridLayout(8, 2));

        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField genderField = new JTextField();
        JTextField specialtyField = new JTextField();
        JTextField shiftTimeField = new JTextField();

        doctorDialog.add(new JLabel("ID:"));
        doctorDialog.add(idField);
        doctorDialog.add(new JLabel("Name:"));
        doctorDialog.add(nameField);
        doctorDialog.add(new JLabel("Age:"));
        doctorDialog.add(ageField);
        doctorDialog.add(new JLabel("Gender:"));
        doctorDialog.add(genderField);
        doctorDialog.add(new JLabel("Specialty:"));
        doctorDialog.add(specialtyField);
        doctorDialog.add(new JLabel("Shift Time:"));
        doctorDialog.add(shiftTimeField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText());
                    String name = nameField.getText();
                    int age = Integer.parseInt(ageField.getText());
                    String gender = genderField.getText();
                    String specialty = specialtyField.getText();
                    String shiftTime = shiftTimeField.getText();

                    // Validate inputs
                    if (id <= 0 || age <= 0 || !nameValidation(name) || !genderValidation(gender) || specialty.isEmpty() || shiftTime.isEmpty()) {
                        JOptionPane.showMessageDialog(doctorDialog, "Please enter valid data for all fields.");
                        return;
                    }

                    Doctor doctor = new Doctor(id, name, age, gender, specialty, shiftTime);
                    hms.addDoctor(doctor);
                    JOptionPane.showMessageDialog(doctorDialog, "Doctor added successfully.");
                    doctorDialog.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(doctorDialog, "Please enter numeric values for ID and Age.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(doctorDialog, "Error: " + ex.getMessage());
                }
            }
        });
        doctorDialog.add(saveButton);
        doctorDialog.setVisible(true);
    }

    private void openAppointmentDashboard() {
        JDialog appointmentDialog = new JDialog(this, "Appointment Dashboard", true);
        appointmentDialog.setSize(400, 300);
        appointmentDialog.setLayout(new GridLayout(7, 2));

        JTextField idField = new JTextField();
        JTextField patientIdField = new JTextField();
        JTextField doctorIdField = new JTextField();
        JTextField dateField = new JTextField();
        JTextField timeField = new JTextField();

        appointmentDialog.add(new JLabel("ID:"));
        appointmentDialog.add(idField);
        appointmentDialog.add(new JLabel("Patient ID:"));
        appointmentDialog.add(patientIdField);
        appointmentDialog.add(new JLabel("Doctor ID:"));
        appointmentDialog.add(doctorIdField);
        appointmentDialog.add(new JLabel("Date:"));
        appointmentDialog.add(dateField);
        appointmentDialog.add(new JLabel("Time:"));
        appointmentDialog.add(timeField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText());
                    int patientId = Integer.parseInt(patientIdField.getText());
                    int doctorId = Integer.parseInt(doctorIdField.getText());
                    String date = dateField.getText();
                    String time = timeField.getText();

                    // Validate inputs
                    if (id <= 0 || patientId <= 0 || doctorId <= 0 || date.isEmpty() || time.isEmpty()) {
                        JOptionPane.showMessageDialog(appointmentDialog, "Please enter valid data for all fields.");
                        return;
                    }

                    Appointment appointment = new Appointment(id, patientId, doctorId, date, time);
                    hms.addAppointment(appointment);
                    JOptionPane.showMessageDialog(appointmentDialog, "Appointment added successfully.");
                    appointmentDialog.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(appointmentDialog, "Please enter numeric values for IDs.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(appointmentDialog, "Error: " + ex.getMessage());
                }
            }
        });
        appointmentDialog.add(saveButton);
        appointmentDialog.setVisible(true);
    }

    private void displayPatients() {
        // Fetch all patients from the hospital management system
        List<Patient> patients = hms.getAllPatients();

        // Clear previous table data
        patientTableModel.setColumnCount(0);
        patientTableModel.setRowCount(0);

        // Add columns to the table model
        patientTableModel.addColumn("ID");
        patientTableModel.addColumn("Name");
        patientTableModel.addColumn("Age");
        patientTableModel.addColumn("Gender");
        patientTableModel.addColumn("Medical Condition");

        // Add data rows to the table model
        for (Patient patient : patients) {
            patientTableModel.addRow(new Object[]{
                    patient.getId(),
                    patient.getName(),
                    patient.getAge(),
                    patient.getGender(),
                    patient.getMedicalCondition()
            });
        }

        // Create and display a table for patients
        JTable patientTable = new JTable(patientTableModel);
        JOptionPane.showMessageDialog(this, new JScrollPane(patientTable), "All Patients", JOptionPane.PLAIN_MESSAGE);
    }

    private void displayDoctors() {
        // Fetch all doctors from the hospital management system
        List<Doctor> doctors = hms.getAllDoctors();

        // Clear previous table data
        doctorTableModel.setColumnCount(0);
        doctorTableModel.setRowCount(0);

        // Add columns to the table model
        doctorTableModel.addColumn("ID");
        doctorTableModel.addColumn("Name");
        doctorTableModel.addColumn("Age");
        doctorTableModel.addColumn("Gender");
        doctorTableModel.addColumn("Specialty");
        doctorTableModel.addColumn("Shift Time");

        // Add data rows to the table model
        for (Doctor doctor : doctors) {
            doctorTableModel.addRow(new Object[]{
                    doctor.getId(),
                    doctor.getName(),
                    doctor.getAge(),
                    doctor.getGender(),
                    doctor.getSpecialty(),
                    doctor.getShiftTime()
            });
        }

        // Create and display a table for doctors
        JTable doctorTable = new JTable(doctorTableModel);
        JOptionPane.showMessageDialog(this, new JScrollPane(doctorTable), "All Doctors", JOptionPane.PLAIN_MESSAGE);
    }

    private void displayAppointments() {
        // Fetch all appointments from the hospital management system
        List<Appointment> appointments = hms.getAllAppointments();

        // Clear previous table data
        appointmentTableModel.setColumnCount(0);
        appointmentTableModel.setRowCount(0);

        // Add columns to the table model
        appointmentTableModel.addColumn("ID");
        appointmentTableModel.addColumn("Patient ID");
        appointmentTableModel.addColumn("Doctor ID");
        appointmentTableModel.addColumn("Date");
        appointmentTableModel.addColumn("Time");

        // Add data rows to the table model
        for (Appointment appointment : appointments) {
            appointmentTableModel.addRow(new Object[]{
                    appointment.getId(),
                    appointment.getPatientId(),
                    appointment.getDoctorId(),
                    appointment.getDate(),
                    appointment.getTime()
            });
        }

        // Create and display a table for appointments
        JTable appointmentTable = new JTable(appointmentTableModel);
        JOptionPane.showMessageDialog(this, new JScrollPane(appointmentTable), "All Appointments", JOptionPane.PLAIN_MESSAGE);
    }

    private boolean nameValidation(String name) {
         String word[] = name.split(" ");
        String fullName = "";
        boolean check = false;
        for (int i = 0; i <= word.length - 1; i++) {
            fullName = fullName + word[i];
        }
        for (int j = 0; j <= fullName.length() - 1; j++) {
            if(fullName.charAt(j) >= 'A' && fullName.charAt(j) <= 'Z' || fullName.charAt(j) >= 'a' && fullName.charAt(j) <= 'z'){
                check = true;
            }
            else{
                check = false;
                break;
            }
        }
        return !name.isEmpty();
    }

    private boolean genderValidation(String gender) {
        boolean check = false;
        String male = "male";
        String female = "female";
        if(gender.equalsIgnoreCase(male) || gender.equalsIgnoreCase(female)){
            check = true;
        }
        else{
            check = false;
        }
        return gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new HospitalManagementSystemGUI().setVisible(true);
        });
    }
}

import java.io.*;
import java.util.*;

public class HospitalManagementSystem {
    private List<Patient> patients;
    private List<Doctor> doctors;
    private List<Appointment> appointments;

    public HospitalManagementSystem() {
        patients = new ArrayList<>();
        doctors = new ArrayList<>();
        appointments = new ArrayList<>();
        loadPatientsFromFile();
        loadDoctorsFromFile();
        loadAppointmentsFromFile();
    }

    // Method to load patients from file
    private void loadPatientsFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("patientFile.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                Patient patient = new Patient(
                        Integer.parseInt(parts[0]), parts[1], Integer.parseInt(parts[2]),
                        parts[3], parts[4]);
                patients.add(patient);
            }
        } catch (IOException e) {
            System.out.println("Error loading patients: " + e.getMessage());
        }
    }

    // Method to load doctors from file
    private void loadDoctorsFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("doctorFile.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                Doctor doctor = new Doctor(
                        Integer.parseInt(parts[0]), parts[1], Integer.parseInt(parts[2]),
                        parts[3], parts[4], parts[5]);
                doctors.add(doctor);
            }
        } catch (IOException e) {
            System.out.println("Error loading doctors: " + e.getMessage());
        }
    }

    // Method to load appointments from file
    private void loadAppointmentsFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("appointmentFile.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                Appointment appointment = new Appointment(
                        Integer.parseInt(parts[0]), Integer.parseInt(parts[1]),
                        Integer.parseInt(parts[2]), parts[3], parts[4]);
                appointments.add(appointment);
            }
        } catch (IOException e) {
            System.out.println("Error loading appointments: " + e.getMessage());
        }
    }

    // Method to save patients to file
    private void savePatientsToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("patientFile.txt"))) {
            for (Patient patient : patients) {
                pw.println(patient.getId() + "," + patient.getName() + "," + patient.getAge() + ","
                        + patient.getGender() + "," + patient.getMedicalCondition());
            }
        } catch (IOException e) {
            System.out.println("Error saving patients: " + e.getMessage());
        }
    }

    // Method to save doctors to file
    private void saveDoctorsToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("doctorFile.txt"))) {
            for (Doctor doctor : doctors) {
                pw.println(doctor.getId() + "," + doctor.getName() + "," + doctor.getAge() + ","
                        + doctor.getGender() + "," + doctor.getSpecialty() + "," + doctor.getShiftTime());
            }
        } catch (IOException e) {
            System.out.println("Error saving doctors: " + e.getMessage());
        }
    }

    // Method to save appointments to file
    private void saveAppointmentsToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("appointmentFile.txt"))) {
            for (Appointment appointment : appointments) {
                pw.println(appointment.getId() + "," + appointment.getPatientId() + ","
                        + appointment.getDoctorId() + "," + appointment.getDate() + "," + appointment.getTime());
            }
        } catch (IOException e) {
            System.out.println("Error saving appointments: " + e.getMessage());
        }
    }

    // Method to fetch all patients
    public List<Patient> getAllPatients() {
        return patients;
    }

    // Method to fetch all doctors
    public List<Doctor> getAllDoctors() {
        return doctors;
    }

    // Method to fetch all appointments
    public List<Appointment> getAllAppointments() {
        return appointments;
    }

    // Method to add a patient
    public void addPatient(Patient patient) {
        patients.add(patient);
        savePatientsToFile();
    }

    // Method to add a doctor
    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
        saveDoctorsToFile();
    }

    // Method to add an appointment
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
        saveAppointmentsToFile();
    }
}

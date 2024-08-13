
public class Doctor {
    private int id;
    private String name;
    private int age;
    private String gender;
    private String specialty;
    private String shiftTime;

    public Doctor(int id, String name, int age, String gender, String specialty, String shiftTime) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.specialty = specialty;
        this.shiftTime = shiftTime;
    }

    // Getters and Setters
    public int getId() { 
        return id; 
        }
    public void setId(int id) { 
        this.id = id; 
        }
    public String getName() {
         return name; 
         }
    public void setName(String name) {
         this.name = name; 
         }
    public int getAge() {
         return age; 
         }
    public void setAge(int age) { 
        this.age = age; 
        }
    public String getGender() { 
        return gender;
         }
    public void setGender(String gender) { 
        this.gender = gender; 
        }
    public String getSpecialty() { 
        return specialty;
         }
    public void setSpecialty(String specialty) {
         this.specialty = specialty;
          }
    public String getShiftTime() { 
        return shiftTime; 
        }
    public void setShiftTime(String shiftTime) { 
        this.shiftTime = shiftTime; 
        }
}

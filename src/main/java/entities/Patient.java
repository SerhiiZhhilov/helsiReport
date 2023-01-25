package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    private String name;
    private Date date;
    private String phone;
    private String address;
    private String doctorName;
    private String FOP;

    public void setDateStr(String dateStr) {
        SimpleDateFormat sf = new SimpleDateFormat("dd.MM.yyyy");
        try {
            setDate(sf.parse(dateStr));
        } catch (ParseException e) {
            setDate(null);
        }
    }

    public void setValue(int count, String line) {
        switch (count) {
            case 0 : setName(line); break;
            case 1 : setDateStr(line);break;
            case 2 : setPhone(line);break;
            case 3 : setAddress(line);break;
            case 4 : setDoctorName(line);break;
            case 5 : setFOP(line);break;
        }
    }

}

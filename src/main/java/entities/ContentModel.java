package entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utils.Constans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Data
@AllArgsConstructor
public class ContentModel {


    private final List<String> content = new ArrayList<>();
    private final List<Patient> patients = new ArrayList<>();

    public void assignContent(List<String> content) {
        content.stream().forEach(this.content::add);
    }

    public void transformContentToPatients() {
        Iterator<String> iterator = content.iterator();
        int count = 0;
        Patient patient = null;
        while (iterator.hasNext()) {
            String line = iterator.next().trim();

            if (line.isEmpty() || line.length() < 3)
                continue;

            if (count == 0 ){
                patient = new Patient();
            }

            if (Constans.DELIMITER.equalsIgnoreCase(line)) {
                this.patients.add(patient);
                count = 0;
                continue;
            }
            patient.setValue(count++, line);
        }

        if (patient != null)
            this.patients.add(patient);

    }

    public void fillSheet(XSSFWorkbook wb, XSSFSheet sheet) {
        int iRow = 0;
        Row row = sheet.createRow(iRow);
        // create headers
        for (int iCol = 0; iCol < Constans.EXCEL_HEADERS.length; iCol++) {
            Cell cell = row.createCell(iCol);
            cell.setCellValue(Constans.EXCEL_HEADERS[iCol]);
        }
        // fill data
        Iterator<Patient> iterator = this.patients.iterator();
        while (iterator.hasNext()) {
            Patient patient = iterator.next();
            row = sheet.createRow(++iRow);
            int iCol = 0;

            Cell cell = row.createCell(iCol);
            setCellStringStyle(wb, cell);
            cell.setCellValue(patient.getName());

            cell = row.createCell(++iCol);
            setCellDateStyle(wb, cell);
            cell.setCellValue(patient.getDate());

            cell = row.createCell(++iCol);
            setCellStringStyle(wb, cell);
            cell.setCellValue(patient.getPhone());

            cell = row.createCell(++iCol);
            setCellStringStyle(wb, cell);
            cell.setCellValue(patient.getAddress());

            cell = row.createCell(++iCol);
            setCellStringStyle(wb, cell);
            cell.setCellValue(patient.getDoctorName());

            cell = row.createCell(++iCol);
            setCellStringStyle(wb, cell);
            cell.setCellValue(patient.getFOP());
        }
    }


    private void setCellDateStyle(XSSFWorkbook wb, Cell cell) {
        CellStyle cellStyle = wb.createCellStyle();
        CreationHelper createHelper = wb.getCreationHelper();
        cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd.mm.yyyy"));
        cell.setCellStyle(cellStyle);
    }

    private void setCellStringStyle(XSSFWorkbook wb, Cell cell) {
        CellStyle cellStyle = wb.createCellStyle();
        CreationHelper createHelper = wb.getCreationHelper();
        cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("@"));
        cell.setCellStyle(cellStyle);
    }

    public boolean isContentEmpty(){
        return content.isEmpty();
    }

    public boolean isPatientsEmpty(){
        return patients.isEmpty();
    }

    public boolean isEmpty() {
        return isContentEmpty() || isPatientsEmpty();
    }
}

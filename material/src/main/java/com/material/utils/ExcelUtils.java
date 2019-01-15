package com.material.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils{
    public static void main(String[] args) {
        String filePath = "D:\\materialFile\\2018-12-30\\a0e47562-2f63-4c74-a194-e070da3b4d13\\材料导入模板.xlsx";
        String[] columns = { "materialName", "productDate", "validdate" };
        readExcelByPath(filePath, columns);
    }

    private static List<Map<String, Object>> getExcelContent(Workbook wb, String[] columnArr){
        Sheet sheet = null;
        Row row = null;
        List list = null;
        String cellData = null;
        if (wb != null)
        {
            list = new ArrayList();

            sheet = wb.getSheetAt(0);

            int rownum = sheet.getPhysicalNumberOfRows();

            row = sheet.getRow(0);

            int colnum = row.getPhysicalNumberOfCells();
            for (int i = 1; i < rownum; i++) {
                Map map = new LinkedHashMap();
                row = sheet.getRow(i);
                if (row == null) break;
                for (int j = 0; j < colnum; j++) {
                    cellData = (String)getCellFormatValue(row.getCell(j));
                    map.put(columnArr[j], cellData);
                }

                list.add(map);
            }
        }
        return list;
    }

    public static List<Map<String, Object>> readExcelByStream(InputStream inputStream, String fileName, String[] columnArr){
        Workbook wb = null;
        try {
            if (fileName.endsWith("xls")) {
                wb = new HSSFWorkbook(inputStream);
            }
            else if (fileName.endsWith("xlsx")) {
                wb = new XSSFWorkbook(inputStream);
            }
            else {
                return null;
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getExcelContent(wb, columnArr);
    }

    public static List<Map<String, Object>> readExcelByPath(String filePath, String[] columnArr){
        Workbook wb = null;
        if (filePath == null) {
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        try {
            is = new FileInputStream(new File(filePath));
            if (".xls".equals(extString)) {
                wb = new HSSFWorkbook(is);
            } else if (".xlsx".equals(extString)) {
                wb = new XSSFWorkbook(is);
            } else {
                ArrayList localArrayList = new ArrayList();
                return localArrayList;
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return getExcelContent(wb, columnArr);
    }

    public static Object getCellFormatValue(Cell cell) {
        Object cellValue = null;
        if (cell != null) {
            switch (cell.getCellTypeEnum()){
                case FORMULA:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        cellValue = DateUtils.dateFormat(cell.getDateCellValue(), "yyyy-MM-dd"); break;
                    }
                    cellValue = String.valueOf(cell.getNumericCellValue());
                    break;
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        cellValue = DateUtils.dateFormat(cell.getDateCellValue(), "yyyy-MM-dd"); break;
                    }

                    cellValue = String.valueOf(cell.getNumericCellValue());

                    break;
                case STRING:
                    cellValue = cell.getStringCellValue();
                    break;
                default:
                    cellValue = ""; break;
            }
        }
        else cellValue = "";

        return cellValue;
    }
}
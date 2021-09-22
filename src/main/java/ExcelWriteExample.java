import java.io.*;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelWriteExample {
    private static FileInputStream fis;
    private static FileOutputStream fos;


    public static void main(String[] args) throws FileNotFoundException, IOException {
        //.xls 확장자 지원
        HSSFWorkbook wb = null;
        HSSFSheet sheet = null;
        Row row = null;
        Cell cell = null;

        //.xlsx 확장자 지원
        XSSFWorkbook xssfWb = null; // .xlsx
        XSSFSheet xssfSheet = null; // .xlsx
        XSSFRow xssfRow = null; // .xlsx
        XSSFCell xssfCell = null;// .xlsx

        try {
            if(new File("./resultdata.xlsx").exists()){
                System.out.println("파일이 존재합니다.");
                File file = new File("./resultdata.xlsx");
                fis = new FileInputStream(file);
                xssfWb = new XSSFWorkbook(fis);
                xssfSheet = xssfWb.createSheet("Sheet111");
                xssfRow = xssfSheet.createRow(0);
                xssfCell = xssfRow.createCell(0);
                xssfCell.setCellValue("AAA");
                fos = new FileOutputStream(file);
                xssfWb.write(fos);
                fos.flush();
                fos.close();
                System.out.println("add sheet Done");
            }else{
                // 워크북 생성
                xssfWb = new XSSFWorkbook();
                xssfSheet = xssfWb.createSheet("Sheet3");
                xssfRow = xssfSheet.createRow(0);
                xssfCell = xssfRow.createCell(0);
                xssfCell.setCellValue("QQQ");
                System.out.println("파일이 존재하지 않습니다.");
                fos = new FileOutputStream("./resultdata.xlsx");
                xssfWb.write(fos);
                fos.flush();
                fos.close();
                System.out.println("Done");
            }
        }
        catch(Exception e){

        }finally{

        }
    }
}

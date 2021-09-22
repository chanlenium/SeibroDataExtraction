package utils;

import model.BondCompanyModel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

@FunctionalInterface
public interface Write {
    void writeExcel(ArrayList<?> Companies, String sheetName) throws FileNotFoundException, IOException;
}

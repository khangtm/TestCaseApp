package com.example.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.EncryptionMode;
import org.apache.poi.poifs.crypt.Encryptor;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.apache.poi.xssf.usermodel.XSSFDataValidationConstraint;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.constant.CommonConstants;


public class ExcelUtil {

    public static final String XLSM = "xlsm";
    public static final String XLSX = "xlsx";
    public static final String DOT = ".";

    public static void copyRow(Sheet sheet, int sourceRowNum, int destinationRowNum) {
        int cellIndex = 0;
        // Get the source / new row
        Row destinationRow = sheet.getRow(destinationRowNum);
        Row sourceRow = sheet.getRow(sourceRowNum);

        if (destinationRow == null) {
            destinationRow = sheet.createRow(destinationRowNum);
        }

        // copy row height to new row
        destinationRow.setHeight(sourceRow.getHeight());

        // Loop through source columns to add to new row
        while (cellIndex < sourceRow.getLastCellNum()) {
            Cell newCell = destinationRow.createCell(cellIndex);
            Cell oldCell = sourceRow.getCell(cellIndex);

            // if old cell null then continue
            if (oldCell == null) {
                newCell = null;
                cellIndex++;
                continue;
            }

            // copy cell style to new cell
            CellStyle style = oldCell.getCellStyle();
            newCell.setCellStyle(style);

            // if cell is comment is copy it
            if (oldCell.getCellComment() != null) {
                newCell.setCellComment(oldCell.getCellComment());
            }

            // if cell is hyberlink
            if (oldCell.getHyperlink() != null) {
                newCell.setHyperlink(oldCell.getHyperlink());
            }

            // set cell data type
            newCell.setCellType(oldCell.getCellTypeEnum());

            //set cell data value
            switch (oldCell.getCellTypeEnum()) {
                case BOOLEAN:
                    newCell.setCellValue(oldCell.getBooleanCellValue());
                    break;
                case ERROR:
                    newCell.setCellErrorValue(oldCell.getErrorCellValue());
                    break;
                case FORMULA:
                    newCell.setCellFormula(oldCell.getCellFormula());
                    break;
                case NUMERIC:
                    newCell.setCellValue(oldCell.getNumericCellValue());
                    break;
                case STRING:
                    newCell.setCellValue(oldCell.getStringCellValue());
                    break;
                default:
                    break;
            }

            // If there are are any merged regions in the source row, copy to new row
            for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
                CellRangeAddress oldRange = sheet.getMergedRegion(i);
                if (oldRange.getFirstRow() == sourceRow.getRowNum()) {
                    CellRangeAddress newRange = new CellRangeAddress(destinationRow.getRowNum(),
                                    destinationRow.getRowNum() + (oldRange.getLastRow()
                                                    - oldRange.getFirstRow()),
                                    oldRange.getFirstColumn(), oldRange.getLastColumn());
                    if (!checkRangeWasMerged(sheet, newRange)) {
                        sheet.addMergedRegion(newRange);
                    }
                }
            }
            cellIndex++;
        }
    }

    public static boolean checkRangeWasMerged(Sheet sheet, CellRangeAddress rangeDestination) {
        for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
            CellRangeAddress rangeSrc = sheet.getMergedRegion(i);
            if (comparingRange(rangeDestination, rangeSrc)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param cell
     * @return
     */
    public static String getCellValue(final Cell cell) {
        final String data;
        //final int cellType = cell.getCellType();
        final CellType cellType = cell.getCellTypeEnum();
        switch (cellType) {
            case BOOLEAN:
            case NUMERIC:
            case STRING:
            case BLANK:
            case ERROR:
                data = getCellData(cell, cellType);
                break;
            case FORMULA:
                data = getCellData(cell, cell.getCachedFormulaResultTypeEnum());
                break;
            default:
                data = "";
                break;
        }
        return data;
    }

    /**
     * 繧ｻ繝ｫ縺ｫ險ｭ螳壹＆繧後※縺�繧九ョ繝ｼ繧ｿ繧貞叙蠕励＠縺ｾ縺吶��
     *
     * @param cell
     * @return
     */
    private static String getCellData(final Cell cell, final CellType cellType) {
        final String data;
        switch (cellType) {
            case BOOLEAN:
                boolean bdata = cell.getBooleanCellValue();
                data = String.valueOf(bdata);
                break;
            case NUMERIC:
                double ddata = cell.getNumericCellValue();
                if (DateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    data = DATE_FORMAT.format(ddata);
                    //data = analyzeDatetime(DateUtil.getJavaDate(ddata));
                } else {
                    DataFormatter df = new DataFormatter();
                    data = df.formatCellValue(cell);
                }
                break;
            case STRING:
                data = cell.getRichStringCellValue().getString();
                break;
            case FORMULA:
                cell.getCachedFormulaResultType();

                data = cell.getCellFormula();
                break;
            case ERROR:
                data = "";
                break;
            case BLANK:
            case _NONE:
            default:
                data = "";
                break;
        }
        return data;
    }

    private static boolean comparingRange(CellRangeAddress rangeDestination,
                    CellRangeAddress rangeSrc) {
        if (rangeDestination.getFirstColumn() == rangeSrc.getFirstColumn()) {
            if (rangeDestination.getFirstRow() == rangeSrc.getFirstRow()) {
                if (rangeDestination.getLastColumn() == rangeSrc.getLastColumn()) {
                    if (rangeDestination.getLastRow() == rangeSrc.getLastRow()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void copyRange(Sheet sheet, int firstRow, int lastRow, int rowIndex) {
        while (firstRow < lastRow) {
            copyRow(sheet, firstRow, rowIndex);
            rowIndex++;
            firstRow++;
        }
    }

    public static void addDropdownListToExcelFile(Sheet sheet, CellRangeAddressList range,
                    List<String> valuesInDropdownList){
        String[] arrayValue = (String[]) valuesInDropdownList
                        .toArray(new String[valuesInDropdownList.size()]);
        XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper((XSSFSheet) sheet);
        XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) dvHelper
                        .createExplicitListConstraint(arrayValue);
        if(dvConstraint.getFormula1().length() > 255){
            throw new IllegalArgumentException("Excel does not allow entering validation range string more than 255 characters");
        }
        XSSFDataValidation dv = (XSSFDataValidation) dvHelper.createValidation(dvConstraint, range);
        dv.setErrorStyle(DataValidation.ErrorStyle.STOP);
        dv.createErrorBox("", "");
        dv.setShowErrorBox(true);
        dv.setSuppressDropDownArrow(true);
        sheet.addValidationData(dv);
    }

    public static void addDropdownListNoneShowErrorBoxToExcelFile(Sheet sheet, CellRangeAddressList range,
                    List<String> valuesInDropdownList){
        String[] arrayValue = (String[]) valuesInDropdownList
                        .toArray(new String[valuesInDropdownList.size()]);
        XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper((XSSFSheet) sheet);
        XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) dvHelper
                        .createExplicitListConstraint(arrayValue);
        if(dvConstraint.getFormula1().length() > 255){
            throw new IllegalArgumentException("Excel does not allow entering validation range string more than 255 characters");
        }
        XSSFDataValidation dv = (XSSFDataValidation) dvHelper.createValidation(dvConstraint, range);
        dv.setErrorStyle(DataValidation.ErrorStyle.STOP);
        dv.createErrorBox("", "");
        dv.setShowErrorBox(false);
        dv.setSuppressDropDownArrow(true);
        sheet.addValidationData(dv);
    }

    public static void addDropdownListToExcelFileByFormula(Sheet sheet, CellRangeAddressList range,
                    String listFormula) {

        XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper((XSSFSheet) sheet);
        XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) dvHelper
                        .createFormulaListConstraint(listFormula);
        XSSFDataValidation dv = (XSSFDataValidation) dvHelper.createValidation(dvConstraint, range);
        dv.setErrorStyle(DataValidation.ErrorStyle.STOP);
        dv.createErrorBox("", "");
        dv.setShowErrorBox(true);
        dv.setSuppressDropDownArrow(true);
        sheet.addValidationData(dv);
    }

    public static void deleteRow(Sheet sheet, int rowIndex) {

        //sheet.removeRow(row);   // this only deletes all the cell values
        int lastRowNum = sheet.getLastRowNum();
        if (rowIndex >= 0 && rowIndex < lastRowNum) {
            sheet.shiftRows(rowIndex + 1, lastRowNum, -1);
        }
    }

    /*繝ｭ繝�繧ｯ縺輔ｌ縺ｦ縺�繧九そ繝ｫ縺悟性縺ｾ繧後ｋ陦後↓bottom border繧定ｨｭ螳壹☆繧九Γ繧ｽ繝�繝�
     * @param
     *  workbook:蟇ｾ雎｡workbook
     *  sheet:蟇ｾ雎｡繧ｷ繝ｼ繝�
     *  rowIndex:border繧定ｨｭ螳壹☆繧句ｯｾ雎｡陦�
     *  startCell: 髢句ｧ九そ繝ｫ
     *  endCell: 邨ゆｺ�繧ｻ繝ｫ
     * */
    public static void setBorderBottom(Workbook workbook, Sheet sheet, int rowIndex, int startCell,
                    int endCell) {
        Row row = sheet.createRow(rowIndex);
        CellStyle borderStyle = workbook.createCellStyle();
        borderStyle.setBorderTop(BorderStyle.MEDIUM);
        for (int i = startCell; i < endCell; i++) {
            row.createCell(i).setCellStyle(borderStyle);
        }
    }

    /*繝ｭ繝�繧ｯ縺輔ｌ縺ｦ縺�繧九そ繝ｫ繧貞性縺ｾ繧後↑縺�陦後↓bottom border繧定ｨｭ螳壹☆繧九Γ繧ｽ繝�繝�
     * @param
     *  workbook:蟇ｾ雎｡workbook
     *  sheet:蟇ｾ雎｡繧ｷ繝ｼ繝�
     *  rowIndex:border繧定ｨｭ螳壹☆繧句ｯｾ雎｡陦�
     *  startCell: 髢句ｧ九そ繝ｫ
     *  endCell: 邨ゆｺ�繧ｻ繝ｫ
     * */
    public static void setBorderBottomForUnlockedRow(Workbook workbook, Sheet sheet, int rowIndex, int startCell,
                    int endCell) {
        //蟇ｾ蜃ｦ縺ｮ陦後ｒ蜿悶ｊ蜃ｺ縺吶��
        Row row = sheet.getRow(rowIndex-1);
        for (int i = startCell; i < endCell; i++) {
            CellStyle currentStyle = row.getCell(i).getCellStyle();
            CellStyle newStyle = workbook.createCellStyle();
            newStyle.cloneStyleFrom(currentStyle);   //迴ｾ蝨ｨ縺ｮ繧ｹ繧ｿ繧､繝ｫ繧貞叙蠕励☆繧九��
            newStyle.setBorderBottom(BorderStyle.MEDIUM);// BorderBottom縺ｮ繧ｹ繧ｿ繧､繝ｫ繧堤ｽｮ縺肴鋤縺医ｋ縲�
            row.createCell(i).setCellStyle(newStyle);
        }
    }
    /*
     * @param
     *  workbook:蟇ｾ雎｡workbook
     *  sheet:蟇ｾ雎｡繧ｷ繝ｼ繝�
     *  rowIndex:border繧定ｨｭ螳壹☆繧句ｯｾ雎｡陦�
     *  startCell: 髢句ｧ九そ繝ｫ
     *  endCell: 邨ゆｺ�繧ｻ繝ｫ
     * */
    public static void setBorderBottomAndFillWhiteColor(Workbook workbook, Sheet sheet,
                    int rowIndex, int startCell, int endCell) {
        Row row = sheet.createRow(rowIndex);
        CellStyle borderStyle = workbook.createCellStyle();
        borderStyle.setBorderTop(BorderStyle.MEDIUM);
        borderStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        borderStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        for (int i = startCell; i < endCell; i++) {
            row.createCell(i).setCellStyle(borderStyle);
        }
    }

    /**
     * Read workbook of excel file
     * @param directory 蜃ｺ蜉帛�医ョ繧｣繝ｬ繧ｯ繝医Μ縲�
     * @param fileName 繝輔ぃ繧､繝ｫ蜷阪��
     * @return Workbook
     * @throws IOException - 蜈･蜃ｺ蜉帙↓髢｢騾｣縺励◆萓句､悶�ｮ縺ｩ繧後°縺檎匱逕溘＠縺溷�ｴ蜷医��
     */
    public static Workbook readExcelFile(String directory, String fileName) throws IOException {
        final File file = new File(directory + CommonConstants.FILE_SLASH + fileName);
        final FileInputStream inputStream = new FileInputStream(file);
        final Workbook workbook = new XSSFWorkbook(inputStream);
        inputStream.close();
        return workbook;
    }

    /**
     * Read workbook of excel file
     * @param filePath縲�
     * @return Workbook
     * @throws IOException - 蜈･蜃ｺ蜉帙↓髢｢騾｣縺励◆萓句､悶�ｮ縺ｩ繧後°縺檎匱逕溘＠縺溷�ｴ蜷医��
     */
    public static Workbook readExcelFile(String filePath) throws IOException {
        final File file = new File(filePath);
        final FileInputStream inputStream = new FileInputStream(file);
        final Workbook workbook = new XSSFWorkbook(inputStream);
        inputStream.close();
        return workbook;
    }

    public static Workbook readExcelFileProtect(String directory, String fileName, String password) throws IOException {
        final File file = new File(directory + CommonConstants.FILE_SLASH + fileName);
        final FileInputStream inputStream = new FileInputStream(file);
        Workbook workbook = null;

        POIFSFileSystem fs = new POIFSFileSystem(inputStream);
        EncryptionInfo info = new EncryptionInfo(fs);
        Decryptor dec = Decryptor.getInstance(info);
        try {
            if(dec.verifyPassword(password)){
                workbook = new XSSFWorkbook(dec.getDataStream(fs));
            }
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        inputStream.close();
        return workbook;
    }

    public static boolean verifyPassword(String path, String password) {
        try {
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(path));
            EncryptionInfo info = new EncryptionInfo(fs);

            Decryptor dec = Decryptor.getInstance(info);
            if(dec.verifyPassword(password)){
                return true;
            }else{
                return false;
            }
        } catch (IOException | GeneralSecurityException e) {
            return false;
        }
    }

    @SuppressWarnings("resource")
    public static boolean isExcelEncrypted(String path) {
        try {
            new POIFSFileSystem(new FileInputStream(path));
            return true;
        } catch (OfficeXmlFileException | IOException  e) {
            return false;
        }
    }

    /**
     * Write workbook into excel file
     * @param workbook
     * @param directory 蜃ｺ蜉帛�医ョ繧｣繝ｬ繧ｯ繝医Μ縲�
     * @param fileName 繝輔ぃ繧､繝ｫ蜷阪��
     * @return
     * @throws IOException - 蜈･蜃ｺ蜉帙↓髢｢騾｣縺励◆萓句､悶�ｮ縺ｩ繧後°縺檎匱逕溘＠縺溷�ｴ蜷医��
     */
    public static void writeExcelFile(Workbook workbook, String directory, String fileName)
                    throws IOException {
        final File file = new File(directory + CommonConstants.FILE_SLASH + fileName);
        final FileOutputStream outStream = new FileOutputStream(file);
        workbook.write(outStream);
        workbook.close();
        outStream.close();

    }

    

    /**
     * Download excel file
     * @param inputPath
     * @param outputPath 蜃ｺ蜉帛�医ョ繧｣繝ｬ繧ｯ繝医Μ縲�
     * @return
     * @throws IOException - 蜈･蜃ｺ蜉帙↓髢｢騾｣縺励◆萓句､悶�ｮ縺ｩ繧後°縺檎匱逕溘＠縺溷�ｴ蜷医��
     * @throws InvalidFormatException 
     */
    public static void downloadExcelFile(String inputPath, String outputPath)
                    throws IOException {
        FileInputStream inputStream = new FileInputStream(new File(inputPath));
        Workbook workbook = new XSSFWorkbook(inputStream);
        inputStream.close();

        FileOutputStream outputStream = new FileOutputStream(new File(outputPath));
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

    public static void main(String[] args)  {

    }

    public static void copyRangeWithRichText(Sheet sheet, int firstRow, int lastRow, int rowIndex) {
        while (firstRow < lastRow) {
            copyRowWithRichText(sheet, firstRow, rowIndex);
            rowIndex++;
            firstRow++;
        }
    }

    private static void copyRowWithRichText(Sheet sheet, int sourceRowNum, int destinationRowNum) {
        int cellIndex = 0;
        // Get the source / new row
        Row destinationRow = sheet.getRow(destinationRowNum);
        Row sourceRow = sheet.getRow(sourceRowNum);

        if (destinationRow == null) {
            destinationRow = sheet.createRow(destinationRowNum);
        }

        // copy row height to new row
        destinationRow.setHeight(sourceRow.getHeight());

        // Loop through source columns to add to new row
        while (cellIndex < sourceRow.getLastCellNum()) {
            Cell newCell = destinationRow.createCell(cellIndex);
            Cell oldCell = sourceRow.getCell(cellIndex);

            // if old cell null then continue
            if (oldCell == null) {
                newCell = null;
                cellIndex++;
                continue;
            }

            // copy cell style to new cell
            CellStyle style = oldCell.getCellStyle();
            newCell.setCellStyle(style);

            // if cell is comment is copy it
            if (oldCell.getCellComment() != null) {
                newCell.setCellComment(oldCell.getCellComment());
            }

            // if cell is hyberlink
            if (oldCell.getHyperlink() != null) {
                newCell.setHyperlink(oldCell.getHyperlink());
            }

            // set cell data type
            newCell.setCellType(oldCell.getCellTypeEnum());

            //set cell data value
            switch (oldCell.getCellTypeEnum()) {
                case BOOLEAN:
                    newCell.setCellValue(oldCell.getBooleanCellValue());
                    break;
                case ERROR:
                    newCell.setCellErrorValue(oldCell.getErrorCellValue());
                    break;
                case FORMULA:
                    newCell.setCellFormula(oldCell.getCellFormula());
                    break;
                case NUMERIC:
                    newCell.setCellValue(oldCell.getNumericCellValue());
                    break;
                case STRING:
                    newCell.setCellValue(oldCell.getRichStringCellValue());
                    break;
                default:
                    break;
            }

            // If there are are any merged regions in the source row, copy to new row
            for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
                CellRangeAddress oldRange = sheet.getMergedRegion(i);
                if (oldRange.getFirstRow() == sourceRow.getRowNum()) {
                    CellRangeAddress newRange = new CellRangeAddress(destinationRow.getRowNum(),
                                    destinationRow.getRowNum() + (oldRange.getLastRow()
                                                    - oldRange.getFirstRow()),
                                    oldRange.getFirstColumn(), oldRange.getLastColumn());
                    if (!checkRangeWasMerged(sheet, newRange)) {
                        sheet.addMergedRegion(newRange);
                    }
                }
            }
            cellIndex++;
        }
    }
    
    /**
     * Get excel files in directory  
     * @param directory
     * @return files
     */
    public static List<File> getExcelFiles(String directory) {
        
        List<File> files = new ArrayList<>();
        File folder = new File(directory);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile() && (file.getName().toLowerCase().endsWith(ExcelUtil.XLSX)
                            || file.getName().toLowerCase().endsWith(ExcelUtil.XLSM))) {
            	if (validTemplateHiddenCell(file.getAbsolutePath())) {
            		files.add(file);
                }
            }
        }
        return files;
    }
    
    /**
     * validate Template Hidden Cell
     * @param filePath
     * @param testaceExternalJobId
     * @return true if isValidFile, else false
     */
    private static boolean validTemplateHiddenCell(String filePath) {
        File file = null;
        boolean isValidFile = true;
        /*Workbook workbook;
        try {
            file = new File(filePath);
            workbook = readExcelFile(file.getParent(), file.getName());
            if (workbook != null && workbook.getNumberOfSheets() >= 2){
                Sheet sheet = workbook.getSheetAt(1);
                CellReference cellReference = new CellReference(ExcelConstants.CELL_HIDDEN);
                Cell hiddenCell = sheet.getRow(cellReference.getRow()).getCell(cellReference.getCol());
                if (testaceExternalJobId.equals(ExcelUtil.getCellValue(hiddenCell))) {
                    isValidFile = true;
                }
            }

        } catch (IOException | IllegalArgumentException | NullPointerException e) {            
            return false;
        }*/
        return isValidFile;
    }

}

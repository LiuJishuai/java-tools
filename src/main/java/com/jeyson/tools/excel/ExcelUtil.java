package com.jeyson.tools.excel;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: liujishuai
 * @Time: 2019-08-09 19:27
 * @Description:
 */
public class ExcelUtil {


    public static <T> byte[] getListToExcelBytes(List<List<T>> list) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        Cell cell = null;
        ByteArrayOutputStream buffout = null;
        byte[] bytes = null;
        try {
            for (int i = 0; i < list.size(); ++i) {
                Row row = sheet.createRow(i);
                List<T> values = list.get(i);
                for (int j = 0; j < values.size(); ++j) {
                    cell = row.createCell(j);
                    if (values.get(j) != null) {
                        cell.setCellValue(values.get(j).toString());
                    } else {
                        cell.setCellValue("");
                    }
                }
            }
            buffout = new ByteArrayOutputStream();
            workbook.write(buffout);
            buffout.flush();
            bytes = buffout.toByteArray();
        } catch (Exception e) {

        } finally {
            if (null != buffout) {
                try {
                    buffout.close();
                } catch (IOException e) {
                }
            }

        }
        return bytes;
    }

    private static Workbook getHSSFWorkbook(InputStream stream) throws IOException {
        return new HSSFWorkbook(stream);
    }

    private static Workbook getXSSFWorkbook(InputStream stream) throws IOException {
        return new XSSFWorkbook(stream);
    }

    public static Workbook getWorkbook(InputStream stream, String fileName) {
        try {
            String fileExt = getFileExt(fileName);
            if(".xls".equalsIgnoreCase(fileExt)) {
                return getHSSFWorkbook(stream);
            } else if(".xlsx".equalsIgnoreCase(fileExt)) {
                return getXSSFWorkbook(stream);
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if(null != stream) {
                try {
                    stream.close();
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static Workbook getWorkbook(File file) {
        if(null == file) {
            return null;
        }

        InputStream stream = null;
        try {
            stream = new FileInputStream(file);
            String fileExt = getFileExt(file.getName());
            if(".xls".equalsIgnoreCase(fileExt)) {
                return getHSSFWorkbook(stream);
            } else if(".xlsx".equalsIgnoreCase(fileExt)) {
                return getXSSFWorkbook(stream);
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if(null != stream) {
                try {
                    stream.close();
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    /**
     * excel转变成list(指定sheet指定行开始封装，默认为第0个sheet的第0行)
     *
     * @return
     */
    public static List<List<Object>> getExcelToList(Workbook workbook, Integer... idx) {
        List<List<Object>> result = new ArrayList<List<Object>>();
        Sheet sheet = null;
        if(null==idx) {
            sheet = workbook.getSheetAt(0);
        } else {
            sheet = workbook.getSheetAt(idx[0]);
        }
        int i = 0;
        if(null!=idx && idx.length > 1) {
            i = idx[1];
        }

        for(; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            Object value = null;
            List<Object> values = new ArrayList<Object>();
            if(null!=row) {
                for(int j = 0; j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j);

                    if(null==cell) {
                        value = "";
                    } else {
                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_NUMERIC :
                                if(DateUtil.isCellDateFormatted(cell)){
                                    value=cell.getDateCellValue();
                                }else {
                                    value = cell.getNumericCellValue();
                                }
                                break;
                            case Cell.CELL_TYPE_STRING :
                                value = cell.getStringCellValue();
                                break;
                            case Cell.CELL_TYPE_BOOLEAN :
                                value = String.valueOf(cell.getBooleanCellValue());
                                break;
                            //							case Cell.CELL_TYPE_BLANK :
                            //							case Cell.CELL_TYPE_ERROR :
                            //							case Cell.CELL_TYPE_FORMULA :
                            //								value = "";
                            //								break;
                            default :
                                value = "";
                                break;
                        }
                    }
                    values.add(value);
                }

                if(null!=values) {
                    result.add(values);
                }
            }
        }

        return result;
    }

    /**
     * list转变成excel(带样式)
     *
     * @return
     * @throws IOException
     */
    public static void getListToExcel(List<List<String>> list, String outpath, List<List<CellStyle>> cellstyle) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        Cell cell = null;
        for(int i = 0; i < list.size(); i++) {
            Row row = sheet.createRow(i);
            List<String> values = list.get(i);
            for(int j = 0; j < values.size(); j++) {
                cell = row.createCell(j);
                cell.setCellValue(values.get(j));
                if(null!=cellstyle) {
                    cell.setCellStyle(cellstyle.get(i).get(j));
                }
            }
        }
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(outpath);
            workbook.write(fOut);
            fOut.flush();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(null != fOut) {
                try {
                    fOut.close();
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * list转变成excel(不带样式)
     *
     * @return
     * @throws IOException
     */
    public static void getListToExcel(List<List<String>> list, String outpath) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        Cell cell = null;
        for(int i = 0; i < list.size(); i++) {
            Row row = sheet.createRow(i);
            List<String> values = list.get(i);
            for(int j = 0; j < values.size(); j++) {
                cell = row.createCell(j);
                cell.setCellValue(values.get(j));
            }
        }
        FileOutputStream fOut = null;
        try {
            File dir = new File(outpath);
            if(!dir.getParentFile().exists()){
                dir.getParentFile().mkdirs();
            }
            fOut = new FileOutputStream(outpath);
            workbook.write(fOut);
            fOut.flush();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(null != fOut) {
                try {
                    fOut.close();
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * 获取文件扩展名
     *
     * @param fileName
     * @return
     */
    public static String getFileExt(String fileName) {
        if(StringUtils.isBlank(fileName)) {
            return "";
        }

        int index = fileName.lastIndexOf(".");
        if(-1 == index) {
            throw new IllegalArgumentException("illegal filename[" + fileName + "]");
        }

        return fileName.substring(index);
    }

    public static String numberFormat(Number number, String... pattern) {
        if (null==pattern|| pattern.length == 0) {
            return FORMAT.format(number);
        }

        return FORMAT.format(pattern[0]);
    }
    private static Format FORMAT = new DecimalFormat("#.##");

}

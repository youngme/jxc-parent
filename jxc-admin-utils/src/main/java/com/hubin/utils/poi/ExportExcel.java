package com.hubin.utils.poi;

import com.hubin.dto.system.SalaryExcelDTO;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <br>
 *
 * @author hubin
 * @title: 导出Excel文件
 * @description:
 * @date: 2019/4/9 23:13
 */
@Component
public class ExportExcel<T> {

    private final Logger logger = LoggerFactory.getLogger(ExportExcel.class);
    //TODO 改变Excel导出路径
    private final static String EXCEL_MODEL_PATH = "/data/model/poi/excel";
    private final static String EXCEL_MODEL_FILE = File.separator + "salary_model.xlsx";
    public void exportXlsxExcelForData(List<T> list,
                                       HttpServletRequest request, HttpServletResponse response,String... fieldNames) {
        Workbook wb = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            try {
                String fileName = request.getParameter("fileName");
                //TODO 改变Excel导出路径
                File file = new File(ResourceUtils.getFile("classpath:poi")
                        + EXCEL_MODEL_FILE);
//                File file = new File(EXCEL_MODEL_PATH+ EXCEL_MODEL_FILE);
                if (!file.exists()) {
                    System.err.println("文件不存在");
                }
                //加载Excel模版
                wb = new XSSFWorkbook(new FileInputStream(file));
                //获取指定的Sheet页
                Sheet sheet = wb.getSheetAt(0);
                //设置表头
                sheet.getRow(0).getCell(0).setCellValue(fileName);
                //指定rowIndex
                AtomicInteger rowIndex = new AtomicInteger(3);
                AtomicInteger index = new AtomicInteger(1);

                list.stream().forEach(t -> {
                    Row row = sheet.getRow(rowIndex.get());
                    if (Objects.isNull(row)) {
                        row = sheet.createRow(rowIndex.get());
                    }

                    setCellValues(t, row, index.get(),sheet.getRow(3),fieldNames);
                    rowIndex.getAndIncrement();
                    index.getAndIncrement();
                });
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                wb.write(baos);
                byte[] contents = baos.toByteArray();
                InputStream is = new ByteArrayInputStream(contents);

                response.reset();
                response.setContentType("application/vnd.ms-excel;charset=utf-8");
                response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName + ".xlsx").getBytes(), "iso-8859-1"));

                ServletOutputStream sout = response.getOutputStream();
                bis = new BufferedInputStream(is);
                bos = new BufferedOutputStream(sout);

                byte[] buff = new byte[2048];
                int bytesRead;
                // Simple read/write loop.
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }
            } catch (IOException e) {
                System.err.println("Excel导出异常");
                logger.error("Excel导出异常", e);
                e.printStackTrace();
            }finally {
                if (bis != null)
                    bis.close();
                if (bos != null)
                    bos.close();
                if (wb != null)
                    wb.close();
            }
        } catch (IOException e) {
            System.err.println("Excel导出异常");
            logger.error("Excel导出异常", e);
            e.printStackTrace();
        }

    }


    private Row setCellValues(T t, Row row,int index,Row styleRow,String...fieldNames) {
        //获取所有属性
        Field[] fields = t.getClass().getDeclaredFields();

        //第1列
        Cell cell0 = row.getCell(0);
            if (Objects.isNull(cell0)) {
            cell0 = row.createCell(0);
        }
        CellStyle cellStyle0 =styleRow.getCell(0).getCellStyle();
        cell0.setCellStyle(cellStyle0);
        cell0.setCellValue(index);
        int continueCount = 0;
        for (int i = 0; i < fields.length; i++) {
            String fieldName = fields[i].getName();
            if (fieldNames[fieldNames.length-1].equals(fieldName)) {
                continueCount++;
                continue;
            }
            int cellIndex = i-continueCount+1;
            Cell cell = row.getCell(cellIndex);
            if (Objects.isNull(cell)) {
                cell = row.createCell(cellIndex);
            }

            String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            try {
                Method method = t.getClass().getMethod(getMethodName,new Class[]{});
                Object fieldValue = method.invoke(t, new Object[]{}).toString();
                String cellValue;
                if ( fields[i].getType().getName().equals("java.math.BigDecimal")) {
                    cellValue =(BigDecimal.valueOf(Double.valueOf(fieldValue.toString())))
                            .compareTo(BigDecimal.ZERO)<=0?"/":fieldValue.toString();
                } else {
                    cellValue = fieldValue.toString();
                }
                CellStyle cellStyle =styleRow.getCell(cellIndex).getCellStyle();
                cell.setCellStyle(cellStyle);
                cell.setCellValue(cellValue);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return row;
    }

    public static void main(String[] args) {
        System.err.println(SalaryExcelDTO.class.getDeclaredFields()[4].getType().getName().equals("java.math.BigDecimal"));
        test("tsss");
        Object fieldValue = "0.00";
        System.out.println(((BigDecimal.valueOf(Double.valueOf(fieldValue.toString()))).compareTo(BigDecimal.ZERO)<=0?"/":fieldValue.toString()));
    }

    public static void test(String... value) {
        System.out.println(value[value.length-1]);
    }

}

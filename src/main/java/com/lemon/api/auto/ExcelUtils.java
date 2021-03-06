package com.lemon.api.auto;

import com.alibaba.fastjson.JSONObject;
import org.apache.poi.ss.usermodel.*;

import java.io.InputStream;
import java.util.Map;
import java.util.Set;

/**
 * @author: suchunlei
 * @create: 2018-12-19 16:27
 * @description:
 */
public class ExcelUtils {
    public static Object[][] readExcel(String excelPath, int sheetNum) {
        //获取工作簿
        Object[][] datas = null;
        try {
            InputStream inp = ExcelUtils.class.getResourceAsStream(excelPath);
            Workbook workbook = WorkbookFactory.create(inp);
            //获取第一个sheet
            Sheet sheet = workbook.getSheetAt(sheetNum - 1);
            //遍历拿到每一行
            //再遍历每一行拿到每一列
            int lastRowNum = sheet.getLastRowNum();
            //用二维数组接收excel
            datas = new Object[lastRowNum + 1][];
            //System.out.println(lastRowNum);
            for (int i = 0; i <= lastRowNum; i++) {
                //遍历每一行
                Row row = sheet.getRow(i);
                int lastCellNum = row.getLastCellNum();
                //创建一个一维数组，保存该行的所有列信息
                Object[] cellValueArray = new Object[lastCellNum];
                for (int j = 0; j < lastCellNum; j++) {
                    //遍历每一列,获得当前行的每一列
                    Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    cell.setCellType(CellType.STRING);
                    //获得该列的值
                    String cellValue = cell.getStringCellValue();
                    //把当前列的值添加到一维数组中
                    cellValueArray[j] = cellValue;
                    //    System.out.print(cellValue+"   ");
                }
                datas[i] = cellValueArray;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return datas;
//        Row row = sheet.getRow(0);
//        //获取第一列
//        //Cell cell = row.getCell(0);
//        Cell cell = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
//        cell.setCellType(CellType.STRING);
//        String value = cell.getStringCellValue();
//        //System.out.println(value);
    }

    //example:示例检查
    public static void main(String[] args) throws Exception {
//        Object[][] datas = readExcel("/api.xlsx",1);
//        for (Object[] cellValueArray : datas) {
//            for (Object cellValue : cellValueArray) {
//                System.out.print(cellValue+"   ");
//            }
//            System.out.println();
//        }

        String jsonStr = "{\"mobilephone\":\"${mobilephone}\",\"pwd\":\"123456\",\"regname\":\"__getRegName(aa,bb,cc)\"}";
//        Object object = JSONObject.parse(jsonStr);
        Map<String,String>dataMap= (Map<String, String>) JSONObject.parse(jsonStr);
//        System.out.println(object.getClass());
        Set<String> keySet = dataMap.keySet();
        for (String key : keySet) {
            System.out.println(key+":"+dataMap.get(key));
        }
    }
}
package com.jeyson.tools.excel;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.util.List;

/**
 * @Author: liujishuai
 * @Time: 2019-08-09 19:28
 * @Description:
 */
public class ExcelTest {
    public static void main(String[] args) {

        File file = new File("/Users/jeyson/Downloads/709/school.xlsx");
        Workbook workbook = ExcelUtil.getWorkbook(file);
        List<List<Object>> lists = ExcelUtil.getExcelToList(workbook, 0);
        StringBuilder sb= new StringBuilder();
        for (List<Object> list : lists) {
            if(lists.indexOf(list)==0){
                continue;
            }
            if (CollectionUtils.isNotEmpty(list) && list.size() > 1 ) {
                long bossId = new Double((double)list.get(1)).longValue();
                String schoolName = (String)list.get(0);
                String shortEnName = (String)list.get(2);
                String fullEnName = (String)list.get(3);
                String localName = (String)list.get(4);
                String country = (String)list.get(5);
                String schoolCode = (String)list.get(6);
                sb.append("insert into school (level, full_chn_name, short_en_name, full_en_name, local_name,  country, school_type, school_code) ");
                sb.append(" values (10,").append("'").append(schoolName).append("'").append(",").append("'").append(shortEnName).append("'").append(",").append("'").append(fullEnName).append("'").append(",")
                        .append("'").append(localName).append("'").append(",").append("'").append(country).append("'").append(",1,").append("'").append(schoolCode).append("'").append(");");


            }

        }
        System.out.println(sb.toString());
    }
}

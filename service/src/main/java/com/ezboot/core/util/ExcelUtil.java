package com.ezboot.core.util;

import com.ezboot.system.admin.entity.Admin;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.*;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.apache.poi.ss.usermodel.CellType.STRING;

/**
 * HSSF是POI工程对Excel 97(-2007)文件操作的纯Java实现
 * XSSF是POI工程对Excel 2007 OOXML (.xlsx)文件操作的纯Java实现
 * 从POI 3.8版本开始，提供了一种基于XSSF的低内存占用的API----SXSSF
 * SXSSFWorkbook提供了一种低内存占用的EXCEL导出方法，但是没有提供读取文件流的方法。因此读入大数据量的时候还是只能使用XSSFWorkbook来读取。
 */
public class ExcelUtil {

    public static void main(String[] args) {

        // 查询得到用户数据
        List<Admin> adminList = new ArrayList<>();
        Admin admin = new Admin();
        admin.setUsername("a1");
        admin.setPassword("b1");
        admin.setLastLoginIp("127.0.0.1");
        admin.setLastLoginTime(new Date());
        admin.setAvatar("头像1");
        admin.setEnabled(false);
        admin.setCreateName("wang");
        admin.setCreateTime(new Date());
        adminList.add(admin);
        // 定义表格头信息
        String[] titles = new String[]{"用户名", "密码", "最后一次登录ip", "最后一次登录时间", "头像", "软删除", "创建人", "创建时间"};
        // 为表格内容赋值
        String[][] contents = new String[adminList.size()][titles.length];

        String sheetName = "管理员信息表";

        String fileName = "管理员信息表"+System.currentTimeMillis()+".xlsx";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (adminList != null && adminList.size() > 0) {
            for (int i = 0; i < adminList.size(); i++) {
                contents[i][0] = adminList.get(i).getUsername();
                contents[i][1] = adminList.get(i).getPassword();
                contents[i][2] = adminList.get(i).getLastLoginIp();
                contents[i][3] = sdf.format(adminList.get(i).getLastLoginTime());
                contents[i][4] = adminList.get(i).getAvatar();
                contents[i][5] = "";
                contents[i][6] = adminList.get(i).getCreateName();
                contents[i][7] = sdf.format(adminList.get(i).getCreateTime());
            }
        }

        XSSFWorkbook wb = getHSSFWorkbook(sheetName, titles, contents);
        //响应到客户端
        try {
            FileOutputStream os = new FileOutputStream(fileName);
            wb.write(os);
            os.flush();
            os.close();
            System.out.println("成功创建excel文件");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static XSSFWorkbook getHSSFWorkbook(String sheetName, String[] title, String[][] contents) {

        // 第一步，创建一个Excel
        XSSFWorkbook wb = new XSSFWorkbook();

        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        XSSFSheet sheet = wb.createSheet(sheetName);

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        XSSFRow row = sheet.createRow(0);

        //创建标题
        for (int i = 0; i < title.length; i++) {
            XSSFCell cell = row.createCell(i);
            row.setHeightInPoints(50);
            cell.setCellValue(title[i]);
            cell.setCellStyle(getTitleStyle(wb));
        }

        //创建内容
        for (int i = 0; i < contents.length; i++) {
            row = sheet.createRow(i + 1);
            row.setHeightInPoints(30);
            for (int j = 0; j < contents[i].length; j++) {
                //将内容按顺序赋给对应的列对象
                XSSFCell cell = row.createCell(j);
                cell.setCellValue(contents[i][j]);
                cell.setCellStyle(getStyle(wb));
            }
        }
        setSizeColumn(sheet, title.length);
        return wb;
    }


    /**
     * 设置标题样式
     *
     * @param workbook
     * @return
     */
    public static XSSFCellStyle getTitleStyle(XSSFWorkbook workbook) {

        // 设置字体
        XSSFFont font = workbook.createFont();
        //设置字体名字
        font.setFontName("微软雅黑"); //字体
        //设置字体大小
        font.setFontHeightInPoints((short) 11);
        //字体加粗
        font.setBold(true);
        //设置样式;
        XSSFCellStyle style = workbook.createCellStyle();

        XSSFColor xssfColor = new XSSFColor(new Color(70, 114, 120), new DefaultIndexedColorMap());

        style.setFillForegroundColor(xssfColor);

        style.setFillBackgroundColor(xssfColor);

        //设置底边框;
        style.setBorderBottom(BorderStyle.THIN);
        //设置底边框颜色;
        style.setBottomBorderColor(xssfColor);
        //设置左边框;
        style.setBorderLeft(BorderStyle.THIN);
        //设置左边框颜色;
        style.setLeftBorderColor(xssfColor);
        //设置右边框;
        style.setBorderRight(BorderStyle.THIN);
        //设置右边框颜色;
        style.setRightBorderColor(xssfColor);
        //设置顶边框;
        style.setBorderTop(BorderStyle.THIN);
        //设置顶边框颜色;
        style.setTopBorderColor(xssfColor);
        //在样式用应用设置的字体;
        style.setFont(font);
        //设置自动换行;
        style.setWrapText(false);
        //设置水平对齐的样式为居中对齐;
        style.setAlignment(HorizontalAlignment.CENTER);
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }

    /**
     * 设置内容样式
     *
     * @param workbook
     * @return
     */
    public static XSSFCellStyle getStyle(XSSFWorkbook workbook) {

        // 设置字体
        XSSFFont font = workbook.createFont();

        //设置字体名字
        font.setFontName("微软雅黑"); //字体

        //设置字体大小
        font.setFontHeightInPoints((short) 11);

        //字体加粗
        font.setBold(true);

        XSSFCellStyle style = workbook.createCellStyle();

        //设置自动换行;
        style.setWrapText(true);

        //设置水平对齐的样式为居中对齐;
        style.setAlignment(HorizontalAlignment.CENTER);

        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        return style;
    }

    /**
     * 设置列宽自适应
     *
     * @param sheet
     * @param size
     */
    private static void setSizeColumn(XSSFSheet sheet, int size) {
        for (int columnNum = 0; columnNum < size; columnNum++) {
            int columnWidth = sheet.getColumnWidth(columnNum) / 256;
            for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
                XSSFRow currentRow;
                //当前行未被使用过
                if (sheet.getRow(rowNum) == null) {
                    currentRow = sheet.createRow(rowNum);
                } else {
                    currentRow = sheet.getRow(rowNum);
                }

                if (currentRow.getCell(columnNum) != null) {
                    XSSFCell currentCell = currentRow.getCell(columnNum);
                    if (currentCell.getCellType() == STRING) {
                        int length = currentCell.getStringCellValue().getBytes().length;
                        if (columnWidth < length) {
                            columnWidth = length;
                        }
                    }
                }
            }
            sheet.setColumnWidth(columnNum, columnWidth * 256);
        }
    }


}

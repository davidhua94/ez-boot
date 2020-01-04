package com.ezboot.core.util;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.*;

import java.awt.*;

/**
 * HSSF是POI工程对Excel 97(-2007)文件操作的纯Java实现
 * XSSF是POI工程对Excel 2007 OOXML (.xlsx)文件操作的纯Java实现
 * 从POI 3.8版本开始，提供了一种基于XSSF的低内存占用的API----SXSSF
 * SXSSFWorkbook提供了一种低内存占用的EXCEL导出方法，但是没有提供读取文件流的方法。因此读入大数据量的时候还是只能使用XSSFWorkbook来读取。
 */
public class ExcelUtil {

    /**
     * 设置标题样式
     * @param workbook
     * @return
     */
    public XSSFCellStyle getTitleStyle(XSSFWorkbook workbook) {

        // 设置字体
        XSSFFont font = workbook.createFont();
        //设置字体名字
        font.setFontName("宋体"); //字体
        //设置字体大小
        font.setFontHeightInPoints((short)11);
        //字体加粗
        font.setBold(true);
        //设置样式;
        XSSFCellStyle style = workbook.createCellStyle();

        XSSFColor xssfColor = new XSSFColor(new Color(70, 114, 120), new DefaultIndexedColorMap());

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
        style.setWrapText(true);
        //设置水平对齐的样式为居中对齐;
        style.setAlignment(HorizontalAlignment.CENTER);
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }

    /**
     * 设置内容样式
     * @param workbook
     * @return
     */
    public XSSFCellStyle getStyle(XSSFWorkbook workbook) {

        // 设置字体
        XSSFFont font = workbook.createFont();

        //设置字体名字
        font.setFontName("宋体"); //字体

        //设置字体大小
        font.setFontHeightInPoints((short)11);

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


}

package com.jwk.common.utils;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
public class ExcelCommonUtil {
	 public static HSSFWorkbook getHSSFWorkbook(String sheetName,String []title,String [][]values, HSSFWorkbook wb){
         // 第一步，创建一个webbook，对应一个Excel文件  
        if(wb == null){
            wb = new HSSFWorkbook();
        }
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet(sheetName);  
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow(0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        
        sheet.autoSizeColumn((short)0); //调整第一列宽度
        sheet.autoSizeColumn((short)1); //调整第二列宽度
        sheet.autoSizeColumn((short)2); //调整第三列宽度
        sheet.autoSizeColumn((short)3); //调整第四列宽度
        sheet.autoSizeColumn((short)4); //调整第一列宽度
        sheet.autoSizeColumn((short)5); //调整第二列宽度
        sheet.autoSizeColumn((short)6); //调整第三列宽度
        sheet.autoSizeColumn((short)7); //调整第四列宽度
        sheet.autoSizeColumn((short)8); //调整第一列宽度
        sheet.autoSizeColumn((short)9); //调整第二列宽度
        sheet.autoSizeColumn((short)10); //调整第三列宽度
        sheet.autoSizeColumn((short)11); //调整第四列宽度
        sheet.autoSizeColumn((short)12); //调整第一列宽度
        sheet.autoSizeColumn((short)13); //调整第二列宽度
        sheet.autoSizeColumn((short)14); //调整第三列宽度
        sheet.autoSizeColumn((short)15); //调整第四列宽度
        sheet.autoSizeColumn((short)16); //调整第一列宽度
        sheet.autoSizeColumn((short)17); //调整第二列宽度
        sheet.autoSizeColumn((short)18); //调整第三列宽度
        sheet.autoSizeColumn((short)19); //调整第四列宽度
        sheet.autoSizeColumn((short)20); //调整第一列宽度
        sheet.autoSizeColumn((short)21); //调整第二列宽度
        sheet.autoSizeColumn((short)22); //调整第三列宽度
        sheet.autoSizeColumn((short)23); //调整第四列宽度
        sheet.autoSizeColumn((short)24); //调整第一列宽度
        sheet.autoSizeColumn((short)25); //调整第二列宽度
        sheet.autoSizeColumn((short)26); //调整第三列宽度
        sheet.autoSizeColumn((short)27); //调整第四列宽度
        
        HSSFCell cell = null;  
        //创建标题
        for(int i=0;i<title.length;i++){
            cell = row.createCell(i);  
            cell.setCellValue(title[i]);  
            cell.setCellStyle(style);  
        }
        
        
        //***************************************************
       
        BufferedImage bufferImg = null;//图片一  
        BufferedImage bufferImg1 = null;//图片二  
        try { 
            // 先把读进来的图片放到一个ByteArrayOutputStream中，以便产生ByteArray  
            ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();  
            ByteArrayOutputStream byteArrayOut1 = new ByteArrayOutputStream();  
              
            //将两张图片读到BufferedImage  
           /* bufferImg = ImageIO.read(new File("d:/img/syj.jpg"));  
            bufferImg1 = ImageIO.read(new File("d:/img/rj.jpg"));  
            ImageIO.write(bufferImg, "jpg", byteArrayOut);  
            ImageIO.write(bufferImg1, "jpg", byteArrayOut1); */ 
  
           
  
            //HSSFPatriarch patriarch = sheet.createDrawingPatriarch();  
            /** 
             * 该构造函数有8个参数 
             * 前四个参数是控制图片在单元格的位置，分别是图片距离单元格left，top，right，bottom的像素距离 
             * 后四个参数，前连个表示图片左上角所在的cellNum和 rowNum，后天个参数对应的表示图片右下角所在的cellNum和 rowNum， 
             * excel中的cellNum和rowNum的index都是从0开始的 
             *  
             */  
            //图片一导出到单元格B2中  
           /* HSSFClientAnchor anchor = new HSSFClientAnchor(0,0, 0, 0,  
                    (short) 1, 1, (short) 2, 2);  
            //图片二导出到单元格C3到E5中，且图片的left和top距离边框50  
            HSSFClientAnchor anchor1 = new HSSFClientAnchor(10, 10, 0, 0,  
                    (short) 2, 1, (short) 3, 2);  
  
            // 插入图片  
            patriarch.createPicture(anchor, wb.addPicture(byteArrayOut  
                    .toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));  
            patriarch.createPicture(anchor1, wb.addPicture(byteArrayOut1  
                    .toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));  */
            //***************************************************
            
          //创建内容
            for(int i=0;i<values.length;i++){
                row = sheet.createRow(i + 1); 
                for(int j=0;j<values[i].length;j++){
                	if(j==1){
                		//bufferImg = ImageIO.read(new File(values[i][j])); 
                		bufferImg = ImageIO.read(new File("d:/img/rj.jpg"));
                        ImageIO.write(bufferImg, "jpg", byteArrayOut); 
                        HSSFPatriarch patriarchimg = sheet.createDrawingPatriarch();  
                      //图片一导出到单元格B2中  
                        HSSFClientAnchor anchorimg = new HSSFClientAnchor(0,0, 0, 0,  
                                (short) 1, i + 1, (short) 2, i + 2);  
                        // 插入图片  
                        patriarchimg.createPicture(anchorimg, wb.addPicture(byteArrayOut  
                                .toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG)); 
                        
                	}else if(j==2){
                		//bufferImg = ImageIO.read(new File(values[i][j]));
                		bufferImg1 = ImageIO.read(new File("d:/img/rj.jpg"));
                        ImageIO.write(bufferImg1, "jpg", byteArrayOut1); 
                        HSSFPatriarch patriarchimg1 = sheet.createDrawingPatriarch();  
                      //图片一导出到单元格B2中  
                        HSSFClientAnchor anchorimg1 = new HSSFClientAnchor(10,10, 0, 0,  
                                (short) 2, i + 1, (short) 3 , i + 2);  
                        // 插入图片  
                        patriarchimg1.createPicture(anchorimg1, wb.addPicture(byteArrayOut1  
                                .toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
                	}else{
                		row.createCell(j).setCellValue(values[i][j]);
                	}
                     
                }
            }
        }catch (Exception e) {
            System.out.println(e.toString());
        }
        
       return wb;
    }
}

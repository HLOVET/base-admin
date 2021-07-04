package com.yaraculture.resource.util;

import com.yaraculture.resource.common.exception.BizException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

import static com.yaraculture.resource.common.exception.ErrorCodeEnum.IMPORT_FILE_FAILED;

/**
 * 导入csv文件
 */
public class CsvImportUtil {

    //上传文件的路径
    private final static String PATH = "C://importDir//";

    /**
     * @return File  一般文件类型
     * @Description 上传文件的文件类型
     * @Param multipartFile
     **/
    public static File uploadFile(MultipartFile multipartFile) {
        try {
            // 获 取上传 路径
            String path = PATH + multipartFile.getOriginalFilename();
            // 通过将给定的路径名字符串转换为抽象路径名来创建新的 File实例
            File file = new File(path);
            // 此抽象路径名表示的文件或目录是否存在
            if (!file.getParentFile().exists()) {
                // 创建由此抽象路径名命名的目录，包括任何必需但不存在的父目录
                file.getParentFile().mkdirs();
            }
            // 转换为一般file 文件
            multipartFile.transferTo(file);

            return file;
        } catch (Exception e) {
            throw BizException.build(IMPORT_FILE_FAILED);
        }
    }

    /**
     * @return List<List<String>>
     * @Description 读取CSV文件的内容（不含表头）
     * @Param filePath 文件存储路径，colNum 列数
     **/
    public static List<CSVRecord> readCSV(File filePath, String[] fileHeader) {
            CSVFormat format = CSVFormat.DEFAULT.withHeader(fileHeader).withSkipHeaderRecord();
            // 这是从上面写入的文件中读出数据的代码
            try(BufferedReader in =new BufferedReader(new InputStreamReader(new FileInputStream(filePath),"UTF-8"));) {
                CSVParser csvParser = format.parse(in);
                return csvParser.getRecords();
//                Iterable<CSVRecord> records = format.parse(in);
//                for (CSVRecord record : records) {
//                    strID = record.get("达人昵称");
//                    strName = record.get("个人主页链接");
//                    System.out.println(strID + " " + strName);
//                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return Collections.EMPTY_LIST;
    }

}
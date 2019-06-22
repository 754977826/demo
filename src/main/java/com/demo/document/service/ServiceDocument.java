package com.demo.document.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.common.CommonException;
import com.demo.document.bean.DocumentBean;
import com.demo.document.bean.Paragraph;
import com.demo.document.dao.DocumentRepository;
import com.demo.utils.DateUtil;
import com.demo.utils.ResultUtil;
import com.demo.utils.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

/**
 * @author nieyawei
 * @version 1.0
 * @className: ServiceWord
 * @description:
 * @date 2019-06-12 20:43
 */
@Slf4j
@Service
public class ServiceDocument {
    @Value("${document.path}")
    private String documentPath;
    @Autowired
    private DocumentRepository documentRepository;

    public List<DocumentBean> getDocList(){
        return documentRepository.findAll();
    }

    public JSONObject updateDoc() {
        InputStream is = null;
        XWPFDocument doc = null;
        try {
            is = new FileInputStream("D:\\temp\\test.docx");
            doc = new XWPFDocument(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*List<XWPFParagraph> paras = doc.getParagraphs();
        for (
                XWPFParagraph para : paras) {
            //当前段落的属性
            //CTPPr pr = para.getCTP().getPPr();
            System.out.println(para.getText());
        }*/
        JSONArray response = new JSONArray();
        int paragraphCount = 0;
        List<IBodyElement> list = doc.getBodyElements();
        for (int i = 0; i < list.size(); i++) {
            IBodyElement element = list.get(i);
            BodyElementType type = element.getElementType();
            System.out.println(type);
            if (BodyElementType.PARAGRAPH == type) {
                IBody body = element.getBody();
                XWPFParagraph para = body.getParagraphArray(paragraphCount);
                paragraphCount ++;
                //CTPPr pr = para.getCTP().getPPr();
                List<XWPFRun> runsLists = para.getRuns();//获取段楼中的句列表
                for (int j = 0; j < runsLists.size(); j++) {
                    XWPFRun run = runsLists.get(j);
                    String color = run.getColor();//获取句的字体颜色
                    int size = run.getFontSize();//获取句中字的大小
                    String text = run.getText(0);//获取文本内容
                    String fontName = run.getFontName();
                    System.out.println(color);
                    System.out.println(size);
                    System.out.println(text);
                    System.out.println(fontName);
                    System.out.println("---------------");
                    Paragraph paragraph = new Paragraph();
                    paragraph.setColor(color);
                    paragraph.setFontSize(size);
                    paragraph.setText(text);
                    paragraph.setFontName(fontName);
                    response.add(JSONObject.toJSON(paragraph));
                }
            }

        }

        //获取文档中所有的表格
        /*List<XWPFTable> tables = doc.getTables();
        List<XWPFTableRow> rows;
        List<XWPFTableCell> cells;
        for (XWPFTable table : tables) {
            //表格属性
            //CTTblPr pr = table.getCTTbl().getTblPr();
            //获取表格对应的行
            rows = table.getRows();
            for (XWPFTableRow row : rows) {
                //获取行对应的单元格
                cells = row.getTableCells();
                for (XWPFTableCell cell : cells) {
                    System.out.println(cell.getText());
                    ;
                }
            }
        }*/
        close(is);
        return ResultUtil.getDataJSON("0", "成功！", response);
    }


    /**
     * 关闭输入流
     *
     * @param is
     */
    private static void close(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public JSONObject saveDocument(DocumentBean document) {
        //document.setId(UUIDUtil.getUUID());
        document.setPath(documentPath);
        documentRepository.save(document);
        JSONObject json = new JSONObject();
        json.put("errCode", "0");
        json.put("errInfo", "保存成功！");
        return json;
    }


    public JSONObject saveDocumentFile(MultipartFile in) {
        String fileName = in.getOriginalFilename();
        String type = getType(fileName);
        if (type == null) {
            return ResultUtil.getJSON("1", "文件格式不正确！");
        }
        DocumentBean document = new DocumentBean();
        //document.setId(UUIDUtil.getUUID());
        document.setFileName(UUIDUtil.getUUID() + "_" + fileName);
        document.setType(type);
        document.setPath(documentPath);
        documentRepository.saveAndFlush(document);
        File filepath = new File(documentPath);
        if (!filepath.exists()) {
            filepath.mkdirs();
        }
        // 将上传文件保存到一个目标文档中

        File file = new File(documentPath + fileName);
        try {
            in.transferTo(file);
        } catch (IOException e) {
            DateUtil.ERROR_COUNT++;
            log.info("文件保存错误：", e);
            throw new CommonException("9", "文件上传失败！");
        }
        return ResultUtil.getJSON("0", "保存成功！");
    }

    private String getType(String fileName) {
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        if (suffix.equalsIgnoreCase("doc") || suffix.equalsIgnoreCase("docx")) {
            return "0";
        } else if (suffix.equalsIgnoreCase("xls") || suffix.equalsIgnoreCase("xlsx")) {
            return "1";
        }

        return null;
    }
}

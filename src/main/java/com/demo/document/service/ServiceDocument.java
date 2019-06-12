package com.demo.document.service;

import com.alibaba.fastjson.JSONObject;
import com.demo.common.CommonException;
import com.demo.document.bean.DocumentBean;
import com.demo.document.dao.DocumentRepository;
import com.demo.utils.DateUtil;
import com.demo.utils.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

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

    public JSONObject saveDocument(DocumentBean document) {
        //document.setId(UUIDUtil.getUUID());
        document.setPath(documentPath);
        documentRepository.save(document);
        JSONObject json = new JSONObject();
        json.put("errCode", "0");
        json.put("errInfo", "保存成功！");
        return json;
    }

    ;

    public JSONObject saveDocumentFile(MultipartFile in) {
        String fileName = in.getOriginalFilename();
        String type = getType(fileName);
        if(type == null){
            return getJSON("1", "文件格式不正确！");
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
        return getJSON("0", "保存成功！");
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

    private JSONObject getJSON(String errCode, String errInfo){
        JSONObject json = new JSONObject();
        json.put("errCode", errCode);
        json.put("errInfo", errInfo);
        return json;
    }
}

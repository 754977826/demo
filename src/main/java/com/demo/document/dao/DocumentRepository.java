package com.demo.document.dao;

import com.demo.document.bean.DocumentBean;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author nieyawei
 * @version 1.0
 * @className: DocumentRepository
 * @description:
 * @date 2019-06-12 20:39
 */

public interface DocumentRepository extends JpaRepository<DocumentBean, String> {
}

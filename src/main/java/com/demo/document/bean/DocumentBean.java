package com.demo.document.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author nieyawei
 * @version 1.0
 * @className: DocumentBean
 * @description:
 * @date 2019-06-12 20:32
 */

@Entity
@Table(name = "tab_document")
@Getter
@Setter
@NoArgsConstructor
public class DocumentBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column
    private String fileName;
    @Column
    private String type;
    @Column
    private String path;
}

package com.files.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer id;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date date;

    @Column
    public String baseDirectory;

    @Column
    public Integer dirCount;

    @Column
    public Integer fileCount;

    @Column
    public String sumSize;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "address")
    private List<Files> filesList;

    public Address() {
    }

    public Address(Date date, String baseDirectory, Integer dirCount, Integer fileCount, String sumSize, List<Files> filesList) {
        this.date = date;
        this.baseDirectory = baseDirectory;
        this.dirCount = dirCount;
        this.fileCount = fileCount;
        this.sumSize = sumSize;
        this.filesList = filesList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getBaseDirectory() {
        return baseDirectory;
    }

    public void setBaseDirectory(String baseDirectory) {
        this.baseDirectory = baseDirectory;
    }

    public Integer getDirCount() {
        return dirCount;
    }

    public void setDirCount(Integer dirCount) {
        this.dirCount = dirCount;
    }

    public Integer getFileCount() {
        return fileCount;
    }

    public void setFileCount(Integer fileCount) {
        this.fileCount = fileCount;
    }

    public String getSumSize() {
        return sumSize;
    }

    public void setSumSize(String sumSize) {
        this.sumSize = sumSize;
    }

    public List<Files> getFilesList() {
        return filesList;
    }

    public void setFilesList(List<Files> filesList) {
        this.filesList = filesList;
    }
}

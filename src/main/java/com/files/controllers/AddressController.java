package com.files.controllers;

import com.files.model.Address;
import com.files.model.Files;
import com.files.service.AddressService;
import com.files.service.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Controller
public class AddressController {

    private static final Logger logger = Logger.getLogger(AddressController.class);

    @Autowired
    private final AddressService addressService;

    @Autowired
    private final FilesService filesService;

    public AddressController(AddressService addressService, FilesService filesService) {
        this.addressService = addressService;
        this.filesService = filesService;
    }

    @PostMapping("/addAddress")
    public String addPost(@ModelAttribute Address address, Model model) throws Exception {

        File file = new File(address.getBaseDirectory());

        if (!file.exists()) {
            logger.warn("address.getBaseDirectory() is not exists");
            throw new Exception("address.getBaseDirectory() is not exists");
        }

        if (!file.isDirectory()) {
            logger.warn("address.getBaseDirectory() is not directory");
            throw new Exception("address.getBaseDirectory() is not directory");
        }

        Files files;
        List<Files> listFiles = new ArrayList<>();
        List<Files> listDir = new ArrayList<>();
        File fileCur = new File(address.getBaseDirectory());
        int countDir = 0;
        int countFile = 0;
        double sumSize = 0;
        Date date = new Date();
        address.setDate(date);

        for (String current : file.list()) {
            fileCur = new File(address.getBaseDirectory() + "\\" + current);
            files = new Files();
            files.setAddress(address);
            files.setName(fileCur.getName());
            if (fileCur.isFile()) {
                files.setSize(checkSize((double)fileCur.length()));
                sumSize = sumSize + fileCur.length();
                countFile++;
                listFiles.add(files);
            } else {
                listDir.add(files);
                files.setSize("<DIR>");
                countDir++;
            }
        }

        address.setDirCount(countDir);
        address.setFileCount(countFile);
        address.setSumSize(checkSize(sumSize));

        addressService.save(address);

        logger.info("Was add address id = " + address.getId());

        for (Files curDir:listDir) {
            filesService.save(curDir);
            logger.info("Was add dir id = " + curDir.getId());
        }

        listFiles.sort((o1,o2)-> o1.getName().compareTo(o2.getName()));
        for (Files curFile:listFiles) {
            filesService.save(curFile);
            logger.info("Was add file id = " + curFile.getId());
        }

        logger.info("Was add address id = " + address.getId());

        return "redirect:/address";
    }

    private String checkSize(double size){
        String result = "";
        long value = (long) size;
        if (Long.toString(value).length() >=3){
            result = String.format("%(.2f ", size/1000) + "Kb";
        }
        if (Long.toString(value).length() >=6){
            result = String.format("%(.2f ", size/1000000) + "Mb";
        }
        if (Long.toString(value).length() >=9){
            result = String.format("%(.2f ", size/1000000000) + "Gb";
        }
        return result;
    }

    @GetMapping("/address")
    public String list(Model model) {

        logger.info("Page open /address");

        List<Address> list = addressService.findAll();
        model.addAttribute("listAddress", list);

        model.addAttribute("address", new Address());

        return "address/list";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }
}

package com.files.controllers;

import com.files.model.Address;
import com.files.model.Files;
import com.files.service.AddressService;
import com.files.service.FilesService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class FilesController {

    private static final Logger logger = Logger.getLogger(AddressController.class);

    @Autowired
    private final AddressService addressService;

    @Autowired
    private final FilesService filesService;


    public FilesController(AddressService addressService, FilesService filesService) {
        this.addressService = addressService;
        this.filesService = filesService;
    }

    @PostMapping("/showFiles")
    public String list(@ModelAttribute("id") int id,
                       Model model) {

        logger.info("Page open /files");

        Address address = addressService.findById(id);
        List<Files> listFiles = address.getFilesList();
        listFiles.sort((o1,o2)-> o1.compareTo(o2));

        model.addAttribute("listFiles", listFiles);
        model.addAttribute("idAddress", address.getId());
        model.addAttribute("nameAddress", address.getBaseDirectory());

        return "files/list";
    }
}

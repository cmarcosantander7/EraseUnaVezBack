package com.proyectointegrador.proyecto.Controllers;

import com.proyectointegrador.proyecto.DTO.ImageUtility;
import com.proyectointegrador.proyecto.Models.Image;
import com.proyectointegrador.proyecto.Models.ImageModel;
import com.proyectointegrador.proyecto.Repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

@RestController
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(path = "check")

    public class ImageController {

    @Autowired
    ImageRepository imageRepository;


    @GetMapping(path = {"/get/imagessss/{name}"})
    public ResponseEntity<byte[]> getImage(@PathVariable("name") String name) throws IOException {

        final Optional<ImageModel> dbImage = imageRepository.findByName(name);

        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(dbImage.get().getType()))
                .body(ImageUtility.decompressImage(dbImage.get().getPic()));
    }



}
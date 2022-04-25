package com.proyectointegrador.proyecto.Controllers;

import com.proyectointegrador.proyecto.DTO.ImageUtility;
import com.proyectointegrador.proyecto.DTO.ImagenModelResponse;
import com.proyectointegrador.proyecto.DTO.PostResponse;
import com.proyectointegrador.proyecto.Models.Image;
import com.proyectointegrador.proyecto.Repositories.ImgRepository;
import com.proyectointegrador.proyecto.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController

public class ImgController {
    private PostService postService;
    @Autowired
    ImgRepository imageRepository;


    @PostMapping("/upload/image")
    public Image uplaodImage(@RequestParam("image") MultipartFile file)
            throws IOException {
        Image img = (Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .image(ImageUtility.compressImage(file.getBytes())).build());
  final Image savedImage = imageRepository.save(img);

        return savedImage;
    }

    @GetMapping(path = {"/get/image/info/{id}"})
    public Image getImageDetails(@PathVariable("id") Long id) throws IOException {

        final Optional<Image> dbImage = imageRepository.findById(id);

        return Image.builder()
                .id(dbImage.get().getId())
                .name(dbImage.get().getName())
                .type(dbImage.get().getType())
                .image(ImageUtility.decompressImage(dbImage.get().getImage())).build();
    }

    @GetMapping(path = {"/get/image/{id}"})
    public ResponseEntity<byte[]> getImage(@PathVariable("id") Long id) throws IOException {

        final Optional<Image> dbImage = imageRepository.findById(id);

        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(dbImage.get().getType()))
                .body(ImageUtility.decompressImage(dbImage.get().getImage()));
    }


    @GetMapping(path = {"fi/{id}"})
    public ResponseEntity<ImagenModelResponse> listbyimg(@PathVariable("id") Long id) {
        ImagenModelResponse Responses = postService.listimgt(id);
        return new ResponseEntity<>(Responses, HttpStatus.OK);
    }
}

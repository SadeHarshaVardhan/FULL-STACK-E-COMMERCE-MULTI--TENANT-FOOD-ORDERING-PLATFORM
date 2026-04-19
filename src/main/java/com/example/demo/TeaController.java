package com.example.demo;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class TeaController {

    @Autowired
    TeaService t;

    @Autowired
    DropboxService dropboxService;

    @PostMapping("/alltea/insert")
    public ResponseEntity<String> insert(@RequestParam String name,
                                         @RequestParam("file") MultipartFile file,
                                         @RequestParam int price,
                                         @RequestParam String det) {
        try (InputStream in = file.getInputStream()) {
            String fileName = file.getOriginalFilename();
            String link = dropboxService.uploadFileAndGetLink(in,fileName);

            String msg = t.indet(name, link, price, det);
            return ResponseEntity.ok("Uploaded and saved successfully Status→ " + msg + "\nLink: " + link);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Upload failed: " + e.getMessage());
        }
    }

    @DeleteMapping("/alltea/delete/{id}")
    public String delete(@PathVariable int id) throws Exception {

        return t.dodel(id);
    }

    @PutMapping("/alltea/update/")
    public ResponseEntity<String> update(
            @RequestParam int id,
            @RequestParam String name,
            @RequestParam("file") MultipartFile file,
            @RequestParam int price,
            @RequestParam String det) throws Exception {
        if(file.isEmpty())
        {
            String link1=null;
            String msg1 = t.doupdate(id,name, link1, price, det);
            return ResponseEntity.ok("Uploaded and saved successfully → " + msg1+ "\nLink: " + link1);
        }

        try (InputStream in = file.getInputStream()) {
            String fileName = file.getOriginalFilename();
            String link = dropboxService.uploadFileAndGetLinkForUpdate(in, fileName);

            String msg = t.doupdate(id,name, link, price, det);
            return ResponseEntity.ok("Uploaded and saved successfully → " + msg + "\nLink: " + link);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Upload failed: " + e.getMessage());
        }
    }



    @GetMapping("/alltea")
    public List<Tea> getdet() throws Exception

    {
        return t.getdet();
    }
}

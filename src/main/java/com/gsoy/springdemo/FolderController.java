package com.gsoy.springdemo;

import io.swagger.v3.oas.annotations.Operation;
import nonapi.io.github.classgraph.json.JSONSerializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class FolderController {

    List<String> folderList = new ArrayList<>();

    @GetMapping("/folder")
    @Operation(summary = "Klasör silme işlemi")
    public String greeting(@RequestParam(name = "id", required = false, defaultValue = "1") String id) throws IOException {
        return Delete(id);
    }

    private String Delete(String id) throws IOException {
        folderList.clear();
        String filepath;

        return JSONSerializer.serializeObject(folderList);
    }

}

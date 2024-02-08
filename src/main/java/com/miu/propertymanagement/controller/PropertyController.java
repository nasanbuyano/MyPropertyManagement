package com.miu.propertymanagement.controller;

import com.miu.propertymanagement.domain.dto.PropertyRequestDTO;
import com.miu.propertymanagement.domain.dto.PropertyResponseDTO;
import com.miu.propertymanagement.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
@CrossOrigin("*")
public class PropertyController {
    @Autowired
    private PropertyService propertyService;

    @GetMapping("/property")
    ResponseEntity<?> getAllProperty() {
        return ResponseEntity.ok(propertyService.getAllProperty());
    }

    @GetMapping("property/{id}")
    ResponseEntity<?> getPropertyById(@PathVariable("id") Integer id
    ) {
        return ResponseEntity.ok(propertyService.getPropertyById(id));
    }

    @PutMapping("/property/{id}")
    ResponseEntity<?> updateProperty(@PathVariable("id") Integer id, @RequestBody PropertyRequestDTO propertyRequestDTO) {
        PropertyResponseDTO updatedPropertyDTO = propertyService.updateProperty(id, propertyRequestDTO);
        return updatedPropertyDTO == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(updatedPropertyDTO);
    }

    @DeleteMapping("/property/{id}")
    ResponseEntity<?> deleteProduct(@PathVariable("id") Integer id) {
        PropertyResponseDTO propertyResponseDTO = propertyService.deleteProperty(id);
        return propertyResponseDTO == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(propertyResponseDTO);
    }
}

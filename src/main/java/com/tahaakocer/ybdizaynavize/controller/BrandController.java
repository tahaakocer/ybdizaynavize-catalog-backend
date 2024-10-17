package com.tahaakocer.ybdizaynavize.controller;

import com.tahaakocer.ybdizaynavize.dto.BrandDto;
import com.tahaakocer.ybdizaynavize.dto.response.BrandResponse;
import com.tahaakocer.ybdizaynavize.mapper.BrandMapper;
import com.tahaakocer.ybdizaynavize.service.IBrandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/brands")
public class BrandController {

    private final IBrandService brandService;
    private final BrandMapper brandMapper;

    public BrandController(IBrandService brandService, BrandMapper brandMapper) {
        this.brandService = brandService;
        this.brandMapper = brandMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<BrandResponse> create(@RequestParam String name) {
        BrandDto brandDto = this.brandService.create(name);
        return ResponseEntity.ok(this.brandMapper.dtoToResponse(brandDto));
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<BrandResponse>> getAll() {
        return ResponseEntity.ok(this.brandMapper.dtoListToResponseList(this.brandService.getAll()));
    }

    @GetMapping("/get-by-id")
    public ResponseEntity<BrandResponse> getById(@RequestParam Long id) {
        return ResponseEntity.ok(this.brandMapper.dtoToResponse(this.brandService.getById(id)));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam Long id) {
        return ResponseEntity.ok(this.brandService.delete(id));
    }
}

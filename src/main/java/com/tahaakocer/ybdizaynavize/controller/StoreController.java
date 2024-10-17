package com.tahaakocer.ybdizaynavize.controller;

import com.tahaakocer.ybdizaynavize.dto.StoreDto;
import com.tahaakocer.ybdizaynavize.dto.response.StoreResponse;
import com.tahaakocer.ybdizaynavize.mapper.StoreMapper;
import com.tahaakocer.ybdizaynavize.service.IStoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stores")
public class StoreController {
    private final IStoreService storeService;
    private final StoreMapper storeMapper;

    public StoreController(IStoreService storeService, StoreMapper storeMapper) {
        this.storeService = storeService;
        this.storeMapper = storeMapper;
    }
    @PostMapping("/create")
    public ResponseEntity<StoreResponse> create(@RequestParam String name) {
        return ResponseEntity.ok().body(this.storeMapper.dtoToResponse(
                this.storeService.create(name)));
    }
    @GetMapping("/get-by-id")
    public ResponseEntity<StoreResponse> getById(@RequestParam Long id) {
        return ResponseEntity.ok().body(this.storeMapper.dtoToResponse(this.storeService.get(id)));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam Long id) {
        return ResponseEntity.ok().body(this.storeService.delete(id));
    }
    @GetMapping("/get-all")
    public ResponseEntity<List<StoreResponse>> getAll() {
        return ResponseEntity.ok().body(this.storeMapper.dtoListToResponseList(this.storeService.getAll()));
    }

}

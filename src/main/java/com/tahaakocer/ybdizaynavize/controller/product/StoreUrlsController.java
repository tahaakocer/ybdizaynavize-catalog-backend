package com.tahaakocer.ybdizaynavize.controller.product;

import com.tahaakocer.ybdizaynavize.dto.product.response.StoreUrlsResponse;
import com.tahaakocer.ybdizaynavize.mapper.product.StoreUrlsMapper;
import com.tahaakocer.ybdizaynavize.service.product.IStoreUrlsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/store-urls")
public class StoreUrlsController {
    private final IStoreUrlsService storeUrlsService;
    private final StoreUrlsMapper storeUrlsMapper;

    public StoreUrlsController(IStoreUrlsService storeUrlsService, StoreUrlsMapper storeUrlsMapper) {
        this.storeUrlsService = storeUrlsService;
        this.storeUrlsMapper = storeUrlsMapper;
    }
    @PostMapping("/create")
    public ResponseEntity<StoreUrlsResponse> create(@RequestParam Long storeId, @RequestParam String value) {
        return ResponseEntity.ok().body(
                this.storeUrlsMapper.dtoToResponse(
                        this.storeUrlsService.create(storeId, value)));
    }
    @GetMapping("/get-by-id")
    public ResponseEntity<StoreUrlsResponse> getById(@RequestParam Long id) {
        StoreUrlsResponse response = storeUrlsMapper.dtoToResponse(storeUrlsService.get(id));
        return ResponseEntity.ok().body(response);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam Long id) {
        return ResponseEntity.ok().body(storeUrlsService.delete(id));
    }
    @GetMapping("/get-all")
    public ResponseEntity<List<StoreUrlsResponse>> getAll() {
        return ResponseEntity.ok().body(storeUrlsMapper.dtoListToResponseList(storeUrlsService.getAll()));
    }
}

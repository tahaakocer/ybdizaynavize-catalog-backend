package com.tahaakocer.ybdizaynavize.dto.response;

import com.tahaakocer.ybdizaynavize.dto.StoreDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreUrlsResponse {
    private Long id;
    private StoreDto store;
    private String url;
}

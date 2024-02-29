package com.wenjuju.top.bean;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean<T> {
    @Schema(description = "总数")

    private Long total;//总条数
    @Schema(description = "数据集合")

    private List<T> items;//数据集合
}

package com.wenjuju.top.bean;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
//    @NotNull
//    @NotEmpty
    private Integer id;//主键id
    @NotEmpty
    @Schema(description = "分类名称")

    private String categoryName;//分类名称
    @NotEmpty
    @Schema(description = "分类别名")
    private String categoryAlias;//分类别名
    @Schema(description = "创建人ID")

    private Integer createUser;//创建人ID
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
}

package com.wenjuju.top.bean;

import com.wenjuju.top.anno.State;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;
@Data

//文章实现类
public class Article {
    private Integer id;//主键id
    @NotEmpty
    @Pattern(regexp = "^\\S{1,10}$")
    @Schema(description = "文章标题")
    private String title;//文章标题
    @NotEmpty
    @Schema(description = "文章内容")
    private String content;//文章内容
    @NotEmpty
    @URL
    @Schema(description = "文章封面")
    private String coverImg;//文章封面
    @State
    @Schema(description = "文章状态-已发布/草稿")

    private String state;//文章状态
    @NotNull
    @Schema(description = "文章分类id")

    private Integer categoryId;//文章分类id
    @Schema(description = "创建人id")

    private Integer createUser;//创建人id
    @Schema(description = "创建时间")

    private LocalDateTime createTime;//创建时间
    @Schema(description = "更新时间")

    private LocalDateTime updateTime;//更新时间
}

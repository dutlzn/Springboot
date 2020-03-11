package study.demo.param;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 接受前端param参数 校验参数
 */
@Data
public class User2Param {
    private long id;
    @NotNull(message = "姓名不能为空")
    private String userName;
    @NotEmpty(message = "密码不能为空")
    @Length(min = 6,message = "密码长度不能小于6位")
    private String passWord;
    @Max(value = 100,message = "年龄不能超过100岁")
    @Min(value = 18,message = "年纪不能小于18岁")
    private int age;

}

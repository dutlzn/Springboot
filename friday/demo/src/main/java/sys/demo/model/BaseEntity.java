package sys.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
//@Builder
public abstract class BaseEntity<ID extends Serializable>
        implements Serializable {
    private static final long serialVersionUID = 8925514045582235838L;
    private ID id; // id
    private Date createTime = new Date(); // 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd  HH:mm:ss")
    private Date updateTime = new Date(); // 更新时间
}
package net.xdclass.dto.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestBodyDTO {

    /**
     * 存储消息的主体内容
     * 这里使用String类型来保存消息的文本内容
     */
    private String body;

    /**
     * 表示消息主体的类型
     * 用于描述消息体的格式，例如JSON、XML等
     */
    private String bodyType;
}

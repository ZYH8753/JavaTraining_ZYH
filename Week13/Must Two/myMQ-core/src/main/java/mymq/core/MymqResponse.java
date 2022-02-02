package mymq.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MymqResponse {
    boolean status;
    String errorMsg;
    MymqMessage mymqMessage;
}

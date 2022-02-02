package mymq.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString
public class MymqMessage {
    public static final int PRODUCE_MODE = 1;
    public static final int CONSUME_MODE = 2;

    int mode;
    String topic;
    String message;
}

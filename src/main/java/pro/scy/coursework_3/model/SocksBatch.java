package pro.scy.coursework_3.model;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocksBatch {
    private Socks socks;
    private int quantity;

}

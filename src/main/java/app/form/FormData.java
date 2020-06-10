package app.form;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FormData {
    private String email;
    private String name;
    private String surname;
    private String status;
    private String password;
    private String pswRepeat;
    private String url;
}

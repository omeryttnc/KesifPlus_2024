package pojos;

import lombok.*;

@AllArgsConstructor
@Getter // butun property lere etki edecek
//@ToString
//@Setter
//@Data // diger butun annotationlari kapsiyor (Constructur haric)
public class LombokClassRecord {
    private  String name;
    @Setter // ama sadece email setter i olacak
    private  String email;
}

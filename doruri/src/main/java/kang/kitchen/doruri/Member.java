package kang.kitchen.doruri;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Member {

    @Id
    @Column
    private String oauthId;

    @Column
    private String name;

    @Column
    private String providerName;

    @Column
    private String accessToken;

    @Column
    private String allergy_list;




    public Member(String oauthId, String name, String providerName, String accessToken, String allergy_list) {

        this.oauthId = oauthId;
        this.name = name;
        this.providerName = providerName;
        this.accessToken = accessToken;
        this.allergy_list = allergy_list;
    }
}
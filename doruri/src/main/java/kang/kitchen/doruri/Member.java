package kang.kitchen.doruri;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


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
    private String allergyList;


    public Member(String oauthId, String name, String providerName, String accessToken, String allergyList) {

        this.oauthId = oauthId;
        this.name = name;
        this.providerName = providerName;
        this.accessToken = accessToken;
        this.allergyList = allergyList;
    }

    public void update(String allergyList) {
        this.allergyList = allergyList;
    }

}
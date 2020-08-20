package kang.kitchen.doruri;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FoodStar {

    @Id
    private Long prdlstReportNo;

    @Column
    private Float rating;

    @Column
    private Integer participant;

    @ManyToMany
    private List<Member> members = new ArrayList<Member>();


    public FoodStar(Long prdlstReportNo,Float rating, Integer participant) {
        this.prdlstReportNo = prdlstReportNo;
        this.rating = rating;
        this.participant = participant;
    }
}
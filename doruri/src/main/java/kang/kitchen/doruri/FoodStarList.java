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
public class FoodStarList {

    @Id
    private Long prdlstReportNo;

    @Column
    private Long id;


    public FoodStarList(Long prdlstReportNo,Long id) {
        this.prdlstReportNo = prdlstReportNo;
        this.id = id;
    }
}

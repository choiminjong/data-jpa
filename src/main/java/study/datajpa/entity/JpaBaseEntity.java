package study.datajpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public class JpaBaseEntity {

    @Column(updatable = false)
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;

    @PrePersist
    //저장전
    public void prePersist(){
        LocalDateTime now = LocalDateTime.now();
        this.createdDate = now;
        this.updateDate = now;
    }

    @PreUpdate
    //저장후
    public void preUpdate(){
        this.updateDate = LocalDateTime.now();
    }


}

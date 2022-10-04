package com.portfolio.AC.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "experience")
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 100, message = "should has min 3 chars and max 100 chars")
    private String position;

    @Size(min = 3, max = 100, message = "should has min 3 chars and max 100 chars")
    private String company;

    @Size(max = 1500, message = "should has min 3 chars and max 1500 chars")
    private String description;

    private String company_img;

    @NotNull
    //@Temporal(TemporalType.DATE)
    private java.sql.Date start_date;
    //@Temporal(TemporalType.DATE)
    private java.sql.Date finish_date;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "user_id")
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

//    @ManyToOne()
//    @JoinColumn(name = "user_id")
//    private User user;
}

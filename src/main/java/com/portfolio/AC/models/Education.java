package com.portfolio.AC.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "education")
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 100, message = "should has min 3 chars and max 100 chars")
    private String title;

    @Size(min = 3, max = 100, message = "should has min 3 chars and max 100 chars")
    private String institute;

    @Size(min = 3, max = 1500, message = "should has min 3 chars and max 1500 chars")
    private String description;

    @NotNull
    //@Temporal(TemporalType.DATE)
    private java.sql.Date start_date;
    //@Temporal(TemporalType.DATE)
    private java.sql.Date finish_date;

    @NotNull(message = "User field cannot be null")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;
}

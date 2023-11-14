package br.com.profitness.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Embeddable
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@FlywayDataSource
@Table(name = "Address")
public class Address {

    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "UUIDGenerator")
    @SequenceGenerator(name = "ADDRESS_ID_SEQ", sequenceName = "ADDRESS_ID_SEQ", allocationSize = 1)
    private UUID id;

    @Column(nullable = false, length = 50)
    private String street;

    @Column(nullable = false, length = 4)
    private Integer number;

    @Column(length = 20)
    private String complement;

    @Column(nullable = false, length = 20)
    private String neigborhood;

    @Column(nullable = false)
    private String zipCode;

    @Column(length = 20)
    private String reference;

    @Column(nullable = false, length = 30)
    private String city;

    @Column(nullable = false, length = 20)
    private String state;

    @Column(nullable = false, length = 15)
    private String country;

    @OneToMany(mappedBy = "address", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
//    @OneToMany(mappedBy = "address", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Client> client;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private boolean deleted;

    @Column(nullable = false)
    private Instant createdAt;

    @Column(nullable = true)
    private Instant updatedAt;

    @Column(nullable = true)
    private Instant deletedAt;

}

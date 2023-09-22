package br.com.profitness.models;

import br.com.profitnes.annotation.Cpf;
import br.com.profitness.dto.client.ClientDto;
import br.com.profitness.dto.client.ClientInput;
import br.com.profitness.dto.client.ClientUpdateDto;
import br.com.profitness.util.MapperUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;


@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@FlywayDataSource
@Table(name = "Client")
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

//    @ApiModelProperty(value = "Código do Cliente")
    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "UUIDGenerator")
    @SequenceGenerator(name = "CLIENT_ID_SEQ", sequenceName = "CLIENT_ID_SEQ", allocationSize = 1)
    private UUID id;

    @Version
    private Long jversion;

    @Column(nullable = false, length = 100)
    private String fullName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    @Cpf
    @Column(nullable = false, unique = true, length = 14)
    private String cpf;

    @Column(nullable = false, unique = true, length = 13)
    private String rg;

    @Column(nullable = false, length = 2)
    private String ddd;

    @Column(nullable = false, length = 10)
    private String phoneNumber;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String sex;

    @Column(nullable = false)
    private boolean active;

    @Column
    private boolean deleted;

    @Column(nullable = false)
    private Instant createdAt;

    @Column
    private Instant updatedAt;

    @Column
    private Instant deletedAt;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    public Client(String fullName, LocalDate birthDate, String cpf, String rg, String ddd,  String phoneNumber, String email, String sex, Instant createdAt, Instant updatedAt, Instant deletedAt, boolean active, boolean deleted){
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.cpf = cpf;
        this.rg = rg;
        this.ddd = ddd;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.sex = sex;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.active = active;
        this.deleted = deleted;
    }

//    public static Client newClient(String fullName, LocalDate birthDate, String cpf, String rg, String ddd,  String phoneNumber, String email, String sex, boolean active){
//        final Instant now = InstantUtils.now();
//        return new Client( fullName,  birthDate, cpf, rg,  ddd, phoneNumber, email, sex, now, false, false,true, false);
//    }


    public ClientInput toDto(){
        return MapperUtil.converte(this, ClientInput.class);
    }

    public ClientDto clienteDto(){
        return MapperUtil.converte(this, ClientDto.class);
    }

    public Client toObject() {
        return MapperUtil.converte(this, Client.class);
    }

    public ClientUpdateDto clienteUpdateToDto(){
        return MapperUtil.getMapper().map(this, ClientUpdateDto.class);
    }

}

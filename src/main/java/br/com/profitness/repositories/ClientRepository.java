package br.com.profitness.repositories;

import br.com.profitness.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {

    boolean existsByCpf(String cpf);
    boolean existsByRg(String rg);


}

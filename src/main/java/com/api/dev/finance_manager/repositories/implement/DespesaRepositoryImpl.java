package com.api.dev.finance_manager.repositories.implement;

import com.api.dev.finance_manager.enums.DespesaStatus;
import com.api.dev.finance_manager.model.Despesa;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DespesaRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Despesa> find(Long id, String destino, Date data, DespesaStatus despesaStatus){
        StringBuilder jpql = new StringBuilder();
        jpql.append("from Despesa WHERE 0 = 0 ");
        Map<String,Object> mapaParametros = new HashMap<>();

        if(id != null){
            jpql.append(" and id = :id ");
            mapaParametros.put("id",id);
        }
        if(destino != null){
            jpql.append(" and destino LIKE :destino ");
            mapaParametros.put("destino","%"+destino+"%");
        }
        if(data != null){
            jpql.append(" and DATE(data) = :data");
            mapaParametros.put("data",data);
        }

        if(despesaStatus != null){
            jpql.append("and despesaStatus LIKE :despesaStatus");
            if(despesaStatus == DespesaStatus.PAGA){
                mapaParametros.put("despesaStatus",DespesaStatus.PAGA);
            }
            if(despesaStatus == DespesaStatus.PENDENTE){
                mapaParametros.put(("despesaStatus"),DespesaStatus.PENDENTE);
            }
        }

        TypedQuery<Despesa> query = entityManager.createQuery(jpql.toString(),Despesa.class);

        mapaParametros.forEach((chave,valor) -> query.setParameter(chave,valor));
        return query.getResultList();
    }

}

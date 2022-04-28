package com.api.dev.finance_manager.repositories.implement;

import com.api.dev.finance_manager.enums.DespesaCategoria;
import com.api.dev.finance_manager.enums.DespesaStatus;
import com.api.dev.finance_manager.model.Despesa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DespesaRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public Page<Despesa> find(Long id, String destino, Date data, DespesaStatus despesaStatus, DespesaCategoria despesaCategoria, Pageable pageable){
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
            switch (despesaStatus){
                case PAGA: mapaParametros.put("despesaStatus",DespesaStatus.PAGA);break;
                case PENDENTE: mapaParametros.put("despesaStatus",DespesaStatus.PENDENTE);break;
            }
        }
        if(despesaCategoria != null){
            jpql.append(" and despesaCategoria LIKE :categoria");
            switch (despesaCategoria){
                case EDUCACAO: mapaParametros.put("categoria",DespesaCategoria.EDUCACAO);break;
                case COMIDA: mapaParametros.put("categoria",DespesaCategoria.COMIDA);break;
                case SAUDE: mapaParametros.put("categoria",DespesaCategoria.SAUDE);break;
                case LAZER: mapaParametros.put("categoria",DespesaCategoria.LAZER);break;
            }
        }

        TypedQuery<Despesa> query = entityManager.createQuery(jpql.toString(),Despesa.class);

        mapaParametros.forEach((chave,valor) -> query.setParameter(chave,valor));
        List<Despesa> listaResultado = query.getResultList();
        return new PageImpl<>(listaResultado, pageable, listaResultado.size());
    }

    public void updateDespesa(Long id, Despesa despesa){
        StringBuilder jpql = new StringBuilder();

        Map<String,Object> parametros = new HashMap<>();

        jpql.append("UPDATE Despesa SET id = :id");
        if(despesa.getDestino() != null){
            jpql.append(", destino = :destino");
            parametros.put("destino", despesa.getDestino());
        }
        if(despesa.getData() != null){
            jpql.append(", data = :data");
            parametros.put("data",despesa.getData());
        }
        if(despesa.getDespesaStatus() != null){
            jpql.append(", despesaStatus = :status");
            switch (despesa.getDespesaStatus()){
                case PENDENTE: parametros.put("status",DespesaStatus.PENDENTE);break;
                case PAGA: parametros.put("status",DespesaStatus.PAGA);break;
            }
        }
        if(despesa.getDespesaCategoria() != null){
            jpql.append(", despesaCategoria = :categoria");
            switch (despesa.getDespesaCategoria()){
                case LAZER: parametros.put("categoria",DespesaCategoria.LAZER);break;
                case SAUDE: parametros.put("categoria",DespesaCategoria.SAUDE);break;
                case COMIDA:parametros.put("categoria",DespesaCategoria.COMIDA);break;
                case EDUCACAO: parametros.put("categoria",DespesaCategoria.EDUCACAO);break;
            }
        }
        jpql.append(" WHERE id = :id");
        parametros.put("id",id);

        Query queryUpdate = this.entityManager.createQuery(jpql.toString());
        parametros.forEach((key,value) -> queryUpdate.setParameter(key,value));
        queryUpdate.executeUpdate();
    }

    public void deleteById(Long id){
        Query queryDelete = this.entityManager.createQuery("DELETE FROM Despesa WHERE id = :id");
        queryDelete.setParameter("id",id);
        queryDelete.executeUpdate();
    }

}

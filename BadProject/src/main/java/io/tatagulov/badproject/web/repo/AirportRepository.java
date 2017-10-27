package io.tatagulov.badproject.web.repo;

import io.tatagulov.badproject.web.dto.airport.AirportListRequest;
import io.tatagulov.badproject.web.entity.AirportsEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;

@Repository
public class AirportRepository extends CrudRepository<AirportsEntity,String> {

    @Override
    public Class<AirportsEntity> getType() {
        return AirportsEntity.class;
    }

    public List<AirportsEntity> getList(AirportListRequest request) {
        int offSet = request.getOffset();
        int limit = request.getLimit();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<AirportsEntity> q = cb.createQuery(AirportsEntity.class);
        Root<AirportsEntity> c = q.from(AirportsEntity.class);
        List<Predicate> predicates = new LinkedList<>();

        if (request.getAirportCode()!=null) {
            predicates.add(cb.equal(c.get("airportCode"),request.getAirportCode()));
        }
        if (request.getAirportStartName()!=null) {
            predicates.add(cb.like(cb.upper(c.get("airportName")),request.getAirportStartName().toUpperCase()+"%"));
        }
        if (request.getCityStartName()!=null) {
            predicates.add(cb.like(cb.upper(c.get("city")),request.getCityStartName().toUpperCase()+"%"));
        }
        q.select(c).where(predicates.toArray(new Predicate[predicates.size()]));
        return entityManager.createQuery(q).setFirstResult(offSet).setMaxResults(limit).getResultList();
    }
}

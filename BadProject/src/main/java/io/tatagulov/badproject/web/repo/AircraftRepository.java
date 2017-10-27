package io.tatagulov.badproject.web.repo;

import io.tatagulov.badproject.web.dto.aircraft.AircraftListRequest;
import io.tatagulov.badproject.web.entity.AircraftsEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;

@Repository
public class AircraftRepository extends CrudRepository<AircraftsEntity,String> {

    @Override
    public Class<AircraftsEntity> getType() {
        return AircraftsEntity.class;
    }

    public List<AircraftsEntity> getList(AircraftListRequest aircraftListRequest) {
        int offSet = aircraftListRequest.getOffset();
        int limit = aircraftListRequest.getLimit();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<AircraftsEntity> q = cb.createQuery(AircraftsEntity.class);
        Root<AircraftsEntity> c = q.from(AircraftsEntity.class);
        List<Predicate> predicates = new LinkedList<>();

        if (aircraftListRequest.getCode()!=null) {
            predicates.add(cb.equal(c.get("aircraftCode"),aircraftListRequest.getCode()));
        }
        if (aircraftListRequest.getRangeStart()!=null) {
            predicates.add(cb.greaterThanOrEqualTo(c.get("range"),aircraftListRequest.getRangeStart()));
        }
        if (aircraftListRequest.getRangeEnd()!=null) {
            predicates.add(cb.lessThanOrEqualTo(c.get("range"),aircraftListRequest.getRangeEnd()));
        }
        q.select(c).where(predicates.toArray(new Predicate[predicates.size()]));
        return entityManager.createQuery(q).setFirstResult(offSet).setMaxResults(limit).getResultList();
    }
}

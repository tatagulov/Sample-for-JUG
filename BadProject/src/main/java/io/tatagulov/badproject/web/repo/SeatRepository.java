package io.tatagulov.badproject.web.repo;

import io.tatagulov.badproject.web.dto.seat.SeatListRequest;
import io.tatagulov.badproject.web.entity.SeatsEntity;
import io.tatagulov.badproject.web.entity.SeatsEntityPK;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;

@Repository
public class SeatRepository extends CrudRepository<SeatsEntity,SeatsEntityPK> {

    @Override
    public Class<SeatsEntity> getType() {
        return SeatsEntity.class;
    }

    public List<SeatsEntity> list(SeatListRequest request) {
        int offSet = request.getOffset();
        int limit = request.getLimit();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<SeatsEntity> q = cb.createQuery(SeatsEntity.class);
        Root<SeatsEntity> c = q.from(SeatsEntity.class);
        List<Predicate> predicates = new LinkedList<>();

        if (request.getAircraftCode()!=null) {
            predicates.add(cb.equal(c.get("aircraftCode"),request.getAircraftCode()));
        }
        if (request.getFareConditions()!=null) {
            predicates.add(cb.equal(c.get("fareConditions"),request.getFareConditions()));
        }

        q.select(c).where(predicates.toArray(new Predicate[predicates.size()]));
        return entityManager.createQuery(q).setFirstResult(offSet).setMaxResults(limit).getResultList();
    }
}

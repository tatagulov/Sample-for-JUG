package io.tatagulov.badproject.web.repo;

import io.tatagulov.badproject.web.dto.ticketflight.TicketFlightListRequest;
import io.tatagulov.badproject.web.entity.TicketFlightsEntity;
import io.tatagulov.badproject.web.entity.TicketFlightsEntityPK;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;

@Repository
public class TicketFlightRepository  extends CrudRepository<TicketFlightsEntity,TicketFlightsEntityPK> {

    @Override
    public Class<TicketFlightsEntity> getType() {
        return TicketFlightsEntity.class;
    }

    public List<TicketFlightsEntity> list(TicketFlightListRequest request) {
        int offSet = request.getOffset();
        int limit = request.getLimit();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<TicketFlightsEntity> q = cb.createQuery(TicketFlightsEntity.class);
        Root<TicketFlightsEntity> c = q.from(TicketFlightsEntity.class);
        List<Predicate> predicates = new LinkedList<>();

        if (request.getFlightId()!=null) {
            predicates.add(cb.equal(c.get("flightId"),request.getFlightId()));
        }
        if (request.getTicketNo()!=null) {
            predicates.add(cb.equal(c.get("ticketNo"),request.getTicketNo()));
        }

        q.select(c).where(predicates.toArray(new Predicate[predicates.size()]));
        return entityManager.createQuery(q).setFirstResult(offSet).setMaxResults(limit).getResultList();
    }
}

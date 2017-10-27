package io.tatagulov.badproject.web.repo;

import io.tatagulov.badproject.web.dto.ticket.TicketListRequest;
import io.tatagulov.badproject.web.entity.TicketsEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;

@Repository
public class TicketRepository extends CrudRepository<TicketsEntity,String> {
    @Override
    public Class<TicketsEntity> getType() {
        return TicketsEntity.class;
    }

    public List<TicketsEntity> list(TicketListRequest request) {
        int offSet = request.getOffset();
        int limit = request.getLimit();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<TicketsEntity> q = cb.createQuery(TicketsEntity.class);
        Root<TicketsEntity> c = q.from(TicketsEntity.class);
        List<Predicate> predicates = new LinkedList<>();

        if (request.getPassengerName()!=null) {
            predicates.add(cb.equal(c.get("passengerName"),request.getPassengerName()));
        }

        if (request.getPassengerId()!=null) {
            predicates.add(cb.equal(c.get("passengerId"),request.getPassengerId()));
        }

        if (request.getBookRef()!=null) {
            predicates.add(cb.equal(c.get("bookRef"),request.getBookRef()));
        }

        if (request.getTicketNo()!=null) {
            predicates.add(cb.equal(c.get("ticketNo"),request.getTicketNo()));
        }

        q.select(c).where(predicates.toArray(new Predicate[predicates.size()]));
        return entityManager.createQuery(q).setFirstResult(offSet).setMaxResults(limit).getResultList();
    }
}

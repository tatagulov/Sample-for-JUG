package io.tatagulov.badproject.web.repo;

import io.tatagulov.badproject.web.dto.booking.BookingListRequest;
import io.tatagulov.badproject.web.entity.BookingsEntity;
import io.tatagulov.badproject.web.util.Utils;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

@Repository
public class BookingRepository extends CrudRepository<BookingsEntity,String> {

    @Override
    public Class<BookingsEntity> getType() {
        return BookingsEntity.class;
    }


    public List<BookingsEntity> list(BookingListRequest request) {
        int offSet = request.getOffset();
        int limit = request.getLimit();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<BookingsEntity> q = cb.createQuery(BookingsEntity.class);
        Root<BookingsEntity> c = q.from(BookingsEntity.class);
        List<Predicate> predicates = new LinkedList<>();

        if (request.getStartDate()!=null) {
            Date date = Utils.dateParse(request.getStartDate());
            predicates.add(cb.greaterThanOrEqualTo(c.get("bookDate").as(Date.class),date));
        }
        if (request.getEndDate()!=null) {
            Date date = Utils.dateParse(request.getEndDate());
            predicates.add(cb.lessThanOrEqualTo(c.get("bookDate").as(Date.class),date));
        }
        if (request.getMaxTotalAmmount()!=null) {
            predicates.add(cb.lessThanOrEqualTo(c.get("totalAmount"),request.getMaxTotalAmmount()));
        }

        q.select(c).where(predicates.toArray(new Predicate[predicates.size()]));
        return entityManager.createQuery(q).setFirstResult(offSet).setMaxResults(limit).getResultList();
    }
}
